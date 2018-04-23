package vn.fis.cms.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.fis.cms.domain.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long>{
	@Query( "select o from Permission o where o.id in :ids" )
	Set<Permission> findByInventoryIdIn(@Param("ids") List<Long> ids);
	
	@Query( "select o from Permission o order by o.code" )
	List<Permission> findAllOrderByCodeAsc();
}
