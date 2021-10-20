package com.example.myheadachediary.model;

/*
public class Diary {
    public Diary(){}
    public Diary(String strHeadaches){}


    }
}*/

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class Diary {
    public static void main(String strDiary) {

        final String strXML = strDiary;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Document document = null;
        try {
            document = builder.parse(new File( "MyDiaryTest.xml" ));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Schema schema = null;
        try {
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory schfactory = SchemaFactory.newInstance(language);
            schema = schfactory.newSchema(new File("MyDiaryTest.xml"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        Validator validator = schema.newValidator();
        try {
            validator.validate(new DOMSource(document));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element root = document.getDocumentElement();

       // element.getAttribute("attributeName") ;    //returns specific attribute
        //element.getAttributes();                //returns a Map (table) of names/values

        //Element node;
       // node.getElementsByTagName("subElementName"); //returns a list of sub-elements of specified name
        //node.getChildNodes()         ;                //returns a list of all child nodes
    }



}
