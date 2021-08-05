package com.example.application.views.about;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("About")
@Route(value = "about/:samplePersonID?/:action?(edit)", layout = MainView.class)
public class AboutViewMain extends Div {

    Component currentComponent = new Label("Select something");
    private transient UserConfiguration userConfiguration;

    public AboutViewMain(@Autowired UserConfiguration userConfiguration) {
        this.userConfiguration = userConfiguration;
        setSizeFull();
        
        ComboBox<UIFactory<?>> comboBox = new ComboBox<>();
        comboBox.setItems(userConfiguration.getConfiguration());
        comboBox.addValueChangeListener(this::changeView);
        add(comboBox);
    }

    public void setCurrentComponent(Component component) {
        this.currentComponent = component;
    }

    private void changeView(ComponentValueChangeEvent<ComboBox<UIFactory<?>>, UIFactory<?>> event) {
        UIFactory<?> config = event.getValue();
            if (event.getValue() != null) {
                EntityView2<?> newView = new EntityView2<>(config, userConfiguration);
                newView.setHeightFull();
                replace(currentComponent, newView);
                setCurrentComponent(newView);  
            }
    }
}
