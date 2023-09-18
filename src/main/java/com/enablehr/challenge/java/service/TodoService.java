package com.enablehr.challenge.java.service;

import com.enablehr.challenge.java.entity.Todo;
import com.enablehr.challenge.java.entity.TodoResp;
import com.enablehr.challenge.java.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TodoService {

    @Autowired
    TodoRepository todoRepository;

    public TodoResp create(String text) {
        Todo todo = new Todo();
        todo.setCompleted(Boolean.FALSE);
        todo.setText(text);
        todoRepository.save(todo);
        return new TodoResp(todo);
    }

    public List<TodoResp> list() {
        return todoRepository.findAll().stream().map(TodoResp::new).collect(Collectors.toList());
    }

    public TodoResp update(Todo body) {
        Todo toUpdate = todoRepository.getById(Objects.requireNonNull(body.getId()));
        toUpdate.setText(body.getText());
        todoRepository.save(toUpdate);
        return new TodoResp(toUpdate);
    }

    public void delete(Integer id) {
        todoRepository.delete(todoRepository.getById(Objects.requireNonNull(id)));
    }

    public TodoResp markAsComplete(Integer id) {
        Todo toUpdate = todoRepository.getById(Objects.requireNonNull(id));
        toUpdate.setCompleted(true);
        todoRepository.save(toUpdate);
        return new TodoResp(toUpdate);
    }

    public List<TodoResp> clearCompleted() {
        List<TodoResp> incompleteTodo = new ArrayList<>();
        todoRepository.findAll().forEach(data -> {
            if (data.getCompleted())
                todoRepository.delete(data);
            else
                incompleteTodo.add(new TodoResp(data));
        });
        return incompleteTodo;
    }

    public List<TodoResp> completeAll() {
        List<TodoResp> todoList = new ArrayList<>();
        todoRepository.findAll().forEach(data -> {
            data.setCompleted(true);
            todoRepository.save(data);
            todoList.add(new TodoResp(data));
        });
        return todoList;
    }

}
