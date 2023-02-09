package com.greatlearning.emra.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	
	public Employee(String firstName, String lastName, String email) {
		// TODO Auto-generated constructor stub
	}

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="EMAIL")
	private String email;
	
}
