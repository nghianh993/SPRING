package vn.fis.cms.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.fis.cms.domain.User;
import vn.fis.cms.model.AccountModel;

@Repository
public interface AccountRepository extends JpaRepository<User, Long>{
	
	public User findByEmail(String email);
	
	@Query("select new vn.fis.cms.model.AccountModel(u.id, u.address, "
			+ "u.datecreate, u.email, u.fullname, u.iplogin, u.islock, "
			+ "u.lockresion, u.password, u.phone) from User u")
	public Page<AccountModel> findAllAccount(Pageable pageable);
	
	
	@Query( "select o from User o where o.id in :ids" )
	Set<User> findByInventoryIdIn(@Param("ids") List<Long> ids);
}
