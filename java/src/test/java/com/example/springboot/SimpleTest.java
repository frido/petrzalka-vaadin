package com.example.springboot;

import com.example.springboot.model.Simple;
import com.example.springboot.service.SimpleService;
import com.example.springboot.test.LockBlock;
import com.example.springboot.test.TransactionBlock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SimpleTest {
//
//    private static final Logger log = LoggerFactory.getLogger(SimpleTest.class);
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
//
//
//    @BeforeEach
//    public void setUp() throws Exception {
//        tx.execute(status -> {
//            em.createQuery("DELETE FROM Simple").executeUpdate();
//            return status;
//        });
//    }
//
//    @Test
//    public void happyFlow() throws Exception {
//        tx.execute(status -> {
//            log.info("happyFlow");
//            Simple simple = new Simple();
//            simple.setName("name");
//            simleService.persist(simple);
//            simleService.findById(Simple.class, simple.getId());
//            assertEquals("name", simple.getName());
//            simple.setName("name2");
//            simleService.merge(simple);
//            simleService.findById(Simple.class, simple.getId());
//            assertEquals("name2", simple.getName());
//            simleService.delete(simple);
//            simleService.findById(Simple.class, simple.getId());
//            return status;
//        });
//    }
//
//    // final merge is done at the end of transaction.
//    // No matter how many setters and merges are in transaction.
//    // At the end is only one final merge
//    @Test
//    public void mergeConflict() throws Exception {
//        tx.execute(status -> {
//            log.info("mergeConflict");
//            Simple simple = new Simple();
//            simple.setName("name");
//            simleService.persist(simple);
//
//            Simple entity1 = simleService.findById(Simple.class, simple.getId());
//            Simple entity2 = simleService.findById(Simple.class, simple.getId());
//
//            entity1.setName("a1");
//            simleService.merge(entity1);
//            entity2.setName("a2");
//            simleService.merge(entity2);
//            return status;
//        });
//    }
//
//    // Nested transaction is joined to parent.
//    // Commit is at the end of parent transaction
//    @Test
//    public void nestedTransaction() throws Exception {
//        tx.execute(status -> {
//            log.info("nestedTransaction");
//            Simple simple = new Simple();
//            simple.setName("name");
//            simleService.persist(simple);
//
//            Simple entity1 = simleService.findById(Simple.class, simple.getId());
//            Simple entity2 = simleService.findById(Simple.class, simple.getId());
//
//            entity1.setName("a1");
//            simleService.merge(entity1);
//            tx.execute(status2 -> {
//                entity2.setName("a2");
//                simleService.merge(entity2);
//                return status2;
//            });
//            return status;
//        });
//    }
//
//    // Flush do real query to the DB ??? NOT SURE
//    @Test
//    public void flush() throws Exception {
//        tx.execute(status -> {
//            log.info("mergeConflict");
//            Simple simple = new Simple();
//            simple.setName("name");
//            simleService.persist(simple);
//
//            Simple entity1 = simleService.findById(Simple.class, simple.getId());
//            Simple entity2 = simleService.findById(Simple.class, simple.getId());
//
//            entity1.setName("a1");
//            simleService.merge(entity1);
//            em.flush();
//            entity2.setName("a2");
//            simleService.merge(entity2);
//            return status;
//        });
//    }
//
//    // Query to the DB is real but it is without commit. Commit is only after transaction is finished.
//    // Entity is not visible in DB without commit. Thats is why I can see sql in console but nothing in the DB.
//    @Test
//    public void rollback() throws Exception {
//        TransactionBlock tb = new TransactionBlock(tx);
//        Simple simple = new Simple();
//        simple.setName("old");
//        tb.run(() -> {
//            simleService.persist(simple);
//        });
//        try {
//            tb.run(() -> {
//                Simple entity = simleService.findById(Simple.class, simple.getId());
//                entity.setName("new");
//                simleService.merge(entity);
//                em.flush();
//                throw new NullPointerException();
//            });
//        } catch (Exception e) {
//            tb.run(() -> {
//                Simple entity = simleService.findById(Simple.class, simple.getId());
//                // Should see new value because of transaction is finished and it was committed to the DB
//                assertEquals("old", entity.getName());
//            });
//        }
//
//    }
//
//    // we can see new value in the DB before the transaction is committed.
//    // this is because of transaction isolation is ISOLATION_READ_UNCOMMITTED
//    @Test
//    public void parallel() throws Exception {
//        LockBlock lb1 = new LockBlock();
//        LockBlock lb2 = new LockBlock();
//        Simple simple = new Simple();
//        simple.setName("change1");
//        // TODO: executor a latch zabalit do jedneho objektu spolu s transaction block
//        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
//        CountDownLatch latch = new CountDownLatch(2);
//        // for ISOLATION_READ_COMMITTED it failed because tx2 can not find entity persisted by tx1
//        tx.setIsolationLevel(TransactionDefinition.ISOLATION_READ_UNCOMMITTED);
//        TransactionBlock tb = new TransactionBlock(tx);
//        executor.execute(() -> {
//            tb.run(() -> {
//                simleService.persist(simple);
//                lb2.release();
//                lb1.waiting();
//            });
//            latch.countDown();
//        });
//        executor.execute(() -> {
//            tb.run(() -> {
//                lb2.waiting();
//                Simple entity = simleService.findById(Simple.class, simple.getId());
//                assertNotNull(entity);
//                lb1.release();
//            });
//            latch.countDown();
//        });
//        latch.await();
//    }
//
//    // we can see new value in the DB only after the transaction is committed. Even flush will not help us.
//    // this is because of transaction isolation is default
//    @Test
//    public void parallel2() throws Exception {
//        LockBlock lb1 = new LockBlock();
//        LockBlock lb2 = new LockBlock();
//        Simple simple = new Simple();
//        simple.setName("old");
//        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
//        CountDownLatch latch = new CountDownLatch(2);
//        tx.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
//        TransactionBlock tb = new TransactionBlock(tx);
//        tb.run(() -> {
//            simleService.persist(simple);
//        });
//        executor.execute(() -> {
//            tb.run(() -> {
//                Simple entity = simleService.findById(Simple.class, simple.getId());
//                entity.setName("new");
//                simleService.merge(entity);
//                em.flush();
//                lb2.release();
//                lb1.waiting();
//            });
//            tb.run(() -> {
//                Simple entity = simleService.findById(Simple.class, simple.getId());
//                // Should see new value because of transaction is finished and it was committed to the DB
//                assertEquals("new", entity.getName());
//            });
//            latch.countDown();
//        });
//        executor.execute(() -> {
//            tb.run(() -> {
//                lb2.waiting();
//                Simple entity = simleService.findById(Simple.class, simple.getId());
//                // Should see old value because transaction is not finished (even it was flushed)
//                assertEquals("old", entity.getName());
//                lb1.release();
//            });
//            latch.countDown();
//        });
//        latch.await();
//    }

}
