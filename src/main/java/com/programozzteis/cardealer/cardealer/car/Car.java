package com.programozzteis.cardealer.cardealer.car;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.programozzteis.cardealer.cardealer.model.BaseEntity;

@Entity
@Table(name = "advertisements")
public class Car extends BaseEntity {

	@Enumerated(EnumType.STRING)
	@Column(name = "car_type")
	private CarTypes carType;
	
	@DateTimeFormat(pattern = "yyyy-MM")
	@Column(name = "car_prod_date")
	private LocalDate prodYear;
	
	@Column(name = "car_power")
	private String power;
	
	@Column(name = "car_consumption")
	private String consumption;
	
	@Column(name = "car_price")
	private int price;
	
	@Column(name = "car_user_id")
	private int user;

	public CarTypes getCarType() {
		return carType;
	}

	public void setCarType(CarTypes carType) {
		this.carType = carType;
	}

	public LocalDate getProdYear() {
		return prodYear;
	}

	public void setProdYear(LocalDate prodYear) {
		this.prodYear = prodYear;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getConsumption() {
		return consumption;
	}

	public void setConsumption(String consumption) {
		this.consumption = consumption;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}
	
}
