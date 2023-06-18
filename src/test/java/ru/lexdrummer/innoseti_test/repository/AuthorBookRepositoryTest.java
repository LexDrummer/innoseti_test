package ru.lexdrummer.innoseti_test.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.lexdrummer.innoseti_test.ContainerBaseTest;
import ru.lexdrummer.innoseti_test.entity.Author;
import ru.lexdrummer.innoseti_test.entity.Book;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AuthorBookRepositoryTest extends ContainerBaseTest {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void givenAuthorObject_whenSave_thenReturnSavedAuthor() {

        Author author = new Author("William Shakespeare");
        Book book = new Book("Romeo and Juliet");
        author.setBooks(new HashSet<>(Set.of(book)));
        Author savedAuthor = authorRepository.save(author);
        List<Book> savedBooks = bookRepository.findAllByAuthorsContains(savedAuthor);

        Assertions.assertNotNull(savedAuthor);
        Assertions.assertTrue(savedAuthor.getId() > 0);
        Assertions.assertTrue(savedBooks.size() > 0);
        Assertions.assertNotNull(savedBooks.get(0));

    }
}