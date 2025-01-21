package main;

import objetos.Heroe;
import objetos.Titulo;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException {

        //creación del título
        Titulo t = new Titulo("Heroes");
        //Llamada al método para crear nuevos superheroes
        crearHeroes(t);
        //Creamos el objeto JSON título
        JSONObject jsonTitulo = new JSONObject();
        //Creamos un JSONArray de heroes
        JSONArray jsonArrayHeroes = new JSONArray();
        //Recorremos el Array de título y los heroes los metemos en JSONArray
        for (Heroe h : t.getHeroes()){
            //Creamos un nuebo JSONObject
            JSONObject jsonHero = new JSONObject();
            //Recogemos del ARRAYLIST título los heroes e introducimos las características que queremos en nuestro JOSN
            jsonHero.put("hero",h.getNombreHeroe());
            jsonHero.put("name",h.getNombreReal());
            jsonHero.put("link",h.getLink());
            jsonHero.put("img",h.getImg());
            jsonHero.put("size",h.getTamanio());
            //Ahora metemos el objeto ya lleno en el jsonArrayHeroes
            jsonArrayHeroes.put(jsonHero);
        }
        //Introducimos el nombre del título (será el nombre del primer campo del JSON "hero":)
        jsonTitulo.put("hero", t.getTitulo());
        //Tras el bucle introducimos el Array jsonArrayHeroes al objeto Título
        jsonTitulo.put("heroes",jsonArrayHeroes);
        //Finalmente creamos el JSON
        Files.write(Path.of("reto2.json"),jsonTitulo.toString().getBytes());
    }
    public static void crearHeroes(Titulo t){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("¿Quieres introducir un nuevo superheroe?\n1. Sí (escribe 'Si')\n2. No, quiero terminar (escribe '*')");
            String input = sc.nextLine();
            switch (input.toUpperCase()){
                case "SI":
                    System.out.println("Introduce el nombre del superheroe");
                    String nombreHeroe = sc.nextLine();
                    System.out.println("Introduce el nombre real de la persona");
                    String nombreReal = sc.nextLine();
                    System.out.println("Introduce el link");
                    String link = sc.nextLine();
                    System.out.println("Introduce el link de la imagen");
                    String img = sc.nextLine();
                    System.out.println("Introduce el tamaño");
                    int tamanio = sc.nextInt();
                    sc.nextLine();
                    Heroe h = new Heroe(nombreHeroe, nombreReal, link, img, tamanio);
                    t.add(h);
                    break;
                case "*":
                    System.out.println("¡Has terminado, hasta la próxima!");
                    break;
                default:
                    System.out.println("Introduce una respuesta valida");
                    break;
            }
            if (input.equals("*")){
                break;
            }
        }
    }
}
