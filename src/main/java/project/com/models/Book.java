package project.com.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Table
@Entity
public class Book {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "title shouldn't be empty")
         @Pattern(regexp = "[A-Z]\\w+ [a-zA-Z]\\w*" , message = "title should start with uppercase")
    @Column
    private String title;
    @NotBlank(message = "author shouldn't be empty")
    @Column
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+" , message = "author should start with uppercase")
    private String author;

    @Column
    @Min(value = 900, message = "year must in range of 900-2022")
    @Max(value = 2022, message = " year must in range of 900-2022")
    private int year;


    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    public Client owner;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;

    @Transient
    private boolean overdue;

    public Date getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }

    public boolean isOverdue() {
        return overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }


    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
