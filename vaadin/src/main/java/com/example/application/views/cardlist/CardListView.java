package com.example.application.views.cardlist;

import com.example.application.knowledge.CustomInterceptorImpl;
import com.example.application.knowledge.Department;
import com.example.application.knowledge.MessageQueue;
import com.example.application.knowledge.Person;
import com.example.application.knowledge.Team;
import com.example.application.old.page.budget.Budget;
import com.example.application.services.EntityService;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "card-list", layout = MainView.class)
@PageTitle("Card List")
public class CardListView extends Div {

    private EntityService service;
    private MessageQueue messageQueue = MessageQueue.getInstance();
    private VerticalLayout infoPanel;
    private Person personEntity;
    private Department departmentEntity;
    private Team teamEntity;
    private Label personLabel = new Label();
    private Label departmentLabel = new Label();
    private Label teamLabel = new Label();

    public CardListView(@Autowired EntityService service) { 
        this.service = service;

        VerticalLayout buttonPanel = new VerticalLayout();
        Button refreshInfoPanel = new Button("Refresh", this::refreshInfoPanel);
        buttonPanel.add(personLabel, departmentLabel, teamLabel,refreshInfoPanel);
        
        HorizontalLayout person = new HorizontalLayout();
        Button loadPersonBtn = new Button("Load Person", this::onLoadPerson);
        Button getDepartmentBtn = new Button("Get Department", this::onGetDepartment);
        Button getTeamBtn = new Button("Get Team", this::onGetTeam);
        getTeamBtn.setEnabled(false);
        Button loatPersonTreeBtn = new Button("Load Person Tree", this::findPersonTree);
        person.add(loadPersonBtn, getDepartmentBtn, getTeamBtn, loatPersonTreeBtn);
        buttonPanel.add(person);

        infoPanel = new VerticalLayout();
        
        HorizontalLayout main = new HorizontalLayout();
        main.add(buttonPanel, infoPanel);
        add(main);
    }

    private void refreshInfoPanel(ClickEvent<Button> event) {
        updateInfoPanel();
    }

    private void onLoadPerson(ClickEvent<Button> event) {
        personEntity = service.find(Person.class);
        personLabel.setText(String.valueOf(personEntity));
        updateInfoPanel();
    }

    private void findPersonTree(ClickEvent<Button> event) {
        personEntity = service.findPersonTree();
        teamEntity = personEntity.getTeam();
        departmentEntity = personEntity.getDepartment();
        personLabel.setText(String.valueOf(personEntity));
        teamLabel.setText(String.valueOf(teamEntity));
        departmentLabel.setText(String.valueOf(departmentEntity));
        updateInfoPanel();
    }
    

    private void onGetDepartment(ClickEvent<Button> event) {
        departmentEntity = personEntity.getDepartment();
        departmentLabel.setText(String.valueOf(departmentEntity));
        updateInfoPanel();
    }

    private void onGetTeam(ClickEvent<Button> event) {
        teamEntity = personEntity.getTeam();
        service.run(() -> teamLabel.setText(String.valueOf(teamEntity)));
        updateInfoPanel();
    }
    
    private void updateInfoPanel() {
        messageQueue.poolAll().forEach(str -> infoPanel.addComponentAtIndex(0, new Label(str)));
    }

}
