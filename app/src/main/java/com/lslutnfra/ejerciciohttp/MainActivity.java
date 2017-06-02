package com.lslutnfra.ejerciciohttp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends AppCompatActivity implements Handler.Callback {

    private ImageView img;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView) findViewById(R.id.img);
        txt = (TextView) findViewById(R.id.txt);
        Handler h = new Handler(this);

        // flag en true para recibir bytes
 /*       ThreadConexion tc = new ThreadConexion(h,"http://www.pngmart.com/files/2/Mario-PNG-Image.png",true);
        Thread t = new Thread(tc);
        t.start();*/
        //________________________________

        // flag en false para recibir un string
        ThreadConexion tc2 = new ThreadConexion(h,"http://www.lslutnfra.com/alumnos/practicas/listaPersonas.xml",false);
        Thread t2 = new Thread(tc2);
        t2.start();
        //_____________________________________

    }

    @Override
    public boolean handleMessage(Message msg) {

        switch(msg.arg1)
        {

            case 0:{
                Log.d("activity","Error");
                break;
            }
            case 1:{
                Log.d("activity","Recibiendo bytes (imagen)");
                byte[] bytes = (byte[]) msg.obj;

                Bitmap bitmap =
                        BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                img.setImageBitmap(bitmap);
                break;
            }
            //http://www.lslutnfra.com/alumnos/practicas/listaPersonas.xml
            case 2:{
                Log.d("activity","Recibiendo string (xml)");
                List<Persona> personas = (List<Persona>) msg.obj;
                for(Persona p :personas){
                    Log.d("Persona",p.getNombre()+" "+p.getEdad());
                }
                txt.setText(personas.get(0).getNombre());
                break;
            }
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
