package app.console;

import app.domain.Author;
import app.domain.Book;
import app.domain.Category;
import app.domain.enums.AgeRestriction;
import app.domain.enums.EditionType;
import app.service.AuthorService;
import app.service.BookService;
import app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
        /*  insert data from files
        //insertAuthorsFromFile("authors.txt");
        //insertBooksFromFile("books.txt");

        //addCategory("Fantasy");
        //addCategory("Horror");
        */

        /*  query by age restriction
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while (!(input = bufferedReader.readLine()).equals("Stop")) {
            AgeRestriction ageRestriction = AgeRestriction.valueOf(input.toUpperCase());
            List<Book> books = bookService.findByAgeRestriction(ageRestriction);
            for (Book book : books) {
                System.out.println(book.getTitle());
            }
        }
        */

        /* query using JPQL (edition type and number of copies)
        List<Book> books = bookService.findByEditionAndCopies();
        for (Book book : books) {
            System.out.println(book.getTitle());
        }
        */

        /* query using JPQL (price)
        List<Book> books = bookService.findByPrice();
        for (Book book : books) {
            System.out.println(book.getTitle() + "- $" + book.getPrice());
        }
        */

        /* a repository query
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
        Date date = sdf.parse("2017-01-22");
        List<Book> books = bookService.findByReleaseDateNot(date);
        for (Book book : books) {
            System.out.println(book.getTitle() + ", Release date: " + book.getReleaseDate());
        }
        */

        /* a repository query: books released before a console given date
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date = sdf.parse(br.readLine());
        List<Book> books = this.bookService.findByReleaseDateBefore(date);
        for (Book book : books) {
            System.out.println(book.getTitle() + ", released: " + book.getReleaseDate());
        }
        */

        /* JPQL authors query
        List<Object[]> objects = this.authorService.findAuthorBookStartingWith();
        for (Object[] object : objects) {
            Author author = (Author) object[0];
            long books = (long) object[1];
            System.out.println(String.format("%s - %d", author.getFirstName(), books));
        }
        */

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

    private void addCategory(String name) {
        Category category = new Category();
        category.setName(name);
        categoryService.save(category);
    }
}
