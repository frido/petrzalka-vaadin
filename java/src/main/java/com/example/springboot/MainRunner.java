package com.example.springboot;

import com.example.springboot.component.PageWriter;
import com.example.springboot.page.*;
import com.example.springboot.page.budget.BudgetDetailPage;
import com.example.springboot.page.budget.BudgetPage;
import com.example.springboot.page.grant.GrantPage;
import com.example.springboot.page.project.ProjectDetailPage;
import com.example.springboot.page.project.ProjectPage;
import com.example.springboot.page.budget.BudgetService;
import com.example.springboot.page.grant.GrantService;
import com.example.springboot.page.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MainRunner  {

    @Autowired
    BudgetService budgetService;

    @Autowired
    GrantService grantService;

    @Autowired
    ProjectService projectService;

    public void run(String... args) throws Exception {
        System.out.println("It Works!");

        PageWriter pw = new PageWriter(new Configuration());
        pw.write(new IndexPage(budgetService, grantService, projectService));
        pw.write(new ProjectPage(projectService));
        pw.write(new GrantPage(grantService));
        pw.write(new BudgetPage(budgetService));
        projectsPageGenerator().forEach(pw::write);
        budgetPageGenerator(List.of(2020, 2021)).forEach(pw::write);
    }

    private List<Page> budgetPageGenerator(List<Integer> years) {
        return years.stream().map(year -> new BudgetDetailPage(budgetService.getBudgetByYear(year))).collect(Collectors.toList());
    }

    private List<Page> projectsPageGenerator() {
        return projectService.getAllProjects().stream().map(ProjectDetailPage::new).collect(Collectors.toList());
    }
}
