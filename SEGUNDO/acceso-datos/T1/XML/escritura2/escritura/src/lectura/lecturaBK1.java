package lectura;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class lecturaBK1 {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse("BurguerKing1.xml");
        System.out.println("Todo correcto");
        doc.getDocumentElement().normalize();

        //Lo primero que tenemos que hacer es recoger el nodo principal
        Element nodoRaiz = doc.getDocumentElement();

        //Desde elemento podemos acceder a los atributos
        String atributoTitulo = nodoRaiz.getAttribute("title");
        System.out.println(atributoTitulo);

        //Recoger Elementos hijos del DOM de unXML hay que meterlo en NodeList
        //Creamos un NodeList que lo va a recoger todo
        NodeList nList = doc.getElementsByTagName("menu");
        System.out.println(nList.getLength()); //Nos dice cuantos elementos tiene el nList
        //Empezamos a recorrer el NodeList
        for (int i = 0; i < nList.getLength(); i++) {
            //Hay que convertir cada uno de los menus en un Element
            Element menu = (Element) nList.item(i);
            //Recogemos de cada menu, los nombres
            String nombre = menu.getElementsByTagName("nombre").item(0).getTextContent();
            String salsa = menu.getElementsByTagName("salsa").item(0).getTextContent();
            System.out.println("El menú " + nombre + " tiene una salsa de " + salsa);

        }

    }
}
