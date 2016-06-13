package at.fh.swenga.ima.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.ima.dao.StudentRepository;
import at.fh.swenga.ima.dao.UserRepository;
import at.fh.swenga.ima.model.User;

@Controller
public class AccountController {

	@Autowired
	StudentRepository studentRepository;
	@Autowired
	UserRepository userRepository;

	// StudentModel student =
	// studentRepository.findById(newStudentModel.getId());

	@RequestMapping(value = "/editPassword", method = RequestMethod.GET)
	public String showEditPasswordForm(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		if (userDetails != null) {
			model.addAttribute("user", userDetails);
			System.out.println(userDetails.getUsername());
			return "passwordEdit";
		} else {
			model.addAttribute("errorMessage", "Account doesn't exist!");
			return "forward:/";
		}
	}

	@RequestMapping(value = "/editPassword", method = RequestMethod.POST)
	public String editPassword(Model model, @AuthenticationPrincipal UserDetails userDetails,
			@RequestParam String oldPassword, @RequestParam String newPassword,
			@RequestParam String newPasswordConfirmation, HttpServletRequest request, HttpServletResponse response) {

		System.out.println(userDetails.getUsername());
		System.out.println(oldPassword);
		System.out.println(userDetails.getPassword());
		// for some reason, userDetails.getPassword() always returns null
		User user = userRepository.findByUserName(userDetails.getUsername());
		model.addAttribute("user", userDetails);
		if (!newPassword.equals(newPasswordConfirmation)) {
			model.addAttribute("errorMessage", "Password confirmation didn't match!");
			return "passwordEdit";
		} else if (!BCrypt.checkpw(oldPassword, user.getPassword())) {
			model.addAttribute("errorMessage", "Wrong current password entered!");
			return "passwordEdit";
		} else if (!userDetails.isAccountNonExpired() || !userDetails.isAccountNonLocked() || !userDetails.isCredentialsNonExpired()) {
			model.addAttribute("errorMessage", "Account expired/locked or credentials expired!");
			return "passwordEdit";
		} else { // everything ok, go ahead and change password
			String hashedNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
			user.setPassword(hashedNewPassword);
			userRepository.save(user);
			model.addAttribute("message", "Password for user \"" + userDetails.getUsername() + "\" has been changed.");
			
			
			// logout
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			new SecurityContextLogoutHandler().logout(request, response, auth);;
			
			return "redirect:/login?logout";
		}

	}
}
