package com.nt.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.CityMaster;

public interface ICityRepo extends JpaRepository<CityMaster, Integer> {
	
	List<CityMaster> findByStateId(Integer stateId);

}
