package app.dao;

import app.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Category findById(Long id);

    Iterable<Category> findAll();

    List<Category> findByNameIn(String[] names);

}
