package com.example.application.views.about;

import java.util.function.Function;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.function.ValueProvider;

public class StringFieldFactory<E> extends FieldFactory<E> {

    private Function<E, String> getter;

    protected StringFieldFactory(String property, Function<E, String> getter) {
        super(property);
        this.getter = getter;
    }

    @Override
    public Component apply(BeanValidationBinder<E> binder) {
        TextField com = new TextField(getName());
        binder.forField(com).bind(getName());
        return com;
    }

    @Override
    public ValueProvider<E, ?> getValueProvider() {
        return x -> getter.apply(x);
    }

}
