package com.student.crud.apps.ws.dao;

import org.springframework.data.repository.CrudRepository;

import com.student.crud.apps.ws.entities.Student;

public interface StudentRepository extends CrudRepository<Student, Integer>{

}
