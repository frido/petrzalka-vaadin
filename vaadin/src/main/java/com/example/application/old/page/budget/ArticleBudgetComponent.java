package com.example.application.old.page.budget;

import com.example.application.old.component.Amount;
import com.example.application.old.html.*;
import com.example.application.old.page.project.ProjectStatus;

public class ArticleBudgetComponent extends HtmlTag {

    private final BudgetProject project;
    private final int year;

    public ArticleBudgetComponent(BudgetProject project, int year) {
        super("div");
        this.project = project;
        this.year = year;
    }

    @Override
    public String toString() {
        addContent(article());
        return super.toString();
    }

    private HtmlTag article() {
        HtmlTag article = new HtmlTag("article");
        article.clazz("box").clazz(ProjectStatus.INWORK.clazz());
        Row row = (Row) article.createContent(new Row());
        row.column("col-md-10").createContent(new H(3)).with(project.getTitle());
        row.column("col-md-2 text-right")
                .with(statusText(Amount.of(project.getAmount(year)), ProjectStatus.INWORK.clazz()));
        article.createContent(new Div("budget-list")).with(budget());
        return article;
    }

    private HtmlTag budget() {
        HtmlTag row = new Div("budget-row");
        for (Integer year : project.getYears()) {
            Row budgetRow = (Row) row.createContent(new Row());
            if (this.year != year) {
                budgetRow.clazz("small");
            }
            budgetRow.column("col-md-1").with(String.valueOf(year));
            budgetRow.column("col-md-2").with(statusText(Amount.of(project.getAmount(year)), getProjectStatus(project, year)));
            HtmlTag budgetList = budgetRow.column("col-md-9");
            project.getBudgets(year).forEach(i -> budgetList.with(budgetItem(i)));

        }
        return row;
    }

    private HtmlTag budgetItem(Budget i) {
        Row budgetItemRow = new Row();
        budgetItemRow.column("col-md-12")
                .with(i.getTitle())
                .with(" - ")
                .with(statusText(Amount.of(i.getAmount()), getProjectStatus(i)));
        if(i.getShowComment()) {
            budgetItemRow.column("col-md-12").with(i.getComment()).clazz("muted");
        }
        return budgetItemRow;
    }

    private String getProjectStatus(BudgetProject project, Integer yearBudget) {
        return project.getBudgets(yearBudget).stream().allMatch(Budget::getUseAmountReal) ? ProjectStatus.DONE.clazz() : ProjectStatus.INWORK.clazz();
    }

    private String getProjectStatus(Budget budget) {
        return budget.getUseAmountReal() ? ProjectStatus.DONE.clazz() : ProjectStatus.INWORK.clazz();
    }

    private HtmlTag statusText(String text, String clazz) {
        return new Span("status-text", text).clazz(clazz);
    }
}
