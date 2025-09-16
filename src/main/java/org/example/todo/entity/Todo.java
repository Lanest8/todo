package org.example.todo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @Id
    private String id;
    private String text;
    private boolean done;

    @PrePersist
    public void ensureId() {
        this.id = UUID.randomUUID().toString();
    }
}
