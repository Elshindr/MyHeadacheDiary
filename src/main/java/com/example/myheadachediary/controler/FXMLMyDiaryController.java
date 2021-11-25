package com.example.myheadachediary.controler;

import com.example.myheadachediary.model.Episode;
import com.example.myheadachediary.model.Headache;
import com.example.myheadachediary.model.SerializeToXML;
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
import java.util.ArrayList;
import java.util.List;

/**
 * This Class is the main controller class for FXML view file
 */
public class FXMLMyDiaryController {
    // Serializable list
    @FXML
    private List<Headache> lstHeadaches = new ArrayList<>();
    @FXML
    private final List<Episode> lstEpisodes = new ArrayList<>();

    // Create an empty ObservableList for Record all FileCaract object
    ObservableList<Headache> obsLstHd = FXCollections.observableArrayList();
    ObservableList<Episode> obsLstEp = FXCollections.observableArrayList();

    // Create observable list with string variable for choiceboxes
    String[] tabSymp = {"None", "Mild", "Moderate", "Severe", "Vertigo", "Vomiting", "Left", "Right", "Both", "Light", "Sound", "Smell"};
    ObservableList<String> obsDiz = FXCollections.observableArrayList(tabSymp[0], tabSymp[1], tabSymp[2], tabSymp[4]);
    ObservableList<String> obsNausea = FXCollections.observableArrayList(tabSymp[0], tabSymp[1], tabSymp[2], tabSymp[5]);
    ObservableList<String> obsAura = FXCollections.observableArrayList(tabSymp[0], tabSymp[1], tabSymp[2]);
    ObservableList<String> obsSevere = FXCollections.observableArrayList(tabSymp[1], tabSymp[2], tabSymp[3]);
    ObservableList<String> obsSide = FXCollections.observableArrayList(tabSymp[6], tabSymp[7], tabSymp[8]);

    // ToggleButton Add
    @FXML
    ToggleButton togglebtnAddHeadache;
    @FXML
    ToggleButton togglebtnAddEpisode;

    // Local variables for create Episode object
    String severity;
    String dizziness;
    String aura;
    String nausea;
    String sideHeadache;
    String otherSymp;
    String hypersensibility;
    String triggers;
    String medication;
    String comments;

    // Dates Variables
    @FXML
    private DatePicker dayStart;
    @FXML
    private DatePicker dayEnd;
    @FXML
    private Spinner<Integer> spinStartHour;
    @FXML
    private Spinner<Integer> spinEndHour;
    @FXML
    private Spinner<Integer> spinStartMin;
    @FXML
    private Spinner<Integer> spinEndMin;

    // Symptom variables
    @FXML
    private ChoiceBox<String> cobSeverity;
    @FXML
    private ChoiceBox<String> cobDizziness;
    @FXML
    private ChoiceBox<String> cobAura;
    @FXML
    private ChoiceBox<String> cobNausea;
    @FXML
    private ChoiceBox<String> cobSideHeadache;
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
    private Spinner<Integer> spinMedIbup;
    @FXML
    private Spinner<Integer> spinMedPara;
    @FXML
    private Spinner<Integer> spinMedTrip;
    @FXML
    private Spinner<Integer> spinMedOther;

    // Button variables
    @FXML
    private Button btnAddHeadache;

    @FXML
    private Button btnLastestSetting;

    // Labels issues
    @FXML
    private Label lblIssues;
    @FXML
    private TextArea txtAComments;
    //
    // Variables for TabDiary
    // TableView for Headaches
    @FXML
    private TableView<Headache> tabHeadache;
    @FXML
    private TableColumn<Headache, Integer> colIdHeadache;
    @FXML
    private TableColumn<Headache, String> colHdStart;
    @FXML
    private TableColumn<Headache, String> colHdEnd;
    @FXML
    private TableColumn<Headache, Integer> colHdNbEpisode;
    @FXML
    private TableColumn<Episode, String> colHdComments;


    // TableView for Episodes
    @FXML
    private TableView<Episode> tabEpisodes;
    @FXML
    private TableColumn<Episode, Integer> colIdEpisode;
    @FXML
    private TableColumn<Episode, String> colDateStart;
    @FXML
    private TableColumn<Episode, String> colDateEnd;
    @FXML
    private TableColumn<Episode, String> colSeverity;
    @FXML
    private TableColumn<Episode, String> colAura;
    @FXML
    private TableColumn<Episode, String> colNausea;
    @FXML
    private TableColumn<Episode, String> colDizziness;
    @FXML
    private TableColumn<Episode, String> colSide;
    @FXML
    private TableColumn<Episode, String> colHyper;
    @FXML
    private TableColumn<Episode, String> colOtherSymp;
    @FXML
    private TableColumn<Episode, String> colTriggers;
    @FXML
    private TableColumn<Episode, String> colMed;
    @FXML
    private TableColumn<Episode, String> colCom;

    /**
     * This method initialize all fxml components
     */
    @FXML
    private void initialize() {
        // Disable btn
        //btnAddHeadache.setDisable(true);

        // Initialize dates, symptoms, medication and tableView with default values
        dayInitialize();
        symptomInitialize();
        medicationInitialize();
        tabDiaryInitialize();

        ToggleGroup groupAddEntries = new ToggleGroup();
        togglebtnAddHeadache.setToggleGroup(groupAddEntries);
        togglebtnAddHeadache.setTooltip(new Tooltip("Add a new headache entries"));
        togglebtnAddHeadache.isSelected();

        togglebtnAddEpisode.setToggleGroup(groupAddEntries);
        togglebtnAddEpisode.setTooltip(new Tooltip("Add a new episode from last headache entry"));

        btnLastestSetting.setTooltip(new Tooltip("Auto fill With Lastest Settings!"));
        if(lstHeadaches.size() == 0){
            togglebtnAddEpisode.setDisable(true);
        }

        // If no row to display
        tabHeadache.setPlaceholder(new Label("No Headache to display? Lucky one!"));
        tabEpisodes.setPlaceholder(new Label("Select or add new Headache entries"));

        // Diary Tab
        // First Line selected
        tabHeadache.getSelectionModel().selectLast();

    }

    /**
     *
     */
    @FXML
    public void onSelectedRowTabHeadache(){
        Headache selectedHeadache = tabHeadache.getSelectionModel().getSelectedItem();

        obsLstEp.clear();
        obsLstEp.addAll(selectedHeadache.getLstEpisodes());
        tabEpisodes.setItems(obsLstEp);

    }




    /**
     * This method is call on click event from AddHeadache button. All information are stored in a new Episode object.
     * @see Episode
     */
    @FXML
    protected void onClickAddHeadache() {

        // Add Headache Tab
        // Get Dates
        String startDay = "";
        String endDay = "";

        LocalDateTime startDate = getDateHeadache(dayStart.getValue(), spinStartHour.getValue(), spinStartMin.getValue());
        LocalDateTime endDate = getDateHeadache(dayEnd.getValue(), spinEndHour.getValue(), spinEndMin.getValue());

        if (startDate.compareTo(endDate) > 0) {
            btnAddHeadache.setDisable(true);
            lblIssues.setText("Ending date is recent than Starting date");
        } else {
            btnAddHeadache.setDisable(false);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm");
            startDay = dtf.format(startDate);
            endDay = dtf.format(endDate);
        }

        //Get values from choiceBox
        severity = cobSeverity.getValue();
        aura = cobAura.getValue();
        nausea = cobNausea.getValue();
        dizziness = cobDizziness.getValue();
        sideHeadache = cobSideHeadache.getValue();
        otherSymp = txtAOther.getText();

        // Get values from Hypersensitivity
        checkHypersensibility();

        // Get values from potentials Triggers
        checkPotentialsTriggers();

        // Get values from Medication
        checkMedication();


        // IF Add New Episode
        if (togglebtnAddEpisode.isSelected()) {
            // on recupére la derniere migraine. On crée un nouvel episode et modifie la date de fin de la migraine
            Headache lastHeadache = lstHeadaches.get(lstHeadaches.size() - 1);
            lastHeadache.setEndHeadache(endDay);
            Episode newEpisode = new Episode(startDay, endDay, severity, dizziness, aura, nausea, sideHeadache, otherSymp, hypersensibility, triggers, medication, txtAComments.getText());

            // on ajoute cet episode à la liste de la derniere migraine
            lastHeadache.addEpisodeInList(newEpisode);
            lastHeadache.setNbEpisodes();
            obsLstEp.add(newEpisode);
            obsLstHd.set(lstHeadaches.size() - 1, lastHeadache);

        // IF Add New Headache
        } else if (togglebtnAddHeadache.isSelected()) {
           // Créé une migraine puis un Episode. On ajoute cet episode à sa liste d'episode
            Headache aHeadache = new Headache(lstHeadaches.size() + 1, startDay, endDay, txtAComments.getText());
            Episode anEpisode = new Episode(startDay, endDay, severity, dizziness, aura, nausea, sideHeadache, otherSymp, hypersensibility, triggers, medication, txtAComments.getText());
            aHeadache.addEpisodeInList(anEpisode);

            // on ajoute cette migraine à la liste des migraines pour le relier à son observateur
            lstHeadaches.add(aHeadache);

            obsLstHd.add(aHeadache);
            obsLstEp.addAll(aHeadache.getLstEpisodes());

            if(lstHeadaches.size() > 0){
                togglebtnAddEpisode.setDisable(false);
            }
        }


        // Display row data in TabView
        tabHeadache.setItems(obsLstHd);
        tabEpisodes.setItems(obsLstEp);

        // Sauvegarde les migraines dans la liste des migraines
        try {

            SerializeToXML.saveToXML(lstHeadaches);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Clean up with Init methods
        dayInitialize();
        symptomInitialize();
        medicationInitialize();
        checkBokReboot();
        txtAComments.setText("");
        //tabDiaryInitialize();

    }

    /**
     * This method get and  format the selected date from datepickers
     * @param day
     * @param hours
     * @param minutes
     * @return date and time formated
     */
    private LocalDateTime getDateHeadache(LocalDate day, int hours, int minutes) {
        LocalTime time = LocalTime.of(hours, minutes);

        return LocalDateTime.of(day, time);
    }

    /**
     * This method get values from Hypersensibility if check box is checked
     */
    private void checkHypersensibility() {
        hypersensibility = "";
        if (chkHypLight.isSelected()) {
            hypersensibility += chkHypLight.getText() + ",";
        }
        if (chkHypSound.isSelected()) {
            hypersensibility += chkHypSound.getText() + ",";
        }
        if (chkHypSmell.isSelected()) {
            hypersensibility += chkHypSmell.getText() + ",";
        }
        if (chkHypMovement.isSelected()) {
            hypersensibility += chkHypMovement.getText();
        }
    }

    /**
     * This method get values from PotentialsTrigger if check box is checked
     */
    private void checkPotentialsTriggers() {
        triggers = "";
        if (chkTrigLight.isSelected()) {
            triggers += chkTrigLight.getText() + ",";
        }
        if (chkTrigSound.isSelected()) {
            triggers += chkTrigSound.getText() + ",";
        }
        if (chkTrigLake.isSelected()) {
            triggers += chkTrigLake.getText() + ",";
        }
        if (chkTrigPeriod.isSelected()) {
            triggers += chkTrigPeriod.getText() + ",";
        }
        if (chkTrigSleep.isSelected()) {
            triggers += chkTrigSleep.getText() + ",";
        }
        if (chkTrigOther.isSelected()) {
            triggers += txtTrigOther.getText();
        }
    }

    /**
     * checkMedication method Get values from medication
     */
    private void checkMedication() {
        medication = "";
        if (chkMedIbup.isSelected()) {
            medication += chkMedIbup.getText() + ": " + spinMedIbup.getValue() + ",";
        }
        if (chkMedPara.isSelected()) {
            medication += chkMedPara.getText() + ": " + spinMedPara.getValue() + ",";
        }
        if (chkMedTrip.isSelected()) {
            medication += chkMedTrip.getText() + ": " + spinMedTrip.getValue() + ",";
        }
        if (chkMedOther.isSelected()) {
            medication += txtMedOther.getText() + ": " + spinMedOther.getValue();
        }
    }


    /**
     * This method uncheck all checked variables
     */
    public void checkBokReboot() {
        chkHypLight.setSelected(false);
        chkHypMovement.setSelected(false);
        chkHypSmell.setSelected(false);
        chkHypSound.setSelected(false);

        chkTrigPeriod.setSelected(false);
        chkTrigLake.setSelected(false);
        chkTrigLight.setSelected(false);
        chkTrigOther.setSelected(false);
        chkTrigSleep.setSelected(false);
        chkTrigSound.setSelected(false);

        chkMedPara.setSelected(false);
        chkMedIbup.setSelected(false);
        chkMedTrip.setSelected(false);
        chkMedOther.setSelected(false);
    }


    /**
     * This method initialize all dates variables
     */
    private void dayInitialize() {
        // Setting the current date
        dayStart.setValue(LocalDate.now());
        dayEnd.setValue(LocalDate.now());
        LocalTime ltInitSpinners = LocalTime.now();

        // Value factory for Minutes and Hours
        SpinnerValueFactory<Integer> valueStartHours = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24, ltInitSpinners.getHour());
        SpinnerValueFactory<Integer> valueStartMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, ltInitSpinners.getMinute());
        SpinnerValueFactory<Integer> valueEndHours = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24, ltInitSpinners.getHour());
        SpinnerValueFactory<Integer> valueEndMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, ltInitSpinners.getMinute());
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
    }


    /**
     * Setting cobChoice Value ;
     */
    private void symptomInitialize() {
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
    }


    /**
     * Setting medication objects
     */
    private void medicationInitialize() {
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
    }


    /**
     * Setting tableViews
     */
    private void tabDiaryInitialize() {
        // Set TabView tabHeadache
        colIdHeadache.setCellValueFactory(new PropertyValueFactory<>("idHeadache"));
        colHdStart.setCellValueFactory(new PropertyValueFactory<>("startHeadache"));
        colHdEnd.setCellValueFactory(new PropertyValueFactory<>("endHeadache"));
        colHdNbEpisode.setCellValueFactory(new PropertyValueFactory<>("nbEpisodes"));
        colHdComments.setCellValueFactory(new PropertyValueFactory<>("comments"));

        // Set TabView tabEpisodes
        // Defines how to fill data for each cell with value from get property of Object.
        colIdEpisode.setCellValueFactory(new PropertyValueFactory<>("idEpisode"));
        colDateStart.setCellValueFactory(new PropertyValueFactory<>("dateStart"));
        colDateEnd.setCellValueFactory(new PropertyValueFactory<>("dateEnd"));
        colAura.setCellValueFactory(new PropertyValueFactory<>("aura"));
        colSeverity.setCellValueFactory(new PropertyValueFactory<>("severity"));
        colNausea.setCellValueFactory(new PropertyValueFactory<>("nausea"));
        colDizziness.setCellValueFactory(new PropertyValueFactory<>("dizziness"));
        colSide.setCellValueFactory(new PropertyValueFactory<>("SideHeadache"));
        colOtherSymp.setCellValueFactory(new PropertyValueFactory<>("OtherSymp"));
        colHyper.setCellValueFactory(new PropertyValueFactory<>("hypersensibility"));
        colTriggers.setCellValueFactory(new PropertyValueFactory<>("triggers"));
        colMed.setCellValueFactory(new PropertyValueFactory<>("medication"));
        colCom.setCellValueFactory(new PropertyValueFactory<>("commentEp"));


        // Load data from XML file
        try {
            Object diary = null;
            try {
                diary = SerializeToXML.loadFromXML() ;

            } catch (IOException e) {
                e.printStackTrace();
            }

            if (diary != null) {
                lstHeadaches = (List<Headache>) diary;
                obsLstHd.addAll(lstHeadaches);

                Headache lastHeadache = lstHeadaches.get(lstHeadaches.size() - 1);
                obsLstEp.addAll(lastHeadache.getLstEpisodes());

                // Display row data in TabView
                tabHeadache.setItems(obsLstHd);
                tabEpisodes.setItems(obsLstEp);
                System.out.println(lstEpisodes.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Fill form with last Headache information
     */
    @FXML
    protected void onClickFillLastestSettings() {
        Headache anHeadache = obsLstHd.get(obsLstHd.size() - 1);
        // recupérer le bon episode de la bonne migraine

        Episode anEpisode = obsLstEp.get(obsLstEp.size() - 1);
        //Set values from choiceBox
        cobSeverity.setValue(anEpisode.getSeverity());
        cobAura.setValue(anEpisode.getAura());
        cobNausea.setValue(anEpisode.getNausea());
        cobDizziness.setValue(anEpisode.getDizziness());
        cobSideHeadache.setValue(anEpisode.getSideHeadache());
        txtAOther.setText(anEpisode.getOtherSymp());
        txtAComments.setText(anEpisode.getCommentEp());
        // St values from hypersensibility
        hypersensibility = anEpisode.getHypersensibility();

        if (hypersensibility.contains(tabSymp[9])) {
            chkHypLight.setSelected(true);
        }
        if (hypersensibility.contains(tabSymp[10])) {
            chkHypSound.setSelected(true);
        }
        if (hypersensibility.contains(tabSymp[11])) {
            chkHypSmell.setSelected(true);
        }
        if (hypersensibility.contains("Movement")) {
            chkHypMovement.setSelected(true);
        }

        // Get values from potentials Triggers
        triggers = anEpisode.getTriggers();

        if (triggers.contains(tabSymp[9])) {
            chkTrigLight.setSelected(true);
        }
        if (triggers.contains(tabSymp[10])) {
            chkTrigSound.setSelected(true);
        }
        if (triggers.contains("Lake")) {
            chkTrigLake.setSelected(true);
        }
        if (triggers.contains("Period")) {
            chkTrigPeriod.setSelected(true);
        }
        if (triggers.contains("Sleep")) {
            chkTrigSleep.setSelected(true);
        }
        triggers = triggers.replace(",", "").replace(tabSymp[9], "").replace(tabSymp[10], "").replace("Lake", "").replace("Period", "").replace("Sleep", "");
        if (!triggers.equals("")) {
            chkTrigOther.setSelected(true);
            txtTrigOther.setText(triggers);
        }

        // Get values from medication
        medication = anEpisode.getMedication();
        if (medication.contains("Ibuprofen")) {
            chkMedIbup.setSelected(true);
        }
        if (medication.contains("Paracetamol")) {
            chkMedPara.setSelected(true);
        }
        if (medication.contains("Triptan")) {
            chkMedTrip.setSelected(true);
        }
        medication = medication.replace(",", "").replace("Ibuprofen", "").replace("Paracetamol", "").replace("Triptan", "");
        if (!medication.equals("")) {
            chkMedOther.setSelected(true);
            txtMedOther.setText(medication);
        }

        // Get values from comments
        comments = anEpisode.getCommentEp();
        if(!comments.equals("")){
            txtAComments.setText(comments);
        }

    }
}