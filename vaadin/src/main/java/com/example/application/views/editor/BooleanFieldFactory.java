package com.example.application.views.editor;

import java.util.function.Function;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.function.ValueProvider;

public class BooleanFieldFactory<E> extends FieldFactory<E, Boolean> {

    protected BooleanFieldFactory(String property, Function<E, Boolean> getter) {
        super(property, getter);
    }

    @Override
    public Component apply(BeanValidationBinder<E> binder) {
        Checkbox com = new Checkbox(property);
        binder.forField(com).bind(property);
        return com;
        
    }



}
