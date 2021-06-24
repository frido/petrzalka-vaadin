package com.example.springboot.model;

import com.example.springboot.page.project.Project;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "statement")
public class Statement {
    @Id
    private int id;
    private String title;
    private String source;
    private LocalDate date;
    private String status;
    @Column(name = "status_description")
    private String statusDescription;
    @ManyToOne()
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
