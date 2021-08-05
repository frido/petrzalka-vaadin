package com.example.application.views.about;

import java.util.Arrays;
import java.util.List;

import com.example.application.old.page.budget.Budget;
import com.example.application.old.page.budget.BudgetProject;
import com.example.application.old.page.project.Program;
import com.example.application.services.EntityService;
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
public class AboutViewMain extends Div {

    Component currentComponent = new Label("Select something");

    public AboutViewMain(@Autowired EntityService service) {
        setSizeFull();
        ComboBox<GridConfig2<?>> comboBox = new ComboBox<>();
        comboBox.setLabel("Entity");
        GridConfig2<Budget> budgetConf = new GridConfig2<>(Budget.class, service.getEm());
        budgetConf.addField(new IntegerFieldFactory<>("id", Budget::getId));
        budgetConf.addField(new StringFieldFactory<>("title", Budget::getTitle));
        budgetConf.addField(new IntegerFieldFactory<>("year", Budget::getYear));
        budgetConf.addField(new EnumFieldFactory<>("program", Program.class, Budget::getProgram));
        GridConfig2<BudgetProject> projectConf = new GridConfig2<>(BudgetProject.class, service.getEm());
        projectConf.addField(new IntegerFieldFactory<>("id", BudgetProject::getId));
        projectConf.addField(new StringFieldFactory<>("title", BudgetProject::getTitle));
        projectConf.addField(new StringFieldFactory<>("description", BudgetProject::getDescription));
        projectConf.addField(new StringFieldFactory<>("url", BudgetProject::getUrl));
        List<GridConfig2<?>> configlist = Arrays.asList(budgetConf, projectConf);

        comboBox.setItems(configlist);
        add(comboBox);

        comboBox.addValueChangeListener(event -> {
            GridConfig2<?> config = event.getValue();
            if (event.getValue() == null) {
            } else {
                // GridBuilder<?> gridBuilder = new GridBuilder<>(config, service);
                Component newView = new EntityView2<>(config, service);
                replace(currentComponent, newView);
                setCurrentComponent(newView);
            }
        });
    }

    public void setCurrentComponent(Component component) {
        this.currentComponent = component;
    }
}
