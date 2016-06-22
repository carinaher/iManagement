package at.fh.swenga.ima.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import at.fh.swenga.ima.dao.StudentRepository;
import at.fh.swenga.ima.dao.TaskRepository;
import at.fh.swenga.ima.model.StudentModel;
import at.fh.swenga.ima.model.TaskModel;

@Controller
public class TaskController {
	
	@Autowired
	StudentRepository studentRepository;

	@Autowired
	TaskRepository taskRepository;

	@RequestMapping(value = { "/calendarJson", "json" }, produces = "application/json")
	public @ResponseBody String getCalendarJson(HttpServletResponse response, @AuthenticationPrincipal UserDetails userDetails) {		
        // Convert model to JSON string
        response.setCharacterEncoding("UTF-8");        
        String json = new Gson().toJson(taskRepository.findByUserName(userDetails.getUsername()));
        return json;
	}

	@RequestMapping(value = { "/calendar", "calendar" })
	public String showCalendar(Model model) {
		model.addAttribute("pageTitle", "Calendar View");
		
		setUserPanel(model);
        return "calendarIndex";
	}
	
	@RequestMapping(value = { "/task", "list" })
	public String index(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		List<TaskModel> tasks = taskRepository.findByUserName(userDetails.getUsername());
		model.addAttribute("tasks", tasks);
		model.addAttribute("type", "findAll");
		model.addAttribute("pageTitle", "ToDo List");

		setUserPanel(model);
		return "taskIndex";
	}
	
	@RequestMapping(value = { "/findTask" })
	public String find(Model model, @RequestParam String searchString, @ModelAttribute("type") String type) {
		List<TaskModel> tasks = new ArrayList<>();
		
		tasks = taskRepository.findByUserNameContainsOrTitleContainsOrDescriptionContainsOrStatusContainsAllIgnoreCase(searchString, searchString, searchString,Boolean.parseBoolean(searchString));

		model.addAttribute("tasks", tasks);
		
		setUserPanel(model);
		return "taskIndex";
	}

	@RequestMapping("/fillTasks")
	@Transactional
	public String fillData(Model model, @AuthenticationPrincipal UserDetails userDetails,
			@RequestParam(value = "returnUrl", required = false) String returnUrl) {

		DataFactory df = new DataFactory();
		Date now = new Date();
		Date startDate = df.getDateBetween(now, df.getDate(2016, 6, 30));
		LocalDateTime endLocalDateTime = LocalDateTime.from(startDate.toInstant().atZone(ZoneId.of("UTC"))).plusDays(3); // can only increment a LocalDateTime
		Date endDate = Date.from(endLocalDateTime.toInstant(ZoneOffset.UTC));
		
		List<TaskModel> tasks = new ArrayList<>();
		tasks.add(new TaskModel("SWENGA", "Software Engineering Advanced", true, startDate, df.getDateBetween(startDate,endDate ), "FH-Joanneum Graz", userDetails.getUsername()));
		tasks.add(new TaskModel("HVSYS", "Heterogene vernetzte Systeme", true, startDate, df.getDateBetween(startDate,endDate ),"FH-Joanneum Graz", userDetails.getUsername()));
		tasks.add(new TaskModel("DMT3", "Digitale Medien Technologien 3", false, startDate, df.getDateBetween(startDate,endDate ),"FH-Joanneum Kapfenberg", userDetails.getUsername()));
		tasks.add(new TaskModel("GPMGT", "Geschäftsprozessmanagement", true, startDate, df.getDateBetween(startDate,endDate ),"FH-Joanneum Graz", userDetails.getUsername()));
		tasks.add(new TaskModel("TEAMT", "Teamtraining", false, startDate, df.getDateBetween(startDate,endDate ),"FH-Joanneum Bad Gleichenberg", userDetails.getUsername()));
				
		

		List<TaskModel> savedTaskModels = taskRepository.save(tasks);
		savedTaskModels.stream().forEach(t -> t.setUrl("#")); // setter automatically generates a url (with id) to edit this task
		
		taskRepository.save(savedTaskModels);
		model.addAttribute("message", "Created example tasks");
		
		setUserPanel(model);
		return createReturnViewString(returnUrl);
	}
	
	@RequestMapping("/deleteTask")
	public String deleteData(Model model, @RequestParam int id, 
			@RequestParam(value = "returnUrl", required = false) String returnUrl,
			@AuthenticationPrincipal UserDetails userDetails) {
		
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		if (!taskRepository.findTaskById(id).getUserName().equals(userDetails.getUsername())) {
			{
				model.addAttribute("errorMessage", "Not authorized to delete this Entry");
				return createReturnViewString(returnUrl);
			}
		} else {
			taskRepository.delete(id);

		}


		setUserPanel(model);
		return createReturnViewString(returnUrl);
	}

	@RequestMapping(value = "/addTask", method = RequestMethod.GET)
	public String showAddTaskkForm(Model model, @AuthenticationPrincipal UserDetails userDetails,
			@RequestParam(value = "returnUrl", required = false) String returnUrl) {
		model.addAttribute("pageTitle", "Add Task");
		model.addAttribute("userDetails", userDetails);
		model.addAttribute("returnUrl", returnUrl);
		setUserPanel(model);
		return "taskEdit";
	}
	
	@RequestMapping(value = "/addTask", method = RequestMethod.POST)
	public String addTask(@Valid @ModelAttribute TaskModel newTaskModel, BindingResult bindingResult,
			Model model, @AuthenticationPrincipal UserDetails userDetails,
			@RequestParam(value = "returnUrl", required = false) String returnUrl,
			@RequestParam(required = false) String statusCheckbox) {
 
		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			// put the errors into the model
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/calendar";
		}
 
		TaskModel task = taskRepository.findTaskById(newTaskModel.getId());
		
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		if (task != null) {
			model.addAttribute("errorMessage", "Task already exists!<br>");
		} else if (!authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")) && !newTaskModel.getUserName().equals(userDetails.getUsername())) {
			// not an admin and not the current user
			model.addAttribute("errorMessage", "Not authorized to add task for " + newTaskModel.getUserName());
		} else {
			if (statusCheckbox == null) newTaskModel.setStatus(false);
			else newTaskModel.setStatus(true);
			TaskModel savedTaskModel = taskRepository.save(newTaskModel);
			savedTaskModel.setUrl("#"); // setter automatically generates a url (with id) to edit this task
			taskRepository.save(savedTaskModel);
			model.addAttribute("message", "Added new task" + newTaskModel.getTitle());
		}

		setUserPanel(model);
		return createReturnViewString(returnUrl);
	}
	

	@RequestMapping(value = "/editTask", method = RequestMethod.GET)
	public String editTask(Model model, @RequestParam int id, @AuthenticationPrincipal UserDetails userDetails,
			@RequestParam(value = "returnUrl", required = false) String returnUrl) {
		TaskModel task = taskRepository.findTaskById(id);

		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		
		if (task == null) {
			model.addAttribute("errorMessage", "Couldn't find task with id " + id);
			return "forward:/calendar";
		} else if (!authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")) && !task.getUserName().equals(userDetails.getUsername())) {
			// not an admin and not the current user
			model.addAttribute("errorMessage", "Not authorized view tasks of " + task.getUserName());
			return createReturnViewString(returnUrl);
		} else {
			model.addAttribute("task", task);
			model.addAttribute("pageTitle", "Edit Task");
			model.addAttribute("returnUrl", returnUrl);

			setUserPanel(model);
			return "taskEdit";
		}
	}

	
	@RequestMapping(value = "/editTask", method = RequestMethod.POST)
	public String editStudent(@Valid @ModelAttribute TaskModel editedTaskModel, BindingResult bindingResult,
			Model model, @AuthenticationPrincipal UserDetails userDetails,
			@RequestParam(value = "returnUrl", required = false) String returnUrl,
			@RequestParam(required = false) String statusCheckbox) {
 
		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			setUserPanel(model);
			return "forward:/calendar";
		}
 
		TaskModel task = taskRepository.findTaskById(editedTaskModel.getId());
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		
		if (task == null) {
			model.addAttribute("errorMessage", "Task" + editedTaskModel.getId() + "does not exist!<br>");
		} else if (!authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")) && !task.getUserName().equals(userDetails.getUsername())) {
			// not an admin and not the current user
			model.addAttribute("errorMessage", "Not authorized edit tasks of " + task.getUserName());
		}  else if (!authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")) && !editedTaskModel.getUserName().equals(userDetails.getUsername())) {
			// changed owner to different user
			model.addAttribute("errorMessage", "Not authorized to change owner to " + editedTaskModel.getUserName());
		} else {

			task.setTitle(editedTaskModel.getTitle());
			task.setDescription(editedTaskModel.getDescription());
			if (statusCheckbox == null) task.setStatus(false);
			else task.setStatus(true);
			task.setStart(editedTaskModel.getStart());
			task.setEnd(editedTaskModel.getEnd());
			task.setPlace(editedTaskModel.getPlace());
			task.setUserName(editedTaskModel.getUserName());
			task.setUrl("#"); // setter automatically generates a url to edit this task
			
			model.addAttribute("message", "Changed task " + editedTaskModel.getTitle());
			taskRepository.save(task);
		}

		return createReturnViewString(returnUrl);
	}
		
	String createReturnViewString(String returnUrl) {
		if (returnUrl != null && returnUrl.equals("task")) {
			return "forward:/task";	
		} else {
			return "forward:/calendar";	
		}
	}
	
	void setUserPanel(Model model) {
		
		StudentModel student = studentRepository.findFirstByUserName(getUser(model));
		if (student != null) {
			model.addAttribute("student", student);
		}
		model.addAttribute("setSearch", "findTask");
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
