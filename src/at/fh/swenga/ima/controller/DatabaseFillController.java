package at.fh.swenga.ima.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import at.fh.swenga.ima.dao.UserRepository;
import at.fh.swenga.ima.dao.UserRoleRepository;
import at.fh.swenga.ima.model.User;
import at.fh.swenga.ima.model.UserRole;

@Controller
public class DatabaseFillController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserRoleRepository userRoleRepository;

	@RequestMapping(value = { "/fillDatabase", "fill" })
	public String fill() {

		// admin
		userRepository.save(new User("admin", "$2a$10$2BZh7qw/FSh23ZCbojA.OOoo7vzg7KaqHUp34l8/i9.ktxzcr3vJm", true));
		userRoleRepository.save(new UserRole(userRepository.findByUserName("admin"), "ROLE_USER"));
		userRoleRepository.save(new UserRole(userRepository.findByUserName("admin"), "ROLE_ADMIN"));

		// demo user (no student)
		userRepository.save(new User("user", "$2a$10$2BZh7qw/FSh23ZCbojA.OOoo7vzg7KaqHUp34l8/i9.ktxzcr3vJm", true));
		userRoleRepository.save(new UserRole(userRepository.findByUserName("user"), "ROLE_USER"));

		// students
		List<String> students = new ArrayList<String>();
		students.add("folkdani13");
		students.add("seebacha14");
		students.add("lichtene05");
		students.add("skerbinz13");
		students.add("herzocar14");
		students.add("bajricam14");
		students.add("fuchsmic14");
		students.add("graffeli13");
		students.add("hasenbic13");
		students.add("hoxahagra14");
		students.add("hysistev14");
		students.add("kandlhof14");
		students.add("karimova13");
		students.add("knallerm14");
		students.add("koernerp13");
		students.add("laggerch14");
		students.add("leitners07");
		students.add("meizenit14");
		students.add("ortmann14");
		students.add("rexhajrr13");
		students.add("schneida13");
		students.add("spalekni14");
		students.add("spanning13");
		students.add("steinkel14");
		students.add("wagenede14");

		for (String student : students) {
			userRepository.save(new User(student, "$2a$10$2BZh7qw/FSh23ZCbojA.OOoo7vzg7KaqHUp34l8/i9.ktxzcr3vJm", true));
			userRoleRepository.save(new UserRole(userRepository.findByUserName(student), "ROLE_USER"));
			userRoleRepository.save(new UserRole(userRepository.findByUserName(student), "ROLE_STUDENT"));
		}

		return "databaseFilled";
	}

}
