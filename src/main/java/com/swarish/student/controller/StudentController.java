package com.swarish.student.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swarish.student.model.Student;
import com.swarish.student.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	  
	@GetMapping("/getAll")
	public List<Student> list(){
		return studentService.listAll();
	}
	
	@PostMapping("/add")
	public String add(@RequestBody Student student) {
		studentService.save(student);
		return "New Student Added";
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<Student> get(@PathVariable Integer id){
		try {
			Student student = studentService.get(id);
			return new ResponseEntity<Student>(student ,HttpStatus.OK);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Student> update(@RequestBody Student student,@PathVariable Integer id){
		try { 
   		  Student existingStudent = studentService.get(id);
			studentService.save(student);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
	studentService.delete(id);
	return "Delete Student with id";
	}
}
