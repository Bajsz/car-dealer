package com.programozzteis.cardealer.cardealer.users;

import org.springframework.data.repository.Repository;

public interface CustomerRepository extends Repository<Customer, Integer> {

	
	/**
	 * Returns all instances of the type.
	 *
	 * @return all entities
	 */
	Iterable<Customer> findAll();
	
}
