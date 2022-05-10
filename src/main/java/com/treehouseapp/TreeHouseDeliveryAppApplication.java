package com.treehouseapp;

import com.treehouseapp.repository.ConfirmationTokenRepository;
import com.treehouseapp.repository.CartRepository;
import com.treehouseapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableJpaRepositories(basePackageClasses = {UserRepository.class, ConfirmationTokenRepository.class, CartRepository.class})
@RequiredArgsConstructor
public class TreeHouseDeliveryAppApplication implements CommandLineRunner {



    public static void main(String[] args) {
        SpringApplication.run(TreeHouseDeliveryAppApplication.class, args);

    }

    private final StartUpService startUpService;

    @Override
    public void run(String... args) throws Exception {
        startUpService.initiateStartup();
        startUpService.createAdmin();
    }
}
