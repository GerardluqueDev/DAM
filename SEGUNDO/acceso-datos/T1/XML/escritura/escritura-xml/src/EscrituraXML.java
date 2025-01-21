
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class EscrituraXML {
    public static void main(String[] args) throws FileNotFoundException, ParserConfigurationException, TransformerException {
        //Creación de un documento xml
        String nombreFichero = "src/curso.xml";
        File fichero = new File(nombreFichero);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        //Creamos un documento
        Document doc = dBuilder.newDocument();

        // TRABAJO DEL EXÁMEN !!
        Element elementoPrincipal = doc.createElement("UAX");
        elementoPrincipal.setAttribute("lugar","Brunete");
        doc.appendChild(elementoPrincipal);

        Element asignatutra = doc.createElement("Asignatura");
        asignatutra.setAttribute("ciclo","DAM");
        elementoPrincipal.appendChild(asignatutra);

        Element alumno = doc.createElement("Alumno");
        alumno.appendChild(doc.createTextNode("Pepe"));
        asignatutra.appendChild(alumno);
        //Agregamos otro alumno
        alumno = doc.createElement("Alumno");
        alumno.appendChild(doc.createTextNode("Sara"));
        asignatutra.appendChild(alumno);



        //Transformer para la escritura
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        //Hacemos referencia al documento doc que es el documento que hemos creado
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new FileOutputStream(fichero));
        transformer.transform(source,result);
        System.out.println("");
    }
}
