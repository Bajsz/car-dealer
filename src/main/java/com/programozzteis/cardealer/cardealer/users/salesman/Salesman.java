package com.programozzteis.cardealer.cardealer.users.salesman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.programozzteis.cardealer.cardealer.car.Car;
import com.programozzteis.cardealer.cardealer.model.NamedEntity;

@Entity
@Table(name = "salesmans")
public class Salesman extends NamedEntity {

	@OneToMany(mappedBy = "salesman")
	private List<Car> cars;

	public List<Car> getCars() {
		return cars;
	}

	public void addCar(Car car) {
		this.cars.add(car);
		car.setSalesman(this);
	}
}
