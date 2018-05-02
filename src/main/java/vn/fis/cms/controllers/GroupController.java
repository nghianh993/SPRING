package vn.fis.cms.controllers;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vn.fis.cms.domain.Role;
import vn.fis.cms.domain.User;
import vn.fis.cms.model.AjaxResult;
import vn.fis.cms.model.AjaxResultModel;
import vn.fis.cms.model.GroupModel;
import vn.fis.cms.model.GroupPermissionModel;
import vn.fis.cms.services.IAccountService;
import vn.fis.cms.services.IGroupPermissionService;
import vn.fis.cms.services.IRoleService;

@Controller
@PropertySource(value= {"classpath:application.properties"})
public class GroupController {
	
	@Autowired
	IRoleService roleService;
	
	@Autowired
	IGroupPermissionService groupPermissionService;
	
	@Autowired
	IAccountService acountService;
	
	@Inject
	private Environment env;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value = {"/admin/group"}, method = RequestMethod.GET)
	public String Group(ModelMap model) {
		Map<Long, List<GroupPermissionModel>> lstDataPer = groupPermissionService.GetListPermission();
		List<User> lstUser = acountService.GetAllData();
		Page<Role> pgRole = roleService.GetListRole(0, Integer.parseInt(env.getProperty("webconfig.pageSize")));
		List<Role> lstRole = pgRole.getContent();
		model.addAttribute("lstRole", lstRole);
		model.addAttribute("lstUser", lstUser);
		model.addAttribute("lstDataPer", lstDataPer);
		model.addAttribute("total", pgRole.getTotalElements());
		model.addAttribute("totalPage", pgRole.getTotalPages());
		model.addAttribute("currentpage", 1);
		model.addAttribute("active", "group");
		return "group";
	}
	
	@RequestMapping(value = { "api/admin/group/loaddata" }, method = RequestMethod.POST)
	public ModelAndView GetListLocation(int pageIndex, int pageSize) {
		Page<Role> pgRole = roleService.GetListRole(pageIndex -1, pageSize);
		List<Role> lstRole = pgRole.getContent();
		ModelAndView mav = new ModelAndView("pagging_group");
	    mav.addObject("lstRole", lstRole);
	    mav.addObject("total", pgRole.getTotalElements());
	    mav.addObject("totalPage", pgRole.getTotalPages());
	    mav.addObject("currentpage", pageIndex);
        return mav;
	}
	
	@RequestMapping(value = {"api/admin/group/detail"}, method = RequestMethod.POST)
	public @ResponseBody AjaxResultModel<GroupModel> GetActionDetail(Long id) {
		AjaxResultModel<GroupModel> result = new AjaxResultModel<GroupModel>();
		try {
			GroupModel model = roleService.getGroupDetail(id);
			result.setResult(true);
			result.setMessage(messageSource.getMessage("S001", null, Locale.getDefault()));
			result.setResultData(model);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(false);
			result.setMessage(messageSource.getMessage("E002", null, Locale.getDefault()));
			return result;
		}
		return result;
	}
	
	@RequestMapping(value = {"api/admin/group/edit"}, method = RequestMethod.POST)
	public @ResponseBody AjaxResult EditGroup(@ModelAttribute GroupModel model) {
		AjaxResult result = new AjaxResult();
		try {
			boolean check = roleService.EditRole(model);
			if(check) {
				result.setCode(1);
				result.setResult(true);
			}else {
				result.setResult(false);
				result.setCode(0);
			}
			result.setMessage(messageSource.getMessage("S001", null, Locale.getDefault()));			
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(false);
			result.setMessage(messageSource.getMessage("E002", null, Locale.getDefault()));
			return result;
		}
		return result;
	}
	
	@RequestMapping(value = {"api/admin/group/add"}, method = RequestMethod.POST)
	public @ResponseBody AjaxResult AddGroup(@ModelAttribute GroupModel model) {
		AjaxResult result = new AjaxResult();
		try {
			boolean check = roleService.AddRole(model);
			if(check) {
				result.setCode(1);
				result.setResult(true);
			}else {
				result.setResult(false);
				result.setCode(0);
			}
			result.setMessage(messageSource.getMessage("S001", null, Locale.getDefault()));			
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(false);
			result.setMessage(messageSource.getMessage("E002", null, Locale.getDefault()));
			return result;
		}
		return result;
	}
	
	@RequestMapping(value = {"api/admin/group/delete"}, method = RequestMethod.POST)
	public @ResponseBody AjaxResult DeleteGroup(long id) {
		AjaxResult result = new AjaxResult();
		try {
			boolean check = roleService.DeleteGroup(id);
			result.setResult(check);
			result.setMessage(messageSource.getMessage("S001", null, Locale.getDefault()));			
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(false);
			result.setMessage(messageSource.getMessage("E002", null, Locale.getDefault()));
			return result;
		}
		return result;
	}
}
