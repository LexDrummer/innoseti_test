package ru.lexdrummer.innoseti_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lexdrummer.innoseti_test.entity.Author;
import ru.lexdrummer.innoseti_test.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findBookByTitle(String title);

    List<Book> findAllByAuthorsContains(Author author);

}
