package com.example.application.petrzalka.page.project;

import java.util.List;

import com.example.application.petrzalka.html.HtmlTag;

public class ArticleProjectListComponent extends HtmlTag {

    private final List<Project> project;

    public ArticleProjectListComponent(List<Project> project) {
        super("div");
        this.project = project;
    }

    @Override
    public String toString() {
        project.forEach(p -> addContent(new ArticleProjectComponent(p, true)));
        return super.toString();
    }
}
