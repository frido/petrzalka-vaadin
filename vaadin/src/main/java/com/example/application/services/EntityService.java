package com.example.application.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.example.application.knowledge.MessageQueue;
import com.example.application.knowledge.Person;
import com.example.application.old.page.budget.Budget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntityService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    TransactionalService transactionalService;

    public EntityManager getEm() {
        return em;
    }

    @Transactional // TODO: nerozumiem na co to tu je
    public <T> T save(T entity) {
        return em.merge(entity);
    }

    public <T> List<T> findAll(Class<T> clazz) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> rootEntry = cq.from(clazz);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    @Transactional
    public <T> T find(Class<T> clazz) {
        return em.find(clazz, 1);
    }

    @Transactional
    public Person findPersonTree() {
        Person person = em.find(Person.class, 1);
        MessageQueue.getInstance().add("findPersonTree - can load departnent: " + person.getDepartment());
        MessageQueue.getInstance().add("findPersonTree - can load team: " + transactionalService.getTeamNameRequired(person));
        return person;
    }

    @Transactional
    public Budget merge(Budget data) {
        return em.merge(data);
    }

    @Transactional
    public void run(Runnable runnable) {
        runnable.run();
    }
}
