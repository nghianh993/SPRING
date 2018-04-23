package vn.fis.cms.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import vn.fis.cms.domain.User;
import vn.fis.cms.model.AccountModel;

@Service
public interface IAccountService {
	
	Page<AccountModel> GetListUser(int pageIndex, int pageSize);
	
	List<User> GetAllData();
	
	List<String> GetListPermissonByUserEMail(String email);
	
	Set<User> findByInventoryIdIn(List<Long> ids);
}
