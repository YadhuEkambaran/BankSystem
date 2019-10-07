package bank.employee.withdraw;

import bank.common.Constants;
import bank.common.InitController;
import bank.common.SwapController;
import bank.common.Utils;
import bank.model.FolderFileManager;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class WithdrawController implements Initializable, InitController {

    @FXML
    JFXTextField tf_account_no;

    @FXML
    JFXTextField tf_amount;

    private SwapController mSwapController;


    @Override
    public void init(SwapController controller) {
        mSwapController = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void onBackClicked() {
        mSwapController.goToMainPage();
    }

    @FXML
    public void onWithdrawClicked() {
        String accountNo = tf_account_no.getText();
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
                    if (amt > balance) {
                        Utils.showDialog("Insufficient balance" , Alert.AlertType.INFORMATION);
                        return;
                    }

                    balance = balance - amt;
                    userDetails.put(Constants.JsonKeys.USER_BALANCE, balance);
                    accountDetails.put(Constants.JsonKeys.USER_DETAILS, userDetails);

                    JSONArray withdrawArray = accountDetails.get(Constants.JsonKeys.USER_WITHDRAW) == null? new JSONArray() : (JSONArray) accountDetails.get(Constants.JsonKeys.USER_WITHDRAW);
                    JSONObject withdrawObject = new JSONObject();
                    withdrawObject.put(Constants.JsonKeys.USER_WITHDRAW_DATE, System.currentTimeMillis());
                    withdrawObject.put(Constants.JsonKeys.USER_WITHDRAW_AMOUNT, amt);
                    withdrawArray.add(withdrawObject);
                    accountDetails.put(Constants.JsonKeys.USER_WITHDRAW, withdrawArray);

                    FolderFileManager.write(fileName, accountDetails);

                    Utils.showDialog("Amount withdrawn successfully" , Alert.AlertType.INFORMATION);
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


    private void clearFields() {
        tf_account_no.setText("");
        tf_amount.setText("");
    }
}
