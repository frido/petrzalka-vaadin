package com.example.springboot.criteria;

import com.example.springboot.page.grant.GrantSubject;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

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
    public void where(Expression restriction) {
        subquery.where(restriction);
    }

    public CriteriaBuilder getCb() {
        return cb;
    }


    @Override
    public EntityManager getEm() {
        return null;
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
