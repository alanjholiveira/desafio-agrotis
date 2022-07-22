package br.com.agrotis.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DesafioTecnicoApoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioTecnicoApoApplication.class, args);
    }

}
