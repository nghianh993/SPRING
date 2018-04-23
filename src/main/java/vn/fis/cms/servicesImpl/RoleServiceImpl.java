package vn.fis.cms.servicesImpl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.fis.cms.domain.Permission;
import vn.fis.cms.domain.Role;
import vn.fis.cms.domain.User;
import vn.fis.cms.model.GroupModel;
import vn.fis.cms.repositories.RoleRepository;
import vn.fis.cms.services.IAccountService;
import vn.fis.cms.services.IPermissionService;
import vn.fis.cms.services.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	IAccountService accountService;
	
	@Autowired
	IPermissionService permissionService;
	
	@Override
	public Page<Role> GetListRole(int pageIndex, int pageSize) {
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		return roleRepository.findAll(pageable);
	}

	@Override
	public GroupModel getGroupDetail(Long id) {
		GroupModel groupModel = new GroupModel();
		Optional<Role> role = roleRepository.findById(id);
		if(!role.isPresent())		
			return null;
		Role data = role.get();
		groupModel.setName(data.getRolename());
		groupModel.setLstuser(data.getUsers().stream().map(User::getId).collect(Collectors.toList()));
		groupModel.setLstPermision(data.getPermissions().stream().map(Permission::getId).collect(Collectors.toList()));
		
		return groupModel;
	}

	@Override
	public boolean EditRole(GroupModel model) {
		try {
				Optional<Role> role = roleRepository.findById(model.getId());
				if(!role.isPresent())
					return false;
				Role data = role.get();
				data.setRolename(model.getName());
				roleRepository.save(data);
				data.setUsers(accountService.findByInventoryIdIn(model.getLstuser()));
				data.setPermissions(permissionService.findByInventoryIdIn(model.getLstPermision()));
				roleRepository.save(data);
				return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
