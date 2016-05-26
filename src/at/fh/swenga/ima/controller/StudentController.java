package at.fh.swenga.ima.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	@RequestMapping(value = { "/find" })
	// search for Requestparameter with the name type and store it in the string
	// type
	public String find(Model model, @RequestParam String searchString, @ModelAttribute("type") String type) {
		List<StudentModel> students = null;
		int count = 0;

		switch (type) {
		case "":
			break;

		default:
			students = studentRepository.findAll();

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

		List<StudentModel> students = new ArrayList<>();
		students.add(new StudentModel("Folkdani13", "Daniel", "Folk", "Folkdani13", "daniel.folk@edu-fh.joanneum.at",14, 1));
		students.add(new StudentModel("Seebacha14", "Andreas", "Seebacher", "Andseeb","andreas.seebacher@edu-fh.joanneum.at", 14, 1));
		students.add(new StudentModel("Lichtene05", "Alexander", "Lichtenegger", "AlexanderLichtenegger","alexander.lichtenegger@edu-fh.joanneum.at", 14, 1));
		students.add(new StudentModel("Skerbinz13", "Verena", "Skerbinz", "Verisker","verena.skerbinz@edu-fh.joanneum.at", 14, 1));
		students.add(new StudentModel("Herzocar14", "Carina", "Herzog", "Carinaher", "carina.herzog@edu-fh.joanneum.at",14, 1));
		
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
		students.add(new StudentModel("spalekni", "Nina", "Spalek", "NSpalek", "Nina.Spalek@edu-fh.joanneum.at",14, 1));
		students.add(new StudentModel("spanning13", "Florian", "Spanninger", "spanning13", "Florian.Spanninger@edu-fh.joanneum.at",14, 2));
		students.add(new StudentModel("steinkel14", "Wolfgang", "Steinkellner", "steinkel14", "Wolfgang.Steinkellner@edu-fh.joanneum.at",14, 2));
		students.add(new StudentModel("wagenede14", "Maximilian", "Wageneder", "mwageneder", "Maximilian.Wageneder@edu-fh.joanneum.at",14, 1));
		
		
		
		studentRepository.save(students);

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
