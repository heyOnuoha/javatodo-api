package com.example.first_spring_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.first_spring_app.models.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    
}
