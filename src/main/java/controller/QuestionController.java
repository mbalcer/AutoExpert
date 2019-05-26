package controller;

import db.dao.QuestionDAO;
import entity.Question;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utility.AutoExpert;
import utility.CheckData;

import java.io.IOException;

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
                        autoExpert.reduceListByEnginePower(range[0], range[1]);
                    } else
                        return;
                } else
                    return;
            }
        }
        System.out.println(autoExpert.getCarList());
        if (numberQuestion == 4)
            loadResultView();
        else {
            numberQuestion++;
            loadQuestionFromDatabase();
        }
    }

    private void loadResultView() {
        
    }


}
