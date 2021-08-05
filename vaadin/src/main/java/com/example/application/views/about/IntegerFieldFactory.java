package com.example.application.views.about;

import java.util.function.Function;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.function.ValueProvider;

public class IntegerFieldFactory<E> extends FieldFactory<E> {

    private Function<E, Integer> getter;

    protected IntegerFieldFactory(String property, Function<E, Integer> getter) {
        super(property);
        this.getter = getter;
    }

    @Override
    public Component apply(BeanValidationBinder<E> binder) {
        TextField com = new TextField(getName());
        binder.forField(com).withConverter(new IntegerConverter()).bind(getName());
        return com;
        
    }

    @Override
    public ValueProvider<E, ?> getValueProvider() {
        return x -> getter.apply(x);
    }

    

}
