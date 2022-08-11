package com.example.headachediary.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Headache
 * A headache have an id, a starting date and ending, then a List of one or several Episodes.
 * @see Episode
 */
public class Headache implements Serializable {

    @Serial
    /**
     * Property IDVersion
     */
    private static final long serialVersionUID = 1L;

    /**
     * Property id of a headache
     */
    private int idHeadache;

    /**
     * Property date and time when begin the headache
     */
    private String startHeadache;

    /**
     * Property date and time when stop the headache
     */
    private String endHeadache;

    /**
     * Property number of headache episodes
     */
    public int nbEpisodes;

    /**
     * Property  lstEpisodes List<Episode>
     */
    private List<Episode> lstEpisodes = new ArrayList<>();

    /**
     * Constructor Headache for Serialize
     */
    public Headache(){super();}

    /**
     * Constructor Headache
     * @param idHeadache id of a headache
     * @param startHeadache date and time when begin the headache
     * @param endHeadache date and time when stop the headache
     */
    public Headache(int idHeadache, String startHeadache, String endHeadache) {
        super();
        this.idHeadache = idHeadache;
        this.startHeadache = startHeadache;
        this.endHeadache = endHeadache;
        this.nbEpisodes = -1; // by default
    }

    /**
     * Getter Accessor of idHeadache
     * @return idHeadache Integer
     */
    public int getIdHeadache() {
        return idHeadache;
    }

    /**
     * Setter Accessor of idHeadache
     * @param idHeadache id of a headache
     */
    public void setIdHeadache(int idHeadache) {
        this.idHeadache = idHeadache;
    }

    /**
     * Getter Accessor of startHeadache
     * @return startHeadache String
     */
    public String getStartHeadache() {
        return startHeadache;
    }

    /**
     * Setter Accessor of startHeadache
     * @param startHeadache date and time when begin the headache
     */
    public void setStartHeadache(String startHeadache) {
        this.startHeadache = startHeadache;
    }

    /**
     * Getter Accessor of endHeadache
     * @return endHeadache String
     */
    public String getEndHeadache() {
        return endHeadache;
    }

    /**
     * Setter Accessor of startHeadache
     * @param endHeadache date and time when stop the headache
     */
    public void setEndHeadache(String endHeadache) {
        this.endHeadache = endHeadache;
    }

    /**
     * Getter Accessor of lstEpisodes
     * @return lstEpisodes List<Episode>
     * @see Episode
     */
    public List<Episode> getLstEpisodes() {
        return lstEpisodes;
    }

    /**
     * Setter Accessor of lstEpisodes
     * @param lstEpisodes list of the different episodes of a migraine
     * @see Episode
     */
    public void setLstEpisodes(List<Episode> lstEpisodes) {
        this.lstEpisodes = lstEpisodes;
    }

    /**
     * Getter Accessor of nbEpisodes
     * @return nbEpisodes Integer
     * @see Episode
     */
    public int getNbEpisodes() {
        return nbEpisodes;
    }

    /**
     * Setter Accessor of nbEpisodes
     * @see Episode
     * @param nbEpisodes Integer
     */
    public void setNbEpisodes(int nbEpisodes) {
        this.nbEpisodes = nbEpisodes;
    }

    /**
     * Method to add a new episode in the List lstEpisodes
     * @param anEpisode An episode of Headache
     * @see Episode
     */
    public void addEpisodeInList(Episode anEpisode) {
        lstEpisodes.add(anEpisode);
        anEpisode.setIdEpisode(lstEpisodes.size());
        setNbEpisodes(lstEpisodes.size());
    }
}

