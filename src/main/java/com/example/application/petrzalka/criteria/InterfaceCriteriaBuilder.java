package com.example.application.petrzalka.criteria;

import javax.persistence.criteria.Root;

public interface InterfaceCriteriaBuilder<R> {
    public void apply(QueryContext<?> q, Root<R> r);
}
