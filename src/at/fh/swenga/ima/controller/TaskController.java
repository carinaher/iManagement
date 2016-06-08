package at.fh.swenga.ima.controller;

import java.util.List;

import javax.validation.Valid;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import at.fh.swenga.ima.dao.TaskRepository;
import at.fh.swenga.ima.model.TaskModel;

@Controller
public class TaskController {

	@Autowired
	TaskRepository taskRepository;

	@RequestMapping(value = { "/task", "list" })
	public String index(Model model) {
		List<TaskModel> tasks = taskRepository.findAll();
		model.addAttribute("tasks", tasks);
		model.addAttribute("type", "findAll");
		model.addAttribute("pageTitle", "Task List");

		return "taskIndex";
	}


	@RequestMapping("/fillTasks")
	@Transactional
	public String fillData(Model model) {

		// Creates always the same data
		DataFactory df = new DataFactory();

		for (int i = 0; i < 10; i++) {
			TaskModel tm = new TaskModel(df.getFirstName(), df.getFirstName(), df.chance(50), df.getBirthDate());
			taskRepository.save(tm);
		}

		return "forward:/task";
	}

	@RequestMapping("/deleteTask")
	public String deleteData(Model model, @RequestParam int id) {
		taskRepository.delete(id);

		return "forward:/task";
	}
	
	@RequestMapping(value = "/addTask", method = RequestMethod.GET)
	public String showAddTaskForm(Model model) {
		return "taskEdit";
	}
	
	@RequestMapping(value = "/addTask", method = RequestMethod.POST)
	public String addTask(@Valid @ModelAttribute TaskModel newTaskModel, BindingResult bindingResult,
			Model model) {
 
		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			// put the errors into the model
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/task";
		}
 
		TaskModel task = taskRepository.findTaskById(newTaskModel.getId());
 
		if (task != null) {
			model.addAttribute("errorMessage", "Task already exists!<br>");
		} else {
			taskRepository.save(newTaskModel);
			model.addAttribute("message", "New task " + newTaskModel.getId() + " added.");
		}
 
		return "forward:/task";
	}
	
	
	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {
		return "showError";
	}
}
