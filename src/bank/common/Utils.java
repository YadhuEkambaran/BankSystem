package bank.common;

import javafx.scene.control.Alert;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static void showDialog(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public static String loadDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        return dateFormat.format(new Date());
    }
}
