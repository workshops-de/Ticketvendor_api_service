package de.workshops.ticketvendorservice;

import org.springframework.boot.SpringApplication;

public class TestTicketvendorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(TicketvendorServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }
}