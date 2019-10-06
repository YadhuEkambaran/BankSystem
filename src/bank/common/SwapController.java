package bank.common;

import bank.employee.EmployeeController;
import bank.employee.create.CreateUserController;
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
}
