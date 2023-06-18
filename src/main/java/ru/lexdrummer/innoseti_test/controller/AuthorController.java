package ru.lexdrummer.innoseti_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import ru.lexdrummer.innoseti_test.entity.Author;
import ru.lexdrummer.innoseti_test.entity.Book;
import ru.lexdrummer.innoseti_test.service.AuthorService;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @QueryMapping
    public List<Author> getAllAuthors() {
        return authorService.findAll();
    }
    @QueryMapping
    public Author getAuthor(@Argument String name) {
        return authorService.getAuthorByName(name);
    }

    @BatchMapping
    public Map<Author, Set<Book>> books(Set<Author> authors) {
        return authors
                .stream().collect(Collectors.toMap(autor -> autor,
                        Author::getBooks));
    }

    @MutationMapping
    Author saveAuthor(@Argument String name, @Argument List<String> books) {
        return authorService.saveAuthor(name, books);
    }
}
