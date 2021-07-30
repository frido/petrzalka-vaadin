package com.example.application.views.about;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.example.application.services.BudgetService3;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.provider.QuerySortOrder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.vaadin.artur.spring.dataprovider.FilterablePageableDataProvider;

public class EntityDataProvider<T, F> extends FilterablePageableDataProvider<T, F>{

    private transient EntityManager em;
    private Class<T> clazz;

    public EntityDataProvider(EntityManager em, Class<T> clazz) {
        this.em = em;
        this.clazz = clazz;
    }

    @Override
    protected Page<T> fetchFromBackEnd(Query<T, F> arg0, Pageable pagable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> rootEntry = cq.from(clazz);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = em.createQuery(all);
        allQuery.setMaxResults(pagable.getPageSize());
        allQuery.setFirstResult((int) pagable.getOffset());
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
//
//    public T save(T entity) {
//        return service.save(entity);
//    }

    
}