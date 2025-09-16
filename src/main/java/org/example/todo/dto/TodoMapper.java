package org.example.todo.dto;

import org.example.todo.entity.Todo;
import org.springframework.beans.BeanUtils;

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
}
