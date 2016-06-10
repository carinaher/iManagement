package at.fh.swenga.ima.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import at.fh.swenga.ima.dao.UserRepository;
import at.fh.swenga.ima.dao.UserRoleRepository;
import at.fh.swenga.ima.model.User;
import at.fh.swenga.ima.model.UserRole;

@Controller
public class DatabaseFillController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserRoleRepository userRoleRepository;


	@RequestMapping(value = { "/fillDatabase", "fill" }, produces = MediaType.TEXT_HTML_VALUE)
	public
	@ResponseBody String fill() {
		
		// users
		User[] users = new User[] {
				new User("admin", "$2a$10$2BZh7qw/FSh23ZCbojA.OOoo7vzg7KaqHUp34l8/i9.ktxzcr3vJm", true),
				new User("user", "$2a$10$2BZh7qw/FSh23ZCbojA.OOoo7vzg7KaqHUp34l8/i9.ktxzcr3vJm", true),
				new User("user2", "$2a$10$2BZh7qw/FSh23ZCbojA.OOoo7vzg7KaqHUp34l8/i9.ktxzcr3vJm", true)
		};
		userRepository.save(Arrays.asList(users));
		
		// user roles
		UserRole[] roles = new UserRole[] {
				new UserRole(userRepository.findByUserName("admin"), "ROLE_USER"),
				new UserRole(userRepository.findByUserName("admin"), "ROLE_ADMIN"),
				
				new UserRole(userRepository.findByUserName("user"), "ROLE_USER"),
				new UserRole(userRepository.findByUserName("user"), "ROLE_STUDENT"),
				
				new UserRole(userRepository.findByUserName("user2"), "ROLE_USER"),
				new UserRole(userRepository.findByUserName("user2"), "ROLE_STUDENT")
		};
		userRoleRepository.save(Arrays.asList(roles));
		
		

		return "Created users and roles!<br><a href=\"login\">Go to Login</a>";
	}
	
}
