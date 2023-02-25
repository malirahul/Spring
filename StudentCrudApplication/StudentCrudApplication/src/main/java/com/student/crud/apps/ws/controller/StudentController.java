package com.student.crud.apps.ws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.student.crud.apps.ws.ExceptionHandler.StudentNotFound;
import com.student.crud.apps.ws.entities.Student;
import com.student.crud.apps.ws.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/student")
	public ResponseEntity<Object> createStudent(@RequestBody Student student) {

		student = studentService.createStudent(student);
		return new ResponseEntity<>("Student is created successfully with id = " + student.getId(), HttpStatus.CREATED);

	}

	@PutMapping("/students/{id}")
	public ResponseEntity<Object> updateStudent(@PathVariable("id") int id, @RequestBody Student student) {

		boolean isStudentExist = studentService.isStudentExist(id);
		
		
		if (isStudentExist) {
			student.setId(id);
			studentService.updateStudent(student);
			return new ResponseEntity<>("Student is updated successfully", HttpStatus.OK);
		} else {
		  throw new RuntimeException("Student doest not exits");
		}
		

	}

	@GetMapping("/students/{id}")
	public ResponseEntity<Object> getStudent(@PathVariable("id") int id) {
		boolean isStudentExist = studentService.isStudentExist(id);
		if (isStudentExist) {
			Student student = studentService.getStudent(id);
			return new ResponseEntity<>(student, HttpStatus.OK);
		} else {
			throw new StudentNotFound("Student not found with id "+id);
		}

	}

	@GetMapping("/students")
	public ResponseEntity<Object> getStudents() {
		List<Student> studentList = studentService.getStudents();
		return new ResponseEntity<>(studentList, HttpStatus.OK);
	}

	@DeleteMapping("/students/{id}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable("id") int id) {
		boolean isStudentExist = studentService.isStudentExist(id);
		if (isStudentExist) {
			studentService.deleteStudent(id);
			return new ResponseEntity<>("Student is deleted successsfully", HttpStatus.OK);
		} else {
			return null;
		}

	}

}
