package project.com.models;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Client {
    public Client() {
    }

    public Client(String FIO) {
        this.FIO = FIO;
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+",
            message = "name and surname should start with uppercase + space")
    @NotBlank(message = "name shouldn`t be empty")
    @Size(min = 3, max = 20, message = "Name should be between 3 and 20")
    private String FIO;

    @Column
    @Min(value = 1900, message = "age should be 1900+")
    @Max(value = 2006, message = "max value is 2006")
    private int birth_year;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;


    public List<Book> getBooks() {
        return books;
    }


    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public int getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(int birth_year) {
        this.birth_year = birth_year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
