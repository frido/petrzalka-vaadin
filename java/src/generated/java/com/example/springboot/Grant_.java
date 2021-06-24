package com.example.springboot;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Grant.class)
public abstract class Grant_ {

	public static volatile SingularAttribute<Grant, BigDecimal> amount;
	public static volatile SingularAttribute<Grant, Integer> year;
	public static volatile SingularAttribute<Grant, GrantSubject> subject;
	public static volatile SingularAttribute<Grant, Integer> id;
	public static volatile SingularAttribute<Grant, String> detail;

	public static final String AMOUNT = "amount";
	public static final String YEAR = "year";
	public static final String SUBJECT = "subject";
	public static final String ID = "id";
	public static final String DETAIL = "detail";

}

