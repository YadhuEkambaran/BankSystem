package bank.common;

import bank.employee.EmployeeController;
import bank.employee.create.CreateUserController;
import bank.employee.deposit.DepositController;
import bank.employee.payment.dashboard.DashboardController;
import bank.employee.payment.hydro.HydroController;
import bank.employee.payment.mobile.MobileController;
import bank.employee.transfer.TransferController;
import bank.employee.view.ViewController;
import bank.employee.view.edit.EditController;
import bank.employee.withdraw.WithdrawController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class SwapController {

    private Scene mScene;

    public SwapController(Scene mScene) {
        this.mScene = mScene;
    }

    public void goToMainPage() {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/bank/employee/employee.fxml")
        );
        try {

            mScene.setRoot(loader.load());
            EmployeeController controller = loader.getController();
            controller.init(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToCreateUserPage() {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/bank/employee/create/create_user.fxml")
        );
        try {
            mScene.setRoot(loader.load());
            CreateUserController controller = loader.getController();
            controller.init(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToDepositPage() {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/bank/employee/deposit/deposit.fxml")
        );
        try {
            mScene.setRoot(loader.load());
            DepositController controller = loader.getController();
            controller.init(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToWithdrawPage() {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/bank/employee/withdraw/withdraw.fxml")
        );
        try {
            mScene.setRoot(loader.load());
            WithdrawController controller = loader.getController();
            controller.init(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToTransferPage() {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/bank/employee/transfer/transfer.fxml")
        );
        try {
            mScene.setRoot(loader.load());
            TransferController controller = loader.getController();
            controller.init(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToViewPage() {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/bank/employee/view/view.fxml")
        );
        try {
            mScene.setRoot(loader.load());
            ViewController controller = loader.getController();
            controller.init(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToEditPage(String accountNo) {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/bank/employee/view/edit/edit.fxml")
        );
        try {
            mScene.setRoot(loader.load());
            EditController controller = loader.getController();
            controller.init(this);
            controller.setAccountNo(accountNo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToPaymentDashboardPage() {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/bank/employee/payment/dashboard/dashboard.fxml")
        );
        try {
            mScene.setRoot(loader.load());
            DashboardController controller = loader.getController();
            controller.init(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void goToMobilePaymentPage() {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/bank/employee/payment/mobile/mobile.fxml")
        );
        try {
            mScene.setRoot(loader.load());
            MobileController controller = loader.getController();
            controller.init(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToHydroPaymentPage() {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/bank/employee/payment/hydro/hydro.fxml")
        );
        try {
            mScene.setRoot(loader.load());
            HydroController controller = loader.getController();
            controller.init(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
