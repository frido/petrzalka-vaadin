package com.example.application.views.about;

import java.util.Arrays;
import java.util.List;

import com.example.application.old.page.budget.Budget;
import com.example.application.old.page.budget.BudgetProject;
import com.example.application.services.BudgetService3;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("About")
@Route(value = "about/:samplePersonID?/:action?(edit)", layout = MainView.class)
public class AboutViewMain extends Div /*implements BeforeEnterObserver*/ {

    Component currentComponent = new Label("Select something");

    
    public AboutViewMain(@Autowired BudgetService3 service) {
        setSizeFull();
        ComboBox<GridConfig2<?>> comboBox = new ComboBox<>();
        comboBox.setLabel("Entity");
        List<GridConfig2<?>> configlist = Arrays.asList(new GridConfig2<>(Budget.class), new GridConfig2<>(BudgetProject.class));

        comboBox.setItems(configlist);
        add(comboBox);

        comboBox.addValueChangeListener(event -> {
            GridConfig2<?> config = event.getValue();
            if (event.getValue() == null) {
            } else {
                GridBuilder<?> gridBuilder = new GridBuilder<>(config, service.getEm(), service);
                Component newView = new EntityView2<>(gridBuilder, service);
                replace(currentComponent, newView);
                setCurrentComponent(newView);
            }
        });
    }

    public void setCurrentComponent(Component component) {
        this.currentComponent = component;
    }
}
