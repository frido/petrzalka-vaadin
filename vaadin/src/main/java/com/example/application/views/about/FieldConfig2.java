package com.example.application.views.about;

import com.example.application.services.BudgetService3;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.function.ValueProvider;

import javax.persistence.Entity;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class FieldConfig2 {

    private final PropertyDescriptor property;

    public FieldConfig2(PropertyDescriptor pd) {
        this.property = pd;
    }

    public PropertyDescriptor getProperty() {
        return property;
    }

    public String getHeader() {
        return property.getDisplayName();
    }
}
