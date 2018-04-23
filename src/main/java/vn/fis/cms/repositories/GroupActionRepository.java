package vn.fis.cms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.fis.cms.domain.GroupAction;

@Repository
public interface GroupActionRepository extends JpaRepository<GroupAction, Long> {
	
}
