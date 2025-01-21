import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class entrada {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        //Crear objeto DocumentBuilder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("src/ejercicio2.xml"));

        //Normalización
        document.getDocumentElement().normalize();

        //Extraer nodo raiz
        Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());

        //Transformación a una estructura de datos Java (es NodeList)
        NodeList nList = document.getElementsByTagName("empleado");
        System.out.println(nList.getLength());

        Node nodo;
        for (int i = 0; i < nList.getLength(); i++){
            nodo = nList.item(i);
            System.out.println("inicio del empleado");
            if (nodo.getNodeType() == Node.ELEMENT_NODE){
                Element elemento = (Element) nodo;
                System.out.println("su id es: "+elemento.getAttribute("id"));
                System.out.println("Nombre: "+elemento.getElementsByTagName("nombre").item(0).getTextContent());
                System.out.println("Apellido: "+elemento.getElementsByTagName("apellidos").item(0).getTextContent());
                System.out.println("Ciudad: "+elemento.getElementsByTagName("ciudad").item(0).getTextContent());

                if (elemento.getElementsByTagName("ciudad").item(0).getTextContent().equalsIgnoreCase("madrid")){
                    System.out.println("Es MADRILEÑ@");
                }

                System.out.println("----------------------------");

            }
        }
    }
}
