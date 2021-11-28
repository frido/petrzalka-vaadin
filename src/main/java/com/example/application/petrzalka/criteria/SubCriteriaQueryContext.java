package com.example.application.petrzalka.criteria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import com.example.application.petrzalka.page.grant.GrantSubject;

public class SubCriteriaQueryContext<T> implements QueryContext<T> {
    private final CriteriaBuilder cb;
    private final Subquery<Integer> subquery;
    private final Root<T> root;
    private final EntityManager em;

    public <U> SubCriteriaQueryContext(QueryContext<T> q, Subquery<Integer> subquery) {
        cb = q.getCb();
        this.subquery = subquery;
        this.root = q.getRoot();
        em = q.getEm();
    }


    @Override
    public void where(Expression<Boolean> restriction) {
        subquery.where(restriction);
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
        throw new RuntimeException("subquery");
    }

    public Root<T> getRoot() {
        return root;
    }

    public SubCriteriaQueryContext<T> apply(InterfaceCriteriaBuilder<T> qcb) {
        qcb.apply(this, root);
        return this;
    }

    public List<GrantSubject> getResultList() {
        throw new RuntimeException("getResultList");
    }

    @Override
    public void order(List<Order> order) {
        throw new RuntimeException("getResultList");
    }
}
