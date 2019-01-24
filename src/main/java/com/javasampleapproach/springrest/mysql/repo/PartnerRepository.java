package com.javasampleapproach.springrest.mysql.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.javasampleapproach.springrest.mysql.model.Customer;
import com.javasampleapproach.springrest.mysql.model.Partner;

public interface PartnerRepository extends CrudRepository<Partner, Long> {
	List<Partner> findByAge(int age);

	Optional<Partner> findById(long id);
}
