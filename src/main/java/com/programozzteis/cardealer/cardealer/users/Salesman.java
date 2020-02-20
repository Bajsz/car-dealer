package com.programozzteis.cardealer.cardealer.users;

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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "salesman")
	private Set<Car> cars;
	
	protected Set<Car> getCarsInternal() {
		if (this.cars == null) {
			this.cars = new HashSet<>();
		}
		return this.cars;
	}

	protected void setCarsInternal(Set<Car> cars) {
		this.cars = cars;
	}

	public List<Car> getCars() {
		return Collections.unmodifiableList(new ArrayList<>(getCarsInternal()));
	}

	public void addCar(Car car) {
		getCarsInternal().add(car);
		car.setSalesman(this);
	}
}
