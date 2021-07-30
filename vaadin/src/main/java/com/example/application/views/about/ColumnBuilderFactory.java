package com.example.application.views.about;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.function.ValueProvider;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

public class ColumnBuilderFactory {

    public <T> void build(FieldConfig2 config, Grid<T> grid) {
        grid.addColumn(getValueProvider(config))
                // TODO configure Renderer based on type
            .setHeader(config.getHeader())
            .setAutoWidth(true);
    }

    private <T> ValueProvider<T, ?> getValueProvider(FieldConfig2 config) {
        return x -> getValue(x, config);
    }

    private <T> Object getValue(T x, FieldConfig2 config) {
        PropertyDescriptor property = config.getProperty();
        try {
            if(property.getReadMethod() != null) {
                return String.valueOf(property.getReadMethod().invoke(x));
            } else {
                return "NO READ";
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return "error";
    }
}
