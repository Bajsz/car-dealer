package com.programozzteis.cardealer.cardealer.users;

import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Integer> {

	/**
	 * Returns all instances of the type.
	 *
	 * @return all entities
	 */
	Iterable<User> findAll();
	
}
