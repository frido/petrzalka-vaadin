package com.example.application.views.about;

import java.math.BigDecimal;
import java.util.function.Function;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

public class DecimalFieldFactory<E> extends FieldFactory<E, BigDecimal> {

    protected DecimalFieldFactory(String property, Function<E, BigDecimal> getter) {
        super(property, getter);
    }

    @Override
    public Component apply(BeanValidationBinder<E> binder) {
        TextField com = new TextField(getName());
        binder.forField(com).withConverter(new DecimalConverter()).bind(getName());
        return com;
        
    }
}
