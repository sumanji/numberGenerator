package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LogoStorage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int logoId;
	
	private String applicationName;
	private String logoName;

}
