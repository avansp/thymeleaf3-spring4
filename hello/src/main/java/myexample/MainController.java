package myexample;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * This is where we define web path
 *
 */
@Controller
public class MainController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("recipient", "World");
		return "index.html";
	}
	
}
