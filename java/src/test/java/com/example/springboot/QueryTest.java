package com.example.springboot;

import com.example.springboot.model.Simple;
import com.example.springboot.service.SimpleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class QueryTest {
//
//    private static final Logger log = LoggerFactory.getLogger(QueryTest.class);
//
//    @Autowired
//    private SimpleService simleService;
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Autowired
//    private TransactionTemplate tx;
//
//    @BeforeEach
//    public void setUp() throws Exception {
//        tx.execute(status -> {
//            em.createQuery("DELETE FROM Simple").executeUpdate();
//            simleService.persist(new Simple("Peter"));
//            // TODO: create data in DB
//            return status;
//        });
//    }
//
//    @Test
//    public void happyFlow() throws Exception {
//        tx.execute(status -> {
//            log.info("happyFlow");
//            CriteriaBuilder cb = em.getCriteriaBuilder();
//            CriteriaQuery<Simple> cq = cb.createQuery(Simple.class);
//            Root<Simple> from = cq.from(Simple.class);
//            cq.where(cb.equal(from.get(Simple_.name), "Peter"));
//            TypedQuery<Simple> q = em.createQuery(cq);
//            List<Simple> allitems = q.getResultList();
//            assertEquals(1, allitems.size());
//            return status;
//        });
//    }


}
