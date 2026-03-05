module lk.ijse.myap {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires  java.sql;
    requires net.sf.jasperreports.core;
    requires org.slf4j;



    opens lk.ijse.myap.controller to javafx.fxml;
    opens lk.ijse.myap.dto to java.base;
    opens lk.ijse.myap.entity to java.base;

    
    exports lk.ijse.myap;
    exports lk.ijse.myap.controller;
    exports lk.ijse.myap.dto;
}
