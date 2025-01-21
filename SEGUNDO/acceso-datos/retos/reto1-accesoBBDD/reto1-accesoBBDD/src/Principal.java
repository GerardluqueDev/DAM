import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Principal {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        //Nos crea un objeto que es el que vamos a necesitar para construir nuestro DocumentBuilder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("noticias.xml"));

        //Normalizar el documento
        document.getDocumentElement().normalize();

        //Transformación del documento a elementos
        //Vamos a acceder al elemento hijo del documento
        Element root = document.getDocumentElement();
        NodeList channel = root.getElementsByTagName("channel");

        //Recorrer los canales
        for (int i = 0; i < channel.getLength(); i++) {
            System.out.println("-----> CANAL <-----");
            Element channelElement = (Element) channel.item(i);
            String tituloChannel = channelElement.getElementsByTagName("title").item(0).getTextContent();
            System.out.println("Titulo del Canal: " + tituloChannel);
            String copyright = channelElement.getElementsByTagName("copyright").item(0).getTextContent();
            System.out.println("Copyright: " + copyright);
            System.out.println("*********************************************");

            //Obtener los elementos <item>
            NodeList item = channelElement.getElementsByTagName("item");
            //Recorrer los elementos <item> dentro de channel
            for (int j = 0; j < item.getLength(); j++) {
                System.out.println("------> NOTÍCIA " + (j + 1) + " <------");
                Element itemElement = (Element) item.item(j);
                String tituloNoticia = itemElement.getElementsByTagName("title").item(0).getTextContent();
                System.out.println("\tEl título de la notícia es: " + tituloNoticia);
                NodeList mediaTitleList = itemElement.getElementsByTagName("media:title");
                if (mediaTitleList.getLength() > 0) { // Verificar si media:title existe
                    Element mediaTitleElement = (Element) mediaTitleList.item(0);
                    if (mediaTitleElement != null) { // Asegurar que el elemento no sea null
                        String tituloMedia = mediaTitleElement.getTextContent();
                        System.out.println("\tEl titulo de media:title es: " + tituloMedia);

                        // Atributo de media:title
                        String atributoMedia = mediaTitleElement.getAttribute("type");
                        System.out.println("\tEl atributo de media:title es: " + atributoMedia);
                    } else {
                        System.out.println("\t----> media:title no tiene contenido <----");
                    }
                } else {
                    System.out.println("\t----> No hay media:title en este item <-----");
                }

                NodeList creador = itemElement.getElementsByTagName("dc:creator");
                if (creador.getLength() > 0) {
                    Element creatorElement = (Element) creador.item(0);
                    if (creatorElement != null) {
                        String creator = creatorElement.getTextContent();
                        System.out.println("\tEl creador de la notícia es: " + creator);
                    }
                } else {
                    System.out.println("\t----> No hay dc:creator en este item <-----");
                }
                System.out.println("===========================================================");
            }
        }
    }
}
