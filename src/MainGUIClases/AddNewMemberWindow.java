package MainGUIClases;

import OtherClases.Funtional;
import OtherClases.AULDinayan;
import OtherClases.PKGenaretor;
import OtherClases.UserInputChecker;
import SaveData.SaveAbadithaUpakarana;
import SaveData.SaveDWWNHethu;
import SaveData.SaveDWWNSamajikayin;
import SaveData.SaveDWishramaWatup;
import SaveData.SaveMWLSamajikayin;
import SaveData.SaveSDaruwan;
import SaveData.SaveSLSahana;
import SaveData.SaveSSwisheshaEkaka;
import SaveData.SaveSThanathura;
import SaveData.SaveSahakaru;
import SaveData.SaveSamajikaya;
import SaveData.SaveSthiraLipinaya;
import SaveData.SaveTrasthaPrahara;
import SaveData.SaveWWNHethu;
import SaveData.SaveWWNSamajikayin;
import SaveData.SaveWidyaPariksahanaya;
import SaveData.SaveWisramaWatup;
import datechooser.beans.DateChooserCombo;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import OtherClases.JTextFieldLimit;
import SaveData.SaveAthuruAbada;
import SaveData.SaveMitaPasuAbadithaULDinayan;
import SaveData.SaveMitaPeraAbadithaULDinayan;

public class AddNewMemberWindow extends javax.swing.JFrame {

    public AddNewMemberWindow() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        lblSA.setText(PKGenaretor.getNextPK("SMJK", "SA"));
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);

    }

    private String DateToString(DateChooserCombo dc) {
        return new SimpleDateFormat("yyyy:MM:dd").format(dc.getSelectedDate().getTime());
    }

    private void saveData() {

        //Data for SMJK-සාමාජිකයා
        String SA = lblSA.getText();
        String JHA = txtJathikaHadunumAnkaya.getText();
        String SN = txtSampurnaNama.getText();
        String PBD = DateToString(dtpPolisiyataBadunaDinaya);
        String UD = DateToString(dtpDOB);
        String AWSKS = txtAwasanWarataSKSthanaya.getText();
        String AWSKK = txtAwasanWarataSKKottasaya.getText();
        String WL = txtEmail.getText();
        String WLD = DateToString(dtpWishramaLabuDinaya);
        String NWUD = cmbNawathaWiwahaUweda.getSelectedItem().toString();
        String JDA = txtJangamaDurakathanaya.getText();
        String SDA = txtSthawaraDurakathanaya.getText();
        String DKKD = cmbDikkasadada.getSelectedItem().toString();
        String WD = cmbWiwahakada.getSelectedIndex()==0 ? "Tõ" : "ke;";
        String WAWATBD = cmbWiwahayaAsarthakada.getSelectedItem().toString();
        String NSUS = cmbPolisiyataBahune.getSelectedItem().toString();
        String SLD = DateToString(dtpSamajikathwayaLDinaya);
        new SaveSamajikaya(SA, JHA, SN, PBD, UD, AWSKS,
                AWSKK, WL, WLD, NWUD, JDA, SDA, DKKD, WD,
                WAWATBD, NSUS, SLD).save();
        //----------------------------------------------

        // TRPWLS-ත්‍රස්තවාදී ප්‍රහාර වලට ලක් වූ සමාජිකයින්
        String ISHP = Integer.toString(Integer.parseInt(cmbIpaimaSHPrathishathaya.getSelectedItem().toString().replace('%', '0')) / 10);
        String OWAD = cmbOthpalada.getSelectedItem().toString();
        String TRKA = Integer.toString(cmbThrasthaKandayama.getSelectedIndex() + 1);
        String LUD = DateToString(dtpPraharayaSiduwuDinaya);
        String LUK = txtPradeshiyaLekamKottasaya.getText();
        String ASB = cmbSharirikaHani.getSelectedItem().toString().equals("fjk;a") ? txtWenathHani.getText() : cmbSharirikaHani.getSelectedItem().toString();
        String LUS = txtPraharayaSiduwuSthanaya.getText();
        new SaveTrasthaPrahara(SA, ISHP, OWAD, TRKA, LUD, LUK, ASB, LUS).save();
        //---------------------------------------------------

        //ST- සමාජිකයාගේ තනතුර. 
        String TNTR = Integer.toString(cmbPBadenawitaThanathura.getSelectedIndex() < 2
                ? cmbPBadenawitaThanathura.getSelectedIndex() + 1
                : cmbPBadenawitaThanathura.getSelectedIndex() + 3);

        //String NA = txtNilaAnkaya.getText();
        String UA = txtUpasewaAnkaya.getText();
        String RHA = txtRanawiruHadunumAnkaya.getText();
        String NSA = txtNithyaSevaAnkaya.getText();
        String TNTR2 = Integer.toString(cmbWishramaYWThanathura.getSelectedIndex() + 1);
        new SaveSThanathura(SA, TNTR, TNTR2, UA, RHA, NSA).save();
        //-----------------------------------------------------------------

        //AASS අතුරු අබාද 
        if (getBooleanValue(cmbAthuruAAdda1.getSelectedItem().toString())) {
            for (int i = 0; i < tblAthuruAbada.getRowCount(); i++) {
                String ABD = tblAthuruAbada.getValueAt(i, 0).toString();
                new SaveAthuruAbada(SA, ABD).save();
            }
        }
        //-----------------------------

        //Data for SL-ස්ථිර ලිපිනය
        String SSL = txtSthiraLipinaya.getText();
        String GNW = txtGramaNWasama.getText();
        String PW = txtPolisWasamaSL.getText();
        String PLK = txtPradeshiyaLekamKottasaya.getText();
        String DTRK = txtDistrikkaya.getText();
        new SaveSthiraLipinaya(SA, SSL, GNW, PW, PLK, DTRK).save();
        //------------------------------------------------------------------

        if (getBooleanValue(cmbWisramaWLabanneda.getSelectedItem().toString())) {
            //Data for WW-විශ්‍රාම වැටුප
            String wwPLK = txtWWLPLKottashaya.getText();
            String wwWWA = txtWWAnkaya.getText();
            String WTNK = txtWWWatinakama.getText();
            new SaveWisramaWatup(SA, wwPLK, wwWWA, WTNK).save();
            //------------------------------------------------------------------

        } else {
            //විශ්‍රාම වැටුප් නොලැබෙන සාමාජිකයෙකි
            new SaveWWNSamajikayin(SA).save();
            //SWWNH-සමාජිකයින්ට විශ්‍රාම වැටුප් නොලැබීමට හේතු
            for (int i = 0; i < tblWWNHethu.getRowCount(); i++) {
                String HTW = tblWWNHethu.getValueAt(i, 0).toString();
                new SaveWWNHethu(SA, HTW).save();
            }
        }

        if (getBooleanValue(cmbDWWLabanneda.getSelectedItem().toString())) {
            //Data for WW-දුබලතා විශ්‍රාම වැටුප
            String dwwPLK = txtWWLPLKottashaya.getText();
            String dwwWWA = txtWWAnkaya.getText();
            String WTKM = txtDWWWatinakama.getText();
            new SaveDWishramaWatup(SA, dwwPLK, dwwWWA, WTKM).save();
            //------------------------------------------------------------------
        } else {
            //දුබලතා විශ්‍රාම වැටුප් නොලැබෙන සාමාජිකයෙකි
            new SaveDWWNSamajikayin(SA).save();
            //SDWWNH-සමාජිකයින්ට දුබලතා විශ්‍රාම වැටුප් නොලැබීමට හේතු.
            for (int i = 0; i < tblDWWNHethu.getRowCount(); i++) {
                String HTW = tblDWWNHethu.getValueAt(i, 0).toString();
                new SaveDWWNHethu(SA, HTW).save();
            }
        }

        if (cmbWiwahakada.getSelectedIndex() == 0) {
            //SS-ස්මජිකයාගේ සහකරු/සහකාරිය
            String SRL = txtSahakarugeRLipinaya.getText();
            String SSDA = txtSahakarugeDKA.getText();
            String SR = txtSahakarugeRakiyawa.getText();
            String SSN = txtSahakarugeNama.getText();
            new SaveSahakaru(SA, SRL, SSDA, SR, SSN).save();
            //------------------------------------------------------------------
        }

        //ආබාදිත උපකරණ සහ ලබාගත් දිනයන් 
        if (cbxAbadithaUBKarai.isSelected()) {
            for (int i = 0; i < tblAU.getRowCount(); i++) {
                String AUN = tblAU.getValueAt(i, 0).toString();
                String AUA = PKGenaretor.getNextPK("SAU", "UA");
                new SaveAbadithaUpakarana(SA, AUA, AUN).save();
                if (!auldMitaPera.isEmpty()) {
                    for (AULDinayan auld1 : auldMitaPera) {
                        if (auld1.getIndex() == i) {
                            String ULD = auld1.getDinaya().replace('$', ':');
                            new SaveMitaPeraAbadithaULDinayan(AUA, ULD).save();
                        }
                    }

                }

                if (!auldMitaPasu.isEmpty()) {
                    for (AULDinayan auld1 : auldMitaPasu) {
                        if (auld1.getIndex() == i) {
                            String ULD = auld1.getDinaya().replace('$', ':');
                            new SaveMitaPasuAbadithaULDinayan(AUA, ULD).save();
                        }
                    }

                }

            }
        }
            //---------------------------------------------------------------------

        //SLS-සාමාජිකයාට ලැබෙන සහන
        if (cbxSahanaLabiAtha.isSelected()) {
            for (int i = 0; i < tblSahana.getRowCount(); i++) {
                String SHA = tblSahana.getValueAt(i, 0).toString();
                String SLS = tblSahana.getValueAt(i, 1).toString();
                String SAWLD = tblSahana.getValueAt(i, 2).toString().replace('$', ':');
                new SaveSLSahana(SA, SHA, SLS, SAWLD).save();
            }
        }

        //MWLS-මාසික වැටුප් ලබන සාමාජිකයින්.-MS
        if (getBooleanValue(cmbMasukaWLabanneda.getSelectedItem().toString())) {
            String LK = txtWlabanaKottasaya.getText();
            String PS = txtWlabanaPolisWasama.getText();
            String WWT = txtMulikaWatupeWatinakama.getText();
            String DWT = txtDimanaWalaWatinakama.getText();
            //String LDAKD="NULL";
            new SaveMWLSamajikayin(SA, LK, PS, WWT, DWT).save();

        }
            //---------------------------------------------------------

        //SD-ස්මජිකයාගේ දරුවන්
        if (cbxDaruwanSiti.isSelected()) {
            for (int i = 0; i < tblDaruwan.getRowCount(); i++) {
                String dUD = tblDaruwan.getValueAt(i, 1).toString().replace('$', ':');
                String dNAMA = tblDaruwan.getValueAt(i, 0).toString();
                String DT = tblDaruwan.getValueAt(i, 2).toString();
                String ABTD = tblDaruwan.getValueAt(i, 3).toString();
                new SaveSDaruwan(SA, dUD, dNAMA, DT, ABTD).save();

            }
        }

        //සමාජිකයා සේවය කළ විශේෂ ඒකක.
        if (getBooleanValue(cmbWEAnuyuththada.getSelectedItem().toString())) {
            String WEA = Integer.toString(cmbWisheshaEkakaya.getSelectedIndex() + 1);
            String BD = DateToString(dtpWEBadunaDinaya);
            String KAAA = txtAnuKanda.getText();
            new SaveSSwisheshaEkaka(SA, WEA, BD, KAAA).save();
        }

        //සාමාජිකයාගේ වෛද්‍ය පරීක්ෂණය.
        String SKS = txtWidyaPPawathwuSthanaya.getText();
        String SKD = DateToString(dtpWidyaPPawathwuDinaya);
        String SKK = txtWidyaPPawathwuKo.getText();
        new SaveWidyaPariksahanaya(SA, SKS, SKD, SKK).save();

    }

    //Get boolean value from ChoiceBoxes--->if-ඔව්-true- or නැත-false 
    private boolean getBooleanValue(String value) {
        return value.equals("Tõ");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        PanelForm = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        lblSA = new javax.swing.JLabel();
        btnChangePK = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtSampurnaNama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtSthiraLipinaya = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbPolisiyataBahune = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        cmbPBadenawitaThanathura = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        cmbWishramaYWThanathura = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        txtUpasewaAnkaya = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtJathikaHadunumAnkaya = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtRanawiruHadunumAnkaya = new javax.swing.JTextField();
        cmbWEAnuyuththada = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        titleWEAnuyuththanam = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        cmbWisheshaEkakaya = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtAnuKanda = new javax.swing.JTextField();
        dtpWEBadunaDinaya = new datechooser.beans.DateChooserCombo();
        jLabel19 = new javax.swing.JLabel();
        txtAwasanWarataSKSthanaya = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtAwasanWarataSKKottasaya = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        cmbThrasthaKandayama = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtPraharayaSiduwuKottasaya = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtPraharayaSiduwuSthanaya = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        cmbSharirikaHani = new javax.swing.JComboBox();
        txtWenathHani = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        cmbOthpalada = new javax.swing.JComboBox();
        jLabel27 = new javax.swing.JLabel();
        titleAAbada = new javax.swing.JPanel();
        txtAthuruAbadaya = new javax.swing.JTextField();
        btnAddAthuruAbada = new javax.swing.JButton();
        btnDeleteAthuruAbada = new javax.swing.JButton();
        btnDAllAthuruAbada = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAthuruAbada = new javax.swing.JTable();
        jLabel36 = new javax.swing.JLabel();
        txtWidyaPPawathwuSthanaya = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        cmbIpaimaSHPrathishathaya = new javax.swing.JComboBox();
        dtpPraharayaSiduwuDinaya = new datechooser.beans.DateChooserCombo();
        dtpWidyaPPawathwuDinaya = new datechooser.beans.DateChooserCombo();
        jLabel35 = new javax.swing.JLabel();
        cmbAthuruAAdda1 = new javax.swing.JComboBox();
        jLabel73 = new javax.swing.JLabel();
        txtWidyaPPawathwuKo = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lblWasara = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        lblMasa = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        lblDina = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        dtpSamajikathwayaLDinaya = new datechooser.beans.DateChooserCombo();
        dtpPolisiyataBadunaDinaya = new datechooser.beans.DateChooserCombo();
        dtpWishramaLabuDinaya = new datechooser.beans.DateChooserCombo();
        jLabel39 = new javax.swing.JLabel();
        cmbMasukaWLabanneda = new javax.swing.JComboBox();
        titleMWLabenam = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        txtWlabanaKottasaya = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtWlabanaPolisWasama = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txtMulikaWatupeWatinakama = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        txtDimanaWalaWatinakama = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        lblMuluWatupeWatinakama = new javax.swing.JLabel();
        cmbWisramaWLabanneda = new javax.swing.JComboBox();
        jLabel45 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        cmbDWWLabanneda = new javax.swing.JComboBox();
        titleWishramaWLanannenam = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        txtWWAnkaya = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        txtWWWatinakama = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        txtDWWWatinakama = new javax.swing.JTextField();
        txtWWLPLKottashaya = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        titleWWNolabenam = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        cmbWWNHethuwa = new javax.swing.JComboBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblWWNHethu = new javax.swing.JTable();
        btnDAllWWNH = new javax.swing.JButton();
        btnDeleteWWNH = new javax.swing.JButton();
        btnAddWWNH = new javax.swing.JButton();
        titleDWWNHethu = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        cmbDWWNHethuwa = new javax.swing.JComboBox();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDWWNHethu = new javax.swing.JTable();
        btnDAllDWWNH = new javax.swing.JButton();
        btnDeleteDWWNH = new javax.swing.JButton();
        btnAddDWWNH = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        txtPolisWasamaSL = new javax.swing.JTextField();
        txtGramaNWasama = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        txtDistrikkaya = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        txtPradeshiyaLekamKottasaya = new javax.swing.JTextField();
        txtSthawaraDurakathanaya = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        txtJangamaDurakathanaya = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        dtpDOB = new datechooser.beans.DateChooserCombo();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        cmbWiwahakada = new javax.swing.JComboBox();
        titleWiwahakanam = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        txtSahakarugeNama = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        txtSahakarugeRakiyawa = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtSahakarugeRLipinaya = new javax.swing.JTextArea();
        cmbDikkasadada = new javax.swing.JComboBox();
        jLabel65 = new javax.swing.JLabel();
        cmbNawathaWiwahaUweda = new javax.swing.JComboBox();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        cmbWiwahayaAsarthakada = new javax.swing.JComboBox();
        txtSahakarugeDKA = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        titleDaruwanSitinam = new javax.swing.JPanel();
        txtDaruwageNama = new javax.swing.JTextField();
        cmbDaruwageDanataThathwaya = new javax.swing.JComboBox();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblDaruwan = new javax.swing.JTable();
        dtpDaruwageDOB = new datechooser.beans.DateChooserCombo();
        cmbDaruwaAbadithada = new javax.swing.JComboBox();
        btnAddDaruwa = new javax.swing.JButton();
        btnDeleteDaruwa = new javax.swing.JButton();
        btnDAllDaruwa = new javax.swing.JButton();
        cbxDaruwanSiti = new javax.swing.JCheckBox();
        cbxAbadithaUBKarai = new javax.swing.JCheckBox();
        titleAUBKarainam = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblAU = new javax.swing.JTable();
        dtpAULDinayanMitaPera = new datechooser.beans.DateChooserCombo();
        cmbAbadithaUpakarana = new javax.swing.JComboBox();
        btnAddAU = new javax.swing.JButton();
        btnDeleteAU = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblAULDinayanMitaPera = new javax.swing.JTable();
        btnAddAULDMitaPera = new javax.swing.JButton();
        btnDeleteAULDMitaPera = new javax.swing.JButton();
        txtWenathAbadithaUpakarana = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnDeleteAULDMitaPasu = new javax.swing.JButton();
        btnAddAULDMitaPasu = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblAULDinayanMitaPasu = new javax.swing.JTable();
        dtpAULDinayanMitaPasu = new datechooser.beans.DateChooserCombo();
        cbxSahanaLabiAtha = new javax.swing.JCheckBox();
        titleSahanaLabinama = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblSahana = new javax.swing.JTable();
        btnAddSahana = new javax.swing.JButton();
        btnDeleteSahana = new javax.swing.JButton();
        txtSahanaya = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnDAllSahana = new javax.swing.JButton();
        txtSahanaLabanaSthanaya = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        dtpSahanaAwasanWLDinaya = new datechooser.beans.DateChooserCombo();
        btnAddNewMember = new javax.swing.JButton();
        txtNithyaSevaAnkaya = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        lblClear = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("සාමාජික අයදුම් පත ");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jScrollPane1.setAutoscrolls(true);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel1MouseMoved(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelForm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255), 2));
        PanelForm.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                PanelFormComponentResized(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 102, 255));

        jLabel1.setFont(new java.awt.Font("FMBindumathi", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Y%S ,xld fmd,sia hqO wdndê; rKúre ix.uh");

        jLabel69.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel69.setText("සාමාජික අයදුම් පත ");

        jLabel70.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel70.setText("සාමාජික අංකය ");

        lblSA.setBackground(new java.awt.Color(255, 255, 255));
        lblSA.setFont(new java.awt.Font("Iskoola Pota", 0, 24)); // NOI18N
        lblSA.setForeground(new java.awt.Color(255, 255, 255));
        lblSA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSA.setText("1");

        btnChangePK.setBackground(new java.awt.Color(0, 153, 255));
        btnChangePK.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255)));
        btnChangePK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblSA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChangePK, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChangePK, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSA)
                    .addComponent(jLabel69))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel2.setText("idudðl;ajh ,enQ oskh ");

        txtSampurnaNama.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel4.setText("iïmq¾K ku ");

        jLabel5.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel5.setText("iaÒr ,smskh ");

        txtSthiraLipinaya.setColumns(20);
        txtSthiraLipinaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        txtSthiraLipinaya.setLineWrap(true);
        txtSthiraLipinaya.setRows(5);
        txtSthiraLipinaya.setToolTipText("");
        txtSthiraLipinaya.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtSthiraLipinaya);

        jLabel7.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel7.setText("fmd,sishg ne`ÿk oskh ");

        jLabel8.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel8.setText("fmd,sishg ne`ÿfka ");

        cmbPolisiyataBahune.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        cmbPolisiyataBahune.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ks;H fiajd ", "Wm fiajd " }));

        jLabel9.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel9.setText("fmd,sishg nef`ok úg ;k;=r ");

        cmbPBadenawitaThanathura.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        cmbPBadenawitaThanathura.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "fmd,sia fldia;dm,a ", "Wm fmd,sia mÍlaIl ", "iyldr fmd,sia wêldÍ " }));

        jLabel10.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel10.setText("úY%du hk úg ;k;=r ");

        cmbWishramaYWThanathura.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        cmbWishramaYWThanathura.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "fmd,sia fldia;dm,a ", "fmd,sia ierhka ", "Wm fmd,sia mÍlaIl", "fmd,sia mÍlaIl", "m%Odk fmd,sia mÍlaIl  ", "iyldr fmd,sia wêldÍ ", "fcHIaG fmd,sia wêldÍ", "ksfhdacH fmd,sia m;s " }));

        jLabel12.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel12.setText("Wm fiajd wxlh ");

        txtUpasewaAnkaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel13.setText("cd'ye wxlh ");

        txtJathikaHadunumAnkaya.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        txtJathikaHadunumAnkaya.setDocument(new  JTextFieldLimit(15));

        jLabel14.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel14.setText("rKúre'ye wxlh ");

        txtRanawiruHadunumAnkaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

        cmbWEAnuyuththada.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        cmbWEAnuyuththada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tõ", "ke; " }));
        cmbWEAnuyuththada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbWEAnuyuththadaActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel15.setText("úfYaI tallhlg wkqhqla;j isáfhao ");

        titleWEAnuyuththanam.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "úfYaI tallhlg wkqhqla;j isáfhakï", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FMMalithi", 0, 14))); // NOI18N

        jLabel16.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel16.setText("úfYaI tallh ");

        cmbWisheshaEkakaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        cmbWisheshaEkakaya.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "úfYaI ld¾h n,ldh ", "m%N+ wdrlaIl wxYh ", "fmd,sia wkq lxv ", "wmrdO mÍlaIK fomd¾;fïka;=j ", ";%ia;jdoS úu¾Yk tallh" }));

        jLabel17.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel17.setText("ne`ÿk oskh ");

        jLabel18.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel18.setText("wkq LKav fyda lKavdhï wxl ");

        txtAnuKanda.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

        dtpWEBadunaDinaya.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    dtpWEBadunaDinaya.setNothingAllowed(false);
    dtpWEBadunaDinaya.setCurrentNavigateIndex(0);

    javax.swing.GroupLayout titleWEAnuyuththanamLayout = new javax.swing.GroupLayout(titleWEAnuyuththanam);
    titleWEAnuyuththanam.setLayout(titleWEAnuyuththanamLayout);
    titleWEAnuyuththanamLayout.setHorizontalGroup(
        titleWEAnuyuththanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(titleWEAnuyuththanamLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(titleWEAnuyuththanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(titleWEAnuyuththanamLayout.createSequentialGroup()
                    .addComponent(jLabel18)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtAnuKanda))
                .addGroup(titleWEAnuyuththanamLayout.createSequentialGroup()
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cmbWisheshaEkakaya, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(18, 18, 18)
            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(dtpWEBadunaDinaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    titleWEAnuyuththanamLayout.setVerticalGroup(
        titleWEAnuyuththanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(titleWEAnuyuththanamLayout.createSequentialGroup()
            .addGroup(titleWEAnuyuththanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(titleWEAnuyuththanamLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(dtpWEBadunaDinaya, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, titleWEAnuyuththanamLayout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addGroup(titleWEAnuyuththanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(cmbWisheshaEkakaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17))))
            .addGap(9, 9, 9)
            .addGroup(titleWEAnuyuththanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel18)
                .addComponent(txtAnuKanda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jLabel19.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel19.setText("wjika jrg fiajh l, ia:dkh ");

    txtAwasanWarataSKSthanaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    txtAwasanWarataSKSthanaya.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtAwasanWarataSKSthanayaActionPerformed(evt);
        }
    });

    jLabel20.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel20.setText("wjika jrg fiajh l, fldÜGdYh  ");

    txtAwasanWarataSKKottasaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ";%ia;jdoS m%ydrh ms<sno úia;r ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FMMalithi", 0, 14))); // NOI18N

    cmbThrasthaKandayama.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cmbThrasthaKandayama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "t,aááB ", "fÊúmS", "m%pKav l%shd" }));
    cmbThrasthaKandayama.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cmbThrasthaKandayamaActionPerformed(evt);
        }
    });

    jLabel21.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel21.setText(";%ia;jdoS m%ydrh isÿlf,a ");

    jLabel22.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel22.setText(";%ia;jdoS m%ydrhg ,lajQ oskh ");

    jLabel23.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel23.setText(";%ia;jdoS m%ydrhg ,lajQ fldÜGdYh");

    txtPraharayaSiduwuKottasaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    jLabel24.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel24.setText(";%ia;jdoS m%ydrhg ,lajQ ia:dkh ");

    txtPraharayaSiduwuSthanaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    jLabel25.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel25.setText("wk;=ßka isÿjQ ;=jd, j, yd Ydßßl ydks j, iajNdjh ");

    cmbSharirikaHani.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cmbSharirikaHani.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "tla mdohla wysñ ", "mdo folu wysñ ", "tla w;la wysñ ", "w;a folu wysñ ", "tla weila wysñ ", "weia folu wysñ ", "tla w;la yd mdohla wysñ ", "w;la yd mdohla hk folu wysñ ", "ysig ydks jQ ", "iakdhq wdndO iys; jQ", "Y%jkdndO iys; jQ", "Wrl=yrhg fyda Worl=yrhg ydkS jQ ", "fjk;a" }));
    cmbSharirikaHani.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cmbSharirikaHaniActionPerformed(evt);
        }
    });

    txtWenathHani.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    txtWenathHani.setEnabled(false);
    txtWenathHani.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtWenathHaniActionPerformed(evt);
        }
    });

    jLabel26.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel26.setText("fjk;a ydks ");

    cmbOthpalada.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cmbOthpalada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tõ", "ke; " }));

    jLabel27.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel27.setText("fuu YdÍßl ydks fya;=fjka fjk;a w;=re wdndOhkag ,laj ;sfío");

    titleAAbada.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "w;=re wndO we;akï", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FMMalithi", 0, 14))); // NOI18N

    txtAthuruAbadaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    btnAddAthuruAbada.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnAddAthuruAbada.setText("එකතු කරන්න ");
    btnAddAthuruAbada.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAddAthuruAbadaActionPerformed(evt);
        }
    });

    btnDeleteAthuruAbada.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnDeleteAthuruAbada.setText("ඉවත් කරන්න ");
    btnDeleteAthuruAbada.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDeleteAthuruAbadaActionPerformed(evt);
        }
    });

    btnDAllAthuruAbada.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnDAllAthuruAbada.setText("සියල්ල ඉවත් කරන්න ");
    btnDAllAthuruAbada.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDAllAthuruAbadaActionPerformed(evt);
        }
    });

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
    jScrollPane3.setViewportView(tblAthuruAbada);
    tblAthuruAbada.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

    javax.swing.GroupLayout titleAAbadaLayout = new javax.swing.GroupLayout(titleAAbada);
    titleAAbada.setLayout(titleAAbadaLayout);
    titleAAbadaLayout.setHorizontalGroup(
        titleAAbadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(titleAAbadaLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(titleAAbadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtAthuruAbadaya)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleAAbadaLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnDAllAthuruAbada)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnDeleteAthuruAbada)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnAddAthuruAbada)))
            .addContainerGap())
    );
    titleAAbadaLayout.setVerticalGroup(
        titleAAbadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(titleAAbadaLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(txtAthuruAbadaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(titleAAbadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnAddAthuruAbada)
                .addComponent(btnDeleteAthuruAbada)
                .addComponent(btnDAllAthuruAbada))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 8, Short.MAX_VALUE))
    );

    jLabel36.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel36.setText("ffjoH mßlaIK uKav,h meje;ajQ oskh ");

    txtWidyaPPawathwuSthanaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    jLabel37.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel37.setText("ffjoH mßlaIK uKav,h meje;ajQ ia:dkh ");

    jLabel38.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel38.setText("bmehSfï Yla;sh ysk jQ m%;sY;h ");

    cmbIpaimaSHPrathishathaya.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    cmbIpaimaSHPrathishathaya.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "5%", "10%", "15%", "20%", "25%", "30%", "35%", "40%", "45%", "50%", "55%", "60%", "65%", "70%", "75%", "80%", "85%", "90%", "95%", "100%" }));

    dtpPraharayaSiduwuDinaya.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
        new datechooser.view.appearance.ViewAppearance("custom",
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                true,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 255),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(128, 128, 128),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(255, 0, 0),
                false,
                false,
                new datechooser.view.appearance.swing.ButtonPainter()),
            (datechooser.view.BackRenderer)null,
            false,
            true)));
dtpPraharayaSiduwuDinaya.setNothingAllowed(false);
dtpPraharayaSiduwuDinaya.setCurrentNavigateIndex(0);

dtpWidyaPPawathwuDinaya.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
    new datechooser.view.appearance.ViewAppearance("custom",
        new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
            new java.awt.Color(0, 0, 0),
            new java.awt.Color(0, 0, 255),
            false,
            true,
            new datechooser.view.appearance.swing.ButtonPainter()),
        new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
            new java.awt.Color(0, 0, 0),
            new java.awt.Color(0, 0, 255),
            true,
            true,
            new datechooser.view.appearance.swing.ButtonPainter()),
        new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
            new java.awt.Color(0, 0, 255),
            new java.awt.Color(0, 0, 255),
            false,
            true,
            new datechooser.view.appearance.swing.ButtonPainter()),
        new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
            new java.awt.Color(128, 128, 128),
            new java.awt.Color(0, 0, 255),
            false,
            true,
            new datechooser.view.appearance.swing.LabelPainter()),
        new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
            new java.awt.Color(0, 0, 0),
            new java.awt.Color(0, 0, 255),
            false,
            true,
            new datechooser.view.appearance.swing.LabelPainter()),
        new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
            new java.awt.Color(0, 0, 0),
            new java.awt.Color(255, 0, 0),
            false,
            false,
            new datechooser.view.appearance.swing.ButtonPainter()),
        (datechooser.view.BackRenderer)null,
        false,
        true)));
dtpWidyaPPawathwuDinaya.setNothingAllowed(false);
dtpWidyaPPawathwuDinaya.setCurrentNavigateIndex(0);

jLabel35.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
jLabel35.setText("isÿ ù we;s ydks fya;=fjka iodld,slj frdao mqgqjlg fyda .s,ka weolg iSudù isào ");

cmbAthuruAAdda1.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
cmbAthuruAAdda1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tõ", "ke; " }));
cmbAthuruAAdda1.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
    cmbAthuruAAdda1ActionPerformed(evt);
    }
    });

    jLabel73.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel73.setText("ffjoH mßlaIK uKav,h meje;ajQ fldÜGdYh");

    txtWidyaPPawathwuKo.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtPraharayaSiduwuKottasaya)
                        .addComponent(txtPraharayaSiduwuSthanaya)
                        .addComponent(dtpPraharayaSiduwuDinaya, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(347, 347, 347))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(cmbIpaimaSHPrathishathaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(597, 597, 597))
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(cmbAthuruAAdda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cmbOthpalada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtWidyaPPawathwuKo))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dtpWidyaPPawathwuDinaya, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtWidyaPPawathwuSthanaya, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbThrasthaKandayama, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbSharirikaHani, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtWenathHani)))
                        .addComponent(titleAAbada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
    );
    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel21)
                .addComponent(cmbThrasthaKandayama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(dtpPraharayaSiduwuDinaya, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel23)
                .addComponent(txtPraharayaSiduwuKottasaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel24)
                .addComponent(txtPraharayaSiduwuSthanaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel25)
                .addComponent(cmbSharirikaHani, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel26)
                .addComponent(txtWenathHani, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(10, 10, 10)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel35)
                .addComponent(cmbOthpalada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel27)
                .addComponent(cmbAthuruAAdda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(titleAAbada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(dtpWidyaPPawathwuDinaya, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel37)
                .addComponent(txtWidyaPPawathwuSthanaya, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel73)
                .addComponent(txtWidyaPPawathwuKo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel38)
                .addComponent(cmbIpaimaSHPrathishathaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(14, Short.MAX_VALUE))
    );

    jLabel29.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel29.setText("úY%du ,enQ oskg fiajd ld,h  ");

    jLabel30.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel30.setText("jir  ");

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

    jLabel28.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel28.setText("úY%du ,enQ oskh ");

    dtpSamajikathwayaLDinaya.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
        new datechooser.view.appearance.ViewAppearance("custom",
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                true,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 255),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(128, 128, 128),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(255, 0, 0),
                false,
                false,
                new datechooser.view.appearance.swing.ButtonPainter()),
            (datechooser.view.BackRenderer)null,
            false,
            true)));
dtpSamajikathwayaLDinaya.setNothingAllowed(false);
dtpSamajikathwayaLDinaya.setFormat(1);

dtpPolisiyataBadunaDinaya.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
    new datechooser.view.appearance.ViewAppearance("custom",
        new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
            new java.awt.Color(0, 0, 0),
            new java.awt.Color(0, 0, 255),
            false,
            true,
            new datechooser.view.appearance.swing.ButtonPainter()),
        new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
            new java.awt.Color(0, 0, 0),
            new java.awt.Color(0, 0, 255),
            true,
            true,
            new datechooser.view.appearance.swing.ButtonPainter()),
        new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
            new java.awt.Color(0, 0, 255),
            new java.awt.Color(0, 0, 255),
            false,
            true,
            new datechooser.view.appearance.swing.ButtonPainter()),
        new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
            new java.awt.Color(128, 128, 128),
            new java.awt.Color(0, 0, 255),
            false,
            true,
            new datechooser.view.appearance.swing.LabelPainter()),
        new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
            new java.awt.Color(0, 0, 0),
            new java.awt.Color(0, 0, 255),
            false,
            true,
            new datechooser.view.appearance.swing.LabelPainter()),
        new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
            new java.awt.Color(0, 0, 0),
            new java.awt.Color(255, 0, 0),
            false,
            false,
            new datechooser.view.appearance.swing.ButtonPainter()),
        (datechooser.view.BackRenderer)null,
        false,
        true)));
dtpPolisiyataBadunaDinaya.setNothingAllowed(false);
dtpPolisiyataBadunaDinaya.setCurrentNavigateIndex(0);

dtpWishramaLabuDinaya.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
new datechooser.view.appearance.ViewAppearance("custom",
    new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
        new java.awt.Color(0, 0, 0),
        new java.awt.Color(0, 0, 255),
        false,
        true,
        new datechooser.view.appearance.swing.ButtonPainter()),
    new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
        new java.awt.Color(0, 0, 0),
        new java.awt.Color(0, 0, 255),
        true,
        true,
        new datechooser.view.appearance.swing.ButtonPainter()),
    new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
        new java.awt.Color(0, 0, 255),
        new java.awt.Color(0, 0, 255),
        false,
        true,
        new datechooser.view.appearance.swing.ButtonPainter()),
    new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
        new java.awt.Color(128, 128, 128),
        new java.awt.Color(0, 0, 255),
        false,
        true,
        new datechooser.view.appearance.swing.LabelPainter()),
    new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
        new java.awt.Color(0, 0, 0),
        new java.awt.Color(0, 0, 255),
        false,
        true,
        new datechooser.view.appearance.swing.LabelPainter()),
    new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
        new java.awt.Color(0, 0, 0),
        new java.awt.Color(255, 0, 0),
        false,
        false,
        new datechooser.view.appearance.swing.ButtonPainter()),
    (datechooser.view.BackRenderer)null,
    false,
    true)));
    dtpWishramaLabuDinaya.setNothingAllowed(false);
    dtpWishramaLabuDinaya.setCurrentNavigateIndex(0);
    dtpWishramaLabuDinaya.addCommitListener(new datechooser.events.CommitListener() {
        public void onCommit(datechooser.events.CommitEvent evt) {
            dtpWishramaLabuDinayaOnCommit(evt);
        }
    });

    jLabel39.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel39.setText("udisl jegqma yd osukd ,nkafkao ");

    cmbMasukaWLabanneda.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cmbMasukaWLabanneda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tõ", "ke; " }));
    cmbMasukaWLabanneda.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cmbMasukaWLabannedaActionPerformed(evt);
        }
    });

    titleMWLabenam.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "udisl jegqma yd osukd ,nkafka kï ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FMMalithi", 0, 14))); // NOI18N

    jLabel40.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel40.setText("jegqma ,nk fldÜGdYh");

    txtWlabanaKottasaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    jLabel41.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel41.setText("fmd,sia ia:dkh  ");

    txtWlabanaPolisWasama.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    jLabel42.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel42.setText("uQ,sl jegqm ");

    txtMulikaWatupeWatinakama.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    txtMulikaWatupeWatinakama.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            txtMulikaWatupeWatinakamaKeyReleased(evt);
        }
    });

    jLabel43.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel43.setText("oSukd j, tl;=j ");

    txtDimanaWalaWatinakama.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    txtDimanaWalaWatinakama.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            txtDimanaWalaWatinakamaKeyReleased(evt);
        }
    });

    jLabel44.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel44.setText("uq¨ jegqm");

    lblMuluWatupeWatinakama.setFont(new java.awt.Font("Iskoola Pota", 3, 14)); // NOI18N
    lblMuluWatupeWatinakama.setForeground(new java.awt.Color(204, 0, 204));
    lblMuluWatupeWatinakama.setText("????");

    javax.swing.GroupLayout titleMWLabenamLayout = new javax.swing.GroupLayout(titleMWLabenam);
    titleMWLabenam.setLayout(titleMWLabenamLayout);
    titleMWLabenamLayout.setHorizontalGroup(
        titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(titleMWLabenamLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(titleMWLabenamLayout.createSequentialGroup()
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblMuluWatupeWatinakama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(330, 330, 330))
                .addGroup(titleMWLabenamLayout.createSequentialGroup()
                    .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(titleMWLabenamLayout.createSequentialGroup()
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtWlabanaKottasaya))
                        .addGroup(titleMWLabenamLayout.createSequentialGroup()
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtMulikaWatupeWatinakama, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtWlabanaPolisWasama, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDimanaWalaWatinakama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(30, 30, 30))))
    );
    titleMWLabenamLayout.setVerticalGroup(
        titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(titleMWLabenamLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(titleMWLabenamLayout.createSequentialGroup()
                        .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(txtWlabanaKottasaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(txtMulikaWatupeWatinakama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(titleMWLabenamLayout.createSequentialGroup()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel43)))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleMWLabenamLayout.createSequentialGroup()
                    .addComponent(txtWlabanaPolisWasama, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDimanaWalaWatinakama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel44)
                .addComponent(lblMuluWatupeWatinakama))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    cmbWisramaWLabanneda.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cmbWisramaWLabanneda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tõ", "ke; " }));
    cmbWisramaWLabanneda.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cmbWisramaWLabannedaActionPerformed(evt);
        }
    });

    jLabel45.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel45.setText("úY%du jegqma ,nkafkao ");

    jLabel47.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel47.setText("ÿn,;d úY%du jegqma ,nkafkao ");

    cmbDWWLabanneda.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cmbDWWLabanneda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tõ", "ke; " }));
    cmbDWWLabanneda.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cmbDWWLabannedaActionPerformed(evt);
        }
    });

    titleWishramaWLanannenam.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "úY%du jegqma ,nkafkakï ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FMMalithi", 0, 14))); // NOI18N

    jLabel48.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel48.setText("úY%du jegqma wxlh ");

    txtWWAnkaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    jLabel50.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel50.setText("úY%du jegqm  ");

    txtWWWatinakama.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    jLabel51.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel51.setText("ÿn,;d úY%du jegqm ");

    txtDWWWatinakama.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    txtWWLPLKottashaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    jLabel49.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel49.setText("úY%du jegqma ,nd.kakd m%dfoaYSh f,alï fldÜGdYh ");

    javax.swing.GroupLayout titleWishramaWLanannenamLayout = new javax.swing.GroupLayout(titleWishramaWLanannenam);
    titleWishramaWLanannenam.setLayout(titleWishramaWLanannenamLayout);
    titleWishramaWLanannenamLayout.setHorizontalGroup(
        titleWishramaWLanannenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(titleWishramaWLanannenamLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(titleWishramaWLanannenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(titleWishramaWLanannenamLayout.createSequentialGroup()
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtWWWatinakama, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(txtDWWWatinakama, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(titleWishramaWLanannenamLayout.createSequentialGroup()
                    .addGroup(titleWishramaWLanannenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, titleWishramaWLanannenamLayout.createSequentialGroup()
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtWWAnkaya))
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                    .addComponent(txtWWLPLKottashaya, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(75, 75, 75))
    );
    titleWishramaWLanannenamLayout.setVerticalGroup(
        titleWishramaWLanannenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(titleWishramaWLanannenamLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(titleWishramaWLanannenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel48)
                .addComponent(txtWWAnkaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(titleWishramaWLanannenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel49)
                .addComponent(txtWWLPLKottashaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(titleWishramaWLanannenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel50)
                .addComponent(txtWWWatinakama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel51)
                .addComponent(txtDWWWatinakama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(20, 20, 20))
    );

    titleWWNolabenam.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "úY%du jegqma fkd,nkafka kï", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FMMalithi", 0, 14))); // NOI18N
    titleWWNolabenam.setEnabled(false);

    jLabel52.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel52.setText("fya;=j ");
    jLabel52.setEnabled(false);

    cmbWWNHethuwa.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cmbWWNHethuwa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "fiajd ld,h jir 10 wvqùu ", "fmdÿ ksfhda. fkd,eîu ", "ffjoH mÍlaIK uKav, jd¾;d wia;dk.; ùu", "úkh fya;= u; ", "isoaêhg wod, f,aLk wia:dk.; ùu  " }));
    cmbWWNHethuwa.setEnabled(false);

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
    jScrollPane4.setViewportView(tblWWNHethu);

    btnDAllWWNH.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnDAllWWNH.setText("සියල්ල ඉවත් කරන්න ");
    btnDAllWWNH.setEnabled(false);
    btnDAllWWNH.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDAllWWNHActionPerformed(evt);
        }
    });

    btnDeleteWWNH.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnDeleteWWNH.setText("ඉවත් කරන්න ");
    btnDeleteWWNH.setEnabled(false);
    btnDeleteWWNH.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDeleteWWNHActionPerformed(evt);
        }
    });

    btnAddWWNH.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnAddWWNH.setText("එකතු කරන්න ");
    btnAddWWNH.setEnabled(false);
    btnAddWWNH.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAddWWNHActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout titleWWNolabenamLayout = new javax.swing.GroupLayout(titleWWNolabenam);
    titleWWNolabenam.setLayout(titleWWNolabenamLayout);
    titleWWNolabenamLayout.setHorizontalGroup(
        titleWWNolabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(titleWWNolabenamLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(titleWWNolabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane4)
                .addGroup(titleWWNolabenamLayout.createSequentialGroup()
                    .addComponent(jLabel52)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cmbWWNHethuwa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleWWNolabenamLayout.createSequentialGroup()
                    .addComponent(btnDAllWWNH)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnDeleteWWNH)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnAddWWNH)))
            .addContainerGap())
    );
    titleWWNolabenamLayout.setVerticalGroup(
        titleWWNolabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(titleWWNolabenamLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(titleWWNolabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel52)
                .addComponent(cmbWWNHethuwa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(titleWWNolabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnAddWWNH)
                .addComponent(btnDeleteWWNH)
                .addComponent(btnDAllWWNH))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    titleDWWNHethu.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ÿn,;d úY%du jegqma fkd,nkafka kï", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FMMalithi", 0, 14))); // NOI18N
    titleDWWNHethu.setEnabled(false);

    jLabel53.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel53.setText("fya;=j ");
    jLabel53.setEnabled(false);

    cmbDWWNHethuwa.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cmbDWWNHethuwa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "fmdÿ ksfhda. fkd,eîu ", "ffjoH mÍlaIK uKav, jd¾;d wia;dk.; ùu", "bmhSfï Yla;sh 20] wvqùu" }));
    cmbDWWNHethuwa.setEnabled(false);

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
    jScrollPane5.setViewportView(tblDWWNHethu);

    btnDAllDWWNH.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnDAllDWWNH.setText("සියල්ල ඉවත් කරන්න ");
    btnDAllDWWNH.setEnabled(false);
    btnDAllDWWNH.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDAllDWWNHActionPerformed(evt);
        }
    });

    btnDeleteDWWNH.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnDeleteDWWNH.setText("ඉවත් කරන්න ");
    btnDeleteDWWNH.setEnabled(false);
    btnDeleteDWWNH.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDeleteDWWNHActionPerformed(evt);
        }
    });

    btnAddDWWNH.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnAddDWWNH.setText("එකතු කරන්න ");
    btnAddDWWNH.setEnabled(false);
    btnAddDWWNH.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAddDWWNHActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout titleDWWNHethuLayout = new javax.swing.GroupLayout(titleDWWNHethu);
    titleDWWNHethu.setLayout(titleDWWNHethuLayout);
    titleDWWNHethuLayout.setHorizontalGroup(
        titleDWWNHethuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(titleDWWNHethuLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(titleDWWNHethuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane5)
                .addGroup(titleDWWNHethuLayout.createSequentialGroup()
                    .addComponent(jLabel53)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cmbDWWNHethuwa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleDWWNHethuLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnDAllDWWNH)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnDeleteDWWNH)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnAddDWWNH)))
            .addContainerGap())
    );
    titleDWWNHethuLayout.setVerticalGroup(
        titleDWWNHethuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(titleDWWNHethuLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(titleDWWNHethuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel53)
                .addComponent(cmbDWWNHethuwa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(titleDWWNHethuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnAddDWWNH)
                .addComponent(btnDeleteDWWNH)
                .addComponent(btnDAllDWWNH))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "iaÒr ,smskhg wod, ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FMMalithi", 0, 14))); // NOI18N

    jLabel54.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel54.setText("fmd,sia jiu ");

    txtPolisWasamaSL.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    txtGramaNWasama.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    jLabel55.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel55.setText(".%du ks,OdÍ jiu ");

    jLabel56.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel56.setText("osia;%Slalh ");

    txtDistrikkaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    jLabel57.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel57.setText("m%dfoaYSh f,alï fldÜGdYh");

    txtPradeshiyaLekamKottasaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
    jPanel11.setLayout(jPanel11Layout);
    jPanel11Layout.setHorizontalGroup(
        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel11Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(4, 4, 4)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(txtPolisWasamaSL, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                .addComponent(txtPradeshiyaLekamKottasaya))
            .addGap(18, 18, 18)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addComponent(jLabel56)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDistrikkaya, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(txtGramaNWasama, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel11Layout.setVerticalGroup(
        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel11Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel54)
                .addComponent(txtPolisWasamaSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel55)
                .addComponent(txtGramaNWasama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel57)
                .addComponent(txtPradeshiyaLekamKottasaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel56)
                .addComponent(txtDistrikkaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    txtSthawaraDurakathanaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    jLabel58.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel58.setText("ia:djr ÿrl:k wxlh ");

    jLabel59.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel59.setText("cx.u ÿrl:k wxlh ");

    txtJangamaDurakathanaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    txtEmail.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N

    jLabel60.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel60.setText("úoHq;a ,smskh ");

    dtpDOB.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
        new datechooser.view.appearance.ViewAppearance("custom",
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                true,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 255),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(128, 128, 128),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(255, 0, 0),
                false,
                false,
                new datechooser.view.appearance.swing.ButtonPainter()),
            (datechooser.view.BackRenderer)null,
            false,
            true)));
dtpDOB.setNothingAllowed(false);
dtpDOB.setCurrentNavigateIndex(0);

jLabel61.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
jLabel61.setText("Wmka oskh ");

jLabel62.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
jLabel62.setText("újdyl wújdyl nj ");

cmbWiwahakada.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
cmbWiwahakada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "újdylhs ", "wújdylhs" }));
cmbWiwahakada.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmbWiwahakadaActionPerformed(evt);
    }
    });

    titleWiwahakanam.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "újylkï ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FMMalithi", 0, 14))); // NOI18N

    jLabel63.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel63.setText("ìßof.a$iajdñ mqreIhdf.a ku ");

    txtSahakarugeNama.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    jLabel64.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel64.setText("ìßof.a$iajdñ mqreIhdf.a rdcldÍ ,smskh ");

    jLabel66.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel66.setText("ìßof.a$iajdñ mqreIhdf.a /lshdj ");

    txtSahakarugeRakiyawa.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    txtSahakarugeRLipinaya.setColumns(20);
    txtSahakarugeRLipinaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    txtSahakarugeRLipinaya.setRows(5);
    jScrollPane6.setViewportView(txtSahakarugeRLipinaya);

    cmbDikkasadada.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cmbDikkasadada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tõ", "ke; " }));

    jLabel65.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel65.setText("oslalido jQfhao hk nj ");

    cmbNawathaWiwahaUweda.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cmbNawathaWiwahaUweda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tõ", "ke; " }));

    jLabel67.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel67.setText("kej; újdy jQfhao hk nj");

    jLabel68.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel68.setText("újdyh wid¾:l ùug wdndê; ;;ajh n,mEfõo ");

    cmbWiwahayaAsarthakada.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cmbWiwahayaAsarthakada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tõ", "ke; " }));

    txtSahakarugeDKA.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    jLabel71.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel71.setText("ìßof.a$iajdñ mqreIhdf.a ÿrl:k wxlh ");

    javax.swing.GroupLayout titleWiwahakanamLayout = new javax.swing.GroupLayout(titleWiwahakanam);
    titleWiwahakanam.setLayout(titleWiwahakanamLayout);
    titleWiwahakanamLayout.setHorizontalGroup(
        titleWiwahakanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(titleWiwahakanamLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(titleWiwahakanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(titleWiwahakanamLayout.createSequentialGroup()
                    .addGroup(titleWiwahakanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(titleWiwahakanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jLabel71)
                        .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(titleWiwahakanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(titleWiwahakanamLayout.createSequentialGroup()
                            .addComponent(cmbDikkasadada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(cmbNawathaWiwahaUweda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtSahakarugeDKA)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                        .addComponent(txtSahakarugeRakiyawa)
                        .addComponent(txtSahakarugeNama)))
                .addGroup(titleWiwahakanamLayout.createSequentialGroup()
                    .addComponent(jLabel68)
                    .addGap(36, 36, 36)
                    .addComponent(cmbWiwahayaAsarthakada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    titleWiwahakanamLayout.setVerticalGroup(
        titleWiwahakanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(titleWiwahakanamLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(titleWiwahakanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel63)
                .addComponent(txtSahakarugeNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(titleWiwahakanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel66)
                .addComponent(txtSahakarugeRakiyawa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(9, 9, 9)
            .addGroup(titleWiwahakanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel64)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(titleWiwahakanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel71)
                .addComponent(txtSahakarugeDKA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE)
            .addGroup(titleWiwahakanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel65)
                .addComponent(cmbDikkasadada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel67)
                .addComponent(cmbNawathaWiwahaUweda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(titleWiwahakanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel68)
                .addComponent(cmbWiwahayaAsarthakada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    titleDaruwanSitinam.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "orejka iïnkao f;dr;=re ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FMMalithi", 0, 14))); // NOI18N

    txtDaruwageNama.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    cmbDaruwageDanataThathwaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cmbDaruwageDanataThathwaya.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mdi,a hk ", "Wiia wOHdmkhg fhduq jQ ", "/lshd lrk ", "/lshd úrys; " }));

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
    jScrollPane7.setViewportView(tblDaruwan);
    if (tblDaruwan.getColumnModel().getColumnCount() > 0) {
        tblDaruwan.getColumnModel().getColumn(0).setResizable(false);
        tblDaruwan.getColumnModel().getColumn(0).setPreferredWidth(222);
        tblDaruwan.getColumnModel().getColumn(1).setResizable(false);
        tblDaruwan.getColumnModel().getColumn(1).setPreferredWidth(146);
        tblDaruwan.getColumnModel().getColumn(2).setResizable(false);
        tblDaruwan.getColumnModel().getColumn(2).setPreferredWidth(140);
        tblDaruwan.getColumnModel().getColumn(3).setResizable(false);
        tblDaruwan.getColumnModel().getColumn(3).setPreferredWidth(140);
    }

    dtpDaruwageDOB.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
        new datechooser.view.appearance.ViewAppearance("custom",
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                true,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 255),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(128, 128, 128),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(255, 0, 0),
                false,
                false,
                new datechooser.view.appearance.swing.ButtonPainter()),
            (datechooser.view.BackRenderer)null,
            false,
            true)));
dtpDaruwageDOB.setNothingAllowed(false);
dtpDaruwageDOB.setCurrentNavigateIndex(0);

cmbDaruwaAbadithada.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
cmbDaruwaAbadithada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tõ", "ke; " }));

btnAddDaruwa.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
btnAddDaruwa.setText("එකතු කරන්න ");
btnAddDaruwa.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnAddDaruwaActionPerformed(evt);
    }
    });

    btnDeleteDaruwa.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnDeleteDaruwa.setText("ඉවත් කරන්න ");
    btnDeleteDaruwa.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDeleteDaruwaActionPerformed(evt);
        }
    });

    btnDAllDaruwa.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnDAllDaruwa.setText("සියල්ල ඉවත් කරන්න ");
    btnDAllDaruwa.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDAllDaruwaActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout titleDaruwanSitinamLayout = new javax.swing.GroupLayout(titleDaruwanSitinam);
    titleDaruwanSitinam.setLayout(titleDaruwanSitinamLayout);
    titleDaruwanSitinamLayout.setHorizontalGroup(
        titleDaruwanSitinamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(titleDaruwanSitinamLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(titleDaruwanSitinamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane7)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleDaruwanSitinamLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnDAllDaruwa)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnDeleteDaruwa)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnAddDaruwa))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleDaruwanSitinamLayout.createSequentialGroup()
                    .addComponent(txtDaruwageNama)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(dtpDaruwageDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(cmbDaruwageDanataThathwaya, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cmbDaruwaAbadithada, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())
    );
    titleDaruwanSitinamLayout.setVerticalGroup(
        titleDaruwanSitinamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(titleDaruwanSitinamLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(titleDaruwanSitinamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleDaruwanSitinamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbDaruwageDanataThathwaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbDaruwaAbadithada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(titleDaruwanSitinamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dtpDaruwageDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDaruwageNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(12, 12, 12)
            .addGroup(titleDaruwanSitinamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnAddDaruwa)
                .addComponent(btnDeleteDaruwa)
                .addComponent(btnDAllDaruwa))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    cbxDaruwanSiti.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cbxDaruwanSiti.setSelected(true);
    cbxDaruwanSiti.setText("orejka isà ");
    cbxDaruwanSiti.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cbxDaruwanSitiActionPerformed(evt);
        }
    });

    cbxAbadithaUBKarai.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cbxAbadithaUBKarai.setSelected(true);
    cbxAbadithaUBKarai.setText("wdndê; WmlrK Ndú;d lSÍug isÿfõ");
    cbxAbadithaUBKarai.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cbxAbadithaUBKaraiActionPerformed(evt);
        }
    });

    titleAUBKarainam.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "tfia kï Ndú;d lrk wdndê; Wmlrk iïnkao f;dr;=re ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FMMalithi", 0, 14))); // NOI18N

    tblAU.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    tblAU.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "ආබාධිත උපකරණ "
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
    tblAU.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

    dtpAULDinayanMitaPera.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
        new datechooser.view.appearance.ViewAppearance("custom",
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                true,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 255),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(128, 128, 128),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(255, 0, 0),
                false,
                false,
                new datechooser.view.appearance.swing.ButtonPainter()),
            (datechooser.view.BackRenderer)null,
            false,
            true)));
dtpAULDinayanMitaPera.setNothingAllowed(false);
dtpAULDinayanMitaPera.setCurrentNavigateIndex(0);

cmbAbadithaUpakarana.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
cmbAbadithaUpakarana.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "w;ajdre ", "frdaomqgq", ".s,ka weoka ", "jdhq fyda j;=r fuÜg ", "weia ldkakdä ", "Y%jkdOdr ", "lD;Su mdo ", "fjk;a" }));
cmbAbadithaUpakarana.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmbAbadithaUpakaranaActionPerformed(evt);
    }
    });

    btnAddAU.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnAddAU.setText("එකතු කරන්න ");
    btnAddAU.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAddAUActionPerformed(evt);
        }
    });

    btnDeleteAU.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnDeleteAU.setText("ඉවත් කරන්න ");
    btnDeleteAU.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDeleteAUActionPerformed(evt);
        }
    });

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
    jScrollPane9.setViewportView(tblAULDinayanMitaPera);
    tblAULDinayanMitaPera.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

    btnAddAULDMitaPera.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnAddAULDMitaPera.setText("එකතු කරන්න ");
    btnAddAULDMitaPera.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAddAULDMitaPeraActionPerformed(evt);
        }
    });

    btnDeleteAULDMitaPera.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnDeleteAULDMitaPera.setText("ඉවත් කරන්න ");
    btnDeleteAULDMitaPera.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDeleteAULDMitaPeraActionPerformed(evt);
        }
    });

    txtWenathAbadithaUpakarana.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    txtWenathAbadithaUpakarana.setEnabled(false);

    jLabel3.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel3.setText("fjk;a");

    btnDeleteAULDMitaPasu.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnDeleteAULDMitaPasu.setText("ඉවත් කරන්න ");
    btnDeleteAULDMitaPasu.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDeleteAULDMitaPasuActionPerformed(evt);
        }
    });

    btnAddAULDMitaPasu.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnAddAULDMitaPasu.setText("එකතු කරන්න ");
    btnAddAULDMitaPasu.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAddAULDMitaPasuActionPerformed(evt);
        }
    });

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
    jScrollPane11.setViewportView(tblAULDinayanMitaPasu);
    tblAULDinayanMitaPasu.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

    dtpAULDinayanMitaPasu.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
        new datechooser.view.appearance.ViewAppearance("custom",
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                true,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 255),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(128, 128, 128),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(255, 0, 0),
                false,
                false,
                new datechooser.view.appearance.swing.ButtonPainter()),
            (datechooser.view.BackRenderer)null,
            false,
            true)));
dtpAULDinayanMitaPasu.setNothingAllowed(false);
dtpAULDinayanMitaPasu.setCurrentNavigateIndex(0);

javax.swing.GroupLayout titleAUBKarainamLayout = new javax.swing.GroupLayout(titleAUBKarainam);
titleAUBKarainam.setLayout(titleAUBKarainamLayout);
titleAUBKarainamLayout.setHorizontalGroup(
    titleAUBKarainamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleAUBKarainamLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(titleAUBKarainamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(cmbAbadithaUpakarana, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, titleAUBKarainamLayout.createSequentialGroup()
                .addGroup(titleAUBKarainamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtpAULDinayanMitaPasu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleAUBKarainamLayout.createSequentialGroup()
                        .addComponent(btnDeleteAULDMitaPasu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAddAULDMitaPasu)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(titleAUBKarainamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtpAULDinayanMitaPera, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleAUBKarainamLayout.createSequentialGroup()
                        .addComponent(btnDeleteAULDMitaPera)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAddAULDMitaPera))))
            .addGroup(titleAUBKarainamLayout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtWenathAbadithaUpakarana))
            .addGroup(titleAUBKarainamLayout.createSequentialGroup()
                .addGap(0, 436, Short.MAX_VALUE)
                .addComponent(btnDeleteAU)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddAU)))
        .addGap(43, 43, 43))
    );
    titleAUBKarainamLayout.setVerticalGroup(
        titleAUBKarainamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(titleAUBKarainamLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(cmbAbadithaUpakarana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(titleAUBKarainamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtWenathAbadithaUpakarana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3))
            .addGap(18, 18, 18)
            .addGroup(titleAUBKarainamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnAddAU)
                .addComponent(btnDeleteAU))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(29, 29, 29)
            .addGroup(titleAUBKarainamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(titleAUBKarainamLayout.createSequentialGroup()
                    .addComponent(dtpAULDinayanMitaPasu, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(13, 13, 13)
                    .addGroup(titleAUBKarainamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDeleteAULDMitaPasu)
                        .addComponent(btnAddAULDMitaPasu))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(titleAUBKarainamLayout.createSequentialGroup()
                    .addComponent(dtpAULDinayanMitaPera, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(13, 13, 13)
                    .addGroup(titleAUBKarainamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDeleteAULDMitaPera)
                        .addComponent(btnAddAULDMitaPera))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    cbxSahanaLabiAtha.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cbxSahanaLabiAtha.setSelected(true);
    cbxSahanaLabiAtha.setText("iyk ,eî we; ");
    cbxSahanaLabiAtha.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cbxSahanaLabiAthaActionPerformed(evt);
        }
    });

    titleSahanaLabinama.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ",nd we;s iyk ms<sno úia;r ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FMMalithi", 0, 14))); // NOI18N

    tblSahana.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    tblSahana.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "සහනය", "ලබන ස්ථානය  ", "අවසන් වරට ලබාගත් දිනය"
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
    if (tblSahana.getColumnModel().getColumnCount() > 0) {
        tblSahana.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblSahana.getColumnModel().getColumn(1).setPreferredWidth(10);
    }

    btnAddSahana.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnAddSahana.setText("එකතු කරන්න ");
    btnAddSahana.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAddSahanaActionPerformed(evt);
        }
    });

    btnDeleteSahana.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnDeleteSahana.setText("ඉවත් කරන්න ");
    btnDeleteSahana.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDeleteSahanaActionPerformed(evt);
        }
    });

    txtSahanaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    jLabel6.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel6.setText("iykh ");

    btnDAllSahana.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnDAllSahana.setText("සියල්ල ඉවත් කරන්න ");
    btnDAllSahana.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDAllSahanaActionPerformed(evt);
        }
    });

    txtSahanaLabanaSthanaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    jLabel46.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel46.setText(",nk ia:dkh ");

    jLabel72.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel72.setText("wjika jrg ,nd.;a oskh ");

    dtpSahanaAwasanWLDinaya.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
        new datechooser.view.appearance.ViewAppearance("custom",
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                true,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 255),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(128, 128, 128),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(255, 0, 0),
                false,
                false,
                new datechooser.view.appearance.swing.ButtonPainter()),
            (datechooser.view.BackRenderer)null,
            false,
            true)));
dtpSahanaAwasanWLDinaya.setNothingAllowed(false);
dtpSahanaAwasanWLDinaya.setCurrentNavigateIndex(0);

javax.swing.GroupLayout titleSahanaLabinamaLayout = new javax.swing.GroupLayout(titleSahanaLabinama);
titleSahanaLabinama.setLayout(titleSahanaLabinamaLayout);
titleSahanaLabinamaLayout.setHorizontalGroup(
    titleSahanaLabinamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(titleSahanaLabinamaLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(titleSahanaLabinamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleSahanaLabinamaLayout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSahanaya))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleSahanaLabinamaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnDAllSahana)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeleteSahana)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddSahana))
            .addGroup(titleSahanaLabinamaLayout.createSequentialGroup()
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSahanaLabanaSthanaya)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel72)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dtpSahanaAwasanWLDinaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap())
    );
    titleSahanaLabinamaLayout.setVerticalGroup(
        titleSahanaLabinamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(titleSahanaLabinamaLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(titleSahanaLabinamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6)
                .addComponent(txtSahanaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(titleSahanaLabinamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(titleSahanaLabinamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(txtSahanaLabanaSthanaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel72))
                .addComponent(dtpSahanaAwasanWLDinaya, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(14, 14, 14)
            .addGroup(titleSahanaLabinamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnAddSahana)
                .addComponent(btnDeleteSahana)
                .addComponent(btnDAllSahana))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    btnAddNewMember.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    btnAddNewMember.setText("idudðlhd nojd.kak ");
    btnAddNewMember.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAddNewMemberActionPerformed(evt);
        }
    });

    txtNithyaSevaAnkaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    jLabel33.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel33.setText("ks;H fiajd wxlh ");

    javax.swing.GroupLayout PanelFormLayout = new javax.swing.GroupLayout(PanelForm);
    PanelForm.setLayout(PanelFormLayout);
    PanelFormLayout.setHorizontalGroup(
        PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(PanelFormLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelFormLayout.createSequentialGroup()
                    .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 735, Short.MAX_VALUE)
                        .addGroup(PanelFormLayout.createSequentialGroup()
                            .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(PanelFormLayout.createSequentialGroup()
                                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbWisramaWLabanneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(66, 66, 66)
                                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(cmbDWWLabanneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(PanelFormLayout.createSequentialGroup()
                                    .addGap(539, 539, 539)
                                    .addComponent(btnAddNewMember)))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(titleDWWNHethu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(titleMWLabenam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
                .addGroup(PanelFormLayout.createSequentialGroup()
                    .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelFormLayout.createSequentialGroup()
                            .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(10, 10, 10))
                        .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)))
                    .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dtpDOB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSthawaraDurakathanaya, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtJangamaDurakathanaya, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(364, 364, 364))
                .addGroup(PanelFormLayout.createSequentialGroup()
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbWiwahakada, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(460, 460, 460))
                .addGroup(PanelFormLayout.createSequentialGroup()
                    .addComponent(titleWEAnuyuththanam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelFormLayout.createSequentialGroup()
                    .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(titleWWNolabenam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(titleWishramaWLanannenam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelFormLayout.createSequentialGroup()
                    .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(titleAUBKarainam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(titleDaruwanSitinam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelFormLayout.createSequentialGroup()
                    .addComponent(titleSahanaLabinama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
                .addGroup(PanelFormLayout.createSequentialGroup()
                    .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbxDaruwanSiti)
                        .addComponent(cbxAbadithaUBKarai)
                        .addComponent(cbxSahanaLabiAtha)
                        .addComponent(titleWiwahakanam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
                .addGroup(PanelFormLayout.createSequentialGroup()
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtNithyaSevaAnkaya, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(PanelFormLayout.createSequentialGroup()
                    .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelFormLayout.createSequentialGroup()
                            .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelFormLayout.createSequentialGroup()
                                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbMasukaWLabanneda, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelFormLayout.createSequentialGroup()
                                    .addComponent(jLabel29)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel30)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblWasara, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addComponent(jLabel32)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblMasa, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel34)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblDina, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PanelFormLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbPolisiyataBahune, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelFormLayout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbWishramaYWThanathura, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelFormLayout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtJathikaHadunumAnkaya, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelFormLayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dtpPolisiyataBadunaDinaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelFormLayout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUpasewaAnkaya, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelFormLayout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2))
                            .addGroup(PanelFormLayout.createSequentialGroup()
                                .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dtpSamajikathwayaLDinaya, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSampurnaNama, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(PanelFormLayout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dtpWishramaLabuDinaya, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelFormLayout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbPBadenawitaThanathura, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelFormLayout.createSequentialGroup()
                                .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAwasanWarataSKKottasaya, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAwasanWarataSKSthanaya, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(PanelFormLayout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmbWEAnuyuththada, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelFormLayout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRanawiruHadunumAnkaya, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
    );
    PanelFormLayout.setVerticalGroup(
        PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(PanelFormLayout.createSequentialGroup()
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(dtpSamajikathwayaLDinaya, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel4)
                .addComponent(txtSampurnaNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel5)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel7)
                .addComponent(dtpPolisiyataBadunaDinaya, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel8)
                .addComponent(cmbPolisiyataBahune, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel9)
                .addComponent(cmbPBadenawitaThanathura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel10)
                .addComponent(cmbWishramaYWThanathura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel33)
                .addComponent(txtNithyaSevaAnkaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(9, 9, 9)
            .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel12)
                .addComponent(txtUpasewaAnkaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel13)
                .addComponent(txtJathikaHadunumAnkaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel14)
                .addComponent(txtRanawiruHadunumAnkaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(cmbWEAnuyuththada)
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(titleWEAnuyuththanam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel19)
                .addComponent(txtAwasanWarataSKSthanaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel20)
                .addComponent(txtAwasanWarataSKKottasaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel28)
                .addComponent(dtpWishramaLabuDinaya, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel29)
                .addComponent(jLabel30)
                .addComponent(lblWasara)
                .addComponent(jLabel32)
                .addComponent(lblMasa)
                .addComponent(jLabel34)
                .addComponent(lblDina))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel39)
                .addComponent(cmbMasukaWLabanneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(titleMWLabenam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel45)
                .addComponent(cmbWisramaWLabanneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel47)
                .addComponent(cmbDWWLabanneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(titleWishramaWLanannenam, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(titleWWNolabenam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(titleDWWNHethu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel58)
                .addComponent(txtSthawaraDurakathanaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel59)
                .addComponent(txtJangamaDurakathanaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel60)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(dtpDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel61))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel62)
                .addComponent(cmbWiwahakada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(titleWiwahakanam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(3, 3, 3)
            .addComponent(cbxDaruwanSiti)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(titleDaruwanSitinam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(cbxAbadithaUBKarai)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(titleAUBKarainam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(cbxSahanaLabiAtha)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(titleSahanaLabinama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(btnAddNewMember)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jPanel1.add(PanelForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, -1, -1));

    lblClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Clear.png"))); // NOI18N
    lblClear.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
        public void mouseMoved(java.awt.event.MouseEvent evt) {
            lblClearMouseMoved(evt);
        }
    });
    lblClear.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            lblClearMouseClicked(evt);
        }
    });
    jPanel1.add(lblClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, 140));

    jLabel31.setFont(new java.awt.Font("Iskoola Pota", 1, 14)); // NOI18N
    jLabel31.setText("දත්ත මකා දමන්න ");
    jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

    jScrollPane1.setViewportView(jPanel1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1017, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtWenathHaniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtWenathHaniActionPerformed


    }//GEN-LAST:event_txtWenathHaniActionPerformed

    private void btnAddAthuruAbadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAthuruAbadaActionPerformed

        if (!txtAthuruAbadaya.getText().isEmpty()) {
            DefaultTableModel tmAA = (DefaultTableModel) tblAthuruAbada.getModel();
            Vector rowData = new Vector();
            rowData.add(txtAthuruAbadaya.getText());
            tmAA.addRow(rowData);
            txtAthuruAbadaya.setText("");
        } else {
            showInformationMessage01();
        }


    }//GEN-LAST:event_btnAddAthuruAbadaActionPerformed

    private void showInformationMessage01() {
        JOptionPane.showMessageDialog(this, "කරුණාකර නිවැරදි දත්ත පමණක් ඇතුළු කරන්න..!", "දත්ත නිවැරදි නොවේ", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showInformationMessage02() {
        JOptionPane.showMessageDialog(this, "කරුණාකර ඉවත්කිරීමට අවශ්‍ය row එක තෝරන්න ", "තේරීම නිවැරදි නොවේ", JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnDeleteAthuruAbadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAthuruAbadaActionPerformed

        deleteTableRow(tblAthuruAbada);

    }//GEN-LAST:event_btnDeleteAthuruAbadaActionPerformed

    private void btnDAllAthuruAbadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDAllAthuruAbadaActionPerformed

        deleteAllTableRow(tblAthuruAbada);

    }//GEN-LAST:event_btnDAllAthuruAbadaActionPerformed

    private void btnDAllWWNHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDAllWWNHActionPerformed

        deleteAllTableRow(tblWWNHethu);

    }//GEN-LAST:event_btnDAllWWNHActionPerformed

    private void btnDeleteWWNHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteWWNHActionPerformed

        deleteTableRow(tblWWNHethu);


    }//GEN-LAST:event_btnDeleteWWNHActionPerformed

    private void deleteAllTableRow(JTable tbl) {
        if (0 == JOptionPane.showConfirmDialog(this, "වගුවෙහි සියලු දත්ත ඉවත් කල යුතුද ?", "සියල්ල ඉවත් කරන්න", JOptionPane.YES_NO_OPTION)) {
            DefaultTableModel tmAA = (DefaultTableModel) tbl.getModel();
            tmAA.setRowCount(0);
        }
    }

    private void deleteTableRow(JTable tbl) {
        if (tbl.getSelectedRow() != -1) {
            DefaultTableModel tmAA = (DefaultTableModel) tbl.getModel();
            tmAA.removeRow(tbl.getSelectedRow());
        } else {
            showInformationMessage02();
        }
    }


    private void btnAddWWNHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddWWNHActionPerformed

        setTableDateSingleValue(tblWWNHethu, cmbWWNHethuwa);

    }//GEN-LAST:event_btnAddWWNHActionPerformed

    private void setTableDateSingleValue(JTable tbl, JComboBox cmb) {
        if (!Funtional.getBooleanFun(() -> {
            boolean isIn = false;
            for (int i = 0; i < tbl.getRowCount(); i++) {
                if (tbl.getValueAt(i, 0).toString().equals(cmb.getSelectedItem().toString())) {
                    isIn = true;
                }
            }
            return isIn;
        })) {
            DefaultTableModel tmAA = (DefaultTableModel) tbl.getModel();
            Vector rowData = new Vector();
            rowData.add(cmb.getSelectedItem().toString());
            tmAA.addRow(rowData);
        } else {
            JOptionPane.showMessageDialog(this, "දැනටමත් මෙය ඇතුලත් කර ඇත", "සමාන දත්ත", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void btnDAllDWWNHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDAllDWWNHActionPerformed

        deleteAllTableRow(tblDWWNHethu);


    }//GEN-LAST:event_btnDAllDWWNHActionPerformed

    private void btnDeleteDWWNHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteDWWNHActionPerformed

        deleteTableRow(tblDWWNHethu);

    }//GEN-LAST:event_btnDeleteDWWNHActionPerformed

    private void btnAddDWWNHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDWWNHActionPerformed

        setTableDateSingleValue(tblDWWNHethu, cmbDWWNHethuwa);


    }//GEN-LAST:event_btnAddDWWNHActionPerformed

    private void btnAddDaruwaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDaruwaActionPerformed

        if (!txtDaruwageNama.getText().isEmpty()) {
            DefaultTableModel tmAA = (DefaultTableModel) tblDaruwan.getModel();
            Vector rowData = new Vector();
            rowData.add(txtDaruwageNama.getText());
            rowData.add(DateToStringForTable(dtpDaruwageDOB.getSelectedDate().getTime()));
            rowData.add(cmbDaruwageDanataThathwaya.getSelectedItem().toString());
            rowData.add(cmbDaruwaAbadithada.getSelectedItem().toString());
            tmAA.addRow(rowData);
            txtDaruwageNama.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "කරුණාකර දරුවාගේ නම සදහන් කරන්න", "සදහන් කර නොමැත ", JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_btnAddDaruwaActionPerformed

    private String DateToStringForTable(Date date) {
        return new SimpleDateFormat("yyyy$MM$dd").format(date);
    }

    private void btnDeleteDaruwaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteDaruwaActionPerformed

        deleteTableRow(tblDaruwan);

    }//GEN-LAST:event_btnDeleteDaruwaActionPerformed

    private void btnDAllDaruwaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDAllDaruwaActionPerformed

        deleteAllTableRow(tblDaruwan);


    }//GEN-LAST:event_btnDAllDaruwaActionPerformed

    private void btnAddAUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAUActionPerformed

        if (!Funtional.getBooleanFun(() -> {
            boolean isIn = false;
            for (int i = 0; i < tblAU.getRowCount(); i++) {
                if ("fjk;a".equals(cmbAbadithaUpakarana.getSelectedItem().toString())) {
                    if (tblAU.getValueAt(i, 0).toString().equals(txtWenathAbadithaUpakarana.getText())) {
                        isIn = true;
                    }
                } else {
                    if (tblAU.getValueAt(i, 0).toString().equals(cmbAbadithaUpakarana.getSelectedItem().toString())) {
                        isIn = true;
                    }
                }
            }
            return isIn;
        })) {
            if ("fjk;a".equals(cmbAbadithaUpakarana.getSelectedItem().toString())) {
                if (!txtWenathAbadithaUpakarana.getText().isEmpty()) {
                    DefaultTableModel tmAA = (DefaultTableModel) tblAU.getModel();
                    Vector rowData = new Vector();
                    rowData.add(txtWenathAbadithaUpakarana.getText());
                    tmAA.addRow(rowData);

                    txtWenathAbadithaUpakarana.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "කරුණාකර ආබාදිත උපකරණයේ නම සදහන කරන්න", "නම සදහන් කර නොමැත", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                DefaultTableModel tmAA = (DefaultTableModel) tblAU.getModel();
                Vector rowData = new Vector();
                rowData.add(cmbAbadithaUpakarana.getSelectedItem().toString());
                tmAA.addRow(rowData);

            }
        } else {
            JOptionPane.showMessageDialog(this, "දැනටමත් මෙය ඇතුලත් කර ඇත", "සමාන දත්ත", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btnAddAUActionPerformed

    private final ArrayList<AULDinayan> auldMitaPera = new ArrayList<>();
    private final ArrayList<AULDinayan> auldMitaPasu = new ArrayList<>();

    private void btnDeleteAUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAUActionPerformed

        if (tblAU.getSelectedRow() != -1) {
            ArrayList<AULDinayan> indexes_for_r = new ArrayList<>();
            auldMitaPera.stream().filter((auld1) -> (auld1.getIndex() == tblAU.getSelectedRow())).forEach((auld1) -> indexes_for_r.add(auld1));
            indexes_for_r.stream().forEach((indexes_for_r1) -> auldMitaPera.remove(indexes_for_r1));
            auldMitaPera.stream().filter((auld1) -> (auld1.getIndex() > tblAU.getSelectedRow())).forEach((auld1) -> auld1.setIndex(auld1.getIndex() - 1));
        }
        deleteTableRow(tblAU);
        DefaultTableModel tm = (DefaultTableModel) tblAULDinayanMitaPera.getModel();
        tm.setRowCount(0);


    }//GEN-LAST:event_btnDeleteAUActionPerformed

    private void btnAddAULDMitaPeraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAULDMitaPeraActionPerformed

        ArrayList<String> dinayn = new ArrayList<>();
        if (tblAU.getSelectedRow() != -1) {
            DefaultTableModel tbl2 = (DefaultTableModel) tblAULDinayanMitaPera.getModel();
            tbl2.setRowCount(0);
            int showIndex = tblAU.getSelectedRow();
            if (!Funtional.getBooleanFun(() -> {
                boolean isIn = false;
                for (AULDinayan auld1 : auldMitaPera) {
                    if (auld1.getIndex() == showIndex) {
                        if (auld1.getDinaya().equals(DateToStringForTable(dtpAULDinayanMitaPera.getSelectedDate().getTime()))) {
                            isIn = true;
                        }
                    }
                }
                return isIn;
            })) {
                auldMitaPera.add(new AULDinayan(DateToStringForTable(dtpAULDinayanMitaPera.getSelectedDate().getTime()), showIndex));
                auldMitaPera.stream().filter((auld1) -> (auld1.getIndex() == showIndex)).forEach((auld1) -> {
                    dinayn.add(auld1.getDinaya());
                });
                dinayn.forEach(d -> {
                    DefaultTableModel tbl = (DefaultTableModel) tblAULDinayanMitaPera.getModel();
                    Vector rowData = new Vector();
                    rowData.add(d);
                    tbl.addRow(rowData);
                });
            } else {
                auldMitaPera.stream().filter((auld1) -> (auld1.getIndex() == showIndex)).forEach((auld1) -> {
                    dinayn.add(auld1.getDinaya());
                });
                dinayn.forEach(d -> {
                    DefaultTableModel tbl = (DefaultTableModel) tblAULDinayanMitaPera.getModel();
                    Vector rowData = new Vector();
                    rowData.add(d);
                    tbl.addRow(rowData);
                });
                JOptionPane.showMessageDialog(this, "මෙම දිනය දැනටමත් ඇතුලත් කර ඇත", "ඇතුලත් කර ඇත", JOptionPane.INFORMATION_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this, "කරුණාකර දිනය ඇතුලත් කලයුතු ආබාදිත උපකරණය තෝරන්න", "තෝරා නොමැත", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btnAddAULDMitaPeraActionPerformed

    private void btnDeleteAULDMitaPeraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAULDMitaPeraActionPerformed

        ArrayList<String> dinayn = new ArrayList<>();

        if (tblAULDinayanMitaPera.getSelectedRow() != -1) {
            for (int i = 0; i < auldMitaPera.size(); i++) {
                if (auldMitaPera.get(i).getIndex() == tblAU.getSelectedRow()) {
                    if (auldMitaPera.get(i).getDinaya().equals(tblAULDinayanMitaPera.getValueAt(tblAULDinayanMitaPera.getSelectedRow(), 0))) {
                        auldMitaPera.remove(auldMitaPera.get(i));
                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "කරුණාකර ඉවත් කිරීමට අවශ්‍ය දිනය තෝරන්න", "තෝරා නොමැත", JOptionPane.INFORMATION_MESSAGE);
        }
        DefaultTableModel tbl = (DefaultTableModel) tblAULDinayanMitaPera.getModel();
        tbl.setRowCount(0);
        auldMitaPera.stream().filter((auld1) -> (auld1.getIndex() == tblAU.getSelectedRow())).forEach((auld1) -> {
            dinayn.add(auld1.getDinaya());
        });
        dinayn.forEach(d -> {
            Vector rowData = new Vector();
            rowData.add(d);
            tbl.addRow(rowData);
        });


    }//GEN-LAST:event_btnDeleteAULDMitaPeraActionPerformed

    private void btnAddSahanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSahanaActionPerformed

        if (!txtSahanaya.getText().isEmpty()) {
            if (!txtSahanaLabanaSthanaya.getText().isEmpty()) {
                DefaultTableModel tmAA = (DefaultTableModel) tblSahana.getModel();
                Vector rowData = new Vector();
                rowData.add(txtSahanaya.getText());
                rowData.add(txtSahanaLabanaSthanaya.getText());
                rowData.add(DateToStringForTable(dtpSahanaAwasanWLDinaya.getSelectedDate().getTime()));
                tmAA.addRow(rowData);
                txtSahanaya.setText("");
                txtSahanaLabanaSthanaya.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "කරුණාකර \'සහන ලැබූ ස්ථානය\" සදහන් කරන්න", "සදහන් කර නොමැත ", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "කරුණාකර \'සහනය\" සදහන් කරන්න", "සදහන් කර නොමැත ", JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_btnAddSahanaActionPerformed

    private void btnDeleteSahanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSahanaActionPerformed

        deleteTableRow(tblSahana);

    }//GEN-LAST:event_btnDeleteSahanaActionPerformed

    private void btnDAllSahanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDAllSahanaActionPerformed

        deleteAllTableRow(tblSahana);

    }//GEN-LAST:event_btnDAllSahanaActionPerformed

    private void btnAddNewMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewMemberActionPerformed

        if (isReadyToSaveData()) {
            saveData();
            lblSA.setText(PKGenaretor.getNextPK("SMJK", "SA"));
            JOptionPane.showMessageDialog(this, "සාමාජිකයා පද්දතියට ඇතුලුකරන ලදී", "ඇතුලුකරන ලදී", JOptionPane.INFORMATION_MESSAGE);
            clearData();
        } else {
            JOptionPane.showMessageDialog(this, "කරුණාකර නිවැරදිව ඉල්ලුම් පත්‍රය පුරවන්න", "නිවැරදි නොවේ", JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_btnAddNewMemberActionPerformed

    private void PanelFormComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_PanelFormComponentResized


    }//GEN-LAST:event_PanelFormComponentResized

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized


    }//GEN-LAST:event_formComponentResized

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing


    }//GEN-LAST:event_formWindowClosing

    private void lblClearMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblClearMouseMoved

        lblClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Clear2.png")));


    }//GEN-LAST:event_lblClearMouseMoved

    private void jPanel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseMoved

        lblClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Clear.png")));


    }//GEN-LAST:event_jPanel1MouseMoved

    private void cmbAbadithaUpakaranaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAbadithaUpakaranaActionPerformed

        if ("fjk;a".equals(cmbAbadithaUpakarana.getSelectedItem().toString())) {
            txtWenathAbadithaUpakarana.setEnabled(true);
        } else {
            txtWenathAbadithaUpakarana.setEnabled(false);
        }

    }//GEN-LAST:event_cmbAbadithaUpakaranaActionPerformed

    private void tblAUKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblAUKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (tblAU.getSelectedRow() != -1) {
                DefaultTableModel tbl2 = (DefaultTableModel) tblAULDinayanMitaPera.getModel();
                tbl2.setRowCount(0);
                int showIndex = tblAU.getSelectedRow();
                ArrayList<String> dinayn = new ArrayList<>();
                auldMitaPera.stream().filter((auld1) -> (auld1.getIndex() == showIndex)).forEach((auld1) -> dinayn.add(auld1.getDinaya()));
                dinayn.forEach(d -> {
                    DefaultTableModel tbl = (DefaultTableModel) tblAULDinayanMitaPera.getModel();
                    Vector rowData = new Vector();
                    rowData.add(d);
                    tbl.addRow(rowData);
                });
            }

            if (tblAU.getSelectedRow() != -1) {
                DefaultTableModel tbl2 = (DefaultTableModel) tblAULDinayanMitaPasu.getModel();
                tbl2.setRowCount(0);
                int showIndex = tblAU.getSelectedRow();
                ArrayList<String> dinayn = new ArrayList<>();
                auldMitaPasu.stream().filter((auld1) -> (auld1.getIndex() == showIndex)).forEach((auld1) -> dinayn.add(auld1.getDinaya()));
                dinayn.forEach(d -> {
                    DefaultTableModel tbl = (DefaultTableModel) tblAULDinayanMitaPasu.getModel();
                    Vector rowData = new Vector();
                    rowData.add(d);
                    tbl.addRow(rowData);
                });
            }
        }

    }//GEN-LAST:event_tblAUKeyReleased

    private void tblAUMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAUMouseReleased

        if (tblAU.getSelectedRow() != -1) {
            DefaultTableModel tbl2 = (DefaultTableModel) tblAULDinayanMitaPera.getModel();
            tbl2.setRowCount(0);
            int showIndex = tblAU.getSelectedRow();
            ArrayList<String> dinayn = new ArrayList<>();
            auldMitaPera.stream().filter((auld1) -> (auld1.getIndex() == showIndex)).forEach((auld1) -> dinayn.add(auld1.getDinaya()));
            dinayn.forEach(d -> {
                DefaultTableModel tbl = (DefaultTableModel) tblAULDinayanMitaPera.getModel();
                Vector rowData = new Vector();
                rowData.add(d);
                tbl.addRow(rowData);
            });
        }

        if (tblAU.getSelectedRow() != -1) {
            DefaultTableModel tbl2 = (DefaultTableModel) tblAULDinayanMitaPasu.getModel();
            tbl2.setRowCount(0);
            int showIndex = tblAU.getSelectedRow();
            ArrayList<String> dinayn = new ArrayList<>();
            auldMitaPasu.stream().filter((auld1) -> (auld1.getIndex() == showIndex)).forEach((auld1) -> dinayn.add(auld1.getDinaya()));
            dinayn.forEach(d -> {
                DefaultTableModel tbl = (DefaultTableModel) tblAULDinayanMitaPasu.getModel();
                Vector rowData = new Vector();
                rowData.add(d);
                tbl.addRow(rowData);
            });
        }


    }//GEN-LAST:event_tblAUMouseReleased

    private void btnChangePKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePKActionPerformed

        try {
            String userInput = JOptionPane.showInputDialog(this, "සාමාජික අංකය ඇතුලත් කරන්න");
            if (!userInput.isEmpty()) {
                if (isSAAvailable(userInput)) {
                    JOptionPane.showMessageDialog(this, "ඇතුලත් කල සාමාජික අංකය භාවිත්යේ ඇත. වෙනත් අංකයක් ඇතුල් කරන්න.", "භාවිත්යේ ඇත", JOptionPane.WARNING_MESSAGE);
                } else {
                    lblSA.setText(userInput);
                }
            }
        } catch (Exception e) {
           
        }


    }//GEN-LAST:event_btnChangePKActionPerformed

    private boolean isSAAvailable(String SA) throws Exception {
        return SQLConnection.SqlConnection.getData("SELECT SA FROM SMJK WHERE SA='" + SA + "'").first();
    }

    private void cmbAthuruAAdda1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAthuruAAdda1ActionPerformed

        enableDesable(cmbAthuruAAdda1, titleAAbada, tblAthuruAbada);

    }//GEN-LAST:event_cmbAthuruAAdda1ActionPerformed

    private void cmbWEAnuyuththadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbWEAnuyuththadaActionPerformed

        enableDesable(cmbWEAnuyuththada, titleWEAnuyuththanam, null);

    }//GEN-LAST:event_cmbWEAnuyuththadaActionPerformed

    private void cmbMasukaWLabannedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMasukaWLabannedaActionPerformed

        enableDesable(cmbMasukaWLabanneda, titleMWLabenam, null);

    }//GEN-LAST:event_cmbMasukaWLabannedaActionPerformed

    private void cbxDaruwanSitiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDaruwanSitiActionPerformed

        enableDesable(cbxDaruwanSiti, titleDaruwanSitinam, tblDaruwan);

    }//GEN-LAST:event_cbxDaruwanSitiActionPerformed

    private void lblClearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblClearMouseClicked

        if (0 == JOptionPane.showConfirmDialog(this, "සියලූම දත්ත මකා දැමිය යුතුද ?", "මකා දැමීම ", JOptionPane.YES_NO_OPTION)) {
            clearData();
        }


    }//GEN-LAST:event_lblClearMouseClicked

    private void dtpWishramaLabuDinayaOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dtpWishramaLabuDinayaOnCommit

        LocalDate ld1 = dtpPolisiyataBadunaDinaya.getSelectedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ld2 = dtpWishramaLabuDinaya.getSelectedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Period p = Period.between(ld1, ld2);
        lblWasara.setText(Integer.toString(p.getYears()));
        lblMasa.setText(Integer.toString(p.getMonths()));
        lblDina.setText(Integer.toString(p.getDays()));


    }//GEN-LAST:event_dtpWishramaLabuDinayaOnCommit

    private void cmbSharirikaHaniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSharirikaHaniActionPerformed

        if (cmbSharirikaHani.getSelectedItem().toString().equals("fjk;a")) {
            txtWenathHani.setEnabled(true);
        } else {
            txtWenathHani.setEnabled(false);
        }


    }//GEN-LAST:event_cmbSharirikaHaniActionPerformed

    private void cmbWiwahakadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbWiwahakadaActionPerformed

        if (cmbWiwahakada.getSelectedIndex() == 0) {
            titleWiwahakanam.setEnabled(true);
            for (int i = 0; i < titleWiwahakanam.getComponentCount(); i++) {
                titleWiwahakanam.getComponent(i).setEnabled(true);

            }
            txtSahakarugeRLipinaya.setEnabled(true);
        } else {
            titleWiwahakanam.setEnabled(false);
            txtSahakarugeRLipinaya.setEnabled(false);
            for (int i = 0; i < titleWiwahakanam.getComponentCount(); i++) {
                titleWiwahakanam.getComponent(i).setEnabled(false);
                try {
                    JTextField tx = (JTextField) titleWiwahakanam.getComponent(i);
                    tx.setBackground(Color.WHITE);
                } catch (Exception e1) {
                    try {
                        JTextArea tx = (JTextArea) titleWiwahakanam.getComponent(i);
                        tx.setBackground(Color.WHITE);
                    } catch (Exception e2) {
                    }
                }
            }
        }

    }//GEN-LAST:event_cmbWiwahakadaActionPerformed

    private void cbxSahanaLabiAthaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSahanaLabiAthaActionPerformed

        enableDesable(cbxSahanaLabiAtha, titleSahanaLabinama, tblSahana);

    }//GEN-LAST:event_cbxSahanaLabiAthaActionPerformed

    private void cmbWisramaWLabannedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbWisramaWLabannedaActionPerformed

        boolean x = getBooleanValue(cmbWisramaWLabanneda.getSelectedItem().toString());
        boolean y = getBooleanValue(cmbDWWLabanneda.getSelectedItem().toString());
        setA(x || y);
        setB(x);
        setC(y);
        setD(!x);
        setE(!y);


    }//GEN-LAST:event_cmbWisramaWLabannedaActionPerformed

    private void setA(boolean b) {
        txtWWAnkaya.setEnabled(b);
        txtWWLPLKottashaya.setEnabled(b);
        if (!b) {
            txtWWAnkaya.setBackground(Color.WHITE);
            txtWWLPLKottashaya.setBackground(Color.WHITE);

        }
    }

    private void setB(boolean b) {
        txtWWWatinakama.setEnabled(b);
        if (!b) {
            txtWWWatinakama.setBackground(Color.WHITE);
        }
    }

    private void setC(boolean b) {
        txtDWWWatinakama.setEnabled(b);
        if (!b) {
            txtDWWWatinakama.setBackground(Color.WHITE);
        }
    }

    private void setD(boolean b) {
        titleWWNolabenam.setEnabled(b);

        for (int i = 0; i < titleWWNolabenam.getComponentCount(); i++) {
            titleWWNolabenam.getComponent(i).setEnabled(b);
            if (!b) {
                try {
                    TextField f = (TextField) titleWWNolabenam.getComponent(i);
                    f.setBackground(Color.WHITE);

                } catch (Exception e) {
                }
            }
        }
        tblWWNHethu.setEnabled(b);
        if (!b) {
            TitledBorder tb = (TitledBorder) titleWWNolabenam.getBorder();
            titleWWNolabenam.setBorder(BorderFactory.createTitledBorder(null, tb.getTitle(),
                    TitledBorder.DEFAULT_JUSTIFICATION,
                    TitledBorder.DEFAULT_POSITION,
                    new java.awt.Font("FMMalithi", 0, 14)));

        }
    }

    private void setE(boolean b) {
        titleDWWNHethu.setEnabled(b);
        for (int i = 0; i < titleDWWNHethu.getComponentCount(); i++) {
            titleDWWNHethu.getComponent(i).setEnabled(b);
            if (!b) {
                try {
                    TextField f = (TextField) titleDWWNHethu.getComponent(i);
                    f.setBackground(Color.WHITE);

                } catch (Exception e) {
                }
            }
        }
        if (!b) {
            TitledBorder tb = (TitledBorder) titleDWWNHethu.getBorder();
            titleDWWNHethu.setBorder(BorderFactory.createTitledBorder(null, tb.getTitle(),
                    TitledBorder.DEFAULT_JUSTIFICATION,
                    TitledBorder.DEFAULT_POSITION,
                    new java.awt.Font("FMMalithi", 0, 14)));

        }

        tblDWWNHethu.setEnabled(b);
    }


    private void cmbDWWLabannedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDWWLabannedaActionPerformed

        boolean x = getBooleanValue(cmbWisramaWLabanneda.getSelectedItem().toString());
        boolean y = getBooleanValue(cmbDWWLabanneda.getSelectedItem().toString());
        setA(x || y);
        setB(x);
        setC(y);
        setD(!x);
        setE(!y);

    }//GEN-LAST:event_cmbDWWLabannedaActionPerformed

    private void cbxAbadithaUBKaraiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAbadithaUBKaraiActionPerformed

        if (cbxAbadithaUBKarai.isSelected()) {
            titleAUBKarainam.setEnabled(true);
            for (int i = 0; i < titleAUBKarainam.getComponentCount(); i++) {
                titleAUBKarainam.getComponent(i).setEnabled(true);
            }
            tblAU.setEnabled(true);
            tblAULDinayanMitaPera.setEnabled(true);

        } else {
            titleAUBKarainam.setEnabled(false);
            for (int i = 0; i < titleAUBKarainam.getComponentCount(); i++) {
                titleAUBKarainam.getComponent(i).setEnabled(false);
            }
            tblAU.setEnabled(false);
            tblAULDinayanMitaPera.setEnabled(false);

            TitledBorder tb = (TitledBorder) titleAUBKarainam.getBorder();
            titleAUBKarainam.setBorder(BorderFactory.createTitledBorder(null, tb.getTitle(),
                    TitledBorder.DEFAULT_JUSTIFICATION,
                    TitledBorder.DEFAULT_POSITION,
                    new java.awt.Font("FMMalithi", 0, 14)));
        }

    }//GEN-LAST:event_cbxAbadithaUBKaraiActionPerformed

    private void txtAwasanWarataSKSthanayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAwasanWarataSKSthanayaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAwasanWarataSKSthanayaActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown

        System.out.println("ok show");
        lblSA.setText(PKGenaretor.getNextPK("SMJK", "SA"));


    }//GEN-LAST:event_formComponentShown

    private void cmbThrasthaKandayamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbThrasthaKandayamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbThrasthaKandayamaActionPerformed

    private void btnDeleteAULDMitaPasuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAULDMitaPasuActionPerformed

        ArrayList<String> dinayn = new ArrayList<>();

        if (tblAULDinayanMitaPasu.getSelectedRow() != -1) {
            for (int i = 0; i < auldMitaPasu.size(); i++) {
                if (auldMitaPasu.get(i).getIndex() == tblAU.getSelectedRow()) {
                    if (auldMitaPasu.get(i).getDinaya().equals(tblAULDinayanMitaPasu.getValueAt(tblAULDinayanMitaPasu.getSelectedRow(), 0))) {
                        auldMitaPasu.remove(auldMitaPasu.get(i));
                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "කරුණාකර ඉවත් කිරීමට අවශ්‍ය දිනය තෝරන්න", "තෝරා නොමැත", JOptionPane.INFORMATION_MESSAGE);
        }
        DefaultTableModel tbl = (DefaultTableModel) tblAULDinayanMitaPasu.getModel();
        tbl.setRowCount(0);
        auldMitaPasu.stream().filter((auld1) -> (auld1.getIndex() == tblAU.getSelectedRow())).forEach((auld1) -> {
            dinayn.add(auld1.getDinaya());
        });
        dinayn.forEach(d -> {
            Vector rowData = new Vector();
            rowData.add(d);
            tbl.addRow(rowData);
        });

    }//GEN-LAST:event_btnDeleteAULDMitaPasuActionPerformed

    private void btnAddAULDMitaPasuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAULDMitaPasuActionPerformed

        ArrayList<String> dinayn = new ArrayList<>();
        if (tblAU.getSelectedRow() != -1) {
            DefaultTableModel tbl2 = (DefaultTableModel) tblAULDinayanMitaPasu.getModel();
            tbl2.setRowCount(0);
            int showIndex = tblAU.getSelectedRow();
            if (!Funtional.getBooleanFun(() -> {
                boolean isIn = false;
                for (AULDinayan auld1 : auldMitaPasu) {
                    if (auld1.getIndex() == showIndex) {
                        if (auld1.getDinaya().equals(DateToStringForTable(dtpAULDinayanMitaPasu.getSelectedDate().getTime()))) {
                            isIn = true;
                        }
                    }
                }
                return isIn;
            })) {
                auldMitaPasu.add(new AULDinayan(DateToStringForTable(dtpAULDinayanMitaPasu.getSelectedDate().getTime()), showIndex));
                auldMitaPasu.stream().filter((auld1) -> (auld1.getIndex() == showIndex)).forEach((auld1) -> {
                    dinayn.add(auld1.getDinaya());
                });
                dinayn.forEach(d -> {
                    DefaultTableModel tbl = (DefaultTableModel) tblAULDinayanMitaPasu.getModel();
                    Vector rowData = new Vector();
                    rowData.add(d);
                    tbl.addRow(rowData);
                });
            } else {
                auldMitaPasu.stream().filter((auld1) -> (auld1.getIndex() == showIndex)).forEach((auld1) -> {
                    dinayn.add(auld1.getDinaya());
                });
                dinayn.forEach(d -> {
                    DefaultTableModel tbl = (DefaultTableModel) tblAULDinayanMitaPasu.getModel();
                    Vector rowData = new Vector();
                    rowData.add(d);
                    tbl.addRow(rowData);
                });
                JOptionPane.showMessageDialog(this, "මෙම දිනය දැනටමත් ඇතුලත් කර ඇත", "ඇතුලත් කර ඇත", JOptionPane.INFORMATION_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this, "කරුණාකර දිනය ඇතුලත් කලයුතු ආබාදිත උපකරණය තෝරන්න", "තෝරා නොමැත", JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_btnAddAULDMitaPasuActionPerformed

    private void txtMulikaWatupeWatinakamaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMulikaWatupeWatinakamaKeyReleased

        displayMuluWW();

    }//GEN-LAST:event_txtMulikaWatupeWatinakamaKeyReleased

    private void txtDimanaWalaWatinakamaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDimanaWalaWatinakamaKeyReleased

        displayMuluWW();

    }//GEN-LAST:event_txtDimanaWalaWatinakamaKeyReleased

    private void displayMuluWW() {
        try {
            float total = Float.parseFloat(txtMulikaWatupeWatinakama.getText()) + Float.parseFloat(txtDimanaWalaWatinakama.getText());
            lblMuluWatupeWatinakama.setText(Float.toString(total));
        } catch (Exception e) {
            lblMuluWatupeWatinakama.setText("??????");
        }
    }

    private void enableDesable(JComboBox cmb, JPanel title, JTable table) {
        if (getBooleanValue(cmb.getSelectedItem().toString())) {
            title.setEnabled(true);
            for (int i = 0; i < title.getComponentCount(); i++) {
                title.getComponent(i).setEnabled(true);
            }
            if (table != null) {
                table.setEnabled(true);
            }
        } else {
            title.setEnabled(false);
            for (int i = 0; i < title.getComponentCount(); i++) {
                title.getComponent(i).setEnabled(false);
                try {
                    JTextField tf = (JTextField) title.getComponent(i);
                    tf.setBackground(Color.WHITE);
                } catch (Exception e) {
                    try {
                        JTextArea tf = (JTextArea) title.getComponent(i);
                        tf.setBackground(Color.WHITE);
                    } catch (Exception ex) {
                    }
                }

            }

            TitledBorder tb = (TitledBorder) title.getBorder();
            title.setBorder(BorderFactory.createTitledBorder(null, tb.getTitle(),
                    TitledBorder.DEFAULT_JUSTIFICATION,
                    TitledBorder.DEFAULT_POSITION,
                    new java.awt.Font("FMMalithi", 0, 14)));
            if (table != null) {
                table.setEnabled(false);
            }

        }
    }

    private void enableDesable(JCheckBox cmb, JPanel title, JTable table) {
        if (cmb.isSelected()) {
            title.setEnabled(true);
            for (int i = 0; i < title.getComponentCount(); i++) {
                title.getComponent(i).setEnabled(true);
            }
            if (table != null) {
                table.setEnabled(true);
            }
        } else {
            title.setEnabled(false);
            for (int i = 0; i < title.getComponentCount(); i++) {
                title.getComponent(i).setEnabled(false);
                try {
                    JTextField tf = (JTextField) title.getComponent(i);
                    tf.setBackground(Color.WHITE);
                } catch (Exception e) {
                    try {
                        JTextArea tf = (JTextArea) title.getComponent(i);
                        tf.setBackground(Color.WHITE);
                    } catch (Exception ex) {
                    }
                }

            }

            TitledBorder tb = (TitledBorder) title.getBorder();
            title.setBorder(BorderFactory.createTitledBorder(null, tb.getTitle(),
                    TitledBorder.DEFAULT_JUSTIFICATION,
                    TitledBorder.DEFAULT_POSITION,
                    new java.awt.Font("FMMalithi", 0, 14)));
            if (table != null) {
                table.setEnabled(false);
            }

        }
    }

    private boolean isReadyToSaveData() {
        boolean isReady;
        //Chack Data of TextFields
        boolean ctx01 = UserInputChecker.check(txtAnuKanda);
        boolean ctx02 = UserInputChecker.check(txtAwasanWarataSKKottasaya);
        boolean ctx03 = UserInputChecker.check(txtAwasanWarataSKSthanaya);
        boolean ctx04 = UserInputChecker.isANumber(txtDWWWatinakama);
        boolean ctx05 = UserInputChecker.isANumber(txtDimanaWalaWatinakama);
        boolean ctx06 = UserInputChecker.check(txtDistrikkaya);
        boolean ctx07 = UserInputChecker.check(txtEmail);
        boolean ctx08 = UserInputChecker.check(txtGramaNWasama);
        boolean ctx09 = UserInputChecker.check(txtJangamaDurakathanaya);
        boolean ctx10 = UserInputChecker.check(txtJathikaHadunumAnkaya);
        boolean ctx11 = UserInputChecker.isANumber(txtMulikaWatupeWatinakama);
        // boolean ctx12 = UserInputChecker.check(txtNilaAnkaya);
        boolean ctx13 = UserInputChecker.check(txtPolisWasamaSL);
        boolean ctx14 = UserInputChecker.check(txtPradeshiyaLekamKottasaya);
        boolean ctx15 = UserInputChecker.check(txtPraharayaSiduwuKottasaya);
        boolean ctx16 = UserInputChecker.check(txtPraharayaSiduwuSthanaya);
        boolean ctx17 = UserInputChecker.check(txtRanawiruHadunumAnkaya);
        boolean ctx18 = UserInputChecker.check(txtSahakarugeNama);
        boolean ctx19 = UserInputChecker.check(txtSahakarugeRLipinaya);
        boolean ctx20 = UserInputChecker.check(txtSahakarugeRakiyawa);
        boolean ctx21 = UserInputChecker.check(txtSampurnaNama);
        boolean ctx22 = UserInputChecker.check(txtSthawaraDurakathanaya);
        boolean ctx23 = UserInputChecker.check(txtSthiraLipinaya);
        boolean ctx24 = UserInputChecker.check(txtUpasewaAnkaya);
        boolean ctx25 = UserInputChecker.check(txtWWAnkaya);
        boolean ctx26 = UserInputChecker.check(txtWWLPLKottashaya);
        boolean ctx27 = UserInputChecker.isANumber(txtWWWatinakama);
        boolean ctx28 = UserInputChecker.check(txtWidyaPPawathwuSthanaya);
        boolean ctx29 = UserInputChecker.check(txtWlabanaKottasaya);
        boolean ctx30 = UserInputChecker.check(txtWlabanaPolisWasama);
        //---------------end---------------------------------------------
        boolean isReadyTextFields = ctx01 && ctx02 && ctx03 && ctx04 && ctx05 && ctx06 && ctx07 && ctx08 && ctx09 && ctx10
                && ctx11 && ctx13 && ctx14 && ctx15 && ctx16 && ctx17 && ctx18 && ctx19 && ctx20
                && ctx21 && ctx22 && ctx23 && ctx24 && ctx25 && ctx26 && ctx27 && ctx28 && ctx29 && ctx30;

        //Chack Tables Data
        boolean ctbl01 = UserInputChecker.check(titleAUBKarainam, tblAU);
        boolean ctbl02 = UserInputChecker.check(titleAAbada, tblAthuruAbada);
        boolean ctbl03 = UserInputChecker.check(titleDWWNHethu, tblDWWNHethu);
        boolean ctbl04 = UserInputChecker.check(titleDaruwanSitinam, tblDaruwan);
        boolean ctbl05 = UserInputChecker.check(titleSahanaLabinama, tblSahana);
        boolean ctbl06 = UserInputChecker.check(titleWWNolabenam, tblWWNHethu);

        boolean isReadyTables = ctbl01 && ctbl02 && ctbl03 && ctbl04 && ctbl05 && ctbl06;
        isReady = isReadyTextFields && isReadyTables;
        return isReady;
    }

    private void clearData() {
        txtAnuKanda.setText("");
        txtAthuruAbadaya.setText("");
        txtAwasanWarataSKKottasaya.setText("");
        txtAwasanWarataSKSthanaya.setText("");
        txtDWWWatinakama.setText("");
        txtDaruwageNama.setText("");
        txtDimanaWalaWatinakama.setText("");
        txtDistrikkaya.setText("");
        txtEmail.setText("");
        txtGramaNWasama.setText("");
        txtJangamaDurakathanaya.setText("");
        txtJathikaHadunumAnkaya.setText("");
        txtMulikaWatupeWatinakama.setText("");
        //txtNilaAnkaya.setText("");
        txtPolisWasamaSL.setText("");
        txtPradeshiyaLekamKottasaya.setText("");
        txtPraharayaSiduwuKottasaya.setText("");
        txtPraharayaSiduwuSthanaya.setText("");
        txtRanawiruHadunumAnkaya.setText("");
        txtSahakarugeNama.setText("");
        txtSahakarugeRLipinaya.setText("");
        txtSahakarugeRakiyawa.setText("");
        txtSahanaya.setText("");
        txtSampurnaNama.setText("");
        txtSthawaraDurakathanaya.setText("");
        txtSthiraLipinaya.setText("");
        txtUpasewaAnkaya.setText("");
        txtWWAnkaya.setText("");
        txtWWLPLKottashaya.setText("");
        txtWWWatinakama.setText("");
        txtWenathAbadithaUpakarana.setText("");
        txtWenathHani.setText("");
        txtWidyaPPawathwuSthanaya.setText("");
        txtWlabanaKottasaya.setText("");
        txtWlabanaPolisWasama.setText("");

        //-------------reset DECORATONS--------------//
        UserInputChecker.reset(txtAnuKanda);
        UserInputChecker.reset(txtAwasanWarataSKKottasaya);
        UserInputChecker.reset(txtAwasanWarataSKSthanaya);
        UserInputChecker.reset(txtDWWWatinakama);
        UserInputChecker.reset(txtDimanaWalaWatinakama);
        UserInputChecker.reset(txtDistrikkaya);
        UserInputChecker.reset(txtEmail);
        UserInputChecker.reset(txtGramaNWasama);
        UserInputChecker.reset(txtJangamaDurakathanaya);
        UserInputChecker.reset(txtJathikaHadunumAnkaya);
        UserInputChecker.reset(txtMulikaWatupeWatinakama);
        // UserInputChecker.reset(txtNilaAnkaya);
        UserInputChecker.reset(txtPolisWasamaSL);
        UserInputChecker.reset(txtPradeshiyaLekamKottasaya);
        UserInputChecker.reset(txtPraharayaSiduwuKottasaya);
        UserInputChecker.reset(txtPraharayaSiduwuSthanaya);
        UserInputChecker.reset(txtRanawiruHadunumAnkaya);
        UserInputChecker.reset(txtSahakarugeNama);
        UserInputChecker.reset(txtSahakarugeRLipinaya);
        UserInputChecker.reset(txtSahakarugeRakiyawa);
        UserInputChecker.reset(txtSampurnaNama);
        UserInputChecker.reset(txtSthawaraDurakathanaya);
        UserInputChecker.reset(txtSthiraLipinaya);
        UserInputChecker.reset(txtUpasewaAnkaya);
        UserInputChecker.reset(txtWWAnkaya);
        UserInputChecker.reset(txtWWLPLKottashaya);
        UserInputChecker.reset(txtWWWatinakama);
        UserInputChecker.reset(txtWidyaPPawathwuSthanaya);
        UserInputChecker.reset(txtWlabanaKottasaya);
        UserInputChecker.reset(txtWlabanaPolisWasama);

        UserInputChecker.reset(titleAUBKarainam);
        UserInputChecker.reset(titleAAbada);
        UserInputChecker.reset(titleDWWNHethu);
        UserInputChecker.reset(titleDaruwanSitinam);
        UserInputChecker.reset(titleSahanaLabinama);
        UserInputChecker.reset(titleWWNolabenam);

        DefaultTableModel tbl1 = (DefaultTableModel) tblAU.getModel();
        DefaultTableModel tbl2 = (DefaultTableModel) tblAULDinayanMitaPera.getModel();
        DefaultTableModel tbl3 = (DefaultTableModel) tblAthuruAbada.getModel();
        DefaultTableModel tbl4 = (DefaultTableModel) tblDWWNHethu.getModel();
        DefaultTableModel tbl5 = (DefaultTableModel) tblDaruwan.getModel();
        DefaultTableModel tbl6 = (DefaultTableModel) tblSahana.getModel();
        DefaultTableModel tbl7 = (DefaultTableModel) tblWWNHethu.getModel();

        tbl1.setRowCount(0);
        tbl2.setRowCount(0);
        tbl3.setRowCount(0);
        tbl4.setRowCount(0);
        tbl5.setRowCount(0);
        tbl6.setRowCount(0);
        tbl7.setRowCount(0);

        auldMitaPera.removeAll(auldMitaPera);

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelForm;
    private javax.swing.JButton btnAddAU;
    private javax.swing.JButton btnAddAULDMitaPasu;
    private javax.swing.JButton btnAddAULDMitaPera;
    private javax.swing.JButton btnAddAthuruAbada;
    private javax.swing.JButton btnAddDWWNH;
    private javax.swing.JButton btnAddDaruwa;
    private javax.swing.JButton btnAddNewMember;
    private javax.swing.JButton btnAddSahana;
    private javax.swing.JButton btnAddWWNH;
    private javax.swing.JButton btnChangePK;
    private javax.swing.JButton btnDAllAthuruAbada;
    private javax.swing.JButton btnDAllDWWNH;
    private javax.swing.JButton btnDAllDaruwa;
    private javax.swing.JButton btnDAllSahana;
    private javax.swing.JButton btnDAllWWNH;
    private javax.swing.JButton btnDeleteAU;
    private javax.swing.JButton btnDeleteAULDMitaPasu;
    private javax.swing.JButton btnDeleteAULDMitaPera;
    private javax.swing.JButton btnDeleteAthuruAbada;
    private javax.swing.JButton btnDeleteDWWNH;
    private javax.swing.JButton btnDeleteDaruwa;
    private javax.swing.JButton btnDeleteSahana;
    private javax.swing.JButton btnDeleteWWNH;
    private javax.swing.JCheckBox cbxAbadithaUBKarai;
    private javax.swing.JCheckBox cbxDaruwanSiti;
    private javax.swing.JCheckBox cbxSahanaLabiAtha;
    private javax.swing.JComboBox cmbAbadithaUpakarana;
    private javax.swing.JComboBox cmbAthuruAAdda1;
    private javax.swing.JComboBox cmbDWWLabanneda;
    private javax.swing.JComboBox cmbDWWNHethuwa;
    private javax.swing.JComboBox cmbDaruwaAbadithada;
    private javax.swing.JComboBox cmbDaruwageDanataThathwaya;
    private javax.swing.JComboBox cmbDikkasadada;
    private javax.swing.JComboBox cmbIpaimaSHPrathishathaya;
    private javax.swing.JComboBox cmbMasukaWLabanneda;
    private javax.swing.JComboBox cmbNawathaWiwahaUweda;
    private javax.swing.JComboBox cmbOthpalada;
    private javax.swing.JComboBox cmbPBadenawitaThanathura;
    private javax.swing.JComboBox cmbPolisiyataBahune;
    private javax.swing.JComboBox cmbSharirikaHani;
    private javax.swing.JComboBox cmbThrasthaKandayama;
    private javax.swing.JComboBox cmbWEAnuyuththada;
    private javax.swing.JComboBox cmbWWNHethuwa;
    private javax.swing.JComboBox cmbWisheshaEkakaya;
    private javax.swing.JComboBox cmbWishramaYWThanathura;
    private javax.swing.JComboBox cmbWisramaWLabanneda;
    private javax.swing.JComboBox cmbWiwahakada;
    private javax.swing.JComboBox cmbWiwahayaAsarthakada;
    private datechooser.beans.DateChooserCombo dtpAULDinayanMitaPasu;
    private datechooser.beans.DateChooserCombo dtpAULDinayanMitaPera;
    private datechooser.beans.DateChooserCombo dtpDOB;
    private datechooser.beans.DateChooserCombo dtpDaruwageDOB;
    private datechooser.beans.DateChooserCombo dtpPolisiyataBadunaDinaya;
    private datechooser.beans.DateChooserCombo dtpPraharayaSiduwuDinaya;
    private datechooser.beans.DateChooserCombo dtpSahanaAwasanWLDinaya;
    private datechooser.beans.DateChooserCombo dtpSamajikathwayaLDinaya;
    private datechooser.beans.DateChooserCombo dtpWEBadunaDinaya;
    private datechooser.beans.DateChooserCombo dtpWidyaPPawathwuDinaya;
    private datechooser.beans.DateChooserCombo dtpWishramaLabuDinaya;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
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
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblClear;
    private javax.swing.JLabel lblDina;
    private javax.swing.JLabel lblMasa;
    private javax.swing.JLabel lblMuluWatupeWatinakama;
    private javax.swing.JLabel lblSA;
    private javax.swing.JLabel lblWasara;
    private javax.swing.JTable tblAU;
    private javax.swing.JTable tblAULDinayanMitaPasu;
    private javax.swing.JTable tblAULDinayanMitaPera;
    private javax.swing.JTable tblAthuruAbada;
    private javax.swing.JTable tblDWWNHethu;
    private javax.swing.JTable tblDaruwan;
    private javax.swing.JTable tblSahana;
    private javax.swing.JTable tblWWNHethu;
    private javax.swing.JPanel titleAAbada;
    private javax.swing.JPanel titleAUBKarainam;
    private javax.swing.JPanel titleDWWNHethu;
    private javax.swing.JPanel titleDaruwanSitinam;
    private javax.swing.JPanel titleMWLabenam;
    private javax.swing.JPanel titleSahanaLabinama;
    private javax.swing.JPanel titleWEAnuyuththanam;
    private javax.swing.JPanel titleWWNolabenam;
    private javax.swing.JPanel titleWishramaWLanannenam;
    private javax.swing.JPanel titleWiwahakanam;
    private javax.swing.JTextField txtAnuKanda;
    private javax.swing.JTextField txtAthuruAbadaya;
    private javax.swing.JTextField txtAwasanWarataSKKottasaya;
    private javax.swing.JTextField txtAwasanWarataSKSthanaya;
    private javax.swing.JTextField txtDWWWatinakama;
    private javax.swing.JTextField txtDaruwageNama;
    private javax.swing.JTextField txtDimanaWalaWatinakama;
    private javax.swing.JTextField txtDistrikkaya;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGramaNWasama;
    private javax.swing.JTextField txtJangamaDurakathanaya;
    private javax.swing.JTextField txtJathikaHadunumAnkaya;
    private javax.swing.JTextField txtMulikaWatupeWatinakama;
    private javax.swing.JTextField txtNithyaSevaAnkaya;
    private javax.swing.JTextField txtPolisWasamaSL;
    private javax.swing.JTextField txtPradeshiyaLekamKottasaya;
    private javax.swing.JTextField txtPraharayaSiduwuKottasaya;
    private javax.swing.JTextField txtPraharayaSiduwuSthanaya;
    private javax.swing.JTextField txtRanawiruHadunumAnkaya;
    private javax.swing.JTextField txtSahakarugeDKA;
    private javax.swing.JTextField txtSahakarugeNama;
    private javax.swing.JTextArea txtSahakarugeRLipinaya;
    private javax.swing.JTextField txtSahakarugeRakiyawa;
    private javax.swing.JTextField txtSahanaLabanaSthanaya;
    private javax.swing.JTextField txtSahanaya;
    private javax.swing.JTextField txtSampurnaNama;
    private javax.swing.JTextField txtSthawaraDurakathanaya;
    private javax.swing.JTextArea txtSthiraLipinaya;
    private javax.swing.JTextField txtUpasewaAnkaya;
    private javax.swing.JTextField txtWWAnkaya;
    private javax.swing.JTextField txtWWLPLKottashaya;
    private javax.swing.JTextField txtWWWatinakama;
    private javax.swing.JTextField txtWenathAbadithaUpakarana;
    private javax.swing.JTextField txtWenathHani;
    private javax.swing.JTextField txtWidyaPPawathwuKo;
    private javax.swing.JTextField txtWidyaPPawathwuSthanaya;
    private javax.swing.JTextField txtWlabanaKottasaya;
    private javax.swing.JTextField txtWlabanaPolisWasama;
    // End of variables declaration//GEN-END:variables
}
