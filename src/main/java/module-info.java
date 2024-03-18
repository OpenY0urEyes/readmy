module org.example.viewplan2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires retrofit2;
    requires gson;
    requires retrofit2.converter.gson;
    requires java.sql;
    opens org.example.viewplan2 to javafx.fxml;
    exports org.example.viewplan2;

    opens org.example.viewplan2.models;
}