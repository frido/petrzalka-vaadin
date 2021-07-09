package com.example.application.old.criteria;

import javax.persistence.criteria.Root;

public class NoCriteriaBuilder<R> implements InterfaceCriteriaBuilder<R> {

    public NoCriteriaBuilder() {

    }

    public void apply(QueryContext<?> q, Root<R> r) {

    }
}
