package com.itwill.jpa.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.jpa.dao.order.OrderDao;
import com.itwill.jpa.dao.user.UserDao;
import com.itwill.jpa.dto.user.UserDto;
import com.itwill.jpa.dto.user.UserLoginDto;
import com.itwill.jpa.dto.user.UserUpdateDto;
import com.itwill.jpa.entity.user.User;
import com.itwill.jpa.exception.user.ExistedUserException;
import com.itwill.jpa.exception.user.PasswordMismatchException;
import com.itwill.jpa.exception.user.UserNotFoundException;
import com.itwill.jpa.repository.user.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	OrderDao orderDao;
	
	@Autowired
	UserRepository repository;

	@Override
	public UserDto createUser(UserDto userDto) throws Exception {
		if (userDao.existsById(userDto.getUserId())) {
			throw new ExistedUserException(userDto.getUserId() + "는 이미 존재하는 아이디입니다.");
		}

		User createdUser = userDao.createUser(User.toEntity(userDto));

		return UserDto.toDto(createdUser);
	}

	@Override
	public User loginUser(String userId, String userPw) throws Exception {
		User userinfo = userDao.findUser(userId);
		User fUser = User.builder().userId(userId).userPw(userPw).build();

		if (userinfo == null) {
			UserNotFoundException exception = new UserNotFoundException(userId + " 는 존재하지않는 아이디입니다.");
			exception.setData(fUser);
			throw exception;
		}
		String userPassword = userinfo.getUserPw();
		if (!userPassword.equals(userPw)) {
			PasswordMismatchException exception = new PasswordMismatchException("패쓰워드가 일치하지 않습니다.");
			exception.setData(fUser);
			throw exception;
		}

		return userinfo;
	}

	@Override
	public UserDto updateUser(UserUpdateDto userUpdateDto) throws Exception {
		User user = userDao.findUser(userUpdateDto.getUserId());

		if (user == null) {
			throw new Exception("사용자를 찾을 수 없습니다.");
		}

		user.setUserPw(userUpdateDto.getUserPw());
		user.setUserName(userUpdateDto.getUserName());
		user.setUserPhone(userUpdateDto.getUserPhone());
		user.setUserAddress(userUpdateDto.getUserAddress());
		user.setUserEmail(userUpdateDto.getUserEmail());

		User updatedUser = userDao.updateUser(user);

		return UserDto.toDto(updatedUser);
	}

	@Override
	public void deleteUser(String userId) throws Exception {
		userDao.deleteUser(userId);
	}

	@Override
	public UserDto findUser(String userId) throws Exception {
		return UserDto.toDto(userDao.findUser(userId));
	}

	@Override
	public boolean checkPassword(String userId, String enteredPassword) {
		// 사용자 정보 조회
		UserDto user =UserDto.toDto(userDao.findUser(userId));

		// 사용자가 존재하고 비밀번호가 일치하는지 확인
		return user != null && user.getUserPw().equals(enteredPassword);
	}

	@Override
	public List<UserDto> findUserList() {
		List<User> userList = userDao.findUserList();
		List<UserDto> userDtoList = new ArrayList<UserDto>();

		for (User user : userList) {
			userDtoList.add(UserDto.toDto(user));
		}
		return userDtoList;
	}

	@Override
	public boolean existsById(String userId) throws Exception {
		return userDao.existsById(userId);

	}

	@Override
	public String findUserIdByUserNameUserEmail(String userName, String userEmail) throws Exception {
		if (userName == null || userName.isEmpty()) {
			throw new Exception("사용자 이름이 잘못되었습니다.");
		}
		if (userEmail == null || userEmail.isEmpty()) {
			throw new Exception("사용자 이메일이 잘못되었습니다.");
		}
		String userId = userDao.findUserIdByUserNameUserEmail(userName, userEmail);
		if (userId == null) {
			throw new Exception("해당 정보로 등록된 사용자가 없습니다.");
		}
		return userId;
	}

	@Override
	public String findUserPwByUserIdUserPhone(String userId, String userPhone) throws Exception {
		if (userId == null || userId.isEmpty()) {
			throw new Exception("사용자 아이디가 잘못되었습니다.");
		}
		if (userPhone == null || userPhone.isEmpty()) {
			throw new Exception("사용자 번호가 잘못되었습니다.");
		}

		String userPw = userDao.findUserPwByUserIdUserPhone(userId, userPhone);
		if (userPw == null) {
			throw new Exception("해당 정보로 등록된 사용자가 없습니다.");
		}
		return userPw;
	}

}
