package com.itwill.jpa.dto.user;

import com.itwill.jpa.entity.user.User;
import com.itwill.jpa.entity.vote.Vote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserUpdateDto {
	 
	private String userId;
	private String userPw;
	private String userName;
	private String userPhone;
	private String userAddress;
	private String userEmail;
	//private Long voteId; // 문제 생기면 주석 풀기
	
	public static UserUpdateDto toDto(User entity) {
		return UserUpdateDto.builder()
							.userId(entity.getUserId())
							.userPw(entity.getUserPw())
							.userName(entity.getUserName())
							.userPhone(entity.getUserPhone())
							.userAddress(entity.getUserAddress())
							.userEmail(entity.getUserEmail())
							.build();
	}
	
	
}
