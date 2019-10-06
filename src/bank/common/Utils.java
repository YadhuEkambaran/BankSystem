package bank.common;

import javafx.scene.control.Alert;

public class Utils {

    public static void showDialog(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
