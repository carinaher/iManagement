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

	/*
	 * @RequestMapping(value = { "/getPage" }) public String getPage(Pageable
	 * page, Model model) { // Pagenumbers began with 0 Page<StudentModel>
	 * students = studentRepository.findAll(page); // Page contains the list and
	 * additional information model.addAttribute("students",
	 * students.getContent()); model.addAttribute("studentsPage", students); //
	 * send the whole page // Information to the // jsp
	 * 
	 * return "index"; }
	 */
	
	

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

	@RequestMapping("/fillStudents")
	@Transactional
	public String fillData(Model model) {

		List<StudentModel> students = new ArrayList<>();
		students.add(new StudentModel("folkdani13", "Daniel", "Folk", "Folkdani13", "daniel.folk@edu-fh.joanneum.at",14, 1));
		students.add(new StudentModel("seebacha14", "Andreas", "Seebacher", "andseeb","andreas.seebacher@edu-fh.joanneum.at", 14, 1));
		students.add(new StudentModel("lichtene05", "Alexander", "Lichtenegger", "AlexanderLichtenegger","alexander.lichtenegger@edu-fh.joanneum.at", 14, 1));
		students.add(new StudentModel("skerbinz13", "Verena", "Skerbinz", "verisker","verena.skerbinz@edu-fh.joanneum.at", 14, 1));
		students.add(new StudentModel("herzocar14", "Carina", "Herzog", "carinaher", "carina.herzog@edu-fh.joanneum.at",14, 1));
		
		students.add(new StudentModel("bajricam14", "Amar", "Bajric", "amarbajric", "Amar.Bajric@edu-fh.joanneum.at",14, 2));
		students.add(new StudentModel("fuchsmic14", "Michael", "Fuchs", "deKilla", "Michael.Fuchs@edu-fh.joanneum.at",14, 1));
		students.add(new StudentModel("graffeli13", "Felix", "Graf", "Graf-Carello", "Felix.Graf@edu-fh.joanneum.at",14, 1));
		students.add(new StudentModel("hasenbic13", "Timo", "Hasenbichler", "timoooo", "Timo.Hasenbichler@edu-fh.joanneum.at",14, 2));
		students.add(new StudentModel("hoxahagra14", "Granit", "Hoxha", "hoxhagra14", "Granit.Hoxha@edu-fh.joanneum.at",14, 2));
		students.add(new StudentModel("hysistev14", "Steven", "Hysi", "lionade", "Steven.Hysi@edu-fh.joanneum.at",14, 2));
		students.add(new StudentModel("kandlhof14", "Daniel", "Kandlhofer", "danielkandlhofer", "Daniel.Kandlhofer@edu-fh.joanneum.at",14, 2));
		students.add(new StudentModel("karimova13", "Elza", "Karimova", "elsakarimova", "Elza.Karimova@edu-fh.joanneum.at",14, 1));
		students.add(new StudentModel("knallerm14", "Markus", "Knaller", "knalla66", "Markus.Knaller@edu-fh.joanneum.at",14, 2));
		students.add(new StudentModel("koernerp13", "Paul", "Körner", "mcKorleone", "Paul.Körner@edu-fh.joanneum.at",14, 1));
		students.add(new StudentModel("laggerch14", "Christian", "Lagger", "clagger", "Christian.Lagger@edu-fh.joanneum.at",14, 2));
		students.add(new StudentModel("leitners07", "Stefan", "Leitner", "loete", "Stefan.Leitner@edu-fh.joanneum.at",14, 2));
		students.add(new StudentModel("meizenit14", "Georg", "Meizenitsch", "meizenit14", "Georg.Meizenitsch@edu-fh.joanneum.at",14, 2));
		students.add(new StudentModel("ortmann14", "Thomas", "Ortmann", "tortmann", "Thomas.Ortmann@edu-fh.joanneum.at",14, 1));
		students.add(new StudentModel("rexhajrr13", "Rrolf", "Rexhaj", "rexhajrr13", "Rrolf.Rexhaj@edu-fh.joanneum.at",14, 2));
		students.add(new StudentModel("schneida13", "Andreas", "Schneider", "zerberuss", "Andreas.Schneider2@edu-fh.joanneum.at",14, 2));
		students.add(new StudentModel("spalekni14", "Nina", "Spalek", "NSpalek", "Nina.Spalek@edu-fh.joanneum.at",14, 1));
		students.add(new StudentModel("spanning13", "Florian", "Spanninger", "spanning13", "Florian.Spanninger@edu-fh.joanneum.at",14, 2));
		students.add(new StudentModel("steinkel14", "Wolfgang", "Steinkellner", "steinkel14", "Wolfgang.Steinkellner@edu-fh.joanneum.at",14, 2));
		students.add(new StudentModel("wagenede14", "Maximilian", "Wageneder", "mwageneder", "Maximilian.Wageneder@edu-fh.joanneum.at",14, 1));
		
		
		
		studentRepository.save(students);
		
		setUserPanel(model);
		return "forward:/student";
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
	


	String setUserPanel(Model model) {
		
		StudentModel student = studentRepository.findFirstByUserName(getUser(model));
		if (student != null) {
			model.addAttribute("student", student);
			model.addAttribute("setSearch", "findStudent");
		}
	    else {
			model.addAttribute("errorMessage", "Student doesn't exist!");
		}
		return "search";
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
