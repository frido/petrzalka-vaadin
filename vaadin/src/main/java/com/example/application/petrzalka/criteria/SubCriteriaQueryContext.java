package com.example.application.petrzalka.criteria;

import org.apache.commons.lang3.NotImplementedException;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;

import com.example.application.petrzalka.page.grant.GrantSubject;

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
        throw new NotImplementedException("subquery");
    }

    public Root<T> getRoot() {
        return root;
    }

    public SubCriteriaQueryContext<T> apply(InterfaceCriteriaBuilder<T> qcb) {
        qcb.apply(this, root);
        return this;
    }

    public List<GrantSubject> getResultList() {
        throw new NotImplementedException("getResultList");
    }

    @Override
    public void order(List<Order> order) {
        throw new NotImplementedException("getResultList");
    }
}
