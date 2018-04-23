package vn.fis.cms.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import vn.fis.cms.domain.GroupPermission;
import vn.fis.cms.model.GroupPermissionModel;

public interface IGroupPermissionService {
	Map<Long, List<GroupPermissionModel>> GetListPermission();
	
	Optional<GroupPermission> findById(Long id);
	
	void AddNewGroup(String name, Long parentId);
}
