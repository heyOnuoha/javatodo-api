package com.example.first_spring_app.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Update a Todo Item")
public class UpdateTodoDto {
    @Schema(description = "Todo's ID", example = "1")
    private Long id;

    @Schema(description = "Todo's title", example = "Get Something Tonight")
    private String title;

    @Schema(description = "Todo's Description", example = "Make sure it's food")
    private String description;

    @Schema(description = "Todo's status", example = "false")
    private Boolean isCompleted;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isIsCompleted() {
        return this.isCompleted;
    }

    public Boolean getIsCompleted() {
        return this.isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

}
