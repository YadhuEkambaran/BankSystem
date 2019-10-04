module BankSystem {

    requires javafx.fxml;
    requires javafx.controls;
    requires com.jfoenix;
    requires json.simple;
    requires org.apache.commons.io;

    opens bank;
    opens bank.user;
    opens bank.employee;
    opens bank.employee.create;
}