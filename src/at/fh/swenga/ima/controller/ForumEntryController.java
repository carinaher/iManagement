package at.fh.swenga.ima.controller;

import java.util.Date;
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

import at.fh.swenga.ima.dao.ForumEntryRepository;
import at.fh.swenga.ima.model.ForumEntryModel;
import at.fh.swenga.ima.model.TaskModel;


@Controller

public class ForumEntryController {

	@Autowired
	ForumEntryRepository forumEntryRepository;

	@RequestMapping(value = { "/forum", "list" })
	public String index(Model model) {
		List<ForumEntryModel> forumEntrys = forumEntryRepository.findAll();
		model.addAttribute("forumEntrys", forumEntrys);
		model.addAttribute("type", "findAll");
		model.addAttribute("pageTitle", "Forum ");

		return "forumIndex";
	}
	
	@RequestMapping("/fillForumEntrys")
	@Transactional
	public String fillData(Model model) {

		// Creates always the same data
		DataFactory df = new DataFactory();
		Date now = new Date();

		for (int i = 0; i < 10; i++) {
			ForumEntryModel fem = new ForumEntryModel(df.getFirstName(), df.getRandomText(10, 50));
			forumEntryRepository.save(fem);
		}

		return "forward:/forum";
	}
	
	@RequestMapping("/deleteForumEntry")
	public String deleteData(Model model, @RequestParam int id) {
		forumEntryRepository.delete(id);

		return "forward:/forum";
	}
	
	@RequestMapping(value = "/addForumEntry", method = RequestMethod.GET)
	public String showAddForumEntryForm(Model model) {
		return "forumEntryEdit";
	}
	
	
	@RequestMapping(value = "/addForumEntry", method = RequestMethod.POST)
	public String addForumEntry(@Valid @ModelAttribute ForumEntryModel newForumEntryModel, BindingResult bindingResult,
			Model model) {
 
		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			// put the errors into the model
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/forum";
		}
 
		ForumEntryModel entry = forumEntryRepository.findForumEntryById(newForumEntryModel.getId());
 
		if (entry != null) {
			model.addAttribute("errorMessage", "Entry already exists!<br>");
		} else {
			forumEntryRepository.save(newForumEntryModel);
			model.addAttribute("message", "New entry " + newForumEntryModel.getId() + " added.");
		}
 
		return "forward:/forum";
	}
	@RequestMapping(value = "/editForumEntry", method = RequestMethod.GET)
	public String showEditForumEntryForm(Model model, @RequestParam int id) {
		ForumEntryModel forumEntry = forumEntryRepository.findForumEntryById(id);
		if (forumEntry != null) {
			model.addAttribute("forumEntrys", forumEntry);
			return "forumEntryEdit";
		} else {
			model.addAttribute("errorMessage", "Couldn't find entry " + id);
			return "forward:/forum";
		}
	}
	
	@RequestMapping(value = "/editForumEntry", method = RequestMethod.POST)
	public String editForumEntry(@Valid @ModelAttribute ForumEntryModel editedForumEntryModel, BindingResult bindingResult,
			Model model) {
 
		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/forum";
		}
 
		ForumEntryModel forumEntry = forumEntryRepository.findForumEntryById(editedForumEntryModel.getId());
 
		if (forumEntry == null) {
			model.addAttribute("errorMessage", "Entry" + editedForumEntryModel.getId() + "does not exist!<br>");
		} else {
			//student.setId(editedTaskModel.getId());
			forumEntry.setId(editedForumEntryModel.getId());
			forumEntry.setTopic(editedForumEntryModel.getTopic());
			forumEntry.setText(editedForumEntryModel.getText());
			model.addAttribute("message", "Changed task " + editedForumEntryModel.getId());
			forumEntryRepository.save(forumEntry);
		}
 
		return "forward:/forum";
	}

	
	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {
		return "showError";
	}
	
	
}
