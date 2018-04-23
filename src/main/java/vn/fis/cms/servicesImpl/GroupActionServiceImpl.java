package vn.fis.cms.servicesImpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.fis.cms.domain.GroupAction;
import vn.fis.cms.model.GroupActionModel;
import vn.fis.cms.repositories.GroupActionRepository;
import vn.fis.cms.services.IGroupActionService;

@Service
public class GroupActionServiceImpl implements IGroupActionService{

	@Autowired
	GroupActionRepository groupActionRepository;
	
	@Override
	public Map<Long, List<GroupActionModel>> GetListAction() {
		Map<Long, List<GroupActionModel>> groupAction = new HashMap<Long, List<GroupActionModel>>();
		List<GroupAction> _lstgroup = groupActionRepository.findAll();
		
		if(_lstgroup != null && _lstgroup.size() > 0)
		{
			for (GroupAction item : _lstgroup) {
				if( groupAction.get(item.getParentid()) == null) {						
					groupAction.put(item.getParentid(), new LinkedList<GroupActionModel>());
				}
				List<GroupActionModel> lstGroup = groupAction.get(item.getParentid());
				GroupActionModel groupItem = new GroupActionModel();
				groupItem.setId(item.getId());
				groupItem.setName(item.getName());
				groupItem.setAction(item.getActions());
				lstGroup.add(groupItem);
			}
		}
		
		return groupAction;
	}

	@Override
	public void AddNewGroup(String name, Long parentId) {
		GroupAction group = new GroupAction();
		group.setName(name);
		group.setParentid(parentId);
		groupActionRepository.save(group);
	}

	@Override
	public Optional<GroupAction> findById(Long id) {
		return groupActionRepository.findById(id);
	}

}
