package com.example.application.views.editor;

import java.util.function.Function;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

public class StringFieldFactory<E> extends FieldFactory<E, String> {

    protected StringFieldFactory(String property, Function<E, String> getter) {
        super(property, getter);
    }

    @Override
    public Component apply(BeanValidationBinder<E> binder) {
        TextField com = new TextField(getName());
        binder.forField(com).bind(getName());
        return com;
    }
}
