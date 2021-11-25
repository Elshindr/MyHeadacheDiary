package com.example.myheadachediary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This Class is the main Class application, which create a new JavaFx scene, load a fxml file then display it.
 */
public class HeadacheDiaryApplication extends Application {
    /**
     * This method create a scene then load the fxml file in the provided stage
     * @param aStage Stage
     * @throws IOException
     */
    @Override
    public void start(Stage aStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HeadacheDiaryApplication.class.getResource("SceneBuilder_Migraine.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        aStage.setTitle("MyHeadacheDiary!");
        aStage.setMinHeight(600.0);
        aStage.setMinWidth(860.0);
        aStage.setResizable(false);
        aStage.setScene(scene);
        aStage.show();
    }

    /**
     * This main method launch the app
     * @param args array String
     */
    public static void main(String[] args) {
        launch();
    }
}