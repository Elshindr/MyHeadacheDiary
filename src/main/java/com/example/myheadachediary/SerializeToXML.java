package com.example.myheadachediary;


import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class SerializeToXML {
    private static final String SERIALIZED_FILE_NAME = "MyHeadacheDiary_XML.xml";

    public static void saveToXML(List<Headache> lstHeadache) throws IOException
    {
        FileOutputStream fos = new FileOutputStream(SERIALIZED_FILE_NAME);
        XMLEncoder encoder = new XMLEncoder(fos);
        encoder.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                System.out.println("Exception! :"+e.toString());
            }
        });
        encoder.writeObject(lstHeadache);
        encoder.close();
        fos.close();
    }

    public static Object loadFromXML() throws FileNotFoundException, IOException{

        Object anObject = null;
        XMLDecoder decoder = new XMLDecoder(new FileInputStream(SERIALIZED_FILE_NAME));
        try {
            anObject = decoder.readObject();
        }
        finally{
            decoder.close();
        }
        return anObject;
    }
}
