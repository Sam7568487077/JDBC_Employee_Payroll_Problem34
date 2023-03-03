module com.example.jdbc_employee_payroll_problem34 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.jdbc_employee_payroll_problem34 to javafx.fxml;
    exports com.example.jdbc_employee_payroll_problem34;
}