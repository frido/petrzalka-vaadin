package com.example.application.views.about;

import java.util.function.Function;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.PropertyDescriptor;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.function.ValueProvider;

public abstract class FieldFactory<E, T> {

    protected final String property;
    private Function<E, T> getter;

    protected FieldFactory(String property, Function<E, T> getter) {
        this.property = property;
        this.getter = getter;
    }

    protected String getName() {
        return property;
    }

    public abstract Component apply(BeanValidationBinder<E> binder);

    public ValueProvider<E, T> getValueProvider() {
        return x -> getter.apply(x);
    }

    public void applyColumn(Grid<E> grid) {
        grid.addColumn(getValueProvider())
                // TODO configure Renderer based on type
            .setHeader(getName())
            .setAutoWidth(true);
    }

    
}
