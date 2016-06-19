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
public class TimetableController {

	@Autowired
	StudentRepository studentRepository;
	@Autowired
	UserRepository userRepository;


	@RequestMapping(value = "/my_timetable", method = RequestMethod.GET)
	public String showOwnTimetable(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		if (userDetails != null) {
			model.addAttribute("user", userDetails);
			
			StudentModel student = studentRepository.findFirstByUserName(userDetails.getUsername());
			if (student != null) {
				model.addAttribute("student", student);
			}
			
			return "timetable";
		} else {
			model.addAttribute("errorMessage", "Account doesn't exist!");
			return "forward:/";
		}
	}
	
}
