package com.programozzteis.cardealer.cardealer.users.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.programozzteis.cardealer.cardealer.model.NamedEntity;

@Entity
@Table(name = "customers")
public class Customer extends NamedEntity {

	@Column(name = "current_money")
	private int currentMoney;

	public int getCurrentMoney() {
		return currentMoney;
	}
	
	public void setCurrentMoney(int currentMoney) {
		this.currentMoney = currentMoney;
	}

}
