package vn.fis.cms.controllers;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vn.fis.cms.model.GroupPermissionModel;
import vn.fis.cms.services.IGroupPermissionService;
import vn.fis.cms.services.IPermissionService;
import vn.fis.cms.domain.Permission;
import vn.fis.cms.model.AjaxResult;

@Controller
@PropertySource(value= {"classpath:application.properties"})
public class PermissionController {
	
	@Autowired
	IGroupPermissionService groupPermissionService;
	
	@Autowired
	IPermissionService permissionService;
	
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = {"/admin/permission"}, method = RequestMethod.GET)
	public String Account(ModelMap model) {
		Map<Long, List<GroupPermissionModel>> lstData = groupPermissionService.GetListPermission();
		
		model.addAttribute("lstData", lstData);
		model.addAttribute("active", "permission");
		return "permission";
	}
	
	@RequestMapping(value = { "api/admin/permission/loadgroup" }, method = RequestMethod.POST)
	public ModelAndView GetListLocation() {
		Map<Long, List<GroupPermissionModel>> lstData = groupPermissionService.GetListPermission();
		ModelAndView mav = new ModelAndView("group_permission");
		mav.addObject("lstData", lstData);
        return mav;
	}
	
	@RequestMapping(value = {"api/admin/permission/addgroup"}, method = RequestMethod.POST)
	public @ResponseBody AjaxResult AddGroup(String name, Long parentId) {
		AjaxResult result = new AjaxResult();
		try {
			groupPermissionService.AddNewGroup(name, parentId);
			result.setResult(true);
			result.setMessage(messageSource.getMessage("S001", null, Locale.getDefault()));
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(false);
			result.setMessage(messageSource.getMessage("E002", null, Locale.getDefault()));
			return result;
		}
		return result;
	}
	
	@RequestMapping(value = {"api/admin/permission/add"}, method = RequestMethod.POST)
	public @ResponseBody AjaxResult AddPermission(String code, String description, boolean islock, long parentId) {
		AjaxResult result = new AjaxResult();
		try {
			boolean check = permissionService.AddPermission(code, description, islock, parentId);
			if(check) {
				result.setCode(1);
				
			}else {
				result.setCode(0);
			}
			result.setResult(true);
			result.setMessage(messageSource.getMessage("S001", null, Locale.getDefault()));			
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(false);
			result.setMessage(messageSource.getMessage("E002", null, Locale.getDefault()));
			return result;
		}
		return result;
	}
	
	@RequestMapping(value = {"api/admin/getpermission"}, method = RequestMethod.POST)
	public @ResponseBody AjaxResult GetPermissionById(long id) {
		AjaxResult result = new AjaxResult();
		try {
			Optional<Permission> permission = permissionService.GetPermissionById(id);
			if(permission != null) {
				result.setCode(1);
			}else {
				result.setCode(0);
			}
			result.setResult(true);
			result.setMessage(messageSource.getMessage("S001", null, Locale.getDefault()));			
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(false);
			result.setMessage(messageSource.getMessage("E002", null, Locale.getDefault()));
			return result;
		}
		return result;
	}
	
	@RequestMapping(value = {"api/admin/permission/delete"}, method = RequestMethod.POST)
	public @ResponseBody AjaxResult DeletePermission(long id) {
		AjaxResult result = new AjaxResult();
		try {
			permissionService.DeletePermission(id);
			result.setResult(true);
			result.setMessage(messageSource.getMessage("S001", null, Locale.getDefault()));			
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(false);
			result.setMessage(messageSource.getMessage("E002", null, Locale.getDefault()));
			return result;
		}
		return result;
	}
	
	@RequestMapping(value = {"api/admin/permission/lock"}, method = RequestMethod.POST)
	public @ResponseBody AjaxResult LockPermission(long id) {
		AjaxResult result = new AjaxResult();
		try {
			if(permissionService.UnLockOrLockPermission(id, true)) {
				result.setCode(1);
			}else {
				result.setCode(0);
			}
			result.setResult(true);
			result.setMessage(messageSource.getMessage("S001", null, Locale.getDefault()));			
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(false);
			result.setMessage(messageSource.getMessage("E002", null, Locale.getDefault()));
			return result;
		}
		return result;
	}
	
	@RequestMapping(value = {"api/admin/permission/unlock"}, method = RequestMethod.POST)
	public @ResponseBody AjaxResult UnLockPermission(long id) {
		AjaxResult result = new AjaxResult();
		try {
			if(permissionService.UnLockOrLockPermission(id, false)) {
				result.setCode(1);
			}else {
				result.setCode(0);
			}
			result.setResult(true);
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
