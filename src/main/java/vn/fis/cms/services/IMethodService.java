package vn.fis.cms.services;

import java.util.List;
import java.util.Set;

import vn.fis.cms.domain.Method;

public interface IMethodService {
	
	List<Method> findAll();
	
	Set<Method> findByInventoryIdIn(List<Long> ids);
}
