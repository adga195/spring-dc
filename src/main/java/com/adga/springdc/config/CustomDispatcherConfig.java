package com.adga.springdc.config;

import com.adga.springdc.log.SpringLogger;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.naming.ConfigurationException;
import java.beans.PropertyVetoException;
import java.util.Objects;
import java.util.Properties;

// EnableWebMvc enables annotation driven support for spring mvc
@EnableWebMvc
// EnableTransactionManagement equivalent to <tx:annotation-driven transaction-manager="sqlServerTransactionManager"/>
@EnableTransactionManagement
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.adga.springdc")
@PropertySource("/resources/config/aop.properties")
public class CustomDispatcherConfig implements WebMvcConfigurer, EnvironmentAware {

    // Internal encoding and file size.
    public static final String ENCODING_UTF = "UTF";
    public static final String ENCODING_UTF_8 = "UTF_8";
    public static final long MAX_UPLOAD_FILE_SIZE = 52428807;
    public static final long MAX_UPLOAD_PER_FILE_SIZE = 5242880;

    private Environment env;

    @Override
    public void setEnvironment(final Environment environment) {
        this.env = environment;
    }

    @Bean
    public SpringLogger customSpringLogger() {

        SpringLogger myLogger = new SpringLogger();

        myLogger
            .initLogger(env.getProperty("springLogger.rootLoggerLevel"),
                        env.getProperty("springLogger.printedLoggerLevel"),
                        Boolean.parseBoolean(env.getProperty("springLogger.enableLogger"))
        );

        return myLogger;
    }

    @Bean
    public ComboPooledDataSource sqlServerDataSource() throws ConfigurationException {

        ComboPooledDataSource comboPool = new ComboPooledDataSource();

        try {
            comboPool.setDriverClass(env.getProperty("db.driverClass"));
        } catch (PropertyVetoException e) {
            throw new ConfigurationException("ComboPooledDataSource driverClass error.");
        }

        comboPool.setJdbcUrl(env.getProperty("db.jdbcUrl"));
        comboPool.setUser(env.getProperty("db.user"));
        comboPool.setPassword(env.getProperty("db.password"));
        comboPool.setMinPoolSize(Integer.parseInt(Objects.requireNonNull(env.getProperty("db.minPoolSize"))));
        comboPool.setMaxPoolSize(Integer.parseInt(Objects.requireNonNull(env.getProperty("db.maxPoolSize"))));
        comboPool.setMaxIdleTime(Integer.parseInt(Objects.requireNonNull(env.getProperty("db.maxIdleTime"))));

        return comboPool;
    }

    @Bean
    public LocalSessionFactoryBean sqlServerSessionFactory() throws ConfigurationException {

        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();

        org.hibernate.cfg.Environment hEnv = null;
        localSessionFactoryBean.setDataSource(sqlServerDataSource());
        localSessionFactoryBean.setPackagesToScan(env.getProperty("sessionFactory.packagesToScan"));

        Properties hibernateProperties = new Properties();

        hibernateProperties.put(hEnv.DIALECT, env.getProperty("hibernate.dialect"));
        hibernateProperties.put(hEnv.SHOW_SQL, env.getProperty("hibernate.show_sql"));
        hibernateProperties.put(hEnv.DEFAULT_SCHEMA, env.getProperty("hibernate.default_schema"));

        localSessionFactoryBean.setHibernateProperties(hibernateProperties);

        return localSessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager sqlServerTransactionManager() throws ConfigurationException {

        HibernateTransactionManager hibernateTransactionManager =
                new HibernateTransactionManager();

        hibernateTransactionManager.setSessionFactory(sqlServerSessionFactory().getObject());

        return hibernateTransactionManager;
    }

    // InternalResourceViewResolver configuration to resolve .jsp pages at a given directory.
    @Bean
    public InternalResourceViewResolver jspViewResolver() {

        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();

        internalResourceViewResolver.setPrefix("/WEB-INF/view/");
        internalResourceViewResolver.setSuffix(".jsp");
        internalResourceViewResolver.setOrder(1);

        return internalResourceViewResolver;
    }

    @Override
    // Equivalent to <mvc:resources location="/resources/" mapping="/resources/**"></mvc:resources>
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/resources/**")
            .addResourceLocations("/resources/")
            .setCachePeriod(3600)
            .resourceChain(true)
            .addResolver(new PathResourceResolver());
    }

    @Override
    // Equivalent to a welcome file!
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/WEB-INF/index.jsp");
    }

}
