package controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import utility.AutoExpert;

import java.io.IOException;

public class WelcomeController {

    private MainController mainController;
    private AutoExpert autoExpert;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void initialize() {
        autoExpert = new AutoExpert();
    }

    @FXML
    void startApplication() {
        loadQuestionView();
    }

    private void loadQuestionView() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/questionView.fxml"));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        QuestionController questionController = loader.getController();
        questionController.setMainController(mainController);
        questionController.setAutoExpert(autoExpert);

        mainController.setMainBorderPane(parent);
    }

}
