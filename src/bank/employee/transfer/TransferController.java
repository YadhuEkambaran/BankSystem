package bank.employee.transfer;

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

public class TransferController implements Initializable, InitController {

    @FXML
    Label label_date;

    @FXML
    JFXTextField tf_from_account_no;

    @FXML
    JFXTextField tf_to_account_no;

    @FXML
    JFXTextField tf_amount;

    private SwapController mSwapController;

    @Override
    public void init(SwapController controller) {
        mSwapController = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label_date.setText(Utils.loadDate());
    }

    public void onTransferClicked() {
        String fromAccount = tf_from_account_no.getText();
        String toAccount = tf_to_account_no.getText();
        String amount = tf_amount.getText();

        if (fromAccount.equals("")) {
            Utils.showDialog("From account number can not be empty", Alert.AlertType.ERROR);
            return;
        } else if (toAccount.equals("")) {
            Utils.showDialog("To account number can not be empty", Alert.AlertType.ERROR);
            return;
        } else if (amount.equals("")) {
            Utils.showDialog("Amount can not be empty", Alert.AlertType.ERROR);
            return;
        }

        float amt = Float.valueOf(amount);
        if (amt <= 0) {
            Utils.showDialog("Enter a valid amount", Alert.AlertType.ERROR);
            return;
        }

        String fromFileName = Constants.FOLDER_NAME_USER + fromAccount + Constants.FILE_FORMAT;
        String toFileName = Constants.FOLDER_NAME_USER + toAccount + Constants.FILE_FORMAT;
        File fromUserAccount = new File(fromFileName);
        File toUserAccount = new File(toFileName);
        if (fromUserAccount.exists() && toUserAccount.exists()) {
            try {
                JSONObject fromAccountDetails = FolderFileManager.read(fromFileName);
                JSONObject toAccountDetails = FolderFileManager.read(toFileName);


                if (fromAccountDetails != null && toAccountDetails != null) {
                    JSONObject fromUserDetails = (JSONObject) fromAccountDetails.get(Constants.JsonKeys.USER_DETAILS);
                    JSONObject toUserDetails = (JSONObject) toAccountDetails.get(Constants.JsonKeys.USER_DETAILS);

                    double fromUserBalance = (Double) fromUserDetails.getOrDefault(Constants.JsonKeys.USER_BALANCE, 0.0f);
                    double toUserBalance = (Double) toUserDetails.getOrDefault(Constants.JsonKeys.USER_BALANCE, 0.0f);



                    if (fromUserBalance < amt) {
                        Utils.showDialog("User don't have sufficient amount to do transfer", Alert.AlertType.ERROR);
                        return;
                    }

                    fromUserBalance = fromUserBalance - amt;
                    toUserBalance = toUserBalance + amt;

                    fromUserDetails.put(Constants.JsonKeys.USER_BALANCE, fromUserBalance);
                    toUserDetails.put(Constants.JsonKeys.USER_BALANCE, toUserBalance);

                    fromAccountDetails.put(Constants.JsonKeys.USER_DETAILS, fromUserDetails);
                    toAccountDetails.put(Constants.JsonKeys.USER_DETAILS, toUserDetails);

                    //START - Adding transfer details to file
                    JSONArray fromAccountTransfer = fromAccountDetails.get(Constants.JsonKeys.USER_TRANSFER) == null? new JSONArray() : (JSONArray) fromAccountDetails.get(Constants.JsonKeys.USER_TRANSFER);
                    JSONObject transferObject = new JSONObject();
                    transferObject.put(Constants.JsonKeys.USER_TRANSFER_DATE, System.currentTimeMillis());
                    transferObject.put(Constants.JsonKeys.USER_TRANSFER_AMOUNT, amt);
                    transferObject.put(Constants.JsonKeys.USER_TRANSFER_ACCOUNT, toAccount);
                    fromAccountTransfer.add(transferObject);
                    fromAccountDetails.put(Constants.JsonKeys.USER_TRANSFER, fromAccountTransfer);
                    //END - Adding transfer details to file

                    FolderFileManager.write(fromFileName, fromAccountDetails);

                    //START - Adding deposit details to file
                    JSONArray toAccountDeposit = toAccountDetails.get(Constants.JsonKeys.USER_DEPOSIT) == null? new JSONArray() : (JSONArray) toAccountDetails.get(Constants.JsonKeys.USER_DEPOSIT);
                    JSONObject depositObject = new JSONObject();
                    depositObject.put(Constants.JsonKeys.USER_DEPOSIT_DATE, System.currentTimeMillis());
                    depositObject.put(Constants.JsonKeys.USER_DEPOSIT_AMOUNT, amt);
                    toAccountDeposit.add(depositObject);
                    toAccountDetails.put(Constants.JsonKeys.USER_DEPOSIT, toAccountDeposit);
                    //END - Adding deposit details to file

                    FolderFileManager.write(toFileName, toAccountDetails);


                    Utils.showDialog("Amount transferred successfully" , Alert.AlertType.INFORMATION);
                    clearFields();
                }

            } catch (Exception e) {
                e.printStackTrace();
                Utils.showDialog("Something went wrong, Please try again later" , Alert.AlertType.ERROR);
            }
        } else if (!fromUserAccount.exists()) {
            Utils.showDialog("From account number does not exist" , Alert.AlertType.ERROR);
        } else if (!toUserAccount.exists()) {
            Utils.showDialog("To account number does not exist" , Alert.AlertType.ERROR);
        }
    }

    public void onBackClicked() {
        mSwapController.goToMainPage();
    }

    private void clearFields() {
        tf_from_account_no.setText("");
        tf_to_account_no.setText("");
        tf_amount.setText("");
    }

}
