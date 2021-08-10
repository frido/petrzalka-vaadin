package com.example.application.views.knowledge;

import java.time.LocalTime;

import com.example.application.knowledge.Department;
import com.example.application.knowledge.MessageQueue;
import com.example.application.knowledge.Person;
import com.example.application.knowledge.PersonWithVersion;
import com.example.application.knowledge.Team;
import com.example.application.services.EntityService;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "card-list", layout = MainView.class)
@PageTitle("Card List")
public class KnowledgeView extends Div {

    private transient EntityService service;
    private transient MessageQueue messageQueue = MessageQueue.getInstance();
    private VerticalLayout infoPanel;
    private transient Person personEntity;
    private transient PersonWithVersion personWithVersionEntity;
    private transient Department departmentEntity;
    private transient Team teamEntity;
    private Label personLabel = new Label();
    private Label personWithVersionLabel = new Label();
    private Label departmentLabel = new Label();
    private Label teamLabel = new Label();

    public KnowledgeView(@Autowired EntityService service) { 
        this.service = service;

        var buttonPanel = new VerticalLayout();
        var refreshInfoPanel = new Button("Refresh", this::refreshInfoPanel);
        buttonPanel.add(personLabel, departmentLabel, teamLabel, refreshInfoPanel);
        
        var person1 = new HorizontalLayout();
        var loadPersonBtn = new Button("Load Person", this::onLoadPerson);
        var getDepartmentBtn = new Button("Get Department", this::onGetDepartment);
        var getTeamBtn = new Button("Get Team", this::onGetTeam);
        var person2 = new HorizontalLayout();
        var loatPersonTreeBtn = new Button("Load Person Tree", this::findPersonTree);
        var loatPersonFetchBtn = new Button("Load Person Fetch", this::findPersonFetch);
        person1.add(loadPersonBtn, getDepartmentBtn, getTeamBtn);
        person2.add(loatPersonTreeBtn, loatPersonFetchBtn);
        buttonPanel.add(person1, person2);

        var merge1 = new HorizontalLayout();
        var merge2 = new HorizontalLayout();
        var mergePersonBtn = new Button("Merge Person", this::onMergePerson);
        var mergePersonAllBtn = new Button("Merge Person All", this::onMergePersonAll);
        var mergePersonDtoBtn = new Button("Merge Person DTO", this::onMergePersonDto);
        var loadPersonVersionBtn = new Button("Load Person Version", this::onLoadPersonVerson);
        var changePersonVersionBtn = new Button("Change Person Version", this::onChangePersonVerson);
        var mergePersonVersionBtn = new Button("Merge Person Version", this::onMergePersonVerson);
        merge1.add(mergePersonBtn, mergePersonAllBtn, mergePersonDtoBtn);
        merge2.add(loadPersonVersionBtn, changePersonVersionBtn, mergePersonVersionBtn);
        buttonPanel.add(merge1, personWithVersionLabel, merge2);

        // TODO: transaction isolation, sesions (httpSession, VaadinSession, SpringSession)?
        // TODO: kde sa inicializuje VaadinServlet?
        // TODO: ine formy optimistic lock (version) rieseni

        infoPanel = new VerticalLayout();
        
        var main = new HorizontalLayout();
        main.add(buttonPanel, infoPanel);
        add(main);

        messageQueue.addListener(x -> infoPanel.addComponentAtIndex(0, new Label("..." + x)));
    }

    private void refreshInfoPanel(ClickEvent<Button> event) {
        updateInfoPanel();
    }

    private void onLoadPerson(ClickEvent<Button> event) {
        clean();
        personEntity = service.find(Person.class);
        personLabel.setText(String.valueOf(personEntity));
        updateInfoPanel();
    }

    private void onMergePerson(ClickEvent<Button> event) {
        clean();
        personEntity.setName(randomText());
        personEntity = service.merge(personEntity);
        personLabel.setText(String.valueOf(personEntity));
        updateInfoPanel();
    }

    private void onMergePersonAll(ClickEvent<Button> event) {
        clean();
        personEntity.setName(randomText());
        personEntity.getDepartment().setName(randomText());
        personEntity.getTeam().setName(randomText());
        personEntity = service.merge(personEntity);
        personLabel.setText(String.valueOf(personEntity));
        updateInfoPanel();
    }

    private void onMergePersonDto(ClickEvent<Button> event) {
        clean();
        var personDto = service.findPersonDto();
        var personEnt = new Person();
        personEnt.setId(personDto.id());
        personEnt.setName(randomText());
        personEnt.setDepartment(personDto.department());
        personEnt.setTeam(personDto.team()); // TODO: nie uplne spravne DTO kedze toto je proxy
        personEnt = service.merge(personEnt);
        personLabel.setText(String.valueOf(personEnt));
        updateInfoPanel();
    }

    private void onLoadPersonVerson(ClickEvent<Button> event) {
        personWithVersionEntity = service.findPersonWithVersion();
        personWithVersionLabel.setText(String.valueOf(personWithVersionEntity));
        updateInfoPanel();
    }

    private void onChangePersonVerson(ClickEvent<Button> event) {
        var person = service.findPersonWithVersion();
        person.setName(randomText());
        service.merge(person);
        updateInfoPanel();
    }

    private void onMergePersonVerson(ClickEvent<Button> event) {
        try {
        personWithVersionEntity.setName(randomText());
        service.merge(personWithVersionEntity);
        updateInfoPanel();
        } catch (Exception e) {
            Notification.show(e.getMessage());
        }
    }

    private void findPersonTree(ClickEvent<Button> event) {
        clean();
        personEntity = service.findPersonTree();
        teamEntity = personEntity.getTeam();
        departmentEntity = personEntity.getDepartment();
        personLabel.setText(String.valueOf(personEntity));
        teamLabel.setText(String.valueOf(teamEntity));
        departmentLabel.setText(String.valueOf(departmentEntity));
        updateInfoPanel();
    }

    private void findPersonFetch(ClickEvent<Button> event) {
        clean();
        personEntity = service.findPersonFetch();
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

    private void clean() {
        personLabel.setText("");
        departmentLabel.setText("");
        teamLabel.setText("");
    }

    private String randomText() {
        return LocalTime.now().toString();
    }

}
