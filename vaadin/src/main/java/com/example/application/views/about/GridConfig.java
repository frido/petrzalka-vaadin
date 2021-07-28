package com.example.application.views.about;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.application.old.page.budget.Budget;
import com.example.application.old.page.budget.BudgetDto;
import com.example.application.services.BudgetService3;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.binder.BeanValidationBinder;

public class GridConfig<T> {
    private Class<T> clazz;
    private List<FieldConfig<T>> properties = new ArrayList();
    private BeanValidationBinder<T> binder;
    BudgetService3 service;
    
    GridConfig(Class<T> clazz, BudgetService3 service) {
        this.clazz = clazz;
        this.service = service;
        binder = new BeanValidationBinder<>(clazz);
        try {
            Arrays.stream(Introspector.getBeanInfo(clazz).getPropertyDescriptors())
                .filter(x -> !x.getDisplayName().equals("class"))
                .filter(x -> !x.getDisplayName().equals("projectId"))
                .forEach(this::addField);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }

    public Class<T> getClazz() {
        return clazz;
    }

    private void addField(PropertyDescriptor  pd) {
        properties.add(new FieldConfig<T>(pd, binder, service));
    }

    public List<Component> getComponents() {
        return properties.stream().map(FieldConfig::getComponent).collect(Collectors.toList());
    }

    public List<FieldConfig<T>>  getProperties() {
        return properties;
    }

    public BeanValidationBinder<T> getBinder() {
        return binder;
    }

    public static void main(String[] args) {
        GridConfig<Budget> c = new GridConfig<>(Budget.class, null);
    }
}
