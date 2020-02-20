package com.programozzteis.cardealer.cardealer.users;

import org.springframework.data.repository.Repository;

public interface SalesmanRepository extends Repository<Salesman, Integer> {

	/**
	 * Returns all instances of the type.
	 *
	 * @return all entities
	 */
	Iterable<Salesman> findAll();	
	
}
