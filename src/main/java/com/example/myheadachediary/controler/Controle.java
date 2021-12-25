package com.example.myheadachediary.controler;

import com.example.myheadachediary.model.Episode;
import com.example.myheadachediary.model.Headache;

public class Controle {
    private static Controle instance = null;
    private static Headache headache;

    /**
     * Private constructor
     */
    private Controle(){super();}

    /**
     * Method to get the only one instance of Control Class or to create it
     * @return instance
     */
    public static final Controle getInstance(){
        if(instance == null){
            Controle.instance = new Controle();}
        return Controle.instance;
    }

    public void createHeadache(int id, String startDay, String endDay, String comments){
        headache = new Headache(id, startDay, endDay, comments);
        //Episode anEpisode = new Episode(startDay, endDay, severity, dizziness, aura, nausea, sideHeadache, sensibility, triggers, medication, txtAComments.getText());
        headache.addEpisodeInList(anEpisode);

    }
}
