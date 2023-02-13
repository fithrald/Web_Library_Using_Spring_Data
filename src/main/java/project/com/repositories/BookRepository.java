package project.com.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.com.models.Book;
import project.com.models.Client;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByOrderByYear();

    Page<Book> findAllByOrderByYear(Pageable var1);

    Page<Book> findAll(Pageable var1);

    List<Book> findByOwner(Client owner);

    List<Book> findByTitleStartingWith(String query);

    Optional<Book> findByTitle(String query);
}
