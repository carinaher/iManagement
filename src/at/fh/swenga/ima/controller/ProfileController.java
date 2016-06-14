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
import at.fh.swenga.ima.model.StudentModel;
import at.fh.swenga.ima.model.User;

@Controller
public class ProfileController {

	@Autowired
	StudentRepository studentRepository;
	@Autowired
	UserRepository userRepository;

	// StudentModel student =
	// studentRepository.findById(newStudentModel.getId());

	@RequestMapping(value = "/my_profile", method = RequestMethod.GET)
	public String showProfileInformation(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		if (userDetails != null) {
			model.addAttribute("user", userDetails);
			
			StudentModel student = studentRepository.findFirstByUserName(userDetails.getUsername());
			if (student != null) {
				model.addAttribute("student", student);
			}
			
			return "profileSelfEdit";
		} else {
			model.addAttribute("errorMessage", "Account doesn't exist!");
			return "forward:/";
		}
	}
	@RequestMapping(value = "/my_profile", method = RequestMethod.POST)
	public String editProfileInformation(Model model, @AuthenticationPrincipal UserDetails userDetails, @RequestParam String githubUser) {
		if (userDetails != null) {
			model.addAttribute("user", userDetails);
			StudentModel student = studentRepository.findFirstByUserName(userDetails.getUsername());
			if (student != null) {
				model.addAttribute("student", student);
				student.setGithubUser(githubUser);
				studentRepository.save(student);
				model.addAttribute("message", "Saved changes");
				return "profileSelfEdit";
			} else {
				model.addAttribute("errorMessage", "Student doesn't exist anyomre!");
				return "profileSelfEdit";				
			}

		} else {
			model.addAttribute("errorMessage", "Account doesn't exist!");
			return "forward:/";
		}
	}
	
	
	@RequestMapping(value = "/editPassword", method = RequestMethod.GET)
	public String showEditPasswordForm(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		if (userDetails != null) {
			model.addAttribute("user", userDetails);
			System.out.println(userDetails.getUsername());
			return "passwordSelfEdit";
		} else {
			model.addAttribute("errorMessage", "Account doesn't exist!");
			return "forward:/";
		}
	}

	@RequestMapping(value = "/editPassword", method = RequestMethod.POST)
	public String editPassword(Model model, @AuthenticationPrincipal UserDetails userDetails,
			@RequestParam String oldPassword, @RequestParam String newPassword,
			@RequestParam String newPasswordConfirmation, HttpServletRequest request, HttpServletResponse response) {

		// for some reason, userDetails.getPassword() always returns null
		User user = userRepository.findByUserName(userDetails.getUsername());
		model.addAttribute("user", userDetails);
		if (!newPassword.equals(newPasswordConfirmation)) {
			model.addAttribute("errorMessage", "Password confirmation didn't match!");
			return "passwordSelfEdit";
		} else if (!BCrypt.checkpw(oldPassword, user.getPassword())) {
			model.addAttribute("errorMessage", "Wrong current password entered!");
			return "passwordSelfEdit";
		} else if (!userDetails.isAccountNonExpired() || !userDetails.isAccountNonLocked() || !userDetails.isCredentialsNonExpired()) {
			model.addAttribute("errorMessage", "Account expired/locked or credentials expired!");
			return "passwordSelfEdit";
		} else { // everything ok, go ahead and change password
			String hashedNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
			user.setPassword(hashedNewPassword);
			userRepository.save(user);
			model.addAttribute("message", "Password for user \"" + userDetails.getUsername() + "\" has been changed.");

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			new SecurityContextLogoutHandler().logout(request, response, auth);;
			return "redirect:/login?pw-changed=true";
		}
		

	}
	

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		new SecurityContextLogoutHandler().logout(request, response, auth);;
		return "redirect:/login";
	}
}
