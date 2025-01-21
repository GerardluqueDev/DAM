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

//Hay que saber como es el formato del documento
public class LecturaXML {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        //RECOGEMOS Y PARSEAMOS EL DOCUMENTO
        String nombreFichero = "src/curso.xml";
        File fichero = new File(nombreFichero);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(fichero);

        //Normalizar el documento
        doc.getDocumentElement().normalize();

        //Extrer nodo raiz:
        Element root = doc.getDocumentElement();
        System.out.println(root.getNodeName());

        //Transformación a una estructura de datos Java
        NodeList nList = doc.getElementsByTagName("Asignatura");

        //Recorrer la estructura para leer información
        Node nodo;
        int contador;
        for (int i = 0; i < nList.getLength(); i++){
            contador = 0;
            nodo = nList.item(i);
            Element element = (Element) nodo;
            System.out.println("Asignatura: "+element.getAttribute("ciclo"));
            if (nodo.getNodeType() == Node.ELEMENT_NODE){
                System.out.println("Alumno: ");
                System.out.println("\tNombre: "+ element.getElementsByTagName("Nombre").item(0).getTextContent());
                System.out.println("\tApellidos: " + element.getElementsByTagName("Apellidos").item(0).getTextContent());
                contador++;
                System.out.println("Total de alumnos en " + element.getAttribute("ciclo") + " : " + contador);
                System.out.println("----------------------------");
            }

        }
    }
}
