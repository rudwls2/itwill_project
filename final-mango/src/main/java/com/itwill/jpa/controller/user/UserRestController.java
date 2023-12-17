
package com.itwill.jpa.controller.user;

import java.net.http.HttpHeaders;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dto.cart.CartDto;
import com.itwill.jpa.dto.user.UserDto;
import com.itwill.jpa.dto.user.UserLoginDto;
import com.itwill.jpa.dto.user.UserUpdateDto;
import com.itwill.jpa.entity.user.User;
import com.itwill.jpa.exception.user.ExistedUserException;
import com.itwill.jpa.exception.user.PasswordMismatchException;
import com.itwill.jpa.service.cart.CartService;
import com.itwill.jpa.service.cart.CartUserServiceImpl;
import com.itwill.jpa.service.user.UserService;
import com.itwill.jpa.service.user.UserServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.models.media.MediaType;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired 
	private CartUserServiceImpl cartUserServiceImpl;
	
	@Autowired
	private CartService cartService;

	@Operation(summary = "회원가입[성공]")
	@PostMapping(value = "/join")
	public ResponseEntity<?> user_write_action(@RequestBody UserDto userDto) throws Exception {
		try {
			if (userService.existsById(userDto.getUserId())) {
				throw new ExistedUserException(">>>" + userDto.getUserId() + "는 이미 존재하는 아이디입니다.");
			}
			UserDto createdUser = userService.createUser(userDto);
			cartService.createCart(userDto.getUserId());

			return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
		} catch (ExistedUserException e) {
			// 이미 존재하는 사용자 예외 처리
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			// 기타 예외 처리
			return new ResponseEntity<>("Internal Server Error>> user_write_action 레스트 컨트롤러 작동하지만 기타예외로 납치됨",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "로그인 성공")
	@PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
	public ResponseEntity<UserLoginDto> user_login_action(@RequestBody UserLoginDto userLogindto, HttpSession session)
			throws Exception {
		User loginUser = userService.loginUser(userLogindto.getUserId(), userLogindto.getUserPw());

		if (loginUser != null) {
			// 로그인 성공 시 사용자 정보를 세션에 저장
			session.setAttribute("sUserId", loginUser.getUserId());
			session.setAttribute("sUserName", loginUser.getUserName());
			session.setAttribute("sUserPw", loginUser.getUserPw());

			return new ResponseEntity<UserLoginDto>(userLogindto, HttpStatus.OK);
		} else {
			// 로그인 실패 시 UNAUTHORIZED 상태 반환
			return new ResponseEntity<UserLoginDto>(HttpStatus.UNAUTHORIZED);
		}
	}

	@LoginCheck
	@Operation(summary = "회원상세보기[성공]")
	@PostMapping(value = "/view/{userId}")
	public ResponseEntity<UserDto> user_View(@PathVariable(name = "userId") String userId, HttpSession session)
			throws Exception {
		try {
			UserDto user = userService.findUser(userId);
			System.out.println(">>> 회원 조회 성공 " + user);
			if (user != null) {
				System.out.println(">> 회원 조회");
				return ResponseEntity.status(HttpStatus.OK).body(user);
			}

		} catch (Exception e) {
			e.getMessage();
		}
		System.out.println(">> 회원 조회 실패");
		return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
	}

	@Operation(summary = "회원리스트[성공]")
	@GetMapping("/list")
	public ResponseEntity<?> userList() throws Exception {
		try {
			List<UserDto> users = userService.findUserList();
			if (users.isEmpty()) {
				return new ResponseEntity<>("No users found", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@LoginCheck
	@Operation(summary = "회원업데이트[성공]")
	@PutMapping(value = "/update/{userId}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> user_modify_action(@PathVariable(name = "userId") String userId,
			@RequestBody UserUpdateDto userUpdateDto, HttpSession session) {
		try {
			session.getAttribute("sUserId");

			UserDto updatedUser = userService.updateUser(userUpdateDto);

			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// 아이디 중복 체크 API
	@Operation(summary = "아이디중복체크")
	@GetMapping("/{userId}")
	public ResponseEntity<Boolean> checkUserIdExists(@PathVariable(name = "userId") String userId) throws Exception {
		boolean exists = userService.existsById(userId);
		return new ResponseEntity<>(exists, HttpStatus.OK);
	}

	// 아이디, 이메일로 아이디 찾기 API
	@Operation(summary = "아이디찾기[성공]")
	@GetMapping("/find-id")
	public ResponseEntity<String> findUserIdByUserNameUserEmail(@RequestParam(name = "userName") String userName,
			@RequestParam(name = "userEmail") String userEmail) {
		try {
			String foundUserId = userService.findUserIdByUserNameUserEmail(userName, userEmail);
			return new ResponseEntity<>(foundUserId, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// 아이디, 전화번호로 비밀번호 찾기 API
	@Operation(summary = "비밀번호찾기[성공]")
	@GetMapping("/find-pw")
	public ResponseEntity<String> findUserPwByUserIdUserPhone(@RequestParam(name = "userId") String userId,
			@RequestParam(name = "user_phone") String userPhone) {
		try {
			String foundUserPw = userService.findUserPwByUserIdUserPhone(userId, userPhone);
			return new ResponseEntity<>(foundUserPw, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "회원탈퇴") // 컨트롤러 테스트 완료!!!
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<String> user_delete_action(@PathVariable(name = "userId") String userId, HttpSession session)
			throws Exception {
		String loginUser = (String) session.getAttribute("sUserId");
		// 기존데이터 >>> userService.deleteUser(loginUser);
		
		// 수정한 부분 시작
		cartUserServiceImpl.deleteByUserIdCart(userId);
		// 수정한 부분 종료
		
		session.invalidate();

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e) {
		return new ResponseEntity<>("서버 오류: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}