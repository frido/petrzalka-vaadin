package com.example.springboot.page.budget;

import com.example.springboot.component.PageHeader;
import com.example.springboot.html.*;
import com.example.springboot.model.Statement;
import com.example.springboot.page.project.Project;
import com.example.springboot.page.project.ProjectStatus;

public class ArticleBudgetComponent extends HtmlTag {

    private final BudgetProject project;
    private final boolean isFull;

    public ArticleBudgetComponent(BudgetProject project, boolean isFull) {
        super("div");
        this.project = project;
        this.isFull = isFull;
    }

    @Override
    public String toString() {
        addContent(article(project));
        return super.toString();
    }

    private HtmlTag article(BudgetProject project) {
        HtmlTag article = new HtmlTag("article");
        article.clazz("box").clazz(ProjectStatus.INWORK.clazz());
        Row row = (Row) article.createContent(new Row());
        HtmlTag header = row.column("col-md-10");
//                .with(new H(5)
//                        .clazz("status-text")
//                        .clazz(project.getStatus().clazz())
//                        .with(project.getPhase().getLabel()));
        if (isFull) {
            header.with(new H(3)
                    .with(new AHref(resolve(project.getUrl()), project.getTitle())));
        }
        row.column("col-md-2 text-right")
                .with(statusText(ProjectStatus.INWORK.label(), ProjectStatus.INWORK.clazz()));
        Row content = (Row) article.createContent(new Row());
        HtmlTag cardContent = null;
        if (!isFull) {
            cardContent = content.column("col-md-12");
        }
        else {
            content.column("col-md-3").with(new Img(null,null).clazz("card-img"));
            cardContent = content.column("col-md-9").with(new Div("project-description").with(project.getDescription()));
        }
        HtmlTag statementList = cardContent.createContent(new Div("statement-list"));

        project.getBudgets().forEach(s -> statementList.addContent(statement(s)));
        return article;
    }

    private String resolve(String url) {
        return PageHeader.POSTS + url + "/";
    }

    private HtmlTag statement(Budget statement) {
        HtmlTag row = new Div("statement-row");
        row.with(new Span("", statement.getTitle()));
        row.with(new Span("muted", String.valueOf(statement.getYear())));
        return row;
    }

    private HtmlTag statusText(String text, String clazz) {
        return new Span("status-text", text).clazz(clazz);
    }
}
