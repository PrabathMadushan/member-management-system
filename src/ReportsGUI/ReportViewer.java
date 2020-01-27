package ReportsGUI;

import OtherClases.Funtional;
import OtherClases.MyJasperViewer;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JTable;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReportViewer {

    static ReportViewer rw = new ReportViewer();

    private InputStream getJRXMLFile(String report_name) {
        return this.getClass().getResourceAsStream("/JasperReportJRXML/" + report_name + ".jrxml");
    }

    public static void view_Report(String report_name, String title, Connection con, Map<String, Object> paraList) throws Exception {
        if (con == null) {
            InputStream rs = rw.getJRXMLFile(report_name);
            JasperDesign jd = JRXmlLoader.load(rs);

            JasperReport jReport = JasperCompileManager.compileReport(jd);

            JasperPrint fillReport = JasperFillManager.fillReport(jReport, paraList);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
            MyJasperViewer.viewReport(fillReport, title);
        } else {
            InputStream rs = rw.getJRXMLFile(report_name);
            JasperDesign jd = JRXmlLoader.load(rs);
            JasperReport jReport = JasperCompileManager.compileReport(jd);
            JasperPrint fillReport = JasperFillManager.fillReport(jReport, paraList, con);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
            MyJasperViewer.viewReport(fillReport, title);
        }
    }

    private static void p_view_Report(String report_name, String title, List<Object> beanList, Map<String, Object> paraList) throws Exception {

        InputStream rs = rw.getJRXMLFile(report_name);
        JasperDesign jd = JRXmlLoader.load(rs);
        JasperReport jReport = JasperCompileManager.compileReport(jd);
        JasperPrint fillReport = JasperFillManager.fillReport(jReport, paraList, new JRBeanCollectionDataSource(beanList));
        JasperViewer.setDefaultLookAndFeelDecorated(true);
        MyJasperViewer.viewReport(fillReport, title);

    }

    public static void view_Report(JTable tabel, String discription) throws Exception {

        if (tabel.getRowCount() >= 0) {
            List list = new ArrayList<>();
            int AA = 0;
            for (int i = 0; i < tabel.getRowCount(); i++) {

                AA++;
                String SA = tabel.getValueAt(i, 0).toString();
                String sql = "SELECT SN,JHA,JDA,SL,NSUS,TNTR2 FROM SMJK INNER JOIN SL ON SMJK.SA=SL.SA "
                        + "INNER JOIN ST ON SMJK.SA=ST.SA "
                        + "WHERE SMJK.SA= '" + SA + "'";
                ResultSet rs1 = SQLConnection.SqlConnection.getData(sql);
                if (rs1.first()) {
                    //AA
                    //SA

                    String JHA = rs1.getString("JHA");
                    String SN = rs1.getString("SN");
                    String LPN = rs1.getString("SL");
                    String DKA = rs1.getString("JDA");

                    //----------
                    String NSUS = rs1.getString("NSUS");
                    //----------
                    String NA = Funtional.getStringValue(() -> {
                        if (NSUS.equals("ks;H fiajd")) {
                            ResultSet rs = SQLConnection.SqlConnection.getData("SELECT NA FROM ST WHERE SA='" + SA + "'");
                            rs.first();
                            return rs.getString("NA");
                        } else {
                            ResultSet rs = SQLConnection.SqlConnection.getData("SELECT UA FROM ST WHERE SA='" + SA + "'");
                            rs.first();
                            return rs.getString("UA");
                        }
                    });
                    final String I_TNTR = rs1.getString("TNTR2");
                    System.out.println(I_TNTR);
                    String TNTR = Funtional.getStringValue(() -> {
                        ResultSet rs = SQLConnection.SqlConnection.getData("SELECT TRNAME FROM THANATHURU WHERE TRID='" + I_TNTR + "'");
                        rs.first();
                        return rs.getString("TRNAME");
                    });

                    list.add(
                            new defualtReportBean(
                                    Integer.toString(AA), SA, JHA, SN, LPN, DKA, NA, TNTR));

                }
            }

            Map<String, Object> paraList = new HashMap<>();
            paraList.put("DISCRIPTION", discription);

            p_view_Report("DefaultReport_for_All", "වාර්තාව", list, paraList);

        } else {

        }
    }

    public static void view_Report2(JTable tabel, String discription) throws Exception {

        if (tabel.getRowCount() >= 0) {
            List list = new ArrayList<>();
            int AA = 0;
            for (int i = 0; i < tabel.getRowCount(); i++) {

                AA++;
                String SA = tabel.getValueAt(i, 1).toString();
                String sql = "SELECT SN,JHA,JDA,SL,NSUS,TNTR2 FROM SMJK INNER JOIN SL ON SMJK.SA=SL.SA "
                        + "INNER JOIN ST ON SMJK.SA=ST.SA "
                        + "WHERE SMJK.SA= '" + SA + "'";
                ResultSet rs1 = SQLConnection.SqlConnection.getData(sql);
                if (rs1.first()) {
                    //AA
                    //SA

                    String JHA = rs1.getString("JHA");
                    String SN = rs1.getString("SN");
                    String LPN = rs1.getString("SL");
                    String DKA = rs1.getString("JDA");

                    //----------
                    String NSUS = rs1.getString("NSUS");
                    //----------
                    String NA = Funtional.getStringValue(() -> {
                        if (NSUS.equals("ks;H fiajd")) {
                            ResultSet rs = SQLConnection.SqlConnection.getData("SELECT NA FROM ST WHERE SA='" + SA + "'");
                            rs.first();
                            return rs.getString("NA");
                        } else {
                            ResultSet rs = SQLConnection.SqlConnection.getData("SELECT UA FROM ST WHERE SA='" + SA + "'");
                            rs.first();
                            return rs.getString("UA");
                        }
                    });
                    final String I_TNTR = rs1.getString("TNTR2");
                    System.out.println(I_TNTR);
                    String TNTR = Funtional.getStringValue(() -> {
                        ResultSet rs = SQLConnection.SqlConnection.getData("SELECT TRNAME FROM THANATHURU WHERE TRID='" + I_TNTR + "'");
                        rs.first();
                        return rs.getString("TRNAME");
                    });

                    list.add(
                            new defualtReportBean(
                                    Integer.toString(AA), SA, JHA, SN, LPN, DKA, NA, TNTR));
                    System.out.println(AA + " " + SA + " " + JHA + " " + SN + " " + LPN + " " + DKA + " " + NA + " " + TNTR);

                }
            }

            Map<String, Object> paraList = new HashMap<>();
            paraList.put("DISCRIPTION", discription);

            p_view_Report("DefaultReport_for_All", "වාර්තාව", list, paraList);

        } else {

        }
    }

    public static void view_Report(List<Object> beanList) throws Exception {
        p_view_Report("Watup_Summery", "වාර්තාව", beanList, null);
    }

    public static void view_Report(String reportName, List<Object> beanList, String report_title) throws Exception {

        Map<String, Object> paraList = new HashMap<>();
        paraList.put("RD", report_title);
        p_view_Report(reportName, "වාර්තාව", beanList, paraList);
    }

    public static void view_Report(String reportName, List<Object> beanList, String report_title, Map<String, Object> paraList) throws Exception {
        p_view_Report(reportName, "වාර්තාව", beanList, paraList);
    }

}
