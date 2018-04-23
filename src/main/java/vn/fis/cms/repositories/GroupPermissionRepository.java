package vn.fis.cms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.fis.cms.domain.GroupPermission;

public interface GroupPermissionRepository extends JpaRepository<GroupPermission, Long>{
	
	public List<GroupPermission> findAll();
}
