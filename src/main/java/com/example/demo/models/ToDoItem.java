package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Setter
@Getter
@Entity
@Table(name = "todo_items")
public class ToDoItem implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String description;
    private boolean isComplete;
    private Instant createdAt;
    private Instant updatedAt;

    @Override
    public String toString(){
         return String.format("TodoItem{id=%d, description='%s', isComplete='%s', updatedAt='%s", id, description, isComplete, updatedAt);
    }


}
