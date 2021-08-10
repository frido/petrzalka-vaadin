package com.example.application.knowledge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class MessageQueue {

    private AtomicInteger counter = new AtomicInteger(0);
    private static MessageQueue INSTANCE;
    private LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private List<Consumer<String>> listeners = new ArrayList<>();

    public static synchronized MessageQueue getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MessageQueue();
        }
        return INSTANCE;
    }

    public List<String> poolAll() {
        List<String> list = new ArrayList<>();
        queue.forEach(list::add);
        queue.clear();
        list.add("------------------");
        Collections.reverse(list);
        return list;
    }

    public void add(String msg) {
        int i = counter.getAndIncrement();
        var text = i + " - " + msg;
        queue.add(text);
        callListeners(text);
    }

    private void callListeners(String text) {
        for (Consumer<String> consumer : listeners) {
            consumer.accept(text);
        }
    }

    public void addListener(Consumer<String> listener) {
        queue.forEach(listener);
        this.listeners.add(listener);
    }
    
}
