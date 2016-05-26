package at.fh.swenga.ima.controller;

import java.util.List;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import at.fh.swenga.ima.dao.TaskRepository;
import at.fh.swenga.ima.model.TaskModel;

@Controller
public class TaskController {

	@Autowired
	TaskRepository taskRepository;

	@RequestMapping(value = { "/task", "listTask" })
	public String index(Model model) {
		List<TaskModel> tasks = taskRepository.findAll();
		model.addAttribute("tasks", tasks);
		model.addAttribute("type", "findAll");

		return "taskIndex";
	}
	

	@RequestMapping("/fillTasks")
	@Transactional
	public String fillData(Model model) {

		// Creates always the same data
		DataFactory df = new DataFactory();

		for (int i = 0; i < 100; i++) {
			TaskModel tm = new TaskModel(df.getFirstName(), df.getFirstName(), df.chance(50), df.getBirthDate());
			taskRepository.save(tm);
		}

		return "forward:/task";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {
		return "showError";
	}
}
