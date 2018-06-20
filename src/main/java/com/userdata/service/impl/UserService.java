package com.userdata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.userdata.dao.IUserDAO;
import com.userdata.service.IUserService;
import com.userdata.vo.UserVo;

@Service("userService")
@Transactional
public class UserService implements IUserService {
	
	@Autowired
	private IUserDAO userDao;

	@Override
	public List<UserVo> getAllUsers() {
		// TODO Auto-generated method stub
		return userDao.getAllUsers();
	}

	@Override
	public UserVo getUserById(int userId) {
		UserVo uvo = userDao.getUserById(userId);
		return uvo;
	}
	
	@Override
	public UserVo getUserByEmail(String emailAddress) {
		UserVo uvo = userDao.getUserByEmail(emailAddress.toUpperCase());
		return uvo;
	}


	@Override
	public boolean addUser(UserVo userVo) {
		// TODO Auto-generated method stub
		
		if(userDao.userExists(userVo.getEmailAddress())){
			return false;
		}else{
			userDao.addUser(userVo);
			return true;
		}

	}

	@Override
	public void updateUser(UserVo userVo) {
		// TODO Auto-generated method stub
		userDao.updateUser(userVo);

	}

	@Override
	public void deleteUser(int userId) {
		// TODO Auto-generated method stub
		userDao.deleteUser(userId);

	}

	@Override
	public boolean validateUserEmailAddress(UserVo userVo) {
		// TODO Auto-generated method stub
			return userDao.userExists(userVo.getEmailAddress());
	}

	@Override
	public boolean validateUserCredentials(UserVo userVo) {
		// TODO Auto-generated method stub
		return userDao.validateCredentials(userVo.getEmailAddress(),userVo.getPassword());
	}
	
	

}
