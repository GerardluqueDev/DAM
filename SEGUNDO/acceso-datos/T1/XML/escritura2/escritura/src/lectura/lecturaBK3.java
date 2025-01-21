package lectura;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class lecturaBK3 {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse("BurguerKing3.xml");

        //Normalización
        doc.getDocumentElement().normalize();

        //Lo primero que tenemos que hacer es recoger el nodo principal
        Element nodoRaiz = doc.getDocumentElement();

        //Desde elemento podemos acceder a los atributos
        String atributoTitulo = nodoRaiz.getAttribute("title");
        System.out.println(atributoTitulo);

        //Recoger Elementos hijos del DOM de unXML hay que meterlo en NodeList
        //Creamos un NodeList que lo va a recoger todo
        //Queremos recoger la cantidad de salsa
        NodeList nList = doc.getElementsByTagName("menu");

        //Empezamos a recorrer el NodeList
        for (int i = 0; i < nList.getLength(); i++){
            //Hay que convertir cada una de las menus en un Element
            Element menu = (Element) nList.item(i);
            String nombreMenu = menu.getAttribute("desc");
            // Obtenemos el nodo "salsa" dentro del "menu"
            NodeList salsaList = menu.getElementsByTagName("salsa");
            Element salsa = (Element) salsaList.item(0);
            String atributoSalsa = salsa.getAttribute("cantidad");
            System.out.println("La cantidad de salsa es del menú "+ nombreMenu + " es de "+ atributoSalsa);


        }


    }
}
