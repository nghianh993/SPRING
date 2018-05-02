package vn.fis.cms.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.fis.cms.domain.Organization;
import vn.fis.cms.model.OrganizationModel;
import vn.fis.cms.repositories.OrganizationRepository;
import vn.fis.cms.services.IOrganizationService;

@Service
public class OrganizationServiceImpl implements IOrganizationService{

	@Autowired
	OrganizationRepository organizationRepository;
	
	@Override
	public List<OrganizationModel> ListOrganization() {
		return FindByParentId(new Long(0));
	}

	@Override
	public List<OrganizationModel> FindByParentId(Long parentId) {
		List<Organization> lstOrganization = organizationRepository.findByParentid(parentId);
		List<OrganizationModel> lstData = new ArrayList<OrganizationModel>();
		for (Organization organization : lstOrganization) {
			OrganizationModel model = new OrganizationModel();
			model.setId(organization.getId());
			model.setDescription(organization.getName());
			model.setLever(organization.getLever());
			model.setChildren(FindByParentId(model.getId()));
			lstData.add(model);
		}
		return lstData;
	}

	@Override
	public boolean SaveData(OrganizationModel model) {
		Optional<Organization> parent = organizationRepository.findById(model.getParentId());
		if(!parent.isPresent())
			return false;
		
		Organization item = new Organization();
		item.setName(model.getDescription());
		item.setLever(parent.get().getLever() + 1);
		item.setParentid(model.getParentId());
		organizationRepository.save(item);
		return true;
	}

}
