package com.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.server.domain.Board;
import com.server.domain.BoardFeeling;
import com.server.domain.Category;
import com.server.dto.board.AuthenticatedShowBoardDTO;
import com.server.dto.board.CreateBoardDTO;
import com.server.dto.board.ShowBoardDTO;
import com.server.dto.board.UpdateBoardDTO;
import com.server.dto.comment.CreateCommentDTO;
import com.server.dto.comment.UpdateCommentDTO;
import com.server.dto.response.SimpleResponseDTO;
import com.server.dto.response.SuccessfulResponseDTO;
import com.server.exception.board.BoardNotExistException;
import com.server.securityconfig.TokenProvider;
import com.server.service.BoardService;
import com.server.service.CommentService;
import com.server.service.MemberService;
import com.server.service.ReplyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/boards")
@Api(description = "게시판 관련 REST API")
public class BoardController {

	@Autowired
	TokenProvider tokenProvider;

	@Autowired
	BoardService boardService;

	@Autowired
	MemberService memberService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	ReplyService replyService;

	@ApiOperation(value = "특정 모집글 가져오기")
	@GetMapping("/{boardId}")
	public ResponseEntity<?> getBoard(@PathVariable(name = "boardId") long boardId, @RequestParam(required=false) String memberId,
			HttpServletRequest req, HttpServletResponse res) {

		Cookie[] cookies = req.getCookies();
		Cookie viewCookie = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("view" + boardId)) {
					viewCookie = cookie;
				}
			}
		}
		if (viewCookie == null) {
			int result = boardService.updateBoardCnt(boardId);
			if (result == 0) {
				throw new BoardNotExistException();
			}
			Cookie cookie = new Cookie("view" + boardId, null);
			cookie.setMaxAge(60 * 60 * 24);
			res.addCookie(cookie);
		}
		//if (memberId == null) {
			List<Object[]> findBoard = boardService.getBoard(boardId);
			ShowBoardDTO boardDto = new ShowBoardDTO(findBoard);
			SuccessfulResponseDTO<ShowBoardDTO> response = SuccessfulResponseDTO.<ShowBoardDTO>builder().code(1)
					.message("게시글 조회 성공").data(boardDto).build();

			return ResponseEntity.ok().body(response);
	/*	} else {
			Object[] findBoard = boardService.getBoard(boardId);
			BoardFeeling bf = boardService.getBoardFeeling(memberId, boardId);
			//AuthenticatedShowBoardDTO dto = new AuthenticatedShowBoardDTO(findBoard,bf);
			
			SuccessfulResponseDTO<AuthenticatedShowBoardDTO> response = SuccessfulResponseDTO.<AuthenticatedShowBoardDTO>builder().code(1)
					.message("게시글 조회 성공").data(dto).build();

			return ResponseEntity.ok().body(response);
		}*/

	}

	@PostMapping("/cookies")
	@ApiOperation(value = "쿠키생성")
	public ResponseEntity<?> makeCookies(HttpServletRequest req, HttpServletResponse res) {

		for (int i = 101; i <= 200; i++) {
			Cookie cookie = new Cookie("ck" + i, null);
			cookie.setMaxAge(30 * 60 * 50);
			res.addCookie(cookie);
		}

		SimpleResponseDTO response = SimpleResponseDTO.builder().code(1).message("쿠키 생성 성공").build();
		return ResponseEntity.ok().body(response);

	}

	@DeleteMapping("/cookies")
	@ApiOperation(value = "쿠키삭제")
	public ResponseEntity<?> deleteCookie(HttpServletRequest req, HttpServletResponse res) {
		Cookie[] cookies = req.getCookies();
		HashMap<String, String> map = new HashMap<String, String>();
		if (cookies != null) {
			for (Cookie cookie : cookies) {

				map.put(cookie.getName(), cookie.getValue());
				cookie.setMaxAge(0);
				res.addCookie(cookie);
			}
		}

		SuccessfulResponseDTO<Map<String, String>> response = SuccessfulResponseDTO.<Map<String, String>>builder()
				.code(1).message("쿠키삭제성공").data(map).build();
		return ResponseEntity.ok().body(response);

	}

	@GetMapping("/cookies")
	@ApiOperation(value = "쿠키조회")
	public ResponseEntity<?> searchCookies(HttpServletRequest req, HttpServletResponse res) {
		Cookie[] cookies = req.getCookies();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("size", cookies.length + "");
		if (cookies != null) {
			System.out.println(cookies.length);
			for (Cookie cookie : cookies) {
				map.put(cookie.getName(), cookie.getValue());
			}
		}

		SuccessfulResponseDTO<Map<String, String>> response = SuccessfulResponseDTO.<Map<String, String>>builder()
				.code(1).message("쿠키조회성공").data(map).build();
		return ResponseEntity.ok().body(response);

	}

	@ApiOperation(value = "전체 모집글목록 가져오기")
	@GetMapping
	public ResponseEntity<?> getBoardList(@RequestParam String category, @RequestParam("page") int page,
			@RequestParam("pageSize") int pageNum) {
		Page<Board> boardList = boardService.getBoardList(Category.valueOf(category), page, pageNum);

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Integer> pageInfo = new HashMap<String, Integer>();
		List<ShowBoardDTO> boardDtoList = new ArrayList<ShowBoardDTO>();
		for (Board b : boardList.getContent()) {
			//boardDtoList.add(new ShowBoardDTO(b));

			map.put("items", boardDtoList);
			pageInfo.put("currentPage", boardList.getNumber() + 1);
			pageInfo.put("pageSize", boardList.getSize());
			pageInfo.put("totalPage", boardList.getTotalPages());
			pageInfo.put("totalCount", boardList.getNumberOfElements());
			map.put("page", pageInfo);

		}
		SuccessfulResponseDTO<Map<String, Object>> response = SuccessfulResponseDTO.<Map<String, Object>>builder()
				.code(1).message("모집글 목록 조회 성공").data(map).build();
		return ResponseEntity.ok().body(response);
	}

	@ApiOperation(value = "모집글 생성하기")
	@PostMapping
	public ResponseEntity<?> createBoard(@ApiIgnore @AuthenticationPrincipal String id,
			@RequestBody @Valid CreateBoardDTO dto) {

		boardService.createBoard(dto.toEntity(memberService.getMember(id)));
		SimpleResponseDTO response = SimpleResponseDTO.builder().code(1).message("글 생성 성공").build();
		return ResponseEntity.ok().body(response);

	}

	@ApiOperation(value = "모집글 삭제하기")
	@DeleteMapping("/{boardId}")
	public ResponseEntity<?> deleteBoard(@PathVariable long boardId) {

		boardService.deleteBoard(boardId);
		SimpleResponseDTO response = SimpleResponseDTO.builder().code(1).message("게시글 삭제 성공").build();
		return ResponseEntity.ok().body(response);
	}

	@ApiOperation(value = "모집글 수정하기")
	@PutMapping("/{boardId}")
	public ResponseEntity<?> updateBoard(@PathVariable long boardId, @Valid @RequestBody UpdateBoardDTO dto) {

		boardService.updateBoard(boardId, dto);
		SimpleResponseDTO response = SimpleResponseDTO.builder().code(1).message("게시글 수정 성공").build();
		return ResponseEntity.ok().body(response);
	}

	@ApiOperation(value = "모집글 좋아요 누르기")
	@PutMapping("/{boardId}/like")
	public ResponseEntity<?> addLike(@RequestParam String memberId, @PathVariable long boardId) {
		boardService.like(memberId, boardId);
		SimpleResponseDTO response = SimpleResponseDTO.builder().code(1).message("좋아요 요청 성공").build();
		return ResponseEntity.ok().body(response);
	}

	@ApiOperation(value = "모집글 싫어요 누르기")
	@PutMapping("/{boardId}/dislike")
	public ResponseEntity<?> addDisLike(@RequestParam String memberId, @PathVariable long boardId) {
		boardService.disLike(memberId, boardId);
		SimpleResponseDTO response = SimpleResponseDTO.builder().code(1).message("싫어요 요청 성공").build();
		return ResponseEntity.ok().body(response);
	}
	
	
}
