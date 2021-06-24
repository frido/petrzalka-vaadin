package com.example.springboot.test;

import org.springframework.transaction.support.TransactionTemplate;

public class TransactionBlock {

    private TransactionTemplate tx;

    public TransactionBlock(TransactionTemplate tx) {
        this.tx = tx;
    }

    public void run(Runnable action) {
        tx.execute(status -> {
            action.run();
            return status;
        });
    }
}
