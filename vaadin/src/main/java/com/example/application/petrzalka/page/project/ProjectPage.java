package com.example.application.petrzalka.page.project;

import com.example.application.petrzalka.Configuration;
import com.example.application.petrzalka.component.PageHeader;
import com.example.application.petrzalka.component.Title;
import com.example.application.petrzalka.html.HtmlTag;
import com.example.application.petrzalka.html.Section;
import com.example.application.petrzalka.page.BasePage;

public class ProjectPage extends BasePage {

    protected final ProjectService projectService;

    public ProjectPage(Configuration conf, ProjectService projectService) {
        super(conf);
        this.projectService = projectService;
    }

    public void applyContent(HtmlTag body) {
        body.with(projects());
    }

    private HtmlTag projects() {
        return new Section()
                .with(new Title("Projekty"))
                .with(new ArticleProjectListComponent(projectService.getAllProjects()));
    }

    @Override
    public String getFileName() {
        return PageHeader.POSTS + "/index";
    }
}
