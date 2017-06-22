package com.josdan.clientealgoritmogenetico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;

import adapters.AdapterString;

public class VerResultados extends AppCompatActivity {

    ListView lvRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_resultados);

        lvRespuesta = (ListView) findViewById(R.id.lvRespuesta);

        String respuesta = getIntent().getStringExtra("RespuestaDelServer");

        ArrayList<Integer> solucion = obtenerArray(respuesta);

        ArrayList<String> arrayNombre = new ArrayList<>();

        int con = 0;
        for (int i = 0; i < solucion.size(); i++) {
            con = i + 1;
            arrayNombre.add("Zona " + con+ " donar :");

        }

        AdapterString adapter = new AdapterString(this, solucion, arrayNombre);

        //ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1,solucion);
        lvRespuesta.setAdapter(adapter);

        Log.d("Solucion-2", solucion.get(1).toString());

    }

    private ArrayList<Integer> obtenerArray(String respuesta) {
        JsonParser parser = new JsonParser();

        JsonElement elemento = parser.parse(respuesta);

        ArrayList<Integer> solucion = new ArrayList<>();
        for (int i=0; i < elemento.getAsJsonArray().size(); i++) {
            solucion.add(elemento.getAsJsonArray().get(i).getAsInt());
        }

        return solucion;
    }
}
