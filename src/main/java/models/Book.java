package models;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table (name = "Books")
public class Book {

    private int id;
    private String name;
    private String author;
    private String description;
    private int cost;

    private List<User> users;
    private List<Shop> shops;

    @ManyToMany
    @JoinTable(name = "users_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @ManyToMany
    @JoinTable(name = "shops_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "shop_id"))
    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addShop(Shop shop) {
        shops.add(shop);
    }

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")*/

    public Book() {
    }

    public Book(String name, String author, String descripton) {
        this.name = name;
        this.author = author;
        this.description = descripton;
        users = new ArrayList<>();
        shops = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return name + " " + author + " " + description;
    }
}