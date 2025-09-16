package org.example.todo.controller;

import org.example.todo.dto.TodoRequest;
import org.example.todo.dto.TodoResponse;
import org.example.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.todo.dto.TodoMapper.toEntity;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<TodoResponse> index() {
        return todoService.index();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse createTodo(@RequestBody @Validated TodoRequest todoRequest) {
        return todoService.createTodo(toEntity(todoRequest));
    }

    @PutMapping("/{id}")
    public TodoResponse updateTodo(@RequestBody @Validated TodoRequest todoRequest,
                           @PathVariable String id) {
        return todoService.updateTodo(id, toEntity(todoRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable String id) {
        todoService.deleteTodo(id);
    }
}
