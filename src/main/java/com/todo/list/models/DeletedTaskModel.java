package com.todo.list.models;

import jakarta.persistence.*;

@Entity
@Table(name = "DeletedTask")
public class DeletedTaskModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long deletedId;

    // getters and setters
    public DeletedTaskModel(Long id) {
    }

    public void DeletedTaskId() {}

    public void DeletedTaskId(Long deletedId) {
        this.deletedId = deletedId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeletedId() {
        return deletedId;
    }

    public void setDeletedId(Long deletedId) {
        this.deletedId = deletedId;
    }
}
