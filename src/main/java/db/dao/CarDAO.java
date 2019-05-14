package db.dao;

import db.HibernateFactory;
import entity.Car;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import java.util.List;

public class CarDAO {

    public List<Car> search(Car searchedCar) {
        Session session = new HibernateFactory().getSessionFactory().openSession();
        List<Car> carList = session.createQuery("FROM Car WHERE carClass=:carClass AND engineCapacity=:eCapacity AND engineType=:eType AND enginePower>=:ePower-1 AND enginePower<=:ePower+1 AND fuelUsageTo<=:fuelUsage")
                .setParameter("carClass", searchedCar.getCarClass())
                .setParameter("eCapacity", searchedCar.getEngineCapacity())
                .setParameter("eType", searchedCar.getEngineType())
                .setParameter("ePower", searchedCar.getEnginePower())
                .setParameter("fuelUsage", searchedCar.getFuelUsageTo())
                .list();
        session.close();

        return carList;
    }

    public List<Double> readAllByCarClass(Character carClass) {
        Session session = new HibernateFactory().getSessionFactory().openSession();
        List<Double> carList = session.createQuery("SELECT DISTINCT engineCapacity FROM Car WHERE carClass=:carClass ORDER BY engineCapacity")
                .setParameter("carClass", carClass)
                .list();

        session.close();

        return carList;
    }
}
