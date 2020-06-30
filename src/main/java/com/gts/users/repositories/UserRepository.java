package com.gts.users.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gts.users.entities.UserEntity;


@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>{

	@Query(value = "select * from gts_users where gts_user_email=:f2", nativeQuery = true)
    UserEntity findByEmail(@Param("f2") String email);
	
	@Query(value = "select * from gts_users where gts_user_id=:f2", nativeQuery = true)
	UserEntity findByUserId(@Param("f2") Long f1);

      
}
