package utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckData {
    public boolean checkCarClass(String answer) {
        if (answer.length() > 1) {
            InfoDialog.showAlert("Błąd", "Wprowadź jedną literę oznaczającą klasę samochodu");
            return false;
        }

        Character answerChar = answer.charAt(0);
        if(answerChar != 'A' && answerChar!='B' && answerChar!='C' && answerChar!='D') {
            InfoDialog.showAlert("Błąd", "Wprowadzona litera nie oznacza klasy samochodu. Wprowadź jedną z 5 liter(A,B,C,D,E)");
            return false;
        }
        return true;
    }

    public boolean checkEngineType(String answer) {
        if (!(answer.equals("BENZYNA")) && !(answer.equals("DIESEL"))) {
            InfoDialog.showAlert("Błąd", "Wprowadź rodzaj silnika spośród: benzyna, diesel");
            return false;
        }
        return true;
    }

    public boolean checkEngineCapacity(String answer) {
        if (!(answer.equals("NISKA")) && !(answer.equals("ŚREDNIA")) && !(answer.equals("WYSOKA"))) {
            InfoDialog.showAlert("Błąd", "Wprowadź pojemność silnika spośród: niska, średnia, wysoka");
            return false;
        }
        return true;
    }

    public boolean checkEnginePower(String answer) {
        Pattern pattern = Pattern.compile("\\d{1,3}\\s{0,1}-\\s{0,1}\\d{1,3}");
        Matcher matcher = pattern.matcher(answer);

        if (!matcher.matches()){
            InfoDialog.showAlert("Błąd", "Wprowadź zakres pojemności silnika w formacie: 00-00 np. 30-50");
            return false;
        }
        return true;
    }

    public boolean checkEnginePowerRange(Integer rangeFrom, Integer rangeTo) {
        if (rangeFrom > rangeTo) {
            InfoDialog.showAlert("Błąd", "Musisz podać poprawny zakres mocy silnika (pierwsza liczba musi być mniejsza od drugiej)");
            return false;
        } else if (rangeFrom <30 || rangeFrom>200 || rangeTo<30 || rangeTo>200) {
            InfoDialog.showAlert("Błąd", "Podałeś zbyt odległe zakresy mocy silnika. Podaj moc silnika w zakresie 30-200");
            return false;
        }
        return true;
    }
}
