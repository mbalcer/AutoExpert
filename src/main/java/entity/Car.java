package entity;

import javax.persistence.*;

@Entity
@Table
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Character carClass;
    private Double engineCapacity;
    private Integer enginePower;
    private Double fuelUsageFrom;
    private Double fuelUsageTo;
    private String engineType;

    public Car() {
    }

    public Car(String name, Character carClass, Double engineCapacity, Integer enginePower, Double fuelUsageFrom, Double fuelUsageTo, String engineType) {
        this.name = name;
        this.carClass = carClass;
        this.engineCapacity = engineCapacity;
        this.enginePower = enginePower;
        this.fuelUsageFrom = fuelUsageFrom;
        this.fuelUsageTo = fuelUsageTo;
        this.engineType = engineType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getCarClass() {
        return carClass;
    }

    public void setCarClass(Character carClass) {
        this.carClass = carClass;
    }

    public Double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(Double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public Integer getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(Integer enginePower) {
        this.enginePower = enginePower;
    }

    public Double getFuelUsageFrom() {
        return fuelUsageFrom;
    }

    public void setFuelUsageFrom(Double fuelUsageFrom) {
        this.fuelUsageFrom = fuelUsageFrom;
    }

    public Double getFuelUsageTo() {
        return fuelUsageTo;
    }

    public void setFuelUsageTo(Double fuelUsageTo) {
        this.fuelUsageTo = fuelUsageTo;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", carClass=" + carClass +
                ", engineCapacity=" + engineCapacity +
                ", enginePower=" + enginePower +
                ", fuelUsageFrom=" + fuelUsageFrom +
                ", fuelUsageTo=" + fuelUsageTo +
                ", engineType='" + engineType + '\'' +
                '}';
    }

    public String showDescriptionCar() {
        return  "Wybrałem dla ciebie: " + name + "\n" +
                "Klasa samochodu: " + carClass + "\n" +
                "Pojemność silnika: " + engineCapacity + "\n" +
                "Moc silnika: " + enginePower + " KM\n" +
                "Spalanie: " + fuelUsageFrom + " - " + fuelUsageTo + " l\n" +
                "Typ silnika: " + engineType;
    }
}
