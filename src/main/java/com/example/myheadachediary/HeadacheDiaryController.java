package com.example.myheadachediary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HeadacheDiaryController {
    // Serialisable list
    @FXML
    private List<Headache> lstDiary = new ArrayList<Headache>();

    // Create a empty ObservableList for Record all FileCaract object
    ObservableList<Headache> obsLstHd =  FXCollections.observableArrayList();
    // Create observable list with string variable for choiceboxes
    String[] tabSymp = {"None","Mild","Moderate","Severe","Vertigo","Vomiting","Left", "Right", "Both"};
    ObservableList<String> obsDiz = FXCollections.observableArrayList(tabSymp[0],tabSymp[1],tabSymp[2], tabSymp[4]);
    ObservableList<String> obsNausea = FXCollections.observableArrayList(tabSymp[0], tabSymp[1],tabSymp[2],tabSymp[5]);
    ObservableList<String> obsAura = FXCollections.observableArrayList(tabSymp[0],tabSymp[1],tabSymp[2]);
    ObservableList<String> obsSevere = FXCollections.observableArrayList( tabSymp[1],tabSymp[2],tabSymp[3]);
    ObservableList<String> obsSide = FXCollections.observableArrayList(tabSymp[6], tabSymp[7], tabSymp[8]);

    // Days and Duration Variables
    @FXML
    private DatePicker dayStart;
    @FXML
    private DatePicker dayEnd;
    @FXML
    private Spinner spinStartHour;
    @FXML
    private Spinner spinEndHour;
    @FXML
    private Spinner spinStartMin;
    @FXML
    private Spinner spinEndMin;


    // Symptom variables
    @FXML
    private ChoiceBox cobSeverity;
    @FXML
    private ChoiceBox cobDizziness;
    @FXML
    private ChoiceBox cobAura;
    @FXML
    private ChoiceBox cobNausea;
    @FXML
    private ChoiceBox cobSideHeadache;
    @FXML
    private TextArea txtAOther;

    // Hypersensibility variables
    @FXML
    private CheckBox chkHypLight;
    @FXML
    private CheckBox chkHypSound;
    @FXML
    private CheckBox chkHypMovement;
    @FXML
    private CheckBox chkHypSmell;

    // Triggers variables
    @FXML
    private CheckBox chkTrigLight;
    @FXML
    private CheckBox chkTrigSound;
    @FXML
    private CheckBox chkTrigSleep;
    @FXML
    private CheckBox chkTrigLake;
    @FXML
    private CheckBox chkTrigPeriod;
    @FXML
    private CheckBox chkTrigOther;
    @FXML
    private TextField txtTrigOther;

    // Medication Variables
    @FXML
    private CheckBox chkMedIbup;
    @FXML
    private CheckBox chkMedPara;
    @FXML
    private CheckBox chkMedTrip;
    @FXML
    private CheckBox chkMedOther;
    @FXML
    private TextField txtMedOther;

    @FXML
    private Spinner spinMedIbup;
    @FXML
    private Spinner spinMedPara;
    @FXML
    private Spinner spinMedTrip;
    @FXML
    private Spinner spinMedOther;

    // Button variables
    @FXML
    private Button btnAddHeadache;
    @FXML
    private Button btnAuto;

    //
    // Variables for TabDiary
    @FXML
    private TableView tabHeadache;
    @FXML
    private TableColumn colDay;
    @FXML
    private TableColumn colDuration;
    @FXML
    private TableColumn colSeverity;
    @FXML
    private TableColumn colAura;
    @FXML
    private TableColumn colNausea;
    @FXML
    private TableColumn colDizziness;
    @FXML
    private TableColumn colSide;
    @FXML
    private TableColumn colHyper;
    @FXML
    private TableColumn colOtherSymp;
    @FXML
    private TableColumn colTriggers;
    @FXML
    private TableColumn colMed;


    // Local variables for create Headache objet
    String severity;
    String dizziness;
    String aura;
    String nausea;
    String sideHeadache;
    String otherSymp;
    String hypersensibility;
    String triggers;
    String medication;

    @FXML
    private void initialize(){
        //btnAddHeadache.setDisable(true);

        // Day Init
        // Setting the current date
        dayStart.setValue(LocalDate.now());
        dayEnd.setValue(LocalDate.now());

        // Value factory for Minutes and Hours
        SpinnerValueFactory<Integer> valueStartHours = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24, 1);
        SpinnerValueFactory<Integer> valueStartMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
        SpinnerValueFactory<Integer> valueEndHours = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24, 1);
        SpinnerValueFactory<Integer> valueEndMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
        valueEndMin.increment(5);
        spinStartHour.setValueFactory(valueStartHours);
        spinEndHour.setValueFactory(valueEndHours);
        spinStartMin.setValueFactory(valueStartMin);
        spinEndMin.setValueFactory(valueEndMin);
        // Setting the spinner editable
        spinStartHour.setEditable(true);
        spinEndHour.setEditable(true);
        spinStartMin.setEditable(true);
        spinEndMin.setEditable(true);

        // Symptom init
        // Setting cobChoice Value ;
        cobDizziness.setItems(obsDiz);
        cobDizziness.setValue(tabSymp[0]);

        cobNausea.setItems(obsNausea);
        cobNausea.setValue(tabSymp[0]);

        cobAura.setItems(obsAura);
        cobAura.setValue(tabSymp[0]);

        cobSeverity.setItems(obsSevere);
        cobSeverity.setValue(tabSymp[1]);

        cobSideHeadache.setItems(obsSide);
        cobSideHeadache.setValue(tabSymp[6]);


        // Medication Init
        // Value factory for Minutes and Hours
        SpinnerValueFactory<Integer> valueIbup = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0);
        SpinnerValueFactory<Integer> valuePara = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0);
        SpinnerValueFactory<Integer> valueTrip = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0);
        SpinnerValueFactory<Integer> valueOther = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0);

        spinMedIbup.setValueFactory(valueIbup);
        spinMedPara.setValueFactory(valuePara);
        spinMedTrip.setValueFactory(valueTrip);
        spinMedOther.setValueFactory(valueOther);
        // Setting the spinner editable
        spinMedIbup.setEditable(true);
        spinMedPara.setEditable(true);
        spinMedTrip.setEditable(true);
        spinMedOther.setEditable(true);


        //Tab Diary Init
        // Set TabView
        // Defines how to fill data for each cell with value from get property of Object.
        colDay.setCellValueFactory(new PropertyValueFactory<>("date"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colAura.setCellValueFactory(new PropertyValueFactory<>("aura"));
        colSeverity.setCellValueFactory(new PropertyValueFactory<>("severity"));
        colNausea.setCellValueFactory(new PropertyValueFactory<>("nausea"));
        colDizziness.setCellValueFactory(new PropertyValueFactory<>("dizziness"));
        colSide.setCellValueFactory(new PropertyValueFactory<>("SideHeadache"));
        colOtherSymp.setCellValueFactory(new PropertyValueFactory<>("OtherSymp"));
        colHyper.setCellValueFactory(new PropertyValueFactory<>("hypersensibility"));
        colTriggers.setCellValueFactory(new PropertyValueFactory<>("triggers"));
        colMed.setCellValueFactory(new PropertyValueFactory<>("medication"));

        try {
            Object diary = SerializeToXML.loadFromXML();
            if(diary != null){
                lstDiary = (List<Headache>)diary;
                obsLstHd.addAll(lstDiary);

                // Display row data in TabView
                tabHeadache.setItems(obsLstHd);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onClickAddHeadache() {
        // Get Dates and Duration
        String startDay = getDateHeadache(dayStart.getValue(), (Integer) spinStartHour.getValue(), (Integer) spinStartMin.getValue());
        String endDay = getDateHeadache(dayEnd.getValue(), (Integer) spinEndHour.getValue(), (Integer) spinEndMin.getValue());

        //Get values from choiceBox
        severity = cobSeverity.getValue().toString();
        aura = cobAura.getValue().toString();
        nausea= cobNausea.getValue().toString();
        dizziness = cobDizziness.getValue().toString();
        sideHeadache = cobSideHeadache.getValue().toString();
        otherSymp = txtAOther.getText();

        // Get values from hypersensibility
        hypersensibility = "";
        if(chkHypLight.isSelected()){
            hypersensibility += chkHypLight.getText() + ",";
        }
        if(chkHypSound.isSelected()){
            hypersensibility += chkHypSound.getText() + ",";
        }
        if(chkHypSmell.isSelected()){
            hypersensibility += chkHypSmell.getText() + ",";
        }
        if(chkHypMovement.isSelected()){
            hypersensibility += chkHypMovement.getText() ;
        }

        // Get values from potentials Triggers
        triggers = "";
        if(chkTrigLight.isSelected()){
            triggers += chkTrigLight.getText() + ",";
        }
        if(chkTrigSound.isSelected()){
            triggers += chkTrigSound.getText() + ",";
        }
        if(chkTrigLake.isSelected()){
            triggers += chkTrigLake.getText() + ",";
        }
        if(chkTrigPeriod.isSelected()){
            triggers += chkTrigPeriod.getText() ;
        }
        if(chkTrigSleep.isSelected()){
            triggers += chkTrigSleep.getText() + ",";
        }
        if(chkTrigOther.isSelected()){
            triggers += txtTrigOther.getText() ;
        }

        // Get values from medication
        medication = "";
        if(chkMedIbup.isSelected()){
            medication += chkMedIbup.getText() + ": " + spinMedIbup.getValue() +",";
        }
        if(chkMedPara.isSelected()){
            medication += chkMedPara.getText() + ": " + spinMedPara.getValue() + ",";
        }
        if(chkMedTrip.isSelected()){
            medication += chkMedTrip.getText() + ": " + spinMedTrip.getValue() + ",";
        }
        if(chkMedOther.isSelected()){
            medication += txtMedOther.getText() + ": " + spinMedOther.getValue();
        }




        // Diary Tab


        // If no row to display
        tabHeadache.setPlaceholder(new Label("No files to display"));
        // First Line selected
        tabHeadache.getSelectionModel().selectFirst();



        Headache aHeadache = new Headache(startDay, endDay, severity, dizziness, aura, nausea, sideHeadache, otherSymp, hypersensibility, triggers, medication);
        obsLstHd.add(aHeadache);

        System.out.println("merde");
        lstDiary.add(aHeadache);
        //
        // Display row data in TabView
        tabHeadache.setItems(obsLstHd);


        try {
            SerializeToXML.saveToXML(lstDiary);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    // Method to get the selected date and time formated
    String getDateHeadache(LocalDate day, int hours, int minutes){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm");
        LocalTime time = LocalTime.of(hours, minutes);
        LocalDateTime localDate = LocalDateTime.of(day,time);

       return dtf.format(localDate);
}

}