package com.lslutnfra.ejerciciohttp;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alumno on 01/06/2017.
 */

public class XmlParser {

    public static List<Persona> obtenerPersonas(String xml){
        List<Persona> personas = new ArrayList<>();
        XmlPullParser xmlPullParser = Xml.newPullParser();
        try {
            xmlPullParser.setInput(new StringReader(xml));
//http://www.lslutnfra.com/alumnos/practicas/listaPersonas.xml
            int event = xmlPullParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT){

                if(event == XmlPullParser.START_TAG){
                    if(xmlPullParser.getName().equals("Persona")){
                        Persona p =  new Persona();
                        p.setNombre(xmlPullParser.getAttributeValue(null,"nombre"));
                        p.setEdad(Integer.parseInt(xmlPullParser.getAttributeValue(null,"edad")));

                        personas.add(p);
                    }
                }
                event = xmlPullParser.next();
            }



        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personas;

    }
}
