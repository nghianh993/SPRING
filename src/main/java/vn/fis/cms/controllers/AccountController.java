package vn.fis.cms.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vn.fis.cms.services.IAccountService;
import vn.fis.cms.model.AccountModel;

@Controller
@PropertySource(value= {"classpath:application.properties"})
public class AccountController {

	@Autowired
	IAccountService accountService;
	
	@Inject
	private Environment env;
	
	@RequestMapping(value = {"/admin/account"}, method = RequestMethod.GET)
	public String Account(ModelMap model) {
		Page<AccountModel> pgUser = accountService.GetListUser(0, Integer.parseInt(env.getProperty("webconfig.pageSize")));
		List<AccountModel> lstUser = pgUser.getContent();
		model.addAttribute("lstUser", lstUser);
		model.addAttribute("total", pgUser.getTotalElements());
		model.addAttribute("totalPage", pgUser.getTotalPages());
		model.addAttribute("currentpage", 1);
		model.addAttribute("active", "user");
		return "account";
	}
	
	@RequestMapping(value = { "api/admin/account/loaddata" }, method = RequestMethod.POST)
	public ModelAndView GetListLocation(int pageIndex, int pageSize) {
		Page<AccountModel> pgUser = accountService.GetListUser(pageIndex -1, pageSize);
		List<AccountModel> lstUser = pgUser.getContent();
		ModelAndView mav = new ModelAndView("pagging_account");
	    mav.addObject("lstUser", lstUser);
	    mav.addObject("total", pgUser.getTotalElements());
	    mav.addObject("totalPage", pgUser.getTotalPages());
	    mav.addObject("currentpage", pageIndex);
        return mav;
	}
	
	@RequestMapping(value = "/account/login", method = RequestMethod.GET)
	public ModelAndView login(
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Email hoặc mật khẩu không đúng ");
		}
		if (logout != null) {
			model.addObject("msg", "Bạn đã đăng xuất thành công");
		}
		model.setViewName("login");
		return model;
	}
	
	@RequestMapping(value="/account/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/account/login?logout";
	}
}
