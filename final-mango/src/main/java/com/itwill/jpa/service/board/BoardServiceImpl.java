package com.itwill.jpa.service.board;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.jpa.dto.board.BoardDto;
import com.itwill.jpa.entity.board.Board;
import com.itwill.jpa.entity.board.BoardReply;
import com.itwill.jpa.entity.board.BoardType;
import com.itwill.jpa.repository.board.BoardReplyRepository;
import com.itwill.jpa.repository.board.BoardRepository;
import com.itwill.jpa.repository.board.BoardTypeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private BoardTypeRepository boardTypeRepository;

	@Autowired
	private BoardReplyRepository boardReplyRepository;

	// 게시글 저장
	@Override
	public Board insert(Board board) {
		return boardRepository.save(board);
	}

	@Override
	public BoardReply ReplyInsert(BoardReply boardReply) {
		return boardReplyRepository.save(boardReply);
	}

	// 게시글삭제
	@Override
	public void delete(Long boardId) {
		Optional<Board> boardOptional = boardRepository.findById(boardId);
		if (boardOptional.isPresent()) { // board객체가 존재한다면
			Board board = boardOptional.get(); // board 객체추출
			boardRepository.delete(board); // 삭제
		} else {
			// 예외처리 추가하기.
		}
	}

	// 게시글수정
	@Override
	public Board update(Board board) {
		Board isBoard = boardRepository.findById(board.getBoardId()).orElse(null); 
		if (isBoard != null) {
			// title ,content , image 수정하기 (요소추가)
			isBoard.setBoardTitle(board.getBoardTitle());
			isBoard.setBoardContent(board.getBoardContent());
			isBoard.setBoardImage(board.getBoardImage());
			return boardRepository.save(isBoard); // 수정한board 저장
		} else {
			throw new EntityNotFoundException("게시물을 찾을 수 없습니다."); // 게시물이 없을때 예외처리
		}
	}
	
	@Override
	public Board updateType(Board board) {
		Board isBoard = boardRepository.findById(board.getBoardId()).orElse(null); 
		if (isBoard != null) {
			// title ,content , image 수정하기 (요소추가)
			isBoard.setBoardType(board.getBoardType());
			return boardRepository.save(isBoard); // 수정한board 저장
		} else {
			throw new EntityNotFoundException("게시물을 찾을 수 없습니다."); // 게시물이 없을때 예외처리
		}
	}
	
	//조회수증가
	@Override
	public Board increaseReadCount(Board findboard) {
	    Board boardcount = boardRepository.findById(findboard.getBoardId()).orElse(null); // nullpoint 방지
	    if (boardcount != null) {
	        boardcount.setBoardReadCount(boardcount.getBoardReadCount() + 1);
	        boardRepository.save(boardcount);
	    }
	    return boardcount;
	}


	// 게시글 카테고리별 구분
	@Override
	public List<Board> findBycategory(Long id) {
		return boardRepository.findByBoardCategory_IdOrderByCreatedTime(id);
	}

	@Override
	public List<Board> findByType(Long id) {
		return boardRepository.findByBoardType_TypeIdOrderByCreatedTime(id);
	}

	@Override
	public List<BoardReply> findByBoard_boardId(Long BoardId) {
		return boardReplyRepository.findByBoard_boardId(BoardId);
	}

//	
//	@Override
//	public List<Board> getBoardsOrderByReadCount() {
//		return null;
//	}
//	@Override
//	public void incrementLikeCount(Long boardId) {
//		
//	}

	// 제목키워드로 검색
	@Override
	public List<Board> searchBoardsByKeyword(String keyword) {
		return boardRepository.findByBoardTitleContaining(keyword);
	}

	// 기간으로 검색
	@Override
	public List<Board> searchBoardsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
		return boardRepository.findByCreatedTimeBetween(startDate, endDate);
	}

	@Override
	public List<Board> findAllByOrderByBoardReadCountDesc() {
		return boardRepository.findAllByOrderByBoardReadCountDesc();
	}

	public List<BoardType> findAllByOrderByTypeIdAsc() {
		return boardTypeRepository.findAllByOrderByTypeIdAsc();
	}

	public List<Board> findByBoardCategory_IdAndUser_UserIdOrderByCreatedTime(Long boardCategoryId, String userId) {
		return boardRepository.findByBoardCategory_IdAndUser_UserIdOrderByCreatedTime(boardCategoryId, userId);
	}

	public Optional<Board> findById(Long BoardId) {
		return boardRepository.findById(BoardId);
	}

	public Page<BoardDto> getBoardsByCategory(Long categoryId, Pageable pageable) {
		Page<Board> boardPage = boardRepository.findByBoardCategoryId(categoryId, pageable);
		return boardPage.map(board -> new BoardDto(board.getBoardId(), board.getBoardTitle(), board.getBoardContent(),
				board.getBoardImage(), board.getBoardPrize(), 0, null, null, categoryId, categoryId, null));
	}



}
