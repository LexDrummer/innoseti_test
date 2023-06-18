package ru.lexdrummer.innoseti_test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.lexdrummer.innoseti_test.entity.Author;
import ru.lexdrummer.innoseti_test.entity.Book;
import ru.lexdrummer.innoseti_test.repository.AuthorRepository;
import ru.lexdrummer.innoseti_test.repository.BookRepository;
import ru.lexdrummer.innoseti_test.util.AuthorNotFoundException;
import ru.lexdrummer.innoseti_test.util.EmptyFieldException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author getAuthorByName(String name) {
        Author author = authorRepository.findAuthorByName(name);
        if(author == null) {
            throw new AuthorNotFoundException("Author with name " + name + " is not found!");
        }
        return author;
    }

    @Transactional
    public Author saveAuthor(String name, List<String> books) {
        if(!StringUtils.hasLength(name)) {
            throw new EmptyFieldException("Authors name field should not be empty");
        }
        Author author = new Author();
        author.setName(name);
        if(books != null && !books.isEmpty()) {
            Set<Book> bookSet = books.stream().map(b -> {
                Book book = bookRepository.findBookByTitle(b);
                return book == null ? new Book(b) : book;
            }).collect(Collectors.toSet());
            author.setBooks(bookSet);
        }
        return saveOrUpdate(author);
    }

    @Transactional
    public Author saveOrUpdate(Author author) {
        return authorRepository.save(author);
    }
}
