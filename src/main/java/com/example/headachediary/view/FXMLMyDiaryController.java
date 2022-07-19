package com.example.headachediary.view;


import com.example.headachediary.controler.Controle;
import com.example.headachediary.model.Episode;
import com.example.headachediary.model.Headache;
import com.example.headachediary.tools.Tools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class FXMLMyDiaryController
 * The view main controller class for FXML view file
 */
public class FXMLMyDiaryController {
    //region Initialize objects
    // Serializable list
    private List<Headache> lstHeadaches ;

    // Arrays with string variables for choiceboxes and ObservableList
    String[] arrTrig = {"Light", "Sound", "Smell", "Movement", "Sleep", "Lake of Sleep", "Period", "Coffee", "Tea", "Other"};
    String[] arrSens = {"Light", "Sound", "Sleep", "Movement","Smell"};
    String[] arrSympKey = {"Severity", "Dizziness", "Aura", "Nausea", "Side"};
    String[] arrSympValue ={"None", "Mild", "Moderate", "Severe", "Vertigo", "Vomiting", "Left", "Right", "Both"};
    String[] arrMed = {"Ibuprofen","Paracetamol","Triptan","Other"};

    // ObservableList for Record all FileCaract object
    private final ObservableList<Headache> obsLstHd = FXCollections.observableArrayList();
    private final ObservableList<Episode> obsLstEp = FXCollections.observableArrayList();
    ObservableList<String> obsDiz = FXCollections.observableArrayList(arrSympValue[0], arrSympValue[1], arrSympValue[2], arrSympValue[4]);
    ObservableList<String> obsNausea = FXCollections.observableArrayList(arrSympValue[0], arrSympValue[1], arrSympValue[2], arrSympValue[5]);
    ObservableList<String> obsAura = FXCollections.observableArrayList(arrSympValue[0], arrSympValue[1], arrSympValue[2]);
    ObservableList<String> obsSevere = FXCollections.observableArrayList(arrSympValue[1], arrSympValue[2], arrSympValue[3]);
    ObservableList<String> obsSide = FXCollections.observableArrayList(arrSympValue[6], arrSympValue[7], arrSympValue[8]);
    ObservableList<String> obsSleep = FXCollections.observableArrayList(arrTrig[4], arrTrig[5]);

    // Table
    @FXML
    private Tab tabDiary;

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

    // Sensibility variables
    @FXML
    private CheckBox chkSenLight;
    @FXML
    private CheckBox chkSenSound;
    @FXML
    private CheckBox chkSenMovement;
    @FXML
    private CheckBox chkSenSmell;

    // Trigger variables
    @FXML
    private CheckBox chkTrigLight;
    @FXML
    private CheckBox chkTrigSound;
    @FXML
    private CheckBox chkTrigPeriod;
    @FXML
    private CheckBox chkTrigOther;
    @FXML
    private TextField txtTrigOther;
    @FXML
    private ChoiceBox<String> cobSleep;
    @FXML
    private CheckBox chkSleep;

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
    /// AddTab
    @FXML
    ToggleButton togglebtnAddHeadache;
    @FXML
    ToggleButton togglebtnAddEpisode;
    @FXML
    private Button btnLatestSetting;


    /// DiaryTab
    @FXML
    private ToggleButton btnModify;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnRemove;

    // Text variables
    @FXML
    private Label lblIssues;
    @FXML
    private Label lblDiaryDayCounter;
    @FXML
    private TextArea txtComments;
    @FXML
    private Label lblLastTrig;
    @FXML
    private Label lblMostTrig;
    @FXML
    private Label lblMostSen;

    // TableView variables
    /// TableView columns for Headache
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


    /// TableView columns for Episode
    @FXML
    private TableView<Episode> tabEpisodes;
    @FXML
    private TableColumn<Episode, Integer> colIdEpisode;
    @FXML
    private TableColumn<Episode, String> colDates;
    @FXML
    private TableColumn<Episode, Canvas> colSympCanvas;
    @FXML
    private TableColumn<Episode, Canvas> colSenCanvas;
    @FXML
    private TableColumn<Episode, Canvas> colTrigCanvas;
    @FXML
    private TableColumn<Episode, Canvas> colMed;
    @FXML
    private TableColumn<Episode, String> colCom;


    // Global variables for test Episode object
    private String sensibility;
    private String triggers;
    private String medication;
    private String endDay;
    private String startDay;
    private String severity;
    private String aura;
    private String nausea;
    private String dizziness;
    private String sideHeadache;


    // Controller instance
    private Controle controle;
    //endregion
    private TableColumn[] tabTableColHd={};
    private TableColumn[] tabTableColEp={colIdEpisode, colDates, colSympCanvas, colSenCanvas, colTrigCanvas, colMed, colCom};

    /**
     * Method to initialize and value all fxml components
     */
    @FXML
    private void initialize() {
        TableColumn[] tabTableColHd={colIdHeadache, colHdStart, colHdEnd, colHdNbEpisode};
        TableColumn[] tabTableColEp={colIdEpisode, colDates, colSympCanvas, colSenCanvas, colTrigCanvas, colMed, colCom};
        controle = Controle.getInstance();

        /// Set Objects : dates, symptoms, medication and tableView with default values
        Initialize.initDay(dayStart, spinStartHour, spinStartMin);
        Initialize.initDay(dayEnd, spinEndHour, spinEndMin);

        Initialize.initSymptom(cobDizziness, arrSympValue[0], obsDiz);
        Initialize.initSymptom(cobNausea, arrSympValue[0], obsNausea);
        Initialize.initSymptom(cobAura, arrSympValue[0], obsAura);
        Initialize.initSymptom(cobSeverity, arrSympValue[1], obsSevere);
        Initialize.initSymptom(cobSideHeadache, arrSympValue[8], obsSide);
        Initialize.initSymptom(cobSleep, arrTrig[4], obsSleep);

        Initialize.initMedication(spinMedIbup);
        Initialize.initMedication(spinMedPara);
        Initialize.initMedication(spinMedTrip);
        Initialize.initMedication(spinMedOther);

        Initialize.tabDiaryInitialize(tabTableColHd, tabTableColEp);

        loadDiary();
        /// Set Buttons
        // Tab Form
        ToggleGroup groupAddEntries = new ToggleGroup();
        togglebtnAddHeadache.setToggleGroup(groupAddEntries);
        togglebtnAddHeadache.setTooltip(new Tooltip("Add a new headache"));
        togglebtnAddHeadache.isSelected();

        togglebtnAddEpisode.setToggleGroup(groupAddEntries);
        togglebtnAddEpisode.setTooltip(new Tooltip("Add a new episode in last headache"));

        btnLatestSetting.setTooltip(new Tooltip("Auto fill With Latest Settings!"));
        if(lstHeadaches.isEmpty()){
            togglebtnAddEpisode.setDisable(true);
        }

        // Limit maxlength of textarea
        txtTrigOther.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 30 ? change : null));
        txtMedOther.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 15 ? change : null));
        txtComments.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 100 ? change : null));

        /// Tab Diary
        btnRemove.setDisable(true);
        btnCancel.setDisable(true);

        // Events on click on diary tab
        EventHandler<MouseEvent> eventHandlerMouse = e -> onSelectedRowTabHeadache();
        tabDiary.setOnSelectionChanged(event -> {
            if (tabDiary.isSelected()) {
                if(lstHeadaches != null && !lstHeadaches.isEmpty()){
                    //select first row in tabHeadache
                    tabHeadache.getSelectionModel().selectFirst();
                    Headache selectedHeadache = tabHeadache.getSelectionModel().getSelectedItem();

                    obsLstEp.clear();
                    obsLstEp.addAll(selectedHeadache.getLstEpisodes());
                    tabEpisodes.setItems(obsLstEp);


                    // Set Difference between today and last day with headache
                    LocalDateTime today = LocalDateTime.now();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    String strDateEnd = controle.getLastHeadache().getLstEpisodes().get(controle.getLastHeadache().getLstEpisodes().size()-1).getDateEnd();
                    LocalDateTime lastDayHeadache = LocalDateTime.parse(strDateEnd, formatter);

                    long differenceInDays = ChronoUnit.DAYS.between(lastDayHeadache, today);
                    if((int)differenceInDays <= 1){
                        lblDiaryDayCounter.setText(lblDiaryDayCounter.getText().replaceAll("(?i)days","day"));
                    }
                    else{
                        lblDiaryDayCounter.setText(lblDiaryDayCounter.getText().replaceAll("(?i)days*","days"));
                    }
                    lblDiaryDayCounter.setText(lblDiaryDayCounter.getText().replaceAll("^(\\d|X)+", String.valueOf(differenceInDays)));

                    // Fill lastest and most variables
                    //lblLastTrig.setText(controle.getLastestData().toString());
                }
            }
        });

        EventHandler<KeyEvent> eventHandlerKey = e -> onSelectedRowTabHeadache();
        tabHeadache.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandlerMouse);
        tabHeadache.addEventFilter(KeyEvent.KEY_RELEASED, eventHandlerKey);

        EventHandler<MouseEvent> onClickModify = e -> {
            if(btnModify.isSelected()){
                System.out.println("debut");
                onClickModify();
                System.out.println("fin");
            }
            else{
                tabEpisodes.setEditable(false);
                btnModify.setText("Edit Mode");
                btnRemove.setDisable(true);
                btnRemove.setVisible(false);
                btnCancel.setDisable(true);
                btnCancel.setVisible(false);
            }
        };
        btnModify.addEventFilter(MouseEvent.MOUSE_CLICKED, onClickModify);
    }

    /**
     * Event Method to fill tabEpisode with the selected headache
     */
    @FXML
    private void onSelectedRowTabHeadache(){
        Headache selectedHeadache = tabHeadache.getSelectionModel().getSelectedItem();

        obsLstEp.clear();
        obsLstEp.addAll(selectedHeadache.getLstEpisodes());
        tabEpisodes.setItems(obsLstEp);
    }

    /**
     * Event Method on click from AddHeadache button.
     * Data are stored in a new Episode object.
     * @see Episode
     */
    @FXML
    private void onClickAddInput() {
        // Tab AddEntries
        /// Get DatesF
        startDay = "";
        endDay = "";

        LocalDateTime startDate = Tools.getDateFormat(dayStart.getValue(), spinStartHour.getValue(), spinStartMin.getValue());
        LocalDateTime endDate = Tools.getDateFormat(dayEnd.getValue(), spinEndHour.getValue(), spinEndMin.getValue());

        if (startDate.compareTo(endDate) > 0) {
            lblIssues.setText("Ending date is more recent than Starting date");
        }
        if (!togglebtnAddEpisode.isSelected() && !togglebtnAddHeadache.isSelected()){
            lblIssues.setText("Select one mode : Add Episode or Add Headache");
        }
        else {
            lblIssues.setText("");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm");
            startDay = dtf.format(startDate);
            endDay = dtf.format(endDate);

            //Get values from choiceBox
            severity = cobSeverity.getValue();
            aura = cobAura.getValue();
            nausea = cobNausea.getValue();
            dizziness = cobDizziness.getValue();
            sideHeadache = cobSideHeadache.getValue();

            // Add New Episode
            Headache lastHeadache = null;
            if (togglebtnAddEpisode.isSelected()) {
                lastHeadache = controle.setHeadache(controle.getLastHeadache(), endDay);
                controle.createEpisode(startDay, endDay, getMapSymp(), checkSensibility(), checkTrigger(), checkMedication(), txtComments.getText());
            } // Add New Headache
            else if (togglebtnAddHeadache.isSelected()) {
                lastHeadache = controle.createHeadache(startDay, endDay);
                controle.createEpisode(startDay, endDay, getMapSymp(), checkSensibility(), checkTrigger(), checkMedication(), txtComments.getText());
            }

            obsLstHd.clear();
            obsLstEp.clear();
            lstHeadaches = controle.getLstHeadaches();

            obsLstHd.addAll(lstHeadaches);
            obsLstEp.addAll(controle.getLstEpisodes(lastHeadache));
            tabHeadache.getSelectionModel().select(lastHeadache);

            if(!lstHeadaches.isEmpty()){
                togglebtnAddEpisode.setDisable(false);
            }

            // Display row data in TabView
            tabHeadache.setItems(obsLstHd);
            tabHeadache.getSortOrder().add(colIdHeadache);

            // Save migraines in liste des migraines
            Controle.saveInDiary();

            //Clean up with Init methods
            Initialize.initDay(dayStart, spinStartHour, spinStartMin);
            Initialize.initDay(dayEnd, spinEndHour, spinEndMin);

            Initialize.initSymptom(cobDizziness, arrSympValue[0], obsDiz);
            Initialize.initSymptom(cobNausea, arrSympValue[0], obsNausea);
            Initialize.initSymptom(cobAura, arrSympValue[0], obsAura);
            Initialize.initSymptom(cobSeverity, arrSympValue[1], obsSevere);
            Initialize.initSymptom(cobSideHeadache, arrSympValue[6], obsSide);
            Initialize.initSymptom(cobSleep, arrTrig[4], obsSleep);

            Initialize.initMedication(spinMedIbup);
            Initialize.initMedication(spinMedPara);
            Initialize.initMedication(spinMedTrip);
            Initialize.initMedication(spinMedOther);

            uncheckCheckBox();

            txtComments.setText("");
        }
    }


    /**
     * Event Method after a click on Modify button
     * Modify information in tabDiary Episode List
     */
    public void onClickModify() {
        TableColumn[] tabTableColEp={colIdEpisode, colDates, colSympCanvas, colSenCanvas, colTrigCanvas, colMed, colCom};

        btnModify.setText("Save");
        btnRemove.setDisable(false);
        btnRemove.setVisible(true);
        btnCancel.setDisable(false);
        btnCancel.setVisible(true);

        Initialize.tabDiaryEdit(tabTableColEp);

        tabEpisodes.setEditable(true);


        colIdEpisode.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colIdEpisode.setOnEditCommit(event -> {
            Episode episode = event.getRowValue();
            episode.setIdEpisode(event.getNewValue());
        });
        colDates.setCellFactory(TextFieldTableCell.forTableColumn());
        colDates.setOnEditCommit(event -> {
            Episode episode = event.getRowValue();
            episode.setDateStart(event.getNewValue());
        });

        //colMed.setCellFactory(TextFieldTableCell.forTableColumn(new Map<>()));
        colMed.setCellFactory(ChoiceBoxTableCell.forTableColumn());
        //    new HashMap<>()));
        colMed.setOnEditCommit(event -> {
            Episode episode = event.getRowValue();
            //  episode.modifyMapMed(event.getNewValue(), "pouet");
        });

        colCom.setCellFactory(TextFieldTableCell.forTableColumn());
        colCom.setOnEditCommit(event -> {
            Episode episode = event.getRowValue();
            episode.setComment(event.getNewValue());
        });
        colCom.setMinWidth(200);

        // Sauvegarde les migraines dans la liste des migraines
        Controle.saveInDiary();
    }

    /**
     * Event Method after click on FillInformation button
     * Auto Fill the form with last Episode information
     */
    @FXML
    protected void onClickFillLatestSettings() {
        String comments;

        // Get the last episode
        Episode anEpisode = obsLstEp.get(obsLstEp.size() - 1);

        String sensibility = controle.getLstEpisode(anEpisode).toString().replace("[","");

        // Set values from choiceBox
        cobSeverity.setValue(anEpisode.getMapSympValue(arrSympKey[0]));
        cobDizziness.setValue(anEpisode.getMapSympValue(arrSympKey[1]));
        cobAura.setValue(anEpisode.getMapSympValue(arrSympKey[2]));
        cobNausea.setValue(anEpisode.getMapSympValue(arrSympKey[3]));
        cobSideHeadache.setValue(anEpisode.getMapSympValue(arrSympKey[4]));
        txtComments.setText(anEpisode.getComment());

        // Set values from sensibility
        if (sensibility.contains(arrSens[0])) {
            FXMLMyDiaryController.this.chkSenLight.setSelected(true);
        }
        if (sensibility.contains(arrSens[1])) {
            FXMLMyDiaryController.this.chkSenSound.setSelected(true);
        }
        if (sensibility.contains(arrSens[2])) {
            FXMLMyDiaryController.this.chkSenSmell.setSelected(true);
        }
        if (sensibility.contains(arrSens[3])) {
            FXMLMyDiaryController.this.chkSenMovement.setSelected(true);
        }
        if (sensibility.contains(arrSens[4])) {
            FXMLMyDiaryController.this.chkSenSmell.setSelected(true);
        }


        // Get values from potentials Triggers
        triggers = anEpisode.getLstTriggers().toString();
        if (triggers.contains(arrTrig[4])) {
            cobSleep.setValue(arrTrig[4]);
            chkSleep.setSelected(true);
        }
        else if(triggers.contains(arrTrig[5])){
            cobSleep.setValue(arrTrig[5]);
            chkSleep.setSelected(true);
        }

        if (triggers.contains(arrTrig[0])) {
            chkTrigLight.setSelected(true);
        }
        if (triggers.contains(arrTrig[1])) {
            chkTrigSound.setSelected(true);
        }
        if (triggers.contains(arrTrig[7])) {
            chkTrigPeriod.setSelected(true);
        }

        triggers=triggers.replaceAll("(?:Light|Sound|Smell|Movement|Period|Sleep|Lake of Sleep|[\\[\\]])","").replace(",","");
        if (!triggers.equals("")) {

            chkTrigOther.setSelected(true);
            txtTrigOther.setText(triggers);
        }

        // Get values from medication
        medication = anEpisode.getMapMed().toString();
        Map<String, Integer> mapMed = anEpisode.getMapMed();
        if (mapMed.containsKey(arrMed[0])) {
            chkMedIbup.setSelected(true);
            spinMedIbup.getValueFactory().setValue(mapMed.get(arrMed[0]));
        }
        if (mapMed.containsKey(arrMed[1])) {
            chkMedPara.setSelected(true);
            spinMedPara.getValueFactory().setValue(mapMed.get(arrMed[1]));
        }
        if (mapMed.containsKey(arrMed[2])) {
            chkMedTrip.setSelected(true);
            spinMedTrip.getValueFactory().setValue(mapMed.get(arrMed[2]));
        }

        String strRegexMed="(?:Ibuprofen|Paracetamol|Triptan)";
        String otherMed = mapMed.keySet()
                .stream()
                .filter(string -> !string.matches(strRegexMed))
                .collect(Collectors.toSet())
                .toString().replaceAll("[\\[\\]]","");

        if (mapMed.containsKey(otherMed)) {
            chkMedOther.setSelected(true);
            txtMedOther.setText(otherMed);
            spinMedOther.getValueFactory().setValue(mapMed.get(otherMed));
        }

        // Get values from comments
        comments = anEpisode.getComment();
        if(!comments.equals("")){
            txtComments.setText(comments);
        }
    }

    /**
     * Method to get values from chkSensibility if check box is checked
     * @return Map<String, String>
     */
    private Map<String,String> getMapSymp(){

        Map<String,String> mapSymp = new HashMap<>() ;

        mapSymp.put(arrSympKey[0],cobSeverity.getValue());
        mapSymp.put(arrSympKey[1],cobDizziness.getValue());
        mapSymp.put(arrSympKey[2],cobAura.getValue());
        mapSymp.put(arrSympKey[3],cobNausea.getValue());
        mapSymp.put(arrSympKey[4],cobSideHeadache.getValue());

        return mapSymp;
    }

    //region Check Methods

    /**
     * Method to get values from chkSensibility if check box is checked
     * @return List<String>
     */
    private List<String>  checkSensibility(){
        List<String> lstChkSensibility = new ArrayList<>() ;

        if (chkSenLight.isSelected()) {
            lstChkSensibility.add(chkSenLight.getText());
        }
        if (chkSenSound.isSelected()) {
            lstChkSensibility.add(chkSenSound.getText());
        }
        if (chkSenSmell.isSelected()) {
            lstChkSensibility.add(chkSenSmell.getText());
        }
        if (chkSenMovement.isSelected()) {
            lstChkSensibility.add(chkSenMovement.getText());
        }
        return lstChkSensibility;
    }


    /**
     * Method to get values from PotentialsTrigger if check box is checked
     * @return List<String>
     */
    private List<String>  checkTrigger() {

        List<String> lstChkTrigger = new ArrayList<>();

        if (chkTrigLight.isSelected()) {
            lstChkTrigger.add(chkTrigLight.getText());
        }
        if (chkTrigSound.isSelected()) {
            lstChkTrigger.add(chkTrigSound.getText());
        }
        if (chkTrigPeriod.isSelected()) {
            lstChkTrigger.add(chkTrigPeriod.getText());
        }
        if (chkTrigOther.isSelected()) {
            lstChkTrigger.add(txtTrigOther.getText());
        }
        if(chkSleep.isSelected()){
            lstChkTrigger.add(cobSleep.getValue());
        }
        return lstChkTrigger;
    }


    /**
     * Method checkMedication to Get values from medication
     * @return Map<String, String>
     */
    private Map<String,Integer>  checkMedication() {

        Map<String,Integer> mapMed = new HashMap<>();

        if (chkMedIbup.isSelected()) {
            mapMed.put(chkMedIbup.getText(), spinMedIbup.getValue());
        }
        if (chkMedPara.isSelected()) {
            mapMed.put(chkMedPara.getText(), spinMedPara.getValue());
        }
        if (chkMedTrip.isSelected()) {
            mapMed.put(chkMedTrip.getText(),spinMedTrip.getValue());
        }
        if (chkMedOther.isSelected()) {
            mapMed.put(txtMedOther.getText(),spinMedOther.getValue());
        }
        return mapMed;
    }


    /**
     * Method to uncheck all checked checkboxes
     */
    private void uncheckCheckBox() {
        chkSenLight.setSelected(false);
        chkSenMovement.setSelected(false);
        chkSenSmell.setSelected(false);
        chkSenSound.setSelected(false);

        chkTrigPeriod.setSelected(false);
        chkTrigLight.setSelected(false);
        chkTrigOther.setSelected(false);
        chkTrigSound.setSelected(false);
        chkSleep.setSelected(false);

        chkMedPara.setSelected(false);
        chkMedIbup.setSelected(false);
        chkMedTrip.setSelected(false);
        chkMedOther.setSelected(false);
    }
    //endregion

    //region Diary Methods
    /**
     * Method to load tabDiary information
     */
    public void loadDiary(){
        try {
            lstHeadaches = controle.loadDiary();

            if(lstHeadaches == null){
                lstHeadaches = new ArrayList<>();
            }
            if (!lstHeadaches.isEmpty()) {
                obsLstHd.addAll(lstHeadaches);

                Headache lastHeadache = controle.getLastHeadache();
                //System.out.println("  :::  getNbEpisodes LAst "+lastHeadache.getNbEpisodes());
                obsLstEp.addAll(lastHeadache.getLstEpisodes());

                // Display row data in TabView
                tabHeadache.setItems(obsLstHd);
                tabEpisodes.setItems(obsLstEp);

                // Reverse idHeadache order
                colIdHeadache.setComparator(colIdHeadache.getComparator().reversed());
                tabHeadache.getSortOrder().add(colIdHeadache);

                for(String str: checkSensibility()){
                    System.out.println("FXMLMyDiaryController : Erreur au chargement du journal XML headache");
                }
            }
        } catch (Exception e) {
            System.out.println("FXMLMyDiaryController : Erreur au chargement du journal XML headache");
            e.printStackTrace();
        }
    }
    //endregion
}