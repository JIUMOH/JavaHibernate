package models;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table (name = "Users")
public class User {

    private int id;
    private String pass;
    private String username;
    private int cash;

    private List<Book> books;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_books",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    //OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)

    public User() {
    }

    public User(String pass, String username, int cash) {
        this.pass = pass;
        this.username = username;
        this.cash = cash;
        books = new ArrayList<>();
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    @Override
    public String toString() {
        return "models.User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                ", cash=" + cash +
                '}';
    }
}