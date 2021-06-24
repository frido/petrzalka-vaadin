package com.example.springboot.page;

import com.example.springboot.page.budget.ArticleBudgetListComponent;
import com.example.springboot.page.project.ArticleProjectListComponent;
import com.example.springboot.page.grant.ArticleSportListComponent;
import com.example.springboot.component.Title;
import com.example.springboot.html.HtmlTag;
import com.example.springboot.html.Section;
import com.example.springboot.page.grant.GrantCategory;
import com.example.springboot.page.budget.BudgetService;
import com.example.springboot.page.grant.GrantService;
import com.example.springboot.page.project.ProjectService;

public class IndexPage extends BasePage {

    private final BudgetService budgetService;
    private final GrantService grantService;
    private final ProjectService projectService;

    public IndexPage(BudgetService budgetService, GrantService grantService, ProjectService projectService) {
        this.budgetService = budgetService;
        this.grantService = grantService;
        this.projectService = projectService;
    }

    public void applyContent(HtmlTag body) {
        body.with(projects())
                .with(budgets())
                .with((grant(GrantCategory.SPORT)))
                .with((grant(GrantCategory.OZ)));
    }

    private HtmlTag projects() {
        return new Section()
                .with(new Title("Projekty"))
                .with(new ArticleProjectListComponent(projectService.getProjectForIndex()));
    }

    private HtmlTag budgets() {
        return new Section()
                .with(new Title("Investičný plán 2021"))
                .with(new ArticleBudgetListComponent(budgetService.getBudgetForIndex(), false));
    }

    private HtmlTag grant(GrantCategory grantCategory) {
        return new Section()
                .with(new Title(grantCategory.getLabel()))
                .with(new ArticleSportListComponent(grantService.getGrantTreeByCategory(grantCategory, 4)));
    }

    @Override
    public String getFileName() {
        return "index";
    }
}
