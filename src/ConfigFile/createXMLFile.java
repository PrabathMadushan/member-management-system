package ConfigFile;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class createXMLFile {

  

    public File crateXML() {

        File f = new File("C:\\ProgramData\\SLPolice");
        if (!f.exists()) {
            f.mkdir();
        }

        File realFile = new File("C:\\ProgramData\\SLPolice\\Configer.xml");
        if (!realFile.exists()) {
            try {
                f.createNewFile();
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document document = db.newDocument();
                Element root = document.createElement("root");
                document.appendChild(root);

                Element username = createElement("username", "", document);
                root.appendChild(username);
                Element password = createElement("password", "", document);
                root.appendChild(password);
                Element port = createElement("port", "", document);
                root.appendChild(port);
                Element databasename = createElement("databasename", "", document);
                root.appendChild(databasename);

                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer tfmer = tf.newTransformer();
                DOMSource domS = new DOMSource(document);
                StreamResult streamResult = new StreamResult(realFile);
                tfmer.transform(domS, streamResult);
            } catch (IOException | ParserConfigurationException | DOMException | TransformerException e) {
              
            }
        }

        return realFile;
    }

    private Element createElement(String nameS, String text, Document document) {
        Element name = document.createElement(nameS);
        name.appendChild(document.createTextNode(text));
        return name;
    }

}
