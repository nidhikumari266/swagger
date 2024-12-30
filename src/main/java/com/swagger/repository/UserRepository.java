package com.swagger.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.swagger.entity.User;
import com.swagger.enumration.Role;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE " +
	           "(:name IS NULL OR u.name LIKE %:name%) AND " +
	           "(:email IS NULL OR u.email LIKE %:email%) AND " +
	           "(:role IS NULL OR u.role = :role)")
	    List<User> searchUsers(@Param("name") String name, 
	                           @Param("email") String email, 
	                           @Param("role") Role role);
	
	
	
}
