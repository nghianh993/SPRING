package vn.fis.cms.servicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.fis.cms.services.IGroupPermissionService;
import vn.fis.cms.services.IMethodService;
import vn.fis.cms.services.IPermissionService;
import vn.fis.cms.domain.GroupPermission;
import vn.fis.cms.domain.Method;
import vn.fis.cms.domain.Permission;
import vn.fis.cms.model.PermissionModel;
import vn.fis.cms.repositories.PermissionRepository;

@Service
public class PermissionServiceImpl implements IPermissionService{

	@Autowired
	PermissionRepository permissionRepository;
	
	@Autowired
	IGroupPermissionService groupPermissionService;
	
	@Autowired
	IMethodService methodService;
	
	@Override
	public Page<Permission> GetListPermission(int pageIndex, int pageSize) {
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		
		return permissionRepository.findAll(pageable);
	}

	@Override
	public boolean AddPermission(PermissionModel model) {
		Optional<GroupPermission> groupPermission = groupPermissionService.findById(model.getParentId()); 
		if(!groupPermission.isPresent()) {
			return false;
		}
		Permission permission = new Permission();
		permission.setCode(model.getCode());
		permission.setDescription(model.getDescription());
		permission.setGroupPermission(groupPermission.get());
		permission.setIslock(model.isIslock());
		permission.setLink(model.getLink());
		permissionRepository.save(permission);
		
		permission.setMethod(methodService.findByInventoryIdIn(model.getMethods()));
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

	@Override
	public List<Permission> GetAllPermission() {		
		return permissionRepository.findAllOrderByCodeAsc();
	}

	@Override
	public PermissionModel GetPermissionDetail(Long id) {
		PermissionModel model = new PermissionModel();
		Optional<Permission> permission = permissionRepository.findById(id);
		if(!permission.isPresent())		
			return null;
		model.setDescription(permission.get().getDescription());
		model.setLink(permission.get().getLink());
		model.setParentId(permission.get().getGroupPermission().getId());
		model.setIslock(permission.get().getIslock());
		model.setMethods(permission.get().getMethod().stream().map(Method::getId).collect(Collectors.toList()));
		model.setCode(permission.get().getCode());
		
		return model;
	}

	@Override
	public boolean EditPermission(PermissionModel model) {
		try {
			Optional<GroupPermission> group = groupPermissionService.findById(model.getParentId());
			if (group.isPresent()) {
				Optional<Permission> permissionIt = permissionRepository.findById(model.getId());
				if(!permissionIt.isPresent())
					return false;
				Permission permission = permissionIt.get();
				permission.setCode(model.getCode());
				permission.setDescription(model.getDescription());
				permission.setGroupPermission(group.get());
				permission.setIslock(model.isIslock());
				permission.setLink(model.getLink());
				permissionRepository.save(permission);
				
				permission.setMethod(methodService.findByInventoryIdIn(model.getMethods()));
				permissionRepository.save(permission);
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

}
