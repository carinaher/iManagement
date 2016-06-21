package at.fh.swenga.ima.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import at.fh.swenga.ima.dao.StudentRepository;
import at.fh.swenga.ima.model.StudentModel;

@Controller
public class StartpageController {

	@Autowired
	StudentRepository studentRepository;

	@RequestMapping("/")
	public String index(Model model) {
		// TODO: add models that are displayed on start page (-> calendar/todoList entries of current week)
		
		setUserPanel(model);
		return "index";
	}
	
	void setUserPanel(Model model) {
		
		StudentModel student = studentRepository.findFirstByUserName(getUser(model));
		if (student != null) {
			model.addAttribute("student", student);
		}
	}
	
	 String getUser(Model model) {
		 
	      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName(); //get logged in username
	      model.addAttribute("username", name);
	      return name;
	  }

}
