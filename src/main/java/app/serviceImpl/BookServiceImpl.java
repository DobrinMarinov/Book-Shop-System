package app.serviceImpl;

import app.dao.BookRepository;
import app.domain.Author;
import app.domain.Book;
import app.domain.enums.AgeRestriction;
import app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findByAgeRestriction(AgeRestriction ageRestriction) {
        return bookRepository.findByAgeRestriction(ageRestriction);
    }

    @Override
    public List<Book> findByEditionAndCopies() {
        return bookRepository.findByEditionAndCopies();
    }

    @Override
    public List<Book> findByPrice() {
        return bookRepository.findByPrice();
    }

    @Override
    public List<Book> findByReleaseDateNot(Date date) {
        return bookRepository.findByReleaseDateNot(date);
    }

    @Override
    public List<Book> findByReleaseDateBefore(Date date) {
        return bookRepository.findByReleaseDateBefore(date);
    }

}
