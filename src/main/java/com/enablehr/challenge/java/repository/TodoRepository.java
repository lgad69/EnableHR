package com.enablehr.challenge.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enablehr.challenge.java.entity.Todo;


public interface TodoRepository extends JpaRepository<Todo, Integer> {


}
