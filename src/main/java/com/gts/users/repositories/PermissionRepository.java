package com.gts.users.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gts.users.entities.PermissionEntity;
@Repository
public interface PermissionRepository extends CrudRepository<PermissionEntity, Long> {

	
	@Query(value = "select * from gts_permission where gts_permission_name=:f2", nativeQuery = true)
	PermissionEntity findByName(@Param("f2")String name);
	
}
