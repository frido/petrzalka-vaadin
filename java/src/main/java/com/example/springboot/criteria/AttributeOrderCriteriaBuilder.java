package com.example.springboot.criteria;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AttributeOrderCriteriaBuilder<R> implements InterfaceCriteriaBuilder<R>{

    private final List<SingularAttribute<R, ?>> attributes;

    public AttributeOrderCriteriaBuilder(SingularAttribute<R, ?> attribute) {
        this.attributes = Collections.singletonList(attribute);
    }

    public AttributeOrderCriteriaBuilder(List<SingularAttribute<R, ?>> attributes) {
        this.attributes = attributes;
    }

    public void apply(QueryContext<?> q, Root<R> r) {
        List<Order> orders = attributes.stream().map(a -> q.getCb().desc(r.get(a))).collect(Collectors.toList());
        q.order(orders);
    }
}
