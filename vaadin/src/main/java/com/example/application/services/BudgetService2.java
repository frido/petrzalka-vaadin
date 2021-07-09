package com.example.application.services;

import com.example.application.old.page.budget.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class BudgetService2 extends CrudService<Budget, Integer> {

    BudgetRepository budgetRepository;

    public BudgetService2(@Autowired BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    protected JpaRepository<Budget, Integer> getRepository() {
        return budgetRepository;
    }
}
