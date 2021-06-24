package com.example.springboot;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ValidableTest {
//
//    private static final Logger log = LoggerFactory.getLogger(ValidableTest.class);
//
//    @Autowired
//    private ValidableService service;
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
//            em.createQuery("DELETE FROM validable_entity").executeUpdate();
//            return status;
//        });
//    }
//
//    @Test
//    public void notNull() throws Exception {
//        TransactionBlock tb = new TransactionBlock(tx);
//        ValidableEntity entity = new ValidableEntity();
//        try {
//            tb.run(() -> {
//                try {
//                    service.persist(entity);
//                    Assert.error("There have to be exception");
//                } catch (Exception e) {
//                    // Exception is catch in here
//                    // but there is already transaction rollback flag
//                }
//            });
//        } catch (Exception e) {
//            // ok
//            // I have to catch exception with transaction rollback
//        }
//    }


}
