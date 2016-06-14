package at.fh.swenga.ima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StartpageController {


	@RequestMapping("/")
	public String index(Model model) {
		// TODO: add models that are displayed on start page (-> calendar/todoList entries of current week)
		return "index";
	}

}
