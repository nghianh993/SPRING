package vn.fis.cms.controllers;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vn.fis.cms.domain.Method;
import vn.fis.cms.model.ActionModel;
import vn.fis.cms.model.AjaxResult;
import vn.fis.cms.model.AjaxResultModel;
import vn.fis.cms.model.GroupActionModel;
import vn.fis.cms.model.GroupPermissionModel;
import vn.fis.cms.services.IActionService;
import vn.fis.cms.services.IGroupActionService;
import vn.fis.cms.services.IGroupPermissionService;
import vn.fis.cms.services.IMethodService;

@Controller
public class ActionController {
	
	@Autowired
	IGroupActionService groupActionService;
	
	@Autowired
	IGroupPermissionService groupPermissionService;
	
	@Autowired
	IActionService actionService;
	
	@Autowired
	IMethodService methodService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value = {"/admin/action"}, method = RequestMethod.GET)
	public String Account(ModelMap model) {
		Map<Long, List<GroupActionModel>> lstData = groupActionService.GetListAction();
		Map<Long, List<GroupPermissionModel>> lstDataPer = groupPermissionService.GetListPermission();
		List<Method> lstmethod = methodService.findAll();
		model.addAttribute("lstData", lstData);
		model.addAttribute("lstDataPer", lstDataPer);
		model.addAttribute("lstmethod", lstmethod);
		model.addAttribute("active", "action");
		return "action";
	}
	
	@RequestMapping(value = { "api/admin/action/loadgroup" }, method = RequestMethod.POST)
	public ModelAndView GetListLocation() {
		Map<Long, List<GroupActionModel>> lstData = groupActionService.GetListAction();
		ModelAndView mav = new ModelAndView("group_action");
		mav.addObject("lstData", lstData);
        return mav;
	}
	
	@RequestMapping(value = {"api/admin/action/addgroup"}, method = RequestMethod.POST)
	public @ResponseBody AjaxResult AddGroup(String name, Long parentId) {
		AjaxResult result = new AjaxResult();
		try {
			groupActionService.AddNewGroup(name, parentId);
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
	
	@RequestMapping(value = {"api/admin/action/add"}, method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody AjaxResult AddAction(@ModelAttribute ActionModel model) {
		AjaxResult result = new AjaxResult();
		try {
			boolean check = actionService.AddAction(model.getLink(), model.isIslock(), model.getParentId(), model.getMethods(), model.getPermission());
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
	
	@RequestMapping(value = {"api/admin/action/detail"}, method = RequestMethod.POST)
	public @ResponseBody AjaxResultModel<ActionModel> GetActionDetail(Long id) {
		AjaxResultModel<ActionModel> result = new AjaxResultModel<ActionModel>();
		try {
			ActionModel model = actionService.getActionDetail(id);
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
	
	@RequestMapping(value = {"api/admin/action/edit"}, method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody AjaxResult EditAction(@ModelAttribute ActionModel model) {
		AjaxResult result = new AjaxResult();
		try {
			boolean check = actionService.EditAction(model);
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
}
