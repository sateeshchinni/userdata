package com.userdata.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.userdata.vo.UserVo;

public class UserVoRowMapper implements RowMapper<UserVo> {

	@Override
	public UserVo mapRow(ResultSet row, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		UserVo uvo=new UserVo();

		uvo.setFirstName(row.getString("FIRST_NAME"));
		uvo.setLastName(row.getString("LAST_NAME"));
		uvo.setMiddleName(row.getString("MIDDLE_NAME"));
		uvo.setEmailAddress(row.getString("EMAIL_ADDRESS"));
		uvo.setSno(row.getInt("SNO"));

		return uvo;
	}
	
	 

}
