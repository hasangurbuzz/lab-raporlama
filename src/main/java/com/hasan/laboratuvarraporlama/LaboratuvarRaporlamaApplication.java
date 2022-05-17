package com.hasan.laboratuvarraporlama;

import com.hasan.laboratuvarraporlama.model.Laborant;
import com.hasan.laboratuvarraporlama.repository.LaborantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LaboratuvarRaporlamaApplication implements CommandLineRunner {

    private final LaborantRepository repository;

    public LaboratuvarRaporlamaApplication(LaborantRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(LaboratuvarRaporlamaApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        Laborant laborant = new Laborant("firstName", "lastname");
        repository.save(laborant);
    }
}
