package EscrituraJSON;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EscrituraJSON {
    public static void main(String[] args) throws IOException {
        Jugador j1 = new Jugador("Karim Benzema",32);
        Jugador j2 = new Jugador("Fdson Arantes",87);

        Titulo t = new Titulo("Titulos 24/25");
        t.add(j1);
        t.add(j2);

        //Examen
        JSONObject jsonTitulo = new JSONObject();
        jsonTitulo.put("nombre", t.getNombre());
        //Vamos a recorrer el array de titulos y los jugadores los metemos en el JSONArray
        JSONArray jsonJugadores = new JSONArray();
        for (Jugador j : t.getJugadores()){
            //Creamos objeto y lo metemos
            JSONObject jsonJugador = new JSONObject();
            //Recogemos del arrayList y los metemos en el JSONObject
            jsonJugador.put("nombre", j.getNombre());
            jsonJugador.put("edad", j.getEdad());
            //Lo meto en el array de jsonJugadores
            jsonJugadores.put(jsonJugador);
        }
        //añadir el array al JSON
        jsonTitulo.put("participantes",jsonJugadores);

        //Escritura NO EXAMEN
        Files.write(Paths.get("jugones.json"), jsonTitulo.toString().getBytes());
    }
}
