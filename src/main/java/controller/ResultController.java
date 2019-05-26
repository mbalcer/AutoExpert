package controller;

import entity.Car;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import utility.AutoExpert;

import java.util.List;
import java.util.Random;

public class ResultController {

    @FXML
    private ImageView imageCar;

    @FXML
    private Label resultCar;

    @FXML
    private Label userAnswer;

    private MainController mainController;
    private AutoExpert autoExpert;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setAutoExpert(AutoExpert autoExpert) {
        this.autoExpert = autoExpert;
    }

    public void showResult() {
        List<Car> carList = autoExpert.getCarList();
        if (carList.size()==0)
            resultCar.setText("Niestety nie udało się znaleźć samochodu o podanych parametrach. Zmień parametry samochodu i spróbuj jeszcze raz");
        else if (carList.size()>=1) {
            Random rand = new Random();
            int randNumber = rand.nextInt(carList.size());
            resultCar.setText(carList.get(randNumber).showDescriptionCar());
        }
    }

    @FXML
    void exitApplication() {

    }

}
