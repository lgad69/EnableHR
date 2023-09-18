package com.enablehr.challenge.java.controller;

import com.enablehr.challenge.java.entity.Todo;
import com.enablehr.challenge.java.entity.TodoResp;
import com.enablehr.challenge.java.repository.TodoRepository;
import com.enablehr.challenge.java.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


@RestController
@CrossOrigin
@RequestMapping("/todo")
public class TodoController {

  @Autowired
  TodoRepository todoRepository;

  @Autowired
  TodoService todoService;

  @PostMapping("/add")
  public TodoResp create(@RequestBody Todo body) {
    return todoService.create(body.getText(), body.getParentId());
  }

  @GetMapping("/list")
  public List<TodoResp> list() {
    return todoService.list();
  }

  @PutMapping("/update")
  public TodoResp update(@RequestBody Todo body) {
    return todoService.update(body);
  }

  @DeleteMapping("/delete")
  public void delete(@RequestBody Todo body) {
    todoService.delete(body.getId());
  }

  @PostMapping("/complete")
  public TodoResp markAsComplete(@RequestBody Todo body) {
    return todoService.markAsComplete(body.getId());
  }

  @PostMapping("/batch/clear")
  public List<TodoResp> clearCompleted() {
    return todoService.clearCompleted();
  }

  @PostMapping("/batch/complete")
  public List<TodoResp> completeAll() {
    return todoService.completeAll();
  }

  @PostConstruct
  public void init() {

    Stream.of("Use Redux", "Use React", "Pass the Test")
          .forEach(text -> {
            final Todo todo = new Todo();
            todo.setCompleted(Boolean.FALSE);
            todo.setText(text);
            todo.setParentId(0);
            todoRepository.save(todo);
          });
  }
}
