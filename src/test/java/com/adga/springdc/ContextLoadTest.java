package com.adga.springdc;

import com.adga.springdc.config.CustomDispatcherConfigT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { CustomDispatcherConfigT.class })
@WebAppConfiguration
public class ContextLoadTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void contextAndBeansLoaded() {

        ServletContext servletContext = webApplicationContext.getServletContext();

        Assertions.assertNotNull(servletContext);
        Assertions.assertTrue(servletContext instanceof MockServletContext);
        Assertions.assertNotNull(webApplicationContext.getBean("customSpringLogger"));
        Assertions.assertNotNull(webApplicationContext.getBean("sqlServerDataSource"));
        Assertions.assertNotNull(webApplicationContext.getBean("sqlServerSessionFactory"));
        Assertions.assertNotNull(webApplicationContext.getBean("sqlServerTransactionManager"));
        Assertions.assertNotNull(webApplicationContext.getBean("jspViewResolver"));

    }

}
