package com.programozzteis.cardealer.cardealer.users.salesmans;

import org.springframework.data.repository.Repository;

public interface SalesmanRepository extends Repository<Salesman, Integer> {

	/**
	 * Deletes the entity with the given id.
	 *
	 * @param id must not be {@literal null}.
	 * @throws IllegalArgumentException in case the given {@literal id} is {@literal null}
	 */
	void deleteById(Integer id);
	
	/**
	 * Retrieves an entity by its id.
	 *
	 * @param id must not be {@literal null}.
	 * @return the entity with the given id or {@literal Optional#empty()} if none found.
	 * @throws IllegalArgumentException if {@literal id} is {@literal null}.
	 */
	Salesman findById(Integer id);

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
	Iterable<Salesman> findAll();	
	
}
