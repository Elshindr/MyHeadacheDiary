package com.example.myheadachediary.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Headache implements Serializable {
    private int idHeadache;
    private String startHeadache;
    private String endHeadache;
    private int nbEpisodes;
    private List<Episode> lstEpisodes = new ArrayList<>();

    public Headache() {
    }

    public Headache(int idHeadache, String startHeadache, String endHeadache, List<Episode> lstEpisodes) {
        this.idHeadache = idHeadache; // egale au nombre de headaches existant + 1
        this.startHeadache = startHeadache;
        this.endHeadache = endHeadache;
        this.lstEpisodes =  lstEpisodes; // by default ,then + 1 by another episode
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

    public int getNbEpisodes() {
        return nbEpisodes;
    }

    public void setNbEpisodes(int nbEpisodes) {
        this.nbEpisodes = nbEpisodes;
    }

    public List<Episode> getLstEpisodes() {
        return lstEpisodes;
    }

    public void setLstEpisodes(List<Episode> lstEpisodes) {
        this.lstEpisodes = lstEpisodes;
    }


    public void addEpisodeInList(Episode anEpisode) {
        lstEpisodes.add(anEpisode);
    }

    public static String writeDiary(List<Headache> lstHeadaches) {
        String strEpisode = "";
        String strHeadaches = "";

        for(Headache aHeadache : lstHeadaches){
            for(Episode anEpisode : aHeadache.getLstEpisodes()){
                strEpisode = strEpisode + anEpisode.toString() ;
            }
            strHeadaches = strHeadaches +
                    "   <headache>"+
                    "   <headache id=\""+aHeadache.getIdHeadache()+"\">"+
                    "       <startHeadache>" + aHeadache.getStartHeadache() + "</startHeadache>" +
                    "       <endHeadache>" + aHeadache.getStartHeadache()+ "</endHeadache>" +
                    "       <episodes>" + strEpisode + "</episodes>" +
                    "       <nbEpisodes>" + aHeadache.getNbEpisodes() + "</nbEpisodes>" +
                    "   </headache>";
        }

        return  "<headaches>" + strHeadaches + "\n" + "</headaches>";

    }
}

