package app.dao;

import app.domain.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long>{

    Author findById(long id);
    Iterable<Author> findAll();

}
