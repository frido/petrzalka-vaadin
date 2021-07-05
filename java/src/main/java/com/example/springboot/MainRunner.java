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

    public void run(Configuration config) throws Exception {
        System.out.println("It Works!");

        PageWriter pw = new PageWriter(config);
        pw.write(new IndexPage(config, budgetService, grantService, projectService));
        pw.write(new ProjectPage(config, projectService));
        pw.write(new GrantPage(config, grantService));
        pw.write(new BudgetPage(config, budgetService));
        projectsPageGenerator(config).forEach(pw::write);
        budgetPageGenerator(config, List.of(2020, 2021)).forEach(pw::write);
    }

    private List<Page> budgetPageGenerator(Configuration config, List<Integer> years) {
        return years.stream().map(year -> new BudgetDetailPage(config, year, budgetService.getBudgetProject(year))).collect(Collectors.toList());
    }

    private List<Page> projectsPageGenerator(Configuration config) {
        return projectService.getAllProjects().stream().map(p -> new ProjectDetailPage(config, p)).collect(Collectors.toList());
    }
}
