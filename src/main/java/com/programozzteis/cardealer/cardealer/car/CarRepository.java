package com.programozzteis.cardealer.cardealer.car;

import org.springframework.data.repository.Repository;

public interface CarRepository extends Repository<Car, Integer> {

	public void save(Car car);
	
}
