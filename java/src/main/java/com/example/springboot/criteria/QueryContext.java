package com.example.springboot.criteria;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

public interface QueryContext<T> {
    void where(Expression<Boolean> restriction);

    CriteriaBuilder getCb();

    EntityManager getEm();

    Subquery<Integer> subquery();

    Root<T> getRoot();

    void order(List<Order> order);
}
