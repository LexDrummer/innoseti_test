package ru.lexdrummer.innoseti_test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.lexdrummer.innoseti_test.entity.Author;
import ru.lexdrummer.innoseti_test.entity.Book;
import ru.lexdrummer.innoseti_test.repository.BookRepository;
import ru.lexdrummer.innoseti_test.util.EmptyFieldException;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorService authorService;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByAuthor(Author author) {
        return bookRepository.findAllByAuthorsContains(author);
    }

    public List<Book> getBooksByAuthor(String author) {
        Author authorObj = authorService.getAuthorByName(author);
        return authorObj == null ? Collections.emptyList() : bookRepository.findAllByAuthorsContains(authorObj);
    }

    @Transactional
    public Book saveBook(String title, List<String> authors) {
        if(!StringUtils.hasLength(title) || authors == null || authors.isEmpty()) {
            throw new EmptyFieldException("Fields title or authors should not be empty");
        }
        Book book = bookRepository.save(new Book(title));
        if(!authors.isEmpty()) {
            Set<Author> authorSet = authors.stream().filter(StringUtils::hasLength).map(a -> {
                Author author = authorService.getAuthorByName(a);
                if(author != null) {
                    author.getBooks().add(book);
                } else {
                    author = new Author(a);
                    author.setBooks(new HashSet<>(Set.of(book)));
                    authorService.saveOrUpdate(author);
                }
                return author;
            }).collect(Collectors.toSet());
            book.setAuthors(authorSet);
        }
        return book;
    }



}
