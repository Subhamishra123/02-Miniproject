package com.nt.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.UserMaster;

public interface IUserRepo extends JpaRepository<UserMaster, Integer> {
	
	Optional<UserMaster> findByEmailAndPassword(String email,String password);
	Optional<UserMaster> findByEmail(String email);

}
