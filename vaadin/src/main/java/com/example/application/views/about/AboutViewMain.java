package com.example.application.views.about;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.application.old.page.budget.Budget;
import com.example.application.services.BudgetService3;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("About")
@Route(value = "about/:samplePersonID?/:action?(edit)", layout = MainView.class)
public class AboutViewMain extends Div /*implements BeforeEnterObserver*/ {

    
    public AboutViewMain(@Autowired BudgetService3 service) {
        setSizeFull();
        add(new Label("TEST"));
        add(new EntityView<Budget>(new EntityService<Budget,Integer>(service.getEm(), Budget.class), new GridConfig<>(Budget.class)));
    }
}
