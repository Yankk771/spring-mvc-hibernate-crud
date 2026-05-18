package kata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource =
                new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        dataSource.setUrl(
                "jdbc:mysql://localhost:3306/kata_crud");

        dataSource.setUsername("root");

        dataSource.setPassword("1234");

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