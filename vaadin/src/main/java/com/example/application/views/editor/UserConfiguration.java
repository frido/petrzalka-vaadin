package com.example.application.views.editor;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import com.example.application.petrzalka.page.budget.Budget;
import com.example.application.petrzalka.page.budget.BudgetProject;
import com.example.application.petrzalka.page.budget.BudgetStatus;
import com.example.application.petrzalka.page.project.Program;
import com.example.application.services.EntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConfiguration {

    @Autowired
    EntityService service;

    List<UIFactory<?>> getConfiguration() {
        return Arrays.asList(
          getBudget(),
          getBudgetProject()  
        );
    }

    private UIFactory<?> getBudget() {
        UIFactory<Budget> budgetConf = new UIFactory<>(Budget.class, service.getEm());
        budgetConf.addField(new IntegerFieldFactory<>("id", Budget::getId));
        budgetConf.addField(new StringFieldFactory<>("title", Budget::getTitle));
        budgetConf.addField(new IntegerFieldFactory<>("year", Budget::getYear));
        budgetConf.addField(new EnumFieldFactory<>("program", Program.class, Budget::getProgram));
        budgetConf.addField(new EnumFieldFactory<>("status", BudgetStatus.class, Budget::getStatus));
        budgetConf.addField(new DecimalFieldFactory<>("amountOriginal", Budget::getAmountOriginal));
        budgetConf.addField(new StringFieldFactory<>("comment", Budget::getComment));
        budgetConf.addField(new BooleanFieldFactory<>("useAmountReal", Budget::getUseAmountReal));
        budgetConf.addField(new BooleanFieldFactory<>("showComment", Budget::getShowComment));
        return budgetConf; 
    }

    private UIFactory<?> getBudgetProject() {
        UIFactory<BudgetProject> projectConf = new UIFactory<>(BudgetProject.class, service.getEm());
        projectConf.addField(new IntegerFieldFactory<>("id", BudgetProject::getId));
        projectConf.addField(new StringFieldFactory<>("title", BudgetProject::getTitle));
        projectConf.addField(new StringFieldFactory<>("description", BudgetProject::getDescription));
        projectConf.addField(new StringFieldFactory<>("url", BudgetProject::getUrl));
        return projectConf; 
    }

    @Transactional
    public <T> T save(T entity) {
        return service.save(entity);
    }
    
}
