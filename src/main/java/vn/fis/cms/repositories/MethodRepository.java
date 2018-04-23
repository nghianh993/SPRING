package vn.fis.cms.repositories;


import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.fis.cms.domain.Method;

@Repository
public interface MethodRepository extends JpaRepository<Method, Long>{
	
	@Query( "select o from Method o where o.id in :ids" )
	Set<Method> findByInventoryIdIn(@Param("ids") List<Long> ids);
}
