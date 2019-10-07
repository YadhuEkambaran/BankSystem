package bank.employee.view.edit;

import bank.common.Constants;
import bank.common.InitController;
import bank.common.SwapController;
import bank.common.Utils;
import bank.model.FolderFileManager;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import org.json.simple.JSONObject;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EditController implements Initializable, InitController {

    @FXML
    JFXTextField tf_first_name;

    @FXML
    JFXTextField tf_last_name;

    @FXML
    DatePicker dp_dob;

    @FXML
    JFXTextField tf_middle_name;

    @FXML
    JFXTextField tf_personnal_id;

    @FXML
    Label label_account_no;

    @FXML
    JFXTextField tf_address;

    @FXML
    JFXTextField tf_city;

    @FXML
    JFXTextField tf_province;

    @FXML
    JFXTextField tf_mobile_no;

    @FXML
    Label label_balance;

    @FXML
    JFXRadioButton rb_checking;

    @FXML
    JFXRadioButton rb_savings;

    @FXML
    Label label_card_no;

    @FXML
    Label label_name;

    @FXML
    Label label_date;

    @FXML
    Label label_year;

    private SwapController mSwapController;

    private String mAccountNo;

    private JSONObject accountDetails;

    private String fileName;

    @Override
    public void init(SwapController controller) {
        mSwapController = controller;
    }

    public void setAccountNo(String accountNo) {
        mAccountNo = accountNo;
        fileName = Constants.FOLDER_NAME_USER + mAccountNo + Constants.FILE_FORMAT;
        try {
            accountDetails = FolderFileManager.read(fileName);
            setupUserDetails();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup = new ToggleGroup();
        rb_checking.setToggleGroup(toggleGroup);
        rb_savings.setToggleGroup(toggleGroup);

        dp_dob.setVisible(false);
    }

    private void setupUserDetails() {
        JSONObject userDetails = (JSONObject) accountDetails.get(Constants.JsonKeys.USER_DETAILS);
        tf_first_name.setText(userDetails.get(Constants.JsonKeys.USER_FIRST_NAME).toString());
        tf_middle_name.setText(userDetails.get(Constants.JsonKeys.USER_MIDDLE_NAME).toString());
        tf_last_name.setText(userDetails.get(Constants.JsonKeys.USER_LAST_NAME).toString());
        tf_personnal_id.setText(userDetails.get(Constants.JsonKeys.USER_SIN).toString());

        tf_address.setText(userDetails.get(Constants.JsonKeys.USER_ADDRESS).toString());
        tf_city.setText(userDetails.get(Constants.JsonKeys.USER_CITY).toString());
        tf_province.setText(userDetails.get(Constants.JsonKeys.USER_PROVINCE).toString());
        tf_mobile_no.setText(userDetails.get(Constants.JsonKeys.USER_MOBILE_NO).toString());

        long type = (long) userDetails.get(Constants.JsonKeys.USER_ACCOUNT_TYPE);
        if (type == 1) rb_checking.setSelected(true);
        else rb_savings.setSelected(true);

        label_balance.setText(userDetails.get(Constants.JsonKeys.USER_BALANCE).toString());
        String cardNo = (String) userDetails.get(Constants.JsonKeys.USER_DEBIT_CARD_NO);
        String section1 = cardNo.substring(0, 4);
        String section2 = cardNo.substring(4, 8);
        String section3 = cardNo.substring(8, 12);
        String section4 = cardNo.substring(12, 16);

        String seperator = "     ";

        label_card_no.setText(section1 + seperator + section2 + seperator + section3 + seperator + section4);
        label_date.setText(String.valueOf(userDetails.get(Constants.JsonKeys.USER_DEBIT_CARD_NO_EXPIRY_DATE)));
        label_year.setText(String.valueOf(userDetails.get(Constants.JsonKeys.USER_DEBIT_CARD_NO_EXPIRY_YEAR)));
        String name = userDetails.get(Constants.JsonKeys.USER_FIRST_NAME).toString() + " " +
                userDetails.get(Constants.JsonKeys.USER_MIDDLE_NAME).toString() + " " +
                userDetails.get(Constants.JsonKeys.USER_LAST_NAME).toString();
         label_name.setText(name);
        label_account_no.setText(mAccountNo);

//        DateTimeFormatter df = DateTimeFormatter.ofPattern("ddMMYYYY");
//        LocalDate date = LocalDate.parse(userDetails.get(Constants.JsonKeys.USER_DOB).toString().replace("/", ""), df);
//        dp_dob.setValue(date);

    }

    @FXML
    public void onUpdateClick() {
        try {
            String firstName = tf_first_name.getText();
            String middleName = tf_middle_name.getText();
            String lastName = tf_last_name.getText();
            String socialNo = tf_personnal_id.getText();
            String address = tf_address.getText();
            String city = tf_city.getText();
            String province = tf_province.getText();
            String mobileNo = tf_mobile_no.getText();
            LocalDate date = dp_dob.getValue();


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
            } else if (socialNo.equals("")) {
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

            JSONObject userDetails = (JSONObject) accountDetails.get(Constants.JsonKeys.USER_DETAILS);
            userDetails.put(Constants.JsonKeys.USER_FIRST_NAME, firstName);
            userDetails.put(Constants.JsonKeys.USER_LAST_NAME, lastName);
            userDetails.put(Constants.JsonKeys.USER_MIDDLE_NAME, middleName);
            userDetails.put(Constants.JsonKeys.USER_SIN, socialNo);
            userDetails.put(Constants.JsonKeys.USER_DOB, dob);
            userDetails.put(Constants.JsonKeys.USER_ACCOUNT_TYPE, accountType);
            userDetails.put(Constants.JsonKeys.USER_ADDRESS, address);
            userDetails.put(Constants.JsonKeys.USER_CITY, city);
            userDetails.put(Constants.JsonKeys.USER_PROVINCE, province);
            userDetails.put(Constants.JsonKeys.USER_MOBILE_NO, mobileNo);
            accountDetails.put(Constants.JsonKeys.USER_DETAILS, userDetails);

            FolderFileManager.write(fileName, accountDetails);

            Utils.showDialog("User details updated successfully", Alert.AlertType.INFORMATION);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void onBackClicked() {
        if (mSwapController == null) return;

        mSwapController.goToViewPage();
    }
}
