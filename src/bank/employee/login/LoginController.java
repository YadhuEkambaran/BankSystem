package bank.employee.login;

import bank.common.InitController;
import bank.common.SwapController;
import bank.common.Utils;
import bank.model.FolderFileManager;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import org.json.simple.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable, InitController {

    @FXML
    JFXTextField user_account_no;

    @FXML
    JFXPasswordField pf_pin;

    private SwapController mSwapController;

    @Override
    public void init(SwapController controller) {
        mSwapController = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void onLoginClick() {
        String empId = user_account_no.getText();
        String pin = pf_pin.getText();

        if (empId.equals("")) {
            Utils.showDialog("Enter a valid employee ID" , Alert.AlertType.ERROR);
        } else if (pin.equals("")) {
            Utils.showDialog("Enter a employee PIN" , Alert.AlertType.ERROR);
        }

        JSONObject employeeDetails = FolderFileManager.checkLogin(empId, pin);
        if (employeeDetails == null) {
            Utils.showDialog("Invalid credentials" , Alert.AlertType.ERROR);
        } else {
            if (mSwapController == null) return;

            mSwapController.setEmployeeDetails(employeeDetails);
            mSwapController.goToMainPage();
        }
    }
}
