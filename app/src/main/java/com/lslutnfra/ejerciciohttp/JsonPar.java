package com.lslutnfra.ejerciciohttp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;

/**
 * Created by alumno on 01/06/2017.
 */

public class JsonPar {
    public void parcerar(String jsonFrutas){
    try {
        JSONObject jsonObject = new JSONObject("{'codigo': 200,'mensaje':'Validado'}");
//        JSONArray frutas = jsonObject.getJSONArray("frutas");
//        for(int i=0;i<frutas.length();i++){
//            JSONObject fruta = frutas.getJSONObject(i);
            String mensaje = jsonObject.getString("mensaje");
            Integer cod = jsonObject.getInt("codigo");
//        }
    }catch (Exception e){
        e.printStackTrace();
    }
    }
}
