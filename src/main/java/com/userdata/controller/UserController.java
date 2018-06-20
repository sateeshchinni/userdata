package com.userdata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.userdata.response.BaseResponse;
import com.userdata.service.IUserService;
import com.userdata.vo.UserVo;

@RestController
public class UserController {
	
	@Autowired
	private IUserService userService;
	

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserVo> getUserById(@PathVariable("id") Integer id){
		UserVo uvo = userService.getUserById(id);
		if(uvo == null){
			return new ResponseEntity<UserVo>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserVo>(uvo , HttpStatus.OK);
	}

	@RequestMapping(value = "/getusers/", method = RequestMethod.GET)
	public ResponseEntity<List<UserVo>> getAllUsers(){
		List<UserVo> list = userService.getAllUsers();
		if(list.isEmpty()){
			return new ResponseEntity<List<UserVo>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserVo>>(list , HttpStatus.OK);
	}	
	
	
	@RequestMapping(value = "/adduser/", method = RequestMethod.POST)
	public ResponseEntity<Void> addUser(@RequestBody UserVo userVo, UriComponentsBuilder builder) {
                boolean flag = userService.addUser(userVo);
                if (flag == false) {
                	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/user/{id}").buildAndExpand(userVo.getSno()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/registeruser/", method = RequestMethod.POST)
	public BaseResponse registerUser(@RequestBody UserVo userVo, UriComponentsBuilder builder) {
				BaseResponse response = new BaseResponse();
			
                boolean flag = userService.addUser(userVo);
                if (flag == false) {
                	response.setCode(404);
                	response.setMessage("User Already Exists ");
                	response.setStatus("User Not Created");

                } else{
                	
                	response.setCode(200);
                	response.setMessage("User Registred ");
                	response.setStatus("User Created Successfully");
                	return response;
                	
                }
            	return response;
      }
	
	@RequestMapping(value = "/validateuser/", method = RequestMethod.POST)
	public BaseResponse validateuser(@RequestBody UserVo userVo) {
				BaseResponse response = new BaseResponse();
		
                boolean flag = userService.validateUserEmailAddress(userVo);
                if (flag) {
                	flag = userService.validateUserCredentials(userVo);

                	
                	if(flag){
                    	response.setCode(200);
                    	response.setMessage("User validated successfully");
                    	response.setStatus("Valid User ");
                    	response.setCurrentUser(userService.getUserByEmail(userVo.getEmailAddress()));
                	}else{
                    	response.setCode(404);
                    	response.setMessage("Credentials are incorrect");
                    	response.setStatus("User Not Exists");
                	}
                }else{
                	response.setCode(404);
                	response.setMessage("User Email Address Not Exists in the system");
                	response.setStatus("User Not Exists");
                }
                
                return response;
                
	}
	
	
	
	@RequestMapping(value = "/updateuser/", method = RequestMethod.PUT)
	public ResponseEntity<UserVo> updateUser(@RequestBody UserVo userVo) {
		userService.updateUser(userVo );
		return new ResponseEntity<UserVo>(userVo, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/deleteuser/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	

}
