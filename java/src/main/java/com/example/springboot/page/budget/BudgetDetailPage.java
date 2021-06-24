package com.example.springboot.page.budget;

import com.example.springboot.component.PageHeader;
import com.example.springboot.component.Title;
import com.example.springboot.html.HtmlTag;
import com.example.springboot.html.Section;
import com.example.springboot.page.BasePage;

public class BudgetDetailPage extends BasePage {

    private final BudgetByYearDto budgetByYear;

    public BudgetDetailPage(BudgetByYearDto budgetByYear) {
        this.budgetByYear = budgetByYear;
    }

    @Override
    public void applyContent(HtmlTag body) {
        body.with(budgets());
    }

    private HtmlTag budgets() {
        return new Section()
                .with(new Title("Investičný plán " + budgetByYear.getYear()))
                .with(new ArticleBudgetListComponent(budgetByYear, true));
    }

    @Override
    public String getFileName() {
        return PageHeader.BUDGET + budgetByYear.getYear() + "/index";
    }
}
