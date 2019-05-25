package utility;

import db.dao.CarDAO;
import entity.Car;

import java.util.List;
import java.util.stream.Collectors;

public class AutoExpert {
    private List<Car> carList;

    public AutoExpert() {
        CarDAO carDAO = new CarDAO();
        carList = carDAO.findAll();
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void reduceListByCarClass(Character condition) {
        carList = carList.stream()
                .filter(car -> car.getCarClass() == condition)
                .collect(Collectors.toList());
    }
}
