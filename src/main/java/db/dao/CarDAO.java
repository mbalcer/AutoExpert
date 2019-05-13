package db.dao;

import db.HibernateFactory;
import entity.Car;
import org.hibernate.Session;
import java.util.List;

public class CarDAO {

    public List<Car> searchCar(Car searchedCar) {
        Session session = new HibernateFactory().getSessionFactory().openSession();
        List<Car> carList = session.createQuery("FROM Car WHERE carClass=:carClass AND engineCapacity=:eCapacity AND engineType=:eType AND enginePower=:ePower AND fuelUsageTo<=:fuelUsage")
                .setParameter("carClass", searchedCar.getCarClass())
                .setParameter("eCapacity", searchedCar.getEngineCapacity())
                .setParameter("eType", searchedCar.getEngineType())
                .setParameter("ePower", searchedCar.getEnginePower())
                .setParameter("fuelUsage", searchedCar.getFuelUsageTo())
                .list();

        session.close();

        return carList;
    }
}
