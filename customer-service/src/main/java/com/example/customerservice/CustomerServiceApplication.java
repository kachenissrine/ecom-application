package com.example.customerservice;

import com.example.customerservice.entities.Customer;
import com.example.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CustomerRepository customerRepository,
                                               RepositoryRestConfiguration restConfiguration){
        return args -> {
            restConfiguration.exposeIdsFor(Customer.class);
            customerRepository.saveAll(
                    List.of(
                            Customer.builder().name("Nissrine").email("nissrine@gmail.com").build(),
                            Customer.builder().name("Kenza").email("kenza@gmail.com").build(),
                            Customer.builder().name("Oussama").email("oussama@gmail.com").build()
                    )
            );
            customerRepository.findAll().forEach(c ->{
                System.out.println(c);
            });
        };
    }
}
