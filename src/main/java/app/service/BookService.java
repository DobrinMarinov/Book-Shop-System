package app.service;

import app.domain.Author;
import app.domain.Book;
import app.domain.enums.AgeRestriction;

import java.util.Date;
import java.util.List;

public interface BookService {

    void deleteBook(Book book);

    void saveBook (Book book);

    Book findBookById(Long id);

    List<Book> findByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findByEditionAndCopies();

    List<Book> findByPrice();

    List<Book> findByReleaseDateNot(Date date);

    List<Book> findByReleaseDateBefore(Date date);

}
