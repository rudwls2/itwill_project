package com.itwill.jpa.controller.vote;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.jpa.dao.product.ProductVoteDaoImpl;
import com.itwill.jpa.dao.user.UserDao;
import com.itwill.jpa.dto.product.ProductVoteDto;
import com.itwill.jpa.dto.user.UserDto;
import com.itwill.jpa.entity.product.Product;
import com.itwill.jpa.entity.user.User;
import com.itwill.jpa.service.product.ProductVoteServiceImple;
import com.itwill.jpa.service.user.UserService;
import com.itwill.jpa.service.vote.VoteServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class VoteController {
	@Autowired
	private VoteServiceImpl voteServiceImpl;
	
	@Autowired
	private ProductVoteServiceImple productVoteServiceImple;
	
	
	@Autowired
	private ProductVoteDaoImpl productVoteDaoImpl;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/bestAward") 
	public String bestAward() { 
		String forward_path = "vote_bestAward_form"; 
		return forward_path; 
	}
	
	@GetMapping("/specialAward") 
	public String specialAward() { 
		String forward_path = "vote_specialAward_form"; 
		return forward_path; 
	}
	
	
	@GetMapping(value = "/voteMain")
	public String voteMain(Model model) {		
		return "voteMain";
	}
		
	/*
	@GetMapping("/productVote")
	public String ProductVoteList(Model model) {
		List<Product> product = productVoteServiceImple.findProductsByVoteIsNotNull();
		List<ProductVoteDto> productVote = ProductVoteDto.toDto(product);
		
		
		int totalVotes = 0; // 전체 투표 합

		for (ProductVoteDto dto : productVote) {
		    totalVotes += dto.getVoteTot();
		}
		
		model.addAttribute("productVote", productVote);  
		model.addAttribute("totalVotes", totalVotes);// 투표 합계
		
		System.out.println("productVote: " + productVote);
	        

	    return "voteMain";  // 템플릿 이름 반환
	}
	*/
	
	@GetMapping("/productVote")
	public String ProductVoteList(Model model,HttpSession session) throws Exception {
		/*************** 유저정보 ***************/
		
		
		//임의로 세션 로그인 유저 설정함
		String loginUser = (String) session.getAttribute("sUserId");
		UserDto user=null;
		if(loginUser!=null) {
			user = userService.findUser(loginUser);
			model.addAttribute("loginUser", user);
		}
		model.addAttribute("loginUser", user);
		/*************** 유저정보 ***************/
		
		List<ProductVoteDto> productVote = productVoteDaoImpl.findProductsByVoteIsNotNullOrderByVoteTotDesc();
		
		ProductVoteDto top1Product = productVote.get(0);
		ProductVoteDto top2Product = productVote.get(1);
		ProductVoteDto top3Product = productVote.get(2);
		ProductVoteDto top4Product = productVote.get(3);
		ProductVoteDto top5Product = productVote.get(4);
		ProductVoteDto top6Product = productVote.get(5);
		ProductVoteDto top7Product = productVote.get(6);
		ProductVoteDto top8Product = productVote.get(7);
		ProductVoteDto top9Product = productVote.get(8);
		ProductVoteDto top10Product = productVote.get(9);
		
		
		int totalVotes = 0; // 전체 투표 합

		for (ProductVoteDto dto : productVote) {
		    totalVotes += dto.getVoteTot();}
		
		
		model.addAttribute("totalVotes", totalVotes);	// 투표 합계
		model.addAttribute("top1Product", top1Product); 	// 1위
		model.addAttribute("top1Productpercentage", Math.round(((double) top1Product.getVoteTot() / totalVotes) * 1000.0) / 10.0); // 1위의 득표율
		
		model.addAttribute("top2Product", top2Product); // 2위
		model.addAttribute("top2Productpercentage", Math.round(((double) top2Product.getVoteTot() / totalVotes) * 1000.0) / 10.0); // 2위의 득표율
		
		model.addAttribute("top3Product", top3Product); // 3위
		model.addAttribute("top3Productpercentage", Math.round(((double) top3Product.getVoteTot() / totalVotes) * 1000.0) / 10.0);

		model.addAttribute("top4Product", top4Product); // 4위
		model.addAttribute("top4Productpercentage", Math.round(((double) top4Product.getVoteTot() / totalVotes) * 1000.0) / 10.0);

		model.addAttribute("top5Product", top5Product); // 5위
		model.addAttribute("top5Productpercentage", Math.round(((double) top5Product.getVoteTot() / totalVotes) * 1000.0) / 10.0);

		model.addAttribute("top6Product", top6Product); // 6위
		model.addAttribute("top6Productpercentage", Math.round(((double) top6Product.getVoteTot() / totalVotes) * 1000.0) / 10.0);

		model.addAttribute("top7Product", top7Product); // 7위
		model.addAttribute("top7Productpercentage", Math.round(((double) top7Product.getVoteTot() / totalVotes) * 1000.0) / 10.0);

		model.addAttribute("top8Product", top8Product); // 8위
		model.addAttribute("top8Productpercentage", Math.round(((double) top8Product.getVoteTot() / totalVotes) * 1000.0) / 10.0);

		model.addAttribute("top9Product", top9Product); // 9위
		model.addAttribute("top9Productpercentage", Math.round(((double) top9Product.getVoteTot() / totalVotes) * 1000.0) / 10.0);

		model.addAttribute("top10Product", top10Product); // 10위
		model.addAttribute("top10Productpercentage", Math.round(((double) top10Product.getVoteTot() / totalVotes) * 1000.0) / 10.0);
		
		System.out.println("productVote: " + productVote);
	        

	    return "vote_main";  // 템플릿 이름 반환
	}


	
	
}
