package models;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table (name = "Shops")
public class Shop {

    private int id;
    private String name;
    private int cash;

    private List<Book> books;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "shops_books",
            joinColumns = @JoinColumn(name = "shop_id"),
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

    public void removeBook(Book book) {
        books.remove(book);
    }

    //OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)

    public Shop() {
    }

    public Shop(String name, int cash) {
        this.name = name;
        this.cash = cash;
        books = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    @Override
    public String toString() {
        return "models.Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cash=" + cash +
                '}';
    }
}