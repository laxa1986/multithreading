package com.laxa.multithreading.cache;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by alex4 on 2/22/17.
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ComputationService service = (ComputationService) context.getBean("computationService");
        System.out.println("About to call computation");
        service.compute(2);
        System.out.println("About to call computation second time");
        service.compute(2);
        System.out.println("end");
    }
}