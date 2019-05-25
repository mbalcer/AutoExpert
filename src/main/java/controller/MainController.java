package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane mainBorderPane;

    public void setMainBorderPane(Parent parent) {
        mainBorderPane.setCenter(parent);
    }

    public void initialize() {
        loadWelcomeView("/view/welcomeView.fxml");
    }

    public void loadWelcomeView(String viewPath) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(viewPath));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        WelcomeController welcomeController = loader.getController();
        welcomeController.setMainController(this);
        setMainBorderPane(parent);
    }

}
