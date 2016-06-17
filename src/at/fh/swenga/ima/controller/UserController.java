package at.fh.swenga.ima.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.ima.dao.UserRepository;
import at.fh.swenga.ima.model.User;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = { "/user", "list" })
	public String index(Model model) {
		List<User> users = userRepository.findAll();
		model.addAttribute("users", users);
		model.addAttribute("type", "findAll");
		model.addAttribute("pageTitle", "User List");

		return "userIndex";
	}

	
	@RequestMapping(value = { "/findUser" })
	// search for Requestparameter with the name type and store it in the string
	// type
	public String find(Model model, @RequestParam String searchString, @ModelAttribute("type") String type) {
		List<User> users = null;
		int count = 0;

		switch (type) {
		case "":
			break;

		default:
			users = userRepository.findAll();

		}

		model.addAttribute("users", users);
		model.addAttribute("count", count);
		return "userIndex";
	}

	/*
	@RequestMapping(value = { "/findUserById" })
	public String findById(@RequestParam("id") User u, Model model) {
		List<User> users = new ArrayList<>();
		users.add(u);
		model.addAttribute("users", users);
		return "userIndex";
	}*/

	@RequestMapping("/deleteUser")
	public String deleteData(Model model, @RequestParam String userName) {
		userRepository.delete(userRepository.findByUserName(userName));

		return "forward:/user";
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public String showAddUserForm(Model model) {
		model.addAttribute("pageTitle", "Add User");
		return "userEdit";
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute User newUser, BindingResult bindingResult,
			Model model) {
 
		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			// put the errors into the model
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/user";
		}
 
		User user = userRepository.findByUserName(newUser.getUserName());
 
		if (user != null) {
			model.addAttribute("errorMessage", "User already exists!<br>");
		} else {
			userRepository.save(newUser);
			model.addAttribute("message", "New user " + newUser.getUserName() + " added.");
		}
 
		return "forward:/user";
	}
	
	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public String showEditUserForm(Model model, @RequestParam String userName) {
		User user = userRepository.findByUserName(userName);
		if (user != null) {
			model.addAttribute("user", user);
			model.addAttribute("pageTitle", "Edit User");
			return "userEdit";
		} else {
			model.addAttribute("errorMessage", "Couldn't find user " + userName);
			return "forward:/user";
		}
	}
	
	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public String editUser(@Valid @ModelAttribute User editedUser, BindingResult bindingResult,
			Model model) {
 
		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/user";
		}
 
		User user = userRepository.findByUserName(editedUser.getUserName());
 
		if (user == null) {
			model.addAttribute("errorMessage", "User" + editedUser.getUserName() + "does not exist!<br>");
		} else {
			user.setPassword(editedUser.getPassword());
			user.setEnabled(editedUser.isEnabled());
			model.addAttribute("message", "Changed user " + editedUser.getUserName());
			userRepository.save(user);
		}
 
		return "forward:/user";
	}
	
	
	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "showError";

	}
}
