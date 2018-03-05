package com.demo.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;


@ComponentScan("com.demo")
@Configuration
public class ServiceConfig {

    @Bean
    public DriverManagerDataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:hotel_h2_db;DB_CLOSE_DELAY=-1;LOCK_TIMEOUT=2500;AUTOCOMMIT=OFF;DATABASE_TO_UPPER=false");
//        dataSource.setUrl("jdbc:h2:file:~/h2/hotel_h2_db;DB_CLOSE_ON_EXIT=FALSE;DATABASE_TO_UPPER=false");

        dataSource.setUsername("sa");
        dataSource.setPassword("");

//      schema init
        Resource initData = new ClassPathResource("data_h2_db.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initData);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(DriverManagerDataSource dataSource) {

        LocalSessionFactoryBean bean = new LocalSessionFactoryBean();

        bean.setDataSource(dataSource);

        bean.setPackagesToScan("com.demo.model");

        return bean;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
