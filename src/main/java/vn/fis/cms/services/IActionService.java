package vn.fis.cms.services;

import java.util.List;

import vn.fis.cms.domain.Action;
import vn.fis.cms.model.ActionModel;

public interface IActionService {
	
	List<Action> GetListAction();	
	
	boolean AddAction(String link, boolean islock, Long parentId, List<Long> method, List<Long> permission);
	
	ActionModel getActionDetail(Long id);
	
	 boolean EditAction(ActionModel model);
}
