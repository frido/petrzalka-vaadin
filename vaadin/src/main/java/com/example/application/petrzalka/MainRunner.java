package com.example.application.petrzalka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import com.example.application.petrzalka.component.PageWriter;
import com.example.application.petrzalka.page.IndexPage;
import com.example.application.petrzalka.page.Page;
import com.example.application.petrzalka.page.budget.BudgetDetailPage;
import com.example.application.petrzalka.page.budget.BudgetPage;
import com.example.application.petrzalka.page.budget.BudgetService;
import com.example.application.petrzalka.page.grant.GrantPage;
import com.example.application.petrzalka.page.grant.GrantService;
import com.example.application.petrzalka.page.project.ProjectDetailPage;
import com.example.application.petrzalka.page.project.ProjectPage;
import com.example.application.petrzalka.page.project.ProjectService;

@Component
public class MainRunner {

  @Autowired
  BudgetService budgetService;

  @Autowired
  GrantService grantService;

  @Autowired
  ProjectService projectService;

  public void run(Configuration config) throws Exception {
    System.out.println("It Works!");

    PageWriter pw = new PageWriter(config);
    pw.write(new IndexPage(config, budgetService, grantService, projectService));
    pw.write(new ProjectPage(config, projectService));
    pw.write(new GrantPage(config, grantService));
    pw.write(new BudgetPage(config, budgetService));
    projectsPageGenerator(config).forEach(pw::write);
    budgetPageGenerator(config, List.of(2020, 2021)).forEach(pw::write);
    pw.write(new BudgetDetailPage(config, null, budgetService.getAllBudgetProjects()));
  }

  private List<Page> budgetPageGenerator(Configuration config, List<Integer> years) {
    return years.stream().map(year -> new BudgetDetailPage(config, year, budgetService.getAllBudgetProjects()))
        .collect(Collectors.toList());
  }

  private List<Page> projectsPageGenerator(Configuration config) {
    return projectService.getAllProjects().stream().map(p -> new ProjectDetailPage(config, p))
        .collect(Collectors.toList());
  }
}
