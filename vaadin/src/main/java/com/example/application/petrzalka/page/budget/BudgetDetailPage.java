package com.example.application.petrzalka.page.budget;

import java.util.List;

import com.example.application.petrzalka.Configuration;
import com.example.application.petrzalka.component.PageHeader;
import com.example.application.petrzalka.component.Title;
import com.example.application.petrzalka.html.HtmlTag;
import com.example.application.petrzalka.html.Section;
import com.example.application.petrzalka.page.BasePage;

public class BudgetDetailPage extends BasePage {

  private final Integer year;
  private final List<BudgetProject> budgetProject;

  public BudgetDetailPage(Configuration conf, Integer year, List<BudgetProject> budgetProject) {
    super(conf);
    this.year = year;
    this.budgetProject = budgetProject;
  }

  @Override
  public void applyContent(HtmlTag body) {
    body.with(budgets());
  }

  private HtmlTag budgets() {
    return new Section().with(new Title("Investičný plán "))
        .with(new ArticleBudgetListComponent2(budgetProject));
  }

  @Override
    public String getFileName() {
        return PageHeader.BUDGET + "index";
    }
}
