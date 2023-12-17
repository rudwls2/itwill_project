package com.itwill.jpa.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dao.user.UserDaoImpl;
import com.itwill.jpa.dto.user.UserVoteDto;
import com.itwill.jpa.service.user.UserVotesericeImpl;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class UserVoteRestController {

	@Autowired
	UserVotesericeImpl userVotesericeImpl;
	@Autowired
	UserDaoImpl userDaoImpl;
	
	@LoginCheck
	@Operation(summary = "회원 투표 업데이트[완료]")
	@PutMapping(value = "/userVoteUpdate/{userId}/{voteId}")
	public ResponseEntity<?> updateUserVoteId(@PathVariable(name = "userId") String userId,
	                                           @PathVariable(name = "voteId") Long voteId) throws Exception {
	    // userId와 voteId를 사용하여 업데이트 로직을 수행
	    try {
	       // 추가
	    	if (userDaoImpl.findUser(userId).getVote()!=null) {
	        	return new ResponseEntity<>("이미 투표가 완료된 회원입니다.", HttpStatus.CONFLICT);
			}
	    	UserVoteDto updatedUserVoteId = userVotesericeImpl.updateUserVoteId(userId, voteId);
	        return new ResponseEntity<>(updatedUserVoteId, HttpStatus.OK);
	        
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>("없는 회원입니다.", HttpStatus.NOT_FOUND);
	    } catch (Exception e) {
	        return new ResponseEntity<>("투표번호가 없습니다.", HttpStatus.BAD_REQUEST);
	    }
	}
	
	
	/*
	@LoginCheck
	@Operation(summary = "회원 투표 업데이트[완료]")
	@PutMapping(value = "/userVoteUpdate/{userId}/{voteId}")
	public ResponseEntity<?> updateUserVoteId(@PathVariable(name = "userId") String userId,
	                                          @PathVariable(name = "voteId") Long voteId) {
	    userId ="a";
		
		try {
	        UserVoteDto updatedUserVoteId = userVotesericeImpl.updateUserVoteId(userId, voteId);
	        return new ResponseEntity<>(updatedUserVoteId, HttpStatus.OK);
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>("없는 회원입니다.", HttpStatus.NOT_FOUND);
	    } catch (Exception e) {
	        return new ResponseEntity<>("투표번호가 없습니다.", HttpStatus.BAD_REQUEST);
	    }
	}
	*/
}