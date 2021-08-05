package com.example.application.views.about;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.PropertyDescriptor;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.function.ValueProvider;

public abstract class FieldFactory<E> {

    protected final String property;

    protected FieldFactory(String property) {
        this.property = property;
    }

    protected String getName() {
        return property;
    }

    public abstract Component apply(BeanValidationBinder<E> binder);

    public abstract ValueProvider<E, ?> getValueProvider();

    public void applyColumn(Grid<E> grid) {
        grid.addColumn(getValueProvider())
                // TODO configure Renderer based on type
            .setHeader(getName())
            .setAutoWidth(true);
    }

    
}
