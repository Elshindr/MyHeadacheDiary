package com.example.myheadachediary.view;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.LocalTime;

public class Initialize {
    /**
     * This method initialize dates objects
     * @param day
     * @param spinHour
     * @param spinMin
     */
    public static void initDay(DatePicker day, Spinner<Integer> spinHour, Spinner<Integer> spinMin){
        // Setting the current date
        day.setValue(LocalDate.now());
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
     * This method initialize and set cobChoice objects
     * @param cob
     * @param str
     * @param obsArray
     */
    public static void initSymptom(ChoiceBox<String> cob, String str, ObservableList<String> obsArray) {
        cob.setItems(obsArray);
        cob.setValue(str);
    }


    /**
     * This method initialize and set medication objects
     * @param spinMed
     */
    public static void initMedication(Spinner<Integer> spinMed) {
        // Value factory for Minutes and Hours
        SpinnerValueFactory<Integer> valueMed = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0);
        spinMed.setValueFactory(valueMed);

        // Setting the spinner editable
        spinMed.setEditable(true);
    }


    public static void tabDiaryInitialize(TableColumn column, String property) {
        // Set TabView tabHeadache
        //InitializeControler.tabDiaryInitialize(colIdHeadache, "idHeadache");
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        //colIdHeadache.setComparator(colIdHeadache.getComparator().reversed());


    }
}
