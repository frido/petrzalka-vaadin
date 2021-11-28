package com.example.application.petrzalka;

import java.util.List;

import com.example.application.petrzalka.component.PageWriter;
import com.example.application.petrzalka.page.IndexPage;
import com.example.application.petrzalka.page.budget.BudgetDetailPage;
import com.example.application.petrzalka.page.budget.BudgetPage;
import com.example.application.petrzalka.page.budget.BudgetService;
import com.example.application.petrzalka.page.grant.GrantPage;
import com.example.application.petrzalka.page.grant.GrantService;
import com.example.application.petrzalka.page.project.ProjectDetailPage;
import com.example.application.petrzalka.page.project.ProjectPage;
import com.example.application.petrzalka.page.project.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainRunner {

  @Autowired
  BudgetService budgetService;

  @Autowired
  GrantService grantService;

  @Autowired
  ProjectService projectService;

  public void run(Configuration config) {
    PageWriter pw = new PageWriter(config);
    pw.write(new IndexPage(config, budgetService, grantService, projectService));
    pw.write(new ProjectPage(config, projectService));
    pw.write(new GrantPage(config, grantService));
    pw.write(new BudgetPage(config, budgetService));
    projectsPageGenerator(config).forEach(pw::write);
    pw.write(new BudgetDetailPage(config, budgetService.getAllBudgetProjects()));
  }

  private List<ProjectDetailPage> projectsPageGenerator(Configuration config) {
    return projectService.getAllProjects().stream().map(p -> new ProjectDetailPage(config, p))
        .toList();
  }
}
