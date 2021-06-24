package com.example.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CrudService<T> {

    private static final Logger log = LoggerFactory.getLogger(CrudService.class);

    @PersistenceContext
    private EntityManager em;

    // TODO: there is bug
    List<T> getAll() {
        return em.createQuery("Select s from Simple s").getResultList();
    }

    void persist(T entity) {
        log.info("persist <- {}", entity);
        em.persist(entity);
        log.info("persist -> {}", entity);
    }

    T merge(T entity) {
        log.info("merge <- {}", entity);
        entity = em.merge(entity);
        log.info("merge -> {}", entity);
        return entity;
    }

    T findById(Class<T> clazz, int id) {
        log.info("findById <- {}", id);
        T entity = em.find(clazz, id);
        log.info("findById -> {}", entity);
        return entity;
    }

    void delete(T simple) {
        log.info("delete <- {}", simple);
        em.remove(simple);
        log.info("delete -> {}", simple);
    }

}
