package project.com.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import project.com.dao.BookDao;
import project.com.models.Book;
import project.com.services.BookService;

@Component
public class BookValidation implements Validator {

   private final  BookService bookService;
    public BookValidation(BookService bookService) {
        this.bookService = bookService;
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(Book.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;
        if (bookService.check(book.getTitle()).isPresent())
            errors.rejectValue("title", "", "book with that title is already exist");
    }
}
