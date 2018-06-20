package com.userdata.dao.impl;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.userdata.dao.IUserDAO;
import com.userdata.rowmappers.UserVoRowMapper;
import com.userdata.vo.UserVo;

@Transactional
@Repository
public class UserDao implements IUserDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	@Override
	public List<UserVo> getAllUsers() {
		
		String sql = "SELECT SNO , FIRST_NAME , LAST_NAME , MIDDLE_NAME , EMAIL_ADDRESS  FROM USER_DATA";
		RowMapper<UserVo> rowMapper = new UserVoRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
		
	}

	@Override
	public UserVo getUserById(int userId) {
		
		String sql = "SELECT FIRST_NAME , LAST_NAME , MIDDLE_NAME , EMAIL_ADDRESS FROM USER_DATA WHERE SNO = ? ";
		RowMapper<UserVo> rowMapper = new BeanPropertyRowMapper<UserVo>(UserVo.class);
		UserVo userVo = jdbcTemplate.queryForObject(sql, rowMapper, userId); 
		return userVo;
		
	}
	
	@Override
	public UserVo getUserByEmail(String emailAddress) {
		
		String sql = "SELECT SNO, FIRST_NAME , LAST_NAME , MIDDLE_NAME , EMAIL_ADDRESS, PASSWORD, CREATED_DATE, MODIFIED_DATE, LAST_LOGIN_DATE, ACTIVE FROM USER_DATA WHERE upper(EMAIL_ADDRESS) = ? ";
		RowMapper<UserVo> rowMapper = new BeanPropertyRowMapper<UserVo>(UserVo.class);
		UserVo userVo = jdbcTemplate.queryForObject(sql, rowMapper, emailAddress); 
		return userVo;
		
	}
	
	

	@Override
	public void addUser(UserVo userVo) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO USER_DATA ( FIRST_NAME , LAST_NAME , MIDDLE_NAME , EMAIL_ADDRESS ,PASSWORD, CREATED_DATE,MODIFIED_DATE,LAST_LOGIN_DATE,ACTIVE ) VALUES (? , ? , ? , ? , ? , ? , ? , ?, ?) ";
		jdbcTemplate.update(sql, userVo.getFirstName(),userVo.getLastName(),userVo.getMiddleName(),userVo.getEmailAddress(), userVo.getPassword() ,new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),"Y");
		
		
	}

	@Override
	public void updateUser(UserVo userVo) {
		// TODO Auto-generated method stub
		
		String sql = " UPDATE USER_DATA SET FIRST_NAME = ?  , LAST_NAME = ? , MIDDLE_NAME = ?, EMAIL_ADDRESS = ?, MODIFIED_DATE = ? WHERE SNO = ? ";
		jdbcTemplate.update(sql, userVo.getFirstName(),userVo.getLastName(),userVo.getMiddleName(),userVo.getEmailAddress(),new Date(System.currentTimeMillis()),userVo.getSno());		
		
	}

	@Override
	public void deleteUser(int userId) {
		// TODO Auto-generated method stub
		String sql = " DELETE FROM USER_DATA WHERE SNO = ? ";
		jdbcTemplate.update(sql, userId);
		
	}

	@Override
	public boolean userExists(String emailAddress) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT COUNT(*) FROM USER_DATA WHERE EMAIL_ADDRESS  = ? ";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, emailAddress);
		
		if(count == 0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public boolean validateCredentials(String emailAddress, String password) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT COUNT(*) FROM USER_DATA WHERE EMAIL_ADDRESS  = ? and PASSWORD = ?  ";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, emailAddress, password);
		
		if(count == 0){
			return false;
		}else{
			return true;
		}
	
	
	}
	
	

}
