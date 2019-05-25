package controller;

import db.dao.QuestionDAO;
import entity.Question;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utility.AutoExpert;
import utility.InfoDialog;

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

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setAutoExpert(AutoExpert autoExpert) {
        this.autoExpert = autoExpert;
    }

    public void initialize() {
        numberQuestion = 1l;
        questionDAO = new QuestionDAO();
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
        if (checkCarClass(answer)) {
            autoExpert.reduceListByCarClass(answer.charAt(0));
            numberQuestion++;
            System.out.println(autoExpert.getCarList());
            loadQuestionFromDatabase();
        }
    }

    private boolean checkCarClass(String answer) {
        if (answer.length() > 1) {
            InfoDialog.showAlert("Błąd", "Wprowadź jedną literę oznaczającą klasę samochodu");
            return false;
        }

        Character answerChar = answer.charAt(0);
        if(answerChar != 'A' && answerChar!='B' && answerChar!='C' && answerChar!='D' && answerChar!='E') {
            InfoDialog.showAlert("Błąd", "Wprowadzona litera nie oznacza klasy samochodu. Wprowadź jedną z 5 liter(A,B,C,D,E)");
            return false;
        }

        return true;
    }

}
