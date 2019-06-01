package db.dao;

import db.HibernateFactory;
import entity.Car;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    public List<Car> findAll() {
        Session session = new HibernateFactory().getSessionFactory().openSession();
        List<Car> carList = new ArrayList<>();
        try {
            Query query = session.createQuery("FROM "+Car.class.getName());
            carList = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return carList;
    }
}
