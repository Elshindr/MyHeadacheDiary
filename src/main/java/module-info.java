module com.example.myheadachediary {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires java.desktop;
    requires xstream;
    requires org.json;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires jaxb.api;

    opens com.example.myheadachediary to javafx.fxml;
    exports com.example.myheadachediary;
    exports com.example.myheadachediary.model;
    opens com.example.myheadachediary.model to javafx.fxml;
    exports com.example.myheadachediary.controler;
    opens com.example.myheadachediary.controler to javafx.fxml;

}