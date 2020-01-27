package ReportsGUI;

import OtherClases.AULDinayan;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Report14_Sahana extends javax.swing.JFrame {

     public final String BStringTrue = "Tõ";//ඔව්
    public final String BStringFalse = "ke;";//නැත

    private String REPORT_NAME;

    public Report14_Sahana() {
        this.setResizable(false);
        initComponents();
        setNICColumnFont();
    }

     private void setNICColumnFont() {
        tblSeachedData.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setFont(new java.awt.Font("Iskoola Pota", 0, 14));
                return this;
            }
        });
    }
    
    private void showAllWisramaWatup() {

        DefaultTableModel ww = (DefaultTableModel) tblWWNHethu.getModel();
        DefaultTableModel dww = (DefaultTableModel) tblDWWNHethu.getModel();
        ww.setRowCount(0);
        dww.setRowCount(0);

        if (tblSeachedData.getSelectedRow() != -1) {

            try {
                //WW
                String SA = tblSeachedData.getValueAt(tblSeachedData.getSelectedRow(), 0).toString();
                ResultSet rs1 = SQLConnection.SqlConnection.getData("SELECT * FROM WW WHERE SA='" + SA + "'");
                if (rs1.first()) {
                    lblWWLabannedaha.setText("úY%du jegqma ,efí");
                    String PLK = rs1.getString("PLK");
                    String WWA = rs1.getString("WWA");
                    String WTKM = rs1.getString("WTKM");
                    lblWWW.setText(WTKM);
                    txtWWAnkaya.setText(WWA);
                    wwPradheshilaLK.setText(PLK);
                    titleWWNHethu.setVisible(false);
                } else {
                    titleWWNHethu.setVisible(true);
                    lblWWLabannedaha.setText("úY%du jegqma fkd,efí");
                    lblWWW.setText("-");
                    txtWWAnkaya.setText("-");
                    wwPradheshilaLK.setText("-");

                    DefaultTableModel model_wh = (DefaultTableModel) tblWWNHethu.getModel();
                    model_wh.setRowCount(0);
                    ResultSet rs3 = SQLConnection.SqlConnection.getData("SELECT HTW FROM SWWNH WHERE SA='" + SA + "'");
                    while (rs3.next()) {
                        String HTW = rs3.getString(1);
                        Vector row_data = new Vector();
                        row_data.add(HTW);
                        model_wh.addRow(row_data);
                    }
                }
                //dww
                ResultSet rs4 = SQLConnection.SqlConnection.getData("SELECT * FROM DWW WHERE SA='" + SA + "'");
                if (rs4.first()) {
                    lblDWWLabannedaha.setText("ÿn,;d úY%du jegqma ,efí");
                    String PLK = rs4.getString("PLK");
                    String WWA = rs4.getString("WWA");
                    String WTKM = rs4.getString("WTKM");
                    lblDWWW.setText(WTKM);
                    txtWWAnkaya.setText(WWA);
                    wwPradheshilaLK.setText(PLK);
                    titleDWWNHethu.setVisible(false);
                } else {
                    titleDWWNHethu.setVisible(true);
                    lblDWWLabannedaha.setText("ÿn,;d úY%du jegqma fkd,efí");
                    lblDWWW.setText("-");

                    DefaultTableModel model_wh = (DefaultTableModel) tblDWWNHethu.getModel();
                    model_wh.setRowCount(0);
                    ResultSet rs3 = SQLConnection.SqlConnection.getData("SELECT HTW FROM SDWWNH WHERE SA='" + SA + "'");
                    while (rs3.next()) {
                        String HTW = rs3.getString(1);
                        Vector row_data = new Vector();
                        row_data.add(HTW);
                        model_wh.addRow(row_data);
                    }
                }

            } catch (Exception e) {
               new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
            }

        }

    }

    private void showAllMasikaWatupData() {
        if (tblSeachedData.getSelectedRow() != -1) {
            String SA = tblSeachedData.getValueAt(tblSeachedData.getSelectedRow(), 0).toString();
            try {
                String sql1 = "SELECT * FROM MWLS WHERE SA='" + SA + "'";
                ResultSet rs1 = SQLConnection.SqlConnection.getData(sql1);
                if (rs1.first()) {
                    //CODE HIER
                    titleMWLabenam.setVisible(true);
                    lblMasikaWatupText.setText("udisl jegqma yd oSukd ,efí");

                    String LK = rs1.getString("LK");
                    String PS = rs1.getString("PS");
                    String WWT = rs1.getString("WWT");
                    String DWT = rs1.getString("DWT");

                    //String TL=rs1.getString("TL");-->Not Need
                    txtWlabanaKottasaya.setText(LK);
                    txtWlabanaPolisWasama.setText(PS);
                    txtMulikaWatupeWatinakama.setText(WWT);
                    txtDimanaWalaWatinakama.setText(DWT);
                    if (Integer.parseInt(WWT) <= 0 || Integer.parseInt(DWT) <= 0) {
                        lblMWWInfor.setText("වටිනාකම ඇතුලත්කර නොමැත");
                    } else {
                        lblMWWInfor.setText("");
                    }

                } else {
                    lblMasikaWatupText.setText("udisl jegqma yd oSukd fkd,efí");
                    titleMWLabenam.setVisible(false);
                }
            } catch (Exception e) {
               new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
            }
        }

        try {
            double ww = Integer.parseInt(txtMulikaWatupeWatinakama.getText());
            double dw = Integer.parseInt(txtDimanaWalaWatinakama.getText());
            double sum = ww + dw;
            lblMuluWatupeWatinakama.setText(Double.toString(sum));
        } catch (Exception e) {
            //Don't think
        }
    }

    private void showAllSahanaData() {
        if (tblSeachedData.getSelectedRow() != -1) {
            try {
                DefaultTableModel modelSahana = (DefaultTableModel) tblSahana.getModel();
                String SA = tblSeachedData.getValueAt(tblSeachedData.getSelectedRow(), 0).toString();
                String sql1 = "SELECT * FROM SLS WHERE SA='" + SA + "'";
                ResultSet rs1 = SQLConnection.SqlConnection.getData(sql1);

                if (rs1.first()) {
                    setEnableSahana(true);
                    rs1.beforeFirst();
                    while (rs1.next()) {
                        Vector rowData = new Vector();
                        rowData.add(rs1.getString("SHN"));
                        rowData.add(rs1.getString("LS"));
                        rowData.add(rs1.getString("SAWLD"));
                        modelSahana.addRow(rowData);
                    }
                } else {
                    setEnableSahana(false);
                }
            } catch (Exception e) {
               new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
            }
        }
    }

    private void setEnableSahana(boolean b) {

        final DefaultTableModel modelSahana = (DefaultTableModel) tblSahana.getModel();
        modelSahana.setRowCount(0);
        if (!b) {
            TitledBorder tb = (TitledBorder) titleSahanaLabinama.getBorder();
            titleSahanaLabinama.setBorder(BorderFactory.createTitledBorder(null, tb.getTitle(),
                    TitledBorder.DEFAULT_JUSTIFICATION,
                    TitledBorder.DEFAULT_POSITION,
                    new java.awt.Font("Iskoola Pota", 0, 14)));
        }
    }

    private void showAllAbadithaUpakaranaData() {
        if (tblSeachedData.getSelectedRow() != -1) {
            final DefaultTableModel modelAU = (DefaultTableModel) tblAU.getModel();
            final DefaultTableModel modelAULDMitaPera = (DefaultTableModel) tblAULDinayanMitaPasu.getModel();
            final DefaultTableModel modelAULDMitaPasu = (DefaultTableModel) tblAULDinayanMitaPera.getModel();

            final ArrayList<String> AUList = new ArrayList<>();
            try {
                String SA = tblSeachedData.getValueAt(tblSeachedData.getSelectedRow(), 0).toString();
                auldMETAPERA.removeAll(auldMETAPERA);
                auldMETAPASU.removeAll(auldMETAPASU);
                String sql1 = " SELECT * FROM SAU WHERE SA='" + SA + "'";
                ResultSet rs1 = SQLConnection.SqlConnection.getData(sql1);
                if (rs1.first()) {

                    rs1.beforeFirst();
                    int index = 0;
                    while (rs1.next()) {
                        String UA = rs1.getString("UA");
                        String AUN = rs1.getString("AUN");
                        AUList.add(AUN);
                        String sql2 = "SELECT ULD FROM AULDMITAPERA WHERE UA='" + UA + "'";
                        ResultSet rs2 = SQLConnection.SqlConnection.getData(sql2);
                        while (rs2.next()) {
                            String ULD = rs2.getString("ULD");
                            auldMETAPERA.add(new AULDinayan(ULD, index));
                        }
                        String sql3 = "SELECT ULD FROM AULDMITAPASU WHERE UA='" + UA + "'";
                        ResultSet rs3 = SQLConnection.SqlConnection.getData(sql3);
                        while (rs3.next()) {
                            String ULD = rs3.getString("ULD");
                            auldMETAPASU.add(new AULDinayan(ULD, index));
                        }
                        index++;
                    }
                    modelAU.setRowCount(0);
                    modelAULDMitaPera.setRowCount(0);
                    modelAULDMitaPasu.setRowCount(0);
                    AUList.forEach((u) -> {
                        Vector rowData = new Vector();
                        rowData.add(u);
                        modelAU.addRow(rowData);
                    });

                } else {

                }
            } catch (Exception e) {
               new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
            }

        }
    }

    private void showAllThanathuraData() {
        if (tblSeachedData.getSelectedRow() != -1) {
            try {
                String SA = tblSeachedData.getValueAt(tblSeachedData.getSelectedRow(), 0).toString();

                String sql1 = "SELECT * FROM ST WHERE SA='" + SA + "'";
                ResultSet rs1 = SQLConnection.SqlConnection.getData(sql1);
                if (rs1.first()) {
                    String NA = rs1.getString("NA");
                    String UA = rs1.getString("UA");
                    String RHA = rs1.getString("RHA");
                    int TNTR1 = rs1.getInt("TNTR1");
                    int TNTR2 = rs1.getInt("TNTR2");

                    txtNilaAnkaya.setText(NA);
                    txtUpasewaAnkaya.setText(UA);
                    txtRanawiruHadunumAnkaya.setText(RHA);
                    ResultSet rsOther01 = SQLConnection.SqlConnection.getData("SELECT TRNAME FROM THANATHURU WHERE TRID='" + TNTR1 + "'");
                    if (rsOther01.first()) {
                        cmbPBadenawitaThanathura.setText(rsOther01.getString("TRNAME"));
                    }
                    ResultSet rsOther02 = SQLConnection.SqlConnection.getData("SELECT TRNAME FROM THANATHURU WHERE TRID='" + TNTR2 + "'");
                    if (rsOther02.first()) {
                        cmbWishramaYWThanathura.setText(rsOther02.getString("TRNAME"));
                    }

                    String sql2 = "SELECT * FROM SSWE WHERE SA='" + SA + "'";
                    ResultSet rs2 = SQLConnection.SqlConnection.getData(sql2);
                    if (rs2.first()) {
                        int SSWE = rs2.getInt("SSWE");
                        Date DB = rs2.getDate("DB");
                        String KAAA = rs2.getString("KAAA");

                        cmbWEAnuyuththada.setText(BStringTrue);
                        titleWEAnuyuththanam.setVisible(true);
                        ResultSet rsOther03 = SQLConnection.SqlConnection.getData("SELECT WENAME FROM WISHESHA_EKAKA WHERE WEA='" + SSWE + "'");
                        if (rsOther03.first()) {
                            cmbWisheshaEkakaya.setText(rsOther03.getString("WENAME"));
                        }
                        dtpWEBadunaDinaya.setText(DateToString(DB));
                        txtAnuKanda.setText(KAAA);

                    } else {
                        cmbWEAnuyuththada.setText(BStringFalse);
                        titleWEAnuyuththanam.setVisible(false);
                    }
                }

            } catch (Exception e) {
               new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
            }
        }
    }

    private void showAllWiwahayaData() {
        if (tblSeachedData.getSelectedRow() != -1) {
            try {
                String SA = tblSeachedData.getValueAt(tblSeachedData.getSelectedRow(), 0).toString();
                String sql1 = "SELECT  *  FROM  SS WHERE SA='" + SA + "'";
                ResultSet rs = SQLConnection.SqlConnection.getData(sql1);
                if (rs.first()) {

                    String SSN = rs.getString("SSN");
                    String SRL = rs.getString("SRL");
                    String SDA = rs.getString("SDA");
                    String SR = rs.getString("SR");

                    txtSahakarugeNama.setText(SSN);
                    txtSahakarugeRLipinaya.setText(SRL);
                    txtSahakarugeDKA.setText(SDA);
                    txtSahakarugeRakiyawa.setText(SR);

                    txtSahakarugeNama.setEnabled(true);
                    txtSahakarugeRLipinaya.setEnabled(true);
                    txtSahakarugeDKA.setEnabled(true);
                    txtSahakarugeRakiyawa.setEnabled(true);
                } else {
                    txtSahakarugeNama.setText("-");
                    txtSahakarugeRLipinaya.setText("-");
                    txtSahakarugeDKA.setText("-");
                    txtSahakarugeRakiyawa.setText("-");
                }

                String sql2 = "SELECT DKKD,WD,NWUD,WAWATBD FROM SMJK WHERE SA='" + SA + "'";
                ResultSet rs2 = SQLConnection.SqlConnection.getData(sql2);
                if (rs2.first()) {
                    String DKKD = rs2.getString("DKKD");
                    String NWUD = rs2.getString("NWUD");
                    String WAWATBD = rs2.getString("WAWATBD");

                    cmbDikkasadada.setText(DKKD);
                    cmbNawathaWiwahaUweda.setText(NWUD);
                    cmbWiwahayaAsarthakada.setText(WAWATBD);

                } else {

                }

            } catch (Exception e) {
               new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
            }
        }

    }

    private String getStringBoolean(boolean b) {
        if (b) {
            return "ඔව්";
        } else {
            return "නැත";
        }
    }

    private void showAllTrasthaData() {
        try {
            String SA = tblSeachedData.getValueAt(tblSeachedData.getSelectedRow(), 0).toString();
            String sql2 = "SELECT * FROM TRPWLS WHERE SA='" + SA + "'";
            ResultSet rs = SQLConnection.SqlConnection.getData(sql2);
            if (rs.first()) {
                String ISHP = rs.getString("ISHP");
                String OWAD = rs.getString("OWAD");
                Date LUD = rs.getDate("LUD");
                String LUK = rs.getString("LUK");
                String ASB = rs.getString("ASB");
                String LUS = rs.getString("LUS");
                int TRK = rs.getInt("TRK");
                //Find TRK---->
                ResultSet rsOther = SQLConnection.SqlConnection.getData("SELECT TRNAME FROM TRASTHA_KANDAYAM WHERE TRID='" + TRK + "'");
                if (rsOther.first()) {
                    cmbThrasthaKandayama.setText(rsOther.getString("TRNAME"));
                }

                lblPraharayaSiduwuDinaya.setText(DateToString(LUD));
                lblPraharayaSiduwuKottasaya.setText(LUK);
                lblPraharayaSiduwuSthanaya.setText(LUS);
                lblSharirikaHani.setText(ASB);
                lblOthpalada.setText(OWAD);
                lblIpaimaSHPrathishathaya.setText(ISHP + "%");
                String sql3 = "SELECT * FROM SJWP WHERE SA='" + SA + "'";
                ResultSet rs2 = SQLConnection.SqlConnection.getData(sql3);
                if (rs2.first()) {
                    String SKS = rs2.getString("SKS");
                    String SKK = rs2.getString("SKK");
                    Date SKD = rs2.getDate("SKD");

                    lblWidyaPPawathwuDinaya.setText(DateToString(SKD));
                    lblWidyaPPawathwuSthanaya.setText(SKS);
                    lblWidyaPPawathwuKo.setText(SKK);
                } else {

                }
                String sql4 = "SELECT * FROM AASS WHERE SA='" + SA + "'";
                System.out.println(sql4);
                ResultSet rs3 = SQLConnection.SqlConnection.getData(sql4);
                if (rs3.first()) {
                    DefaultTableModel model = (DefaultTableModel) tblAthuruAbada.getModel();
                    model.setRowCount(0);
                    rs3.beforeFirst();
                    while (rs3.next()) {
                        Vector v = new Vector();
                        v.add(rs3.getString("ABD"));
                        model.addRow(v);
                    }

                } else {
                    DefaultTableModel model = (DefaultTableModel) tblAthuruAbada.getModel();
                    model.setRowCount(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void showAllSamajikayaData() {
        //Samajikayage Thorathuru
        try {
            String sa = tblSeachedData.getValueAt(tblSeachedData.getSelectedRow(), 0).toString();
            ResultSet data = SQLConnection.SqlConnection.getData("SELECT * FROM SMJK WHERE SA='" + sa + "'");
            if (data.first()) {
                String SA = data.getString("SA");
                String JHA = data.getString("JHA");
                String SN = data.getString("SN");
                Date PBD = data.getDate("PBD");
                Date UD = data.getDate("UD");
                String AWSKS = data.getString("AWSKS");
                String AWSKK = data.getString("AWSKK");
                String WL = data.getString("WL");
                Date WLD = data.getDate("WLD");
                //String NWUD = data.getString("NWUD");-------------->Jump to Wiwahaya
                String JDA = data.getString("JDA");
                String SDA = data.getString("SDA");
                // String DKKD = data.getString("DKKD");-------------->Jump to Wiwahaya
                // String WD = data.getString("WD");--------------------->Jump to Wiwahaya
                // String WAWATBD = data.getString("WAWATBD");-->Jump to Wiwahaya
                String NSUS = data.getString("NSUS");
                Date SLD = data.getDate("SLD");
                // String TA = data.getString("TA");----------------------->FK Not Need
                // String PA = data.getString("PA");----------------------->FK Not Need

                ResultSet rs = SQLConnection.SqlConnection.getData("SELECT * FROM SL WHERE SA='" + SA + "'");
                rs.first();
                String SL = rs.getString("SL");
                String GNW = rs.getString("GNW");
                String PW = rs.getString("PW");
                String PLK = rs.getString("PLK");
                String DTRK = rs.getString("DTRK");
                lblSA.setText(SA);
                lblSamajikathwayaLDinaya.setText(DateToString(SLD));
                lblSampurnaNama.setText(SN);
                lblSthiraLipinaya.setText(SL);
                lblPolisiyataBadunaDinaya.setText(DateToString(PBD));
                lblAwasanWarataSKSthanaya.setText(AWSKS);
                lblAwasanWarataSKKottasaya.setText(AWSKK);
                lblWishramaLabuDinaya.setText(DateToString(WLD));
                lblPolisWasamaSL.setText(PW);
                lblPradeshiyaLekamKottasaya.setText(PLK);
                lblGramaNWasama.setText(GNW);
                lblSthawaraDurakathanaya.setText(DTRK);
                lblSthawaraDurakathanaya.setText(SDA);
                lblJangamaDurakathanaya.setText(JDA);
                lblEmail.setText(WL);
                lblDOB.setText(DateToString(UD));
                lblPolisiyataBahune.setText(NSUS);
                lblJathikaHadunumAnkaya.setText(JHA);
                displaySavaKalaya();
                displayWayasa();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void showAllDaruwanData() {
        String SA = tblSeachedData.getValueAt(tblSeachedData.getSelectedRow(), 0).toString();
        try {
            String sql1 = "SELECT * FROM SD WHERE SA='" + SA + "'";
            ResultSet rs = SQLConnection.SqlConnection.getData(sql1);
            DefaultTableModel model = (DefaultTableModel) tblDaruwan.getModel();
            model.setRowCount(0);
            if (rs.first()) {

                rs.beforeFirst();
                while (rs.next()) {
                    String UD = rs.getString("UD");
                    String NAME = rs.getString("NAME");
                    String DT = rs.getString("DT");
                    String ABTD = rs.getString("ABTD");

                    Vector rowData = new Vector();
                    rowData.add(NAME);
                    rowData.add(UD);
                    rowData.add(DT);
                    rowData.add(ABTD);
                    model.addRow(rowData);

                }
            } else {

            }
        } catch (Exception e) {
           new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
        }

    }

    private void displayWayasa() {
        try {
            LocalDate ld1 = LocalDate.parse(lblDOB.getText().replace(':', '-'));
            LocalDate ld2 = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            Period p = Period.between(ld1, ld2);
            lblWasaraW.setText(Integer.toString(p.getYears()));
            lblMasaW.setText(Integer.toString(p.getMonths()));
            lblDinaW.setText(Integer.toString(p.getDays()));
        } catch (Exception e) {
           new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
        }
    }

    private void displaySavaKalaya() {
        try {
            LocalDate ld1 = LocalDate.parse(lblPolisiyataBadunaDinaya.getText().replace(':', '-'));
            LocalDate ld2 = LocalDate.parse(lblWishramaLabuDinaya.getText().replace(':', '-'));

            Period p = Period.between(ld1, ld2);
            lblWasara.setText(Integer.toString(p.getYears()));
            lblMasa.setText(Integer.toString(p.getMonths()));
            lblDina.setText(Integer.toString(p.getDays()));

        } catch (Exception e) {
           new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        cmbSahanaya = new javax.swing.JComboBox();
        btnFillTable = new javax.swing.JButton();
        cbxSahanaya = new javax.swing.JCheckBox();
        cmbSahanaLS = new javax.swing.JComboBox();
        cbxSahanaLS = new javax.swing.JCheckBox();
        cbxThanathura = new javax.swing.JCheckBox();
        cmbThanathura = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSeachedData = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnPrint = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        trasthaPraharaya = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel10 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        titleAAbada = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblAthuruAbada = new javax.swing.JTable();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        cmbThrasthaKandayama = new javax.swing.JLabel();
        lblPraharayaSiduwuDinaya = new javax.swing.JLabel();
        lblPraharayaSiduwuKottasaya = new javax.swing.JLabel();
        lblPraharayaSiduwuSthanaya = new javax.swing.JLabel();
        lblSharirikaHani = new javax.swing.JLabel();
        lblOthpalada = new javax.swing.JLabel();
        lblWidyaPPawathwuDinaya = new javax.swing.JLabel();
        lblWidyaPPawathwuSthanaya = new javax.swing.JLabel();
        lblIpaimaSHPrathishathaya = new javax.swing.JLabel();
        lblWidyaPPawathwuKo = new javax.swing.JLabel();
        samajikaya = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lblSthiraLipinaya = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lblWasara = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        lblMasa = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        lblDina = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        lblPolisWasamaSL = new javax.swing.JLabel();
        lblPradeshiyaLekamKottasaya = new javax.swing.JLabel();
        lblGramaNWasama = new javax.swing.JLabel();
        lblDistrikkaya = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblSA = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        lblWasaraW = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        lblMasaW = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        lblDinaW = new javax.swing.JLabel();
        lblSamajikathwayaLDinaya = new javax.swing.JLabel();
        lblSampurnaNama = new javax.swing.JLabel();
        lblPolisiyataBadunaDinaya = new javax.swing.JLabel();
        lblPolisiyataBahune = new javax.swing.JLabel();
        lblJathikaHadunumAnkaya = new javax.swing.JLabel();
        lblAwasanWarataSKSthanaya = new javax.swing.JLabel();
        lblAwasanWarataSKKottasaya = new javax.swing.JLabel();
        lblWishramaLabuDinaya = new javax.swing.JLabel();
        lblSthawaraDurakathanaya = new javax.swing.JLabel();
        lblJangamaDurakathanaya = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblDOB = new javax.swing.JLabel();
        daruwan = new javax.swing.JPanel();
        titleDaruwanSitinam = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblDaruwan = new javax.swing.JTable();
        masukaWatup = new javax.swing.JPanel();
        lblMasikaWatupText = new javax.swing.JLabel();
        titleMWLabenam = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        lblMWWInfor = new javax.swing.JLabel();
        lblMuluWatupeWatinakama = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        txtWlabanaKottasaya = new javax.swing.JLabel();
        txtWlabanaPolisWasama = new javax.swing.JLabel();
        txtMulikaWatupeWatinakama = new javax.swing.JLabel();
        txtDimanaWalaWatinakama = new javax.swing.JLabel();
        wisramaWatup = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        titleWishramaWLanannenam = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        lblDWWW = new javax.swing.JLabel();
        lblWWW = new javax.swing.JLabel();
        wwPradheshilaLK = new javax.swing.JLabel();
        txtWWAnkaya = new javax.swing.JLabel();
        titleWWNHethu = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblWWNHethu = new javax.swing.JTable();
        lblDWWLabannedaha = new javax.swing.JLabel();
        lblWWLabannedaha = new javax.swing.JLabel();
        titleDWWNHethu = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblDWWNHethu = new javax.swing.JTable();
        sahana = new javax.swing.JPanel();
        titleSahanaLabinama = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblSahana = new javax.swing.JTable();
        abadithaUpakarana = new javax.swing.JPanel();
        titleAUBKarainam = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblAU = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblAULDinayanMitaPasu = new javax.swing.JTable();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblAULDinayanMitaPera = new javax.swing.JTable();
        wiwahaya = new javax.swing.JPanel();
        titleWiwahakanam = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtSahakarugeRLipinaya = new javax.swing.JTextArea();
        jLabel65 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        txtSahakarugeNama = new javax.swing.JLabel();
        txtSahakarugeRakiyawa = new javax.swing.JLabel();
        txtSahakarugeDKA = new javax.swing.JLabel();
        cmbNawathaWiwahaUweda = new javax.swing.JLabel();
        cmbDikkasadada = new javax.swing.JLabel();
        cmbWiwahayaAsarthakada = new javax.swing.JLabel();
        thanathura = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        titleWEAnuyuththanam = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cmbWisheshaEkakaya = new javax.swing.JLabel();
        dtpWEBadunaDinaya = new javax.swing.JLabel();
        txtAnuKanda = new javax.swing.JLabel();
        cmbPBadenawitaThanathura = new javax.swing.JLabel();
        cmbWishramaYWThanathura = new javax.swing.JLabel();
        txtNilaAnkaya = new javax.swing.JLabel();
        txtUpasewaAnkaya = new javax.swing.JLabel();
        txtRanawiruHadunumAnkaya = new javax.swing.JLabel();
        cmbWEAnuyuththada = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("වාර්ථා ");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jTabbedPane2.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 0), 2, true), "සොයන්න", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Iskoola Pota", 0, 14))); // NOI18N

        cmbSahanaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

        btnFillTable.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        btnFillTable.setText("fidhkak");
        btnFillTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFillTableActionPerformed(evt);
            }
        });

        cbxSahanaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        cbxSahanaya.setText("iykh");

        cmbSahanaLS.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

        cbxSahanaLS.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        cbxSahanaLS.setText("iyk ,efnk ia:dkh ");

        cbxThanathura.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        cbxThanathura.setText(";k;=r ");

        cmbThanathura.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        cmbThanathura.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "fmd,sia fldia;dm,a ", "fmd,sia ierhka ", "Wm fmd,sia mÍlaIl", "fmd,sia mÍlaIl", "m%Odk fmd,sia mÍlaIl  ", "iyldr fmd,sia wêldÍ ", "fcHIaG fmd,sia wêldÍ", "ksfhdacH fmd,sia m;s" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(cbxSahanaya, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmbSahanaya, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxSahanaLS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxThanathura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbSahanaLS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbThanathura, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnFillTable, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSahanaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSahanaya))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSahanaLS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSahanaLS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxThanathura)
                    .addComponent(cmbThanathura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFillTable))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 204), 2, true), "m%;sm,", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FMBindumathi", 0, 14))); // NOI18N

        tblSeachedData.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        tblSeachedData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "සාමාජික අංකය ", "නම ", "ජා හැ අංකය "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSeachedData.getTableHeader().setReorderingAllowed(false);
        tblSeachedData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblSeachedDataMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblSeachedData);
        if (tblSeachedData.getColumnModel().getColumnCount() > 0) {
            tblSeachedData.getColumnModel().getColumn(0).setResizable(false);
            tblSeachedData.getColumnModel().getColumn(0).setPreferredWidth(150);
            tblSeachedData.getColumnModel().getColumn(1).setResizable(false);
            tblSeachedData.getColumnModel().getColumn(1).setPreferredWidth(392);
            tblSeachedData.getColumnModel().getColumn(2).setResizable(false);
            tblSeachedData.getColumnModel().getColumn(2).setPreferredWidth(150);
        }

        jLabel1.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel1.setText("idudðlhska ikaLHdj ");

        lblTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTotal.setText("00");

        btnPrint.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        btnPrint.setText("úia;rd;aul jd¾:dj ");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 227, Short.MAX_VALUE)
                        .addComponent(btnPrint)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(lblTotal))
                    .addComponent(btnPrint, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("fidhkak ", jPanel5);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 2, true), "f;dard.;a idudðlhdf.a úia;r ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FMMalithi", 0, 14))); // NOI18N
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setDoubleBuffered(true);
        jTabbedPane1.setFocusCycleRoot(true);
        jTabbedPane1.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jTabbedPane1.setInheritsPopupMenu(true);

        jLabel21.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel21.setText(";%ia;jdoS m%ydrh isÿlf,a ");

        jLabel22.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel22.setText(";%ia;jdoS m%ydrhg ,lajQ oskh ");

        jLabel23.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel23.setText(";%ia;jdoS m%ydrhg ,lajQ fldÜGdYh   ");

        jLabel24.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel24.setText(";%ia;jdoS m%ydrhg ,lajQ ia:dkh ");

        jLabel25.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel25.setText("Wk;=reka isÿjQ ;=jd, j, yd Ydßßl ydks j, iajNdjh  ");

        titleAAbada.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Iskoola Pota", 0, 14))); // NOI18N

        tblAthuruAbada.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        tblAthuruAbada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "අතුරු අබාධ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAthuruAbada.setColumnSelectionAllowed(true);
        tblAthuruAbada.setSelectionBackground(new java.awt.Color(0, 153, 153));
        jScrollPane5.setViewportView(tblAthuruAbada);

        javax.swing.GroupLayout titleAAbadaLayout = new javax.swing.GroupLayout(titleAAbada);
        titleAAbada.setLayout(titleAAbadaLayout);
        titleAAbadaLayout.setHorizontalGroup(
            titleAAbadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleAAbadaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                .addContainerGap())
        );
        titleAAbadaLayout.setVerticalGroup(
            titleAAbadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleAAbadaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel36.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel36.setText("ffjoH mßlaIK uKav,h meje;ajQ oskh ");

        jLabel37.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel37.setText("ffjoH mßlaIK uKav,h meje;ajQ ia:dkh ");

        jLabel38.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel38.setText("bmehSfï Yla;sh ysk jQ m%;sY;h ");

        jLabel35.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel35.setText("isÿ ù we;s ydks fya;=fjka iodld,slj frdao mqgqjlg fyda .s,ka weolg iSudù isào ");

        jLabel73.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel73.setText("ffjoH mßlaIK uKav,h meje;ajQ fldÜGdYh  ");

        cmbThrasthaKandayama.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        cmbThrasthaKandayama.setForeground(new java.awt.Color(153, 51, 0));
        cmbThrasthaKandayama.setText("-");

        lblPraharayaSiduwuDinaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblPraharayaSiduwuDinaya.setForeground(new java.awt.Color(153, 51, 0));
        lblPraharayaSiduwuDinaya.setText("-");

        lblPraharayaSiduwuKottasaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblPraharayaSiduwuKottasaya.setForeground(new java.awt.Color(153, 51, 0));
        lblPraharayaSiduwuKottasaya.setText("-");

        lblPraharayaSiduwuSthanaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblPraharayaSiduwuSthanaya.setForeground(new java.awt.Color(153, 51, 0));
        lblPraharayaSiduwuSthanaya.setText("-");

        lblSharirikaHani.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblSharirikaHani.setForeground(new java.awt.Color(153, 51, 0));
        lblSharirikaHani.setText("-");

        lblOthpalada.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblOthpalada.setForeground(new java.awt.Color(153, 51, 0));
        lblOthpalada.setText("-");

        lblWidyaPPawathwuDinaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblWidyaPPawathwuDinaya.setForeground(new java.awt.Color(153, 51, 0));
        lblWidyaPPawathwuDinaya.setText("-");

        lblWidyaPPawathwuSthanaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblWidyaPPawathwuSthanaya.setForeground(new java.awt.Color(153, 51, 0));
        lblWidyaPPawathwuSthanaya.setText("-");

        lblIpaimaSHPrathishathaya.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        lblIpaimaSHPrathishathaya.setForeground(new java.awt.Color(153, 51, 0));
        lblIpaimaSHPrathishathaya.setText("-");

        lblWidyaPPawathwuKo.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblWidyaPPawathwuKo.setForeground(new java.awt.Color(153, 51, 0));
        lblWidyaPPawathwuKo.setText("-");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPraharayaSiduwuDinaya, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPraharayaSiduwuKottasaya, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPraharayaSiduwuSthanaya, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbThrasthaKandayama, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblWidyaPPawathwuSthanaya, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblWidyaPPawathwuDinaya, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblWidyaPPawathwuKo, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblIpaimaSHPrathishathaya, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblSharirikaHani, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblOthpalada, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(titleAAbada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 8, Short.MAX_VALUE))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(cmbThrasthaKandayama))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(lblPraharayaSiduwuDinaya))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(lblPraharayaSiduwuKottasaya))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(lblPraharayaSiduwuSthanaya))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(lblSharirikaHani))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(lblOthpalada))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(titleAAbada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(lblWidyaPPawathwuDinaya))
                .addGap(12, 12, 12)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(lblWidyaPPawathwuSthanaya))
                .addGap(15, 15, 15)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(lblWidyaPPawathwuKo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(lblIpaimaSHPrathishathaya))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(jPanel10);

        javax.swing.GroupLayout trasthaPraharayaLayout = new javax.swing.GroupLayout(trasthaPraharaya);
        trasthaPraharaya.setLayout(trasthaPraharayaLayout);
        trasthaPraharayaLayout.setHorizontalGroup(
            trasthaPraharayaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );
        trasthaPraharayaLayout.setVerticalGroup(
            trasthaPraharayaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(";%ia; m%ydrh ", trasthaPraharaya);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jLabel2.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel2.setText("idudðl;ajh ,enQ oskh ");

        jLabel4.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel4.setText("iïmQ¾K ku ");

        jScrollPane3.setBorder(null);

        lblSthiraLipinaya.setBackground(new java.awt.Color(240, 240, 240));
        lblSthiraLipinaya.setColumns(20);
        lblSthiraLipinaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblSthiraLipinaya.setForeground(new java.awt.Color(153, 0, 0));
        lblSthiraLipinaya.setLineWrap(true);
        lblSthiraLipinaya.setRows(5);
        lblSthiraLipinaya.setText("-");
        lblSthiraLipinaya.setToolTipText("");
        lblSthiraLipinaya.setWrapStyleWord(true);
        lblSthiraLipinaya.setBorder(null);
        jScrollPane3.setViewportView(lblSthiraLipinaya);

        jLabel5.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel5.setText("iaÒr ,smskh ");

        jLabel7.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel7.setText("fmd,sishg neÿk oskh ");

        jLabel19.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel19.setText("wjika jrg fijh l, ia:dkh ");

        jLabel20.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel20.setText("wjika jrg fiajh l, fldÜGdYh  ");

        jLabel28.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel28.setText("úY%du ,enQ oskh ");

        jLabel29.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel29.setText("úY%du ,enQ oskg fiajd ld,h  ");

        jLabel30.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel30.setText("jir ");

        lblWasara.setFont(new java.awt.Font("Iskoola Pota", 3, 14)); // NOI18N
        lblWasara.setForeground(new java.awt.Color(204, 0, 204));
        lblWasara.setText("????");

        jLabel32.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel32.setText("udi ");

        lblMasa.setFont(new java.awt.Font("Iskoola Pota", 3, 14)); // NOI18N
        lblMasa.setForeground(new java.awt.Color(204, 0, 204));
        lblMasa.setText("????");

        jLabel34.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel34.setText("osk ");

        lblDina.setFont(new java.awt.Font("Iskoola Pota", 3, 14)); // NOI18N
        lblDina.setForeground(new java.awt.Color(204, 0, 204));
        lblDina.setText("????");

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "iaÒr ,smskhg wod, ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FMMalithi", 0, 14))); // NOI18N

        jLabel54.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel54.setText("fmd,sia jiu ");

        jLabel55.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel55.setText(".%du ks,Odß jiu ");

        jLabel56.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel56.setText("osia;%Slalh ");

        jLabel57.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel57.setText("m%dfoaYSh f,alï fldÜGdYh  ");

        lblPolisWasamaSL.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblPolisWasamaSL.setForeground(new java.awt.Color(153, 0, 0));
        lblPolisWasamaSL.setText("-");

        lblPradeshiyaLekamKottasaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblPradeshiyaLekamKottasaya.setForeground(new java.awt.Color(153, 0, 0));
        lblPradeshiyaLekamKottasaya.setText("-");

        lblGramaNWasama.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblGramaNWasama.setForeground(new java.awt.Color(153, 0, 0));
        lblGramaNWasama.setText("-");

        lblDistrikkaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblDistrikkaya.setForeground(new java.awt.Color(153, 0, 0));
        lblDistrikkaya.setText("-");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDistrikkaya, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblGramaNWasama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPolisWasamaSL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPradeshiyaLekamKottasaya, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(lblPolisWasamaSL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(lblPradeshiyaLekamKottasaya))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(lblGramaNWasama))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(lblDistrikkaya))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel58.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel58.setText("ia:djr ÿrl:k wxlh ");

        jLabel59.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel59.setText("cx.u ÿrl:k wxlh ");

        jLabel60.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel60.setText("úoahq;a ,smskh ");

        jLabel61.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel61.setText("Wmka oskh ");

        jLabel3.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel3.setText("idudðl wxlh ");

        lblSA.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblSA.setForeground(new java.awt.Color(153, 0, 0));
        lblSA.setText("-");

        jLabel8.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel8.setText("fmd,sishg neÿfka ");

        jLabel13.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel13.setText("cd ye wxlh ");

        jLabel31.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel31.setText("wo oskg jhi ");

        jLabel33.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel33.setText("jir ");

        lblWasaraW.setFont(new java.awt.Font("Iskoola Pota", 3, 14)); // NOI18N
        lblWasaraW.setForeground(new java.awt.Color(204, 0, 204));
        lblWasaraW.setText("????");

        jLabel39.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel39.setText("udi ");

        lblMasaW.setFont(new java.awt.Font("Iskoola Pota", 3, 14)); // NOI18N
        lblMasaW.setForeground(new java.awt.Color(204, 0, 204));
        lblMasaW.setText("????");

        jLabel40.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel40.setText("osk ");

        lblDinaW.setFont(new java.awt.Font("Iskoola Pota", 3, 14)); // NOI18N
        lblDinaW.setForeground(new java.awt.Color(204, 0, 204));
        lblDinaW.setText("????");

        lblSamajikathwayaLDinaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblSamajikathwayaLDinaya.setForeground(new java.awt.Color(153, 0, 0));
        lblSamajikathwayaLDinaya.setText("-");

        lblSampurnaNama.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblSampurnaNama.setForeground(new java.awt.Color(153, 0, 0));
        lblSampurnaNama.setText("-");

        lblPolisiyataBadunaDinaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblPolisiyataBadunaDinaya.setForeground(new java.awt.Color(153, 0, 0));
        lblPolisiyataBadunaDinaya.setText("-");

        lblPolisiyataBahune.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblPolisiyataBahune.setForeground(new java.awt.Color(153, 0, 0));
        lblPolisiyataBahune.setText("-");

        lblJathikaHadunumAnkaya.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        lblJathikaHadunumAnkaya.setForeground(new java.awt.Color(153, 0, 0));
        lblJathikaHadunumAnkaya.setText("-");

        lblAwasanWarataSKSthanaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblAwasanWarataSKSthanaya.setForeground(new java.awt.Color(153, 0, 0));
        lblAwasanWarataSKSthanaya.setText("-");

        lblAwasanWarataSKKottasaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblAwasanWarataSKKottasaya.setForeground(new java.awt.Color(153, 0, 0));
        lblAwasanWarataSKKottasaya.setText("-");

        lblWishramaLabuDinaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblWishramaLabuDinaya.setForeground(new java.awt.Color(153, 0, 0));
        lblWishramaLabuDinaya.setText("-");

        lblSthawaraDurakathanaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblSthawaraDurakathanaya.setForeground(new java.awt.Color(153, 0, 0));
        lblSthawaraDurakathanaya.setText("-");

        lblJangamaDurakathanaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblJangamaDurakathanaya.setForeground(new java.awt.Color(153, 0, 0));
        lblJangamaDurakathanaya.setText("-");

        lblEmail.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(153, 0, 0));
        lblEmail.setText("-");

        lblDOB.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblDOB.setForeground(new java.awt.Color(153, 0, 0));
        lblDOB.setText("-");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblWasara, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMasa, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDina, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblAwasanWarataSKSthanaya, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                                    .addComponent(lblAwasanWarataSKKottasaya, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblWishramaLabuDinaya, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPolisiyataBadunaDinaya, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPolisiyataBahune, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(lblJathikaHadunumAnkaya, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblWasaraW, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblMasaW, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel40)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDinaW, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSamajikathwayaLDinaya, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSampurnaNama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSA, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel58, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(jLabel59, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(lblSthawaraDurakathanaya, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblJangamaDurakathanaya, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblSA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblSamajikathwayaLDinaya))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblSampurnaNama))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblPolisiyataBadunaDinaya))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblPolisiyataBahune))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lblJathikaHadunumAnkaya))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(lblAwasanWarataSKSthanaya))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(lblAwasanWarataSKKottasaya))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(lblWishramaLabuDinaya))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addComponent(lblWasara)
                    .addComponent(jLabel32)
                    .addComponent(lblMasa)
                    .addComponent(jLabel34)
                    .addComponent(lblDina))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(lblSthawaraDurakathanaya))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(lblJangamaDurakathanaya))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(lblEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(lblDOB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel33)
                    .addComponent(lblWasaraW)
                    .addComponent(jLabel39)
                    .addComponent(lblMasaW)
                    .addComponent(jLabel40)
                    .addComponent(lblDinaW))
                .addContainerGap(157, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel2);

        javax.swing.GroupLayout samajikayaLayout = new javax.swing.GroupLayout(samajikaya);
        samajikaya.setLayout(samajikayaLayout);
        samajikayaLayout.setHorizontalGroup(
            samajikayaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        samajikayaLayout.setVerticalGroup(
            samajikayaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("idudðlhdf.a idudkH úia;r ", samajikaya);

        titleDaruwanSitinam.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblDaruwan.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        tblDaruwan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "දරුවාගේ නම ", "උපන් දිනය ", "දැනට තත්වය ", "අබාදිතද "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDaruwan.setSelectionBackground(new java.awt.Color(0, 153, 153));
        tblDaruwan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblDaruwanMouseReleased(evt);
            }
        });
        jScrollPane7.setViewportView(tblDaruwan);

        javax.swing.GroupLayout titleDaruwanSitinamLayout = new javax.swing.GroupLayout(titleDaruwanSitinam);
        titleDaruwanSitinam.setLayout(titleDaruwanSitinamLayout);
        titleDaruwanSitinamLayout.setHorizontalGroup(
            titleDaruwanSitinamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleDaruwanSitinamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
                .addContainerGap())
        );
        titleDaruwanSitinamLayout.setVerticalGroup(
            titleDaruwanSitinamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleDaruwanSitinamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout daruwanLayout = new javax.swing.GroupLayout(daruwan);
        daruwan.setLayout(daruwanLayout);
        daruwanLayout.setHorizontalGroup(
            daruwanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(daruwanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleDaruwanSitinam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        daruwanLayout.setVerticalGroup(
            daruwanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(daruwanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleDaruwanSitinam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(198, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("orejka ", daruwan);

        lblMasikaWatupText.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblMasikaWatupText.setForeground(new java.awt.Color(153, 0, 204));
        lblMasikaWatupText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMasikaWatupText.setText("udisl jegqma yd oSukd ");

        titleMWLabenam.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Iskoola Pota", 0, 14))); // NOI18N

        jLabel43.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel43.setText("jegqma ,nk fldÜGdYh");

        jLabel44.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel44.setText("fmd,sia ia:dkh  ");

        jLabel45.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel45.setText("uQ,sl jegqm ");

        jLabel47.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel47.setText("uq¨ oSukd ");

        lblMWWInfor.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        lblMWWInfor.setForeground(new java.awt.Color(204, 0, 0));

        lblMuluWatupeWatinakama.setFont(new java.awt.Font("Iskoola Pota", 3, 14)); // NOI18N
        lblMuluWatupeWatinakama.setForeground(new java.awt.Color(204, 0, 204));
        lblMuluWatupeWatinakama.setText("????");

        jLabel72.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel72.setText("uq¨ jgqm");

        txtWlabanaKottasaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        txtWlabanaKottasaya.setForeground(new java.awt.Color(153, 0, 0));
        txtWlabanaKottasaya.setText("-");

        txtWlabanaPolisWasama.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        txtWlabanaPolisWasama.setForeground(new java.awt.Color(153, 0, 0));
        txtWlabanaPolisWasama.setText("-");

        txtMulikaWatupeWatinakama.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        txtMulikaWatupeWatinakama.setForeground(new java.awt.Color(153, 0, 0));
        txtMulikaWatupeWatinakama.setText("-");

        txtDimanaWalaWatinakama.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        txtDimanaWalaWatinakama.setForeground(new java.awt.Color(153, 0, 0));
        txtDimanaWalaWatinakama.setText("-");

        javax.swing.GroupLayout titleMWLabenamLayout = new javax.swing.GroupLayout(titleMWLabenam);
        titleMWLabenam.setLayout(titleMWLabenamLayout);
        titleMWLabenamLayout.setHorizontalGroup(
            titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleMWLabenamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(titleMWLabenamLayout.createSequentialGroup()
                        .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMuluWatupeWatinakama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(75, 75, 75)
                        .addComponent(lblMWWInfor, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(titleMWLabenamLayout.createSequentialGroup()
                        .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtWlabanaKottasaya, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMulikaWatupeWatinakama, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(16, 16, 16)
                        .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDimanaWalaWatinakama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtWlabanaPolisWasama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5))))
        );
        titleMWLabenamLayout.setVerticalGroup(
            titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleMWLabenamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(titleMWLabenamLayout.createSequentialGroup()
                        .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43)
                            .addComponent(txtWlabanaKottasaya))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel45)
                            .addComponent(txtMulikaWatupeWatinakama)))
                    .addGroup(titleMWLabenamLayout.createSequentialGroup()
                        .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44)
                            .addComponent(txtWlabanaPolisWasama))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47)
                            .addComponent(txtDimanaWalaWatinakama))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMWWInfor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                    .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMuluWatupeWatinakama)
                        .addComponent(jLabel72)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout masukaWatupLayout = new javax.swing.GroupLayout(masukaWatup);
        masukaWatup.setLayout(masukaWatupLayout);
        masukaWatupLayout.setHorizontalGroup(
            masukaWatupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(masukaWatupLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(masukaWatupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(masukaWatupLayout.createSequentialGroup()
                        .addComponent(lblMasikaWatupText, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 33, Short.MAX_VALUE))
                    .addComponent(titleMWLabenam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        masukaWatupLayout.setVerticalGroup(
            masukaWatupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(masukaWatupLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMasikaWatupText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titleMWLabenam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(439, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("udisl jegqma ", masukaWatup);

        jScrollPane13.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        titleWishramaWLanannenam.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel51.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel51.setText("úY%du jegqma wxlh ");

        jLabel52.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel52.setText("úY%du jegqm");

        jLabel53.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel53.setText("ÿn,;d úY%du jegqm");

        jLabel62.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel62.setText("úY%du jegqma ,nd.kakd m%dfoaYSh f,aLï fldÜGdYh ");

        lblDWWW.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblDWWW.setForeground(new java.awt.Color(153, 0, 0));
        lblDWWW.setText("-");

        lblWWW.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblWWW.setForeground(new java.awt.Color(153, 0, 0));
        lblWWW.setText("-");

        wwPradheshilaLK.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        wwPradheshilaLK.setForeground(new java.awt.Color(153, 0, 0));
        wwPradheshilaLK.setText("-");

        txtWWAnkaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        txtWWAnkaya.setForeground(new java.awt.Color(153, 0, 0));
        txtWWAnkaya.setText("-");

        javax.swing.GroupLayout titleWishramaWLanannenamLayout = new javax.swing.GroupLayout(titleWishramaWLanannenam);
        titleWishramaWLanannenam.setLayout(titleWishramaWLanannenamLayout);
        titleWishramaWLanannenamLayout.setHorizontalGroup(
            titleWishramaWLanannenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleWishramaWLanannenamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(titleWishramaWLanannenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(titleWishramaWLanannenamLayout.createSequentialGroup()
                        .addGroup(titleWishramaWLanannenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(titleWishramaWLanannenamLayout.createSequentialGroup()
                                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtWWAnkaya, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(titleWishramaWLanannenamLayout.createSequentialGroup()
                                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(wwPradheshilaLK, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 34, Short.MAX_VALUE))
                    .addGroup(titleWishramaWLanannenamLayout.createSequentialGroup()
                        .addGroup(titleWishramaWLanannenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                            .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(titleWishramaWLanannenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblWWW, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDWWW, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        titleWishramaWLanannenamLayout.setVerticalGroup(
            titleWishramaWLanannenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleWishramaWLanannenamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(titleWishramaWLanannenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(txtWWAnkaya))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(titleWishramaWLanannenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(wwPradheshilaLK))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE)
                .addGroup(titleWishramaWLanannenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(lblWWW))
                .addGap(10, 10, 10)
                .addGroup(titleWishramaWLanannenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDWWW)
                    .addComponent(jLabel53))
                .addContainerGap())
        );

        titleWWNHethu.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Iskoola Pota", 0, 14))); // NOI18N
        titleWWNHethu.setEnabled(false);

        tblWWNHethu.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        tblWWNHethu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ව්ශ්‍රම වැටුප් නොලැබීමට හේතු "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblWWNHethu.setEnabled(false);
        tblWWNHethu.setSelectionBackground(new java.awt.Color(0, 153, 153));
        jScrollPane11.setViewportView(tblWWNHethu);

        javax.swing.GroupLayout titleWWNHethuLayout = new javax.swing.GroupLayout(titleWWNHethu);
        titleWWNHethu.setLayout(titleWWNHethuLayout);
        titleWWNHethuLayout.setHorizontalGroup(
            titleWWNHethuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleWWNHethuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11)
                .addContainerGap())
        );
        titleWWNHethuLayout.setVerticalGroup(
            titleWWNHethuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleWWNHethuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblDWWLabannedaha.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblDWWLabannedaha.setForeground(new java.awt.Color(153, 0, 204));
        lblDWWLabannedaha.setText("ÿn,;d úY%du jegqma ");

        lblWWLabannedaha.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblWWLabannedaha.setForeground(new java.awt.Color(153, 0, 204));
        lblWWLabannedaha.setText("úY%du jegqma ");

        titleDWWNHethu.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Iskoola Pota", 0, 14))); // NOI18N
        titleDWWNHethu.setEnabled(false);

        tblDWWNHethu.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        tblDWWNHethu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "දුබලතා විශ්‍රාම වැටුප් නොලබන්නේනම් "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDWWNHethu.setEnabled(false);
        tblDWWNHethu.setSelectionBackground(new java.awt.Color(0, 153, 153));
        jScrollPane12.setViewportView(tblDWWNHethu);

        javax.swing.GroupLayout titleDWWNHethuLayout = new javax.swing.GroupLayout(titleDWWNHethu);
        titleDWWNHethu.setLayout(titleDWWNHethuLayout);
        titleDWWNHethuLayout.setHorizontalGroup(
            titleDWWNHethuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleDWWNHethuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                .addContainerGap())
        );
        titleDWWNHethuLayout.setVerticalGroup(
            titleDWWNHethuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleDWWNHethuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(titleWishramaWLanannenam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titleDWWNHethu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblWWLabannedaha, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDWWLabannedaha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(titleWWNHethu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWWLabannedaha)
                    .addComponent(lblDWWLabannedaha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titleWishramaWLanannenam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(titleWWNHethu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(titleDWWNHethu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane13.setViewportView(jPanel4);

        javax.swing.GroupLayout wisramaWatupLayout = new javax.swing.GroupLayout(wisramaWatup);
        wisramaWatup.setLayout(wisramaWatupLayout);
        wisramaWatupLayout.setHorizontalGroup(
            wisramaWatupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        wisramaWatupLayout.setVerticalGroup(
            wisramaWatupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("úY%du jegqma ", wisramaWatup);

        titleSahanaLabinama.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ",nd we;s iyk ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FMMalithi", 0, 14))); // NOI18N

        tblSahana.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        tblSahana.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "සහනය ", "ලබන ස්ථානය  ", "අවසන් වරට ලබාගත් දිනය"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSahana.setSelectionBackground(new java.awt.Color(0, 153, 153));
        jScrollPane10.setViewportView(tblSahana);

        javax.swing.GroupLayout titleSahanaLabinamaLayout = new javax.swing.GroupLayout(titleSahanaLabinama);
        titleSahanaLabinama.setLayout(titleSahanaLabinamaLayout);
        titleSahanaLabinamaLayout.setHorizontalGroup(
            titleSahanaLabinamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleSahanaLabinamaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                .addContainerGap())
        );
        titleSahanaLabinamaLayout.setVerticalGroup(
            titleSahanaLabinamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleSahanaLabinamaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout sahanaLayout = new javax.swing.GroupLayout(sahana);
        sahana.setLayout(sahanaLayout);
        sahanaLayout.setHorizontalGroup(
            sahanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sahanaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleSahanaLabinama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        sahanaLayout.setVerticalGroup(
            sahanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sahanaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleSahanaLabinama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(251, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("iyk ", sahana);

        titleAUBKarainam.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ndú;d lrk wndê; WmlrK ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FMMalithi", 0, 14))); // NOI18N

        tblAU.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        tblAU.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ආබාදිත උපකරණ "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAU.setColumnSelectionAllowed(true);
        tblAU.setSelectionBackground(new java.awt.Color(0, 153, 153));
        tblAU.getTableHeader().setReorderingAllowed(false);
        tblAU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblAUMouseReleased(evt);
            }
        });
        tblAU.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblAUKeyReleased(evt);
            }
        });
        jScrollPane8.setViewportView(tblAU);

        tblAULDinayanMitaPasu.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        tblAULDinayanMitaPasu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "මීට පසු ලබාගත් දිනයන් "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAULDinayanMitaPasu.setColumnSelectionAllowed(true);
        tblAULDinayanMitaPasu.setSelectionBackground(new java.awt.Color(0, 153, 153));
        jScrollPane9.setViewportView(tblAULDinayanMitaPasu);

        tblAULDinayanMitaPera.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        tblAULDinayanMitaPera.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "මීට පෙර ලබාගත් දිනයන් "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAULDinayanMitaPera.setColumnSelectionAllowed(true);
        tblAULDinayanMitaPera.setSelectionBackground(new java.awt.Color(0, 153, 153));
        jScrollPane14.setViewportView(tblAULDinayanMitaPera);

        javax.swing.GroupLayout titleAUBKarainamLayout = new javax.swing.GroupLayout(titleAUBKarainam);
        titleAUBKarainam.setLayout(titleAUBKarainamLayout);
        titleAUBKarainamLayout.setHorizontalGroup(
            titleAUBKarainamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleAUBKarainamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(titleAUBKarainamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
                    .addGroup(titleAUBKarainamLayout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        titleAUBKarainamLayout.setVerticalGroup(
            titleAUBKarainamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleAUBKarainamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(titleAUBKarainamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout abadithaUpakaranaLayout = new javax.swing.GroupLayout(abadithaUpakarana);
        abadithaUpakarana.setLayout(abadithaUpakaranaLayout);
        abadithaUpakaranaLayout.setHorizontalGroup(
            abadithaUpakaranaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abadithaUpakaranaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleAUBKarainam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        abadithaUpakaranaLayout.setVerticalGroup(
            abadithaUpakaranaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abadithaUpakaranaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleAUBKarainam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("wdndê; WmlrK ", abadithaUpakarana);

        jLabel63.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel63.setText("ìßof.a$iajdñmqreIhdf.a ku ");

        jLabel64.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel64.setText("ìßof.a$iajdñmqreIhdf.a rdcldÍ ,smskh ");

        jLabel66.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel66.setText("ìßof.a$iajdñmqreIhdf.a /lshdj ");

        jScrollPane6.setBorder(null);

        txtSahakarugeRLipinaya.setBackground(new java.awt.Color(240, 240, 240));
        txtSahakarugeRLipinaya.setColumns(20);
        txtSahakarugeRLipinaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        txtSahakarugeRLipinaya.setForeground(new java.awt.Color(153, 51, 0));
        txtSahakarugeRLipinaya.setRows(5);
        txtSahakarugeRLipinaya.setText("-");
        txtSahakarugeRLipinaya.setBorder(null);
        txtSahakarugeRLipinaya.setOpaque(false);
        jScrollPane6.setViewportView(txtSahakarugeRLipinaya);

        jLabel65.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel65.setText("oslalido jQfhao hk nj ");

        jLabel67.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel67.setText("kej; újdy jQfhao hk nj");

        jLabel68.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel68.setText("újdyh wid¾:l ùug wdndê; ;;ajh n,mEfõo ");

        jLabel71.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel71.setText("ìßof.a$iajdñmqreIhdf.a ÿrl:k wxlh ");

        txtSahakarugeNama.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        txtSahakarugeNama.setForeground(new java.awt.Color(153, 51, 0));
        txtSahakarugeNama.setText("-");

        txtSahakarugeRakiyawa.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        txtSahakarugeRakiyawa.setForeground(new java.awt.Color(153, 51, 0));
        txtSahakarugeRakiyawa.setText("-");

        txtSahakarugeDKA.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        txtSahakarugeDKA.setForeground(new java.awt.Color(153, 51, 0));
        txtSahakarugeDKA.setText("-");

        cmbNawathaWiwahaUweda.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        cmbNawathaWiwahaUweda.setForeground(new java.awt.Color(153, 51, 0));
        cmbNawathaWiwahaUweda.setText("-");

        cmbDikkasadada.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        cmbDikkasadada.setForeground(new java.awt.Color(153, 51, 0));
        cmbDikkasadada.setText("-");

        cmbWiwahayaAsarthakada.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        cmbWiwahayaAsarthakada.setForeground(new java.awt.Color(153, 51, 0));
        cmbWiwahayaAsarthakada.setText("-");

        javax.swing.GroupLayout titleWiwahakanamLayout = new javax.swing.GroupLayout(titleWiwahakanam);
        titleWiwahakanam.setLayout(titleWiwahakanamLayout);
        titleWiwahakanamLayout.setHorizontalGroup(
            titleWiwahakanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleWiwahakanamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(titleWiwahakanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(titleWiwahakanamLayout.createSequentialGroup()
                        .addGroup(titleWiwahakanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(titleWiwahakanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(titleWiwahakanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleWiwahakanamLayout.createSequentialGroup()
                                    .addComponent(cmbDikkasadada, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbNawathaWiwahaUweda, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                                .addComponent(txtSahakarugeDKA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtSahakarugeNama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSahakarugeRakiyawa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(titleWiwahakanamLayout.createSequentialGroup()
                        .addComponent(jLabel68)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbWiwahayaAsarthakada, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        titleWiwahakanamLayout.setVerticalGroup(
            titleWiwahakanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleWiwahakanamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(titleWiwahakanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(txtSahakarugeNama))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(titleWiwahakanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(txtSahakarugeRakiyawa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(titleWiwahakanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel64)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(titleWiwahakanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(txtSahakarugeDKA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 17, Short.MAX_VALUE)
                .addGroup(titleWiwahakanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(jLabel67)
                    .addComponent(cmbNawathaWiwahaUweda)
                    .addComponent(cmbDikkasadada))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(titleWiwahakanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(cmbWiwahayaAsarthakada))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout wiwahayaLayout = new javax.swing.GroupLayout(wiwahaya);
        wiwahaya.setLayout(wiwahayaLayout);
        wiwahayaLayout.setHorizontalGroup(
            wiwahayaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wiwahayaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleWiwahakanam, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        wiwahayaLayout.setVerticalGroup(
            wiwahayaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wiwahayaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleWiwahakanam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(330, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("újdyh ", wiwahaya);

        jLabel9.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel9.setText("fmd,sishg nefokúg ;k;=r ");

        jLabel10.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel10.setText("úY%du hkúg ;k;=r ");

        jLabel11.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel11.setText("ks;H fiajd wxlh ");

        jLabel12.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel12.setText("Wm fiajd wxlh ");

        jLabel14.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel14.setText("rKúre'ye wxlh ");

        jLabel15.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel15.setText("úfYaI tallhlg wkqhq;a;j isáfhao ");

        titleWEAnuyuththanam.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Iskoola Pota", 0, 14))); // NOI18N

        jLabel16.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel16.setText("úfYaI tallh ");

        jLabel17.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel17.setText("neÿk oskh ");

        jLabel18.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel18.setText("wkq LKav fyda lKavdhï wxl ");

        cmbWisheshaEkakaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        cmbWisheshaEkakaya.setForeground(new java.awt.Color(153, 0, 0));
        cmbWisheshaEkakaya.setText("-");

        dtpWEBadunaDinaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        dtpWEBadunaDinaya.setForeground(new java.awt.Color(153, 0, 0));
        dtpWEBadunaDinaya.setText("-");

        txtAnuKanda.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        txtAnuKanda.setForeground(new java.awt.Color(153, 0, 0));
        txtAnuKanda.setText("-");

        javax.swing.GroupLayout titleWEAnuyuththanamLayout = new javax.swing.GroupLayout(titleWEAnuyuththanam);
        titleWEAnuyuththanam.setLayout(titleWEAnuyuththanamLayout);
        titleWEAnuyuththanamLayout.setHorizontalGroup(
            titleWEAnuyuththanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleWEAnuyuththanamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(titleWEAnuyuththanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(titleWEAnuyuththanamLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAnuKanda, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                        .addGap(223, 223, 223))
                    .addGroup(titleWEAnuyuththanamLayout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dtpWEBadunaDinaya, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(titleWEAnuyuththanamLayout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbWisheshaEkakaya, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        titleWEAnuyuththanamLayout.setVerticalGroup(
            titleWEAnuyuththanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleWEAnuyuththanamLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(titleWEAnuyuththanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cmbWisheshaEkakaya))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(titleWEAnuyuththanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtAnuKanda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(titleWEAnuyuththanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(dtpWEBadunaDinaya))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        cmbPBadenawitaThanathura.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        cmbPBadenawitaThanathura.setForeground(new java.awt.Color(153, 0, 0));
        cmbPBadenawitaThanathura.setText("-");

        cmbWishramaYWThanathura.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        cmbWishramaYWThanathura.setForeground(new java.awt.Color(153, 0, 0));
        cmbWishramaYWThanathura.setText("-");

        txtNilaAnkaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        txtNilaAnkaya.setForeground(new java.awt.Color(153, 0, 0));
        txtNilaAnkaya.setText("-");

        txtUpasewaAnkaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        txtUpasewaAnkaya.setForeground(new java.awt.Color(153, 0, 0));
        txtUpasewaAnkaya.setText("-");

        txtRanawiruHadunumAnkaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        txtRanawiruHadunumAnkaya.setForeground(new java.awt.Color(153, 0, 0));
        txtRanawiruHadunumAnkaya.setText("-");

        cmbWEAnuyuththada.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        cmbWEAnuyuththada.setForeground(new java.awt.Color(153, 0, 0));
        cmbWEAnuyuththada.setText("-");

        javax.swing.GroupLayout thanathuraLayout = new javax.swing.GroupLayout(thanathura);
        thanathura.setLayout(thanathuraLayout);
        thanathuraLayout.setHorizontalGroup(
            thanathuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thanathuraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(thanathuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleWEAnuyuththanam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(thanathuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, thanathuraLayout.createSequentialGroup()
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbWEAnuyuththada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, thanathuraLayout.createSequentialGroup()
                            .addGroup(thanathuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(thanathuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtRanawiruHadunumAnkaya, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtUpasewaAnkaya, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNilaAnkaya, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbWishramaYWThanathura, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbPBadenawitaThanathura, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        thanathuraLayout.setVerticalGroup(
            thanathuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thanathuraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(thanathuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cmbPBadenawitaThanathura))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(thanathuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmbWishramaYWThanathura))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(thanathuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtNilaAnkaya))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(thanathuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtUpasewaAnkaya))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(thanathuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(txtRanawiruHadunumAnkaya))
                .addGap(12, 12, 12)
                .addGroup(thanathuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cmbWEAnuyuththada))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(titleWEAnuyuththanam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(292, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(";k;=r ", thanathura);

        jPanel8.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 19, 705, 625));

        jTabbedPane2.addTab("f;dr;=re ", jPanel8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        
        jTabbedPane2.setSelectedIndex(0);
        try {
            cmbSahanaya.removeAllItems();
            cmbSahanaLS.removeAllItems();
            ResultSet rs = SQLConnection.SqlConnection.getData("SELECT DISTINCT SHN,LS FROM SLS");
            while (rs.next()) {
                String SHN = rs.getString("SHN");
                String LS = rs.getString("LS");
                cmbSahanaya.addItem(SHN);
                cmbSahanaLS.addItem(LS);
            }
        } catch (Exception e) {
           new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
        }


    }//GEN-LAST:event_formComponentShown

    private void tblSeachedDataMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSeachedDataMouseReleased

        if (tblSeachedData.getSelectedRow() != -1) {
            showAllSamajikayaData();
            showAllTrasthaData();
            showAllDaruwanData();
            showAllWiwahayaData();
            showAllThanathuraData();
            showAllAbadithaUpakaranaData();
            showAllSahanaData();
            showAllMasikaWatupData();
            showAllWisramaWatup();
        }

    }//GEN-LAST:event_tblSeachedDataMouseReleased


    private void btnFillTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFillTableActionPerformed

        try {

            boolean a = cbxSahanaya.isSelected();
            boolean b = cbxSahanaLS.isSelected();
            boolean c = cbxThanathura.isSelected();

            final String r_saha = " iy ";
            final String r_a = "iykh " + cmbSahanaya.getSelectedItem().toString() + " jk";
            final String r_b = "iyk ,efnk ia:dkh " + cmbSahanaLS.getSelectedItem().toString() + " jk";
            final String r_c = ";k;=r " + cmbThanathura.getSelectedItem().toString() + " jk";
            final String r_last = " idudðlhskaf.a ,ehsia;=j";
            final String r_all = " ishÆ ";

            final String s_and = " AND ";
            final String s_where = " WHERE ";
            final String s_join_fab = " INNER JOIN SLS ON SLS.SA=SMJK.SA  ";
            final String s_join_fC = " INNER JOIN ST ON ST.SA=SMJK.SA  ";
            final String s_ex_a = " SHN='" + cmbSahanaya.getSelectedItem().toString() + "'";
            final String s_ex_b = " LS='" + cmbSahanaLS.getSelectedItem().toString() + "'";
            final String s_ex_c = " TNTR2='" + (cmbThanathura.getSelectedIndex() + 1) + "'";

            if (a == false && b == false && c == false) {
                REPORT_NAME = r_all;
                fillTable("");
            } else if (a == false && b == false && c == true) {
                REPORT_NAME = r_c;
                fillTable(s_join_fC + s_where + s_ex_c);
            } else if (a == false && b == true && c == false) {
                REPORT_NAME = r_b;
                fillTable(s_join_fab + s_where + s_ex_b);
            } else if (a == false && b == true && c == true) {
                REPORT_NAME = r_b + r_saha + r_c;
                fillTable(s_join_fC + s_join_fab + s_where + s_ex_b + s_and + s_ex_c);
            } else if (a == true && b == false && c == false) {
                REPORT_NAME = r_a;
                fillTable(s_join_fab + s_where + s_ex_a);
            } else if (a == true && b == false && c == true) {
                REPORT_NAME = r_a + r_saha + r_c;
                fillTable(s_join_fC + s_join_fab + s_where + s_ex_a + s_and + s_ex_c);
            } else if (a == true && b == true && c == false) {
                REPORT_NAME = r_a + r_saha + r_b;
                fillTable(s_join_fab + s_where + s_ex_a + s_and + s_ex_b);
            } else if (a == true && b == true && c == true) {
                REPORT_NAME = r_a + r_saha + r_b + r_saha + r_c;
                fillTable(s_join_fC + s_join_fab + s_where + s_ex_a + s_and + s_ex_b + s_and + s_ex_c);
            }
            REPORT_NAME += r_last;
        } catch (Exception e) {
           new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
        }

    }//GEN-LAST:event_btnFillTableActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed

        try {
            ReportViewer.view_Report(tblSeachedData, REPORT_NAME);
        } catch (Exception e) {
           new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
        }

    }//GEN-LAST:event_btnPrintActionPerformed

    private void tblDaruwanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDaruwanMouseReleased

    }//GEN-LAST:event_tblDaruwanMouseReleased

    private void tblAUMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAUMouseReleased

        if (tblAU.getSelectedRow() != -1) {
            DefaultTableModel tblMitaPera = (DefaultTableModel) tblAULDinayanMitaPera.getModel();
            tblMitaPera.setRowCount(0);
            int showIndexMitaPera = tblAU.getSelectedRow();
            ArrayList<String> dinayn = new ArrayList<>();
            auldMETAPERA.stream().filter((auld1) -> (auld1.getIndex() == showIndexMitaPera)).forEach((auld1) -> dinayn.add(auld1.getDinaya()));
            dinayn.forEach(d -> {
                Vector rowData = new Vector();
                rowData.add(d);
                tblMitaPera.addRow(rowData);
            });

            DefaultTableModel tblMitaPasu = (DefaultTableModel) tblAULDinayanMitaPasu.getModel();
            tblMitaPasu.setRowCount(0);
            int showIndexMitaPasu = tblAU.getSelectedRow();
            ArrayList<String> dinaynMitaPasu = new ArrayList<>();
            auldMETAPASU.stream().filter((auld1) -> (auld1.getIndex() == showIndexMitaPasu)).forEach((auld1) -> dinaynMitaPasu.add(auld1.getDinaya()));
            dinaynMitaPasu.forEach(d -> {
                Vector rowData = new Vector();
                rowData.add(d);
                tblMitaPasu.addRow(rowData);
            });
        }
    }//GEN-LAST:event_tblAUMouseReleased

    private void tblAUKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblAUKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (tblAU.getSelectedRow() != -1) {
                DefaultTableModel tblMitaPera = (DefaultTableModel) tblAULDinayanMitaPera.getModel();
                tblMitaPera.setRowCount(0);
                int showIndexMitaPera = tblAU.getSelectedRow();
                ArrayList<String> dinayn = new ArrayList<>();
                auldMETAPERA.stream().filter((auld1) -> (auld1.getIndex() == showIndexMitaPera)).forEach((auld1) -> dinayn.add(auld1.getDinaya()));
                dinayn.forEach(d -> {
                    Vector rowData = new Vector();
                    rowData.add(d);
                    tblMitaPera.addRow(rowData);
                });

                DefaultTableModel tblMitaPasu = (DefaultTableModel) tblAULDinayanMitaPasu.getModel();
                tblMitaPasu.setRowCount(0);
                int showIndexMitaPasu = tblAU.getSelectedRow();
                ArrayList<String> dinaynMitaPasu = new ArrayList<>();
                auldMETAPASU.stream().filter((auld1) -> (auld1.getIndex() == showIndexMitaPasu)).forEach((auld1) -> dinaynMitaPasu.add(auld1.getDinaya()));
                dinaynMitaPasu.forEach(d -> {
                    Vector rowData = new Vector();
                    rowData.add(d);
                    tblMitaPasu.addRow(rowData);
                });
            }
        }
    }//GEN-LAST:event_tblAUKeyReleased

    private void fillTable(String sql) {
        DefaultTableModel model = (DefaultTableModel) tblSeachedData.getModel();
        model.setRowCount(0);
        try {
            ResultSet rs = SQLConnection.SqlConnection.getData("SELECT SMJK.SA,SN,JHA FROM SMJK " + sql);
            while (rs.next()) {
                Vector row_data = new Vector();
                row_data.add(rs.getString("SA"));
                row_data.add(rs.getString("SN"));
                row_data.add(rs.getString("JHA"));
                model.addRow(row_data);
            }
            lblTotal.setText(Integer.toString(model.getRowCount()));
        } catch (Exception e) {
           new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
        }

    }

    //---------------------------------------------------------------------------------------------------------//---------------------------------------------------------------------------------------------------------
    /**/    private final ArrayList<AULDinayan> auldMETAPERA = new ArrayList<>();//---------
    /**/    private final ArrayList<AULDinayan> auldMETAPASU = new ArrayList<>();//---------
    //---------------------------------------------------------------------------------------------------------

    private String DateToString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel abadithaUpakarana;
    private javax.swing.JButton btnFillTable;
    private javax.swing.JButton btnPrint;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cbxSahanaLS;
    private javax.swing.JCheckBox cbxSahanaya;
    private javax.swing.JCheckBox cbxThanathura;
    private javax.swing.JLabel cmbDikkasadada;
    private javax.swing.JLabel cmbNawathaWiwahaUweda;
    private javax.swing.JLabel cmbPBadenawitaThanathura;
    private javax.swing.JComboBox cmbSahanaLS;
    private javax.swing.JComboBox cmbSahanaya;
    private javax.swing.JComboBox cmbThanathura;
    private javax.swing.JLabel cmbThrasthaKandayama;
    private javax.swing.JLabel cmbWEAnuyuththada;
    private javax.swing.JLabel cmbWisheshaEkakaya;
    private javax.swing.JLabel cmbWishramaYWThanathura;
    private javax.swing.JLabel cmbWiwahayaAsarthakada;
    private javax.swing.JPanel daruwan;
    private javax.swing.JLabel dtpWEBadunaDinaya;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblAwasanWarataSKKottasaya;
    private javax.swing.JLabel lblAwasanWarataSKSthanaya;
    private javax.swing.JLabel lblDOB;
    private javax.swing.JLabel lblDWWLabannedaha;
    private javax.swing.JLabel lblDWWW;
    private javax.swing.JLabel lblDina;
    private javax.swing.JLabel lblDinaW;
    private javax.swing.JLabel lblDistrikkaya;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGramaNWasama;
    private javax.swing.JLabel lblIpaimaSHPrathishathaya;
    private javax.swing.JLabel lblJangamaDurakathanaya;
    private javax.swing.JLabel lblJathikaHadunumAnkaya;
    private javax.swing.JLabel lblMWWInfor;
    private javax.swing.JLabel lblMasa;
    private javax.swing.JLabel lblMasaW;
    private javax.swing.JLabel lblMasikaWatupText;
    private javax.swing.JLabel lblMuluWatupeWatinakama;
    private javax.swing.JLabel lblOthpalada;
    private javax.swing.JLabel lblPolisWasamaSL;
    private javax.swing.JLabel lblPolisiyataBadunaDinaya;
    private javax.swing.JLabel lblPolisiyataBahune;
    private javax.swing.JLabel lblPradeshiyaLekamKottasaya;
    private javax.swing.JLabel lblPraharayaSiduwuDinaya;
    private javax.swing.JLabel lblPraharayaSiduwuKottasaya;
    private javax.swing.JLabel lblPraharayaSiduwuSthanaya;
    private javax.swing.JLabel lblSA;
    private javax.swing.JLabel lblSamajikathwayaLDinaya;
    private javax.swing.JLabel lblSampurnaNama;
    private javax.swing.JLabel lblSharirikaHani;
    private javax.swing.JLabel lblSthawaraDurakathanaya;
    private javax.swing.JTextArea lblSthiraLipinaya;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblWWLabannedaha;
    private javax.swing.JLabel lblWWW;
    private javax.swing.JLabel lblWasara;
    private javax.swing.JLabel lblWasaraW;
    private javax.swing.JLabel lblWidyaPPawathwuDinaya;
    private javax.swing.JLabel lblWidyaPPawathwuKo;
    private javax.swing.JLabel lblWidyaPPawathwuSthanaya;
    private javax.swing.JLabel lblWishramaLabuDinaya;
    private javax.swing.JPanel masukaWatup;
    private javax.swing.JPanel sahana;
    private javax.swing.JPanel samajikaya;
    private javax.swing.JTable tblAU;
    private javax.swing.JTable tblAULDinayanMitaPasu;
    private javax.swing.JTable tblAULDinayanMitaPera;
    private javax.swing.JTable tblAthuruAbada;
    private javax.swing.JTable tblDWWNHethu;
    private javax.swing.JTable tblDaruwan;
    private javax.swing.JTable tblSahana;
    private javax.swing.JTable tblSeachedData;
    private javax.swing.JTable tblWWNHethu;
    private javax.swing.JPanel thanathura;
    private javax.swing.JPanel titleAAbada;
    private javax.swing.JPanel titleAUBKarainam;
    private javax.swing.JPanel titleDWWNHethu;
    private javax.swing.JPanel titleDaruwanSitinam;
    private javax.swing.JPanel titleMWLabenam;
    private javax.swing.JPanel titleSahanaLabinama;
    private javax.swing.JPanel titleWEAnuyuththanam;
    private javax.swing.JPanel titleWWNHethu;
    private javax.swing.JPanel titleWishramaWLanannenam;
    private javax.swing.JPanel titleWiwahakanam;
    private javax.swing.JPanel trasthaPraharaya;
    private javax.swing.JLabel txtAnuKanda;
    private javax.swing.JLabel txtDimanaWalaWatinakama;
    private javax.swing.JLabel txtMulikaWatupeWatinakama;
    private javax.swing.JLabel txtNilaAnkaya;
    private javax.swing.JLabel txtRanawiruHadunumAnkaya;
    private javax.swing.JLabel txtSahakarugeDKA;
    private javax.swing.JLabel txtSahakarugeNama;
    private javax.swing.JTextArea txtSahakarugeRLipinaya;
    private javax.swing.JLabel txtSahakarugeRakiyawa;
    private javax.swing.JLabel txtUpasewaAnkaya;
    private javax.swing.JLabel txtWWAnkaya;
    private javax.swing.JLabel txtWlabanaKottasaya;
    private javax.swing.JLabel txtWlabanaPolisWasama;
    private javax.swing.JPanel wisramaWatup;
    private javax.swing.JPanel wiwahaya;
    private javax.swing.JLabel wwPradheshilaLK;
    // End of variables declaration//GEN-END:variables

}
