package com.example.springboot.criteria;

import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

public class NoCriteriaBuilder<R> implements InterfaceCriteriaBuilder<R>{

    public NoCriteriaBuilder() {

    }

    public void apply(QueryContext<?> q, Root<R> r) {

    }
}
