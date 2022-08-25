package com.example.springpractice;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;


@Configuration
@EntityScan("com.example.springpractice.Entities")
@EnableJpaRepositories
@EnableTransactionManagement
public class JPAConfig {
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/mydb");
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }

    @Bean
    public Properties Properties() {
        final Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        return properties;
    }

    @Bean
    public EntityManagerFactory EntityManagerFactory(DataSource source, Properties properties) {
        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(source);
        factory.setPackagesToScan("hibernate/entities");
        factory.setJpaVendorAdapter( new HibernateJpaVendorAdapter() );
        factory.setJpaProperties(properties);
        factory.setPersistenceUnitName("demo-unit");
        factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Qualifier
    @Primary
    @Bean(name="entityManagerFactory")
    public LocalSessionFactoryBean sessionFactory(DataSource source, Properties properties) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(source);
        sessionFactory.setPackagesToScan("hibernate/entities");
        sessionFactory.setHibernateProperties(properties);
        try {
            sessionFactory.afterPropertiesSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}
