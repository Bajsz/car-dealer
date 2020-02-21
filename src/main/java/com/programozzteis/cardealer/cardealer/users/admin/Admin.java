package com.programozzteis.cardealer.cardealer.users.admin;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.programozzteis.cardealer.cardealer.model.NamedEntity;

@Entity
@Table(name = "admins")
public class Admin extends NamedEntity {

}
