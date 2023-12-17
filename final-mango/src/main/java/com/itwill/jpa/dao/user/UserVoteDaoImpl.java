package com.itwill.jpa.dao.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.user.UserDto;
import com.itwill.jpa.dto.user.UserVoteDto;
import com.itwill.jpa.entity.product.Product;
import com.itwill.jpa.entity.user.User;
import com.itwill.jpa.entity.vote.Vote;
import com.itwill.jpa.repository.product.ProductRepository;
import com.itwill.jpa.repository.user.UserRepository;
import com.itwill.jpa.repository.user.UserVoteRepository;
import com.itwill.jpa.repository.vote.VoteRepository;
import com.itwill.jpa.service.product.ProductServiceImpl;
import com.itwill.jpa.service.user.UserServiceImpl;
import com.itwill.jpa.service.vote.VoteServiceImpl;

@Repository
@Service
public class UserVoteDaoImpl implements UserVoteDao{

	@Autowired 
	UserServiceImpl userServiceImpl;

	@Autowired 
	UserDaoImpl userDaoImpl;
	
	@Autowired 
	VoteServiceImpl voteServiceImpl;

	@Autowired
	UserRepository userRepository;

	
	@Override
	public User findUserVoteId(Long voteId) {
		// TODO Auto-generated method stub
		return null;
	}

	// 유저 투표번호 업데이트
	@Override
	public User userUpdatVoteId(String userId, Long voteId) throws Exception {
		User user = userDaoImpl.findUser(userId);
		if (user==null) {
			System.out.println("없는 회원입니다.");
		}
		
		Vote vote = voteServiceImpl.selectByVoteNo(voteId);
		if (vote==null) {
			System.out.println("없는 투표번호입니다.");
		}
		user.setVote(vote);
		User userUpdatVoteId = userRepository.save(user);
		
		return userUpdatVoteId;
	}
}