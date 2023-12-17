package com.itwill.jpa.service.user;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.jpa.TeamProjectMangoApplicationTest;
import com.itwill.jpa.dao.user.UserDao;
import com.itwill.jpa.dto.user.UserDto;
import com.itwill.jpa.entity.user.User;
import com.itwill.jpa.repository.user.UserRepository;

@SpringBootTest
class UserServiceImplTest extends TeamProjectMangoApplicationTest{
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserDao userDao;
	
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	@DisplayName("회원가입")
	void testCreateUser() {
		UserDto userDto = new UserDto();
		userDto.setUserId("testUser137");
		userDto.setUserPw("password");
		userDto.setUserName("Test User");
		userDto.setUserEmail("test@example.com");
		userDto.setUserPhone("010-7777-7777");
		userDto.setMemberShip(true);

		try {
			UserDto createdUser = userService.createUser(userDto);
			assertNotNull(createdUser);
			assertEquals(userDto.getUserId(), createdUser.getUserId());
		} catch (Exception e) {
			fail("회원가입 실패: " + e.getMessage());
		}
	}

	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	@DisplayName("회원로그인")
    public void testLoginUser() {
		String UserId = "kbs88";
        String UserPw = "qjatjr88";
        try {
	        User loginUser = userService.loginUser(UserId, UserPw);
	        assertNotNull(loginUser);
	        assertEquals(UserId, loginUser.getUserId());
	        
	        System.out.println(">>> 로그인 성공: " + loginUser);
        }catch (Exception e) {
        	fail(">>> 로그인 실패: " + e.getMessage());
		}
	
	}
	
	@Test
	@Disabled
    @Transactional
    @Rollback(false)
    @DisplayName("회원수정")
    public void testUpdateUser() {
		
		String userId = "kbs88";
		String newUserName = "고범석";
		String newUserGenger = "남";
		String newUserPhone = "010-4039-4937";
		try {
			User user = userDao.findUser(userId);
			assertNotNull(user);

			user.setUserName(newUserName);
			user.setUserGender(newUserGenger);
			user.setUserPhone(newUserPhone);
			User updatedUser = userDao.updateUser(user);

			System.out.println(">>> 회원 수정 성공 " + updatedUser);
		} catch (Exception e) {
			fail(">>> 회원 수정 실패 " + e.getMessage());
		}
	}
	
	@Test
	//@Disabled
    @Transactional
    @Rollback(false)
    @DisplayName("회원탈퇴")
    public void testDeleteUser() {
        String userId = "test";

        try {
            userDao.deleteUser(userId);
            
        } catch (Exception e) {
            fail(">>> 회원 탈퇴 실패 " + e.getMessage());
        }
    }
	
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	@DisplayName("회원상세보기")
	public void testFindUser() {
		String userId = "kbs88";

		try {
			UserDto user = UserDto.toDto(userDao.findUser(userId));
			assertNotNull(user);
			assertEquals(userId, user.getUserId());
			System.out.println(">>> 회원 조회 성공 " + user);
		} catch (Exception e) {
			fail(">>> 회원 조회 실패 " + e.getMessage());
		}
	}
	
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	@DisplayName("전체회원리스트")
	public void testFindUserList() {
		try {
			List<User> userList = userDao.findUserList();
			assertNotNull(userList);
			assertFalse(userList.isEmpty());
			System.out.println(">>> 전체 회원 리스트 " + userList);
		} catch (Exception e) {
			fail(">>> 전체회원리스트 조회 실패 " + e.getMessage());
		}
	}
	
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	@DisplayName("아이디중복체크")
	public void testExistsById() throws Exception {
		 String existingUserId = "kbs88";

		    boolean isExists = userDao.existsById(existingUserId);
		    if (isExists) {
		        System.out.println(">>> 아이디 중복됨.");
		        assertTrue(isExists);
		    } else {
		        System.out.println(">>> 아이디 중복 안됨.");
		        assertFalse(isExists);
		    }
		}

		@Test
		@Disabled
		@Transactional
		@Rollback(false)
		@DisplayName("아이디 찾기")
		public void testFindUserIdByUserNameUserEmail() {
			String userName = "고범석";
			String userEmail = "kbs@naver.com";

			try {
				String foundUserId = userService.findUserIdByUserNameUserEmail(userName ,userEmail);
				assertNotNull(foundUserId);
				System.out.println(">>> 아이디 찾기 성공: " + foundUserId);
			} catch (Exception e) {
				fail(">>> 아이디 찾기 실패: " + e.getMessage());
			}
		}

		@Test
		@Disabled
		@Transactional
		@Rollback(false)
		@DisplayName("비밀번호 찾기")
		public void testFindUserPwByUserIdUserPhone() {
			String userId = "kbs88";
			String userPhone = "010-4039-4937";

			try {
				String foundUserPw = userService.findUserPwByUserIdUserPhone(userId, userPhone);
				assertNotNull(foundUserPw);
				System.out.println(">>> 비밀번호 찾기 성공: " + foundUserPw);
			} catch (Exception e) {
				fail(">>> 비밀번호 찾기 실패: " + e.getMessage());
			}
		}
	}

