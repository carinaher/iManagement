package at.fh.swenga.ima.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import at.fh.swenga.ima.dao.EventRepository;
import at.fh.swenga.ima.model.EventModel;

@Controller
public class EventController {
	@Autowired
	EventRepository eventRepository;

	@RequestMapping(value = { "/calendarJson", "json" }, produces = "application/json")
	public @ResponseBody String getCalendarJson(HttpServletResponse response, @AuthenticationPrincipal UserDetails userDetails) {		
        // Convert model to JSON string
        response.setCharacterEncoding("UTF-8");        
        String json = new Gson().toJson(eventRepository.findByUserName(userDetails.getUsername()));
        return json;
	}
	
	


	@RequestMapping("/fillEvents")
	@Transactional
	public String fillData(Model model, @AuthenticationPrincipal UserDetails userDetails) {

        
        List<EventModel> events = new ArrayList<EventModel>();
        events.add(new EventModel(1, "Event test", new Date(),new Date(), "blabla", "Graz", userDetails.getUsername()));
        events.add(new EventModel(2, "Hello world!", new Date(),new Date(), "blabla", "Graz", userDetails.getUsername()));
        events.add(new EventModel(3, "som324ething", new Date(),new Date(), "blabla", "Graz", userDetails.getUsername()));
        
        eventRepository.save(events);

		return "forward:/calendar";
	}


	@RequestMapping(value = { "/calendar", "list" })
	public String showCalendar(Model model) {
		model.addAttribute("pageTitle", "Calendar");
        return "calendarIndex";
	}
	

	@RequestMapping(value = "/addEvent", method = RequestMethod.GET)
	public String showAddEventkForm(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		model.addAttribute("pageTitle", "Add Event");
		model.addAttribute("userDetails", userDetails);
		return "eventEdit";
	}
	
	@RequestMapping(value = "/addEvent", method = RequestMethod.POST)
	public String addEvent(@Valid @ModelAttribute EventModel newEventModel, BindingResult bindingResult,
			Model model, @AuthenticationPrincipal UserDetails userDetails) {
 
		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			// put the errors into the model
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/calendar";
		}
 
		EventModel event = eventRepository.findEventById(newEventModel.getId());
		
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		if (event != null) {
			model.addAttribute("errorMessage", "Event already exists!<br>");
		} else if (!authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")) && !newEventModel.getUserName().equals(userDetails.getUsername())) {
			// not an admin and not the current user
			model.addAttribute("errorMessage", "Not authorized to add event for " + newEventModel.getUserName());
		} else {
			eventRepository.save(newEventModel);
			model.addAttribute("message", "New event " + newEventModel.getTitle() + " added.");
		}
 
		return "forward:/calendar";
	}
	

	@RequestMapping(value = { "/editEvent", "edit" })
	public String editEvent(Model model, @RequestParam int id, @AuthenticationPrincipal UserDetails userDetails) {
		EventModel event = eventRepository.findEventById(id);

		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		
		if (event == null) {
			model.addAttribute("errorMessage", "Couldn't find event with id " + id);
			return "forward:/calendar";
		} else if (!authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")) && !event.getUserName().equals(userDetails.getUsername())) {
			// not an admin and not the current user
			model.addAttribute("errorMessage", "Not authorized view events of " + event.getUserName());
			return "forward:/calendar";
		} else {
			model.addAttribute("event", event);
			model.addAttribute("pageTitle", "Edit Event");
			return "eventEdit";
		}
	}

	
	@RequestMapping(value = "/editEvent", method = RequestMethod.POST)
	public String editStudent(@Valid @ModelAttribute EventModel editedEventModel, BindingResult bindingResult,
			Model model, @AuthenticationPrincipal UserDetails userDetails) {
 
		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/calendar";
		}
 
		EventModel event = eventRepository.findEventById(editedEventModel.getId());
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		
		if (event == null) {
			model.addAttribute("errorMessage", "Event" + editedEventModel.getId() + "does not exist!<br>");
		} else if (!authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")) && !event.getUserName().equals(userDetails.getUsername())) {
			// not an admin and not the current user
			model.addAttribute("errorMessage", "Not authorized edit events of " + event.getUserName());
		} else {

			event.setTitle(editedEventModel.getTitle());
			event.setStart(editedEventModel.getStart());
			event.setEnd(editedEventModel.getEnd());
			event.setDescription(editedEventModel.getDescription());
			event.setPlace(editedEventModel.getPlace());
			event.setUrl("#"); // setter automatically generates a url to edit this event
			
			model.addAttribute("message", "Changed event " + editedEventModel.getId());
			eventRepository.save(event);
		}
 
		return "forward:/calendar";
	}
	

}
