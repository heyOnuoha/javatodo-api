package com.example.first_spring_app.services;

import java.util.List;

import com.example.first_spring_app.dtos.CreateTodoDto;
import com.example.first_spring_app.dtos.TodoDto;
import com.example.first_spring_app.dtos.UpdateTodoDto;
import com.example.first_spring_app.dtos.responses.StructuredResponseDto;

public interface TodoService {
    
    public StructuredResponseDto<List<TodoDto>> getAllTodos();
    public StructuredResponseDto<TodoDto> getTodo(Long id);
    public StructuredResponseDto<TodoDto> updateTodo(UpdateTodoDto todoItem);
    public StructuredResponseDto<TodoDto> addTodo(CreateTodoDto todoItem);
    public StructuredResponseDto<TodoDto> deleteTodo(Long id);
}
