package com.example.application.old.page.project;

import com.example.application.old.Configuration;
import com.example.application.old.component.Figure;
import com.example.application.old.component.PageHeader;
import com.example.application.old.component.Title;
import com.example.application.old.html.*;
import com.example.application.old.page.BasePage;

import java.util.Optional;

public class ProjectDetailPage extends BasePage {

    private final Project project;

    public ProjectDetailPage(Configuration conf, Project project) {
        super(conf);
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
