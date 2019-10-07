package bank.employee;

import bank.common.Constants;
import bank.common.InitController;
import bank.common.SwapController;
import bank.common.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.json.simple.JSONObject;

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

    @FXML
    Label label_employee_name;

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

    @FXML
    public void onLogoutClick() {
        if (mSwapController == null) return;

        mSwapController.setEmployeeDetails(null);
        mSwapController.goToLoginPage();
    }

    @Override
    public void init(SwapController controller) {
        mSwapController = controller;
        JSONObject employee = mSwapController.getEmployeeDetails();
        if (employee != null) {
            String empName = (String) employee.get(Constants.JsonKeys.EMPLOYEE_NAME);
            String empId = (String) employee.get(Constants.JsonKeys.EMPLOYEE_ID);
            label_employee_name.setText(empName + "(" + empId + ")");
        } else {
            mSwapController.goToLoginPage();
        }

    }
}
