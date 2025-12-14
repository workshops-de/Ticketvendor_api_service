package de.workshops.ticketvendorservice;

import de.workshops.ticketvendorservice.application.DbInitPort;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TicketvendorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketvendorServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(DbInitPort dbInitPort) {
        return args -> dbInitPort.initDb();
    }
}
