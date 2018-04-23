package vn.fis.cms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.fis.cms.domain.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, String>{
	
	@Query("SELECT l FROM Location l WHERE l.type = :type ORDER BY l.name ASC")
	List<Location> findByTypeOrderByNameAsc(@Param("type") String type);
	
	@Query("SELECT l FROM Location l WHERE SUBSTR(l.id,0,LENGTH(:id)) = :id and l.type = :type")
	List<Location> getListLocationData(@Param("id") String Id, @Param("type") String type);
	
}