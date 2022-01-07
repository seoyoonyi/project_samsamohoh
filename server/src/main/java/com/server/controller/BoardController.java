package com.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.server.domain.Board;
import com.server.domain.RegionEnum;
import com.server.dto.board.CreateBoardDTO;
import com.server.dto.board.ShowBoardDTO;
import com.server.dto.response.FailedResponseDTO;
import com.server.dto.response.SimpleResponseDTO;
import com.server.dto.response.SuccessfulResponseDTO;
import com.server.responsecode.StatusCode;
import com.server.securityconfig.JwtTokenProvider;
import com.server.service.BoardService;
import com.server.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/boards")
@Api(description="게시판 관련 REST API")
public class BoardController {

	@Autowired
	JwtTokenProvider jtp;

	@Autowired
	BoardService boardService;

	@Autowired
	MemberService memberService;

	@ApiOperation(value = "특정 모집글 가져오기")
	@ApiImplicitParam(name = "boardId", value = "게시판 id")
	@GetMapping("/{boardId}")
	public ResponseEntity<?> getBoard(@PathVariable(name = "boardId") long seq) {

		Optional<Board> option = boardService.getBoard(seq);
		if (option.isPresent()) {
			ShowBoardDTO boardDto = new ShowBoardDTO(option.get());
			SuccessfulResponseDTO<ShowBoardDTO> response = SuccessfulResponseDTO.<ShowBoardDTO>builder()
					.code(StatusCode.STATUS_OK).message("모집글 조회 성공").data(boardDto).build();

			return ResponseEntity.ok().body(response);
		} else {
			FailedResponseDTO<String> response = FailedResponseDTO.<String>builder().code(StatusCode.STATUS_FAIL)
					.message("해당 글이 존재하지 않습니다").build();
			return ResponseEntity.ok().body(response);
		}
	}

	@ApiOperation(value="전체 모집글목록 가져오기")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(name="page",value="원하는 페이지"),
				@ApiImplicitParam(name="pageSize",value="페이지당 원하는 데이터 갯수")
				
			})
			

	@GetMapping
	public ResponseEntity<?> getBoardList(@RequestParam("page") int page, @RequestParam("pageSize") int pageNum) {

		if (page < 0 || pageNum < 0) {
			FailedResponseDTO<String> response = FailedResponseDTO.<String>builder().code(StatusCode.STATUS_FAIL)
					.message("파라미터 값이 올바르지 않습니다.").build();
			return ResponseEntity.ok().body(response);
		}

		PageRequest pageRequest = PageRequest.of(page, pageNum);
		Page<Board> boardList = boardService.getBoardList(pageRequest);

		if (!boardList.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Integer> pageInfo = new HashMap<String, Integer>();
			List<ShowBoardDTO> boardDtoList = new ArrayList<ShowBoardDTO>();
			for (Board b : boardList.getContent()) {
				boardDtoList.add(new ShowBoardDTO(b));
			}

			map.put("items", boardDtoList);
			pageInfo.put("currentPage", boardList.getNumber());
			pageInfo.put("pageSize", boardList.getSize());
			pageInfo.put("totalPage", boardList.getTotalPages());
			pageInfo.put("totalCount", boardList.getNumberOfElements());
			map.put("page", pageInfo);

			SuccessfulResponseDTO<Map<String, Object>> response = SuccessfulResponseDTO.<Map<String, Object>>builder()
					.code(StatusCode.STATUS_OK).message("모집글 목록 조회 성공").data(map).build();
			return ResponseEntity.ok().body(response);

		} else {

			FailedResponseDTO<String> response = FailedResponseDTO.<String>builder().code(StatusCode.STATUS_FAIL)
					.message("등록된 글이 없습니다.").build();
			return ResponseEntity.ok().body(response);
		}

	}

	@ApiOperation(value = "모집글 생성하기")
	@ApiImplicitParam(
			name = "Authorization",
			value = "클라이언트 토큰",
			required = true
			)
	@PostMapping("/board")
	public ResponseEntity<?> createBoard(@RequestHeader("Authorization") String token,@RequestBody CreateBoardDTO dto) {
		
		String id = jtp.getUserPk(token);
		boardService.createBoard(dto.toEntity(memberService.getMember(id).get()));
		SimpleResponseDTO response = SimpleResponseDTO.builder().code(1).message("글 생성 성공").build();
		return ResponseEntity.ok().body(response);

	}

	@ApiOperation(value = "지역 조회")
	@GetMapping("/getRegionCodeXDescriptions")
	public ResponseEntity<?> getRegionCodeXDescriptions() {
		
		HashMap<String,Object> response = new HashMap<String,Object>();
		
		try {
			response.put("code", StatusCode.STATUS_OK);
			response.put("data", RegionEnum.getRegionCodeXDescriptions());
		} catch (Exception e) {
			response.put("code", StatusCode.INTERNAL_SEVER_ERROR);
			return ResponseEntity.ok().body(response);
		}
		return ResponseEntity.ok().body(response);
	}
}
