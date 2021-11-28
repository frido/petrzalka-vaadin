package com.example.application.petrzalka.page.project;

import java.util.Optional;

import com.example.application.petrzalka.Configuration;
import com.example.application.petrzalka.component.Figure;
import com.example.application.petrzalka.component.PageHeader;
import com.example.application.petrzalka.component.Title;
import com.example.application.petrzalka.html.*;
import com.example.application.petrzalka.page.BasePage;

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

    private HtmlTag gallery() { 
        HtmlTag gallery = new Div("gallery row");
        for(Image image : project.getImages()) {
            gallery.with(
            		(new Figure(image.getSource(), image.getTitle())
            				.clazz("col-md-4")
            			));
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
