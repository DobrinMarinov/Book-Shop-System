package app.service;

import app.domain.Author;
import app.domain.Book;

public interface BookService {

    void deleteBook(Book book);

    void saveBook (Book book);

    Book findBookById(Long id);

}
