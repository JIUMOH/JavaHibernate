import models.Book;
import models.User;
import models.Shop;
import services.UserService;
import services.ShopService;

import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;
import java.util.List;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        login();

    }

    private static void login(){
        UserService userService = new UserService();
        User user = new User();
        Scanner in = new Scanner(System.in);

        System.out.println("Введите имя пользователя и пароль");
        while (true) {
            System.out.print("Имя пользователя: ");
            String username = in.nextLine();
            System.out.print("Пароль: ");
            String pass = in.nextLine();
            user = userService.findUser(username);
            if (user != null)
                if (user.getPass().equals(pass)) break;
            System.out.println("Неверное имя пользователя и/или пароль!");
        }

        shopListMenu(user);
    }

    private static void shopListMenu(User user){
        ShopService shopService = new ShopService();
        Scanner in = new Scanner(System.in);

        System.out.println("Выбирите магазин из списка");
        System.out.println("0. Выход");
        List<Shop> shops = shopService.findAllShops();
        int i = 0;
        for (Shop s : shops) {
            i++;
            System.out.printf("%d. %s\n", i, s.getName());
        }
        while (true) {
            i = in.nextInt();
            if (i == 0) System.exit(0);
            i--;
            if (i < 0 || i >= shops.size()) {
                System.out.println("Неправельный номер магазина!");
                continue;
            }
            break;
        }

        bookListMenu(user,shops.get(i));
    }

    private static void bookListMenu(User user,Shop shop) {
        int i;
        Scanner in = new Scanner(System.in);

        System.out.println("Выбирите книгу, которую хотите купить");
        System.out.println("0. Назад");
        List<Book> books = shop.getBooks();
        i = 0;
        for (Book b : books) {
            i++;
            System.out.printf("%d. %s\n", i, b.getName());
        }
        while (true) {
            i = in.nextInt();
            if (i == 0) {
                shopListMenu(user);
                System.exit(0);
            }
            i--;
            if (i < 0 || i >= books.size()) {
                System.out.println("Неправельный номер книги!");
                continue;
            }
            break;
        }

        bookMenu(books.get(i),user,shop);
    }

    private static void bookMenu(Book book, User user, Shop shop) {
        UserService userService = new UserService();
        ShopService shopService = new ShopService();
        Scanner in = new Scanner(System.in);
        int i;

        System.out.println("Название: " + book.getName());
        System.out.println("Автор: " + book.getAuthor());
        System.out.println("Описание: " + book.getDescription());
        System.out.println("Цена: " + book.getCost());
        System.out.println("Ваша наличность: " + user.getCash());
        System.out.println("1. Назад");
        System.out.println("2. Купить");
        while (true) {
            i = in.nextInt();
            if (i == 1) {
                bookListMenu(user,shop);
                System.exit(0);
            }
            if (i == 2){
                if (user.getCash() < book.getCost()){
                    System.out.println("Недостаточно средств!");
                    continue;
                }
                shop.setCash(shop.getCash()+book.getCost());
                user.setCash(user.getCash()-book.getCost());
                shop.removeBook(book);
                user.addBook(book);
                userService.updateUser(user);
                shopService.updateShop(shop);
                bookListMenu(user,shop);
                System.exit(0);
            }
            if (i < 1 || i > 2) {
                System.out.println("Неправельный номер операции!");
                continue;
            }
        }
    }

}