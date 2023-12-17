package com.itwill.jpa.repository.vote;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.jpa.TeamProjectMangoApplicationTest;
import com.itwill.jpa.entity.vote.Vote;

class VoteRepositoryTest extends TeamProjectMangoApplicationTest{
	
	@Autowired
	VoteRepository voteRepository;
	
	   @Test
	   void testSave() {
		   
	     Vote vote = Vote.builder()
	    		 		 .voteTot(33)
	    		 		 .build();

	      voteRepository.save(vote);
	      
	      System.out.println(">>> " + vote);
	      

	   }
	   
	   
}
