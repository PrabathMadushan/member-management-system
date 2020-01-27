package OtherClases;

import SaveData.SaveMWLSamajikayin;
import SaveData.SaveSDaruwan;
import SaveData.SaveSLSahana;
import SaveData.SaveSSwisheshaEkaka;
import SaveData.SaveWidyaPariksahanaya;
import java.io.File;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Importer {

    private final Document document;

    public Importer(File file) throws Exception {
        File xmlFile = file;
        DocumentBuilderFactory dbp = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbp.newDocumentBuilder();
        document = db.parse(xmlFile);

    }

    public void saveMember(String... member_list) throws Exception {
        ArrayList<QuaryStore> list = get_sql_queries_from_xml_file();
        list.stream().collect(Collectors.partitioningBy((QuaryStore q) -> can_save(q.getSA(), member_list))).get(true)
                .forEach(qs -> {
                    qs.getQuaries().forEach((q) -> {
                        try {
                            //System.out.println(q);
                            SQLConnection.SqlConnection.updateDB(q);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });
                });
    }

    private class QuaryStore {

        private String SA;
        private ArrayList<String> queries;

        public QuaryStore(String SA, ArrayList<String> queries) {
            this.SA = SA;
            this.queries = queries;
        }

        public ArrayList<String> getQuaries() {
            return queries;
        }

        public void setQuaries(ArrayList<String> queries) {
            this.queries = queries;
        }

        public String getSA() {
            return SA;
        }

        public void setSA(String SA) {
            this.SA = SA;
        }

    }

    public boolean validateXML() {
        try {
            get_sql_queries_from_xml_file();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private ArrayList<QuaryStore> get_sql_queries_from_xml_file() throws Exception {
        int PK_AUA = Integer.parseInt(PKGenaretor.getNextPK("SAU", "UA"));
        NodeList nl = document.getElementsByTagName("member");
        ArrayList<QuaryStore> queryList = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {

            Element e_tables = (Element) nl.item(i);
            //save SMJK
            Element smjk = (Element) e_tables.getElementsByTagName("smjk").item(0);
            String SA = getElementValue("SA", smjk);

            ArrayList<String> queries = new ArrayList<>();
            String JHA = getElementValue("JHA", smjk);
            String SN = getElementValue("SN", smjk);
            String PBD = getElementValue("PBD", smjk);
            String UD = getElementValue("UD", smjk);
            String AWSKS = getElementValue("AWSKS", smjk);
            String AWSKK = getElementValue("AWSKK", smjk);
            String WL = getElementValue("WL", smjk);
            String WLD = getElementValue("WLD", smjk);
            String NWUD = getElementValue("NWUD", smjk);
            String JDA = getElementValue("JDA", smjk);
            String SDA = getElementValue("SDA", smjk);
            String DKKD = getElementValue("DKKD", smjk);
            String WD = getElementValue("WD", smjk);
            String WAWATBD = getElementValue("WAWATBD", smjk);
            String NUS = getElementValue("NSUS", smjk);
            String SLD = getElementValue("SLD", smjk);
            String smjk_String = new SaveData.SaveSamajikaya(SA, JHA, SN, PBD, UD, AWSKS, AWSKK, WL, WLD, NWUD, JDA, SDA, DKKD, WD, WAWATBD, NUS, SLD).getQuaryString();
            queries.add(smjk_String);

            // TRPWLS-ත්‍රස්තවාදී ප්‍රහාර වලට ලක් වූ සමාජිකයින්
            Element trpwls = (Element) e_tables.getElementsByTagName("trpwls").item(0);
            String ISHP = getElementValue("ISHP", trpwls);
            String OWAD = getElementValue("OWAD", trpwls);
            String TRKA = getElementValue("TRK", trpwls);
            String LUD = getElementValue("LUD", trpwls);
            String LUK = getElementValue("LUK", trpwls);
            String ASB = getElementValue("ASB", trpwls);
            String LUS = getElementValue("LUS", trpwls);
            queries.add(new SaveData.SaveTrasthaPrahara(SA, ISHP, OWAD, TRKA, LUD, LUK, ASB, LUS).getQuaryString());

            //ST- සමාජිකයාගේ තනතුර. 
            Element st = (Element) e_tables.getElementsByTagName("st").item(0);
            String TNTR1 = getElementValue("TNTR1", st);
           // String NA = getElementValue("NA", st);
            String UA = getElementValue("UA", st);
            String RHA = getElementValue("RHA", st);
            String NSA = getElementValue("NA", st);
            String TNTR2 = getElementValue("TNTR2", st);
            queries.add(new SaveData.SaveSThanathura(SA, TNTR1, TNTR2,UA, RHA, NSA).getQuaryString());

            //AASS අතුරු අබාද
            Element aass = (Element) e_tables.getElementsByTagName("aass").item(0);
            NodeList list_aass = aass.getElementsByTagName("value");
            for (int j = 0; j < list_aass.getLength(); j++) {

                Element value = (Element) list_aass.item(j);
                String ABD = getElementValue("abd", value);
                queries.add(new SaveData.SaveAthuruAbada(SA, ABD).getQuaryString());
            }

            //Data for SL-ස්ථිර ලිපිනය
            Element sl = (Element) e_tables.getElementsByTagName("sl").item(0);
            String SSL = getElementValue("SL", sl);
            String GNW = getElementValue("GNW", sl);
            String PW = getElementValue("PW", sl);
            String PLK = getElementValue("PLK", sl);
            String DTRK = getElementValue("DTRK", sl);
            queries.add(new SaveData.SaveSthiraLipinaya(SA, SSL, GNW, PW, PLK, DTRK).getQuaryString());

            //Data for WW-විශ්‍රාම වැටුප
            Element ww = (Element) e_tables.getElementsByTagName("ww").item(0);
            NodeList list_ww = ww.getElementsByTagName("value");
            for (int j = 0; j < list_ww.getLength(); j++) {
                Element value = (Element) list_ww.item(j);
                String wwPLK = getElementValue("PLK", value);
                String wwWWA = getElementValue("WWA", value);
                String wwWTKM = getElementValue("WTKM", value);
                queries.add(new SaveData.SaveWisramaWatup(SA, wwPLK, wwWWA,wwWTKM).getQuaryString());
            }

           

            //විශ්‍රාම වැටුප් නොලැබෙන සාමාජිකයෙකි
            Element wwns = (Element) e_tables.getElementsByTagName("wwns").item(0);
            NodeList list_wwns = wwns.getElementsByTagName("value");
            for (int j = 0; j < list_wwns.getLength(); j++) {
                queries.add(new SaveData.SaveWWNSamajikayin(SA).getQuaryString());
                //SWWNH-සමාජිකයින්ට විශ්‍රාම වැටුප් නොලැබීමට හේතු
                Element swwnh = (Element) e_tables.getElementsByTagName("swwnh").item(0);
                NodeList list_swwnh = swwnh.getElementsByTagName("value");
                for (int h = 0; h < list_wwns.getLength(); h++) {
                    Element value = (Element) list_swwnh.item(h);
                    String HTW = getElementValue("HTW", value);
                    queries.add(new SaveData.SaveWWNHethu(SA, HTW).getQuaryString());

                }

            }
            //Data for DWW-දුබලතා විශ්‍රාම වැටුප
            Element dww = (Element) e_tables.getElementsByTagName("dww").item(0);
            NodeList list_dww = dww.getElementsByTagName("value");
            for (int j = 0; j < list_dww.getLength(); j++) {
                Element value = (Element) list_dww.item(j);
                String wwPLK = getElementValue("PLK", value);
                String wwWWA = getElementValue("WWA", value);
                String wwWTKM = getElementValue("WTKM", value);
                queries.add(new SaveData.SaveDWishramaWatup(SA, wwPLK, wwWWA,wwWTKM).getQuaryString());
            }

         

            //දුබලතා විශ්‍රාම වැටුප් නොලැබෙන සාමාජිකයෙකි
            Element dwwns = (Element) e_tables.getElementsByTagName("dwwns").item(0);
            NodeList list_dwwns = dwwns.getElementsByTagName("value");
            for (int j = 0; j < list_dwwns.getLength(); j++) {
                queries.add(new SaveData.SaveDWWNSamajikayin(SA).getQuaryString());
                //SDWWNH-සමාජිකයින්ට දුබලතා විශ්‍රාම වැටුප් නොලැබීමට හේතු
                Element swwnh = (Element) e_tables.getElementsByTagName("sdwwnh").item(0);
                NodeList list_swwnh = swwnh.getElementsByTagName("value");
                for (int h = 0; h < list_dwwns.getLength(); h++) {
                    Element value = (Element) list_swwnh.item(h);
                    String HTW = getElementValue("HTW", value);
                    queries.add(new SaveData.SaveDWWNHethu(SA, HTW).getQuaryString());

                }

            }

            //SS-ස්මජිකයාගේ සහකරු/සහකාරිය
            Element ss = (Element) e_tables.getElementsByTagName("ss").item(0);
            NodeList list_ss = ss.getElementsByTagName("value");
            for (int j = 0; j < list_ss.getLength(); j++) {
                Element value = (Element) list_ss.item(j);
                String SRL = getElementValue("SRL", value);
                String SSDA = getElementValue("SDA", value);
                String SR = getElementValue("SR", value);
                String SSN = getElementValue("SSN", value);
                queries.add(new SaveData.SaveSahakaru(SA, SRL, SSDA, SR, SSN).getQuaryString());

            }

            //ආබාදිත උපකරණ
            Element sau = (Element) e_tables.getElementsByTagName("sau").item(0);
            NodeList list_sau = sau.getElementsByTagName("aun");
            System.out.println(list_sau.getLength());
            for (int j = 0; j < list_sau.getLength(); j++) {
                String AUN = sau.getElementsByTagName("aun").item(j).getTextContent();
                String AUA = Integer.toString(PK_AUA++);
                queries.add(new SaveData.SaveAbadithaUpakarana(SA, AUA, AUN).getQuaryString());

                Element mpera = (Element) sau.getElementsByTagName("auldmitepera").item(j);
                NodeList value1 = mpera.getElementsByTagName("value");
                for (int k = 0; k < value1.getLength(); k++) {
                    String ULD = mpera.getElementsByTagName("value").item(k).getTextContent();
                    queries.add(new SaveData.SaveMitaPeraAbadithaULDinayan(AUA, ULD).getQuaryString());

                }

                Element mpasu = (Element) sau.getElementsByTagName("auldmitepasu").item(j);
                NodeList value2 = mpasu.getElementsByTagName("value");
                for (int k = 0; k < value2.getLength(); k++) {
                    String ULD = mpasu.getElementsByTagName("value").item(k).getTextContent();
                    queries.add(new SaveData.SaveMitaPasuAbadithaULDinayan(AUA, ULD).getQuaryString());

                }
            }
            queryList.add(new QuaryStore(SA, queries));

            //SLS-සාමාජිකයාට ලැබෙන සහන
            Element sls = (Element) e_tables.getElementsByTagName("sls").item(0);
            NodeList list_sls = sls.getElementsByTagName("value");
            for (int j = 0; j < list_sls.getLength(); j++) {
                Element value = (Element) list_sls.item(j);
                String SHA = getElementValue("SHN", value);
                String SLS = getElementValue("LS", value);
                String SAWLD = getElementValue("SAWLD", value);
                queries.add(new SaveSLSahana(SA, SHA, SLS, SAWLD).getQuaryString());
            }

            //MWLS-මාසික වැටුප් ලබන සාමාජිකයින්.-MS
            Element mwls = (Element) e_tables.getElementsByTagName("mwls").item(0);
            NodeList list_mwls = mwls.getElementsByTagName("value");
            for (int j = 0; j < list_mwls.getLength(); j++) {
                Element value = (Element) list_mwls.item(j);
                String LK = getElementValue("LK", value);
                String PS = getElementValue("PS", value);
                String WWT = getElementValue("WWT", value);
                String DWT = getElementValue("DWT", value);
                queries.add(new SaveMWLSamajikayin(SA, LK, PS,WWT,DWT).getQuaryString());
            }
          

            //SD-ස්මජිකයාගේ දරුවන්
            Element sd = (Element) e_tables.getElementsByTagName("sd").item(0);
            NodeList list_sd = sd.getElementsByTagName("value");
            for (int j = 0; j < list_sd.getLength(); j++) {
                Element value = (Element) list_sd.item(j);
                String dUD = getElementValue("UD", value);
                String dNAMA = getElementValue("NAME", value);
                String DT = getElementValue("DT", value);
                String ABTD = getElementValue("ABTD", value);
                queries.add(new SaveSDaruwan(SA, dUD, dNAMA, DT, ABTD).getQuaryString());
            }

            //සමාජිකයා සේවය කළ විශේෂ ඒකක.
            Element sswe = (Element) e_tables.getElementsByTagName("sswe").item(0);
            NodeList list_sswe = sswe.getElementsByTagName("value");
            for (int j = 0; j < list_sswe.getLength(); j++) {
                Element value = (Element) list_sswe.item(j);
                String WEA = getElementValue("SSWE", value);
                String BD = getElementValue("DB", value);
                String KAAA = getElementValue("KAAA", value);
                queries.add(new SaveSSwisheshaEkaka(SA, WEA, BD, KAAA).getQuaryString());
            }
            //සාමාජිකයාගේ වෛද්‍ය පරීක්ෂණය.
            Element sjwp = (Element) e_tables.getElementsByTagName("sjwp").item(0);
            NodeList list_sjwp = sjwp.getElementsByTagName("value");
            for (int j = 0; j < list_sjwp.getLength(); j++) {
                Element value = (Element) list_sjwp.item(j);
                String SKS = getElementValue("SKS", value);
                String SKD = getElementValue("SKD", value);
                String SKK = getElementValue("SKK", value);
                queries.add(new SaveWidyaPariksahanaya(SA, SKS, SKD, SKK).getQuaryString());
            }

        }
        return queryList;
    }

    public ArrayList<Details> getMembersDetails() {
        ArrayList<Details> members_d = new ArrayList<>();
        NodeList nl = document.getElementsByTagName("member");
        for (int i = 0; i < nl.getLength(); i++) {
            Element e_tables = (Element) nl.item(i);
            Element smjk = (Element) e_tables.getElementsByTagName("smjk").item(0);
            String SA = getElementValue("SA", smjk);
            String JHA = getElementValue("JHA", smjk);
            String SN = getElementValue("SN", smjk);
            members_d.add(new Details(SA, JHA, SN));
        }

        return members_d;
    }

    public class Details {

        private final String sa;
        private final String jha;
        private final String sn;

        public Details(String sa, String jha, String sn) {
            this.sa = sa;
            this.jha = jha;
            this.sn = sn;
        }

        public String getSa() {
            return sa;
        }

        public String getJha() {
            return jha;
        }

        public String getSn() {
            return sn;
        }

    }

    private boolean can_save(String SA, String[] list) {
        boolean can = false;
        for (String l : list) {
            if (!l.equals(SA)) {
                can = false;
            } else {
                can = true;
                break;
            }
        }
        return can;
    }

    private String getElementValue(String elementName, Element perantElement) {
        System.out.println(elementName + " :" + perantElement.getElementsByTagName(elementName.toLowerCase()).item(0).getTextContent());
        return perantElement.getElementsByTagName(elementName.toLowerCase()).item(0).getTextContent();
    }

    

}
