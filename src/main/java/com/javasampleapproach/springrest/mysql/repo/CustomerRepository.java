package com.javasampleapproach.springrest.mysql.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.javasampleapproach.springrest.mysql.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	List<Customer> findByAge(int age);

	Optional<Customer> findById(long id);
}
