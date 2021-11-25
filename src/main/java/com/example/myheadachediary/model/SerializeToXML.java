package com.example.myheadachediary.model;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.List;
/**
 * Class for serialize headaches episodes
 */
public class SerializeToXML  {
    /**
     *
     */
    private static final String SERIALIZED_FILE_NAME = "MyHeadacheDiary_XML.xml";

    /**
     * Methode to save in XML file all Headaches entries
     * @param
     * @throws IOException
    */
    public static void saveToXML(List<Headache> lstHeadache) throws IOException {
        FileOutputStream fos = new FileOutputStream(SERIALIZED_FILE_NAME);
        XMLEncoder encoder = new XMLEncoder(fos);

        encoder.setExceptionListener(e -> System.out.println("Exception! :" + e.toString()));

        encoder.writeObject(lstHeadache);
        encoder.close();
        fos.close();
    }
    /**
    public static void saveToXML(List<Headache> lstHeadaches) throws IOException {
        // Instanciation de la classe XStream
        System.out.println("DEBUT SAVE __________________________");
            /**XStream xstream = new XStream();

        //xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        System.out.println("233333333333333333333333333333");
           // xstream.alias("episode", Episode.class);
            //xstream.alias("headache", Headache.class);

            // Tag implicit array
            //xstream.addImplicitArray(Episode.class, "episodes", "episode");
            //xstream.addImplicitArray(Headache.class,"headaches", "headache");

        List<Episode> lstEpisodes = new ArrayList<Episode>();
        Episode anEpisode = new Episode(10, "125-10-2021 09:43", "125-10-2021 09:43", "String severity", "String dizziness", "String aura", "String nausea", "String sideHeadache", "String otherSymp", "String hypersensibility", "String triggers", "String medication") ;
        lstEpisodes.add(anEpisode);
        Headache newHeadache = new Headache("125-10-2021 09:43", "125-10-2021 09:43", lstEpisodes);


        String xml = xstream.toXML(newHeadache);
        System.out.println("ADDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
        System.out.println(xml);
         /*   // Instanciation d'un fichier c:/temp/article.xml
            File fichier = new File(SERIALIZED_FILE_NAME);
            // Instanciation d'un flux de sortie fichier vers
            // SERIALIZED_FILE_NAME
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fichier);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("444444444444444444444444444444");

            try {
                // Sérialisation de l'objet article dans SERIALIZED_FILE_NAME

                System.out.println("555555555555555555555555555555555555555555555555555");
                for(Headache aHeadache : lstHeadaches){
                    System.out.println("TEST "+ aHeadache.getIdHeadache() + aHeadache.getStartHeadache());
                    xstream.toXML(aHeadache, fos);
                    System.out.println("22222222222 "+ aHeadache.getIdHeadache());
                   // xstream.registerConverter(new Diary());
                }


            }finally {
                // On s'assure de fermer le flux quoi qu'il arrive
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*

        System.out.println("DEBUT boucle liste des headaches");
        for(Headache aHeadache : lstHeadaches){
            System.out.println("Headache n° " + aHeadache.getIdHeadache()+ " " + aHeadache.getStartHeadache());
            //xstream.toXML(aHeadache, fos);
            //System.out.println("22222222222 "+ aHeadache.getIdHeadache());
            // xstream.registerConverter(new Diary());

            System.out.println("\n\nUn headache par morceaux");
            Hello.main(aHeadache);
        }

        //TestJson.txtToXml(joe);



    }*/



    /**
     * Methode to reload XML file with Headaches entries
     * @return
     * @throws IOException
     */
    public static Object loadFromXML() throws IOException {

        Object anObject = null;
        File testXML = new File(SERIALIZED_FILE_NAME);
        if (testXML.exists()) {
            try (
                    XMLDecoder decoder = new XMLDecoder(new FileInputStream(SERIALIZED_FILE_NAME))
            ) {
                anObject = decoder.readObject();
            }
        }
        return anObject;
    }
        /**

        Object anObject = null;
        try {
            // Instanciation de la classe XStream
            XStream xstream = new XStream();
            System.out.println("233333333333333333333333333333");

            // Redirection du fichier c:/temp/article.xml vers un flux
            // d'entrée fichier

            System.out.println("444444444444444444444444444444");
            FileInputStream fis = new FileInputStream(SERIALIZED_FILE_NAME);
            System.out.println("555555555555555555555555555555555555555555555");

                // Désérialisation du fichier c:/temp/article.xml vers un nouvel
                // objet article
                //Article nouvelArticle = (Article) xstream.fromXML(fis);
                anObject = (Headache) xstream.fromXML(fis);
                // Affichage sur la console du contenu de l'attribut synopsis
                // System.out.println(nouvelArticle.getSynopsis());


                // On s'assure de fermer le flux quoi qu'il arrive
                fis.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        *
        return (Headache)anObject;/
    }*/
}
