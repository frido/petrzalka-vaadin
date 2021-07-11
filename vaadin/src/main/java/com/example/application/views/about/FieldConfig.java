package com.example.application.views.about;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.function.ValueProvider;


public class FieldConfig<T> {

    private PropertyDescriptor property;
    private Component component;

    public FieldConfig(PropertyDescriptor pd, BeanValidationBinder<T> binder) {
        this.property = pd;
        this.component = getField(binder);
    }
    
    public ValueProvider<T, ?> getValueProvider() {
        return x -> getValue(x);
    }

    public String getName() {
        return property.getDisplayName();
    }

    private String getValue(T x) {
        try {
            return String.valueOf(property.getReadMethod().invoke(x));
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return "error";
    }

    public Component getComponent() {
        return component;
    }

    private <E> Component getField(BeanValidationBinder<T> binder) {
        if(property.getPropertyType().isEnum()) {
            Class<E> type = (Class<E>) property.getPropertyType();
            ComboBox<E> program = new ComboBox<>(getName());
            List<E> list = Arrays.stream(type.getEnumConstants()).map(x -> type.cast(x)).collect(Collectors.toList());
            program.setItems(list);
            binder.forField(program).bind(getName());
            return program;
        } else if (property.getPropertyType().isAssignableFrom(BigDecimal.class)) {
            TextField com = new TextField(getName());
            binder.forField(com).withConverter(new DecimalConverter()).bind(getName());
            return com;
        } else if (property.getPropertyType().isAssignableFrom(Integer.class) || property.getPropertyType().getSimpleName().equals("int")) {
            TextField com = new TextField(getName());
            binder.forField(com).withConverter(new IntegerConverter()).bind(getName());
            return com;
        } else if (property.getPropertyType().isAssignableFrom(String.class)){
            TextField com = new TextField(getName());
            binder.forField(com).bind(getName());
            return com;
        } else if (property.getPropertyType().isAssignableFrom(Boolean.class)){
            Checkbox com = new Checkbox(getName());
            binder.forField(com).bind(getName());
            return com;
        }
        return new TextField();
    }

    public <E> HasValue<?, E> getField() {
        if(property.getPropertyType().isEnum()) {
            Class<E> type = (Class<E>) property.getPropertyType();
            ComboBox<E> program = new ComboBox<>(getName());
            List<E> list = Arrays.stream(type.getEnumConstants()).map(x -> type.cast(x)).collect(Collectors.toList());
            program.setItems(list);
            return program;
        } else if (property.getPropertyType().isAssignableFrom(BigDecimal.class)) {
            return (HasValue<?, E>) new TextField();
        } else if (property.getPropertyType().isAssignableFrom(Integer.class) || property.getPropertyType().getSimpleName().equals("int")) {
            return (HasValue<?, E>) new TextField();
        } else if (property.getPropertyType().isAssignableFrom(String.class)){
            return (HasValue<?, E>) new TextField();
        } else if (property.getPropertyType().isAssignableFrom(Boolean.class)){
            return (HasValue<?, E>) new Checkbox();
        }
        return null;
        
    }
    
}
