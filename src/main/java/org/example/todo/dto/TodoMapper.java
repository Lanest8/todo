package org.example.todo.dto;

import org.example.todo.entity.Todo;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class TodoMapper {

    public static Todo toEntity(TodoResponse todoResponse) {
        Todo todo = new Todo();
        BeanUtils.copyProperties(todoResponse, todo);
        return todo;
    }

    public static Todo toEntity(TodoRequest todoRequest) {
        Todo todo = new Todo();
        BeanUtils.copyProperties(todoRequest, todo);
        return todo;
    }

    public static TodoResponse todoResponse(Todo todo) {
        TodoResponse todoResponse = new TodoResponse();
        BeanUtils.copyProperties(todo, todoResponse);
        return todoResponse;
    }

    public static List<TodoResponse> todoResponse(List<Todo> todos) {
        return todos.stream().map(TodoMapper::todoResponse).toList();
    }
}
