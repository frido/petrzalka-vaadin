package com.example.application.petrzalka.criteria;

import javax.persistence.criteria.Root;

public class NoCriteriaBuilder<R> implements InterfaceCriteriaBuilder<R> {

    public NoCriteriaBuilder() {
        // nothing to do
    }

    public void apply(QueryContext<?> q, Root<R> r) {
        // nothing to do
    }
}
