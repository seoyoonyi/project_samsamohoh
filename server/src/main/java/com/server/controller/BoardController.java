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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.server.domain.Board;
import com.server.domain.BoardFeeling;
import com.server.domain.Category;
import com.server.dto.board.AuthenticatedShowBoardDTO;
import com.server.dto.board.CreateBoardDTO;
import com.server.dto.board.ShowBoardDTO;
import com.server.dto.board.ShowBoardListDTO;
import com.server.dto.board.UpdateBoardDTO;
import com.server.dto.response.SimpleResponseDTO;
import com.server.dto.response.SuccessfulResponseDTO;
import com.server.exception.board.BoardNotExistException;
import com.server.securityconfig.TokenProvider;
import com.server.service.BoardService;
import com.server.service.CommentService;
import com.server.service.MemberService;
import com.server.service.ReplyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/boards")
@Tag(name="Board",description="게시글 API")
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

	@Operation(summary="특정 게시글 조회",description="게시글 아이디(PK)를 통해 게시글을 조회")
	@GetMapping("/{boardId}")
	public ResponseEntity<?> getBoard(@Parameter(description="게시글의 아이디(PK)")@PathVariable(name = "boardId") long boardId,
			@Parameter(description="Bearer {token}")@RequestHeader(value = "Authorization", required = false) String token, HttpServletRequest req,
			HttpServletResponse res) {

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
		
		String memberId = null;
		
		if (token != null) {
			 memberId = tokenProvider.getMemberId(token.substring(7));
		}

		if (memberId == null) {
			Board findBoard = boardService.getBoard(boardId);
			ShowBoardDTO boardDto = new ShowBoardDTO(findBoard);
			SuccessfulResponseDTO<ShowBoardDTO> response = SuccessfulResponseDTO.<ShowBoardDTO>builder().code(1)
					.message("게시글 조회 성공").data(boardDto).build();

			return ResponseEntity.ok().body(response);
		} else {
			Board findBoard = boardService.getBoard(boardId);
			BoardFeeling bf = boardService.getBoardFeeling(memberId, boardId);
			AuthenticatedShowBoardDTO dto = new AuthenticatedShowBoardDTO(findBoard, bf);
			SuccessfulResponseDTO<AuthenticatedShowBoardDTO> response = SuccessfulResponseDTO
					.<AuthenticatedShowBoardDTO>builder().code(1).message("게시글 조회 성공").data(dto).build();

			return ResponseEntity.ok().body(response);
		}

	}

	/*
	 * @PostMapping("/cookies")
	 * 
	 * @ApiOperation(value = "쿠키생성") public ResponseEntity<?>
	 * makeCookies(HttpServletRequest req, HttpServletResponse res) {
	 * 
	 * for (int i = 101; i <= 200; i++) { Cookie cookie = new Cookie("ck" + i,
	 * null); cookie.setMaxAge(30 * 60 * 50); res.addCookie(cookie); }
	 * 
	 * SimpleResponseDTO response =
	 * SimpleResponseDTO.builder().code(1).message("쿠키 생성 성공").build(); return
	 * ResponseEntity.ok().body(response);
	 * 
	 * }
	 */

	/*
	 * @DeleteMapping("/cookies")
	 * 
	 * @ApiOperation(value = "쿠키삭제") public ResponseEntity<?>
	 * deleteCookie(HttpServletRequest req, HttpServletResponse res) { Cookie[]
	 * cookies = req.getCookies(); HashMap<String, String> map = new HashMap<String,
	 * String>(); if (cookies != null) { for (Cookie cookie : cookies) {
	 * 
	 * map.put(cookie.getName(), cookie.getValue()); cookie.setMaxAge(0);
	 * res.addCookie(cookie); } }
	 * 
	 * SuccessfulResponseDTO<Map<String, String>> response =
	 * SuccessfulResponseDTO.<Map<String, String>>builder()
	 * .code(1).message("쿠키삭제성공").data(map).build(); return
	 * ResponseEntity.ok().body(response);
	 * 
	 * }
	 */

	/*
	 * @GetMapping("/cookies")
	 * 
	 * @ApiOperation(value = "쿠키조회") public ResponseEntity<?>
	 * searchCookies(HttpServletRequest req, HttpServletResponse res) { Cookie[]
	 * cookies = req.getCookies(); HashMap<String, String> map = new HashMap<String,
	 * String>(); map.put("size", cookies.length + ""); if (cookies != null) {
	 * System.out.println(cookies.length); for (Cookie cookie : cookies) {
	 * map.put(cookie.getName(), cookie.getValue()); } }
	 * 
	 * SuccessfulResponseDTO<Map<String, String>> response =
	 * SuccessfulResponseDTO.<Map<String, String>>builder()
	 * .code(1).message("쿠키조회성공").data(map).build(); return
	 * ResponseEntity.ok().body(response);
	 * 
	 * }
	 */

	@Operation(summary="게시글 리스트 조회",description="카테고리,페이지,페이지당 데이터갯수를 이용하여 게시글 리스트 조회")
	@GetMapping
	public ResponseEntity<?> getBoardList(@Parameter(description="카테고리")@RequestParam Category category, @Parameter(description="페이지")@RequestParam int page,
			@Parameter(description="페이지당 데이터 갯수")@RequestParam int pageSize) {
		Page<Board> boardList = boardService.getBoardList(category, page, pageSize);

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Integer> pageInfo = new HashMap<String, Integer>();
		List<ShowBoardListDTO> boardDtoList = new ArrayList<ShowBoardListDTO>();
		for (Board b : boardList.getContent()) {
			boardDtoList.add(new ShowBoardListDTO(b));

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

	@Operation(summary="게시글 생성",description="토큰과 게시글 생성 DTO이용하여 게시글 생성")
	@PostMapping
	public ResponseEntity<?> createBoard(@Parameter(description="Bearer {토큰}")@RequestHeader(value = "Authorization") String token,
			@Parameter(description="카테고리,게시글 제목,게시글 내용 입력")@RequestBody @Valid CreateBoardDTO dto) {
		String memberId = tokenProvider.getMemberId(token.substring(7));
		boardService.createBoard(dto.toEntity(memberService.getMember(memberId)));

		SimpleResponseDTO response = SimpleResponseDTO.builder().code(1).message("글 생성 성공").build();
		return ResponseEntity.ok().body(response);

	}

	@Operation(summary="게시글 삭제",description="게시판 아이디(PK)를 이용하여 게시글 삭제")
	@DeleteMapping("/{boardId}")
	public ResponseEntity<?> deleteBoard(@Parameter(description="게시글 아이디(PK)")@PathVariable long boardId) {

		boardService.deleteBoard(boardId);
		SimpleResponseDTO response = SimpleResponseDTO.builder().code(1).message("게시글 삭제 성공").build();
		return ResponseEntity.ok().body(response);
	}

	@Operation(summary="게시글 수정",description="게시글 아이디(PK)와 카테고리,게시글 제목,게시글 내용을 포함하는 DTO를 이용하여 게시글 삭제")
	@PutMapping("/{boardId}")
	public ResponseEntity<?> updateBoard(@Parameter(description="게시글 아이디(PK)")@PathVariable long boardId,@Parameter(description="카테고리,게시글 제목,게시글 입력") @Valid @RequestBody UpdateBoardDTO dto) {

		boardService.updateBoard(boardId, dto);
		SimpleResponseDTO response = SimpleResponseDTO.builder().code(1).message("게시글 수정 성공").build();
		return ResponseEntity.ok().body(response);
	}

	@Operation(summary="게시글 좋아요 누르기",description="토큰과 게시글 아이디(PK)를 이용하여 게시글에 좋아요누르기")
	@PutMapping("/{boardId}/like")
	public ResponseEntity<?> addLike(@Parameter(description="Bearer {token}")@RequestHeader(value = "Authorization") String token,@Parameter(description="게시글 아이디(PK)") @PathVariable long boardId) {
		String memberId = tokenProvider.getMemberId(token.substring(7));
		boardService.like(memberId, boardId);
		SimpleResponseDTO response = SimpleResponseDTO.builder().code(1).message("좋아요 요청 성공").build();
		return ResponseEntity.ok().body(response);
	}

	@Operation(summary="게시글 실어요 누르기",description="토큰과 게시글 아이디(PK)를 이용하여 게시글에 싫어요 누르기")
	@PutMapping("/{boardId}/dislike")
	public ResponseEntity<?> addDisLike(@Parameter(description="Bearer {token}")@RequestHeader String token,@Parameter(description="게시글 아이디(PK)") @PathVariable long boardId) {
		String memberId = tokenProvider.getMemberId(token.substring(7));
		boardService.disLike(memberId, boardId);
		SimpleResponseDTO response = SimpleResponseDTO.builder().code(1).message("싫어요 요청 성공").build();
		return ResponseEntity.ok().body(response);
	}

}
