package vn.fis.cms.servicesImpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.fis.cms.domain.Method;
import vn.fis.cms.repositories.MethodRepository;
import vn.fis.cms.services.IMethodService;

@Service
public class MethodServiceImpl implements IMethodService{

	@Autowired
	MethodRepository methodRepository;
	
	@Override
	public List<Method> findAll() {
		return methodRepository.findAll();
	}

	@Override
	public Set<Method> findByInventoryIdIn(List<Long> ids) {
		return methodRepository.findByInventoryIdIn(ids);
	}

}
