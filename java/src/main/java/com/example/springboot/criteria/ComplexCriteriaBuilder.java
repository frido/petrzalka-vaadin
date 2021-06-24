package com.example.springboot.criteria;

import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

public class ComplexCriteriaBuilder<R> implements InterfaceCriteriaBuilder<R>{

    private final List<InterfaceCriteriaBuilder<R>> criteriaList;

    public ComplexCriteriaBuilder(List<InterfaceCriteriaBuilder<R>> criteriaList) {
        this.criteriaList = criteriaList;
    }

    public void apply(QueryContext<?> q, Root<R> r) {
        criteriaList.forEach(c -> c.apply(q, r));
    }
}
