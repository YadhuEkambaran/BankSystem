package bank.employee;

import bank.common.InitController;
import bank.common.SwapController;
import bank.common.Utils;
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
    Label label_date;

    @FXML
    Label lb_create_user;

    @FXML
    Label lb_deposit;

    @FXML
    Label lb_update_user;

    @FXML
    Label lb_withdraw;

    @FXML
    Label lb_transfer;

    @FXML
    Label lb_bill_payment;

    private SwapController mSwapController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label_date.setText(Utils.loadDate());
    }

    @FXML
    public void onCreateUserClicked() {
        if (mSwapController == null) return;

        mSwapController.goToCreateUserPage();
    }

    @FXML
    public void onUpdateUserClicked() {
        if (mSwapController == null) return;

        mSwapController.goToViewPage();
    }

    @FXML
    public void onDepositClicked() {
        if (mSwapController == null) return;

        mSwapController.goToDepositPage();
    }

    @FXML
    public void onWithdrawClicked() {
        if (mSwapController == null) return;

        mSwapController.goToWithdrawPage();
    }

    @FXML
    public void onTransferCicked() {
        if (mSwapController == null) return;

        mSwapController.goToTransferPage();
    }

    @FXML
    public void onBillPaymentCicked() {
        if (mSwapController == null) return;

        mSwapController.goToPaymentDashboardPage();
    }

    public static void showMessage(String msg) {
        System.out.println(msg);
    }

    @Override
    public void init(SwapController controller) {
        mSwapController = controller;
    }
}
