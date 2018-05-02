package vn.fis.cms.services;

import java.util.List;

import vn.fis.cms.model.OrganizationModel;

public interface IOrganizationService {
	
	List<OrganizationModel> ListOrganization();
	
	List<OrganizationModel> FindByParentId(Long parentId);
	
	boolean SaveData(OrganizationModel model);
}
