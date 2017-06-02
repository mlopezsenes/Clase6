package com.lslutnfra.ejerciciohttp;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ThreadConexion implements Runnable{

    private String url;
    private Handler h;
    private boolean flagBytesString;

    /*	Recibimos un Handler, la URL y un flag que indica si leemos bytes o String
     *
     */
    public ThreadConexion(Handler h,String url,boolean flagBytesString)
    {
        this.url = url;
        this.h =h;
        this.flagBytesString=flagBytesString;
    }

    /*
     * Metodo run del thread, se conecta a la url y recupera los datos que envio por el handler
     *
     */
    public void run() {


        Message msg = new Message();

//        HttpManager httpManager = new HttpManager(url);
        try{
//http://www.lslutnfra.com/alumnos/practicas/listaPersonas.xml
            HttpConection httpConection = new HttpConection();
            if(flagBytesString) {
                byte[] bytesRespuesta;
                bytesRespuesta =httpConection.getBytesDataByGET(url);
                msg.arg1=1;
                msg.obj=bytesRespuesta;
            }else{
                String strRespuesta;
                strRespuesta = httpConection.getStringDataByPost(url);
                msg.arg1=2;

                msg.obj= XmlParser.obtenerPersonas(strRespuesta);
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
