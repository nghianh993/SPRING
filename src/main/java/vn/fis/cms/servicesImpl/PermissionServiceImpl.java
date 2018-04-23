package vn.fis.cms.servicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.fis.cms.services.IGroupPermissionService;
import vn.fis.cms.services.IPermissionService;
import vn.fis.cms.domain.GroupPermission;
import vn.fis.cms.domain.Permission;
import vn.fis.cms.repositories.PermissionRepository;

@Service
public class PermissionServiceImpl implements IPermissionService{

	@Autowired
	PermissionRepository permissionRepository;
	
	@Autowired
	IGroupPermissionService groupPermissionService;
	
	@Override
	public Page<Permission> GetListPermission(int pageIndex, int pageSize) {
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		
		return permissionRepository.findAll(pageable);
	}

	@Override
	public boolean AddPermission(String code, String description, Boolean lock, Long groupId) {
		Optional<GroupPermission> groupPermission = groupPermissionService.findById(groupId); 
		if(groupPermission == null) {
			return false;
		}
		Permission permission = new Permission();
		permission.setCode(code);
		permission.setDescription(description);
		permission.setGroupPermission(groupPermission.get());
		permission.setIslock(lock);
		
		permissionRepository.save(permission);
		return true;
	}

	@Override
	public Optional<Permission> GetPermissionById(Long id) {
		return permissionRepository.findById(id);
	}

	@Override
	public void DeletePermission(Long id) {
		permissionRepository.deleteById(id);
	}

	@Override
	public boolean UnLockOrLockPermission(Long id, boolean islock) {
		Permission permission = permissionRepository.findById(id).get();
		if(permission == null)
			return false;
		permission.setIslock(islock);
		
		permissionRepository.save(permission);
		return true;
	}

	@Override
	public Set<Permission> findByInventoryIdIn(List<Long> ids) {
		return permissionRepository.findByInventoryIdIn(ids);
	}

}
