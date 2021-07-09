package com.example.application.old.page.project;

import com.example.application.old.html.HtmlTag;

import java.util.List;

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
