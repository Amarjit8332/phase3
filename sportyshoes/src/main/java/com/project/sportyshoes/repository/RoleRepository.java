package com.project.sportyshoes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.sportyshoes.model.Role;



public interface RoleRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findById(Long id);
	
}

