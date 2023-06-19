package ru.lexdrummer.innoseti_test.controller;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import ru.lexdrummer.innoseti_test.entity.Author;
import ru.lexdrummer.innoseti_test.entity.Book;
import ru.lexdrummer.innoseti_test.model.AuthorInput;
import ru.lexdrummer.innoseti_test.service.BookService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @QueryMapping
    Collection<Book> getAllBooks() {
        return bookService.getBooks();
    }

    @QueryMapping
    Collection<Book> getBooksByAuthor(@Argument AuthorInput author) {
        return bookService.getBooksByAuthor(author.getName());
    }

    @BatchMapping
    Map<Book, Set<Author>> authors(Set<Book> books) {
        return books
                .stream().collect(Collectors.toMap(book -> book,
                        Book::getAuthors));
    }

    @MutationMapping
    Book saveBook(@Argument String title, @Argument List<AuthorInput> authors) {
        return bookService.saveBook(title, authors);
    }

}
