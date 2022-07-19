package com.example.headachediary.view;

import com.example.headachediary.controler.Controle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main Class application
 * Create a new JavaFx scene, load a fxml file then display it.
 */
public class MainMyDiaryApplication extends Application {

    private Controle control;

    /**
     * Method main to launch the app
     * @param args array String
     */
    public static void main(String[] args) {
        System.out.println("nik2");
        launch();
    }
    /**
     * Method to create a scene then load the fxml file in the provided stage
     * @param aStage Stage
     * @throws IOException
     */
    @Override
    public void start(Stage aStage) throws IOException {
        System.out.println("nik3");
        FXMLLoader fxmlLoader = new FXMLLoader(MainMyDiaryApplication.class.getResource("SceneBuilder_Migraine.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        aStage.setTitle("MyHeadacheDiary!");
        aStage.setMinHeight(600.0);
        aStage.setMinWidth(860.0);
        aStage.setResizable(true);
        aStage.setScene(scene);
        aStage.show();
    }


}