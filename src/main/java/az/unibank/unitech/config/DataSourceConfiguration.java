package az.unibank.unitech.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

//@Configuration
//@EnableJpaRepositories(basePackages = "az.unibank.unitech.repository",
//        entityManagerFactoryRef = "postgres-em",
//        transactionManagerRef = "postgres-tm")
//@ConfigurationProperties(prefix = "db")
public class DataSourceConfiguration {
//
//    private String username;
//    private String password;
//    private String url;
//    private String driverClassName;
//
//    @Bean
//    public DataSource dataSource() {
//        return DataSourceBuilder.create()
//                .username(username)
//                .password(password)
//                .url(url)
//                .driverClassName(driverClassName)
//                .build();
//    }
////
////    @Bean(name = "postgres-em")
////    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
////        var entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
////        entityManagerFactoryBean.setDataSource(dataSource());
////        entityManagerFactoryBean.setPackagesToScan("az.unibank.unitech.entity");
////        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
////        Map<String, Object> properties = new HashMap<>();
////        properties.put("hibernate.hbm2ddl.auto", "none");
////        properties.put("hibernate.show_sql", true);
////        properties.put("hibernate.format_sql", true);
////        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
////        entityManagerFactoryBean.setJpaPropertyMap(properties);
////        return entityManagerFactoryBean;
////    }
////
////    @Bean(name = "postgres-tm")
////    public PlatformTransactionManager transactionManager() {
////        JpaTransactionManager transactionManager = new JpaTransactionManager();
////        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
////        return transactionManager;
//    }
}
