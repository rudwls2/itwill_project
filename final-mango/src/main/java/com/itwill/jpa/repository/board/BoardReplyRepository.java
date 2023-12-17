package com.itwill.jpa.repository.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.jpa.entity.board.BoardReply;

public interface BoardReplyRepository extends JpaRepository<BoardReply, Long>{

	List<BoardReply> findByBoard_boardId(long BoardId);
	
	
}
