package com.example.springboot;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Budget.class)
public abstract class Budget_ {

	public static volatile SingularAttribute<Budget, BigDecimal> amountUpdated;
	public static volatile SingularAttribute<Budget, BigDecimal> amountOriginal;
	public static volatile SingularAttribute<Budget, Integer> year;
	public static volatile SingularAttribute<Budget, BigDecimal> amountReal;
	public static volatile SingularAttribute<Budget, String> comment;
	public static volatile SingularAttribute<Budget, Integer> id;
	public static volatile SingularAttribute<Budget, Integer> program;
	public static volatile SingularAttribute<Budget, String> title;
	public static volatile SingularAttribute<Budget, String> status;

	public static final String AMOUNT_UPDATED = "amountUpdated";
	public static final String AMOUNT_ORIGINAL = "amountOriginal";
	public static final String YEAR = "year";
	public static final String AMOUNT_REAL = "amountReal";
	public static final String COMMENT = "comment";
	public static final String ID = "id";
	public static final String PROGRAM = "program";
	public static final String TITLE = "title";
	public static final String STATUS = "status";

}

