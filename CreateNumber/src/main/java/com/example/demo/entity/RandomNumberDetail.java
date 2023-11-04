package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name ="RandomNumberStore" )
public class RandomNumberDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int randomNumber;
	@Temporal(TemporalType.DATE)
	private Date numberInsertionDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRandomNumber() {
		return randomNumber;
	}
	public void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}
	public Date getNumberInsertionDate() {
		return numberInsertionDate;
	}
	public void setNumberInsertionDate(Date numberInsertionDate) {
		this.numberInsertionDate = numberInsertionDate;
	}
	
	
}
