package com.itwill.jpa.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dao.user.UserDao;
import com.itwill.jpa.dao.user.UserDaoImpl;
import com.itwill.jpa.dao.user.UserVoteDaoImpl;
import com.itwill.jpa.dto.user.UserDto;
import com.itwill.jpa.dto.user.UserVoteDto;
import com.itwill.jpa.entity.user.User;
import com.itwill.jpa.entity.vote.Vote;
import com.itwill.jpa.repository.user.UserRepository;
import com.itwill.jpa.service.vote.VoteServiceImpl;

@Service
public class UserVotesericeImpl implements UserVoteService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserDaoImpl userDaoImpl;
	
	@Autowired
	UserVoteDaoImpl userVoteDaoImpl;
	
	@Autowired 
	VoteServiceImpl voteServiceImpl;

	
	@Override
	public List<UserDto> findByVote_VoteId(Long voteId) {
	/*	List<User> userList = userRepository.findByVote_VoteId(voteId);
	    List<UserDto> userDtos = new ArrayList<>();

	    for (User user : userList) {
	        UserDto userDto = UserDto.toDto(user);
	        userDtos.add(userDto);
	    }
	    
	    return userDtos;
	*/
		return null;
	}


	
	// 유저 투표번호 업데이트 후 엔티티 변환
	@Override
	public UserVoteDto updateUserVoteId(String userId, Long voteId) throws Exception {
	    User user = userDaoImpl.findUser(userId);
	    if (user == null) {
	        throw new RuntimeException("해당 유저를 찾을 수 없습니다.");
	    }

	    Vote vote = voteServiceImpl.selectByVoteNo(voteId);
	    if (vote == null) {
	        throw new RuntimeException("해당 투표를 찾을 수 없습니다.");
		}
	    user.setVote(vote);
	    User updatedUser = userRepository.save(user);
	    return UserVoteDto.toDto(updatedUser);
	}



	

	
	
	


	

}