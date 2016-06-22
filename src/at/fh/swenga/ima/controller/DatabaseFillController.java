package at.fh.swenga.ima.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import at.fh.swenga.ima.dao.StudentRepository;
import at.fh.swenga.ima.dao.UserRepository;
import at.fh.swenga.ima.dao.UserRoleRepository;
import at.fh.swenga.ima.model.StudentModel;
import at.fh.swenga.ima.model.User;
import at.fh.swenga.ima.model.UserRole;

@Controller
public class DatabaseFillController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserRoleRepository userRoleRepository;
	@Autowired
	StudentRepository studentRepository;

	@RequestMapping(value = { "/fillDatabase", "fill" })
	public String fill() {

		// admin
		userRepository.save(new User("admin", "$2a$10$2BZh7qw/FSh23ZCbojA.OOoo7vzg7KaqHUp34l8/i9.ktxzcr3vJm", true));
		userRoleRepository.save(new UserRole(userRepository.findByUserName("admin"), "ROLE_USER"));
		userRoleRepository.save(new UserRole(userRepository.findByUserName("admin"), "ROLE_ADMIN"));

		// demo user (no student)
		userRepository.save(new User("user", "$2a$10$2BZh7qw/FSh23ZCbojA.OOoo7vzg7KaqHUp34l8/i9.ktxzcr3vJm", true));
		userRoleRepository.save(new UserRole(userRepository.findByUserName("user"), "ROLE_USER"));

		// student roles
		List<String> studentRoles = new ArrayList<String>();
		studentRoles.add("folkdani13");
		studentRoles.add("seebacha14");
		studentRoles.add("lichtene05");
		studentRoles.add("skerbinz13");
		studentRoles.add("herzocar14");
		studentRoles.add("bajricam14");
		studentRoles.add("fuchsmic14");
		studentRoles.add("graffeli13");
		studentRoles.add("hasenbic13");
		studentRoles.add("hoxahagra14");
		studentRoles.add("hysistev14");
		studentRoles.add("kandlhof14");
		studentRoles.add("karimova13");
		studentRoles.add("knallerm14");
		studentRoles.add("koernerp13");
		studentRoles.add("laggerch14");
		studentRoles.add("leitners07");
		studentRoles.add("meizenit14");
		studentRoles.add("ortmann14");
		studentRoles.add("rexhajrr13");
		studentRoles.add("schneida13");
		studentRoles.add("spalekni14");
		studentRoles.add("spanning13");
		studentRoles.add("steinkel14");
		studentRoles.add("wagenede14");

		for (String student : studentRoles) {
			userRepository.save(new User(student, "$2a$10$2BZh7qw/FSh23ZCbojA.OOoo7vzg7KaqHUp34l8/i9.ktxzcr3vJm", true));
			userRoleRepository.save(new UserRole(userRepository.findByUserName(student), "ROLE_USER"));
			userRoleRepository.save(new UserRole(userRepository.findByUserName(student), "ROLE_STUDENT"));
		}
		
		// students
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
		
		
		

		return "databaseFilled";
	}

}
