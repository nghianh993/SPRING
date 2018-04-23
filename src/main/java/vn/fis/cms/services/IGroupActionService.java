package vn.fis.cms.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import vn.fis.cms.domain.GroupAction;
import vn.fis.cms.model.GroupActionModel;

public interface IGroupActionService {
	Map<Long, List<GroupActionModel>> GetListAction();

	Optional<GroupAction> findById(Long id);
	
	void AddNewGroup(String name, Long parentId);
}
