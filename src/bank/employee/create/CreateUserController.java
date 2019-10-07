package bank.employee.create;

import bank.common.Constants;
import bank.common.InitController;
import bank.common.SwapController;
import bank.common.Utils;
import bank.model.FolderFileManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.ResourceBundle;

public class CreateUserController implements Initializable, InitController {

    @FXML
    JFXTextField tf_first_name;

    @FXML
    JFXTextField tf_personnal_id;

    @FXML
    JFXTextField tf_last_name;

    @FXML
    JFXButton btn_create;

    @FXML
    JFXButton btn_back;

    @FXML
    JFXTextField tf_middle_name;

    @FXML
    DatePicker dp_dob;

    @FXML
    JFXTextField tf_address;

    @FXML
    JFXTextField tf_city;

    @FXML
    JFXTextField tf_province;

    @FXML
    JFXTextField tf_mobile_no;

    @FXML
    JFXRadioButton rb_checking;

    @FXML
    JFXRadioButton rb_savings;

    @FXML
    JFXTextField tf_initial_deposit;


    private SwapController mSwapController;

    @Override
    public void init(SwapController controller) {
        mSwapController = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup = new ToggleGroup();
        rb_checking.setToggleGroup(toggleGroup);
        rb_savings.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                if (t1 == rb_checking) {
                    tf_initial_deposit.setVisible(false);
                } else {
                    tf_initial_deposit.setVisible(true);
                }
            }
        });
    }

    @FXML
    public void onCreateClick() {
        String firstName = tf_first_name.getText();
        String middleName = tf_middle_name.getText();
        String lastName = tf_last_name.getText();
        String sin = tf_personnal_id.getText();
        String address = tf_address.getText();
        String city = tf_city.getText();
        String province = tf_province.getText();
        String mobileNo = tf_mobile_no.getText();
        LocalDate date = dp_dob.getValue();

        String balance = tf_initial_deposit.getText();
        float bal = 0.0f;
        if (balance != null && balance.length() > 0) {
            bal = Float.valueOf(balance);
        }
        int accountType;
        if (rb_checking.isSelected()) {
            accountType = Constants.ACCOUNT_TYPE_CHECKING;
        } else {
            accountType = Constants.ACCOUNT_TYPE_SAVINGS;
        }


        if (firstName.equals("")) {
            Utils.showDialog("First name can not be empty", Alert.AlertType.ERROR);
            return;
        } else if (lastName.equals("")) {
            Utils.showDialog("Last name can not be empty", Alert.AlertType.ERROR);
            return;
        } else if (date == null) {
            Utils.showDialog("Date can not be empty", Alert.AlertType.ERROR);
            return;
        } else if (sin.equals("")) {
            Utils.showDialog("Social Number field can not be empty", Alert.AlertType.ERROR);
            return;
        } else if (address.equals("")) {
            Utils.showDialog("Address can not be empty", Alert.AlertType.ERROR);
            return;
        } else if (city.equals("")) {
            Utils.showDialog("City can not be empty", Alert.AlertType.ERROR);
            return;
        } else if (province.equals("")) {
            Utils.showDialog("Province can not be empty", Alert.AlertType.ERROR);
            return;
        } else if (mobileNo.equals("")) {
            Utils.showDialog("Mobile number can not be empty", Alert.AlertType.ERROR);
            return;
        }

        String pattern = "dd/MM/YYYY";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
        String dob = date.format(dateFormatter);

        Random random = new Random();
        String PIN = String.format("%04d", random.nextInt(10000));

        String debitCardNo = debitCardNo();
        int expiryDate = random.nextInt(29) + 1;

        JSONObject userDerails = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constants.JsonKeys.USER_FIRST_NAME, firstName);
        jsonObject.put(Constants.JsonKeys.USER_MIDDLE_NAME, middleName);
        jsonObject.put(Constants.JsonKeys.USER_LAST_NAME, lastName);
        jsonObject.put(Constants.JsonKeys.USER_SIN, sin);
        jsonObject.put(Constants.JsonKeys.USER_DOB, dob);
        jsonObject.put(Constants.JsonKeys.USER_ADDRESS, address);
        jsonObject.put(Constants.JsonKeys.USER_CITY, city);
        jsonObject.put(Constants.JsonKeys.USER_PROVINCE, province);
        jsonObject.put(Constants.JsonKeys.USER_COUNTRY, "Canada");
        jsonObject.put(Constants.JsonKeys.USER_MOBILE_NO, mobileNo);
        jsonObject.put(Constants.JsonKeys.USER_ACCOUNT_TYPE, accountType);
        jsonObject.put(Constants.JsonKeys.USER_BALANCE, bal);
        jsonObject.put(Constants.JsonKeys.USER_PIN, PIN);
        jsonObject.put(Constants.JsonKeys.USER_DEBIT_CARD_NO, debitCardNo);
        jsonObject.put(Constants.JsonKeys.USER_DEBIT_CARD_NO_EXPIRY_DATE, expiryDate);
        jsonObject.put(Constants.JsonKeys.USER_DEBIT_CARD_NO_EXPIRY_YEAR, 2024);

        userDerails.put(Constants.JsonKeys.USER_DETAILS, jsonObject);

        try {
            long fileName = FolderFileManager.createUserFile();
            if (fileName > 405) {
                FolderFileManager.write(Constants.FOLDER_NAME_USER + fileName + ".txt", userDerails);

                Utils.showDialog("Created new user\nAccount no - " + fileName + "\nAutogenerated PIN - " + PIN, Alert.AlertType.INFORMATION);
            } else {
                FolderFileManager.showMessage("Some error occurred");
            }
        } catch (IOException e) {
            FolderFileManager.showMessage("Some error occurred");
            e.printStackTrace();
        }
    }

    private String debitCardNo() {
        Random random = new Random();
        String card1 = String.format("%04d", random.nextInt(10000));
        String card2 = String.format("%04d", random.nextInt(10000));
        String card3 = String.format("%04d", random.nextInt(10000));
        String card4 = String.format("%04d", random.nextInt(10000));

        return card1 + card2 + card3 + card4;
    }

    public void onBackClicked() {
        if (mSwapController == null) return;

        mSwapController.goToMainPage();
    }
}
