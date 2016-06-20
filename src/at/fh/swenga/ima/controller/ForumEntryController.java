package at.fh.swenga.ima.controller;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartFile;

import at.fh.swenga.ima.dao.AttachmentRepository;
import at.fh.swenga.ima.dao.ForumEntryRepository;
import at.fh.swenga.ima.model.AttachmentModel;
import at.fh.swenga.ima.model.ForumEntryModel;
import at.fh.swenga.ima.model.TaskModel;

@Controller

public class ForumEntryController {

	@Autowired
	ForumEntryRepository forumEntryRepository;

	@Autowired
	AttachmentRepository attachmentRepository;

	@RequestMapping(value = { "/forum", "list" })
	public String index(Model model) {
		List<ForumEntryModel> forumEntrys = forumEntryRepository.findAll();
		model.addAttribute("forumEntrys", forumEntrys);
		model.addAttribute("type", "findAll");
		model.addAttribute("pageTitle", "Forum");

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
			model.addAttribute("message", "Added new entry " + newForumEntryModel.getTopic());
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
	public String editForumEntry(@Valid @ModelAttribute ForumEntryModel editedForumEntryModel,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/forum";
		}

		ForumEntryModel forumEntry = forumEntryRepository.findForumEntryByTopic(editedForumEntryModel.getTopic());

		if (forumEntry == null) {
			model.addAttribute("errorMessage", "Entry " + editedForumEntryModel.getTopic() + " does not exist!<br>");
		} else {
			forumEntry.setTopic(editedForumEntryModel.getTopic());
			forumEntry.setText(editedForumEntryModel.getText());
			model.addAttribute("message", "Changed task " + editedForumEntryModel.getTopic());
			forumEntryRepository.save(forumEntry);
		}

		return "forward:/forum";
	}

	// Upload

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String showUploadForm(Model model) {
		return "uploadAttachment";
	}


	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadDocument(Model model, @RequestParam("id") int entryId,
			@RequestParam("myFile") MultipartFile file) {
		
		try {

			ForumEntryModel forumEntry = forumEntryRepository.findForumEntryById(entryId);

			// Already a document available -> delete it
			if (forumEntry == null){
				model.addAttribute("errorMessage", "Entry " + forumEntryRepository.findForumEntryById(entryId) + " does not exist!<br>");
			}
			else if (forumEntry.getAttachment() != null) {
				attachmentRepository.delete(forumEntry.getAttachment());
				// Don't forget to remove the relationship too
				forumEntry.setAttachment(null);
			}

			// Create a new document and set all available infos

			AttachmentModel attachment = new AttachmentModel();
			attachment.setContent(file.getBytes());
			attachment.setContentType(file.getContentType());
			attachment.setCreated(new Date());
			attachment.setFilename(file.getOriginalFilename());
			attachment.setName(file.getName());
			forumEntry.setAttachment(attachment);
			forumEntryRepository.save(forumEntry);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error:" + e.getMessage());
		}

		return "forward:/editForumEntry";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {
		return "showError";
	}

}
