package com.example.application.views.about;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.provider.DataProvider;

public class GridConfig2<T> {
    private final Class<T> clazz;
    private final List<FieldConfig2> properties = new ArrayList<>();
    private List<PropertyDescriptor> propertiesDescriptors;
    private List<FieldFactory<T>> factories = new ArrayList<>();
    private EntityManager em;

    GridConfig2(Class<T> clazz, EntityManager em) {
        this.clazz = clazz;
        this.em = em;
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

    public void addField(FieldFactory<T> factory) {
        factories.add(factory);
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

    public Grid<T> buildGrid() {
        Grid<T> grid = new Grid<>(clazz, false);
        factories.forEach(factory -> factory.applyColumn(grid));
        grid.setDataProvider(getDataProvider());
        return grid;
    }
    
    private DataProvider<T,?> getDataProvider() {
        return new EntityDataProvider<>(em, clazz);
    }

    public BeanValidationBinder<T> buildBinder() {
        return new BeanValidationBinder<>(clazz);
    }

    public T createEntity() {
        Constructor<T> ctor;
        try {
            ctor = clazz.getConstructor();
            return ctor.newInstance();
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Component> buildComponents(BeanValidationBinder<T> binder) {
        return factories.stream()
            .map(factory -> factory.apply(binder))
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }
}
