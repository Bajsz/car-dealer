package com.programozzteis.cardealer.cardealer.car;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

public interface CarRepository extends Repository<Car, Integer> {
	
	/**
	 * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
	 * entity instance completely.
	 *
	 * @param entity must not be {@literal null}.
	 * @return the saved entity; will never be {@literal null}.
	 * @throws IllegalArgumentException in case the given {@literal entity} is {@literal null}.
	 */
	public void save(Car car);
	
	/**
	 * Deletes the entity with the given id.
	 *
	 * @param id must not be {@literal null}.
	 * @throws IllegalArgumentException in case the given {@literal id} is {@literal null}
	 */
	void deleteById(Integer id);
	
	/**
	 * Deletes a given entity.
	 *
	 * @param entity must not be {@literal null}.
	 * @throws IllegalArgumentException in case the given entity is {@literal null}.
	 */
	void delete(Car entity);

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
	List<Car> findAll();
	
	/**
	 * Retrieves an entity by its id.
	 *
	 * @param id must not be {@literal null}.
	 * @return the entity with the given id or {@literal Optional#empty()} if none found.
	 * @throws IllegalArgumentException if {@literal id} is {@literal null}.
	 */
	Car findById(Integer id);
}
