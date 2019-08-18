package services;

import dao.ShopDao;
import models.Book;
import models.Shop;

import java.util.List;

public class ShopService {

    private ShopDao shopsDao = new ShopDao();

    public ShopService() {
    }

    public Shop findShop(String name) {
        List<Shop> users = shopsDao.findAll();
        /*for (Shop s:users) {
            if (u.getUsername().equals(username)) return u;
        }*/
        return null;
    }

    public void saveShop(Shop shop) {
        shopsDao.save(shop);
    }

    public void deleteShop(Shop shop) {
        shopsDao.delete(shop);
    }

    public void updateShop(Shop shop) {
        shopsDao.update(shop);
    }

    public List<Shop> findAllShops() {
        return shopsDao.findAll();
    }

    public Book findAllBookById(int id) {
        return shopsDao.findBookById(id);
    }



}