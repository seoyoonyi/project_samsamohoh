package com.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.server.domain.Board;
import com.server.dto.BoardDto;
import com.server.responsecode.FailResponse;
import com.server.responsecode.StatusCode;
import com.server.responsecode.SuccessResponse;
import com.server.service.BoardService;

@RestController
@RequestMapping("/boards")
public class BoardController {

	@Autowired
	BoardService boardService;

	@GetMapping("/{boardId}")
	public ResponseEntity getBoard(@PathVariable(name = "boardId") long seq) {

		Optional<Board> option = boardService.getBoard(seq);
		if (option.isPresent()) {
			BoardDto boardDto = new BoardDto(option.get());
			return ResponseEntity.ok().body(new SuccessResponse(StatusCode.STATUS_OK, "모집글 조회 성공", boardDto));
		} else {
			return ResponseEntity.ok().body(new FailResponse(StatusCode.STATUS_FAIL, "해당 글이 존재하지 안습니다."));
		}
	}

	@GetMapping("")
	public ResponseEntity getBoardList(@RequestParam("page") int page, @RequestParam("pageSize") int pageNum) {
		
		if(page<0 || pageNum<0) {
			return ResponseEntity.status(HttpStatus.OK).body(new FailResponse(StatusCode.STATUS_FAIL,"파라미터 값이 올바르지 않습니다"));
		}
	
		PageRequest pageRequest = PageRequest.of(page, pageNum);
		Page<Board> boardList = boardService.getBoardList(pageRequest);
		
		if (!boardList.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Integer> pageInfo = new HashMap<String, Integer>();
			List<BoardDto> boardDtoList = new ArrayList<BoardDto>();
			for(Board b : boardList.getContent()) {
				boardDtoList.add(new BoardDto(b));
			}

			map.put("items", boardDtoList);
			pageInfo.put("currentPage", boardList.getNumber());
			pageInfo.put("pageSize",boardList.getSize());
			pageInfo.put("totalPage", boardList.getTotalPages());
			pageInfo.put("totalCount", boardList.getNumberOfElements());
			map.put("page",pageInfo);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new SuccessResponse(StatusCode.STATUS_OK, "모집글 목록 조회 성공", map));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(new FailResponse(StatusCode.STATUS_FAIL, "모집글 목록 조회 실패"));
		}

	}

	@PostMapping("board")
	public ResponseEntity createBoard(Board board) {
		boardService.createBoard(board);

		return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse(StatusCode.STATUS_OK, "글 생성 성공", board));
	}
}
