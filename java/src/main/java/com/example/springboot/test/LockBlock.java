package com.example.springboot.test;

import java.util.concurrent.atomic.AtomicBoolean;

public class LockBlock {

    AtomicBoolean block = new AtomicBoolean(true);

    public void  release() {
        synchronized (block) {
            block.set(false);
            block.notifyAll();
        }
    }

    public void waiting() {
        synchronized (block) {
            while (block.get()) {
                try {
                    block.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
