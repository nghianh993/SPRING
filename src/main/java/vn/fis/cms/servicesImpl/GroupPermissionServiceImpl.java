package vn.fis.cms.servicesImpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.fis.cms.domain.GroupPermission;
import vn.fis.cms.model.GroupPermissionModel;
import vn.fis.cms.repositories.GroupPermissionRepository;
import vn.fis.cms.services.IGroupPermissionService;

@Service
public class GroupPermissionServiceImpl implements IGroupPermissionService{

	
	@Autowired
	GroupPermissionRepository groupPermissionRepository;
	
	@Override
	public Map<Long, List<GroupPermissionModel>> GetListPermission() {
		Map<Long, List<GroupPermissionModel>> groupPermission = new HashMap<Long, List<GroupPermissionModel>>();
		List<GroupPermission> _lstgroup = groupPermissionRepository.findAll();
		
		if(_lstgroup != null && _lstgroup.size() > 0)
		{
			for (GroupPermission item : _lstgroup) {
				if( groupPermission.get(item.getParentid()) == null) {						
					groupPermission.put(item.getParentid(), new LinkedList<GroupPermissionModel>());
				}
				List<GroupPermissionModel> lstGroup = groupPermission.get(item.getParentid());
				GroupPermissionModel groupItem = new GroupPermissionModel();
				groupItem.setId(item.getId());
				groupItem.setName(item.getName());
				groupItem.setPermissions(item.getPermissions());
				lstGroup.add(groupItem);
			}
		}
		
		return groupPermission;
	}

	@Override
	public void AddNewGroup(String name, Long parentId) {
		GroupPermission group = new GroupPermission();
		group.setName(name);
		group.setParentid(parentId);
		
		groupPermissionRepository.save(group);
	}

	@Override
	public Optional<GroupPermission> findById(Long id) {
		return groupPermissionRepository.findById(id);
	}
	
	
}
