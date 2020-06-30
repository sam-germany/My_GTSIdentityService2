package com.gts.users.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gts.users.entities.RoleEntity;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long>{

	
	@Query(value = "select * from gts_roles where gts_role_name=:f2", nativeQuery = true)
	RoleEntity findByName(@Param("f2")String name);

}
