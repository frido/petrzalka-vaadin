package com.example.springboot.criteria;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class CriteriaQueryContext<T> implements QueryContext<T> {
    private final CriteriaBuilder cb;
    private final CriteriaQuery<T> query;
    private final Root<T> root;
    private final EntityManager em;

    public CriteriaQueryContext(EntityManager em, Class<T> clazz) {
        this.em = em;
        this.cb = em.getCriteriaBuilder();
        this.query = cb.createQuery(clazz);
        this.root = query.from(clazz);
    }

    public CriteriaBuilder getCb() {
        return cb;
    }

    @Override
    public EntityManager getEm() {
        return em;
    }

    @Override
    public Subquery<Integer> subquery() {
        return query.subquery(Integer.class);
    }

    public Root<T> getRoot() {
        return root;
    }

    public CriteriaQueryContext<T> apply(InterfaceCriteriaBuilder<T> qcb) {
        qcb.apply(this, root);
        return this;
    }

    public List<T> getResultList(int limit) {
        TypedQuery<T> q = em.createQuery(query);
        q.setMaxResults(limit);
        return q.getResultList();
    }

    @Override
    public void where(Expression<Boolean> restriction) {
        query.where(restriction);
    }

    @Override
    public void order(List<Order> order) {
        query.orderBy(order);
    }
}
