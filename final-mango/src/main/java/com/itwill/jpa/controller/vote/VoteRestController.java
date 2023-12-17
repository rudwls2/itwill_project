package com.itwill.jpa.controller.vote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dao.user.UserVoteDaoImpl;
import com.itwill.jpa.dto.vote.VoteDto;
import com.itwill.jpa.entity.vote.Vote;
import com.itwill.jpa.service.vote.VoteService;
import com.itwill.jpa.service.vote.VoteServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/voteProduct")
//@RequiredArgsConstructor
public class VoteRestController {
	
	@Autowired
	private VoteServiceImpl voteServiceImpl;
	
	@Autowired
	private VoteService voteService;
	
	
	//private final VoteService voteService;


	@Operation(summary = "투표생성") // 완료
	@PutMapping("/create")
	public ResponseEntity<VoteDto> createVote(@RequestBody VoteDto voteDto) throws Exception {
	    
		Vote vote = new Vote(0L, null, 0, null, null);
		VoteDto.toDto(vote);
		
	    // 서비스 메서드를 호출하여 투표 생성
	    Vote createdVote = voteService.createVote(vote);
	    
	    // 생성된 투표 엔티티를 다시 DTO로 변환
	    VoteDto createdVoteDto = new VoteDto(createdVote);
	    
	    // ResponseEntity에 DTO를 담아 응답
	    return ResponseEntity.status(HttpStatus.CREATED).body(createdVoteDto);
	}
    
	
	@Operation(summary = "투표 1개의 tot")
	@GetMapping("/getVoteTot/{voteId}")
    public ResponseEntity<Integer> getVoteTot(@PathVariable(name = "voteId") Long voteId) throws Exception {
		 Vote vote = voteServiceImpl.selectByVoteNo(voteId);
		 VoteDto selectVote = VoteDto.toDto(vote);
		 if (vote==null) {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(selectVote.getVoteTot());
			} 
			 return ResponseEntity.status(HttpStatus.OK).body(selectVote.getVoteTot());

    }
	
	
	
	/* 투표인원이 적어서 투표 수 늘리는 기능 추가
	 *  voteServiceImpl.updateVote에서 
	 *  int totalVoteCount = users.size()+20;으로 변경하여 기존 투표 인원 +20 해놓음
	 *  추후 삭제 필요
	 */ 
	@Operation(summary = "회원 투표 합으로 tot 업데이트") // 완료
	@PutMapping("/update")
	public ResponseEntity<Map<String, Object>> updateVote(@RequestBody VoteDto vote) throws Exception {
		Map<String, Object> responseMap = new HashMap<>();
		if (voteServiceImpl.selectByVoteNo(vote.getVoteId()) == null) {
	        responseMap.put("message", "없는 투표 번호입니다.");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
	    }
			Vote vote1= voteServiceImpl.selectByVoteNo(vote.getVoteId());
			voteServiceImpl.updateVote(vote1);
		    responseMap.put("message", "투표가 수정되었습니다.");
		    return ResponseEntity.status(HttpStatus.OK).body(responseMap);
	   
	}
	
	
	
	@Operation(summary = "투표 전체보기") // 완료 --> url로도 열림 
	@GetMapping("/voteListAll")
	public ResponseEntity<List<VoteDto>> findVoteListAll() throws Exception {
	    List<Vote> voteList = voteServiceImpl.findVoteListAll();
	    List<VoteDto> voteDtos = new ArrayList<>();

	    for (Vote vote : voteList) {
	        VoteDto voteDto = VoteDto.toDto(vote);
	        voteDtos.add(voteDto);
	    }

	    return ResponseEntity.status(HttpStatus.OK).body(voteDtos);
	}
	
	@Operation(summary = "투표 1개보기") // 완료
	@PostMapping("/selectVote{voteId}")
	public ResponseEntity<VoteDto> selectVote(@PathVariable(name = "voteId") Long voteId) throws Exception {
		 Vote vote = voteServiceImpl.selectByVoteNo(voteId);
		 VoteDto selectVote = VoteDto.toDto(vote);
		 if (vote==null) {
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(selectVote);
		} 
		 return ResponseEntity.status(HttpStatus.OK).body(selectVote);

	}
	
	
	@Operation(summary = "투표삭제") // 완료
	@PostMapping("/deleteVote{voteId}")
	public ResponseEntity<Map<String, Object>> deleteVote(@PathVariable(name = "voteId") Long voteId) throws Exception {
	    Map<String, Object> responseMap = new HashMap<>();
	    Vote vote = voteServiceImpl.selectByVoteNo(voteId);
	    VoteDto selectVote = VoteDto.toDto(vote);
	    if (vote == null) {
	        responseMap.put("message", "없는 투표 번호입니다.");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
	    }

	    voteServiceImpl.deleteByVoteNo(selectVote.getVoteId());
	    responseMap.put("message", "투표가 삭제되었습니다.");
	    return ResponseEntity.status(HttpStatus.OK).body(responseMap);
	}
	
	
	
	@Operation(summary = "유저의 투표번호로 투표,상품전체조회")
	@PutMapping("/User_voteing")
	public String user_vote_action(@PathVariable(name = "voteId") Long voteId, 
								   @ModelAttribute UserVoteDaoImpl userVoteDaoImpl,
								   HttpServletRequest request) throws Exception {
		String forwardPath = "";

		forwardPath = "redirect:vote";
		return forwardPath;
	}

}