package com.example.springboot.criteria;

import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

public class EqualsCriteriaBuilder<R> implements InterfaceCriteriaBuilder<R>{

    private final SingularAttribute<R, ?> attribute;
    private final Object value;

    public EqualsCriteriaBuilder(SingularAttribute<R, ?> attribute, Object value) {

        this.attribute = attribute;
        this.value = value;
    }

    public void apply(QueryContext<?> q, Root<R> r) {
        q.where(q.getCb().equal(r.get(attribute), value));
    }
}
