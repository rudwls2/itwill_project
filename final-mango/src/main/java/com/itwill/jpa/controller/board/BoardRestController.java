package com.itwill.jpa.controller.board;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dto.board.BoardDto;
import com.itwill.jpa.dto.board.BoardReplyDto;
import com.itwill.jpa.entity.board.Board;
import com.itwill.jpa.entity.board.BoardReply;
import com.itwill.jpa.entity.board.BoardType;
import com.itwill.jpa.service.board.BoardServiceImpl;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/")
public class BoardRestController {

	@Autowired
	private BoardServiceImpl boardServiceImpl;

	@Operation(summary = "게시판등록[성공]")
	@PostMapping("/board_create")
	public ResponseEntity<BoardDto> createBoard(@RequestBody BoardDto boardDto) {
		try {
			// BoardDto를 엔티티로 변환하여 서비스에서 사용
			Board board = Board.toEntity(boardDto);
			// 게시판 등록
			Board createdBoard = boardServiceImpl.insert(board);
			// 엔티티를 다시 DTO로 변환하여 반환
			BoardDto createdBoardDto = BoardDto.toDto(createdBoard);
			return new ResponseEntity<>(createdBoardDto, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "댓글등록")
	@PostMapping("board_reply_create/{boardId}")
	public ResponseEntity<BoardReplyDto> createBoardReply(@PathVariable(name = "boardId") Long boardId,
			@RequestBody BoardReplyDto boardReplyDto) {
		try {
			BoardReply boardReply = BoardReply.toEntity(boardReplyDto);
			BoardReply createReply = boardServiceImpl.ReplyInsert(boardReply);
			BoardReplyDto createReplyDto = BoardReplyDto.toDto(createReply);
			return new ResponseEntity<>(createReplyDto, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Operation(summary = "타입변화")
	@PutMapping("/board_type_change/{boardId}")
	public ResponseEntity<BoardDto> changeBoardType(@PathVariable(name = "boardId")Long boardId,@RequestBody BoardDto dto) {
	    // increaseReadCount 메서드를 호출하여 조회수 증가
		Optional<Board> optionalBoard = boardServiceImpl.findById(boardId);

	    if (optionalBoard.isPresent()) {
	        // 업데이트된 Board 객체를 DTO로 변환
	    	Board findboard = optionalBoard.get();
	    	findboard.setBoardType(new BoardType(8L,"답변완료",null));
	        BoardDto updatedBoardDto = BoardDto.toDto(findboard);
	        return new ResponseEntity<>(updatedBoardDto, HttpStatus.OK);
	    } else {
	        // 결과가 없을 경우 클라이언트에게 적절한 오류 상태 코드를 반환
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	

	@Operation(summary = "업데이트")
	@PutMapping("/board_update/{boardId}")
	public ResponseEntity<BoardDto> BoardUpdate(@PathVariable(name = "boardId") Long boardId,
			@RequestBody BoardDto dto) {

		Optional<Board> optionalBoard = boardServiceImpl.findById(boardId);

		if (optionalBoard.isPresent()) {
			// Optional에서 Board 객체를 추출합니다.
			Board findboard = optionalBoard.get();
			findboard.setBoardContent(dto.getBoardContent());
			findboard.setBoardTitle(dto.getBoardTitle());
			
//	        findboard.setBoardPrize(dto.getBoardPrize());
//	        findboard.setBoardImage(dto.getBoardImage());

			// Board 객체를 업데이트하고 업데이트된 결과를 얻습니다.
			Board updateboard = boardServiceImpl.update(findboard);

			// 업데이트된 Board 객체를 DTO로 변환합니다.
			BoardDto updateboardDto = BoardDto.toDto(updateboard);

			return new ResponseEntity<>(updateboardDto, HttpStatus.OK);
		} else {
			// 결과가 없을 경우 클라이언트에게 적절한 오류 상태 코드를 반환합니다.
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "삭제[성공]")
	@DeleteMapping("/board_delete/{boardId}")
	public void BoardDelete(@PathVariable(name = "boardId") Long boardId) {
		boardServiceImpl.delete(boardId);
	}

	@Operation(summary = "1대1문의 오래된 순서로 정렬[성공]")
	@GetMapping("/inquiries/reverseSort")
	public ResponseEntity<List<BoardDto>> reverseSortInquiries() {
		Long categoryId = 4L;
		String userId = "why3795";
		List<Board> boardList = boardServiceImpl.findByBoardCategory_IdAndUser_UserIdOrderByCreatedTime(categoryId,
				userId);
		List<BoardDto> boardDtoList = new ArrayList<BoardDto>();

		for (Board board : boardList) {
			boardDtoList.add(BoardDto.toDto(board));
		}

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		return new ResponseEntity<>(boardDtoList, httpHeaders, HttpStatus.OK);
	}

	@Operation(summary = "1대1문의 최신로 정렬[성공]")
	@GetMapping("/inquiries/sort")
	public ResponseEntity<List<BoardDto>> sortInquiries() {
		Long categoryId = 4L;
		String userId = "why3795";
		List<Board> boardList = boardServiceImpl.findByBoardCategory_IdAndUser_UserIdOrderByCreatedTime(categoryId,
				userId);
		// 최신순서로 정렬하는 코딩
		List<BoardDto> boardDtoList = boardList.stream().map(BoardDto::toDto)
				.sorted(Comparator.comparing(BoardDto::getCreatedTime).reversed()).collect(Collectors.toList());

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		return new ResponseEntity<>(boardDtoList, httpHeaders, HttpStatus.OK);
	}

///////////////////////////// 이벤트 ///////////////////////////////////////////////	

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<Page<BoardDto>> getBoardsByCategory(@PathVariable Long categoryId,
			@PageableDefault(page = 0, size = 6) Pageable pageable) {
		Page<BoardDto> boardPage = boardServiceImpl.getBoardsByCategory(categoryId, pageable);

		// 현재 페이지 번호
		int currentPage = boardPage.getNumber();

		// 전체 페이지 수
		int totalPages = boardPage.getTotalPages();

		// 다음 페이지가 있는지 여부 확인
		boolean hasNextPage = currentPage < totalPages - 1;

		// 다음 페이지를 요청하는 URL을 생성
		String nextUrl = hasNextPage ? "/boards/category/{categoryId}?page=" + (currentPage + 1) : null;

		// 다음 페이지 정보를 응답에 포함
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("X-Has-Next-Page", String.valueOf(hasNextPage));
		responseHeaders.add("X-Next-Page-Url", nextUrl);

		return ResponseEntity.ok().headers(responseHeaders).body(boardPage);
	}

}
