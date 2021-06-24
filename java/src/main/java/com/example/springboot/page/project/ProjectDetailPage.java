package com.example.springboot.page.project;

import com.example.springboot.component.Figure;
import com.example.springboot.component.PageHeader;
import com.example.springboot.component.Title;
import com.example.springboot.html.*;
import com.example.springboot.page.BasePage;

import java.util.Optional;

public class ProjectDetailPage extends BasePage {

    private final Project project;

    public ProjectDetailPage(Project project) {
        this.project = project;
    }

    @Override
    public void applyContent(HtmlTag body) {
        body.createContent(new Section())
                .with(new Title(project.getTitle()))
                .with(Span.muted("aktualizované: " + project.getDate().toString())) // TODO: Date formatter
                .with(new ArticleProjectComponent(project, false))
                .with(gallery())
                .with(new HtmlNoTag(project.getDetail()));
    }

    private HtmlTag gallery() { // TODO: can be as component
        HtmlTag gallery = new Div("gallery");
        for(Image image : project.getImages()) {
            gallery.with(new Figure(image.getSource()));
        }
        return gallery;
    }

    @Override
    public String getFileName() {
        return PageHeader.POSTS + project.getUrl() + "/index";
    }

    @Override
    public String getTitle() {
        return project.getTitle();
    }

    @Override
    public String getDescription() {
        return project.getDescription();
    }

    @Override
    public String getIcon() {
        return Optional.ofNullable(project.getIcon()).map(Image::getSource).orElse(super.getIcon());
    }
}
