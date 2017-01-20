package app.console;

import app.domain.Author;
import app.domain.Book;
import app.domain.enums.AgeRestriction;
import app.domain.enums.EditionType;
import app.service.AuthorService;
import app.service.BookService;
import app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Component
public class ConsoleRunner implements CommandLineRunner{

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void run(String... strings) throws Exception {
        insertBooksFromFile("books.txt");
        insertAuthorsFromFile("authors.txt");
    }

    private void insertBooksFromFile(String filePath) throws IOException, ParseException {
            BufferedReader booksReader = new BufferedReader(new FileReader(filePath));
            String line = booksReader.readLine();
            while ((line = booksReader.readLine()) != null) {
                String[] data = line.split("\\s+");

                EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
                SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
                Date releaseDate = formatter.parse(data[1]);
                int copies = Integer.parseInt(data[2]);
                BigDecimal price = new BigDecimal(data[3]);
                AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
                StringBuilder titleBuilder = new StringBuilder();
                for (int i = 5; i < data.length; i++) {
                    titleBuilder.append(data[i]).append(" ");
                }
                titleBuilder.delete(titleBuilder.lastIndexOf(" "), titleBuilder.lastIndexOf(" "));
                String title = titleBuilder.toString();

                Book book = new Book();
                book.setEditionType(editionType);
                book.setReleaseDate(releaseDate);
                book.setCopies(copies);
                book.setPrice(price);
                book.setAgeRestriction(ageRestriction);
                book.setTitle(title);

                bookService.saveBook(book);
            }

    }

    private void insertAuthorsFromFile(String filePath) throws IOException {
        BufferedReader authorsReader = new BufferedReader(new FileReader(filePath));
        String lineAuthors = authorsReader.readLine();
        while ((lineAuthors = authorsReader.readLine()) != null) {
            String[] dataAuthors = lineAuthors.split("\\s+");

            String firstName = dataAuthors[0];
            String lastName = dataAuthors[1];

            Author author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);

            authorService.save(author);

        }
    }
}
