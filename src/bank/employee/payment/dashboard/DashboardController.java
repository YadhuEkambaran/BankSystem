package bank.employee.payment.dashboard;

import bank.common.InitController;
import bank.common.SwapController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements InitController, Initializable {

    @FXML
    Label label_hydro;

    @FXML
    Label label_mobile;

    private SwapController mSwaController;

    @Override
    public void init(SwapController controller) {
        mSwaController = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void onHydroClicked() {
        if (mSwaController == null) return;

        mSwaController.goToHydroPaymentPage();
    }

    @FXML
    public void onMobileClicked() {
        if (mSwaController == null) return;

        mSwaController.goToMobilePaymentPage();
    }
}
