package com.programozzteis.cardealer.cardealer.users.admins;

import org.springframework.data.repository.Repository;

public interface AdminRepository extends Repository<Admin, Integer> {

	/**
	 * Returns whether an entity with the given id exists.
	 *
	 * @param id must not be {@literal null}.
	 * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
	 * @throws IllegalArgumentException if {@literal id} is {@literal null}.
	 */
	boolean existsById(Integer id);
	
	/**
	 * Returns all instances of the type.
	 *
	 * @return all entities
	 */
	Iterable<Admin> findAll();
	
}
