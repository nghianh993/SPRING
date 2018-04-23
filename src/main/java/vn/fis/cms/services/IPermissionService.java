package vn.fis.cms.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import vn.fis.cms.domain.Permission;
import vn.fis.cms.model.PermissionModel;

@Repository
public interface IPermissionService {

	Page<Permission> GetListPermission(int pageIndex, int pageSize);
	
	List<Permission> GetAllPermission();
	
	Optional<Permission> GetPermissionById(Long id);

	boolean AddPermission(PermissionModel model);
	
	void DeletePermission(Long id);
	
	boolean UnLockOrLockPermission(Long id, boolean islock);
	
	Set<Permission> findByInventoryIdIn(List<Long> ids);
	
	PermissionModel GetPermissionDetail(Long id);
	
	boolean EditPermission(PermissionModel model);
}
