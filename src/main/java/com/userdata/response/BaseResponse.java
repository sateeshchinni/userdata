package com.userdata.response;

import java.util.List;

import com.userdata.vo.UserVo;

public class BaseResponse {
	
	private String message;
	private String status;
	private Integer code;
	private List<UserVo> users;
	private UserVo currentUser;
	
	
 
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}
 
	public void setStatus(String status) {
		this.status = status;
	}
 
	public Integer getCode() {
		return code;
	}
 
	public void setCode(Integer code) {
		this.code = code;
	}

	public List<UserVo> getUsers() {
		return users;
	}

	public void setUsers(List<UserVo> users) {
		this.users = users;
	}

	public UserVo getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserVo currentUser) {
		this.currentUser = currentUser;
	}

}
