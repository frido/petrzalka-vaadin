package com.example.application;

import java.util.Properties;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.sql.DataSource;

import com.example.application.knowledge.CustomInterceptorImpl;
import com.example.application.knowledge.MessageQueue;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource(value = { "classpath:jdbc.properties", "classpath:application.properties" })
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public DataSource dataSource(@Autowired Environment env) {
        return DataSourceBuilder.create()
        .driverClassName(env.getProperty("driverClassName"))
        .url(env.getProperty("url"))
        .username("root")
        .password(env.getProperty("password"))
        .build();
    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager jpaTransactionManager(@Autowired LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }

    private HibernateJpaVendorAdapter vendorAdaptor() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        return vendorAdapter;
    }

    @Bean(name="entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
        @Autowired DataSource dataSource, 
        @Autowired Properties properties,
        @Autowired CustomInterceptorImpl interceptor) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan("com.example");
        properties.put("hibernate.session_factory.interceptor", interceptor);
        entityManagerFactoryBean.setJpaProperties(properties);

        return entityManagerFactoryBean;
    }

    @Bean
    public HttpSessionListener httpSessionListener() {

        MessageQueue messageQueue = MessageQueue.getInstance();
        return new HttpSessionListener() {
    
            @Override
            public void sessionCreated(HttpSessionEvent hse) {
                messageQueue.add("Http Session created" + hse.getSession());
            }
        
            @Override
            public void sessionDestroyed(HttpSessionEvent hse) {
                messageQueue.add("Http Session destroyed");
            }
            
        };
    }
}
