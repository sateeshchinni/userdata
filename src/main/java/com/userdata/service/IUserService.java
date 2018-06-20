package com.userdata.service;

import java.util.List;

import com.userdata.vo.UserVo;

public interface IUserService {
	
	List<UserVo> getAllUsers();
	UserVo getUserById(int userId);
	boolean addUser(UserVo userVo);
	void updateUser(UserVo userVo);
	void deleteUser(int userId);
	boolean validateUserEmailAddress(UserVo userVo);
	boolean validateUserCredentials(UserVo userVo);
	UserVo getUserByEmail(String emailAddress);

}
