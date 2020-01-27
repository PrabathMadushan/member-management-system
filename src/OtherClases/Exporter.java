package OtherClases;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Exporter {

    private final ArrayList<String> idList;
    //private final JProgressBar prograss;

    public Exporter(ArrayList idList) throws Exception {
        this.idList = idList;

    }

    public void Export(final File file) throws Exception {
        file.createNewFile();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();
        Element root = document.createElement("root");
        document.appendChild(root);
        for (String SA : idList) {
            Element member = document.createElement("member");
            root.appendChild(member);
            e("SMJK", member, document, SA);
            e("AASS", member, document, SA);
            e("DWW", member, document, SA);
            e("DWWNS", member, document, SA);
           // e("DWWW", member, document, SA);
            e("MWLS", member, document, SA);
           // e("MWW", member, document, SA);
            e("SD", member, document, SA);
            e("SDWWNH", member, document, SA);
            e("SJWP", member, document, SA);
            e("SL", member, document, SA);
            e("SLS", member, document, SA);
            e("SS", member, document, SA);
            e("SSWE", member, document, SA);
            e("ST", member, document, SA);
            e("SWWNH", member, document, SA);
            e("TRPWLS", member, document, SA);
            e("WW", member, document, SA);
            e("WWNS", member, document, SA);
            //e("WWW", member, document, SA);
            Element E = backup_table2(SA, document);
            member.appendChild(E);

        }

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer tfmer = tf.newTransformer();
        DOMSource domS = new DOMSource(document);
        StreamResult streamResult = new StreamResult(file);
        tfmer.transform(domS, streamResult);
    }

    private void e(String table, Element root, Document d, String SA) throws Exception {
        Element ele = backup_table(table, SA, d);
        root.appendChild(ele);
    }

    private Element backup_table(String table_name, String SA, Document document) throws Exception {
        Element table = document.createElement(table_name.toLowerCase());
        ResultSet rs_fileds = SQLConnection.SqlConnection.getData("SELECT * FROM " + table_name + " WHERE SA='" + SA + "'");
        ResultSetMetaData metaData = rs_fileds.getMetaData();
        boolean b = false;

        while (rs_fileds.next()) {
            b = true;
            Element value = document.createElement("value");
            table.appendChild(value);
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String name_of_field = metaData.getColumnName(i);
                Element field = document.createElement(name_of_field.toLowerCase());
                value.appendChild(field);
                field.appendChild(document.createTextNode(rs_fileds.getString(name_of_field) == null ? "null" : rs_fileds.getString(name_of_field)));
            }
        }

        if (!b) {
            table.appendChild(document.createTextNode("null"));
        }

        return table;
    }

    private Element backup_table2(String SA, Document document) throws Exception {
        Element table = document.createElement("SAU".toLowerCase());

        ResultSet rs_fields = SQLConnection.SqlConnection.getData("SELECT AUN,UA FROM SAU WHERE SA='" + SA + "'");
        while (rs_fields.next()) {
            Element value1 = document.createElement("value");
            table.appendChild(value1);
            String AUN = rs_fields.getString("AUN");
            Element f_aun = document.createElement("AUN".toLowerCase());
            value1.appendChild(f_aun);
            f_aun.appendChild(document.createTextNode(AUN == null ? "null" : AUN));

            Element mitapera = document.createElement("AULDMITEPERA".toLowerCase());
            value1.appendChild(mitapera);
            String UA = rs_fields.getString("UA");
            ResultSet rs1 = SQLConnection.SqlConnection.getData("SELECT ULD FROM AULDMITAPERA WHERE UA='" + UA + "'");
            while (rs1.next()) {
                Element value2 = document.createElement("value");
                mitapera.appendChild(value2);
                String ULD = rs1.getString("ULD");
                value2.appendChild(document.createTextNode(ULD == null ? "null" : ULD));
            }
            Element mitapasu = document.createElement("AULDMITEPASU".toLowerCase());
            value1.appendChild(mitapasu);
            ResultSet rs2 = SQLConnection.SqlConnection.getData("SELECT ULD FROM AULDMITAPASU WHERE UA='" + UA + "'");
            while (rs2.next()) {
                Element value2 = document.createElement("value");
                mitapasu.appendChild(value2);
                String ULD = rs2.getString("ULD");
                value2.appendChild(document.createTextNode(ULD == null ? "null" : ULD));
            }
        }
        return table;
    }

  
}
