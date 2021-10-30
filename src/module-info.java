module Proyecto {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.java;
    requires mail;
    requires java.rmi;//esto no es necesario

    opens ferreteria;
    opens controllerPersona;
}