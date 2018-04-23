package vn.fis.cms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.fis.cms.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
