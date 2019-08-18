package dao;

import models.Book;
import models.Shop;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;
import java.util.List;

public class ShopDao {

    public Shop findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Shop shop = session.get(Shop.class, id);
        session.close();
        return shop;
    }

    public void save(Shop shop) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(shop);
        tx1.commit();
        session.close();
    }

    public void update(Shop shop) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(shop);
        tx1.commit();
        session.close();
    }

    public void delete(Shop shop) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(shop);
        tx1.commit();
        session.close();
    }

    public Book findBookById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Book book = session.get(Book.class, id);
        session.close();
        return book;
    }

    public List<Shop> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Shop> shops = session.createQuery("From Shop").list();
        session.close();
        return shops;
    }

}