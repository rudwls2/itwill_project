package com.itwill.jpa.controller.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwill.jpa.controller.user.LoginCheck;
import com.itwill.jpa.dto.user.UserDto;
import com.itwill.jpa.entity.board.Board;
import com.itwill.jpa.entity.board.BoardReply;
import com.itwill.jpa.entity.board.BoardType;
import com.itwill.jpa.service.board.BoardServiceImpl;
import com.itwill.jpa.service.user.UserService;
import com.itwill.jpa.service.user.UserServiceImpl;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class BoardController {

	private final BoardServiceImpl boardServiceImpl;

	private UserServiceImpl userServiceImpl;

	@Autowired
	private UserService userService;

	@GetMapping("/board_event")
	public String eventPage(HttpSession session, Model model) {
		try {
			String loginUser = (String) session.getAttribute("sUserId");
			UserDto user = null;

			if (loginUser != null) {
				user = userService.findUser(loginUser);
			}

			session.setAttribute("loginUser", user);

			String userIdString = (user != null) ? user.getUserId() : null;
			model.addAttribute("userIdString", userIdString);

			List<Board> events = boardServiceImpl.findBycategory(2L);
			model.addAttribute("events", events);
			System.out.println("이벤트 리스트 :" + events);
			return "board_event"; // 뷰 템플릿의 경로를 "event"로 설정
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMSG : " + e.getMessage());
			return null;
		}
	}

	@GetMapping("/board_notification")
	public String notificationPage(HttpSession session, Model model) {
		try {
			String loginUser = (String) session.getAttribute("sUserId");
			UserDto user = null;

			if (loginUser != null) {
				user = userService.findUser(loginUser);
			}

			session.setAttribute("loginUser", user);

			String userIdString = (user != null) ? user.getUserId() : null;
			model.addAttribute("userIdString", userIdString);

			List<Board> notifications = boardServiceImpl.findBycategory(1L);
			model.addAttribute("notifications", notifications);
			System.out.println("notifications리스트 : " + notifications);

			List<BoardType> boardTypes = boardServiceImpl.findAllByOrderByTypeIdAsc();
			System.out.println("boardTypeList" + boardTypes);
			model.addAttribute("boardTypes", boardTypes);

			return "board_notification";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMSG : " + e.getMessage());
			return null;
		}
	}

	@LoginCheck
	@GetMapping("/board_magazine")
	public String magazine(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
		try {
			String loginUser = (String) session.getAttribute("sUserId");

			// 로그인정보없으면 로그인으로
			if (loginUser == null) {
				redirectAttributes.addAttribute("msg", "로그인이 필요합니다");
				return "redirect:/user_login_form";
			}

			UserDto user = null;
			user = userService.findUser(loginUser);
			session.setAttribute("loginUser", user);
			String userIdString = (user != null) ? user.getUserId() : null;
			model.addAttribute("userIdString", userIdString);
			
			List<Board> magazines = boardServiceImpl.findBycategory(3L);
			model.addAttribute("magazines", magazines);
			System.out.println("magazine 리스트 : " + magazines);

			List<Board> sortedMagazines = new ArrayList<>(magazines); // magazines를 복사하여 새로운 리스트 생성

			sortedMagazines.sort(Comparator.comparing(Board::getBoardReadCount).reversed());

			List<Board> top3Magazines = sortedMagazines.subList(0, Math.min(sortedMagazines.size(), 3));
			model.addAttribute("top3Magazines", sortedMagazines);
			System.out.println("탑3매거진리스트 : " + sortedMagazines);

			return "board_magazine";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMSG : " + e.getMessage());
			return null;
		}
	}

	@LoginCheck
	@GetMapping("/board_inquiries")
	public String inquiries(HttpSession session, Model model, RedirectAttributes redirectAttributes) throws Exception {
		try {

			String loginUser = (String) session.getAttribute("sUserId");

			if (loginUser == null) {
				// 로그인되어 있지 않은 경우, 리디렉션
				redirectAttributes.addAttribute("msg", "로그인이 필요합니다");

				return "redirect:/user_login_form";
			}

			UserDto user = null;
			user = userService.findUser(loginUser);
			session.setAttribute("loginUser", user);
			String userIdString = (user != null) ? user.getUserId() : null;
			model.addAttribute("userIdString", userIdString);

			List<Board> inquiries = boardServiceImpl.findByBoardCategory_IdAndUser_UserIdOrderByCreatedTime(4L,
					userIdString);
			Collections.reverse(inquiries);
			model.addAttribute("inquiries", inquiries);
			System.out.println("inquiries 리스트 : " + inquiries);
			return "board_inquiries";

		} catch (Exception e) {
			e.printStackTrace();
			return "index";
		}
	}

	@LoginCheck
	@GetMapping("/board_admin_inquiries")
	public String admin_inquiries(HttpSession session, Model model) throws Exception {
		try {
			String loginUser = (String) session.getAttribute("sUserId");
			UserDto user = null;

			if (loginUser != null) {
				user = userService.findUser(loginUser);

				// 사용자 정보를 세션에 저장
				session.setAttribute("loginUser", user);
			}

			String userIdString = (user != null) ? user.getUserId() : null;
			model.addAttribute("userIdString", userIdString);

			List<Board> inquiries = boardServiceImpl.findBycategory(4L);

			Collections.reverse(inquiries);
			model.addAttribute("inquiries", inquiries);
			System.out.println("inquiries 리스트 : " + inquiries);
			return "board_admin_inquiries";

		} catch (Exception e) {
			e.printStackTrace();
			return "index";
		}
	}

	@GetMapping("/board_faq")
	public String faq(HttpSession session, Model model) {
		try {

			String loginUser = (String) session.getAttribute("sUserId");
			UserDto user = null;

			if (loginUser != null) {
				user = userService.findUser(loginUser);
			}

			session.setAttribute("loginUser", user);

			String userIdString = (user != null) ? user.getUserId() : null;
			model.addAttribute("userIdString", userIdString);

			List<Board> faqs = boardServiceImpl.findBycategory(5L);
			model.addAttribute("faqs", faqs);
			System.out.println("FAQ 리스트 :" + faqs);
			List<BoardType> boardTypes = boardServiceImpl.findAllByOrderByTypeIdAsc();
			System.out.println("boardTypeList" + boardTypes);
			model.addAttribute("boardTypes", boardTypes);
			return "board_faq"; // 뷰 템플릿의 경로를 "faq"로 설정

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMSG : " + e.getMessage());
			return null;
		}
	}

	@GetMapping("/board_write")
	public String board_write(Model model) {
		try {
			return "board_write";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMSG : " + e.getMessage());
			return null;
		}
	}

	@GetMapping("/board_detail")
	public String boardDetail(@RequestParam(name = "boardId") Long boardId, HttpSession session, Model model) {
		try {

			String loginUser = (String) session.getAttribute("sUserId");
			UserDto user = null;

			if (loginUser != null) {
				user = userService.findUser(loginUser);
			}

			session.setAttribute("loginUser", user);

			String userIdString = (user != null) ? user.getUserId() : null;
			model.addAttribute("userIdString", userIdString);
			// 게시물 ID를 사용하여 해당 게시물의 정보를 데이터베이스에서 가져옵니다.
			Optional<Board> boardOptional = boardServiceImpl.findById(boardId);

			if (boardOptional.isPresent()) {
				// 게시물 정보가 존재할 경우 모델에 추가하여 뷰에서 사용할 수 있도록 합니다.
				Board board = boardOptional.get();
				model.addAttribute("board", board);
				System.out.println("board :" + board);
			} else {
				// 게시물이 존재하지 않을 경우 에러 처리
				model.addAttribute("errorMSG", "게시물을 찾을 수 없습니다.");
			}

			return "board_detail";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMSG", "에러 발생: " + e.getMessage());
			return "error";
		}
	}

	@GetMapping("/board_other_detail")
	public String board_other_detail(@RequestParam(name = "boardId") Long boardId, HttpSession session, Model model) {
		try {

			String loginUser = (String) session.getAttribute("sUserId");
			UserDto user = null;

			if (loginUser != null) {
				user = userService.findUser(loginUser);

				// 사용자 정보를 세션에 저장
				session.setAttribute("loginUser", user);
			}

			String userIdString = (user != null) ? user.getUserId() : null;
			model.addAttribute("userIdString", userIdString);

			// 게시물 ID를 사용하여 해당 게시물의 정보를 데이터베이스에서 가져옵니다.
			Optional<Board> boardOptional = boardServiceImpl.findById(boardId);
			List<BoardReply> ReplyList = boardServiceImpl.findByBoard_boardId(boardId);
			if (boardOptional.isPresent()) {
				// 게시물 정보가 존재할 경우 모델에 추가하여 뷰에서 사용할 수 있도록 합니다.
				Board board = boardOptional.get();
				boardServiceImpl.increaseReadCount(board);
				model.addAttribute("board", board);
				model.addAttribute("ReplyList", ReplyList);

			} else {
				// 게시물이 존재하지 않을 경우 에러 처리
				model.addAttribute("errorMSG", "게시물을 찾을 수 없습니다.");
			}

			return "board_other_detail";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMSG", "에러 발생: " + e.getMessage());
			return "error";
		}
	}

	@GetMapping("/board_other_detail1")
	public String board_other_detail1(@RequestParam(name = "boardId") Long boardId, HttpSession session, Model model) {
		try {
			String loginUser = (String) session.getAttribute("sUserId");
			UserDto user = null;

			if (loginUser != null) {
				user = userService.findUser(loginUser);

				// 사용자 정보를 세션에 저장
				session.setAttribute("loginUser", user);
			}

			String userIdString = (user != null) ? user.getUserId() : null;
			model.addAttribute("userIdString", userIdString);
			// 게시물 ID를 사용하여 해당 게시물의 정보를 데이터베이스에서 가져옵니다.
			Optional<Board> boardOptional = boardServiceImpl.findById(boardId);
			List<BoardReply> ReplyList = boardServiceImpl.findByBoard_boardId(boardId);
			if (boardOptional.isPresent()) {
				// 게시물 정보가 존재할 경우 모델에 추가하여 뷰에서 사용할 수 있도록 합니다.
				Board board = boardOptional.get();
				model.addAttribute("board", board);
				model.addAttribute("ReplyList", ReplyList);

			} else {
				// 게시물이 존재하지 않을 경우 에러 처리
				model.addAttribute("errorMSG", "게시물을 찾을 수 없습니다.");
			}

			return "board_other_detail1";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMSG", "에러 발생: " + e.getMessage());
			return "error";
		}
	}

	@GetMapping("/board_detail_edit")
	public String boardDetailEdit(@RequestParam(name = "boardId") Long boardId, Model model) {
		try {
			// 게시물 ID를 사용하여 해당 게시물의 정보를 데이터베이스에서 가져옵니다.
			Optional<Board> boardOptional = boardServiceImpl.findById(boardId);

			if (boardOptional.isPresent()) {
				// 게시물 정보가 존재할 경우 모델에 추가하여 뷰에서 사용할 수 있도록 합니다.
				Board board = boardOptional.get();
				model.addAttribute("board", board);
			} else {
				// 게시물이 존재하지 않을 경우 에러 처리
				model.addAttribute("errorMSG", "게시물을 찾을 수 없습니다.");
			}

			return "board_detail_edit";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMSG", "에러 발생: " + e.getMessage());
			return "error";
		}
	}

	@GetMapping("/board_map")
	public String board_map(Model model) {
		try {
			return "board_map";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMSG : " + e.getMessage());
			return null;
		}
	}

}
