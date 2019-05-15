package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import db.dao.CarDAO;
import entity.Car;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.List;

public class MainController {

    @FXML
    private JFXComboBox<Character> formCarClass;

    @FXML
    private JFXComboBox<Double> formEngineCapacity;

    @FXML
    private JFXSlider formEnginePower;

    @FXML
    private JFXSlider formFuelUsage;

    @FXML
    private JFXComboBox<String> formEngineType;

    @FXML
    private Label result;

    private CarDAO carDAO;

    @FXML
    void checkCar() {
        Car searchCar = new Car();
        searchCar.setCarClass(formCarClass.getSelectionModel().getSelectedItem());
        searchCar.setEngineCapacity(formEngineCapacity.getSelectionModel().getSelectedItem());
        searchCar.setEnginePower((int) formEnginePower.getValue());
        searchCar.setFuelUsageTo(formFuelUsage.getValue());
        searchCar.setEngineType(formEngineType.getSelectionModel().getSelectedItem());

        System.out.println(searchCar);

        List<Car> carList = carDAO.search(searchCar);
        try {
            result.setText(carList.get(0).showDescriptionCar());
        } catch (IndexOutOfBoundsException e) {
            result.setText("Nie mogłem znaleźć odpowiedniego samochodu dla ciebie. Zmień parametry i sprawdź jeszcze raz");
        }
    }

    @FXML
    void chooseCarClass() {
        fillEngineCapacityCombobox();
    }

    @FXML
    void chooseEngineCapacity() {
        fillEngineTypeCombobox();
    }

    public void initialize() {
        carDAO = new CarDAO();
        fillCarClassCombobox();
        fillEngineTypeCombobox();
    }

    private void fillCarClassCombobox() {
        ObservableList<Character> carClassList = FXCollections.observableArrayList();
        carClassList.addAll('A', 'B');
        formCarClass.setItems(carClassList);
    }

    private void fillEngineCapacityCombobox() {
        ObservableList<Double> engineCapacityList = FXCollections.observableArrayList();
        List<Double> carList = carDAO.readEngineCapacityByCarClass(formCarClass.getSelectionModel().getSelectedItem());

        engineCapacityList.addAll(carList);
        formEngineCapacity.setItems(engineCapacityList);
    }

    private void fillEngineTypeCombobox() {
        ObservableList<String> engineTypeList = FXCollections.observableArrayList();
        List<String> carList = carDAO.readEngineTypeByEngineCapacityAndCarClass(
                formEngineCapacity.getSelectionModel().getSelectedItem(),
                formCarClass.getSelectionModel().getSelectedItem());

        engineTypeList.addAll(carList);
        formEngineType.setItems(engineTypeList);
    }
}
