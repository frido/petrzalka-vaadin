package com.example.application.old.page.project;

import com.example.application.old.Configuration;
import com.example.application.old.component.PageHeader;
import com.example.application.old.component.Title;
import com.example.application.old.html.HtmlTag;
import com.example.application.old.html.Section;
import com.example.application.old.page.BasePage;

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
