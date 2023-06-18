package ru.lexdrummer.innoseti_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lexdrummer.innoseti_test.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Author findAuthorByName(String name);

}
