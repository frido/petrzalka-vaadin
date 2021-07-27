package com.example.application.views.about;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;

import com.example.application.old.page.budget.Budget;
import com.example.application.services.BudgetService3;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.provider.QuerySortOrder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.vaadin.artur.spring.dataprovider.FilterablePageableDataProvider;

public class EntityService<T, F> extends FilterablePageableDataProvider<T, F>{

    private EntityManager em;
    Class<T> clazz;
    BudgetService3 service;

    public EntityService(BudgetService3 service, EntityManager em, Class<T> clazz) {
        this.em = em;
        this.clazz = clazz;
        this.service = service;
    }

    @Override
    protected Page<T> fetchFromBackEnd(Query<T, F> arg0, Pageable arg1) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> rootEntry = cq.from(clazz);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = em.createQuery(all);
        return new PageImpl<>(allQuery.getResultList());
    }

    @Override
    protected List<QuerySortOrder> getDefaultSortOrders() {
        return Collections.emptyList();
    }

    @Override
    protected int sizeInBackEnd(Query<T, F> query) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        cq.select(cb.count(cq.from(clazz)));
        Long size =  em.createQuery(cq).getSingleResult();
        return size.intValue();
    }

    public Optional<T> get(F id) {
        return Optional.ofNullable(em.find(clazz, id));
    }

    public T save(T entity) {
        return service.save(entity);
    }

    
}