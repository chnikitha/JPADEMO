package com.example.demo.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.demo.entity.Student;
import com.example.demo.repo.StudentRepo;

@Service
public class StudentService {
		@Autowired
		StudentRepo studentRepo;
		
		public ResponseEntity<Student> saveStudent(@RequestBody Student student)
		{ 
			return new ResponseEntity<>(studentRepo.save(student),HttpStatus.CREATED);		
		}
		
		
		public Map<String, List<Student>> getStudents(String sortBy, String orderBy) 
		{
			List<Student> students=studentRepo.findAll();
			Map<String, List<Student>> sortedStudents=null;
			if(orderBy.equalsIgnoreCase("ascending"))
			{
	        if (sortBy.equalsIgnoreCase("name")) {
	            sortedStudents = students.stream()
	                    .sorted(Comparator.comparing(student -> student.getStudentName()))
	                    .collect(Collectors.groupingBy(Student::getClassId));
	        } else if(sortBy.equalsIgnoreCase("id"))
	            sortedStudents = students.stream()
	                    .sorted(Comparator.comparing(student -> student.getId()))
	                    .collect(Collectors.groupingBy(Student::getClassId));
	        
		else
	        {
	        	sortedStudents = students.stream()
	                    .sorted(Comparator.comparing(student -> student.getStudentEmail()))
	                    .collect(Collectors.groupingBy(Student::getClassId));
	        }
			}
	        	    
		else if(orderBy.equalsIgnoreCase("descending"))
		{
			if (sortBy.equalsIgnoreCase("name")) {
	            sortedStudents = students.stream()
	                    .sorted(Comparator.comparing(student -> ((Student) student).getStudentName()).reversed())
	                    .collect(Collectors.groupingBy(Student::getClassId));
	        } else if(sortBy.equalsIgnoreCase("id"))
	            sortedStudents = students.stream()
	                    .sorted(Comparator.comparing(student -> ((Student) student).getId()).reversed())
	                    .collect(Collectors.groupingBy(Student::getClassId));
	        
		else
	        {
	        	sortedStudents = students.stream()
	                    .sorted(Comparator.comparing(student -> ((Student) student).getStudentEmail()).reversed())
	                    .collect(Collectors.groupingBy(Student::getClassId));
	        }
		}
			
		return (sortedStudents);
		}	
			
		//to get all students based on class Id
		
		
		public List<Student> getStudentsByClassId(String classId) {
	        return studentRepo.findByClassId(classId);
		}
		
		
		public ResponseEntity<Student> getStudent(@PathVariable long id) 
		{
			Optional<Student> student = studentRepo.findById(id);
			if(student.isPresent())
			{
				return new ResponseEntity<>(student.get(),HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		
		public ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody Student stud) 
		{
			Optional<Student> student = studentRepo.findById(id);
			if(student.isPresent())
			{
				student.get().setStudentName(stud.getStudentName());
				student.get().setStudentEmail(stud.getStudentEmail());
				student.get().setStudentAddress(stud.getStudentAddress());
				student.get().setClassId(stud.getClassId());
				return new ResponseEntity<>(studentRepo.save(student.get()),HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		public ResponseEntity<Void> deleteStudent(@PathVariable long id) 
		{
			Optional<Student> student = studentRepo.findById(id);
			if(student.isPresent())
			{
				studentRepo.deleteById(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			else
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		}


		


