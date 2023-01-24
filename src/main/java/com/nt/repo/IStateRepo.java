package com.nt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.StateMaster;

public interface IStateRepo extends JpaRepository<StateMaster, Integer> {
	
	List<StateMaster> findByCountryId(Integer countryId);

}
