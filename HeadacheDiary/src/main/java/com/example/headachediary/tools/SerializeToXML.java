package com.example.headachediary.tools;

import com.example.headachediary.model.Headache;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Class for serialize headaches episodes
 */
public abstract class SerializeToXML  {
    /**
     * Constants File Name
     */
    private static final String SERIALIZED_FILE_NAME = "MyHeadacheDiary_XML.xml";
    private static final String SERIALIZED_FILE_NAME_JSON = "MyHeadacheDiary_XML.json";

    /**
     * Methode to save in XML file all Headaches entries
     * @param lstHeadache List<Headache>
     * @throws IOException encoder XML
     */
    public static void saveToXML(List<Headache> lstHeadache) throws IOException {
        FileOutputStream fos = new FileOutputStream(SERIALIZED_FILE_NAME);
        XMLEncoder encoder = new XMLEncoder(fos);

        encoder.setExceptionListener(e -> System.err.println("Exception! :" + e.toString()));
        encoder.writeObject(lstHeadache);
        encoder.close();

        fos.close();
    }


    /**
     * Methode to reload XML file with Headaches entries
     * @return
     * @throws IOException
     */
    public static Object loadFromXML() throws IOException {

        Object anObject = null;
        File testXML = new File(SERIALIZED_FILE_NAME);
        if (testXML.exists()) {
            try {
                XMLDecoder decoder = new XMLDecoder(new FileInputStream(SERIALIZED_FILE_NAME));
                anObject = decoder.readObject();

            }catch (Exception e) {
                System.err.println("loadFromXML : Erreur au chargement du journal XML headache");
                e.printStackTrace();
            }

        }
        return anObject;
    }
}
