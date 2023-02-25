package com.student.crud.apps.ws.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.crud.apps.ws.ExceptionHandler.StudentNotFound;
import com.student.crud.apps.ws.dao.StudentRepository;
import com.student.crud.apps.ws.entities.Student;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepo;
	
	@Override
	public Student createStudent(Student student) {
		//if(studentRepo.findById(student.getId()) != null) throw new RuntimeException("Record already exit");
		return studentRepo.save(student);
	}

	@Override
	public void updateStudent(Student student) {
		studentRepo.save(student);
		
	}

	@Override
	public Student getStudent(int id) {
		Optional<Student> optional = studentRepo.findById(id);
		Student st = optional.get();
		return st;
	}

	@Override
	public List<Student> getStudents() {
		return (List<Student>)studentRepo.findAll();
	}

	@Override
	public void deleteStudent(int id) {
		studentRepo.deleteById(id);
		
	}

	@Override
	public boolean isStudentExist(int id) {
		return studentRepo.existsById(id);
	}

}
