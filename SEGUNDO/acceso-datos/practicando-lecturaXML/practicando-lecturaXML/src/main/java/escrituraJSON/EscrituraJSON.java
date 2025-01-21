package escrituraJSON;

import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class EscrituraJSON {
    public static void main(String[] args) throws IOException {

        Libro l1 = new Libro("El Quijote","Miguel de Cervantes",1605);
        Libro l2 = new Libro("Cien Años de Soledad","Gabriel García Márquez",1967);
        Libro l3 = new Libro("1984","George Orwell",1949);

        Titulo titulo = new Titulo("Libros");
        titulo.addLibro(l1);
        titulo.addLibro(l2);
        titulo.addLibro(l3);

        JSONObject obj = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for(Libro libro: titulo.getLibros()) {
            JSONObject objLibro = new JSONObject();
            objLibro.put("titulo",libro.getNombre());
            objLibro.put("autor",libro.getAutor());
            objLibro.put("año",libro.getAnio());
            jsonArray.put(objLibro);
        }
        obj.put("libros", jsonArray);
        System.out.println(obj.toString(4));

        Files.write(Paths.get("libros.json"),obj.toString().getBytes());


    }
}
