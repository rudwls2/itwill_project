package com.itwill.jpa.entity.board;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.itwill.jpa.dto.board.BoardReplyDto;
import com.itwill.jpa.entity.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "board_reply")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardReply {
    @Id
    @SequenceGenerator(name = "BOARD_REPLY_NO_SEQ",sequenceName = "BOARD_REPLY_NO_SEQ",initialValue = 1 , allocationSize =1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_REPLY_NO_SEQ")
	private Long boardReplyId;
    
    private String boardReplyTitle;
    private String boardReplyContent;
    
    @CreationTimestamp
    private LocalDateTime createDateTime;
    
    public static BoardReply toEntity(BoardReplyDto dto) {
    	return BoardReply.builder()
    					 .boardReplyId(dto.getBoardReplyId())
    					 .boardReplyTitle(dto.getBoardReplyTitle())
    					 .boardReplyContent(dto.getBoardReplyContent())
    					 .user(User.builder().userId(dto.getUserId()).build())
    					 .board(Board.builder().boardId(dto.getBoardId()).build())
    					 .build();
    }
    
    
	@ManyToOne
	@JoinColumn(name = "board_Id")
	@ToString.Exclude
	private Board board;
	
	@ManyToOne
	@JoinColumn(name = "user_Id")
	@ToString.Exclude
	private User user;
	

    
}