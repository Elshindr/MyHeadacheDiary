module com.example.myheadachediary {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires java.desktop;

    requires jaxb.api;

    exports com.example.myheadachediary.model;
    opens com.example.myheadachediary.model to javafx.fxml;
    exports com.example.myheadachediary.controler;
    opens com.example.myheadachediary.controler to javafx.fxml;
    opens com.example.myheadachediary.tools to javafx.fxml;
    exports com.example.myheadachediary.tools;
    opens com.example.myheadachediary.view to javafx.fxml;
    exports com.example.myheadachediary.view;


}