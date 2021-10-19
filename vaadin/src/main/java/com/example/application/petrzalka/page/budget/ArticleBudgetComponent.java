package com.example.application.petrzalka.page.budget;

import com.example.application.petrzalka.component.Amount;
import com.example.application.petrzalka.html.*;
import com.example.application.petrzalka.page.project.ProjectStatus;

public class ArticleBudgetComponent extends HtmlTag {

  private final BudgetProject project;

  public ArticleBudgetComponent(BudgetProject project) {
    super("div");
    this.project = project;
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
    row.column("col-md-2 text-right").with(statusText(Amount.of(project.getAmountAll()), ProjectStatus.INWORK.clazz()));
    article.createContent(budget());
    return article;
  }

  private HtmlTag budget() {
    Div budgetRow = new Div("budget-list");
    for (Integer projectYear : project.getYears()) {
      HtmlTag row = budgetRow.createContent(new Div("budget-row"));
      Row yearRow = (Row) row.createContent(new Row());
      yearRow.clazz("budget-item-year");
      yearRow.column("col-md-10").createContent(new H(4)).with("v roku " + projectYear);
      yearRow.column("col-md-2 text-right")
          .with(statusText(Amount.of(project.getAmount(projectYear)), getStatusClass(project.isAnyInWork(projectYear))));

      project.getBudgets(projectYear).forEach(i -> row.with(budgetItem(i)));

    }
    return budgetRow;
  }

  private String getStatusClass(boolean isAnyInWork) {
    return isAnyInWork ? ProjectStatus.INWORK.clazz() : ProjectStatus.DONE.clazz();
  }

  private HtmlTag budgetItem(Budget i) {
    Row budgetItemRow = new Row();
    budgetItemRow.clazz("budget-item");
    budgetItemRow.column("col-md-10").with(i.getTitle());
    budgetItemRow.column("col-md-2 text-right").with(statusText(Amount.of(i.getAmount()), getProjectStatus(i)));
    if (i.getShowComment() && false) {
      budgetItemRow.column("col-md-12").with(i.getComment()).clazz("muted");
    }
    return budgetItemRow;
  }

  private String getProjectStatus(Budget budget) {
    // return budget.getUseAmountReal() ? ProjectStatus.DONE.clazz() : ProjectStatus.INWORK.clazz();
    return budget.getStatus().getClazz();
  }

  private HtmlTag statusText(String text, String clazz) {
    return new Span("status-text", text).clazz(clazz);
  }
}
