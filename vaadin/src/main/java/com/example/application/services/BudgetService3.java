package com.example.application.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

@Service
public class BudgetService3 {

    @PersistenceContext
    EntityManager em;

    BudgetRepository budgetRepository;

    public EntityManager getEm() {
        return em;
    }
}
