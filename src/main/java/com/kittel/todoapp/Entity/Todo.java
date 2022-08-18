package com.kittel.todoapp.Entity;

import javax.persistence.*;


@Entity
@Table
public class Todo {
    public Todo() {
    }

    public Todo(Long id, String description, Boolean finished) {
        this.id = id;
        this.description = description;
        this.finished = finished;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="description", length=255, nullable=false, unique=false)
    private String description;

    @Column(columnDefinition="tinyint(1) default 0" )
    private Boolean finished;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


// fields, getters and setters

}

