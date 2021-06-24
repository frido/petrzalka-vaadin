package com.example.springboot.page.project;

import com.example.springboot.model.Statement;
import com.example.springboot.page.budget.Budget;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "project")
public class Project {
    @Id
    private int id;
    private String title;
    private LocalDate date;
    private String description;
    @ManyToOne()
    @JoinColumn(name = "icon", referencedColumnName = "id")
    private Image icon;
//    @ManyToOne()
//    @JoinColumn(name = "project_id", referencedColumnName = "id")
//    private Budget budget;
    private String url;
    @Enumerated(EnumType.STRING)
    private ProjectPhase phase;
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("date DESC")
    private Set<Statement> statements = new HashSet<>(0);
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("source ASC")
    private Set<Image> images = new HashSet<>(0);
    @Column(name = "detail")
    private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }
//
//    public Budget getBudget() {
//        return budget;
//    }
//
//    public void setBudget(Budget budget) {
//        this.budget = budget;
//    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ProjectPhase getPhase() {
        return phase;
    }

    public void setPhase(ProjectPhase phase) {
        this.phase = phase;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public Set<Statement> getStatements() {
        return statements;
    }

    public void setStatements(Set<Statement> statements) {
        this.statements = statements;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }
}
