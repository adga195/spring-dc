package com.adga.springdc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

@Configuration
public class CustomDispatcherServlet implements WebApplicationInitializer {

    // The WebApplicationInitializer interface requires the overriding of onStartup()
    @Override
    public void onStartup(ServletContext servletContext) {

        AnnotationConfigWebApplicationContext webApplicationContext =
                new AnnotationConfigWebApplicationContext();

        // Using the Dispatcher config as part of the Servlet dispatcher context
        webApplicationContext.register(CustomDispatcherConfig.class);
        webApplicationContext.setServletContext(servletContext);

        DispatcherServlet dispatcherServlet =
                new DispatcherServlet(webApplicationContext);

        // Creation of servlet dynamically
        ServletRegistration.Dynamic servlet =
                servletContext.addServlet("DispatcherServlet", dispatcherServlet);

        servlet.setLoadOnStartup(1);
        // Equivalent to <url-pattern>/</url-pattern>
        servlet.addMapping("/");

    }
}
