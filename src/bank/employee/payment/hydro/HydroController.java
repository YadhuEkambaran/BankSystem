package bank.employee.payment.hydro;

import bank.common.Constants;
import bank.common.InitController;
import bank.common.SwapController;
import bank.common.Utils;
import bank.model.FolderFileManager;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HydroController implements Initializable, InitController {

    @FXML
    JFXTextField tf_account_no;

    @FXML
    JFXTextField tf_card_no;

    @FXML
    JFXTextField tf_amount;

    @FXML
    JFXComboBox<String> cb_provider;

    @FXML
    JFXComboBox<Integer> cb_date;

    @FXML
    JFXComboBox<Integer> cb_year;

    private SwapController mSwapController;

    @Override
    public void init(SwapController controller) {
        mSwapController = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> providers = new ArrayList<>();
        providers.add("Bell");
        providers.add("Roger");
        providers.add("Virgin Mobiles");
        providers.add("Fido");
        providers.add("Freedom");

        for (String provider :
                providers) {
            cb_provider.getItems().add(provider);
        }

        for (int i = 1; i <= 31; i++) {
            cb_date.getItems().add(i);
        }

        for (int i = 2020; i < 2027; i++) {
            cb_year.getItems().add(i);
        }
    }

    @FXML
    public void onBackClicked() {
        if (mSwapController == null) return;

        mSwapController.goToPaymentDashboardPage();
    }

    @FXML
    public void onMakePaymentClicked() {
        String mobileNo = tf_account_no.getText();
        String cardNo = tf_card_no.getText();
        String amount = tf_amount.getText();
        int date = cb_date.getValue();
        int year = cb_year.getValue();

        if (mobileNo.equals("")) {
            Utils.showDialog("Mobile number can not be empty", Alert.AlertType.ERROR);
            return;
        } else if (amount.equals("")) {
            Utils.showDialog("Amount can not be empty", Alert.AlertType.ERROR);
            return;
        } else if (cardNo.equals("")) {
            Utils.showDialog("Card number can not be empty", Alert.AlertType.ERROR);
            return;
        } else if (cardNo.length() != 16) {
            Utils.showDialog("Enter a valid card number", Alert.AlertType.ERROR);
            return;
        }


        float amt = Float.valueOf(amount);
        checkCardDetails(cardNo, date, year, amt);

    }

    private boolean checkCardDetails(String enteredCardNo, int enteredDate, int enteredYear, float amount) {
        JSONObject userAccountDetails = FolderFileManager.getUserDetailsBasedOnCardNo(enteredCardNo);
        if (userAccountDetails == null) {
            Utils.showDialog("No user exist with this card no", Alert.AlertType.ERROR);
        } else {
            String cardNo = (String) userAccountDetails.get(Constants.JsonKeys.USER_DEBIT_CARD_NO);
            long date = (long) userAccountDetails.get(Constants.JsonKeys.USER_DEBIT_CARD_NO_EXPIRY_DATE);
            long year = (long) userAccountDetails.get(Constants.JsonKeys.USER_DEBIT_CARD_NO_EXPIRY_YEAR);

            if (enteredCardNo.equals(cardNo) && enteredDate == date && enteredYear == year) {
                double balance = (double) userAccountDetails.get(Constants.JsonKeys.USER_BALANCE);

                if (amount <= balance) {
                    balance = balance - amount;
                    JSONObject accountDetails = FolderFileManager.getUserAccountDetailsBasedOnCardNo(enteredCardNo);
                    doWithdrawal(accountDetails, balance, amount, enteredCardNo);

                    clearFields();
                    Utils.showDialog("Payment completed successfully", Alert.AlertType.INFORMATION);

                } else {
                    Utils.showDialog("User does not have sufficient balance", Alert.AlertType.ERROR);
                }
            } else {
                Utils.showDialog("Card details entered is invalid", Alert.AlertType.ERROR);
            }
        }

        return false;
    }

    private boolean doWithdrawal(JSONObject accountDetails, double balance, double withdrawAmount, String cardNo) {
        JSONObject userDetails = (JSONObject) accountDetails.get(Constants.JsonKeys.USER_DETAILS);
        userDetails.put(Constants.JsonKeys.USER_BALANCE, balance);
        accountDetails.put(Constants.JsonKeys.USER_DETAILS, userDetails);

        JSONArray withdrawArray = accountDetails.get(Constants.JsonKeys.USER_WITHDRAW) == null? new JSONArray() : (JSONArray) accountDetails.get(Constants.JsonKeys.USER_WITHDRAW);
        JSONObject withdrawObject = new JSONObject();
        withdrawObject.put(Constants.JsonKeys.USER_WITHDRAW_DATE, System.currentTimeMillis());
        withdrawObject.put(Constants.JsonKeys.USER_WITHDRAW_AMOUNT, withdrawAmount);
        withdrawArray.add(withdrawObject);
        accountDetails.put(Constants.JsonKeys.USER_WITHDRAW, withdrawArray);

        try {
            FolderFileManager.write(FolderFileManager.getUserFileNameBasedOnCardNo(cardNo), accountDetails);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private void clearFields() {
        tf_account_no.setText("");
        tf_card_no.setText("");
        tf_amount.setText("");
        cb_provider.getSelectionModel().clearSelection();
        cb_date.getSelectionModel().clearSelection();
        cb_year.getSelectionModel().clearSelection();
    }
}
