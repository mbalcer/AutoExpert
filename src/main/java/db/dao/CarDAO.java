package db.dao;

import db.HibernateFactory;
import entity.Car;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
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

    public List<Car> search(Car searchedCar, int subtrahend) {
        Session session = new HibernateFactory().getSessionFactory().openSession();
        List<Car> carList = session.createQuery("FROM Car WHERE carClass=:carClass AND engineCapacity=:eCapacity AND engineType=:eType AND enginePower>=:ePower-:subtrahend AND enginePower<=:ePower+:subtrahend AND fuelUsageTo<=:fuelUsage")
                .setParameter("carClass", searchedCar.getCarClass())
                .setParameter("eCapacity", searchedCar.getEngineCapacity())
                .setParameter("subtrahend", subtrahend)
                .setParameter("eType", searchedCar.getEngineType())
                .setParameter("ePower", searchedCar.getEnginePower())
                .setParameter("fuelUsage", searchedCar.getFuelUsageTo())
                .list();
        session.close();

        return carList;
    }

    public List<Double> readEngineCapacityByCarClass(Character carClass) {
        Session session = new HibernateFactory().getSessionFactory().openSession();
        List<Double> carList = session.createQuery("SELECT DISTINCT engineCapacity FROM Car WHERE carClass=:carClass ORDER BY engineCapacity")
                .setParameter("carClass", carClass)
                .list();

        session.close();

        return carList;
    }

    public List<String> readEngineTypeByEngineCapacityAndCarClass(Double engineCapacity, Character carClass) {
        Session session = new HibernateFactory().getSessionFactory().openSession();
        List<String> engineTypeList = session.createQuery("SELECT DISTINCT engineType FROM Car WHERE carClass=:carClass AND engineCapacity=:engineCapacity ORDER BY engineType")
                .setParameter("carClass", carClass)
                .setParameter("engineCapacity", engineCapacity)
                .list();
        session.close();

        return engineTypeList;
    }
}
