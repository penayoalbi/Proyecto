module Proyecto {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.java;
    requires mail;
    requires java.rmi;
    requires kernel;
    requires pdfa;
    requires pdftest;
    requires layout;
    requires io;

    opens ferreteria;
    opens controllerPersona;
}