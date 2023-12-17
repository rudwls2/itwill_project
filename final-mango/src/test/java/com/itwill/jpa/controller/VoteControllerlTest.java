package com.itwill.jpa.controller;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.itwill.jpa.controller.vote.VoteController;
import com.itwill.jpa.dto.product.ProductVoteDto;
import com.itwill.jpa.entity.product.Product;
import com.itwill.jpa.entity.vote.Vote;
import com.itwill.jpa.service.product.ProductVoteServiceImple;
import com.itwill.jpa.service.vote.VoteServiceImpl;

@WebMvcTest(VoteController.class)
public class VoteControllerlTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VoteServiceImpl voteService;

    @MockBean
    private ProductVoteServiceImple productVoteService;

    @BeforeEach
    public void setup() {
      
    	
    }
    
}
