package com.example.springboot.page.budget;

import com.example.springboot.Configuration;
import com.example.springboot.component.PageHeader;
import com.example.springboot.component.Title;
import com.example.springboot.html.HtmlTag;
import com.example.springboot.html.Section;
import com.example.springboot.page.BasePage;

import java.util.List;
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
