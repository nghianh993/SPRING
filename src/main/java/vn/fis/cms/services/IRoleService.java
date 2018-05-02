package vn.fis.cms.services;

import org.springframework.data.domain.Page;

import vn.fis.cms.domain.Role;
import vn.fis.cms.model.GroupModel;

public interface IRoleService {
	
	Page<Role> GetListRole(int pageIndex, int pageSize);
	
	GroupModel getGroupDetail(Long id);
	
	boolean EditRole(GroupModel model);
	
	boolean AddRole(GroupModel model);
	
	boolean DeleteGroup(Long id);
}
