package controller;

import entity.Car;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utility.AutoExpert;

import java.io.IOException;
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
            imageCar.setImage(new Image("/img/"+carList.get(randNumber).getImage()));
        }
    }

    @FXML
    void exitApplication() {
        System.exit(0);
    }

    @FXML
    void loadStartView() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/welcomeView.fxml"));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        WelcomeController welcomeController = loader.getController();
        welcomeController.setMainController(mainController);
        mainController.setMainBorderPane(parent);
    }

}
