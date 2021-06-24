package com.example.springboot.page.grant;

import com.example.springboot.page.grant.GrantCategory;

import javax.persistence.*;

@Entity
@Table(name = "grant_subject")
public class GrantSubject {
    @Id
    private int id;
    private String title;
    @Enumerated(EnumType.STRING)
    private GrantCategory category;

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

    public GrantCategory getCategory() {
        return category;
    }

    public void setCategory(GrantCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "GrantSubject{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category=" + category +
                '}';
    }
}
