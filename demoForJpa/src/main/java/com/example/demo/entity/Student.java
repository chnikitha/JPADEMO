package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@Entity
public class Student
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	private String studentName;
	private String studentEmail;
	private String studentAddress;
	private String classId;
	public Student() {
		super();
	}
	
	
	
}
