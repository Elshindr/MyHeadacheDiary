package com.example.myheadachediary;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    //private List<String> obsSymptom = new ArrayList<String>();
    ObservableList<String> obsSymp = FXCollections.observableArrayList("Arnab", "Andrew", "Ankit", "None");
    @FXML
    private ChoiceBox cobSeverity ;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SceneBuilder_Migraine.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        String st[] = { "Arnab", "Andrew", "Ankit", "None" };

        // create a choiceBox
        ChoiceBox c = new ChoiceBox(FXCollections.observableArrayList(st));

        // string array
        //String st[] = { "Severe", "Fort", "Moderate", "Gerable" };

        stage.setTitle("MyHeadacheDiary!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}