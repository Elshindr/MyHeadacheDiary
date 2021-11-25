package com.example.myheadachediary.model;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * This Diary Class convert headaches datas for XML serialize
 */
public class Diary implements Converter {

    public void testHedache(){
        System.out.println();
    }

    @Override
    public boolean canConvert(Class clazz) {
        return clazz.equals(Headache.class);
    }

    /**
     * marshal method is responsible for translating an object to XML.
     * @param value the object we are trying to convert
     * @param writer the writer were we should output the data
     * @param context the current marshalling context
     */
    @Override
    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {

        Headache aHeadache = (Headache) value;
        writer.startNode("id_Headache");
        writer.setValue(String.valueOf(aHeadache.getIdHeadache()));
        writer.endNode();

        writer.startNode("day_start");
        writer.setValue(aHeadache.getStartHeadache());
        writer.endNode();

        writer.startNode("day_end");
        writer.setValue(aHeadache.getEndHeadache());
        writer.endNode();

        writer.startNode("nbEpisodes");
        writer.setValue(String.valueOf(aHeadache.getLstEpisodes().size()));
        writer.endNode();

        writer.startNode("episodes");
        writer.setValue(String.valueOf(aHeadache.getLstEpisodes()));
        writer.endNode();
    }
    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {

        Headache aHeadache = new Headache();

        reader.moveDown();
        aHeadache.setIdHeadache(reader.getAttributeCount());
        reader.moveUp();

        reader.moveDown();
        aHeadache.setStartHeadache(reader.getValue());
        reader.moveUp();


        // reader.moveDown();
        // aHeadache.setLstEpisodes(reader.getValue());
        // reader.moveUp();

        return aHeadache;
    }

}
