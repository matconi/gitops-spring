package domain.company.project.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EntityScan(basePackages = {"domain.company.project.module.domain"})
//@EnableEurekaClient
public class ModuleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ModuleApplication.class, args);
        System.out.println("Running module...............");
    }
}
