package net.sjune.mongodb.web.controller;

import java.util.List;

import net.sjune.mongodb.persistence.domain.User;
import net.sjune.mongodb.persistence.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	public UserService userService;

	@RequestMapping("userList.do")
	public String userList(ModelMap model) throws Exception {
		logger.info("called userList.");
		List<User> users = userService.getUsers();
		
		if(users == null) { // 마스터 서버를 못찾을 경우 에러페이지 변경
			
		} else {
			model.addAttribute("users", users);
		}		
		
		return "userList";
	}
	
	
	@RequestMapping("insertList.do")
	public String insertList(ModelMap model) throws Exception {
		logger.info("called userList.");
		
		User user = new User();
	    user.setUserName("testUserName1");
	    user.setPassword("1111");

	    userService.insertUser(user);
	    
	    
		List<User> users = userService.getUsers();
		model.addAttribute("users", users);
		
		return "userList";
	}
}
