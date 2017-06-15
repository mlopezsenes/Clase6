package com.lslutnfra.ejerciciohttp;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONObject;

public class ThreadConexion implements Runnable{

    private String url;
    private Handler h;
    private boolean flagBytesString;

    public JSONObject getDatos() {
        return datos;
    }

    public void setDatos(JSONObject datos) {
        this.datos = datos;
    }

    private JSONObject datos;

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    private String metodo;

    /*	Recibimos un Handler, la URL y un flag que indica si leemos bytes o String
     *
     */
    public ThreadConexion(Handler h,String url,boolean flagBytesString,String metodo)
    {
        this.url = url;
        this.h =h;
        this.flagBytesString=flagBytesString;
        this.metodo = metodo;
    }

    /*
     * Metodo run del thread, se conecta a la url y recupera los datos que envio por el handler
     *
     */
    public void run() {


        Message msg = new Message();

//        HttpManager httpManager = new HttpManager(url);
        try{

            HttpConection httpConection = new HttpConection(this.metodo);
            if(flagBytesString) {
                byte[] bytesRespuesta;
                bytesRespuesta =httpConection.getBytesData(url);
                msg.arg1=1;
                msg.obj=bytesRespuesta;
            }else{
                String strRespuesta;
                if (this.metodo == "POST" && this.datos.length() != 0){
                    httpConection.setDatos(this.datos);
                    Log.d("TC",this.datos.toString());
                }
                strRespuesta =  httpConection.getStringData(url);
                Log.d("hilo",strRespuesta);
                msg.arg1=2;
                msg.obj=strRespuesta;
            }
//            if(flagBytesString)
//            {
//                byte[] bytesRespuesta;
//                bytesRespuesta = httpManager.getBytesDataByGET();
//                msg.arg1=1;
//                msg.obj=bytesRespuesta;
//            }
//            else
//            {
//                String strRespuesta;
//                strRespuesta = httpManager.getStrDataByGET();
//                msg.arg1=2;
//                msg.obj=strRespuesta;
//            }

        }catch(Exception e)
        {
            Log.d("http","ERROR "+e.getMessage());
            msg.arg1=0;
        }

        h.sendMessage(msg);
    }



}
