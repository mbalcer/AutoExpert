package controller;

import db.dao.QuestionDAO;
import entity.Car;
import entity.Question;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utility.AutoExpert;
import utility.CheckData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionController {

    @FXML
    private Label questionLabel;

    @FXML
    private TextField answerField;

    @FXML
    private Label promptLabel;

    private MainController mainController;
    private AutoExpert autoExpert;
    private Long numberQuestion;
    private QuestionDAO questionDAO;
    private CheckData checkData;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setAutoExpert(AutoExpert autoExpert) {
        this.autoExpert = autoExpert;
    }

    public void initialize() {
        numberQuestion = 1l;
        questionDAO = new QuestionDAO();
        checkData = new CheckData();
        loadQuestionFromDatabase();
    }

    private void loadQuestionFromDatabase() {
        Question question = questionDAO.read(numberQuestion);
        questionLabel.setText(question.getQuestion());
        promptLabel.setText(question.getPrompt());
    }

    @FXML
    void nextQuestion() {
        String answer = answerField.getText().toUpperCase();
        switch (numberQuestion.intValue()) {
            case 1: {
                if (checkData.checkCarClass(answer))
                    autoExpert.reduceListByCarClass(answer.charAt(0));
                else
                    return;
            } break;
            case 2: {
                if (checkData.checkEngineType(answer))
                    autoExpert.reduceListByEngineType(answer);
                else
                    return;
            } break;
            case 3: {
                if (checkData.checkEngineCapacity(answer)){
                    Double[] range = autoExpert.setRangeEngineCapacity(answer);
                    autoExpert.reduceListByEngineCapacity(range[0], range[1]);
                } else
                    return;
            } break;
            case 4: {
                if (checkData.checkEnginePower(answer)) {
                    Integer[] range = autoExpert.setRangeEnginePower(answer);
                    if (checkData.checkEnginePowerRange(range[0], range[1])) {
                        List<Car> copyList = autoExpert.getCarList();
                        autoExpert.reduceListByEnginePower(range[0], range[1]);
                        if (!(autoExpert.checkSizeList())) {
                            autoExpert.setCarList(copyList);
                            Car car = autoExpert.searchCar((range[0]+range[1])/2);
                            List<Car> oneCarList = new ArrayList<>();
                            oneCarList.add(car);
                            autoExpert.setCarList(oneCarList);
                        }
                    } else
                        return;
                } else
                    return;
            }
        }
        System.out.println(autoExpert.getCarList());
        if (numberQuestion == 4 || !(autoExpert.checkSizeList()))
            loadResultView();
        else {
            numberQuestion++;
            clearAnswerField();
            loadQuestionFromDatabase();
        }

        autoExpert.searchCar(50);
    }

    private void loadResultView() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/resultView.fxml"));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ResultController resultController = loader.getController();
        resultController.setMainController(mainController);
        resultController.setAutoExpert(autoExpert);
        resultController.showResult();
        mainController.setMainBorderPane(parent);
    }

    private void clearAnswerField() {
        answerField.clear();
    }


}
