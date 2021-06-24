package com.example.springboot.criteria;

import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.persistence.metamodel.SingularAttribute;

public class SubqueryInCriteriaBuilder<R, S, I> implements InterfaceCriteriaBuilder<R>{


    private final SingularAttribute<R, Integer> attribute1;
    private final SingularAttribute<S, Integer> attribute2;
    private InterfaceCriteriaBuilder<S> subCriteria;

    public SubqueryInCriteriaBuilder(SingularAttribute<R, Integer> attribute1, SingularAttribute<S, Integer> attribute2, InterfaceCriteriaBuilder<S> subCriteria) {

        this.attribute1 = attribute1; //Grant_.subjectId
        this.attribute2 = attribute2; //GrantSubject_.id
        this.subCriteria = subCriteria;
    }

    public void apply(QueryContext<?> q, Root<R> r) {
        Subquery<Integer> subQuery = q.subquery();
        Root<S> subRoot = subQuery.from(attribute2.getDeclaringType().getJavaType());
        subQuery.select(subRoot.get(attribute2));

        subCriteria.apply(new SubCriteriaQueryContext(q, subQuery), subRoot);

        q.where(r.get(attribute1).in(subQuery));
    }
}
