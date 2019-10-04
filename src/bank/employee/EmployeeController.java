package bank.employee;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    @FXML
    Label lb_create_user;

    @FXML
    Label lb_deposit;

    @FXML
    Label lb_update_user;

    @FXML
    Label lb_withdraw;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void onCreateUserClicked() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("create/create_user.fxml"));
            Stage stage = new Stage();
            stage.setTitle("CREATE USER");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void onUpdateUserClicked() {
        showMessage("Update user clicked");
    }

    @FXML
    public void onDepositClicked() {
        showMessage("Deposit clicked");
    }

    @FXML
    public void onWithdrawClicked() {
        showMessage("Withdraw clicked");
    }

    public static void showMessage(String msg) {
        System.out.println(msg);
    }
}
