package at.fh.swenga.ima.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.ima.dao.UserRepository;
import at.fh.swenga.ima.dao.UserRoleRepository;
import at.fh.swenga.ima.model.User;
import at.fh.swenga.ima.model.UserRole;

@Controller
public class UserRoleController {

	@Autowired
	UserRoleRepository userRoleRepository;
	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = { "/userrole", "list" })
	public String index(Model model) {
		List<UserRole> userroles = userRoleRepository.findAll();
		model.addAttribute("userroles", userroles);
		model.addAttribute("type", "findAll");
		model.addAttribute("pageTitle", "User Role List");

		return "userroleIndex";
	}

	@RequestMapping(value = "/deleteUserrole", method = RequestMethod.POST)
	public String deleteData(Model model, @RequestParam int userRoleId) {
		userRoleRepository.delete(userRoleRepository.findByUserRoleId(userRoleId));
		model.addAttribute("message", "user role with id " + userRoleId + " deleted.");

		return "forward:/userrole";
	}

	@RequestMapping(value = "/addUserrole", method = RequestMethod.GET)
	public String showAddUserRoleForm(Model model) {
		model.addAttribute("pageTitle", "Add User Role");
		return "userroleEdit";
	}

	@RequestMapping(value = "/addUserrole", method = RequestMethod.POST)
	public String addUserRole(@Valid @RequestParam int userRoleId, @RequestParam String userName,
			@RequestParam String role, Model model) {

		try {

			UserRole userRole = userRoleRepository.findByUserRoleId(userRoleId);

			if (userRole != null) {
				model.addAttribute("errorMessage", "User role already exists!<br>");
			} else {
				UserRole newUserRole = new UserRole(userRepository.findByUserName(userName), role);
				userRoleRepository.save(newUserRole);
				model.addAttribute("message", "New user role: user " + userName + " added as " + role);
			}

			return "forward:/userrole";

		} catch (Exception e) {
			String errorMessage = "Input is invalid";
			// put the errors into the model
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/userrole";
		}
	}

	@RequestMapping(value = "/editUserrole", method = RequestMethod.GET)
	public String showEditUserRoleForm(Model model, @RequestParam int userRoleId) {
		UserRole userRole = userRoleRepository.findByUserRoleId(userRoleId);
		if (userRole != null) {
			model.addAttribute("userRole", userRole);
			model.addAttribute("pageTitle", "Edit User Role");
			return "userroleEdit";
		} else {
			model.addAttribute("errorMessage", "Couldn't find user role with id " + userRoleId);
			return "forward:/userrole";
		}
	}

	@RequestMapping(value = "/editUserrole", method = RequestMethod.POST)
	public String editUserRole(@Valid @RequestParam int userRoleId, @RequestParam String userName,
			@RequestParam String role, Model model) {

		try {

			UserRole userRole = userRoleRepository.findByUserRoleId(userRoleId);
			User user = userRepository.findByUserName(userName);
			if (userRole == null) {
				model.addAttribute("errorMessage", "User role does not exist!<br>");
			} else if (user == null) {
				model.addAttribute("errorMessage", "Error: User " + userName + " does not exist!");
			} else {
				userRole.setUser(user);
				userRole.setRole(role);
				userRoleRepository.save(userRole);
				model.addAttribute("message", "Changed user role for user " + userName + " to " + role);
			}

			return "forward:/userrole";

		} catch (Exception e) {
			String errorMessage = "Input is invalid";
			// put the errors into the model
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/userrole";
		}
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "showError";

	}
}
