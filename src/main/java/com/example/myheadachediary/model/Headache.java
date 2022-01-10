package com.example.myheadachediary.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class represent Headache. A headache have an id, a starting date and ending, and is compose of a List of one or somes episodes.
 * @see Episode
 */
    public class Headache  {

    private static final long serialVersionUID = 1L;


    private int idHeadache;

    private String startHeadache;

    private String endHeadache;

    private int nbEpisodes;

    private String comments;

    private List<Episode> lstEpisodes = new ArrayList<>();


    public Headache(){
        super();
    }

    /**
     * This method is Headache constructor
     * @param startHeadache
     * @param endHeadache
     */
    public Headache(int idHeadache, String startHeadache, String endHeadache, String comments) {
        super();
        this.idHeadache = idHeadache; // egale au nombre de headaches existant + 1
        this.startHeadache = startHeadache;
        this.endHeadache = endHeadache;
        this.nbEpisodes = 1;
        this.comments = comments;
        // by default ,then + 1 by another episode
    }

    public int getIdHeadache() {
        return idHeadache;
    }

    public void setIdHeadache(int idHeadache) {
        this.idHeadache = idHeadache;
    }

    public String getStartHeadache() {
        return startHeadache;
    }

    public void setStartHeadache(String startHeadache) {
        this.startHeadache = startHeadache;
    }

    public String getEndHeadache() {
        return endHeadache;
    }

    public void setEndHeadache(String endHeadache) {
        this.endHeadache = endHeadache;
    }

    public List<Episode> getLstEpisodes() {
        return lstEpisodes;
    }


    public void setLstEpisodes(List<Episode> lstEpisodes) {
        this.lstEpisodes = lstEpisodes;
    }

    public int getNbEpisodes() {
        return nbEpisodes;
    }

    public void setNbEpisodes(int nbEpisodes) {
        this.nbEpisodes = nbEpisodes;
    }

    public void setNbEpisodes() {
        this.nbEpisodes += 1;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {

        this.comments = comments;
    }

    /**
     * This method add a new episode in the List lstEpisodes
     * @param anEpisode
     */
    public void addEpisodeInList(Episode anEpisode) {

        lstEpisodes.add(anEpisode);

        anEpisode.setIdEpisode(lstEpisodes.size());
    }


}

