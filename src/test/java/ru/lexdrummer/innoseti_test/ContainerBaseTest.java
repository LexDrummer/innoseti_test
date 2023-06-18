package ru.lexdrummer.innoseti_test;

import org.testcontainers.containers.PostgreSQLContainer;

public class ContainerBaseTest {

    static final PostgreSQLContainer PSQL_CONTAINER;

    static {
        PSQL_CONTAINER = new PostgreSQLContainer("postgres:15.3");

        PSQL_CONTAINER.start();
    }
}
