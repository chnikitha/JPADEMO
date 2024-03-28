package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.StudentService;
import com.example.demo.entity.Student;
import com.example.demo.repo.StudentRepo;

@RestController
public class StudentController {
	@Autowired
	StudentService studentService;
	
	@PostMapping("/api/students")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student)
	{ 
		return studentService.saveStudent(student)	;	
	}
	
	@GetMapping("/api/students")
	public Map<String, List<Student>> getStudents(@RequestHeader(value = "SortBy", defaultValue = "name") String sortBy, 
			@RequestHeader(value = "OrderBy", defaultValue = "ascending") String orderBy) 
	{
		return studentService.getStudents(sortBy,orderBy);
			
	}
	//to get sorted students based on classId
	@GetMapping("/api/students/class/{classId}")
    public List<Student> getStudentsByClassId(@PathVariable String classId) {
        return studentService.getStudentsByClassId(classId);
	}
	
	@GetMapping("/api/students/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable long id) 
	{
		return studentService.getStudent(id);
	}
	
	@PutMapping("/api/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody Student stud) 
	{
		return studentService.updateStudent(id, stud);
	}
	@DeleteMapping("/api/students/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable long id) 
	{
		return studentService.deleteStudent(id);
	}


}
