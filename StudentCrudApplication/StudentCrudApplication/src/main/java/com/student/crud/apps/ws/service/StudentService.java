package com.student.crud.apps.ws.service;

import java.util.List;

import com.student.crud.apps.ws.entities.Student;

public interface StudentService {

	public abstract Student createStudent(Student student);

	public abstract void updateStudent(Student student);

	public abstract Student getStudent(int id);

	public abstract List<Student> getStudents();

	public abstract void deleteStudent(int id);

	public abstract boolean isStudentExist(int id);

}
