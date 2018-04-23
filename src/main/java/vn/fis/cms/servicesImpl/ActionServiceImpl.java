package vn.fis.cms.servicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.fis.cms.domain.Action;
import vn.fis.cms.domain.GroupAction;
import vn.fis.cms.domain.Method;
import vn.fis.cms.domain.Permission;
import vn.fis.cms.model.ActionModel;
import vn.fis.cms.repositories.ActionRepository;
import vn.fis.cms.services.IActionService;
import vn.fis.cms.services.IGroupActionService;
import vn.fis.cms.services.IMethodService;
import vn.fis.cms.services.IPermissionService;

@Service
public class ActionServiceImpl implements IActionService {

	@Autowired
	ActionRepository actionRepository;

	@Autowired
	IGroupActionService groupActionService;

	@Autowired
	IMethodService methodService;
	
	@Autowired
	IPermissionService permissionService;

	@Override
	public List<Action> GetListAction() {
		return actionRepository.findAll();
	}

	@Override
	public boolean AddAction(String link, boolean islock, Long parentId, List<Long> method, List<Long> permission) {
		try {
			Optional<GroupAction> group = groupActionService.findById(parentId);
			if (group.get() != null) {
				Action action = new Action();
				action.setLink(link);
				action.setIslock(islock);
				action.setGroupAction(group.get());
				actionRepository.save(action);
				action.setMethods(methodService.findByInventoryIdIn(method));
				action.setPermissions(permissionService.findByInventoryIdIn(permission));
				actionRepository.save(action);
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean EditAction(ActionModel model) {
		try {
			Optional<GroupAction> group = groupActionService.findById(model.getParentId());
			if (group.isPresent()) {
				Optional<Action> action = actionRepository.findById(model.getId());
				if(!action.isPresent())
					return false;
				action.get().setLink(model.getLink());
				action.get().setIslock(model.isIslock());
				action.get().setGroupAction(group.get());
				actionRepository.save(action.get());
				action.get().setMethods(methodService.findByInventoryIdIn(model.getMethods()));
				action.get().setPermissions(permissionService.findByInventoryIdIn(model.getPermission()));
				actionRepository.save(action.get());
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public ActionModel getActionDetail(Long id) {
		ActionModel actionModel = new ActionModel();
		Optional<Action> action = actionRepository.findById(id);
		if(!action.isPresent())		
			return null;
		actionModel.setLink(action.get().getLink());
		actionModel.setParentId(action.get().getGroupAction().getId());
		actionModel.setIslock(action.get().getIslock());
		actionModel.setMethods(action.get().getMethods().stream().map(Method::getId).collect(Collectors.toList()));
		actionModel.setPermission(action.get().getPermissions().stream().map(Permission::getId).collect(Collectors.toList()));
		
		return actionModel;
	}

}































