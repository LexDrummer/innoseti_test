package ru.lexdrummer.innoseti_test;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.TypeRuntimeWiring;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import ru.lexdrummer.innoseti_test.service.AuthorService;
import ru.lexdrummer.innoseti_test.service.BookService;

import java.util.function.UnaryOperator;

@SpringBootApplication
public class InnosetiTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(InnosetiTestApplication.class, args);
    }

}
