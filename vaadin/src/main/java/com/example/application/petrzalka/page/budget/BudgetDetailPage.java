package com.example.application.petrzalka.page.budget;

import java.util.List;

import com.example.application.petrzalka.Configuration;
import com.example.application.petrzalka.component.PageHeader;
import com.example.application.petrzalka.component.Title;
import com.example.application.petrzalka.html.HtmlTag;
import com.example.application.petrzalka.html.Section;
import com.example.application.petrzalka.page.BasePage;
// TODO: tu som skoncil
// Novy BudgetProject
// Novy ArticleBudgetListComponent2 ktory celkom dobre zobrazuje BudgetProject
// TODO - aplikovat ArticleBudgetListComponent2 so spravnym rokom v tejto detailovej stranke ale aj v BudgetPage nejakym sposobom
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
        return new Section()
                .with(new Title("Investičný plán " + year))
                .with(new ArticleBudgetListComponent2(budgetProject, year));
    }

    @Override
    public String getFileName() {
        return PageHeader.BUDGET + year + "/index";
    }
}
