package vn.fis.cms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.fis.cms.domain.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long>{
	
	List<Organization> findByParentid(Long parentId);
}
