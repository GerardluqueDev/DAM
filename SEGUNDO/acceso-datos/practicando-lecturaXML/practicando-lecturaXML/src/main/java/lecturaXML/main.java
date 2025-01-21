package lecturaXML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        /*
        Extraiga y muestre los datos de cada libro en el formato:
    Categoría: Novela
    ID: 1
    Título: El Quijote
    Autor: Miguel de Cervantes (Española)
    Año: 1605
    Editorial: Francisco de Robles
         */

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("biblioteca.xml");

        doc.getDocumentElement().normalize();

        //root -> esto es la biblioteca
        Element root = doc.getDocumentElement();
        //entramos en nodelis de categorias
        NodeList categorias = root.getElementsByTagName("categoria");
        int contador = 0;
        for (int i = 0; i < categorias.getLength(); i++) {
            contador++;
            Element categoria = (Element) categorias.item(i);
            String nombreCategoria = categoria.getAttribute("nombre");
            System.out.println("La categoria del nº"+contador+": "+nombreCategoria);
            //entramos en nodelist de libros
            NodeList libros = categoria.getElementsByTagName("libro");
            for (int j = 0; j < libros.getLength(); j++) {
                //sacamos cada elemento de libro
                Element libro = (Element) libros.item(j);
                String idLibro = libro.getAttribute("id");
                System.out.println("El ide del libro nº"+contador+": "+idLibro);
                String tituloLibro = libro.getElementsByTagName("titulo").item(0).getTextContent();
                System.out.println("El titulo del libro nº"+contador+": "+tituloLibro);

                //sacamos el nodelist de autores de cada libro
                NodeList autores = libro.getElementsByTagName("autor");
                for(int k = 0; k < autores.getLength(); k++) {
                    //sacamos cada elemento autor
                    Element autor = (Element) autores.item(k);
                    String nombreAutor = autor.getElementsByTagName("nombre").item(0).getTextContent();
                    String nacionalidadAutor = autor.getElementsByTagName("nacionalidad").item(0).getTextContent();
                    System.out.println("El autor del libro es: "+nombreAutor);
                    System.out.println("La nacionalidad del autor es: "+nacionalidadAutor);
                }
                //sacamos el nodelist de publicaciones
                NodeList publicaciones = libro.getElementsByTagName("publicacion");
                for(int k = 0; k < publicaciones.getLength(); k++) {
                    Element publicacion = (Element) publicaciones.item(k);
                    String anio = publicacion.getElementsByTagName("año").item(0).getTextContent();
                    String editorial = publicacion.getElementsByTagName("editorial").item(0).getTextContent();
                    System.out.println("El año de publicacion es: "+anio);
                    System.out.println("La editorial es: "+editorial);
                }


            }
            System.out.println("------------------------------------------------");
        }















    }
}
/*
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("canal.xml");

        doc.getDocumentElement().normalize();

        Element root = doc.getDocumentElement();
        String tituloCanal = root.getElementsByTagName("titulo").item(0).getTextContent();
        String copyCanal = root.getElementsByTagName("copyright").item(0).getTextContent();
        System.out.println("titulo canal: "+tituloCanal+"   copyriht: "+copyCanal);
        Element noticias = (Element) root.getElementsByTagName("noticias").item(0);
        NodeList noticia = noticias.getElementsByTagName("noticia");
        for (int i = 0; i < noticia.getLength(); i++) {
            Element not = (Element) noticia.item(i);
            String tituloNot = not.getAttribute("titulo");
            System.out.println(tituloNot);

        }
        */