package org.example.todo.service;

import org.example.todo.entity.Todo;
import org.example.todo.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> index() {
        return todoRepository.findAll();
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(String id, Todo updateTodo) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "todo not found with id: " + id);
        }
        updateTodo.setId(id);
        Todo todo = todoOptional.get();
        if (!Objects.isNull(updateTodo.getText())) {
            todo.setText(updateTodo.getText());
        }
        todo.setDone(updateTodo.isDone());
        return todoRepository.save(todo);
    }
}
