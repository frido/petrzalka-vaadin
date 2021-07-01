package com.example.springboot.page.budget;

import com.example.springboot.criteria.*;
import com.example.springboot.page.grant.GrantItem;
import com.example.springboot.page.grant.GrantItem_;
import com.example.springboot.page.grant.GrantSubject_;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class BudgetService {

    @PersistenceContext
    private EntityManager em;
    private final InterfaceCriteriaBuilder<Budget> defaultOrder = new AttributeOrderCriteriaBuilder<>(Budget_.amountOriginal);
    private final InterfaceCriteriaBuilder<BudgetProject> defaultOrder2 = new AttributeOrderCriteriaBuilder<>(BudgetProject_.title);

    public BudgetByYearDto getBudgetForIndex() {
        List<Budget> budgetList = findByCriteria(new EqualsCriteriaBuilder<>(Budget_.year, 2021), 4);
        return new BudgetByYearDto(2021, budgetList);
    }

    public BudgetByYearDto getBudgetByYear(int year) {
        List<Budget> budgetList = findByCriteria(new EqualsCriteriaBuilder<>(Budget_.year, year), 100);
        return new BudgetByYearDto(year, budgetList);
    }

    public List<BudgetProject> getBudgetProject(int year) {
        List<BudgetProject> budgetProjects = this.findByCriteria2(
                new SubqueryInCriteriaBuilder<>(BudgetProject_.id, Budget_.projectId,
                        new EqualsCriteriaBuilder<>(Budget_.year, year)), 100);
        return budgetProjects;
    }

    private List<Budget> findByCriteria(InterfaceCriteriaBuilder<Budget> criteriaBuilder, int limit) {
        return new CriteriaQueryContext<>(em, Budget.class).apply(criteriaBuilder).apply(defaultOrder).getResultList(limit);
    }

    private List<BudgetProject> findByCriteria2(InterfaceCriteriaBuilder<BudgetProject> criteriaBuilder, int limit) {
        return new CriteriaQueryContext<>(em, BudgetProject.class).apply(criteriaBuilder).apply(defaultOrder2).getResultList(limit);
    }
}
