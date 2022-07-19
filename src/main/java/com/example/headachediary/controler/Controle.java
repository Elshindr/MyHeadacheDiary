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

    public Headache createHeadache(String startDay, String endDay){
        Headache aHeadache = new Headache(lstHeadaches == null? 0 : lstHeadaches.size() + 1, startDay, endDay);
        lstHeadaches.add(aHeadache);

        return aHeadache;
    }

    public void createEpisode(String dateStart, String dateEnd, Map<String, String> mapSym, List<String> lstSens, List<String> lstTriggers, Map<String, Integer> mapMed, String commentEp){
        headache = getLastHeadache();
        Episode anEpisode = new Episode(dateStart, dateEnd, mapSym,lstSens, lstTriggers, mapMed, commentEp);

        // on ajoute cette migraine à la liste des migraines puis on la récupère pour update la valeur nbEpisodes
        headache.addEpisodeInList(anEpisode);
        lstEpisodes = headache.getLstEpisodes();

        headache.setNbEpisodes(lstEpisodes.size());
    }

    public List<String> getLstSensi(){
        List<String> fds = new ArrayList<>();
        return fds;
    }

    public List<Headache> getLstHeadaches(){
        return lstHeadaches;
    }

    public List<Episode> getLstEpisodes(Headache aHeadache){
        return aHeadache.getLstEpisodes();
    }

    public static Headache getLastHeadache(){
        if(lstHeadaches.size() == 0){
            return null;
        }
        return  lstHeadaches.get(lstHeadaches.size() - 1);
    }


    public Headache setHeadache(Headache aHeadache, String newValue){
        aHeadache.setEndHeadache(newValue);
        return aHeadache;
    }


    public List<String> getLstEpisode(Episode anEpisode){
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
            System.out.println(" : getNbEpisodes :null" );
            lstHeadaches = new ArrayList<>();
            lstEpisodes = new ArrayList<>();
        }
        else{
            lstHeadaches = (List<Headache>) diary;
            for(Headache anHeadache : lstHeadaches){
                System.out.println(" : getNbEpisodes " + anHeadache.getNbEpisodes());

                for(Episode anEpisode : anHeadache.getLstEpisodes()){
                    List<String> lstSensi = anEpisode.getLstSensi();
                    System.out.println("  :: FOR EP lstSensi " + lstSensi.toString());

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