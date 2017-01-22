package app.dao;

import app.domain.Book;
import app.domain.enums.AgeRestriction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

    Book findById(long id);
    List<Book> findAll();

    List<Book> findByAgeRestriction(AgeRestriction ageRestriction);

    @Query (value = "SELECT b FROM Book AS b WHERE b.editionType = 'GOLD' AND b.copies < 5000")
    List<Book> findByEditionAndCopies();

    @Query (value = "SELECT b FROM Book AS b WHERE b.price < 5 OR b.price > 40")
    List<Book> findByPrice();

    List<Book> findByReleaseDateNot(Date date);

    List<Book> findByReleaseDateBefore(Date date);

}
