package com.example.first_spring_app.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.first_spring_app.dtos.CreateTodoDto;
import com.example.first_spring_app.dtos.TodoDto;
import com.example.first_spring_app.dtos.UpdateTodoDto;
import com.example.first_spring_app.dtos.responses.StructuredResponseDto;
import com.example.first_spring_app.services.TodoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController()
@RequestMapping("/api/todos")
@Tag(name = "Todo Service")
public class TodoController {

    private final TodoService _todoService;

    public TodoController(TodoService todoService) {
        _todoService = todoService;
    }
    
    @Operation(summary = "Get all todos")
    @ApiResponse(responseCode = "200", description = "Todo found",  
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = TodoDto.class)) 
        })
    @GetMapping("/get-all")
    public ResponseEntity<StructuredResponseDto<List<TodoDto>>> getAllTodos() {

        StructuredResponseDto<List<TodoDto>> request = _todoService.getAllTodos();

        return ResponseEntity.ok(request);
    }

    @Operation(summary = "Get a single todo item")
    @ApiResponse(responseCode = "200", description = "Todo found",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = TodoDto.class))
        })
    @GetMapping("/get-todo/{todoId}")
    public ResponseEntity<StructuredResponseDto<TodoDto>> getTodo(
        @Parameter(description = "Todo's Id", example = "1")
        @PathVariable() Long todoId
        ) {

        StructuredResponseDto<TodoDto> request = _todoService.getTodo(todoId);

        if(!request.getSuccess()) {
            return ResponseEntity.status(404).body(request);
        }

        return ResponseEntity.ok(request);
    }

    @Operation(summary = "Add a todo item")
    @ApiResponse(responseCode = "200", description = "Todo added",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = TodoDto.class))
        })
    @PostMapping("/add-todo")
    public ResponseEntity<StructuredResponseDto<TodoDto>> addTodo(
        @Parameter(description = "Update todo dto", required = true, schema = @Schema(implementation = CreateTodoDto.class))
        @Valid @RequestBody CreateTodoDto todoItem
    ) {

        StructuredResponseDto<TodoDto> request = _todoService.addTodo(todoItem);

        if(!request.getSuccess()) {
            return ResponseEntity.badRequest().body(request);
        }

        return ResponseEntity.status(201).body(request);
    }

    @Operation(summary = "update a todo item")
    @ApiResponse(responseCode = "200", description = "Update added",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = TodoDto.class))
        })
    @PutMapping("/update-todo")
    public ResponseEntity<StructuredResponseDto<TodoDto>> updateTodo(
        @Parameter(description = "Update todo dto", required = true, schema = @Schema(implementation = UpdateTodoDto.class))
        @Valid @RequestBody UpdateTodoDto todoItem
    ) {

        StructuredResponseDto<TodoDto> request = _todoService.updateTodo(todoItem);

        if(!request.getSuccess()) {
            return ResponseEntity.badRequest().body(request);
        }

        return ResponseEntity.status(201).body(request);
    }
}
