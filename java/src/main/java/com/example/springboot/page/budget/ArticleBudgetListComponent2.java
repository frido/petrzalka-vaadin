package com.example.springboot.page.budget;

import com.example.springboot.html.HtmlTag;
import com.example.springboot.page.project.Project;

import java.util.List;

public class ArticleBudgetListComponent2 extends HtmlTag {

    private final List<BudgetProject> project;

    public ArticleBudgetListComponent2(List<BudgetProject> project) {
        super("div");
        this.project = project;
    }

    @Override
    public String toString() {
        project.forEach(p -> addContent(new ArticleBudgetComponent(p, true)));
        return super.toString();
    }
}
