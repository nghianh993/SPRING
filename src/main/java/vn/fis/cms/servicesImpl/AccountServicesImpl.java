package vn.fis.cms.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.fis.cms.domain.Permission;
import vn.fis.cms.domain.Role;
import vn.fis.cms.domain.User;
import vn.fis.cms.model.AccountModel;
import vn.fis.cms.repositories.AccountRepository;
import vn.fis.cms.services.IAccountService;

@Service
public class AccountServicesImpl implements IAccountService{
	
	@Autowired
	AccountRepository accountRepository;

	@Override
	public Page<AccountModel> GetListUser(int pageIndex, int pageSize) {
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		return accountRepository.findAllAccount(pageable);
	}

	@Override
	public List<String> GetListPermissonByUserEMail(String email) {
		User userItem = accountRepository.findByEmail(email);
		if(userItem == null) {
			return null;
		}
		List<Role> lstRole = new ArrayList<Role>(userItem.getRoles());
		List<String> lstPermission = new ArrayList<String>();
		for (Role role : lstRole) {
			if(role.getPermissions() != null && role.getPermissions().size() > 0) {
				for (Permission permission : role.getPermissions()) {
					lstPermission.add(permission.getCode());
				}
			}
		}
		return lstPermission;
	}

	@Override
	public List<User> GetAllData() {		
		return accountRepository.findAll();
	}

	@Override
	public Set<User> findByInventoryIdIn(List<Long> ids) {
		return accountRepository.findByInventoryIdIn(ids);
	}
	
}
