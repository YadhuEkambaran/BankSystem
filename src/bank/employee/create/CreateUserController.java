package bank.employee.create;

import bank.common.Constants;
import bank.common.InitController;
import bank.common.SwapController;
import bank.common.Utils;
import bank.model.FolderFileManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.URL;
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

    private SwapController mSwapController;

    @Override
    public void init(SwapController controller) {
        mSwapController = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

        if (firstName.equals("")) {
            Utils.showDialog("First name can not be empty", Alert.AlertType.ERROR);
            return;
        } else if (lastName.equals("")) {
            Utils.showDialog("Last name can not be empty", Alert.AlertType.ERROR);
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

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constants.JsonKeys.USER_FIRST_NAME, firstName);
        jsonObject.put(Constants.JsonKeys.USER_MIDDLE_NAME, middleName);
        jsonObject.put(Constants.JsonKeys.USER_LAST_NAME, lastName);
        jsonObject.put(Constants.JsonKeys.USER_SIN, sin);
        jsonObject.put(Constants.JsonKeys.USER_DOB, "");
        jsonObject.put(Constants.JsonKeys.USER_ADDRESS, address);
        jsonObject.put(Constants.JsonKeys.USER_CITY, city);
        jsonObject.put(Constants.JsonKeys.USER_PROVINCE, province);
        jsonObject.put(Constants.JsonKeys.USER_COUNTRY, "Canada");
        jsonObject.put(Constants.JsonKeys.USER_MOBILE_NO, mobileNo);

        try {
            long fileName = FolderFileManager.createUserFile();
            if (fileName > 405) {
                FolderFileManager.write(Constants.FOLDER_NAME_USER + fileName + ".txt", jsonObject);
            } else {
                FolderFileManager.showMessage("Some error occurred");
            }
        } catch (IOException e) {
            FolderFileManager.showMessage("Some error occurred");
            e.printStackTrace();
        }
    }
}
