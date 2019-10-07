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
    opens bank.employee.deposit;
    opens bank.employee.withdraw;
    opens bank.employee.transfer;
    opens bank.employee.view;
    opens bank.employee.view.edit;
    opens bank.employee.payment.mobile;
    opens bank.employee.payment.dashboard;
    opens bank.employee.payment.hydro;
}