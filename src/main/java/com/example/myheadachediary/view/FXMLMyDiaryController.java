package com.example.myheadachediary.view;

import com.example.myheadachediary.controler.Controle;
import com.example.myheadachediary.model.Episode;
import com.example.myheadachediary.model.Headache;
import com.example.myheadachediary.tools.SerializeToXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;

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
    private List<Headache> lstHeadaches = new ArrayList<>();

    // Create an empty ObservableList for Record all FileCaract object
    private final ObservableList<Headache> obsLstHd = FXCollections.observableArrayList();
    private final ObservableList<Episode> obsLstEp = FXCollections.observableArrayList();

    // Create observable lists with string variable for choiceboxes
    String[] tabSymp = {"None", "Mild", "Moderate", "Severe", "Vertigo", "Vomiting", "Left", "Right", "Both", "Light", "Sound", "Smell", "Sleep", "Lake of Sleep"};
    ObservableList<String> obsDiz = FXCollections.observableArrayList(tabSymp[0], tabSymp[1], tabSymp[2], tabSymp[4]);
    ObservableList<String> obsNausea = FXCollections.observableArrayList(tabSymp[0], tabSymp[1], tabSymp[2], tabSymp[5]);
    ObservableList<String> obsAura = FXCollections.observableArrayList(tabSymp[0], tabSymp[1], tabSymp[2]);
    ObservableList<String> obsSevere = FXCollections.observableArrayList(tabSymp[1], tabSymp[2], tabSymp[3]);
    ObservableList<String> obsSide = FXCollections.observableArrayList(tabSymp[6], tabSymp[7], tabSymp[8]);
    ObservableList<String> obsSleep = FXCollections.observableArrayList(tabSymp[12], tabSymp[13]);


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
    private CheckBox chkSleep;
    @FXML
    private ChoiceBox<String> cobSleep;

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
    private Button btnAddInput;
    @FXML
    private Button btnLastestSetting;
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
    private TextArea txtAComments;


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
    @FXML
    private TableColumn<Episode, String> colHdComments;
    /// TableView columns for Episode
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
    private TableColumn<Episode, String> colSen;
    @FXML
    private TableColumn<Episode, String> colTriggers;
    @FXML
    private TableColumn<Episode, String> colMed;
    @FXML
    private TableColumn<Episode, String> colCom;


    // Local variables for create Episode object
    private String severity;
    private String dizziness;
    private String aura;
    private String nausea;
    private String sideHeadache;
    private String sensibility;
    private String triggers;
    private String medication;
    private String comments;

    // Controller instance
    private Controle controle;


    /**
     * Method to initialize and value all fxml components
     */
    @FXML
    private void initialize() {

        controle = Controle.getInstance();

        // Initialize dates, symptoms, medication and tableView with default values
        Initialize.initDay(dayStart, spinStartHour, spinStartMin);
        Initialize.initDay(dayEnd, spinEndHour, spinEndMin);

        Initialize.initSymptom(cobDizziness, tabSymp[0], obsDiz);
        Initialize.initSymptom(cobNausea, tabSymp[0], obsNausea);
        Initialize.initSymptom(cobAura, tabSymp[0], obsAura);
        Initialize.initSymptom(cobSeverity, tabSymp[1], obsSevere);
        Initialize.initSymptom(cobSideHeadache, tabSymp[8], obsSide);
        Initialize.initSymptom(cobSleep, tabSymp[12], obsSleep);

        Initialize.initMedication(spinMedIbup);
        Initialize.initMedication(spinMedPara);
        Initialize.initMedication(spinMedTrip);
        Initialize.initMedication(spinMedOther);

        // Set TabView tabHeadache
        // Defines how to fill data for each cell with value from get property of Object.
        Initialize.tabDiaryInitialize(colIdHeadache,"idHeadache");
        //colIdHeadache.setComparator(colIdHeadache.getComparator().reversed());
        //tabHeadache.getSortOrder().add(colIdHeadache);

        Initialize.tabDiaryInitialize(colHdStart,"startHeadache");
        Initialize.tabDiaryInitialize(colHdEnd,"endHeadache");
        Initialize.tabDiaryInitialize(colHdNbEpisode,"nbEpisodes");
        Initialize.tabDiaryInitialize(colHdComments,"comments");


        // Set TabView tabEpisode
        Initialize.tabDiaryInitialize(colIdEpisode,"idEpisode");
        Initialize.tabDiaryInitialize(colDateStart,"dateStart");
        Initialize.tabDiaryInitialize(colDateEnd,"dateEnd");
        Initialize.tabDiaryInitialize(colAura,"aura");
        Initialize.tabDiaryInitialize(colSeverity,"severity");
        Initialize.tabDiaryInitialize(colNausea,"nausea");
        Initialize.tabDiaryInitialize(colDizziness,"dizziness");
        Initialize.tabDiaryInitialize(colSide,"SideHeadache");
        Initialize.tabDiaryInitialize(colSen,"Sensibility");
        Initialize.tabDiaryInitialize(colTriggers,"triggers");
        Initialize.tabDiaryInitialize(colMed,"medication");
        Initialize.tabDiaryInitialize(colCom,"commentEp");

        // If no row to display
        tabHeadache.setPlaceholder(new Label("No Headache to display? Lucky one!"));
        tabEpisodes.setPlaceholder(new Label("Select or add new Headache entries"));
        // First Line selected
        tabHeadache.getSelectionModel().selectLast();

        // Load data from XML file
        loadDiary();

        // Set Buttons
        /// Tab AddEntrie
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

        /// Tab Diary
        btnRemove.setDisable(true);
        btnCancel.setDisable(true);


        EventHandler<MouseEvent> eventHandlerMouse = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                onSelectedRowTabHeadache();
            }
        };

        EventHandler<KeyEvent> eventHandlerKey = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e){
                onSelectedRowTabHeadache();
            }
        };
        tabHeadache.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandlerMouse);
        tabHeadache.addEventFilter(KeyEvent.KEY_RELEASED, eventHandlerKey);

        EventHandler<MouseEvent> onClickModify = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                if(btnModify.isSelected()){
                    onClickModify();
                }
                else{
                    tabEpisodes.setEditable(false);
                    btnModify.setText("Edit Mode");
                    btnRemove.setDisable(true);
                    btnCancel.setDisable(true);
                }
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
     * This method is call on click event from AddHeadache button. All information are stored in a new Episode object.
     * @see Episode
     */
    @FXML
    private void onClickAddInput() {

        // Tab AddEntries
        /// Get Dates
        String startDay = "";
        String endDay = "";

        LocalDateTime startDate = getDateHeadache(dayStart.getValue(), spinStartHour.getValue(), spinStartMin.getValue());
        LocalDateTime endDate = getDateHeadache(dayEnd.getValue(), spinEndHour.getValue(), spinEndMin.getValue());

        if (startDate.compareTo(endDate) > 0) {
            btnAddInput.setDisable(true);
            lblIssues.setText("Ending date is more recent than Starting date");
        } else {
            btnAddInput.setDisable(false);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm");
            startDay = dtf.format(startDate);
            endDay = dtf.format(endDate);
        }

        /*//Get values from choiceBox
        severity = cobSeverity.getValue();
        aura = cobAura.getValue();
        nausea = cobNausea.getValue();
        dizziness = cobDizziness.getValue();
        sideHeadache = cobSideHeadache.getValue();*/

        // Get values from sensitivity
       // checkSensibility();

        // Get values from potentials Triggers
       // checkTrigger();

        // Get values from Medication
        //checkMedication();


        // IF Add New Episode
        Headache aHeadache;
        if (togglebtnAddEpisode.isSelected()) {
            // on recupére la derniere migraine. On créée un nouvel episode et modifie la date de fin de la migraine
            aHeadache = lstHeadaches.get(lstHeadaches.size() - 1);
            aHeadache.setEndHeadache(endDay);
            Episode newEpisode = new Episode(startDay, endDay, cobSeverity.getValue(), cobDizziness.getValue(), cobAura.getValue(), cobNausea.getValue(), cobSideHeadache.getValue(), checkSensibility(), checkTrigger(), checkMedication(), txtAComments.getText());

            // ajoute le nouveau commentaire, à la suite du dernier commentaire
            aHeadache.setComments(aHeadache.getComments() + "; " + txtAComments.getText());
            // on ajoute cet episode à la liste de la derniere migraine
            aHeadache.addEpisodeInList(newEpisode);
            aHeadache.setNbEpisodes();
            obsLstEp.add(newEpisode);
            obsLstHd.set(lstHeadaches.size() - 1, aHeadache);

            tabHeadache.getSelectionModel().select(aHeadache);

        // IF Add New Headache
        } else if (togglebtnAddHeadache.isSelected()) {
           // Créé une migraine puis un Episode. On ajoute cet episode à sa liste d'episode
            controle.createHeadache(lstHeadaches.size() + 1, startDay, endDay, txtAComments.getText());
           // aHeadache = new Headache(lstHeadaches.size() + 1, startDay, endDay, txtAComments.getText());
            Episode anEpisode = new Episode(startDay, endDay, cobSeverity.getValue(), cobDizziness.getValue(), cobAura.getValue(), cobNausea.getValue(), cobSideHeadache.getValue(), checkSensibility(), checkTrigger(), checkMedication(), txtAComments.getText());
            //aHeadache.addEpisodeInList(anEpisode);

            // on ajoute cette migraine à la liste des migraines pour le relier à son observateur
            lstHeadaches.add(aHeadache);

            obsLstHd.clear();
            obsLstHd.addAll(lstHeadaches);
            obsLstEp.addAll(aHeadache.getLstEpisodes());

            if(lstHeadaches.size() > 0){
                togglebtnAddEpisode.setDisable(false);
            }

            //colIdHeadache.setComparator(colIdHeadache.getComparator().reversed());
            //tabHeadache.getSortOrder().add(colIdHeadache);
            tabHeadache.getSelectionModel().select(aHeadache);
        }

        // Display row data in TabView
        tabHeadache.setItems(obsLstHd);
        onSelectedRowTabHeadache();
        //colIdHeadache.setComparator(colIdHeadache.getComparator().reversed());
        //tabHeadache.getSortOrder().add(colIdHeadache);
        // Save migraines in liste des migraines
        saveInDiary();

        //Clean up with Init methods
        Initialize.initDay(dayStart, spinStartHour, spinStartMin);
        Initialize.initDay(dayEnd, spinEndHour, spinEndMin);

        Initialize.initSymptom(cobDizziness, tabSymp[0], obsDiz);
        Initialize.initSymptom(cobNausea, tabSymp[0], obsNausea);
        Initialize.initSymptom(cobAura, tabSymp[0], obsAura);
        Initialize.initSymptom(cobSeverity, tabSymp[1], obsSevere);
        Initialize.initSymptom(cobSideHeadache, tabSymp[8], obsSide);
        Initialize.initSymptom(cobSleep, tabSymp[12], obsSleep);

        Initialize.initMedication(spinMedIbup);
        Initialize.initMedication(spinMedPara);
        Initialize.initMedication(spinMedTrip);
        Initialize.initMedication(spinMedOther);
        uncheckCheckBox();
        txtAComments.setText("");

    }

    public void onClickModify(){

        btnModify.setText("Save");
        btnRemove.setDisable(false);
        btnCancel.setDisable(false);

        tabEpisodes.setEditable(true);

        colIdEpisode.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colIdEpisode.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Episode, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Episode, Integer> event) {
                Episode episode = event.getRowValue();
                episode.setIdEpisode(event.getNewValue());
            }
        });
        colDateStart.setCellFactory(TextFieldTableCell.forTableColumn());
        colDateStart.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Episode, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Episode, String> event) {
                Episode episode = event.getRowValue();
                episode.setDateStart(event.getNewValue());
            }
        });
        colDateEnd.setCellFactory(TextFieldTableCell.forTableColumn());
        colDateEnd.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Episode, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Episode, String> event) {
                Episode episode = event.getRowValue();
                episode.setDateEnd(event.getNewValue());
            }
        });
        colAura.setCellFactory(TextFieldTableCell.forTableColumn());
        colAura.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Episode, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Episode, String> event) {
                Episode episode = event.getRowValue();
                episode.setAura(event.getNewValue());
            }
        });
        colSeverity.setCellFactory(TextFieldTableCell.forTableColumn());
        colSeverity.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Episode, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Episode, String> event) {
                Episode episode = event.getRowValue();
                episode.setSeverity(event.getNewValue());
            }
        });
        colNausea.setCellFactory(TextFieldTableCell.forTableColumn());
        colNausea.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Episode, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Episode, String> event) {
                Episode episode = event.getRowValue();
                episode.setNausea(event.getNewValue());
            }
        });

        colDizziness.setCellFactory(TextFieldTableCell.forTableColumn());
        colDizziness.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Episode, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Episode, String> event) {
                Episode episode = event.getRowValue();
                episode.setDizziness(event.getNewValue());
            }
        });

        colSide.setCellFactory(TextFieldTableCell.forTableColumn());
        colSide.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Episode, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Episode, String> event) {
                Episode episode = event.getRowValue();
                episode.setSideHeadache(event.getNewValue());
            }
        });


        colSen.setCellFactory(TextFieldTableCell.forTableColumn());
        colSen.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Episode, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Episode, String> event) {
                Episode episode = event.getRowValue();
                episode.setSensibility(event.getNewValue());
            }
        });

        colTriggers.setCellFactory(TextFieldTableCell.forTableColumn());
        colTriggers.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Episode, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Episode, String> event) {
                Episode episode = event.getRowValue();
                episode.setTriggers(event.getNewValue());
            }
        });
        colMed.setCellFactory(TextFieldTableCell.forTableColumn());
        colMed.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Episode, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Episode, String> event) {
                Episode episode = event.getRowValue();
                episode.setMedication(event.getNewValue());
            }
        });

        colCom.setCellFactory(TextFieldTableCell.forTableColumn());
        colCom.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Episode, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Episode, String> event) {
                Episode episode = event.getRowValue();
                episode.setCommentEp(event.getNewValue());
            }
        });
        colCom.setMinWidth(200);


        // Sauvegarde les migraines dans la liste des migraines
        saveInDiary();
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
     * This method get values frchkSenersensibility if check box is checked
     */
    private String  checkSensibility(){
        sensibility = "";
        if (chkSenLight.isSelected()) {
            sensibility += chkSenLight.getText() + ",";
        }
        if (chkSenSound.isSelected()) {
            sensibility += chkSenSound.getText() + ",";
        }
        if (chkSenSmell.isSelected()) {
            sensibility += chkSenSmell.getText() + ",";
        }
        if (chkSenMovement.isSelected()) {
            sensibility += chkSenMovement.getText();
        }
        return sensibility;
    }


    /**
     * This method get values from PotentialsTrigger if check box is checked
     */
    private String  checkTrigger() {
        triggers = "";
        if (chkTrigLight.isSelected()) {
            triggers += chkTrigLight.getText() + ",";
        }
        if (chkTrigSound.isSelected()) {
            triggers += chkTrigSound.getText() + ",";
        }
        if (chkTrigPeriod.isSelected()) {
            triggers += chkTrigPeriod.getText() + ",";
        }
        if (chkTrigOther.isSelected()) {
            triggers += txtTrigOther.getText() + ",";
        }
        if(chkSleep.isSelected()){
            triggers += cobSleep.getValue();
        }
        return triggers;
    }


    /**
     * checkMedication method Get values from medication
     */
    private String checkMedication() {
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
        return medication;
    }


    /**
     * This method uncheck all checked checkboxes
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

    /**
     * Setting tableViews

    private void tabDiaryInitialize() {
        // Set TabView tabHeadache
        //InitializeControler.tabDiaryInitialize(colIdHeadache, "idHeadache");
        colIdHeadache.setCellValueFactory(new PropertyValueFactory<>("idHeadache"));
        colIdHeadache.setComparator(colIdHeadache.getComparator().reversed());

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

        colSen.setCellValueFactory(new PropertyValueFactory<>("Sensibility"));
        colTriggers.setCellValueFactory(new PropertyValueFactory<>("triggers"));
        colMed.setCellValueFactory(new PropertyValueFactory<>("medication"));
        colCom.setCellValueFactory(new PropertyValueFactory<>("commentEp"));

        // Load data from XML file
        loadDiary();
    }*/


        public void loadDiary(){
    try {
        Object diary = null;
        try {
            diary = SerializeToXML.loadFromXML() ;

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!(diary == null)) {
            lstHeadaches = (List<Headache>) diary;
            obsLstHd.addAll(lstHeadaches);

            Headache lastHeadache = lstHeadaches.get(lstHeadaches.size() - 1);
            obsLstEp.addAll(lastHeadache.getLstEpisodes());

            // Display row data in TabView
            tabHeadache.setItems(obsLstHd);
            tabEpisodes.setItems(obsLstEp);

            // Reverse idHeadache order
            colIdHeadache.setComparator(colIdHeadache.getComparator().reversed());
            tabHeadache.getSortOrder().add(colIdHeadache);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    /**
     * Method to save headache in XML file
     */
    public void saveInDiary(){
    try {

        SerializeToXML.saveToXML(lstHeadaches);

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    /**
     * Fill form with last Episode informations
     */
    @FXML
    protected void onClickFillLastestSettings() {

        // Get the last episode
        Episode anEpisode = obsLstEp.get(obsLstEp.size() - 1);

        // Set values from choiceBox
        cobSeverity.setValue(anEpisode.getSeverity());
        cobAura.setValue(anEpisode.getAura());
        cobNausea.setValue(anEpisode.getNausea());
        cobDizziness.setValue(anEpisode.getDizziness());
        cobSideHeadache.setValue(anEpisode.getSideHeadache());
        txtAComments.setText(anEpisode.getCommentEp());
        // St values from sensibility
        sensibility = anEpisode.getSensibility();

        if (sensibility.contains(tabSymp[9])) {
            FXMLMyDiaryController.this.chkSenLight.setSelected(true);
        }
        if (sensibility.contains(tabSymp[10])) {
            FXMLMyDiaryController.this.chkSenSound.setSelected(true);
        }
        if (sensibility.contains(tabSymp[11])) {
            FXMLMyDiaryController.this.chkSenSmell.setSelected(true);
        }
        if (sensibility.contains("Movement")) {
            FXMLMyDiaryController.this.chkSenMovement.setSelected(true);
        }

        // Get values from potentials Triggers
        triggers = anEpisode.getTriggers();
        if (triggers.contains("Lake of Sleep")) {
            cobSleep.setValue(tabSymp[13]);
            chkSleep.setSelected(true);
        }
        else if(triggers.contains("Sleep")){
            cobSleep.setValue(tabSymp[12]);
            chkSleep.setSelected(true);
        }

        if (triggers.contains(tabSymp[9])) {
            chkTrigLight.setSelected(true);
        }
        if (triggers.contains(tabSymp[10])) {
            chkTrigSound.setSelected(true);
        }

        if (triggers.contains("Period")) {
            chkTrigPeriod.setSelected(true);
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