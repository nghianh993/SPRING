package vn.fis.cms.controllers;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.fis.cms.model.AjaxResult;
import vn.fis.cms.model.OrganizationModel;
import vn.fis.cms.services.IOrganizationService;

@Controller
@PropertySource(value= {"classpath:application.properties"})
public class OrganizationController {
	
	@Autowired
	IOrganizationService organizationService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value = {"/admin/organization"}, method = RequestMethod.GET)
	public String Home(ModelMap model) {
		model.addAttribute("active", "organization");
		List<OrganizationModel> lstData = organizationService.ListOrganization();
		model.addAttribute("lstData", lstData);
		return "organization";
	}
	
	@RequestMapping(value = {"api/admin/organization/loaddata"}, method = RequestMethod.POST)
	public @ResponseBody AjaxResult LoadData() {
		AjaxResult result = new AjaxResult();
		try {
			List<OrganizationModel> lstData = organizationService.ListOrganization();
			result.setResult(true);
			result.setResultData(lstData);
			result.setMessage(messageSource.getMessage("S001", null, Locale.getDefault()));
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(false);
			result.setMessage(messageSource.getMessage("E002", null, Locale.getDefault()));
			return result;
		}
		return result;
	}
	
	@RequestMapping(value = {"api/admin/organization/add"}, method = RequestMethod.POST)
	public @ResponseBody AjaxResult AddData(@ModelAttribute OrganizationModel model) {
		AjaxResult result = new AjaxResult();
		try {
			boolean check = organizationService.SaveData(model);
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
