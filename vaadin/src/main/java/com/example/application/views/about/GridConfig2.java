package com.example.application.views.about;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class GridConfig2<T> {
    private final Class<T> clazz;
    private final List<FieldConfig2> properties = new ArrayList<>();
    private List<PropertyDescriptor> propertiesDescriptors;

    GridConfig2(Class<T> clazz) {
        this.clazz = clazz;
        try {
            this.propertiesDescriptors = Arrays.asList(Introspector.getBeanInfo(clazz).getPropertyDescriptors());
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }

    public  Class<T> getClazz() {
        return clazz;
    }

    public void addField(String name) {
        getPropertyDescriptorByName(name).map(FieldConfig2::new).ifPresent(properties::add);
    }

    public List<FieldConfig2> getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return clazz.getName();
    }

    private Optional<PropertyDescriptor> getPropertyDescriptorByName(String name) {
        return propertiesDescriptors.stream().filter(x -> x.getDisplayName().equalsIgnoreCase(name)).findFirst();
    }
}
