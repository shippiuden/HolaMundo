package com.zapata.edwin.holamundo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.io.OutputStreamWriter;

import android.view.View;
import android.widget.Button;

import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBaseE MBD = new DataBaseE(getApplicationContext());

        Button boton = (Button) findViewById(R.id.guardar);
        Button botonClear = (Button) findViewById(R.id.borrar);
        //MBD.insertarDato(1, "p", 1.0, 1.0);
//        String textSave = "Edd";
//
//        try {
//            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("notas.txt",MODE_APPEND));
//            archivo.write(textSave);
//            archivo.flush();
//            archivo.close();
//        } catch (IOException e) {
//        }
//        Toast t = Toast.makeText(this, "Los datos fueron grabados",
//                Toast.LENGTH_SHORT);
//        t.show();


        // Escribimos 4 registros en nuestra tabla
        //MDB.insertarDato(2, "2", 0.0, 0.0);

        // Recuperamos los 4 registros y los mostramos en el log
//        Log.d("TOTAL", Integer.toString(MDB.recuperarDATOS().size()));
//        int[] ids = new int[MDB.recuperarDATOS().size()];
//        String[] noms = new String[MDB.recuperarDATOS().size()];
//        Double[] tlfs = new Double[MDB.recuperarDATOS().size()];
//        Double[] emls = new Double[MDB.recuperarDATOS().size()];
//
//        for (int i = 0; i < MDB.recuperarDATOS().size(); i++) {
//            ids[i] = MDB.recuperarDATOS().get(i).getID();
//            noms[i] = MDB.recuperarDATOS().get(i).getDedo();
//            tlfs[i] = MDB.recuperarDATOS().get(i).getPosX();
//            emls[i] = MDB.recuperarDATOS().get(i).getPosY();
//            Log.d(""+ids[i], noms[i] + ", " + tlfs[i] + ", " + emls[i]);
//        }

        // Modificamos el registro 3
        //MDB.modificarCONTACTO(3, "PPPPP", 121212121, "xxxx@xxxx.es");

        // Recuperamos el 3 registro y lo mostramos en el log
        //int id = MDB.recuperarCONTACTO(3).getID();
        //String nombre = MDB.recuperarCONTACTO(3).getNOMBRE();
        //int telefono = MDB.recuperarCONTACTO(3).getTELEFONO();
        //String email = MDB.recuperarCONTACTO(3).getEMAIL();
        //Log.d(""+id, nombre + ", " + telefono + ", " + email);

        // Borramos el registro 3
        //MDB.borrarCONTACTO(3);

    }

    public void textGenerator(View v){
        String textSave = "";
        DataBaseE MDB = new DataBaseE(getApplicationContext());
        int sizeDB= MDB.recuperarDATOS().size();
        int[] ids = new int[MDB.recuperarDATOS().size()];
        String[] dedos = new String[MDB.recuperarDATOS().size()];
        Double[] posX = new Double[MDB.recuperarDATOS().size()];
        Double[] posY = new Double[MDB.recuperarDATOS().size()];

        for (int i = 1; i <= MDB.recuperarDATOS().size(); i++) {
            ids[i] = MDB.recuperarDATOS().get(i).getID();
            dedos[i] = MDB.recuperarDATOS().get(i).getDedo();
            posX[i] = MDB.recuperarDATOS().get(i).getPosX();
            posY[i] = MDB.recuperarDATOS().get(i).getPosY();
            textSave = textSave + dedos[i] + "," + Double.toString(posX[i]) + "," + Double.toString(posY[i]) + "; ";
        }


        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("notas.txt",MODE_APPEND));
            archivo.write(textSave);
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
        }
        Toast t = Toast.makeText(this, "Los datos fueron grabados",
                Toast.LENGTH_SHORT);
        t.show();

        for (int i=1; i<=sizeDB; i++){
            MDB.borrarDato(i);
        }

    }

    public void clearDataBase(View v){

    }
}
