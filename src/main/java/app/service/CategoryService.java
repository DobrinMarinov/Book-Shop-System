package app.service;

import app.domain.Category;

public interface CategoryService {

    void save(Category category);

    void delete(Category category);

    Category find (Long id);

}
