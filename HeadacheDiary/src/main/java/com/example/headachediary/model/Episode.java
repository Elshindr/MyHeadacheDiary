package com.example.headachediary.model;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class Episode
 * Represent an Episode when a Headache occurs.
 * A Headache can have one or several Episodes.
 * @see Headache
 */
public class Episode extends Headache implements Serializable {

    // Variables to save
    private int idEpisode;
    private String dateStart;
    private String dateEnd;
    private String comment;

    public List<String> lstSensi = new ArrayList<>();
    private List<String> lstTriggers = new ArrayList<>();

    private Map<String, Integer> mapMed;
    private Map<String, String> mapSymp;

    // to erase?
    private String sensibility;

    // Variables to show
    private transient String strDates;
    private transient Canvas cvsMed;
    private transient Canvas cvsSensi;
    private transient Canvas cvsTrigger;
    private transient Canvas cvsSymp;

    public Episode(){super();}

    /**
     * Episode Constructor
     * @param dateStart
     * @param dateEnd
     * @param mapSymp
     * @param lstSensi
     * @param lstTriggers
     * @param mapMed
     * @param comment
     */
    public Episode(String dateStart, String dateEnd, Map<String, String> mapSymp, List<String> lstSensi, List<String>  lstTriggers, Map<String, Integer> mapMed, String comment) {
        super();
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.comment = comment;

        this.lstSensi = lstSensi;
        this.lstTriggers = lstTriggers;

        this.mapSymp = mapSymp;
        this.mapMed = mapMed;
        this.idEpisode = -1; //by default

        // Method
        setCvsSensi();
        setCvsTrigger();
        setCvsSymp();
        setCvsMed();
        setStrDates();
    }

    public Canvas getCvsMed() {
        return cvsMed;
    }

    public void setCvsMed(Canvas cvsMed) {
        this.cvsMed = cvsMed;
    }

    /**
     * Method to set Medication Canva
     */
    public void setCvsMed(){
        int count = 1;
        int yStroke = 20;

        this.cvsMed = new Canvas(145, 80);
        GraphicsContext gc = cvsMed.getGraphicsContext2D();

        gc.setFill(Color.WHITESMOKE);
        for(Map.Entry<String, Integer> entry : mapMed.entrySet()){
            if (entry.getKey().contains("Paracetamol")){
                gc.fillText(entry.getKey(), 5, yStroke - 5 );
                gc.fillText(entry.getValue().toString(), 105, yStroke - 5 );
            }
            if (entry.getKey().contains("Ibuprofen")) {
                gc.fillText(entry.getKey(), 5, yStroke - 5);
                gc.fillText(entry.getValue().toString(), 105, yStroke - 5 );
            }
            if (entry.getKey().contains("Triptan")) {
                gc.fillText(entry.getKey(), 5, yStroke - 5);
                gc.fillText(entry.getValue().toString(), 105, yStroke - 5 );
            }

            String strRegexMed="(?:Ibuprofen|Paracetamol|Triptan)";

            String otherMed = mapMed.keySet()
                    .stream()
                    .filter(string -> !string.matches(strRegexMed))
                    .collect(Collectors.toSet())
                    .toString()
                    .replaceAll("[\\[\\]]","");

            if (otherMed != "" && entry.getKey().contains(otherMed)) {
                gc.fillText(entry.getKey(), 5, yStroke - 5);
                gc.fillText(entry.getValue().toString(), 105, yStroke - 5 );
            }

            if(mapMed.size() > 1 && count < mapMed.size()){
                gc.setStroke(Color.DARKSLATEBLUE);
                gc.setLineWidth(1);
                gc.strokeLine(0, yStroke, 145, yStroke);
                yStroke += 20;
            }
            count++;
        }

    }

    /**
     * Method to get Symptoms Canva
     * @return Canvas
     */
    public Canvas getCvsSymp() {return cvsSymp;}

    /**
     * Method to set Symptoms Canva
     */
    public void setCvsSymp() {
        this.cvsSymp = new Canvas(145, 85);
        GraphicsContext gc = cvsSymp.getGraphicsContext2D();

        for(Map.Entry<String, String> entry : mapSymp.entrySet()){
            String strImg = "/com/example/headachediary/";
            switch(entry.getValue()) {
                case "Severe":
                    strImg += "sad.png";
                    break;
                case "Moderate":
                    strImg += "neutral.png";
                    break;
                case "Mild":
                    strImg += "great.png";
                    break;
                case "None":
                    strImg += "none.png";
                    break;
                case "Right":
                    strImg += "right.png";
                    break;
                case "Left":
                    strImg += "left.png";
                    break;
                case "Both":
                    strImg += "both-dir.png";
                    break;
                case "Vertigo":
                    strImg += "dizzy.png";
                    break;
                case "Vomiting":
                    strImg += "nausea.png";
                    break;
                default:
                    break;
            }
            switch(entry.getKey()){
                case "Severity":
                    gc.drawImage(new Image(getClass().getResource(strImg).toString()),55,  0, 25, 25);
                    break;
                case "Aura":
                    gc.drawImage(new Image(getClass().getResource(strImg).toString()),120,  0, 25, 25);
                    break;
                case "Dizziness":
                    gc.drawImage(new Image(getClass().getResource(strImg).toString()),55, 30, 25, 25);
                    break;
                case "Side":
                    gc.drawImage(new Image(getClass().getResource(strImg).toString()),120,  30, 25, 25);
                    break;
                case "Nausea":
                    gc.drawImage(new Image(getClass().getResource(strImg).toString()),55,  60, 25, 25);
                    break;
                default:
                    break;
            }

        }

        gc.setFill(Color.WHITESMOKE);
        gc.fillText("Severity", 5, 15);
        gc.fillText("Aura ", 91, 15 );
        gc.fillText("Dizziness", 5, 45 );
        gc.fillText("Side", 91, 45 );
        gc.fillText("Nausea", 5, 75 );

        gc.setStroke(Color.DARKSLATEBLUE);
        gc.setLineWidth(1);
        gc.strokeLine(0, 27, 145, 27);

        gc.setStroke(Color.DARKSLATEBLUE);
        gc.setLineWidth(1);
        gc.strokeLine(0, 57, 145, 57);
    }


    /**
     * Method to set Dates in String
     */
    public void setStrDates(){
        this.strDates = dateStart + "\n" + dateEnd;
    }


    /**
     * Method to set Dates in String
     * Setter for strDates
     * @param strDates
     */
    public void setStrDates(String strDates) {
        this.strDates = strDates;
    }


    /**
     * Method to get Dates in String
     * Getter for strDates
     * @return
     */
    public String getStrDates() {
        return strDates;
    }


    /**
     * Method to get Trigger Canva
     * @return Trigger Canva
     */
    public Canvas getCvsTrigger() {
        return cvsTrigger;
    }


    /**
     * Method to set Trigger Canva
     */
    public void setCvsTrigger() {
        this.cvsTrigger = new Canvas(55, 85);
        GraphicsContext gc = cvsTrigger.getGraphicsContext2D();

        for(int i=0; i<lstTriggers.size(); i++){
            if(lstTriggers.get(i).contains("Light")){
                gc.drawImage(new Image(getClass().getResource("/com/example/headachediary/light.png").toString()),0, 0, 25, 25);
            }
            else if(lstTriggers.get(i).contains("Sound")){
                gc.drawImage(new Image(getClass().getResource("/com/example/headachediary/sound.png").toString()),0, 30, 25, 25);
            }
            else if(lstTriggers.get(i).contains("Period")){
                gc.drawImage(new Image(getClass().getResource("/com/example/headachediary/period.png").toString()),0,  60, 25, 25);
            }
            else if(lstTriggers.get(i).contains("Lake of")){
                gc.drawImage(new Image(getClass().getResource("/com/example/headachediary/nosleep.png").toString()),30,  0, 25, 25);
            }
            else if(lstTriggers.get(i).contains("Sleep")){
                gc.drawImage(new Image(getClass().getResource("/com/example/headachediary/sleep.png").toString()),30,  0, 25, 25);
            }
            else if(lstTriggers.get(i).contains("The") || lstTriggers.get(i).contains("Caf")|| lstTriggers.get(i).contains("Cof")){
                gc.drawImage(new Image(getClass().getResource("/com/example/headachediary/mug.png").toString()),30,  30, 25, 25);
            }
        }
    }

    /**
     * Method to get Sensibility Canva
     * @return Sensibility Canva
     */
    public Canvas getCvsSensi() {return cvsSensi;}

    /**
     * Method to set Sensibility Canva
     */
    public void setCvsSensi() {
        this.cvsSensi = new Canvas(55, 55);
        GraphicsContext gc = cvsSensi.getGraphicsContext2D();

        for(int i=0; i<lstSensi.size(); i++){
            if(lstSensi.get(i).contains("Light")){
                gc.drawImage(new Image(getClass().getResource("/com/example/headachediary/light.png").toString()),0, 0, 25, 25);
            }
            else if(lstSensi.get(i).contains("Sound")){
                gc.drawImage(new Image(getClass().getResource("/com/example/headachediary/sound.png").toString()),0, 30, 25, 25);
            }
            else if(lstSensi.get(i).contains("Smell")){
                gc.drawImage(new Image(getClass().getResource("/com/example/headachediary/smell.png").toString()),30,  0, 25, 25);
            }
            else if(lstSensi.get(i).contains("Movement")){
                gc.drawImage(new Image(getClass().getResource("/com/example/headachediary/move.png").toString()),30,  30, 25, 25);
            }
        }
    }


    /**
     * Method to get Sensibility
     * Setter for sensibility
     * @return String sensibility
     */
    public String getSensibility() {
        return sensibility;
    }

    /**
     * Method to set sensibility
     * Getter for sensibility
     * @param sensibility
     */
    public void setSensibility(String sensibility) {
        this.sensibility = sensibility;
    }

    /**
     * Method
     * @return
     */
    public List<String> getLstSensi() {
        return lstSensi;
    }

    public void setLstSensi(List<String> lstSensi){
        this.lstSensi = lstSensi;
    }

    public void modifyLstSensi(String newValue){
        String[] tabValue = newValue.split(",");
        lstSensi.clear();

        for(String strValue : tabValue){
            lstSensi.add(strValue);
        }
    }

    public List<String> getLstTriggers() {
        return lstTriggers;
    }

    public void setLstTriggers(List<String> lstTriggers) {
        this.lstTriggers = lstTriggers;
    }

    public void modifyLstTriggers(String newValue){
        String[] tabValue = newValue.split(",");
        lstTriggers.clear();

        for(String strValue : tabValue){
            lstTriggers.add(strValue);
        }

    }

    public Map<String, String> getMapSymp() {return mapSymp;}

    public String getMapSympValue(String key) {
        return mapSymp.get(key);
    }
    public void setMapSymp(Map<String, String> mapSymp) {
        this.mapSymp = mapSymp;
    }
    public void modifyMapSymp(String valueSymp, String keySymp) {
        if(keySymp != "" && mapSymp.containsKey(keySymp)){
            mapSymp.put(keySymp,valueSymp);
        }
    }

    public Map<String, Integer> getMapMed() {
        return mapMed;
    }

    public void setMapMed(Map<String, Integer> mapMed) {
        this.mapMed = mapMed;
    }

    public void modifyMapMed(String valueMed, String keyMed) {
        if(!keyMed.equals("") && mapSymp.containsKey(keyMed)){
            mapMed.put(keyMed, Integer.valueOf(valueMed));
        }
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getIdEpisode() {
        return idEpisode;
    }

    public void setIdEpisode(int idEpisode) {
        this.idEpisode = idEpisode;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
