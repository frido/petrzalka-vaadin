package com.example.springboot.page.budget;

import com.example.springboot.model.Statement;
import com.example.springboot.page.project.Image;
import com.example.springboot.page.project.ProjectPhase;
import com.example.springboot.page.project.ProjectStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "budget_project")
public class BudgetProject {
    @Id
    private int id;
    private String title;
    private String description;
    private String url;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("year DESC")
    private Set<Budget> budgets = new HashSet<>(0);

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Budget> getBudgets() {
        return budgets;
    }

    public void setBudgets(Set<Budget> budgets) {
        this.budgets = budgets;
    }
}
