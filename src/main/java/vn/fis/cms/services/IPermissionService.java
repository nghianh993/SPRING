package vn.fis.cms.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import vn.fis.cms.domain.Permission;

@Repository
public interface IPermissionService {

	Page<Permission> GetListPermission(int pageIndex, int pageSize);
	
	Optional<Permission> GetPermissionById(Long id);

	boolean AddPermission(String code, String description, Boolean lock, Long groupId);
	
	void DeletePermission(Long id);
	
	boolean UnLockOrLockPermission(Long id, boolean islock);
	
	Set<Permission> findByInventoryIdIn(List<Long> ids);
}
