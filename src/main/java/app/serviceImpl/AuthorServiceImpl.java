package app.serviceImpl;

import app.dao.AuthorRepository;
import app.domain.Author;
import app.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void delete(Author author) {
        authorRepository.delete(author);
    }

    @Override
    public void delete(Long id) {
        authorRepository.delete(id);
    }

    @Override
    public Author findAuthor(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Iterable<Author> findAuthors() {
        return authorRepository.findAll();
    }
}
