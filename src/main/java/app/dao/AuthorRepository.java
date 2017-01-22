package app.dao;

import app.domain.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long>{

    Author findById(long id);
    Iterable<Author> findAll();

    @Query (value = "SELECT a, COUNT(a) AS cnt FROM Author AS a GROUP BY a ORDER BY cnt")
    List<Object[]> findAuthorBookStartingWith();

}
