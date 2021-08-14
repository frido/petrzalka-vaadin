package com.example.application.knowledge;

import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.MergeEvent;
import org.hibernate.event.spi.MergeEventListener;

public class CustomMergeEventListener implements MergeEventListener{

    private transient MessageQueue queue = MessageQueue.getInstance(); 

    @Override
    public void onMerge(MergeEvent event) throws HibernateException {
        queue.add("CustomMergeEventListener-onMerge1: " + event);
    }

    @Override
    public void onMerge(MergeEvent event, Map copiedAlready) throws HibernateException {
        queue.add("CustomMergeEventListener-onMerge2: " + event);
    }
    
}
