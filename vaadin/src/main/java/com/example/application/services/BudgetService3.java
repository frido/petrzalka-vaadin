package com.example.application.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.example.application.old.page.budget.Budget;

import org.springframework.stereotype.Service;

@Service
public class BudgetService3 {

    @PersistenceContext
    EntityManager em;

    BudgetRepository budgetRepository;

    public EntityManager getEm() {
        return em;
    }

    @Transactional // TODO: nerozumiem na co to tu je
    public Budget save(Budget entity) {
        Budget response = em.merge(entity);
        return response;
    }
}
