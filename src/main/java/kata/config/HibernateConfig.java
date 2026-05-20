package kata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Properties;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

@PropertySource("classpath:db.properties")
@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource =
                new DriverManagerDataSource();

        dataSource.setDriverClassName(
                environment.getProperty("db.driver"));

        dataSource.setUrl(
                environment.getProperty("db.url"));

        dataSource.setUsername(
                environment.getProperty("db.username"));

        dataSource.setPassword(
                environment.getProperty("db.password"));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();

        factory.setDataSource(dataSource());

        factory.setPackagesToScan("kata.model");

        HibernateJpaVendorAdapter adapter =
                new HibernateJpaVendorAdapter();

        factory.setJpaVendorAdapter(adapter);

        Properties properties = new Properties();

        properties.put("hibernate.show_sql", "true");

        properties.put("hibernate.hbm2ddl.auto", "update");

        properties.put("hibernate.dialect",
                "org.hibernate.dialect.MySQL8Dialect");

        factory.setJpaProperties(properties);

        return factory;
    }

    @Bean
    public JpaTransactionManager transactionManager() {

        JpaTransactionManager manager =
                new JpaTransactionManager();

        manager.setEntityManagerFactory(
                entityManagerFactory().getObject());

        return manager;
    }
}