package com.programozzteis.cardealer.cardealer.users.salesman;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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
	
	/**
	 * Retrieve {@link Salesman}s from the data store by name, returning all salesman
	 * whose name <i>starts</i> with the given name.
	 * @param name Value to search for
	 * @return a Collection of matching {@link Salesman}s (or an empty Collection if none found)
	 */
	@Query("SELECT DISTINCT salesman FROM Salesman salesman WHERE salesman.name LIKE ('%' || :name || '%')")
	@Transactional(readOnly = true)
	Collection<Salesman> findByName(@Param("name") String name);
	
}
