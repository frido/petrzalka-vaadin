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
        ComboBox<GridConfig<?>> comboBox = new ComboBox<>();
        comboBox.setLabel("Entity");
        List<GridConfig<?>> departmentList = Arrays.asList(new GridConfig<>(Budget.class, service), new GridConfig<>(BudgetProject.class, service));

        // Choose which property from Department is the presentation value
        // comboBox.setItemLabelGenerator(Department::getName);
        comboBox.setItems(departmentList);
        add(comboBox);

        comboBox.addValueChangeListener(event -> {
            GridConfig<?> value = event.getValue();
            if (event.getValue() == null) {
            } else {
                EntityView newView = new EntityView(new EntityService<>(service, service.getEm(), value.getClazz()), value);
                replace(currentComponent, newView);
                setCurrentComponent(newView);
            }
        });

        // add(new EntityView<Budget>(new EntityService<>(service, service.getEm(), Budget.class), new GridConfig<>(Budget.class, service)));
        // add(new EntityView<BudgetProject>(new EntityService<>(service, service.getEm(), BudgetProject.class), new GridConfig<>(BudgetProject.class, service)));
    }

    public void setCurrentComponent(Component component) {
        this.currentComponent = component;
    }
}
