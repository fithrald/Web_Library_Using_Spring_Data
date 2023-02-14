package project.com.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.com.models.Book;
import project.com.models.Client;
import project.com.repositories.BookRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;

@Service
@Transactional(readOnly = true)
public class BookService {
   private final  BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(int id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
          Book bookToBeUpdated = getBook(id);

        updatedBook.setId(id);
        updatedBook.setOwner(bookToBeUpdated.getOwner());
        bookRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }


    public Client getOwner(int id) {

        Book book = bookRepository.findById(id).orElse(null);
        Client client = book.getOwner();
        return client;
    }

    @Transactional
    public void changeOwner(Client client, int id) {

        Book book = bookRepository.findById(id).orElse(null);
        book.setTakenAt(new Date());
        book.setOwner(client);

    }
    @Transactional
    public void bookRelease(int id) {

        Book book = bookRepository.findById(id).orElse(null);

        book.setOwner(null);
        book.setTakenAt(null);


    }


    public List<Book> findAllOrderByYear() {

        return bookRepository.findAllByOrderByYear();
    }

    ;


    public Page<Book> findAllOrderByYearPag(int page, int itemsPerPage) {


        return bookRepository.findAllByOrderByYear(PageRequest.of(page, itemsPerPage));
    }

    ;


    public Page<Book> findAllPag(int page, int itemsPerPage) {


        return bookRepository.findAll(PageRequest.of(page, itemsPerPage));
    }

    ;

    public List <Book> findByNameStartingWith(String query) {
        return bookRepository.findByTitleStartingWith(query);
    }

    public Optional<Book> check(String title){
        return bookRepository.findByTitle(title);
    }
    @Transactional
    public void isOverdue(List<Book> books) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String date1 = "01.11.2022";
        Date temp = null;
        try {
            temp = format.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date date = new Date();
        long tenDays = 864000000;
        for (Book book : books) {
            if (book.getTakenAt()==null)
            {
                book.setOverdue(false);
                continue;
            }
            long diff = date.getTime() - book.getTakenAt().getTime();
            if (diff > tenDays) {
                book.setOverdue(true);

            } else {
                book.setOverdue(false);

            }
        }

    }

}
