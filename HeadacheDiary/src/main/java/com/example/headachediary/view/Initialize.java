package com.example.headachediary.view;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Class View: Initialize views objects
 */
public abstract class Initialize {


    /**
     * Method to initialize dates objects
     * @param dtpDay
     * @param spinHour
     * @param spinMin
     */
    public static void initDay(DatePicker dtpDay, Spinner<Integer> spinHour, Spinner<Integer> spinMin){
        // Setting the current date and time
        dtpDay.setValue(LocalDate.now());
        LocalTime ltInitSpinners = LocalTime.now();

        // Value factory for Minutes and Hours
        SpinnerValueFactory<Integer> valueHours = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24, ltInitSpinners.getHour());
        SpinnerValueFactory<Integer> valueMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, ltInitSpinners.getMinute());
        valueMin.increment(5);

        spinHour.setValueFactory(valueHours);
        spinMin.setValueFactory(valueMin);

        // Setting the spinner editable
        spinHour.setEditable(true);
        spinMin.setEditable(true);
    }


    /**
     * Method to initialize and set cobChoice objects
     * @param cob
     * @param str
     * @param obsArray
     */
    public static void initSymptom(ChoiceBox<String> cob, String str, ObservableList<String> obsArray) {
        cob.setItems(obsArray);
        cob.setValue(str);
    }


    /**
     * Method to initialize and set medication objects
     * @param spinMed
     */
    public static void initMedication(Spinner<Integer> spinMed) {
        // Value factory for Minutes and Hours
        SpinnerValueFactory<Integer> valueMed = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0);
        spinMed.setValueFactory(valueMed);

        // Setting the spinner editable
        spinMed.setEditable(true);
    }

    /**
     * Method to initialize tabDiary Headache TableView
     * @param tabHd array TableColumn with value for Headache TableView
     */
    public static void tabDiaryInitializeHd(TableColumn[] tabHd) {
        tabHd[0].setCellValueFactory(new PropertyValueFactory<>("idHeadache"));
        tabHd[0].setComparator(tabHd[0].getComparator().reversed());
        tabHd[1].setCellValueFactory(new PropertyValueFactory<>("startHeadache"));
        tabHd[2].setCellValueFactory(new PropertyValueFactory<>("endHeadache"));
        tabHd[3].setCellValueFactory(new PropertyValueFactory<>("nbEpisodes"));
    }

    /**
     * Method to initialize tabDiary Episode TableView
     * @param tabEp array TableColumn with value for Episode TableView
     */
    public static void tabDiaryInitializeEp(TableColumn[] tabEp){
        // Set TabView tabEpisodes
        // Defines how to fill data for each cell with value from get property of Object.
        tabEp[0].setCellValueFactory(new PropertyValueFactory<>("idEpisode"));
        tabEp[0].setPrefWidth(20);
        tabEp[1].setCellValueFactory(new PropertyValueFactory<>("strDates"));
        tabEp[1].setPrefWidth(125);
        tabEp[2].setCellValueFactory(new PropertyValueFactory<>("cvsSymp"));
        tabEp[2].setPrefWidth(150);
        tabEp[3].setCellValueFactory(new PropertyValueFactory<>("cvsSensi"));
        tabEp[3].setPrefWidth(80);
        tabEp[4].setCellValueFactory(new PropertyValueFactory<>("cvsTrigger"));
        tabEp[4].setPrefWidth(75);
        tabEp[5].setCellValueFactory(new PropertyValueFactory<>("cvsMed"));
        tabEp[5].setPrefWidth(143);
        tabEp[6].setCellValueFactory(new PropertyValueFactory<>("comment"));
        tabEp[6].setPrefWidth(230);
        // durée ??
    }

    /**
     * Method to initialize all tabDiary TableView
     * @param tabEp array TableColumn with value for Episode TableView
     */
    public static void tabDiaryEdit(TableColumn[] tabEp) {

        // Set TabView tabEpisodes
        // Defines how to fill data for each cell with value from get property of Object.
        tabEp[0].setCellValueFactory(new PropertyValueFactory<>("idEpisode"));
        tabEp[0].setPrefWidth(20);
        tabEp[1].setCellValueFactory(new PropertyValueFactory<>("strDates"));
        tabEp[1].setPrefWidth(125);
        tabEp[2].setCellValueFactory(new PropertyValueFactory<>("mapSymp"));
        tabEp[2].setPrefWidth(150);
        tabEp[3].setCellValueFactory(new PropertyValueFactory<>("lstSensi"));
        tabEp[3].setPrefWidth(80);
        tabEp[4].setCellValueFactory(new PropertyValueFactory<>("lstTriggers"));
        tabEp[4].setPrefWidth(75);
        tabEp[5].setCellValueFactory(new PropertyValueFactory<>("mapMed"));
        tabEp[5].setPrefWidth(143);
        tabEp[6].setCellValueFactory(new PropertyValueFactory<>("comment"));
        tabEp[6].setPrefWidth(230);
        // durée ??
    }
}
