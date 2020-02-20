package com.programozzteis.cardealer.cardealer.car;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.programozzteis.cardealer.cardealer.model.BaseEntity;
import com.programozzteis.cardealer.cardealer.users.Salesman;

@Entity
@Table(name = "advertisements")
public class Car extends BaseEntity {

	@Enumerated(EnumType.STRING)
	@Column(name = "car_type")
	private CarType type;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "car_prod_date")
	private LocalDate prodYear;
	
	@Column(name = "car_power")
	private String power;
	
	@Column(name = "car_consumption")
	private String consumption;
	
	@Column(name = "car_price")
	private int price;
	
	@ManyToOne
	@JoinColumn(name = "car_salesman_id")
	private Salesman salesman;

	public CarType getType() {
		return type;
	}

	public void setType(CarType type) {
		this.type = type;
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
	
	public Salesman getSalesman() {
		return salesman;
	}

	public void setSalesman(Salesman salesman) {
		this.salesman = salesman;
	}
	
	public int estimatePrice()
	{
		int estimatedPrice = 0;
		
		/** TEST LOGIC */
		estimatedPrice = 100;
		/** TO BE REPLACED */
		
		return estimatedPrice;
	}

	
}
