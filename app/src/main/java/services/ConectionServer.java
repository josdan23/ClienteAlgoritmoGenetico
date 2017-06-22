package services;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by josdan on 21/06/17.
 */

public class ConectionServer {
    private static final int SERVERPORT = 5001;
    private static final String ADDRESS = "192.168.42.24";

    private Socket socket;

    public ConectionServer(){

    }

    public void conection() throws IOException {
        //Se conecta al servidor
        InetAddress serverAddr = InetAddress.getByName(ADDRESS);
        Log.i("I/TCP Client", "Connecting...");
        socket = new Socket(serverAddr, SERVERPORT);
        Log.i("I/TCP Client", "Connected to server");
    }

    public void enviarPeticion(String peticion) throws IOException {
        //envia peticion de cliente
        Log.i("I/TCP Client", "Send data to server");
        PrintStream output = new PrintStream(socket.getOutputStream());
        output.println(peticion);
    }

    public String respuestaDeServidor() throws IOException {
        //recibe respuesta del servidor y formatea a String
        Log.i("I/TCP Client", "Received data to server");
        InputStream stream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        String received = bufferedReader.readLine();
        return received;
    }

    public void cerrarConection() throws IOException {
        socket.close();
    }
}
