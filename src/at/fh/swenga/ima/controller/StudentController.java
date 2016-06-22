package at.fh.swenga.ima.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

import at.fh.swenga.ima.dao.StudentRepository;
import at.fh.swenga.ima.model.StudentModel;

@Controller
public class StudentController {

	@Autowired
	StudentRepository studentRepository;

	@RequestMapping(value = { "/student", "list" })
	public String index(Model model) {
		
		setUserPanel(model);
		List<StudentModel> students = studentRepository.findAll();
		
		model.addAttribute("students", students);
		model.addAttribute("type", "findAll");
		model.addAttribute("pageTitle", "Student List");
		setUserPanel(model);
		return "studentIndex";
		

	}	

	@RequestMapping(value = { "/findStudent" })
	public String find(Model model, @RequestParam String searchString, @ModelAttribute("type") String type) {
		List<StudentModel> students = new ArrayList<>();
		students = studentRepository.findByUserNameContainsOrFirstNameContainsOrLastNameContainsOrGithubUserContainsAllIgnoreCase(searchString,searchString,searchString,searchString);

		model.addAttribute("students", students);

		
		setUserPanel(model);
		return "studentIndex";
	}
	
	@RequestMapping(value = { "/findStudentById" })
	public String findById(@RequestParam("id") StudentModel s, Model model) {
		List<StudentModel> students = new ArrayList<>();
		students.add(s);
		model.addAttribute("students", students);
		
		setUserPanel(model);
		return "studentIndex";
	}


	@RequestMapping("/deleteStudent")
	public String deleteData(Model model, @RequestParam int id) {
		studentRepository.delete(id);

		setUserPanel(model);
		return "forward:/student";
	}

	@RequestMapping(value = "/addStudent", method = RequestMethod.GET)
	public String showAddStudentForm(Model model) {
		model.addAttribute("pageTitle", "Add Student");
		
		setUserPanel(model);
		return "studentEdit";
	}
	
	@RequestMapping(value = "/addStudent", method = RequestMethod.POST)
	public String addStudent(@Valid @ModelAttribute StudentModel newStudentModel, BindingResult bindingResult,
			Model model) {
 
		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			// put the errors into the model
			model.addAttribute("errorMessage", errorMessage);
			
			setUserPanel(model);
			return "forward:/student";
		}
 
		StudentModel student = studentRepository.findById(newStudentModel.getId());
 
		if (student != null) {
			model.addAttribute("errorMessage", "Student already exists!<br>");
		} else {
			studentRepository.save(newStudentModel);
			model.addAttribute("message", "Added new student " + newStudentModel.getUserName());
		}
 
		setUserPanel(model);
		return "forward:/student";
	}
	
	@RequestMapping(value = "/editStudent", method = RequestMethod.GET)
	public String showEditStudentForm(Model model, @RequestParam int id) {
		StudentModel student = studentRepository.findById(id);
		if (student != null) {
			model.addAttribute("student", student);
			model.addAttribute("pageTitle", "Edit Student");
			
			setUserPanel(model);
			return "studentEdit";
		} else {
			model.addAttribute("errorMessage", "Couldn't find student " + id);
			
			setUserPanel(model);
			return "forward:/student";
		}
	}
	
	@RequestMapping(value = "/editStudent", method = RequestMethod.POST)
	public String editStudent(@Valid @ModelAttribute StudentModel editedStudentModel, BindingResult bindingResult,
			Model model) {
 
		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			
			setUserPanel(model);
			return "forward:/student";
		}
 
		StudentModel student = studentRepository.findFirstByUserName(editedStudentModel.getUserName());
 
		if (student == null) {
			model.addAttribute("errorMessage", "Student " + editedStudentModel.getUserName() + " does not exist!<br>");
		} else {
			student.setUserName(editedStudentModel.getUserName());
			student.setFirstName(editedStudentModel.getFirstName());
			student.setLastName(editedStudentModel.getLastName());
			student.setGithubUser(editedStudentModel.getGithubUser());
			student.seteMail(editedStudentModel.geteMail());
			model.addAttribute("message", "Changed student " + editedStudentModel.getUserName());
			studentRepository.save(student);
		}
 
		setUserPanel(model);
		return "forward:/student";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String handleLogin() {
		return "login";
	}
	


	void setUserPanel(Model model) {
		
		StudentModel student = studentRepository.findFirstByUserName(getUser(model));
		if (student != null) {
			model.addAttribute("student", student);
		}
		model.addAttribute("setSearch", "findStudent");
	}
	
	 String getUser(Model model) {
		 
	      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName(); //get logged in username
	      model.addAttribute("username", name);
	      return name;
	  }
	
	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "showError";

	}
}
