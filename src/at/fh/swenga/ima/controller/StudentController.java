package at.fh.swenga.ima.controller;

import java.util.ArrayList;
import java.util.List;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.ima.dao.StudentRepository;
import at.fh.swenga.ima.model.StudentModel;


@Controller
public class StudentController {

	@Autowired
	StudentRepository studentRepository;

	@RequestMapping(value = { "/", "list" })
	public String index(Model model) {
		List<StudentModel> students = studentRepository.findAll();
		model.addAttribute("students", students);
		model.addAttribute("type", "findAll");

		return "index";
	}
	
	
	@RequestMapping(value = { "/getPage" })
	public String getPage(Pageable page, Model model) {
		// Pagenumbers began with 0
		Page<StudentModel> students = studentRepository.findAll(page);
		// Page contains the list and additional information
		model.addAttribute("students", students.getContent());
		model.addAttribute("studentsPage", students); // send the whole page
														// Information to the
														// jsp

		return "index";
	}
	
	
	
	@RequestMapping(value = { "/find" })
	// search for Requestparameter with the name type and store it in the string
	// type
	public String find(Model model, @RequestParam String searchString, @ModelAttribute("type") String type) {
		List<StudentModel> students = null;
		int count = 0;

		switch (type) {
		case "findAll":
			students = studentRepository.findAll(); // fill list with all the
														// employees
			break; // has to be there

		/*case "findByLastName":
			students = studentRepository.findByLastName(searchString);
			break;

		case "findByFirstName":
			students = studentRepository.findByFirstName(searchString);

			break;*/

		default:
			students = studentRepository.findAll(); // if no case matches,
														// this is executed
		}

		model.addAttribute("students", students);
		model.addAttribute("count", count);
		return "index";
	}

	
	@RequestMapping(value = { "/findById" })
	public String findById(@RequestParam("id") StudentModel s, Model model) {
		List<StudentModel> students = new ArrayList<>();
		students.add(s);
		model.addAttribute("students", students);
		return "index";
	}

	
	@RequestMapping("/fill")
	@Transactional
	public String fillData(Model model) {

		// Creates always the same data
		DataFactory df = new DataFactory();


			StudentModel sm = new StudentModel(df.getFirstName(), df.getFirstName(), df.getLastName(),df.getLastName(),df.getEmailAddress(),df.getNumberBetween(2013, 2015),df.getNumberBetween(1,3));

			studentRepository.save(sm);

		return "forward:list";
	}

	
	@RequestMapping("/delete")
	public String deleteData(Model model, @RequestParam int id) {
		studentRepository.delete(id);

		return "forward:list";
	}
	
	
	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "showError";

	}
}
