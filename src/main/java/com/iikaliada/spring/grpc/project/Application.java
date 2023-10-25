package com.iikaliada.spring.grpc.project;

import com.iikaliada.spring.grpc.project.client.EmployeeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private EmployeeClientService employeeClientService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        int id = 1;
        String response = employeeClientService.getEmployeeRequest(id);
        System.out.println("Response: " + response);
    }

}
