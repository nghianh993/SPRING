package vn.fis.cms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value = {"/admin/home"}, method = RequestMethod.GET)
	public String Home(ModelMap model) {
		//final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("active", "home");
		return "home";
	}
	
	@RequestMapping(value = {"/notfound"}, method = RequestMethod.GET)
	public String Notfound(ModelMap model) {
		return "notfound";
	}
}
