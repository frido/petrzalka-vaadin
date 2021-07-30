package com.example.application.views.about;

import com.example.application.services.BudgetService3;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

import javax.persistence.Entity;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FieldBuilderFactory {
    private final BudgetService3 service;

    public FieldBuilderFactory(BudgetService3 service) {

        this.service = service;
    }

    public <E> Component build(FieldConfig2 propertyConfig, BeanValidationBinder<E> binder) {
        PropertyDescriptor property = propertyConfig.getProperty();
        if(property.getWriteMethod() == null) {
            return null;
        }
        if(property.getPropertyType().isEnum()) {
            return createEnumField(binder, property);
        } else if (property.getPropertyType().isAssignableFrom(BigDecimal.class)) {
            return createBigDecimalField(binder, property);
        } else if (property.getPropertyType().isAssignableFrom(Integer.class) || property.getPropertyType().getSimpleName().equals("int")) {
            return createIntegerField(binder, property);
        } else if (property.getPropertyType().isAssignableFrom(String.class)){
            return createStringField(binder, property);
        } else if (property.getPropertyType().isAssignableFrom(Boolean.class)){
            return createCheckboxField(binder, property);
        } else if (property.getPropertyType().isAnnotationPresent(Entity.class)) {
            return createEntityField(binder, property);
        }
        return null;
    }

    private <E> ComboBox<E> createEntityField(BeanValidationBinder<E> binder, PropertyDescriptor property) {
        Class<E> type = (Class<E>) property.getPropertyType();
        ComboBox<E> program = new ComboBox<>(getName(property));
        List<E> list = service.findAll(type).stream().map(type::cast).collect(Collectors.toList());
        program.setItems(list);
        binder.forField(program).bind(getName(property));
        return program;
    }

    private <E> Checkbox createCheckboxField(BeanValidationBinder<E> binder, PropertyDescriptor property) {
        Checkbox com = new Checkbox(getName(property));
        binder.forField(com).bind(getName(property));
        return com;
    }

    private <E> TextField createStringField(BeanValidationBinder<E> binder, PropertyDescriptor property) {
        TextField com = new TextField(getName(property));
        binder.forField(com).bind(getName(property));
        return com;
    }

    private <E> TextField createIntegerField(BeanValidationBinder<E> binder, PropertyDescriptor property) {
        TextField com = new TextField(getName(property));
        binder.forField(com).withConverter(new IntegerConverter()).bind(getName(property));
        return com;
    }

    private <E> TextField createBigDecimalField(BeanValidationBinder<E> binder, PropertyDescriptor property) {
        TextField com = new TextField(getName(property));
        binder.forField(com).withConverter(new DecimalConverter()).bind(getName(property));
        return com;
    }

    private <E> ComboBox<E> createEnumField(BeanValidationBinder<E> binder, PropertyDescriptor property) {
        Class<E> type = (Class<E>) property.getPropertyType();
        ComboBox<E> program = new ComboBox<>(getName(property));
        List<E> list = Arrays.stream(type.getEnumConstants()).map(x -> type.cast(x)).collect(Collectors.toList());
        program.setItems(list);
        binder.forField(program).bind(getName(property));
        return program;
    }

    public String getName(PropertyDescriptor property) {
        return property.getDisplayName();
    }
}
