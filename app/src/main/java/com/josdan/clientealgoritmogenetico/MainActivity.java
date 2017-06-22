package com.josdan.clientealgoritmogenetico;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;

import adapters.ListAdapter;
import model.Zona;
import services.ConectionServer;

public class MainActivity extends AppCompatActivity {

    ListView lvZonasAfectadas;
    EditText etCantidadDonada;
    EditText etNumeroDeZona;
    EditText etCantidadAguaRequerida;
    Button btCrearZona;
    Context context = this;

    ArrayList<Zona> arrayZonas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarComponentes();

        btCrearZona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrayZonas.add(new Zona(Integer.valueOf(etNumeroDeZona.getText().toString()), Integer.valueOf(etCantidadAguaRequerida.getText().toString())));
                etNumeroDeZona.setText("");
                etCantidadAguaRequerida.setText("");
            }
        });

    }

    private void iniciarComponentes() {

        etCantidadDonada = (EditText) findViewById(R.id.etCantidadDonada);
        etNumeroDeZona = (EditText) findViewById(R.id.etNumeroDezona);
        etCantidadAguaRequerida = (EditText) findViewById(R.id.etCantidadAguaRequerida);

        btCrearZona = (Button) findViewById(R.id.btCrearZona);


        setupListView();

    }

    private void setupListView() {
        lvZonasAfectadas = (ListView) findViewById(R.id.lvZonasAfectadas);

        //ArrayList<Zona> arrayZonas = obtenerZonas();

        ListAdapter adapter = new ListAdapter(this, arrayZonas);

        lvZonasAfectadas.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.enviarPeticion:
                int cantidadDonada = Integer.valueOf(etCantidadDonada.getText().toString());
                String mensaje = obtenerJson(cantidadDonada, arrayZonas);
                Log.d("JSON", mensaje);
                MyATaskCliente myATaskCliente = new MyATaskCliente();
                myATaskCliente.execute(mensaje);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private String obtenerJson(int cantidadDonada, ArrayList<Zona> arrayZonas) {
        Gson gson =  new Gson();


        String mensaje = "{'cantidadDonada':"+cantidadDonada+",'zonas':" + gson.toJson(arrayZonas) + '}';

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("cantidadDonada", cantidadDonada);
        jsonObject.addProperty("zonas", mensaje);
        return mensaje;
    }

    class MyATaskCliente extends AsyncTask<String, Void, String> {
        /**
         * Ventana que bloqueara la pantalla del movil hasta recibir respuesta del servidor
         */
        ProgressDialog progressDialog;

        /**
         * muestra una ventana emergente
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setTitle("Connecting to server");
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
        }


        @Override
        protected String doInBackground(String... values) {

            ConectionServer com = new ConectionServer();
            try {
                com.conection();
                com.enviarPeticion(values[0]);
                String respuesta = com.respuestaDeServidor();
                com.cerrarConection();

                return respuesta;
            } catch (IOException ex) {
                Log.e("E/TCP Client", "" + ex.getMessage());
                return ex.getMessage();
            }
        }


        /**
         * Oculta ventana emergente y muestra resultado en pantalla
         */
        @Override
        protected void onPostExecute(String respuesta) {
            progressDialog.dismiss();
            Toast.makeText(context, respuesta, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, VerResultados.class);

            intent.putExtra("RespuestaDelServer", respuesta);
            startActivity(intent);


        }

    }
}
