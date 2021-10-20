package com.example.myheadachediary.model;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.List;

public class SerializeToXML {
    private  SerializeToXML(){}
    private static final String SERIALIZED_FILE_NAME = "MyHeadacheDiary_XML.xml";

    // Methode to save in XML file all Headaches entries
    public static void saveToXML(List<Headache> lstHeadache) throws IOException {
        FileOutputStream fos = new FileOutputStream(SERIALIZED_FILE_NAME);
        XMLEncoder encoder = new XMLEncoder(fos);

        encoder.setExceptionListener(e -> System.out.println("Exception! :" + e.toString()));

        encoder.writeObject(lstHeadache);
        encoder.close();
        fos.close();
    }

    // Methode to reload XML file with Headaches entries
    public static Object loadFromXML() throws IOException {

        Object anObject = null;
        File testXML = new File(SERIALIZED_FILE_NAME);
        if (testXML.exists()) {
            try (
                    XMLDecoder decoder = new XMLDecoder(new FileInputStream(SERIALIZED_FILE_NAME))
            )
            {
                anObject = decoder.readObject();
            }
        }
        return anObject;

    }
}
