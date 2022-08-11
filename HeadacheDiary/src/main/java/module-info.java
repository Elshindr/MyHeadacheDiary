module com.example.headachediary {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.logging;
    requires java.desktop;
    exports com.example.headachediary.model;
    opens com.example.headachediary.model to javafx.fxml;
    exports com.example.headachediary.controler;
    opens com.example.headachediary.controler to javafx.fxml;
    opens com.example.headachediary.tools to javafx.fxml;
    exports com.example.headachediary.tools;
    opens com.example.headachediary.view to javafx.fxml;
    exports com.example.headachediary.view;
}