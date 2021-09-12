module com.example.myheadachediary {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;

    opens com.example.myheadachediary to javafx.fxml;
    exports com.example.myheadachediary;
}