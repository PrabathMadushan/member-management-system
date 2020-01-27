package ConfigFile;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.prabath.encripter.Encrypter;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class ConfigHelper {

    private static final String KEY = "12!assDSD%^XFmr";
    private static Document document;
    private static NodeList nl;

    private static Encrypter encrypter;

    private enum ElementNames {

        USER_NAME("username"), PASSWORD("password"), PORT("port"), DATABASE_NAME("databasename");

        private final String nameAsString;

        private ElementNames(String name) {
            this.nameAsString = name;
        }

        @Override
        public String toString() {
            return this.nameAsString;
        }
    }

    private ConfigHelper() {

    }

    private static String getElementValue(String elementName) {
        Element e = (Element) nl.item(0);
        return e.getElementsByTagName(elementName).item(0).getTextContent();
    }

    private static void setElementValue(String elementName, String elementValue) {
        Element e = (Element) nl.item(0);
        e.getElementsByTagName(elementName).item(0).setTextContent(elementValue);
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer tfmer = tf.newTransformer();
            DOMSource domS = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new ConfigHelper().getXMLFile());
            tfmer.transform(domS, streamResult);
        } catch (TransformerException ex) {
            ex.printStackTrace();
        }

    }

    static {
        encrypter = new Encrypter(KEY);
        try {
            File xmlFile = new ConfigHelper().getXMLFile();
            DocumentBuilderFactory dbp = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbp.newDocumentBuilder();
            document = db.parse(xmlFile);
            nl = document.getElementsByTagName("root");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private File getXMLFile() {
        return new createXMLFile().crateXML();
    }

    public static String getUser_name() {
        return encrypter.decrypt(getElementValue(ElementNames.USER_NAME.toString()));
    }

    public static void setUser_name(String user_name) {
        setElementValue(ElementNames.USER_NAME.toString(), encrypter.encrypt(user_name));

    }

    public static String getPassword() {
        return encrypter.decrypt(getElementValue(ElementNames.PASSWORD.toString()));
    }

    public static void setPassword(String password) {
        setElementValue(ElementNames.PASSWORD.toString(), encrypter.encrypt(password));
    }

    public static String getPort() {
        return getElementValue(ElementNames.PORT.toString());

    }

    public static void setPort(String port) {
        setElementValue(ElementNames.PORT.toString(), port);
    }

    public static String getDatabase_name() {
        return encrypter.decrypt(getElementValue(ElementNames.DATABASE_NAME.toString()));

    }

    public static void setDatabase_name(String database_name) {
        setElementValue(ElementNames.DATABASE_NAME.toString(), encrypter.encrypt(database_name));

    }

}
