package bank.employee;

import bank.common.InitController;
import bank.common.SwapController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable, InitController {

    @FXML
    Label lb_create_user;

    @FXML
    Label lb_deposit;

    @FXML
    Label lb_update_user;

    @FXML
    Label lb_withdraw;

    private SwapController mSwapController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void onCreateUserClicked() {
        if (mSwapController == null) return;

        mSwapController.goToCreateUserPage();
    }

    @FXML
    public void onUpdateUserClicked() {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("This is a Dialog"));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
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

    @Override
    public void init(SwapController controller) {
        mSwapController = controller;
    }
}
