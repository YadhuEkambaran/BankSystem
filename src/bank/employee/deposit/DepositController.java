package bank.employee.deposit;

import bank.common.Constants;
import bank.common.InitController;
import bank.common.SwapController;
import bank.common.Utils;
import bank.model.FolderFileManager;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class DepositController implements Initializable, InitController {

    private String accountNo;

    @FXML
    Label label_date;

    @FXML
    JFXTextField tf_account_number;

    @FXML
    JFXTextField tf_amount;

    private SwapController mSwapController;

    @FXML
    public void onDepositClicked() {

        String accountNo = tf_account_number.getText();
        String amount = tf_amount.getText();

        if (accountNo.equals("")) {
            Utils.showDialog("Account number can not be empty", Alert.AlertType.ERROR);
            return;
        } else if (amount.equals("")) {
            Utils.showDialog("Amount can not be empty", Alert.AlertType.ERROR);
            return;
        }

        String fileName = Constants.FOLDER_NAME_USER + accountNo + Constants.FILE_FORMAT;
        File userAccount = new File(fileName);
        if (userAccount.exists()) {
            try {
                JSONObject accountDetails = FolderFileManager.read(fileName);
                if (accountDetails != null) {
                    JSONObject userDetails = (JSONObject) accountDetails.get(Constants.JsonKeys.USER_DETAILS);
                    double balance = (Double) userDetails.getOrDefault(Constants.JsonKeys.USER_BALANCE, 0.0f);
                    float amt = Float.valueOf(amount);
                    balance = balance + amt;
                    userDetails.put(Constants.JsonKeys.USER_BALANCE, balance);
                    accountDetails.put(Constants.JsonKeys.USER_DETAILS, userDetails);

                    JSONArray depositArray = accountDetails.get(Constants.JsonKeys.USER_DEPOSIT) == null? new JSONArray() : (JSONArray) accountDetails.get(Constants.JsonKeys.USER_DEPOSIT);
                    JSONObject depositObject = new JSONObject();
                    depositObject.put(Constants.JsonKeys.USER_DEPOSIT_DATE, System.currentTimeMillis());
                    depositObject.put(Constants.JsonKeys.USER_DEPOSIT_AMOUNT, amt);
                    depositArray.add(depositObject);
                    accountDetails.put(Constants.JsonKeys.USER_DEPOSIT, depositArray);

                    FolderFileManager.write(fileName, accountDetails);

                    Utils.showDialog("Amount deposited successfully" , Alert.AlertType.INFORMATION);
                    clearFields();
                }

            } catch (Exception e) {
                e.printStackTrace();
                Utils.showDialog("Something went wrong, Please try again later" , Alert.AlertType.ERROR);
            }
        } else {
            Utils.showDialog("Account number does not exist" , Alert.AlertType.ERROR);
        }
    }

    public void onBackClicked() {
        mSwapController.goToMainPage();
    }

    @Override
    public void init(SwapController controller) {
        mSwapController = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label_date.setText(Utils.loadDate());
    }

    private void clearFields() {
        tf_account_number.setText("");
        tf_amount.setText("");
    }
}
