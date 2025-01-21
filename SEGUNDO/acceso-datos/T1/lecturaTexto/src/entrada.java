import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.imageio.IIOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class entrada {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        
        File fichero = null;
        FileReader fr = null;
        BufferedReader br = null;

        //Creación de las bases:
        fichero = new File("src/texto.txt");
        fr = new FileReader(fichero);
        br = new BufferedReader(fr);

        //Fichero de copia:
        File copia = new File("src/textoCopia.txt");
        FileWriter fw = new FileWriter(copia);

        String linea = null;
        while ((linea = br.readLine()) != null){
            System.out.println(linea.replace("a", "@"));
            fw.write(linea + "\n");
        }
        fw.close();

    }
}
