package com.example.first_spring_app.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.first_spring_app.dtos.CreateTodoDto;
import com.example.first_spring_app.dtos.TodoDto;
import com.example.first_spring_app.dtos.UpdateTodoDto;
import com.example.first_spring_app.dtos.responses.StructuredResponseDto;
import com.example.first_spring_app.models.Todo;
import com.example.first_spring_app.repositories.TodoRepository;
import com.example.first_spring_app.services.TodoService;

@Service()
public class TodoServiceImpl implements TodoService {

    private TodoRepository _todoRepo;
    
    @Autowired
    public TodoServiceImpl(TodoRepository todoRepo) {
        _todoRepo = todoRepo;
    }

    @Override
    public StructuredResponseDto<List<TodoDto>> getAllTodos() {
        
        List<TodoDto> todos = _todoRepo.findAll().stream().map((Todo todo) -> convertToDto(todo)).collect(Collectors.toList());

        return StructuredResponseDto.success("Todos Fetched", todos);
    }

    @Override
    public StructuredResponseDto<TodoDto> getTodo(Long id) {
        return _todoRepo.findById(id).map(todo -> {
            TodoDto todoDto = convertToDto(todo);
            return StructuredResponseDto.success("Todo found", todoDto);
        }).orElseGet(() -> StructuredResponseDto.error("Todo not found", null));
    }

    @Override
    public StructuredResponseDto<TodoDto> updateTodo(UpdateTodoDto todoItem) {
       
       return _todoRepo.findById(todoItem.getId()).map(todo -> {

            todo.setDescription(todoItem.getDescription());
            todo.setIsCompleted(todoItem.getIsCompleted());
            todo.setTitle(todoItem.getTitle());

            Todo updatedTodo = _todoRepo.save(todo);

            return StructuredResponseDto.success("Todo updated", convertToDto(updatedTodo));
       }).orElseGet(() -> StructuredResponseDto.error("Todo not found", null));
    }

    @Override
    public StructuredResponseDto<TodoDto> deleteTodo(Long id) {
        return _todoRepo.findById(id).map(todo -> {
            _todoRepo.deleteById(id);
            return StructuredResponseDto.success("Todo deleted", convertToDto(todo));
        }).orElseGet(() -> StructuredResponseDto.error("Todo not found", null));
    }

    public TodoDto convertToDto(Todo todo) {
        TodoDto dto = new TodoDto();
        dto.setId(todo.getId());
        dto.setTitle(todo.getTitle());
        dto.setDescription(todo.getDescription());
        dto.setIsCompleted(todo.getIsCompleted());
        return dto;
    }

    @Override
    public StructuredResponseDto<TodoDto> addTodo(CreateTodoDto todoItem) {

        try {
            Todo todo = new Todo();
            todo.setTitle(todoItem.getTitle());
            todo.setDescription(todoItem.getDescription());
            todo.setIsCompleted(todoItem.getIsCompleted());

            Todo savedTodo = _todoRepo.save(todo);

            return StructuredResponseDto.success("Todo added", convertToDto(savedTodo));
        } catch(Exception e) {

            return StructuredResponseDto.error(e.getMessage(), null);
        }
    }
    
}
