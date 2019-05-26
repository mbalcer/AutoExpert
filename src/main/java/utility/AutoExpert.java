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

    public void reduceListByEngineType(String condition) {
        carList = carList.stream()
                .filter(car -> car.getEngineType().equals(condition.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void reduceListByEngineCapacity(Double rangeFrom, Double rangeTo) {
        carList = carList.stream()
                .filter(car -> car.getEngineCapacity()>=rangeFrom)
                .filter(car -> car.getEngineCapacity()<=rangeTo)
                .collect(Collectors.toList());
    }

    public Double[] setRangeEngineCapacity(String engineCapacity) {
        Double[] range = new Double[2];
        if (engineCapacity.equals("NISKA")){
            range[0] = 0.8;
            range[1] = 1.2;
        } else if (engineCapacity.equals("ŚREDNIA")) {
            range[0] = 1.2;
            range[1] = 1.6;
        } else if (engineCapacity.equals("WYSOKA")) {
            range[0] = 1.6;
            range[1] = 2.0;
        }

        return range;
    }
}
