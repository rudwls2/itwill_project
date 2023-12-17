package com.itwill.jpa.dto.user;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.itwill.jpa.entity.user.User;
import com.itwill.jpa.entity.vote.Vote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVoteDto {	 

	private String userId;			// 회원 아이디
	private String userPw;			// 회원 비밀번호
	private String userName;		// 회원 이름
	
	private Long voteId;	
	
	
	public UserVoteDto(User user, Vote vote) {
		this.userId = user.getUserId();
		this.userPw = user.getUserPw();
		this.userName = user.getUserName();
		this.voteId = vote.getVoteId();
		
	}
	
	
	public static UserVoteDto toDto(User entity) {
		return UserVoteDto.builder()
				  .userId(entity.getUserId())
				  .userPw(entity.getUserPw())
				  .userName(entity.getUserName())
				  .voteId(entity.getVote().getVoteId())
				  .build();
	}

	public static UserVoteDto toDto(User user, Vote vote) {
	    return UserVoteDto.builder()
	    				   .userId(user.getUserId())
						   .userPw(user.getUserPw())
						   .userName(user.getUserName())
						   .voteId(user.getVote().getVoteId())
						   .build();
	}
	
	
	//List<Entity> to List<Dto> 변환
	public static List<UserVoteDto> toDto(List<User> entities){
		List<UserVoteDto> userVoteDtoList = new ArrayList<>();
		 for(User entity : entities) {
		 UserVoteDto userDto = UserVoteDto.builder()
							  .userId(entity.getUserId())
							  .userPw(entity.getUserPw())
							  .userName(entity.getUserName())
							  .voteId(entity.getVote().getVoteId())							  
							  .build();
		 					  userVoteDtoList.add(userDto);
		 }
		return userVoteDtoList;		
	}
	

	
}