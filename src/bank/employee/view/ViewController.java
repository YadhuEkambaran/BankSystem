package bank.employee.view;

import bank.common.InitController;
import bank.common.SwapController;
import bank.common.Utils;
import bank.model.FolderFileManager;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewController implements Initializable, InitController {

    @FXML
    JFXTextField tf_account_no;

    @FXML
    JFXListView lv_accounts;

    private SwapController mSwapController;

    private List<String> mAccountNos;

    @Override
    public void init(SwapController controller) {
        mSwapController = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lv_accounts.setVerticalGap(4.0);
        lv_accounts.setExpanded(true);
        lv_accounts.depthProperty().setValue(1);

        initialListSetup();
    }

    private void initialListSetup() {
        lv_accounts.getItems().clear();
        mAccountNos = FolderFileManager.getUserAccountList();
        addAccountToListView();

    }

    private void addAccountToListView() {
        for (String accountNo:
             mAccountNos) {
            Label label = new Label(accountNo);
            label.setPrefHeight(40);
            lv_accounts.getItems().add(label);
        }
    }

    public void onInputChanged() {
        String searchItem = tf_account_no.getText();
        if (searchItem.length() > 0) {
            refreshListItems(searchItem);
        } else {
            initialListSetup();
        }
    }
    public void onItemClicked() {
        Label label = ((Label)lv_accounts.getSelectionModel().getSelectedItem());
        if (mSwapController == null) return;

        mSwapController.goToEditPage(label.getText());
    }

    public void onBackClicked() {
        if (mSwapController == null) return;

        mSwapController.goToMainPage();
    }

    private void refreshListItems(String seachItem) {
        lv_accounts.getItems().clear();
        for (String accountNo:
             mAccountNos) {
            if (accountNo.contains(seachItem)) {
                Label label = new Label(accountNo);
                label.setPrefHeight(40);
                lv_accounts.getItems().add(label);
            }
        }
    }

}
