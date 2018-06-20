package com.userdata.dao;

import java.util.List;

import com.userdata.vo.UserVo;

public interface IUserDAO {
	
	List<UserVo> getAllUsers();
	UserVo getUserById(int userId);
	void addUser(UserVo userVo);
	void updateUser(UserVo userVo);
	void deleteUser(int userId);
	boolean userExists(String emailAddress);
	boolean validateCredentials(String emailAddress,String password);
	UserVo getUserByEmail(String emailAddress);

}
