package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.CountryMaster;

public interface ICountryRepo extends JpaRepository<CountryMaster, Integer> {

}
