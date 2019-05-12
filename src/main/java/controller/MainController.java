package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {

    @FXML
    private JFXTextField formCarClass;

    @FXML
    private JFXComboBox<?> formEngineCapacity;

    @FXML
    private JFXSlider formEnginePower;

    @FXML
    private JFXSlider formFuelUsage;

    @FXML
    private JFXTextField formEngineType;

    @FXML
    private Label result;

    @FXML
    void checkCar() {

    }

}
