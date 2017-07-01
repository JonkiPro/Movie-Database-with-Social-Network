package com.jonki.Config;

import com.jonki.Entity.Message;
import com.jonki.Entity.Movie;
import com.jonki.Entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("com.jonki")
@PropertySource("classpath:settingsDatabase.properties")
public class WebDatasourceConfig {

    @Value("${database.driver}") String databaseDriver;
    @Value("${database.url}") String databaseUrl;
    @Value("${database.username}") String databaseUsername;
    @Value("${database.password}") String databasePassword;

    @Value("${database.hibernate.show_sql}") boolean databaseHibernateShowSql;
    @Value("${database.hibernate.dialect}") String databaseHibernateDialect;
    @Value("${database.hibernate.hbm2ddl.auto}") String databaseHibernateHbm2ddlAuto;
    @Value("${database.enable_lazy_load_no_trans}") boolean databaseHibernateEnableLazyLoadNoTrans;

    @Value("${database.packages_to_scan}") String databasePackagesToScan;

    @Bean
    public DataSource getDatasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(databaseDriver);
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(databaseUsername);
        dataSource.setPassword(databasePassword);

        return dataSource;
    }

    @Bean
    public SessionFactory getSessionFactory() throws IOException {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setPackagesToScan(databasePackagesToScan);
        sessionFactoryBean.setHibernateProperties(getHibernateProperties());
        sessionFactoryBean.setDataSource(getDatasource());
        sessionFactoryBean.afterPropertiesSet();
        sessionFactoryBean.setAnnotatedClasses(User.class, Message.class, Movie.class);

        return sessionFactoryBean.getObject();
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() throws IOException{
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory());

        return transactionManager;
    }

    private Properties getHibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.show_sql", databaseHibernateShowSql);
        hibernateProperties.put("hibernate.dialect", databaseHibernateDialect);
        hibernateProperties.put("hibernate.hbm2ddl.auto", databaseHibernateHbm2ddlAuto);
        hibernateProperties.put("hibernate.enable_lazy_load_no_trans", databaseHibernateEnableLazyLoadNoTrans);

        return hibernateProperties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean =
                                                new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(getDatasource());
        factoryBean.setPackagesToScan(databasePackagesToScan);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(getHibernateProperties());

        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
