package com.example.myheadachediary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HeadacheDiaryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HeadacheDiaryApplication.class.getResource("SceneBuilder_Migraine.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("MyHeadacheDiary!");
        stage.setMinHeight(600.0);
        stage.setMinWidth(860.0);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}