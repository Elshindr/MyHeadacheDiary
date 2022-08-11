package com.example.headachediary.controler;

import com.example.headachediary.model.Episode;
import com.example.headachediary.model.Headache;
import com.example.headachediary.tools.SerializeToXML;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Class Controller
 */
public class Controle {
    private static Controle instance = null;
    private static Headache headache;
    private static List<Headache> lstHeadaches = new ArrayList<>();
    private static List<Episode> lstEpisodes = new ArrayList<>();

    private static final Logger logger = Logger.getLogger("LogControle ::: ");

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


    /**
     * Method to create new Headache
     * @param startDay
     * @param endDay
     * @return aHeadache
     */
    public Headache createHeadache(String startDay, String endDay){
        Headache aHeadache = new Headache(lstHeadaches == null? 0 : lstHeadaches.size() + 1, startDay, endDay);
        lstHeadaches.add(aHeadache);

        return aHeadache;
    }

    /**
     * Method to create an Episode in last Headache
     * @param dateStart
     * @param dateEnd
     * @param mapSym
     * @param lstSens
     * @param lstTriggers
     * @param mapMed
     * @param commentEp
     */
    public void createEpisode(String dateStart, String dateEnd, Map<String, String> mapSym, List<String> lstSens, List<String> lstTriggers, Map<String, Integer> mapMed, String commentEp){
        headache = getLastHeadache();
        Episode anEpisode = new Episode(dateStart, dateEnd, mapSym,lstSens, lstTriggers, mapMed, commentEp);

        headache.addEpisodeInList(anEpisode);
        lstEpisodes = headache.getLstEpisodes();

        headache.setNbEpisodes(lstEpisodes.size());
    }

    /**
     * Method to get avg Sensibility
     * @return
     */
    public List<String> getAvgSensi(){
      //  List<String> fds = new ArrayList<>();
        return null;
    }

    /**
     * Method to get list Headaches
     * @return List<Headache>
     */
    public List<Headache> getLstHeadaches(){
        return lstHeadaches;
    }

    /**
     * Method to get the last Headache
     * @return Headache
     */
    public static Headache getLastHeadache(){
        if(lstHeadaches.size() == 0){
            return null;
        }
        return  lstHeadaches.get(lstHeadaches.size() - 1);
    }

    /**
     * Method to modify the end date of a Headache
     * @param aHeadache
     * @param newValue
     * @return Headache
     */
    public Headache setEndDateHeadache(Headache aHeadache, String newValue){
        aHeadache.setEndHeadache(newValue);
        return aHeadache;
    }

    /**
     * Method to get list of Episode in a headache
     * @param aHeadache
     * @return List<Episode>
     */
    public List<Episode> getLstEpisode(Headache aHeadache){
        return aHeadache.getLstEpisodes();
    }

    /**
     * Method to get list of sensibility in a episode
     * @param anEpisode
     * @return List<String> sensibility
     */
    public List<String> getLstSensi(Episode anEpisode){
        return anEpisode.getLstSensi();
    }

    /**
     * Method to get data form xml files
     * Call loadFromXML method form SerializeToXML Class
     * @return diary null or xml data
     * @see SerializeToXML
     */
    public static List<Headache> loadDiary() throws IOException {
        Object diary = SerializeToXML.loadFromXML();
        if(diary == null){
            lstHeadaches = new ArrayList<>();
            lstEpisodes = new ArrayList<>();
        }
        else{
            lstHeadaches = (List<Headache>) diary;
            for(Headache anHeadache : lstHeadaches){
                for(Episode anEpisode : anHeadache.getLstEpisodes()){
                    anEpisode.setCvsSymp();
                    anEpisode.setCvsSensi();
                    anEpisode.setCvsTrigger();
                    anEpisode.setStrDates();
                    anEpisode.setCvsMed();
                }
            }
            lstEpisodes = getLastHeadache().getLstEpisodes();
        }

        return lstHeadaches;
    }

    /**
     * Method to find averages data
     * @return List<String>
     */
    public List<String>  getAverageData(){
        List<String> arrLastestData = new ArrayList<>();
        Episode lastEpisode = lstEpisodes.get(lstEpisodes.size() - 1);

        if(lstEpisodes.size() != 0) {
            arrLastestData.add(lastEpisode.getLstTriggers().toString().replaceAll("[\\[\\]]",""));
        }
        return arrLastestData;
    }

    /**
     * Method to save data in XML file
     * Call saveToXML method form SerializeToXML Class
     */
    public static void saveInDiary()  {
        try {
            SerializeToXML.saveToXML(lstHeadaches);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}