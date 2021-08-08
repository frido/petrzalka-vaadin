package com.example.application.views.editor;

import java.util.function.Function;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

public class IntegerFieldFactory<E> extends FieldFactory<E, Integer> {

    protected IntegerFieldFactory(String property, Function<E, Integer> getter) {
        super(property, getter);
    }

    @Override
    public Component apply(BeanValidationBinder<E> binder) {
        TextField com = new TextField(getName());
        binder.forField(com).withConverter(new IntegerConverter()).bind(getName());
        return com;
        
    }
}
