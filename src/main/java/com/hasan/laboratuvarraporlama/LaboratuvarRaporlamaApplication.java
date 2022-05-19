package com.hasan.laboratuvarraporlama;

import com.hasan.laboratuvarraporlama.model.Laborant;
import com.hasan.laboratuvarraporlama.service.LaborantService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LaboratuvarRaporlamaApplication implements CommandLineRunner {

    private final LaborantService laborantService;

    public LaboratuvarRaporlamaApplication(LaborantService laborantService) {
        this.laborantService = laborantService;
    }


    public static void main(String[] args) {
        SpringApplication.run(LaboratuvarRaporlamaApplication.class, args);

    }

    @Override
    public void run(String... args) {
        Laborant laborant = new Laborant(1, "username", "lastname");
        laborantService.saveLaborant(laborant);
    }
}
