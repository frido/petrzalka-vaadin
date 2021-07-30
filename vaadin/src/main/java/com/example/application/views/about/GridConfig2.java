package com.example.application.views.about;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridConfig2<T> {
    private final Class<T> clazz;
    private final List<FieldConfig2> properties = new ArrayList<>();

    GridConfig2(Class<T> clazz) {
        this.clazz = clazz;
        try {
            Arrays.stream(Introspector.getBeanInfo(clazz).getPropertyDescriptors())
                    .filter(x -> !x.getDisplayName().equals("class"))
                    .filter(x -> !x.getDisplayName().equals("projectId"))
                    .forEach(this::addField);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }

    public  Class<T> getClazz() {
        return clazz;
    }

    private void addField(PropertyDescriptor  pd) {
        properties.add(new FieldConfig2(pd));
    }

    public List<FieldConfig2> getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return clazz.getName();
    }
}
