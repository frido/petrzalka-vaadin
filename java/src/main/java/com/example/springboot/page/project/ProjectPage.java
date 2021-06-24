package com.example.springboot.page.project;

import com.example.springboot.component.PageHeader;
import com.example.springboot.component.Title;
import com.example.springboot.html.HtmlTag;
import com.example.springboot.html.Section;
import com.example.springboot.page.BasePage;

public class ProjectPage extends BasePage {

    protected final ProjectService projectService;

    public ProjectPage(ProjectService projectService) {
        super();
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
