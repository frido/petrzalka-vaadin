package com.example.application.views.about;

import java.beans.PropertyDescriptor;


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
