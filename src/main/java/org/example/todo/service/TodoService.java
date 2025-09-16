package org.example.todo.service;

import org.example.todo.dto.TodoResponse;
import org.example.todo.entity.Todo;
import org.example.todo.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.example.todo.dto.TodoMapper.todoResponse;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoResponse> index() {
        return todoResponse(todoRepository.findAll());
    }

    public TodoResponse createTodo(Todo todo) {
        todo.setId(null);
        return todoResponse(todoRepository.save(todo));
    }

    public TodoResponse updateTodo(String id, Todo updateTodo) {
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
        return todoResponse(todoRepository.save(todo));
    }

    public void deleteTodo(String id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "todo not found with id: " + id);
        }
        Todo todo = todoOptional.get();
        todoRepository.delete(todo);
    }
}
