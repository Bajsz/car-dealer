package com.programozzteis.cardealer.cardealer.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.programozzteis.cardealer.cardealer.model.BaseEntity;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private UserType type;
	
	@Column(name = "currentMoney")
	private int currentMoney;

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public int getCurrentMoney() {
		return currentMoney;
	}

	public void setCurrentMoney(int currentMoney) {
		this.currentMoney = currentMoney;
	}
	
	
	
}
