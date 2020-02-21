package com.programozzteis.cardealer.cardealer.users.admins;

import org.springframework.data.repository.Repository;

public interface AdminRepository extends Repository<Admin, Integer> {

	
	/**
	 * Returns all instances of the type.
	 *
	 * @return all entities
	 */
	Iterable<Admin> findAll();
	
}
