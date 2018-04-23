package vn.fis.cms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.fis.cms.domain.Action;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long>{
	
	public List<Action> findAll();
}
