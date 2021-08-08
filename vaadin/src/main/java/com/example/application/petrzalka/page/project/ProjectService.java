package com.example.application.petrzalka.page.project;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.application.old.page.project.Project_;
import com.example.application.petrzalka.criteria.AttributeOrderCriteriaBuilder;
import com.example.application.petrzalka.criteria.CriteriaQueryContext;
import com.example.application.petrzalka.criteria.InterfaceCriteriaBuilder;
import com.example.application.petrzalka.criteria.NoCriteriaBuilder;

import java.util.List;

@Component // TODO: component vs resource
public class ProjectService {

    @PersistenceContext
    private EntityManager em;
    private InterfaceCriteriaBuilder<Project> defaultOrder = new AttributeOrderCriteriaBuilder<>(Project_.date);

    public List<Project> getProjectForIndex() {
        return findByCriteria(new NoCriteriaBuilder<>(), 4);
    }

    private List<Project> findByCriteria(InterfaceCriteriaBuilder<Project> criteriaBuilder, int limit) {
        return new CriteriaQueryContext<>(em, Project.class).apply(criteriaBuilder).apply(defaultOrder).getResultList(limit);
    }

    public List<Project> getAllProjects() {
        return findByCriteria(new NoCriteriaBuilder<>(), 100);
    }
}
