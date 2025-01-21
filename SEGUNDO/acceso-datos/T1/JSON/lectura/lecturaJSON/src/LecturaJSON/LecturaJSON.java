package LecturaJSON;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LecturaJSON {
    public static void main(String[] args) throws IOException {
        String contenido = new String(Files.readAllBytes(Paths.get("jugones.json")));

        JSONObject titulo = new JSONObject(contenido);
        String nombre = titulo.getString("nombre");
        System.out.println(nombre);

        JSONArray participantes = titulo.getJSONArray("participantes");

        for (int i = 0; i<participantes.length();i++){
            JSONObject objetoParticipante = participantes.getJSONObject(i);
            String nombreJugador = objetoParticipante.getString("nombre");
            int edadJugador = objetoParticipante.getInt("edad");
            System.out.println("nombre: "+nombreJugador + " edad: "+edadJugador );
        }
    }
}
