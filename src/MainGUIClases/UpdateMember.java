package MainGUIClases;

import OtherClases.Funtional;
import OtherClases.AULDinayan;
import OtherClases.PKGenaretor;
import OtherClases.UserInputChecker;
import SaveData.SaveMitaPasuAbadithaULDinayan;
import SaveData.SaveAbadithaUpakarana;
import SaveData.SaveAthuruAbada;
import SaveData.SaveDWWNHethu;
import SaveData.SaveDWWNSamajikayin;
import SaveData.SaveDWishramaWatup;
import SaveData.SaveMWLSamajikayin;
import SaveData.SaveMitaPeraAbadithaULDinayan;
import SaveData.SaveSDaruwan;
import SaveData.SaveSLSahana;
import SaveData.SaveSSwisheshaEkaka;
import SaveData.SaveSahakaru;
import SaveData.SaveWWNHethu;
import SaveData.SaveWWNSamajikayin;
import SaveData.SaveWisramaWatup;
import datechooser.beans.DateChooserCombo;
import java.awt.Color;
import java.awt.Component;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class UpdateMember extends javax.swing.JFrame {

    private final String FONT_MALITHI = "FMMalithi";
    private final String FONT_ISKOLAPOTHA = "Iskoola Pota";

    public UpdateMember() {
        this.setResizable(false);
        initComponents();
        changeTableColomnNames();
        lblDWWWorn.setVisible(false);
        lblWWWorn.setVisible(false);
    }

    private void showAllWisramaWatup() {

        DefaultTableModel ww = (DefaultTableModel) tblWWNHethu.getModel();
        DefaultTableModel dww = (DefaultTableModel) tblDWWNHethu.getModel();
        ww.setRowCount(0);
        dww.setRowCount(0);

        if (tblSeachedData.getSelectedRow() != -1) {
            String SA = tblSeachedData.getValueAt(tblSeachedData.getSelectedRow(), 1).toString();
            try {
                boolean x = getXValue(SA);//WW x=true ->labe ,x=false->nolabe.
                boolean y = getYValue(SA);//DWW y=true ->labe ,y=false->nolabe.
                cmbWisramaWLabanneda.setSelectedIndex(x ? 0 : 1);
                cmbDWWLabanneda.setSelectedIndex(y ? 0 : 1);

                setEnableWisramaWatup(x, y);
                //x:විශ්‍රාම වැටුප් ->yes if true
                if (x) {
                    loadWishramaWatupData(SA);
                } else {
                    DefaultTableModel model = (DefaultTableModel) tblWWNHethu.getModel();
                    //x:false load නොලැබීමට හේතු   
                    String sql = "SELECT * FROM WWNS WHERE SA='" + SA + "'";
                    ResultSet rs = SQLConnection.SqlConnection.getData(sql);
                    model.setRowCount(0);
                    rs.first();
                    String A_SA = rs.getString("SA");
                    String sql2 = "SELECT * FROM SWWNH WHERE SA='" + A_SA + "'";
                    ResultSet rs2 = SQLConnection.SqlConnection.getData(sql2);
                    while (rs2.next()) {
                        Vector v = new Vector();
                        v.add(rs2.getString("HTW"));
                        model.addRow(v);
                    }
                }
                //y:දුබලතා විශ්‍රාම වැටුප් ->yes if true
                if (y) {
                    loadDWishramaWatupData(SA);
                } else {
                    //x:false load නොලැබීමට හේතු   
                    DefaultTableModel model = (DefaultTableModel) tblDWWNHethu.getModel();
                    //x:false load නොලැබීමට හේතු   
                    String sql = "SELECT * FROM DWWNS WHERE SA='" + SA + "'";
                    ResultSet rs = SQLConnection.SqlConnection.getData(sql);
                    model.setRowCount(0);
                    rs.first();
                    String A_SA = rs.getString("SA");
                    String sql2 = "SELECT * FROM SDWWNH WHERE SA='" + A_SA + "'";
                    ResultSet rs2 = SQLConnection.SqlConnection.getData(sql2);
                    while (rs2.next()) {
                        Vector v = new Vector();
                        v.add(rs2.getString("HTW"));
                        model.addRow(v);
                    }
                }

            } catch (Exception e) {
               new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
            }
        }
    }

    private void updateWishramaWatup() {
        if (tblSeachedData.getSelectedRow() != -1) {
            String SA = tblSeachedData.getValueAt(tblSeachedData.getSelectedRow(), 1).toString();

            if (isWisramaWatupDataReady()) {
                try {
                    //BOOLEAN BEFORE -bA->විශ්‍රාම වැටුප් ලබේද 
                    //BOOLEAN BEFORE -bB->දුබලතා විශ්‍රාම වැටුප් ලබේද 

                    //BOOLEAN NOW -nA->විශ්‍රාම වැටුප් ලබේද 
                    //BOOLEAN NOW -nB->දුබලතා විශ්‍රාම වැටුප් ලබේද 
                    boolean bA = SQLConnection.SqlConnection.getData("SELECT * FROM WW WHERE SA='" + SA + "'").first();
                    boolean bB = SQLConnection.SqlConnection.getData("SELECT * FROM DWW WHERE SA='" + SA + "'").first();
                    boolean nA = getBooleanValue(cmbWisramaWLabanneda.getSelectedItem().toString());
                    boolean nB = getBooleanValue(cmbDWWLabanneda.getSelectedItem().toString());

                    //---------------------------------------------------------------------------------
                    //ww 
                    if (bA) {
                        if (nA) {
                            //update ww
                            SQLConnection.SqlConnection.updateDB("UPDATE WW SET PLK='"
                                    + txtPradeshiyaLekamKottasaya.getText() + "',"
                                    + "WWA='" + txtWWAnkaya.getText() + "',"
                                    + "WTKM='" + txtWWWatinakama.getText()
                                    + "' WHERE SA='" + SA + "'"
                            );

                        } else {
                            //delete ww/www and add wwns/wwnh
                            SQLConnection.SqlConnection.updateDB("DELETE FROM WW WHERE SA='" + SA + "'");

                            new SaveWWNSamajikayin(SA).save();
                            for (int i = 0; i < tblWWNHethu.getRowCount(); i++) {
                                String htw = tblWWNHethu.getValueAt(i, 0).toString();
                                new SaveWWNHethu(SA, htw).save();
                            }

                        }
                    } else {
                        if (nA) {
                            //add ww/www and delete wwns/wwnh
                            SQLConnection.SqlConnection.updateDB("DELETE FROM SWWNH WHERE SA='" + SA + "'");
                            SQLConnection.SqlConnection.updateDB("DELETE FROM WWNS WHERE SA='" + SA + "'");

                            String PLK = txtPradeshiyaLekamKottasaya.getText();
                            String WWA = txtWWAnkaya.getText();
                            String wwWTKM = txtWWWatinakama.getText();
                            new SaveWisramaWatup(SA, PLK, WWA, wwWTKM).save();
                            //USER MUST ADD VALUES TO WWW FROM WATUP OPARATION WINDOW
                        } else {
                            //update wwnh
                            SQLConnection.SqlConnection.updateDB("DELETE FROM SWWNH WHERE SA='" + SA + "'");
                            for (int i = 0; i < tblWWNHethu.getRowCount(); i++) {
                                String htw = tblWWNHethu.getValueAt(i, 0).toString();
                                new SaveWWNHethu(SA, htw).save();
                            }
                        }
                    }
                    //---------------------------------------------------------------------------------
                    //dww
                    if (bB) {
                        if (nB) {
                            //update dww
                            SQLConnection.SqlConnection.updateDB("UPDATE DWW SET PLK='"
                                    + txtPradeshiyaLekamKottasaya.getText() + "',"
                                    + "WWA='" + txtWWAnkaya.getText() + "',"
                                    + "WTKM='" + txtDWWWatinakama.getText()
                                    + "' WHERE SA='" + SA + "'"
                            );

                        } else {
                            //delete dww/dwww and add dwwns/dwwnh
                            SQLConnection.SqlConnection.updateDB("DELETE FROM DWW WHERE SA='" + SA + "'");

                            new SaveDWWNSamajikayin(SA).save();
                            for (int i = 0; i < tblDWWNHethu.getRowCount(); i++) {
                                String htw = tblDWWNHethu.getValueAt(i, 0).toString();
                                new SaveDWWNHethu(SA, htw).save();
                            }
                        }
                    } else {
                        if (nB) {
                            //add dww/dwww and delete dwwns/dwwnh
                            SQLConnection.SqlConnection.updateDB("DELETE FROM SDWWNH WHERE SA='" + SA + "'");
                            SQLConnection.SqlConnection.updateDB("DELETE FROM DWWNS WHERE SA='" + SA + "'");

                            String PLK = txtPradeshiyaLekamKottasaya.getText();
                            String WWA = txtWWAnkaya.getText();
                            String WTKM = txtDWWWatinakama.getText();
                            new SaveDWishramaWatup(SA, PLK, WWA, WTKM).save();
                            //USER MUST ADD VALUES TO WWW FROM WATUP OPARATION WINDOW
                        } else {
                            //update dwwnh
                            SQLConnection.SqlConnection.updateDB("DELETE FROM SDWWNH WHERE SA='" + SA + "'");
                            for (int i = 0; i < tblDWWNHethu.getRowCount(); i++) {
                                String htw = tblDWWNHethu.getValueAt(i, 0).toString();
                                new SaveDWWNHethu(SA, htw).save();
                            }
                        }
                    }
                    //---------------------------------------------------------------------------------

                } catch (Exception e) {
                   new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
                }
            }

        }
    }

    private boolean isWisramaWatupDataReady() {
        boolean C01 = UserInputChecker.check(txtWWAnkaya);
        boolean C02 = UserInputChecker.check(txtWWLPLKottashaya);
        boolean C05 = UserInputChecker.check(titleWWNolabenam, tblWWNHethu);
        boolean C06 = UserInputChecker.check(titleDWWNHethu, tblDWWNHethu);
        boolean C07 = UserInputChecker.isANumber(txtWWWatinakama);
        boolean C08 = UserInputChecker.isANumber(txtDWWWatinakama);
        return C01 && C02 && C05 && C06 && C07 && C08;
    }

    private void loadWishramaWatupData(final String SA) throws Exception {
        String sql1 = "SELECT * FROM WW WHERE SA='" + SA + "'";
        ResultSet rs = SQLConnection.SqlConnection.getData(sql1);
        if (rs.first()) {
            String PLK = rs.getString("PLK");
            String WWA = rs.getString("WWA");
            String WTKM = rs.getString("WTKM");
            txtWWAnkaya.setText(WWA);
            txtWWLPLKottashaya.setText(PLK);
            txtWWWatinakama.setText(WTKM);
        }

    }

    private void loadDWishramaWatupData(final String SA) throws Exception {
        String sql1 = "SELECT * FROM DWW WHERE SA='" + SA + "'";
        ResultSet rs = SQLConnection.SqlConnection.getData(sql1);
        if (rs.first()) {
            String PLK = rs.getString("PLK");
            String WWA = rs.getString("WWA");
            String WTKM = rs.getString("WTKM");
            txtWWAnkaya.setText(WWA);
            txtWWLPLKottashaya.setText(PLK);
            txtDWWWatinakama.setText(WTKM);
        }

    }

    private boolean getXValue(final String SA) throws Exception {
        String sql1 = "SELECT * FROM WWNS WHERE SA='" + SA + "'";
        ResultSet rs1 = SQLConnection.SqlConnection.getData(sql1);
        return !rs1.first();
    }

    private boolean getYValue(String SA) throws Exception {
        String sql1 = "SELECT * FROM DWWNS WHERE SA='" + SA + "'";
        ResultSet rs1 = SQLConnection.SqlConnection.getData(sql1);
        return !rs1.first();

    }

    private void showAllMasikaWatupData() {
        if (tblSeachedData.getSelectedRow() != -1) {
            String SA = tblSeachedData.getValueAt(tblSeachedData.getSelectedRow(), 1).toString();
            try {
                String sql1 = "SELECT * FROM MWLS WHERE SA='" + SA + "'";
                ResultSet rs1 = SQLConnection.SqlConnection.getData(sql1);
                if (rs1.first()) {
                    //CODE HIER
                    setEnableMasikaWatup(true);
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
                    setEnableMasikaWatup(false);
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

    private void updateMasikaWatupData() {
        if (tblSeachedData.getSelectedRow() != -1) {
            String SA = tblSeachedData.getValueAt(tblSeachedData.getSelectedRow(), 1).toString();
            if (isMasikaWatupDataReady()) {
                try {
                    if (getBooleanValue(cmbMasukaWLabanneda.getSelectedItem().toString())) {
                        String sql1 = "SELECT LK,PS FROM MWLS WHERE SA='" + SA + "'";
                        ResultSet rs1 = SQLConnection.SqlConnection.getData(sql1);
                        if (rs1.first()) {
                            rs1.beforeFirst();
                            String sql2 = "UPDATE MWLS SET "
                                    + "LK='" + txtWlabanaKottasaya.getText() + "',"
                                    + "PS='" + txtWlabanaPolisWasama.getText() + "',"
                                    + "WWT='" + txtMulikaWatupeWatinakama.getText() + "',"
                                    + "DWT='" + txtDimanaWalaWatinakama.getText() + "'"
                                    + "WHERE SA='" + SA + "'";
                            SQLConnection.SqlConnection.updateDB(sql2);

                        } else {
                            String LK = txtWlabanaKottasaya.getText();
                            String PS = txtWlabanaPolisWasama.getText();
                            String WWT = txtMulikaWatupeWatinakama.getText();
                            String DWT = txtDimanaWalaWatinakama.getText();

                            new SaveMWLSamajikayin(SA, LK, PS, WWT, DWT).save();
                        }

                    } else {
                        String sql3 = "DELETE FROM MWLS WHERE SA='" + SA + "'";
                        SQLConnection.SqlConnection.updateDB(sql3);
                    }
                } catch (Exception e) {
                   new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
                }
            }
        }
    }

    private boolean isMasikaWatupDataReady() {
        return UserInputChecker.check(txtWlabanaKottasaya)
                && UserInputChecker.check(txtWlabanaPolisWasama)
                && UserInputChecker.isANumber(txtMulikaWatupeWatinakama)
                && UserInputChecker.isANumber(txtDimanaWalaWatinakama);

    }

    private void setEnableMasikaWatup(boolean b) {
        if (b) {
            cmbMasukaWLabanneda.setSelectedIndex(0);
        } else {
            cmbMasukaWLabanneda.setSelectedIndex(1);
            lblMuluWatupeWatinakama.setText("????");
        }

        titleMWLabenam.setEnabled(b);
        for (int i = 0; i < titleMWLabenam.getComponentCount(); i++) {
            titleMWLabenam.getComponent(i).setEnabled(b);
        }

        txtMulikaWatupeWatinakama.setEnabled(b);
        txtDimanaWalaWatinakama.setEnabled(b);

    }

    private void showAllSahanaData() {
        if (tblSeachedData.getSelectedRow() != -1) {
            try {
                DefaultTableModel modelSahana = (DefaultTableModel) tblSahana.getModel();
                String SA = tblSeachedData.getValueAt(tblSeachedData.getSelectedRow(), 1).toString();
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

    private void updateAllSahana() {
        if (tblSeachedData.getSelectedRow() != -1) {
            try {
                String SA = tblSeachedData.getValueAt(tblSeachedData.getSelectedRow(), 1).toString();

                if (isSahanaDataReady()) {
                    if (cbxSahanaLabiAtha.isSelected()) {
                        String sql1 = "DELETE FROM SLS WHERE SA='" + SA + "'";
                        SQLConnection.SqlConnection.updateDB(sql1);

                        for (int i = 0; i < tblSahana.getRowCount(); i++) {
                            String SHA = tblSahana.getValueAt(i, 0).toString();
                            String SLS = tblSahana.getValueAt(i, 1).toString();
                            String SAWLS = tblSahana.getValueAt(i, 2).toString();
                            new SaveSLSahana(SA, SHA, SLS, SAWLS).save();
                        }

                    } else {
                        String sql1 = "DELETE FROM SLS WHERE SA='" + SA + "'";
                        SQLConnection.SqlConnection.updateDB(sql1);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "කරුණාකර නිවැරදි දත්ත පමණක් ඇතුළු කරන්න..!", "දත්ත නිවැරදි නොවේ", JOptionPane.INFORMATION_MESSAGE);

                }
            } catch (Exception e) {
               new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
            }
        }
    }

    private boolean isSahanaDataReady() {
        return UserInputChecker.check(titleSahanaLabinama, tblSahana);
    }

    private void setEnableSahana(boolean b) {
        cbxSahanaLabiAtha.setSelected(b);
        titleSahanaLabinama.setEnabled(b);
        for (int i = 0; i < titleSahanaLabinama.getComponentCount(); i++) {
            titleSahanaLabinama.getComponent(i).setEnabled(b);
        }
        tblSahana.setEnabled(b);
        final DefaultTableModel modelSahana = (DefaultTableModel) tblSahana.getModel();
        modelSahana.setRowCount(0);
        if (!b) {
            TitledBorder tb = (TitledBorder) titleSahanaLabinama.getBorder();
            titleSahanaLabinama.setBorder(BorderFactory.createTitledBorder(null, tb.getTitle(),
                    TitledBorder.DEFAULT_JUSTIFICATION,
                    TitledBorder.DEFAULT_POSITION,
                    new java.awt.Font("FMMalithi", 0, 14)));
        }
    }

    private void showAllAbadithaUpakaranaData() {
        if (tblSeachedData.getSelectedRow() != -1) {
            final DefaultTableModel modelAU = (DefaultTableModel) tblAU.getModel();
            final DefaultTableModel modelAULDMitaPera = (DefaultTableModel) tblAULDinayanMitaPasu.getModel();
            final DefaultTableModel modelAULDMitaPasu = (DefaultTableModel) tblAULDinayanMitaPera.getModel();

            final ArrayList<String> AUList = new ArrayList<>();
            try {
                String SA = tblSeachedData.getValueAt(tblSeachedData.getSelectedRow(), 1).toString();
                auldMETAPERA.removeAll(auldMETAPERA);
                auldMETAPASU.removeAll(auldMETAPASU);
                String sql1 = " SELECT * FROM SAU WHERE SA='" + SA + "'";
                ResultSet rs1 = SQLConnection.SqlConnection.getData(sql1);
                if (rs1.first()) {
                    setEnableAbadithUpakarana(true);
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
                    setEnableAbadithUpakarana(false);
                }
            } catch (Exception e) {
               new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
            }

        }
    }

    private void updateAbadithaUpakarana() {
        if (tblSeachedData.getSelectedRow() != -1) {
            if (isAUDataReady()) {
                try {
                    String SA = tblSeachedData.getValueAt(tblSeachedData.getSelectedRow(), 1).toString();
                    String sql1 = "SELECT UA FROM SAU WHERE SA='" + SA + "'";
                    ResultSet rs1 = SQLConnection.SqlConnection.getData(sql1);
                    while (rs1.next()) {
                        String UA = rs1.getString("UA");
                        String sql2 = "DELETE FROM AULDMITAPASU WHERE UA='" + UA + "'";
                        SQLConnection.SqlConnection.updateDB(sql2);
                        String sql3 = "DELETE FROM AULDMITAPERA WHERE UA='" + UA + "'";
                        SQLConnection.SqlConnection.updateDB(sql3);
                    }
                    String sql3 = "DELETE FROM SAU WHERE SA='" + SA + "'";
                    SQLConnection.SqlConnection.updateDB(sql3);

                    if (cbxAbadithaUBKarai.isSelected()) {
                        for (int i = 0; i < tblAU.getRowCount(); i++) {
                            String AUN = tblAU.getValueAt(i, 0).toString();
                            String AUA = PKGenaretor.getNextPK("SAU", "UA");
                            new SaveAbadithaUpakarana(SA, AUA, AUN).save();
                            if (!auldMETAPERA.isEmpty()) {
                                for (AULDinayan auld1 : auldMETAPERA) {
                                    if (auld1.getIndex() == i) {
                                        String ULD = auld1.getDinaya();
                                        new SaveMitaPeraAbadithaULDinayan(AUA, ULD).save();
                                    }
                                }

                            }

                            if (!auldMETAPASU.isEmpty()) {
                                for (AULDinayan auld1 : auldMETAPASU) {
                                    if (auld1.getIndex() == i) {
                                        String ULD = auld1.getDinaya();
                                        new SaveMitaPasuAbadithaULDinayan(AUA, ULD).save();
                                    }
                                }

                            }

                        }
                    }

                } catch (Exception e) {
                   new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "කරුණාකර නිවැරදි දත්ත පමණක් ඇතුළු කරන්න..!", "දත්ත නිවැරදි නොවේ", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private boolean isAUDataReady() {
        return UserInputChecker.check(titleAUBKarainam, tblAU);
    }

    private void setEnableAbadithUpakarana(boolean b) {
        cbxAbadithaUBKarai.setSelected(b);
        titleAUBKarainam.setEnabled(b);
        for (int i = 0; i < titleAUBKarainam.getComponentCount(); i++) {
            titleAUBKarainam.getComponent(i).setEnabled(b);
        }
        tblAU.setEnabled(b);
        tblAULDinayanMitaPasu.setEnabled(b);

        //Code leter TitlePane Decoration
        if (!b) {
            final DefaultTableModel modelAU = (DefaultTableModel) tblAU.getModel();
            final DefaultTableModel modelAULD = (DefaultTableModel) tblAULDinayanMitaPasu.getModel();
            modelAU.setRowCount(0);
            modelAULD.setRowCount(0);

            TitledBorder tb = (TitledBorder) titleAUBKarainam.getBorder();
            titleAUBKarainam.setBorder(BorderFactory.createTitledBorder(null, tb.getTitle(),
                    TitledBorder.DEFAULT_JUSTIFICATION,
                    TitledBorder.DEFAULT_POSITION,
                    new java.awt.Font("FMMalithi", 0, 14)));
        }
    }

    private void updateThanathura() {
        if (tblSeachedData.getSelectedRow() != -1) {
            if (isThanathuraDataReady()) {
                try {
                    String SA = tblSeachedData.getValueAt(tblSeachedData.getSelectedRow(), 1).toString();
                    String sql1 = "UPDATE ST SET "
                            + "NA='" + txtNilaAnkaya.getText() + "',"
                            + "UA='" + txtUpasewaAnkaya.getText() + "',"
                            + "RHA='" + txtRanawiruHadunumAnkaya.getText() + "',"
                            + "TNTR1='" + Integer.toString(cmbPBadenawitaThanathura.getSelectedIndex() < 2
                                            ? cmbPBadenawitaThanathura.getSelectedIndex() + 1
                                            : cmbPBadenawitaThanathura.getSelectedIndex() + 4) + "',"
                            + "TNTR2='" + (cmbWishramaYWThanathura.getSelectedIndex() + 1) + "'"
                            + " WHERE SA='" + SA + "'";

                    SQLConnection.SqlConnection.updateDB(sql1);

                    String csql = "SELECT SA FROM SSWE WHERE SA='" + SA + "'";
                    ResultSet crs = SQLConnection.SqlConnection.getData(csql);
                    if (getBooleanValue(cmbWEAnuyuththada.getSelectedItem().toString())) {
                        if (crs.first()) {
                            String sql2 = "UPDATE SSWE SET "
                                    + "SSWE='" + (cmbWisheshaEkakaya.getSelectedIndex() + 1) + "',"
                                    + "DB='" + dateToString(dtpWEBadunaDinaya.getSelectedDate().getTime()) + "',"
                                    + "KAAA='" + txtAnuKanda.getText() + "'"
                                    + " WHERE SA='" + SA + "'";
                            SQLConnection.SqlConnection.updateDB(sql2);
                        } else {
                            String WEA = Integer.toString((cmbWisheshaEkakaya.getSelectedIndex() + 1));
                            String BD = dateToString(dtpWEBadunaDinaya.getSelectedDate().getTime());
                            String KAAA = txtAnuKanda.getText();
                            new SaveSSwisheshaEkaka(SA, WEA, BD, KAAA).save();
                        }
                    } else {
                        String sql3 = "DELETE FROM SSWE WHERE SA='" + SA + "'";
                        SQLConnection.SqlConnection.updateDB(sql3);
                    }

                } catch (Exception e) {
                   new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "කරුණාකර නිවැරදි දත්ත පමණක් ඇතුළු කරන්න..!", "දත්ත නිවැරදි නොවේ", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {

        }
    }

    private boolean isThanathuraDataReady() {
        boolean C01 = UserInputChecker.check(txtNilaAnkaya);
        boolean C02 = UserInputChecker.check(txtUpasewaAnkaya);
        boolean C03 = UserInputChecker.check(txtRanawiruHadunumAnkaya);
        boolean C04 = UserInputChecker.check(txtAnuKanda);

        return C01 && C02 && C03 && C04;
    }

    private void showAllThanathuraData() {
        if (tblSeachedData.getSelectedRow() != -1) {
            try {
                String SA = tblSeachedData.getValueAt(tblSeachedData.getSelectedRow(), 1).toString();

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
                    cmbPBadenawitaThanathura.setSelectedIndex(TNTR1 < 2 ? TNTR1 - 1 : 2);
                    cmbWishramaYWThanathura.setSelectedIndex(TNTR2 - 1);

                    String sql2 = "SELECT * FROM SSWE WHERE SA='" + SA + "'";
                    ResultSet rs2 = SQLConnection.SqlConnection.getData(sql2);
                    if (rs2.first()) {
                        int SSWE = rs2.getInt("SSWE");
                        Date DB = rs2.getDate("DB");
                        String KAAA = rs2.getString("KAAA");

                        cmbWisheshaEkakaya.setSelectedIndex(SSWE - 1);
                        setDataChooserDate(DB, dtpWEBadunaDinaya);
                        txtAnuKanda.setText(KAAA);
                        setEnableWE(true);
                    } else {
                        setEnableWE(false);
                    }
                }

            } catch (Exception e) {
               new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
            }
        }
    }

    private void setEnableWE(boolean b) {
        if (b) {
            cmbWEAnuyuththada.setSelectedIndex(0);
        } else {
            cmbWEAnuyuththada.setSelectedIndex(1);
            txtAnuKanda.setText("");
        }
        titleWEAnuyuththanam.setEnabled(b);
        for (int i = 0; i < titleWEAnuyuththanam.getComponentCount(); i++) {
            titleWEAnuyuththanam.getComponent(i).setEnabled(b);
        }
    }

    private void setDataChooserDate(Date date, DateChooserCombo combo) {
        try {
            int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
            int manth = Integer.parseInt(new SimpleDateFormat("MM").format(date));
            int day = Integer.parseInt(new SimpleDateFormat("dd").format(date));
            combo.setSelectedDate(new GregorianCalendar(year, manth - 1, day));
        } catch (NumberFormatException e) {
        }
    }

    private void showAllWiwahayaData() {
        if (tblSeachedData.getSelectedRow() != -1) {
            try {
                String SA = tblSeachedData.getValueAt(tblSeachedData.getSelectedRow(), 1).toString();
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
                    txtSahakarugeNama.setText("");
                    txtSahakarugeRLipinaya.setText("");
                    txtSahakarugeDKA.setText("");
                    txtSahakarugeRakiyawa.setText("");
                }

                txtSahakarugeNama.setBackground(Color.white);
                txtSahakarugeRLipinaya.setBackground(Color.white);
                txtSahakarugeDKA.setBackground(Color.white);
                txtSahakarugeRakiyawa.setBackground(Color.white);

                String sql2 = "SELECT DKKD,WD,NWUD,WAWATBD FROM SMJK WHERE SA='" + SA + "'";
                ResultSet rs2 = SQLConnection.SqlConnection.getData(sql2);
                if (rs2.first()) {
                    String DKKD = rs2.getString("DKKD");
                    String WD = rs2.getString("WD");
                    String NWUD = rs2.getString("NWUD");
                    String WAWATBD = rs2.getString("WAWATBD");

                    setComboBoolean(cmbDikkasadada, DKKD);
                    setComboBoolean(cmbNawathaWiwahaUweda, NWUD);
                    setComboBoolean(cmbWiwahayaAsarthakada, WAWATBD);
                    cbxWiwahakada.setSelected(getBooleanValue(WD));
                } else {

                }

            } catch (Exception e) {
               new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
            }
        }

    }

    private void updateWiwahaya() {
        if (tblSeachedData.getSelectedRow() != -1) {

            try {
                String SA = tblSeachedData.getValueAt(tblSeachedData.getSelectedRow(), 1).toString();
                String sqlFR = "SELECT  *  FROM  SS WHERE SA='" + SA + "'";
                final boolean haveS = SQLConnection.SqlConnection.getData(sqlFR).first();
                if (cbxWiwahakada.isSelected()) {
                    if (isWiwahayaDataReady()) {
                        if (haveS) {
                            String sql1 = "UPDATE SS SET "
                                    + "SSN ='" + txtSahakarugeNama.getText() + "',"
                                    + "SRL ='" + txtSahakarugeRLipinaya.getText() + "',"
                                    + "SDA ='" + txtSahakarugeDKA.getText() + "',"
                                    + "SR ='" + txtSahakarugeRakiyawa.getText() + "'"
                                    + " WHERE SA='" + SA + "'";
                            SQLConnection.SqlConnection.updateDB(sql1);

                        } else {
                            String SRL = txtSahakarugeRLipinaya.getText();
                            String SSDA = txtSahakarugeDKA.getText();
                            String SR = txtSahakarugeRakiyawa.getText();
                            String SSN = txtSahakarugeNama.getText();
                            new SaveSahakaru(SA, SRL, SSDA, SR, SSN).save();
                        }

                    } else {
                        JOptionPane.showMessageDialog(this, "කරුණාකර නිවැරදි දත්ත පමණක් ඇතුළු කරන්න..!", "දත්ත නිවැරදි නොවේ", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    if (haveS) {
                        String sql = "DELETE FROM SS WHERE SA='" + SA + "'";
                        SQLConnection.SqlConnection.updateDB(sql);
                    }
                }
                String sql2 = "UPDATE SMJK SET "
                        + "DKKD ='" + cmbDikkasadada.getSelectedItem().toString() + "',"
                        + "NWUD ='" + cmbNawathaWiwahaUweda.getSelectedItem().toString() + "',"
                        + "WAWATBD ='" + cmbWiwahayaAsarthakada.getSelectedItem().toString() + "',"
                        + "WD ='" + getStringBoolean(cbxWiwahakada.isSelected()) + "'"
                        + " WHERE SA='" + SA + "'";
                SQLConnection.SqlConnection.updateDB(sql2);

            } catch (Exception e) {
               new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
            }
        }
    }

    private String getStringBoolean(boolean b) {
        if (b) {
            return "Tõ";
        } else {
            return "ke;";
        }
    }

    private boolean isWiwahayaDataReady() {
        boolean C01 = UserInputChecker.check(txtSahakarugeNama);
        boolean C02 = UserInputChecker.check(txtSahakarugeRLipinaya);
        boolean C03 = UserInputChecker.check(txtSahakarugeDKA);
        boolean C04 = UserInputChecker.check(txtSahakarugeRakiyawa);

        return C01 && C02 && C03 && C04;
    }

    private void setComboBoolean(JComboBox combo, String value) {
        if (value.equals("Tõ")) {
            combo.setSelectedIndex(0);
        } else {
            combo.setSelectedIndex(1);
        }
    }

    private void showAllTrasthaData() {
        try {
            String SA = tblSeachedData.getValueAt(tblSeachedData.getSelectedRow(), 1).toString();
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

                cmbThrasthaKandayama.setSelectedIndex(TRK - 1);
                setDataChooserDate(LUD, dtpPraharayaSiduwuDinaya);
                txtPraharayaSiduwuKottasaya.setText(LUK);
                txtPraharayaSiduwuSthanaya.setText(LUS);
                if (!setComboBoxData(cmbSharirikaHani, ASB)) {
                    cmbSharirikaHani.setSelectedIndex(cmbSharirikaHani.getItemCount() - 1);
                    txtWenathHani.setEnabled(true);
                    txtWenathHani.setText(ASB);
                } else {
                    txtWenathHani.setEnabled(false);
                    txtWenathHani.setText("");
                }
                setComboBoxData(cmbOthpalada, OWAD);
                setComboBoxData(cmbIpaimaSHPrathishathaya, ISHP + "%");

                String sql3 = "SELECT * FROM SJWP WHERE SA='" + SA + "'";
                ResultSet rs2 = SQLConnection.SqlConnection.getData(sql3);
                if (rs2.first()) {
                    String SKS = rs2.getString("SKS");
                    String SKK = rs2.getString("SKK");
                    Date SKD = rs2.getDate("SKD");

                    setDataChooserDate(SKD, dtpWidyaPPawathwuDinaya);
                    txtWidyaPPawathwuSthanaya.setText(SKS);
                    txtWidyaPPawathwuKo.setText(SKK);
                } else {

                }
                String sql4 = "SELECT * FROM AASS WHERE SA='" + SA + "'";
                ResultSet rs3 = SQLConnection.SqlConnection.getData(sql4);
                if (rs3.first()) {
                    setEnableAA(true);
                    cmbAthuruAAdda1.setSelectedIndex(0);
                    rs3.beforeFirst();
                    DefaultTableModel model = (DefaultTableModel) tblAthuruAbada.getModel();
                    model.setRowCount(0);
                    while (rs3.next()) {
                        Vector v = new Vector();
                        v.add(rs3.getString("ABD"));
                        model.addRow(v);
                    }

                } else {
                    setEnableAA(false);
                    cmbAthuruAAdda1.setSelectedIndex(1);
                    DefaultTableModel model = (DefaultTableModel) tblAthuruAbada.getModel();
                    model.setRowCount(0);
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setEnableAA(boolean b) {

        for (int i = 0; i < titleAAbada.getComponentCount(); i++) {
            titleAAbada.getComponent(i).setEnabled(b);
        }
        tblAthuruAbada.setEnabled(b);
        titleAAbada.setEnabled(b);
        if (!b) {
            TitledBorder tb = (TitledBorder) titleAAbada.getBorder();
            titleAAbada.setBorder(BorderFactory.createTitledBorder(null, tb.getTitle(),
                    TitledBorder.DEFAULT_JUSTIFICATION,
                    TitledBorder.DEFAULT_POSITION,
                    new java.awt.Font("FMMalithi", 0, 14)));
        }

    }

    private void showAllSamajikayaData() {
        //Samajikayage Thorathuru
        try {
            String sa = tblSeachedData.getValueAt(tblSeachedData.getSelectedRow(), 1).toString();
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
                setDataChooserDate(SLD, dtpSamajikathwayaLDinaya);
                txtSampurnaNama.setText(SN);
                txtSthiraLipinaya.setText(SL);
                setDataChooserDate(PBD, dtpPolisiyataBadunaDinaya);
                txtAwasanWarataSKSthanaya.setText(AWSKS);
                txtAwasanWarataSKKottasaya.setText(AWSKK);
                setDataChooserDate(WLD, dtpWishramaLabuDinaya);
                displaySavaKalaya();
                txtPolisWasamaSL.setText(PW);
                txtPradeshiyaLekamKottasaya.setText(PLK);
                txtGramaNWasama.setText(GNW);
                txtDistrikkaya.setText(DTRK);
                txtSthawaraDurakathanaya.setText(SDA);
                txtJangamaDurakathanaya.setText(JDA);
                txtEmail.setText(WL);
                setDataChooserDate(UD, dtpDOB);
                setComboBoxData(cmbPolisiyataBahune, NSUS);
                txtJathikaHadunumAnkaya.setText(JHA);
                displayWayasa();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void showAllDaruwanData() {
        String SA = tblSeachedData.getValueAt(tblSeachedData.getSelectedRow(), 1).toString();
        try {
            String sql1 = "SELECT * FROM SD WHERE SA='" + SA + "'";
            ResultSet rs = SQLConnection.SqlConnection.getData(sql1);
            DefaultTableModel model = (DefaultTableModel) tblDaruwan.getModel();
            model.setRowCount(0);
            if (rs.first()) {
                cbxDaruwanSiti.setSelected(true);
                setEnableDaruwan(true);
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
                cbxDaruwanSiti.setSelected(false);
                setEnableDaruwan(false);
            }
        } catch (Exception e) {
           new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
        }
        txtDaruwageNamaEdit.setText("");
    }

    private boolean setComboBoxData(JComboBox combo, String item) {
        boolean b = false;
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (combo.getItemAt(i).toString().equals(item)) {
                combo.setSelectedIndex(i);
                b = true;
                break;
            }
        }
        return b;
    }

    private void displayWayasa() {
        LocalDate ld1 = dtpDOB.getSelectedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ld2 = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Period p = Period.between(ld1, ld2);
        lblWasaraW.setText(Integer.toString(p.getYears()));
        lblMasaW.setText(Integer.toString(p.getMonths()));
        lblDinaW.setText(Integer.toString(p.getDays()));
    }

    private void displaySavaKalaya() {
        LocalDate ld1 = dtpPolisiyataBadunaDinaya.getSelectedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ld2 = dtpWishramaLabuDinaya.getSelectedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Period p = Period.between(ld1, ld2);
        lblWasara.setText(Integer.toString(p.getYears()));
        lblMasa.setText(Integer.toString(p.getMonths()));
        lblDina.setText(Integer.toString(p.getDays()));
    }

    private void seachDateBySA() {
        try {
            DefaultTableModel tModel = (DefaultTableModel) tblSeachedData.getModel();
            tModel.setRowCount(0);
            String quary = "SELECT SA,SN FROM SMJK WHERE SA LIKE '%" + txtSeachField.getText() + "%'";
            ResultSet data = SQLConnection.SqlConnection.getData(quary);
            while (data.next()) {
                String seachBy = data.getString("SA");
                String SA = data.getString("SA");
                String SN = data.getString("SN");
                Vector v = new Vector();
                v.add(seachBy);
                v.add(SA);
                v.add(SN);
                tModel.addRow(v);
            }
        } catch (Exception e) {

        }
    }

    private void seachDateByJHA() {
        try {
            DefaultTableModel tModel = (DefaultTableModel) tblSeachedData.getModel();
            tModel.setRowCount(0);
            String quary = "SELECT JHA,SA,SN FROM SMJK WHERE JHA LIKE '" + txtSeachField.getText() + "%'";
            ResultSet data = SQLConnection.SqlConnection.getData(quary);
            while (data.next()) {
                String seachBy = data.getString("JHA");
                String SA = data.getString("SA");
                String SN = data.getString("SN");
                Vector v = new Vector();
                v.add(seachBy);
                v.add(SA);
                v.add(SN);
                tModel.addRow(v);
            }
        } catch (Exception e) {

        }
    }

    private void seachDateBySN() {
        try {
            DefaultTableModel tModel = (DefaultTableModel) tblSeachedData.getModel();
            tModel.setRowCount(0);
            String quary = "SELECT SA,SN FROM SMJK WHERE SN LIKE '%" + txtSeachField.getText() + "%'";
            ResultSet data = SQLConnection.SqlConnection.getData(quary);
            while (data.next()) {
                String seachBy = data.getString("SN");
                String SA = data.getString("SA");
                String SN = data.getString("SN");
                Vector v = new Vector();
                v.add(seachBy);
                v.add(SA);
                v.add(SN);
                tModel.addRow(v);
            }
        } catch (Exception e) {

        }
    }

    private void seachDateByWL() {
        try {
            DefaultTableModel tModel = (DefaultTableModel) tblSeachedData.getModel();
            tModel.setRowCount(0);
            String quary = "SELECT WL,SA,SN FROM SMJK WHERE WL LIKE '" + txtSeachField.getText() + "%'";
            ResultSet data = SQLConnection.SqlConnection.getData(quary);
            while (data.next()) {
                String seachBy = data.getString("WL");
                String SA = data.getString("SA");
                String SN = data.getString("SN");
                Vector v = new Vector();
                v.add(seachBy);
                v.add(SA);
                v.add(SN);
                tModel.addRow(v);
            }
        } catch (Exception e) {

        }
    }

    private void seachDateByJDA() {
        try {
            DefaultTableModel tModel = (DefaultTableModel) tblSeachedData.getModel();
            tModel.setRowCount(0);
            String quary = "SELECT JDA,SA,SN FROM SMJK WHERE JDA LIKE '" + txtSeachField.getText() + "%'";
            ResultSet data = SQLConnection.SqlConnection.getData(quary);
            while (data.next()) {
                String seachBy = data.getString("JDA");
                String SA = data.getString("SA");
                String SN = data.getString("SN");
                Vector v = new Vector();
                v.add(seachBy);
                v.add(SA);
                v.add(SN);
                tModel.addRow(v);
            }
        } catch (Exception e) {

        }
    }

    private void seachDateBySDA() {
        try {
            DefaultTableModel tModel = (DefaultTableModel) tblSeachedData.getModel();
            tModel.setRowCount(0);
            String quary = "SELECT SDA,SA,SN FROM SMJK WHERE SDA LIKE '" + txtSeachField.getText() + "%'";
            ResultSet data = SQLConnection.SqlConnection.getData(quary);
            while (data.next()) {
                String seachBy = data.getString("SDA");
                String SA = data.getString("SA");
                String SN = data.getString("SN");
                Vector v = new Vector();
                v.add(seachBy);
                v.add(SA);
                v.add(SN);
                tModel.addRow(v);
            }
        } catch (Exception e) {

        }
    }

    private void seachDateByNA() {
        try {
            DefaultTableModel tModel = (DefaultTableModel) tblSeachedData.getModel();
            tModel.setRowCount(0);
            String quary = "SELECT NA,SMJK.SA,SN FROM SMJK INNER JOIN ST ON SMJK.SA=ST.SA WHERE NA LIKE '" + txtSeachField.getText() + "%'";
            ResultSet data = SQLConnection.SqlConnection.getData(quary);
            while (data.next()) {
                String seachBy = data.getString("NA");
                String SA = data.getString("SA");
                String SN = data.getString("SN");
                Vector v = new Vector();
                v.add(seachBy);
                v.add(SA);
                v.add(SN);
                tModel.addRow(v);
            }
        } catch (Exception e) {
           new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
        }
    }

    private void seachDateByUSA() {
        try {
            DefaultTableModel tModel = (DefaultTableModel) tblSeachedData.getModel();
            tModel.setRowCount(0);
            String quary = "SELECT UA,SMJK.SA,SN FROM SMJK INNER JOIN ST ON SMJK.SA=ST.SA WHERE UA LIKE '" + txtSeachField.getText() + "%'";
            ResultSet data = SQLConnection.SqlConnection.getData(quary);
            while (data.next()) {
                String seachBy = data.getString("UA");
                String SA = data.getString("SA");
                String SN = data.getString("SN");
                Vector v = new Vector();
                v.add(seachBy);
                v.add(SA);
                v.add(SN);
                tModel.addRow(v);
            }
        } catch (Exception e) {
           new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
        }
    }

    private void seachDateByRHA() {
        try {
            DefaultTableModel tModel = (DefaultTableModel) tblSeachedData.getModel();
            tModel.setRowCount(0);
            String quary = "SELECT RHA,SMJK.SA,SN FROM SMJK INNER JOIN ST ON SMJK.SA=ST.SA WHERE RHA LIKE '" + txtSeachField.getText() + "%'";
            ResultSet data = SQLConnection.SqlConnection.getData(quary);
            while (data.next()) {
                String seachBy = data.getString("RHA");
                String SA = data.getString("SA");
                String SN = data.getString("SN");
                Vector v = new Vector();
                v.add(seachBy);
                v.add(SA);
                v.add(SN);
                tModel.addRow(v);
            }
        } catch (Exception e) {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSeachedData = new javax.swing.JTable();
        btnDeleteAllData = new javax.swing.JButton();
        jScrollPane15 = new javax.swing.JScrollPane();
        txtIwathKirimataHetuwa = new javax.swing.JTextArea();
        jLabel48 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtSeachField = new javax.swing.JTextField();
        cmbSeachBy = new javax.swing.JComboBox();
        jPanel8 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        trasthaPraharaya = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel10 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
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
        jScrollPane5 = new javax.swing.JScrollPane();
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
        btnUpdateTrasthaP = new javax.swing.JButton();
        btnRefrashTrasthaP = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        samajikaya = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        dtpSamajikathwayaLDinaya = new datechooser.beans.DateChooserCombo();
        jLabel2 = new javax.swing.JLabel();
        txtSampurnaNama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtSthiraLipinaya = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        dtpPolisiyataBadunaDinaya = new datechooser.beans.DateChooserCombo();
        txtAwasanWarataSKSthanaya = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtAwasanWarataSKKottasaya = new javax.swing.JTextField();
        dtpWishramaLabuDinaya = new datechooser.beans.DateChooserCombo();
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
        jSeparator1 = new javax.swing.JSeparator();
        btnUpdateSamajikaya = new javax.swing.JButton();
        txtRefrashSamajikaya = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblSA = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbPolisiyataBahune = new javax.swing.JComboBox();
        txtJathikaHadunumAnkaya = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        lblWasaraW = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        lblMasaW = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        lblDinaW = new javax.swing.JLabel();
        daruwan = new javax.swing.JPanel();
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
        txtDaruwageNamaEdit = new javax.swing.JTextField();
        dtpDaruwageDOBEdit = new datechooser.beans.DateChooserCombo();
        cmbDaruwageDanataThathwayaEdit = new javax.swing.JComboBox();
        cmbDaruwaAbadithadaEdit = new javax.swing.JComboBox();
        btnUpdateTblDaruwan = new javax.swing.JButton();
        btnUpdateDaruwan = new javax.swing.JButton();
        btnRefrashDaruwan = new javax.swing.JButton();
        cbxDaruwanSiti = new javax.swing.JCheckBox();
        masukaWatup = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        cmbMasukaWLabanneda = new javax.swing.JComboBox();
        titleMWLabenam = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        txtWlabanaKottasaya = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        txtWlabanaPolisWasama = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        txtMulikaWatupeWatinakama = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        txtDimanaWalaWatinakama = new javax.swing.JTextField();
        lblMWWInfor = new javax.swing.JLabel();
        lblMuluWatupeWatinakama = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        btnUpdateMasikaWatup = new javax.swing.JButton();
        btnRefrashMasikaWatup = new javax.swing.JButton();
        wisramaWatup = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        titleWishramaWLanannenam = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        txtWWAnkaya = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        txtWWWatinakama = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        txtDWWWatinakama = new javax.swing.JTextField();
        txtWWLPLKottashaya = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        lblDWWWorn = new javax.swing.JLabel();
        lblWWWorn = new javax.swing.JLabel();
        cmbDWWLabanneda = new javax.swing.JComboBox();
        titleWWNolabenam = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        cmbWWNHethuwa = new javax.swing.JComboBox();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblWWNHethu = new javax.swing.JTable();
        btnDAllWWNH = new javax.swing.JButton();
        btnDeleteWWNH = new javax.swing.JButton();
        btnAddWWNH = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        titleDWWNHethu = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        cmbDWWNHethuwa = new javax.swing.JComboBox();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblDWWNHethu = new javax.swing.JTable();
        btnDAllDWWNH = new javax.swing.JButton();
        btnDeleteDWWNH = new javax.swing.JButton();
        btnAddDWWNH = new javax.swing.JButton();
        cmbWisramaWLabanneda = new javax.swing.JComboBox();
        btnRefrashWisramaWatup = new javax.swing.JButton();
        btnUpdateWisramaWatup = new javax.swing.JButton();
        sahana = new javax.swing.JPanel();
        titleSahanaLabinama = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblSahana = new javax.swing.JTable();
        btnAddSahana = new javax.swing.JButton();
        btnDeleteSahana = new javax.swing.JButton();
        txtSahanaya = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        btnDAllSahana = new javax.swing.JButton();
        txtSahanaLabanaSthanaya = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        dtpSahanaAWLD = new datechooser.beans.DateChooserCombo();
        cbxSahanaLabiAtha = new javax.swing.JCheckBox();
        jSeparator5 = new javax.swing.JSeparator();
        btnRefrashSahana = new javax.swing.JButton();
        btnUpdateSahana = new javax.swing.JButton();
        abadithaUpakarana = new javax.swing.JPanel();
        cbxAbadithaUBKarai = new javax.swing.JCheckBox();
        titleAUBKarainam = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblAU = new javax.swing.JTable();
        dtpAULDinayanMitaPasu = new datechooser.beans.DateChooserCombo();
        cmbAbadithaUpakarana = new javax.swing.JComboBox();
        btnAddAU = new javax.swing.JButton();
        btnDeleteAU = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblAULDinayanMitaPasu = new javax.swing.JTable();
        btnAddAULDMitaPasu = new javax.swing.JButton();
        btnDeleteAULDMitaPasu = new javax.swing.JButton();
        txtWenathAbadithaUpakarana = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblAULDinayanMitaPera = new javax.swing.JTable();
        btnDeleteAULDMitaPera = new javax.swing.JButton();
        btnAddAULDMitaPera = new javax.swing.JButton();
        dtpAULDinayanMitaPera = new datechooser.beans.DateChooserCombo();
        btnUpdateAU = new javax.swing.JButton();
        btnRefrashAU = new javax.swing.JButton();
        wiwahaya = new javax.swing.JPanel();
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
        cbxWiwahakada = new javax.swing.JCheckBox();
        jSeparator3 = new javax.swing.JSeparator();
        btnUpdateWiwahaya = new javax.swing.JButton();
        btnRefrashWiwahaya = new javax.swing.JButton();
        thanathura = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        cmbPBadenawitaThanathura = new javax.swing.JComboBox();
        cmbWishramaYWThanathura = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNilaAnkaya = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtUpasewaAnkaya = new javax.swing.JTextField();
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
        jSeparator4 = new javax.swing.JSeparator();
        btnUpdateDaruwan1 = new javax.swing.JButton();
        btnRefrashDaruwan1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("දත්ත පද්ධතිය ආශ්‍රිත මෙහෙයුම්.");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jTabbedPane2.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 204), 2, true), "m%;sm,", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FMBindumathi", 0, 14))); // NOI18N

        tblSeachedData.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        tblSeachedData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
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
            tblSeachedData.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblSeachedData.getColumnModel().getColumn(2).setResizable(false);
            tblSeachedData.getColumnModel().getColumn(2).setPreferredWidth(230);
        }

        btnDeleteAllData.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        btnDeleteAllData.setText("ඉවත් කරන්න ");
        btnDeleteAllData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteAllDataActionPerformed(evt);
            }
        });

        jScrollPane15.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane15.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtIwathKirimataHetuwa.setColumns(20);
        txtIwathKirimataHetuwa.setFont(new java.awt.Font("FMMalithi", 0, 13)); // NOI18N
        txtIwathKirimataHetuwa.setRows(5);
        jScrollPane15.setViewportView(txtIwathKirimataHetuwa);

        jLabel48.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel48.setText("bj;a lsÍug fya;=j ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane15)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnDeleteAllData, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDeleteAllData)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 0), 2, true), "සොයන්න", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Iskoola Pota", 0, 14))); // NOI18N

        txtSeachField.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        txtSeachField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSeachFieldActionPerformed(evt);
            }
        });
        txtSeachField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSeachFieldKeyReleased(evt);
            }
        });

        cmbSeachBy.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        cmbSeachBy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "idudðl wxlh", "cd;sl yeÿkqïm;a wxlh", "iïmQ¾K ku", "úoahq;a ,smskh", "cx.u ÿrl:k wxlh", "ia:djr ÿrl:k wxlh", "ks, wxlh", "Wmfijd wxlh", "rKúre yeÿkqïm;a wxlh" }));
        cmbSeachBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSeachByActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSeachField, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbSeachBy, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSeachField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSeachBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("fidhkak ", jPanel5);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 2, true), "තෝරාගත් දත්ත ඉවත්කරන්න ,වෙනස් කරන්න ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Iskoola Pota", 0, 14))); // NOI18N
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setDoubleBuffered(true);
        jTabbedPane1.setFocusCycleRoot(true);
        jTabbedPane1.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jTabbedPane1.setInheritsPopupMenu(true);

        cmbThrasthaKandayama.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        cmbThrasthaKandayama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "t,aááB ", "fÊúmS", "m%pKav l%shd" }));

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
        cmbOthpalada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tõ", "ke;" }));

        jLabel27.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel27.setText("fuu YdÍßl ydks fy;=fjka fjk;a w;=re wdndO weoao ");

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
        jScrollPane5.setViewportView(tblAthuruAbada);

        javax.swing.GroupLayout titleAAbadaLayout = new javax.swing.GroupLayout(titleAAbada);
        titleAAbada.setLayout(titleAAbadaLayout);
        titleAAbadaLayout.setHorizontalGroup(
            titleAAbadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleAAbadaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(titleAAbadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAthuruAbadaya)
                    .addComponent(jScrollPane5)
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
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
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
cmbAthuruAAdda1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tõ", "ke;" }));
cmbAthuruAAdda1.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmbAthuruAAdda1ActionPerformed(evt);
    }
    });

    jLabel73.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel73.setText("ffjoH mßlaIK uKav,h meje;ajQ fldÜGdYh ");

    txtWidyaPPawathwuKo.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
    jPanel12.setLayout(jPanel12Layout);
    jPanel12Layout.setHorizontalGroup(
        jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel12Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbIpaimaSHPrathishathaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(287, 287, 287))
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtWidyaPPawathwuKo, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(170, 170, 170))
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(dtpWidyaPPawathwuDinaya, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                .addComponent(txtWidyaPPawathwuSthanaya)))
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(titleAAbada, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtWenathHani))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbSharirikaHani, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel12Layout.createSequentialGroup()
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(cmbAthuruAAdda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cmbOthpalada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtPraharayaSiduwuSthanaya)
                                .addComponent(dtpPraharayaSiduwuDinaya, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                                .addComponent(txtPraharayaSiduwuKottasaya)
                                .addComponent(cmbThrasthaKandayama, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
    );
    jPanel12Layout.setVerticalGroup(
        jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel12Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel21)
                .addComponent(cmbThrasthaKandayama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(dtpPraharayaSiduwuDinaya, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel23)
                .addComponent(txtPraharayaSiduwuKottasaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel24)
                .addComponent(txtPraharayaSiduwuSthanaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel25)
                .addComponent(cmbSharirikaHani, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel26)
                .addComponent(txtWenathHani, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(10, 10, 10)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel35)
                .addComponent(cmbOthpalada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel27)
                .addComponent(cmbAthuruAAdda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(titleAAbada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(dtpWidyaPPawathwuDinaya, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(txtWidyaPPawathwuSthanaya, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtWidyaPPawathwuKo))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel38)
                .addComponent(cmbIpaimaSHPrathishathaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(14, Short.MAX_VALUE))
    );

    btnUpdateTrasthaP.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnUpdateTrasthaP.setText("දත්ත යාවත්කාලීන කරන්න ");
    btnUpdateTrasthaP.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnUpdateTrasthaPActionPerformed(evt);
        }
    });

    btnRefrashTrasthaP.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnRefrashTrasthaP.setText("දත්ත නැවත පුරවන්න ");
    btnRefrashTrasthaP.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnRefrashTrasthaPActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
    jPanel10.setLayout(jPanel10Layout);
    jPanel10Layout.setHorizontalGroup(
        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addComponent(btnRefrashTrasthaP)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnUpdateTrasthaP))
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(31, 31, 31))
        .addGroup(jPanel10Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel10Layout.setVerticalGroup(
        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel10Layout.createSequentialGroup()
            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnUpdateTrasthaP)
                .addComponent(btnRefrashTrasthaP))
            .addContainerGap())
    );

    jScrollPane4.setViewportView(jPanel10);

    javax.swing.GroupLayout trasthaPraharayaLayout = new javax.swing.GroupLayout(trasthaPraharaya);
    trasthaPraharaya.setLayout(trasthaPraharayaLayout);
    trasthaPraharayaLayout.setHorizontalGroup(
        trasthaPraharayaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
    );
    trasthaPraharayaLayout.setVerticalGroup(
        trasthaPraharayaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
    );

    jTabbedPane1.addTab(";%ia; m%ydrh ", trasthaPraharaya);

    jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

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
try {
    dtpSamajikathwayaLDinaya.setDefaultPeriods(new datechooser.model.multiple.PeriodSet(new datechooser.model.multiple.Period(new java.util.GregorianCalendar(2016, 0, 13),
        new java.util.GregorianCalendar(2016, 0, 13))));
} catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
e1.printStackTrace();
}

jLabel2.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
jLabel2.setText("idudðl;ajh ,enQ oskh ");

txtSampurnaNama.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

jLabel4.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
jLabel4.setText("iïmQ¾K ku ");

txtSthiraLipinaya.setColumns(20);
txtSthiraLipinaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
txtSthiraLipinaya.setLineWrap(true);
txtSthiraLipinaya.setRows(5);
txtSthiraLipinaya.setToolTipText("");
txtSthiraLipinaya.setWrapStyleWord(true);
jScrollPane3.setViewportView(txtSthiraLipinaya);

jLabel5.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
jLabel5.setText("iaÒr ,smskh ");

jLabel7.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
jLabel7.setText("fmd,sishg neÿk oskh ");

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

txtAwasanWarataSKSthanaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
txtAwasanWarataSKSthanaya.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
    txtAwasanWarataSKSthanayaActionPerformed(evt);
    }
    });

    jLabel19.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel19.setText("wjika jrg fijh l, ia:dkh ");

    jLabel20.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel20.setText("wjika jrg fiajh l, fldÜGdYh ");

    txtAwasanWarataSKKottasaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

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

    jLabel28.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel28.setText("úY%du ,enQ oskh ");

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

    jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "iaÒr ,smskhg wod, ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FMMalithi", 0, 14))); // NOI18N

    jLabel54.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel54.setText("fmd,sia jiu ");

    txtPolisWasamaSL.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    txtGramaNWasama.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    jLabel55.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel55.setText(".%du ks,Odß jiu ");

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
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel56)
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtGramaNWasama, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                        .addComponent(txtDistrikkaya)))
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(4, 4, 4)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtPolisWasamaSL)
                        .addComponent(txtPradeshiyaLekamKottasaya))))
            .addContainerGap())
    );
    jPanel11Layout.setVerticalGroup(
        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel11Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel54)
                .addComponent(txtPolisWasamaSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel57)
                .addComponent(txtPradeshiyaLekamKottasaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel55)
                .addComponent(txtGramaNWasama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
    jLabel60.setText("úoahq;a ,smskh ");

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

btnUpdateSamajikaya.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
btnUpdateSamajikaya.setText("යාවත්කාලීන කරන්න ");
btnUpdateSamajikaya.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnUpdateSamajikayaActionPerformed(evt);
    }
    });

    txtRefrashSamajikaya.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    txtRefrashSamajikaya.setText("නැවත දත්ත පුරවන්න ");
    txtRefrashSamajikaya.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtRefrashSamajikayaActionPerformed(evt);
        }
    });

    jLabel3.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel3.setText("idudðl wxlh ");

    lblSA.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    lblSA.setText("සාමාජික අංකය ");

    jLabel8.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel8.setText("fmd,sishg neÿfka ");

    cmbPolisiyataBahune.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cmbPolisiyataBahune.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ks;H fiajd ", "Wm fiajd" }));

    txtJathikaHadunumAnkaya.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N

    jLabel13.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel13.setText("cd ye wxlh ");

    jLabel31.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel31.setText("wo oskg jhi ");

    jLabel33.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel33.setText("jir");

    lblWasaraW.setFont(new java.awt.Font("Iskoola Pota", 3, 14)); // NOI18N
    lblWasaraW.setForeground(new java.awt.Color(204, 0, 204));
    lblWasaraW.setText("????");

    jLabel39.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel39.setText("udi");

    lblMasaW.setFont(new java.awt.Font("Iskoola Pota", 3, 14)); // NOI18N
    lblMasaW.setForeground(new java.awt.Color(204, 0, 204));
    lblMasaW.setText("????");

    jLabel40.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel40.setText("osk ");

    lblDinaW.setFont(new java.awt.Font("Iskoola Pota", 3, 14)); // NOI18N
    lblDinaW.setForeground(new java.awt.Color(204, 0, 204));
    lblDinaW.setText("????");

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGap(21, 21, 21)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSampurnaNama, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtpSamajikathwayaLDinaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dtpDOB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtEmail))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSthawaraDurakathanaya, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                            .addComponent(txtJangamaDurakathanaya)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSA, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbPolisiyataBahune, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtpPolisiyataBadunaDinaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                        .addComponent(lblDinaW, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtJathikaHadunumAnkaya))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtAwasanWarataSKSthanaya, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                            .addComponent(txtAwasanWarataSKKottasaya, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                            .addComponent(dtpWishramaLabuDinaya, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                    .addComponent(jLabel29)
                    .addGap(38, 38, 38)
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
                    .addComponent(lblDina, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(70, Short.MAX_VALUE))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtRefrashSamajikaya)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(btnUpdateSamajikaya)
            .addGap(38, 38, 38))
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addComponent(jSeparator1)
            .addContainerGap())
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGap(18, 18, 18)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3)
                .addComponent(lblSA))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(dtpSamajikathwayaLDinaya, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel4)
                .addComponent(txtSampurnaNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel5)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(dtpPolisiyataBadunaDinaya, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel8)
                .addComponent(cmbPolisiyataBahune, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(txtJathikaHadunumAnkaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(3, 3, 3)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel19)
                .addComponent(txtAwasanWarataSKSthanaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel20)
                .addComponent(txtAwasanWarataSKKottasaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(dtpWishramaLabuDinaya, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(txtSthawaraDurakathanaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel59)
                .addComponent(txtJangamaDurakathanaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel60))
            .addGap(12, 12, 12)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(dtpDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel31)
                .addComponent(jLabel33)
                .addComponent(lblWasaraW)
                .addComponent(jLabel39)
                .addComponent(lblMasaW)
                .addComponent(jLabel40)
                .addComponent(lblDinaW))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnUpdateSamajikaya)
                .addComponent(txtRefrashSamajikaya))
            .addContainerGap())
    );

    jScrollPane2.setViewportView(jPanel2);

    javax.swing.GroupLayout samajikayaLayout = new javax.swing.GroupLayout(samajikaya);
    samajikaya.setLayout(samajikayaLayout);
    samajikayaLayout.setHorizontalGroup(
        samajikayaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
    );
    samajikayaLayout.setVerticalGroup(
        samajikayaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
    );

    jTabbedPane1.addTab("idudðlhdf.a idudkH úia;r ", samajikaya);

    titleDaruwanSitinam.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

    txtDaruwageNama.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    cmbDaruwageDanataThathwaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cmbDaruwageDanataThathwaya.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mdi,a hk ", "Wiia wOHdmkhg fhduq jQ ", "/lshd lrk ", "/lshd úrys;" }));

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
cmbDaruwaAbadithada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tõ", "ke;" }));

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

    txtDaruwageNamaEdit.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    dtpDaruwageDOBEdit.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
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
dtpDaruwageDOBEdit.setNothingAllowed(false);
dtpDaruwageDOBEdit.setCurrentNavigateIndex(0);

cmbDaruwageDanataThathwayaEdit.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
cmbDaruwageDanataThathwayaEdit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mdi,a hk ", "Wiia wOHdmkhg fhduq jQ ", "/lshd lrk ", "/lshd úrys;" }));

cmbDaruwaAbadithadaEdit.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
cmbDaruwaAbadithadaEdit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tõ", "ke;" }));

btnUpdateTblDaruwan.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
btnUpdateTblDaruwan.setText("හරි");
btnUpdateTblDaruwan.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnUpdateTblDaruwanActionPerformed(evt);
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
                    .addGroup(titleDaruwanSitinamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleDaruwanSitinamLayout.createSequentialGroup()
                            .addComponent(cmbDaruwageDanataThathwayaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbDaruwaAbadithadaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleDaruwanSitinamLayout.createSequentialGroup()
                            .addComponent(btnDAllDaruwa)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnDeleteDaruwa)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnAddDaruwa))
                        .addComponent(btnUpdateTblDaruwan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleDaruwanSitinamLayout.createSequentialGroup()
                            .addComponent(dtpDaruwageDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbDaruwageDanataThathwaya, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbDaruwaAbadithada, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(titleDaruwanSitinamLayout.createSequentialGroup()
                    .addGroup(titleDaruwanSitinamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtDaruwageNama, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(titleDaruwanSitinamLayout.createSequentialGroup()
                            .addComponent(txtDaruwageNamaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(dtpDaruwageDOBEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );
    titleDaruwanSitinamLayout.setVerticalGroup(
        titleDaruwanSitinamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(titleDaruwanSitinamLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(titleDaruwanSitinamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(titleDaruwanSitinamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbDaruwageDanataThathwaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbDaruwaAbadithada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(txtDaruwageNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(dtpDaruwageDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(titleDaruwanSitinamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnAddDaruwa)
                .addComponent(btnDeleteDaruwa)
                .addComponent(btnDAllDaruwa))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(titleDaruwanSitinamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(titleDaruwanSitinamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(titleDaruwanSitinamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbDaruwageDanataThathwayaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbDaruwaAbadithadaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dtpDaruwageDOBEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(txtDaruwageNamaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(btnUpdateTblDaruwan)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    btnUpdateDaruwan.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnUpdateDaruwan.setText("දත්ත යාවත්කාලීන කරන්න ");
    btnUpdateDaruwan.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnUpdateDaruwanActionPerformed(evt);
        }
    });

    btnRefrashDaruwan.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnRefrashDaruwan.setText("දත්ත නැවත පුරවන්න ");
    btnRefrashDaruwan.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnRefrashDaruwanActionPerformed(evt);
        }
    });

    cbxDaruwanSiti.setFont(new java.awt.Font("Iskoola Pota", 1, 14)); // NOI18N
    cbxDaruwanSiti.setSelected(true);
    cbxDaruwanSiti.setText("දරුවන් සිටී.");
    cbxDaruwanSiti.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cbxDaruwanSitiActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout daruwanLayout = new javax.swing.GroupLayout(daruwan);
    daruwan.setLayout(daruwanLayout);
    daruwanLayout.setHorizontalGroup(
        daruwanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(daruwanLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(daruwanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(daruwanLayout.createSequentialGroup()
                    .addComponent(cbxDaruwanSiti)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(daruwanLayout.createSequentialGroup()
                    .addGroup(daruwanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(titleDaruwanSitinam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, daruwanLayout.createSequentialGroup()
                            .addGap(0, 331, Short.MAX_VALUE)
                            .addComponent(btnRefrashDaruwan)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnUpdateDaruwan)))
                    .addContainerGap())))
    );
    daruwanLayout.setVerticalGroup(
        daruwanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(daruwanLayout.createSequentialGroup()
            .addComponent(cbxDaruwanSiti)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(titleDaruwanSitinam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(daruwanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnUpdateDaruwan)
                .addComponent(btnRefrashDaruwan))
            .addContainerGap(121, Short.MAX_VALUE))
    );

    jTabbedPane1.addTab("orejka ", daruwan);

    jLabel42.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel42.setText("udisl jegqma yd osukd ,nkafkao ");

    cmbMasukaWLabanneda.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cmbMasukaWLabanneda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tõ", "ke;" }));
    cmbMasukaWLabanneda.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cmbMasukaWLabannedaActionPerformed(evt);
        }
    });

    titleMWLabenam.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "udisl jegqma iy oSukd ,nkafkakï", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FMMalithi", 0, 14))); // NOI18N

    jLabel43.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel43.setText("jegqma ,nk fldÜGdYh ");

    txtWlabanaKottasaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    jLabel44.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel44.setText("fmd,sia ia:dkh  ");

    txtWlabanaPolisWasama.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    jLabel45.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel45.setText("uQ,sl jegqm");

    txtMulikaWatupeWatinakama.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N

    jLabel47.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel47.setText("uq¨ oSukd ");

    txtDimanaWalaWatinakama.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N

    lblMWWInfor.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    lblMWWInfor.setForeground(new java.awt.Color(204, 0, 0));

    lblMuluWatupeWatinakama.setFont(new java.awt.Font("Iskoola Pota", 3, 14)); // NOI18N
    lblMuluWatupeWatinakama.setForeground(new java.awt.Color(204, 0, 204));
    lblMuluWatupeWatinakama.setText("????");

    jLabel72.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel72.setText("uq¨ jgqm");

    javax.swing.GroupLayout titleMWLabenamLayout = new javax.swing.GroupLayout(titleMWLabenam);
    titleMWLabenam.setLayout(titleMWLabenamLayout);
    titleMWLabenamLayout.setHorizontalGroup(
        titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(titleMWLabenamLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(titleMWLabenamLayout.createSequentialGroup()
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(16, 16, 16)
                    .addComponent(lblMuluWatupeWatinakama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(65, 65, 65)
                    .addComponent(lblMWWInfor, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
                .addGroup(titleMWLabenamLayout.createSequentialGroup()
                    .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(titleMWLabenamLayout.createSequentialGroup()
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtWlabanaKottasaya))
                        .addGroup(titleMWLabenamLayout.createSequentialGroup()
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtMulikaWatupeWatinakama, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18)
                    .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                        .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtWlabanaPolisWasama, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDimanaWalaWatinakama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 10, Short.MAX_VALUE))))
    );
    titleMWLabenamLayout.setVerticalGroup(
        titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(titleMWLabenamLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(titleMWLabenamLayout.createSequentialGroup()
                    .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel43)
                        .addComponent(txtWlabanaKottasaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel45)
                        .addComponent(txtMulikaWatupeWatinakama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(titleMWLabenamLayout.createSequentialGroup()
                    .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtWlabanaPolisWasama, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel47)
                        .addComponent(txtDimanaWalaWatinakama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblMWWInfor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(titleMWLabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMuluWatupeWatinakama)
                    .addComponent(jLabel72)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    btnUpdateMasikaWatup.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnUpdateMasikaWatup.setText("දත්ත යාවත්කාලීන කරන්න ");
    btnUpdateMasikaWatup.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnUpdateMasikaWatupActionPerformed(evt);
        }
    });

    btnRefrashMasikaWatup.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnRefrashMasikaWatup.setText("දත්ත නැවත පුරවන්න ");
    btnRefrashMasikaWatup.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnRefrashMasikaWatupActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout masukaWatupLayout = new javax.swing.GroupLayout(masukaWatup);
    masukaWatup.setLayout(masukaWatupLayout);
    masukaWatupLayout.setHorizontalGroup(
        masukaWatupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(masukaWatupLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(masukaWatupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(masukaWatupLayout.createSequentialGroup()
                    .addComponent(btnRefrashMasikaWatup)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnUpdateMasikaWatup))
                .addGroup(masukaWatupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(masukaWatupLayout.createSequentialGroup()
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbMasukaWLabanneda, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(titleMWLabenam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator6)))
            .addContainerGap(23, Short.MAX_VALUE))
    );
    masukaWatupLayout.setVerticalGroup(
        masukaWatupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(masukaWatupLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(masukaWatupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel42)
                .addComponent(cmbMasukaWLabanneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(titleMWLabenam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(masukaWatupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnUpdateMasikaWatup)
                .addComponent(btnRefrashMasikaWatup))
            .addContainerGap(335, Short.MAX_VALUE))
    );

    jTabbedPane1.addTab("udisl jegqma ", masukaWatup);

    jScrollPane13.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    titleWishramaWLanannenam.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "úY%du jegqma ,nkafkakï ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FMMalithi", 0, 14))); // NOI18N

    jLabel51.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel51.setText("úY%du jegqma wxlh ");

    txtWWAnkaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    jLabel52.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel52.setText("úY%du jegqm ");

    txtWWWatinakama.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    jLabel53.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel53.setText("ÿn,;d úY%du jegqm");

    txtDWWWatinakama.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    txtWWLPLKottashaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    jLabel62.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel62.setText("úY%du jegqma ,nd.kakd m%dfoaYSh f,aLï fldÜGdih ");

    lblDWWWorn.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    lblDWWWorn.setForeground(new java.awt.Color(255, 0, 0));
    lblDWWWorn.setText("වටිනාකම ඇතුළු කර නොමැත ");

    lblWWWorn.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    lblWWWorn.setForeground(new java.awt.Color(255, 0, 0));
    lblWWWorn.setText("වටිනාකම ඇතුළු කර නොමැත ");

    javax.swing.GroupLayout titleWishramaWLanannenamLayout = new javax.swing.GroupLayout(titleWishramaWLanannenam);
    titleWishramaWLanannenam.setLayout(titleWishramaWLanannenamLayout);
    titleWishramaWLanannenamLayout.setHorizontalGroup(
        titleWishramaWLanannenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(titleWishramaWLanannenamLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(titleWishramaWLanannenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleWishramaWLanannenamLayout.createSequentialGroup()
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtWWWatinakama, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtDWWWatinakama, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(titleWishramaWLanannenamLayout.createSequentialGroup()
                    .addGroup(titleWishramaWLanannenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, titleWishramaWLanannenamLayout.createSequentialGroup()
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtWWAnkaya))
                        .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtWWLPLKottashaya, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(30, 30, 30))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleWishramaWLanannenamLayout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(lblWWWorn)
            .addGap(152, 152, 152)
            .addComponent(lblDWWWorn)
            .addContainerGap())
    );
    titleWishramaWLanannenamLayout.setVerticalGroup(
        titleWishramaWLanannenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(titleWishramaWLanannenamLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(titleWishramaWLanannenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel51)
                .addComponent(txtWWAnkaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(titleWishramaWLanannenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel62)
                .addComponent(txtWWLPLKottashaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(titleWishramaWLanannenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel52)
                .addComponent(txtWWWatinakama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel53)
                .addComponent(txtDWWWatinakama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(7, 7, 7)
            .addGroup(titleWishramaWLanannenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblDWWWorn)
                .addComponent(lblWWWorn))
            .addContainerGap())
    );

    cmbDWWLabanneda.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cmbDWWLabanneda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tõ", "ke;" }));
    cmbDWWLabanneda.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cmbDWWLabannedaActionPerformed(evt);
        }
    });

    titleWWNolabenam.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "úY%du jegqma fkd,efíkï ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FMMalithi", 0, 14))); // NOI18N
    titleWWNolabenam.setEnabled(false);

    jLabel69.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel69.setText("fya;=j  ");
    jLabel69.setEnabled(false);

    cmbWWNHethuwa.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cmbWWNHethuwa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "fiajd ld,h jir 10 wvqùu ", "fmdÿ ksfhda. fkd,eîu ", "ffjoH mÍlaIK uKav, jd¾;d wia;dk.; ùu", "úkh fya;= u; ", "isoaoshg wod, f,aLk wia:dk.; ùu" }));
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
    jScrollPane11.setViewportView(tblWWNHethu);

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
                .addComponent(jScrollPane11)
                .addGroup(titleWWNolabenamLayout.createSequentialGroup()
                    .addComponent(jLabel69)
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
                .addComponent(jLabel69)
                .addComponent(cmbWWNHethuwa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(titleWWNolabenamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnAddWWNH)
                .addComponent(btnDeleteWWNH)
                .addComponent(btnDAllWWNH))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jLabel50.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel50.setText("ÿn,;d úY%du jegqfma jákdlu ");

    jLabel49.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel49.setText("úY%du jegqma ,nkafkao ");

    titleDWWNHethu.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ÿn,;d úY%du jegqma fkd,efíkï ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FMMalithi", 0, 14))); // NOI18N
    titleDWWNHethu.setEnabled(false);

    jLabel70.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel70.setText("fya;=j ");
    jLabel70.setEnabled(false);

    cmbDWWNHethuwa.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cmbDWWNHethuwa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "fiajd ld,h jir 10 wvqùu ", "fmdÿ ksfhda. fkd,eîu ", "ffjoH mÍlaIK uKav, jd¾;d wia;dk.; ùu", "bmhSfï Yla;sh 20] wvqùu" }));
    cmbDWWNHethuwa.setEnabled(false);

    tblDWWNHethu.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
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
                .addComponent(jScrollPane12)
                .addGroup(titleDWWNHethuLayout.createSequentialGroup()
                    .addComponent(jLabel70)
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
                .addComponent(jLabel70)
                .addComponent(cmbDWWNHethuwa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(titleDWWNHethuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnAddDWWNH)
                .addComponent(btnDeleteDWWNH)
                .addComponent(btnDAllDWWNH))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    cmbWisramaWLabanneda.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cmbWisramaWLabanneda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tõ", "ke;" }));
    cmbWisramaWLabanneda.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cmbWisramaWLabannedaActionPerformed(evt);
        }
    });

    btnRefrashWisramaWatup.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnRefrashWisramaWatup.setText("දත්ත නැවත පුරවන්න ");
    btnRefrashWisramaWatup.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnRefrashWisramaWatupActionPerformed(evt);
        }
    });

    btnUpdateWisramaWatup.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnUpdateWisramaWatup.setText("දත්ත යාවත්කාලීන කරන්න ");
    btnUpdateWisramaWatup.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnUpdateWisramaWatupActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(titleDWWNHethu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titleWWNolabenam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbWisramaWLabanneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbDWWLabanneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(titleWishramaWLanannenam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addComponent(btnRefrashWisramaWatup)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnUpdateWisramaWatup)))
            .addContainerGap())
    );
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel49)
                .addComponent(cmbWisramaWLabanneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel50)
                .addComponent(cmbDWWLabanneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(titleWishramaWLanannenam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(titleWWNolabenam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(titleDWWNHethu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnUpdateWisramaWatup)
                .addComponent(btnRefrashWisramaWatup))
            .addContainerGap())
    );

    jScrollPane13.setViewportView(jPanel4);

    javax.swing.GroupLayout wisramaWatupLayout = new javax.swing.GroupLayout(wisramaWatup);
    wisramaWatup.setLayout(wisramaWatupLayout);
    wisramaWatupLayout.setHorizontalGroup(
        wisramaWatupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
    );
    wisramaWatupLayout.setVerticalGroup(
        wisramaWatupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
    );

    jTabbedPane1.addTab("úY%du jegqma ", wisramaWatup);

    titleSahanaLabinama.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ",nd we;s iyk ms<sno úia;r ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FMMalithi", 0, 14))); // NOI18N

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
    if (tblSahana.getColumnModel().getColumnCount() > 0) {
        tblSahana.getColumnModel().getColumn(0).setPreferredWidth(230);
        tblSahana.getColumnModel().getColumn(1).setPreferredWidth(233);
        tblSahana.getColumnModel().getColumn(2).setPreferredWidth(170);
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

    jLabel41.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel41.setText("iykh  ");

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

    jLabel74.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel74.setText("wjika jrg ,nd.;a oskh ");

    dtpSahanaAWLD.setFieldFont(new java.awt.Font("Iskoola Pota", java.awt.Font.PLAIN, 14));

    javax.swing.GroupLayout titleSahanaLabinamaLayout = new javax.swing.GroupLayout(titleSahanaLabinama);
    titleSahanaLabinama.setLayout(titleSahanaLabinamaLayout);
    titleSahanaLabinamaLayout.setHorizontalGroup(
        titleSahanaLabinamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(titleSahanaLabinamaLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(titleSahanaLabinamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane10)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleSahanaLabinamaLayout.createSequentialGroup()
                    .addComponent(jLabel41)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtSahanaya))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleSahanaLabinamaLayout.createSequentialGroup()
                    .addGap(0, 252, Short.MAX_VALUE)
                    .addComponent(btnDAllSahana)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnDeleteSahana)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnAddSahana))
                .addGroup(titleSahanaLabinamaLayout.createSequentialGroup()
                    .addComponent(jLabel46)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtSahanaLabanaSthanaya))
                .addGroup(titleSahanaLabinamaLayout.createSequentialGroup()
                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(dtpSahanaAWLD, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );
    titleSahanaLabinamaLayout.setVerticalGroup(
        titleSahanaLabinamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(titleSahanaLabinamaLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(titleSahanaLabinamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel41)
                .addComponent(txtSahanaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(titleSahanaLabinamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel46)
                .addComponent(txtSahanaLabanaSthanaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(titleSahanaLabinamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(titleSahanaLabinamaLayout.createSequentialGroup()
                    .addComponent(jLabel74)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addComponent(dtpSahanaAWLD, javax.swing.GroupLayout.PREFERRED_SIZE, 21, Short.MAX_VALUE))
            .addGap(18, 18, 18)
            .addGroup(titleSahanaLabinamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnAddSahana)
                .addComponent(btnDeleteSahana)
                .addComponent(btnDAllSahana))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    cbxSahanaLabiAtha.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cbxSahanaLabiAtha.setSelected(true);
    cbxSahanaLabiAtha.setText("iyk ,eî we; ");
    cbxSahanaLabiAtha.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cbxSahanaLabiAthaActionPerformed(evt);
        }
    });

    btnRefrashSahana.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnRefrashSahana.setText("දත්ත නැවත පුරවන්න ");
    btnRefrashSahana.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnRefrashSahanaActionPerformed(evt);
        }
    });

    btnUpdateSahana.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnUpdateSahana.setText("දත්ත යාවත්කාලීන කරන්න ");
    btnUpdateSahana.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnUpdateSahanaActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout sahanaLayout = new javax.swing.GroupLayout(sahana);
    sahana.setLayout(sahanaLayout);
    sahanaLayout.setHorizontalGroup(
        sahanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(sahanaLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(sahanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(titleSahanaLabinama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(sahanaLayout.createSequentialGroup()
                    .addComponent(cbxSahanaLabiAtha)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addComponent(jSeparator5)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sahanaLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnRefrashSahana)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnUpdateSahana)))
            .addContainerGap())
    );
    sahanaLayout.setVerticalGroup(
        sahanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(sahanaLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(cbxSahanaLabiAtha)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(titleSahanaLabinama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(sahanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnUpdateSahana)
                .addComponent(btnRefrashSahana))
            .addContainerGap(105, Short.MAX_VALUE))
    );

    jTabbedPane1.addTab("iyk ", sahana);

    cbxAbadithaUBKarai.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cbxAbadithaUBKarai.setSelected(true);
    cbxAbadithaUBKarai.setText("wdndê; WmlrK Ndú;d lrhs");
    cbxAbadithaUBKarai.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cbxAbadithaUBKaraiActionPerformed(evt);
        }
    });

    titleAUBKarainam.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ndú;d lrk wdndê; Wmlrk iïnkao f;dr;=re ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FMMalithi", 0, 14))); // NOI18N

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

cmbAbadithaUpakarana.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
cmbAbadithaUpakarana.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "w;ajdre ", "frdomqgq", ".s,ka weoka ", "jdhq fyda j;=r fuÜg ", "weia ldkakdä ", "Y%jkdOdr ", "lD;Su mdo ", "fjk;a" }));
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
    tblAULDinayanMitaPasu.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

    btnAddAULDMitaPasu.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnAddAULDMitaPasu.setText("එකතු කරන්න ");
    btnAddAULDMitaPasu.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAddAULDMitaPasuActionPerformed(evt);
        }
    });

    btnDeleteAULDMitaPasu.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnDeleteAULDMitaPasu.setText("ඉවත් කරන්න ");
    btnDeleteAULDMitaPasu.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDeleteAULDMitaPasuActionPerformed(evt);
        }
    });

    txtWenathAbadithaUpakarana.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    txtWenathAbadithaUpakarana.setEnabled(false);

    jLabel6.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel6.setText("fjk;a");

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
    tblAULDinayanMitaPera.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

    btnDeleteAULDMitaPera.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnDeleteAULDMitaPera.setText("ඉවත් කරන්න ");
    btnDeleteAULDMitaPera.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDeleteAULDMitaPeraActionPerformed(evt);
        }
    });

    btnAddAULDMitaPera.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnAddAULDMitaPera.setText("එකතු කරන්න ");
    btnAddAULDMitaPera.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAddAULDMitaPeraActionPerformed(evt);
        }
    });

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

javax.swing.GroupLayout titleAUBKarainamLayout = new javax.swing.GroupLayout(titleAUBKarainam);
titleAUBKarainam.setLayout(titleAUBKarainamLayout);
titleAUBKarainamLayout.setHorizontalGroup(
    titleAUBKarainamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(titleAUBKarainamLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(titleAUBKarainamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8)
            .addGroup(titleAUBKarainamLayout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(10, 10, 10)
                .addComponent(txtWenathAbadithaUpakarana))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleAUBKarainamLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnDeleteAU)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddAU))
            .addComponent(cmbAbadithaUpakarana, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(titleAUBKarainamLayout.createSequentialGroup()
                .addGroup(titleAUBKarainamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(titleAUBKarainamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(dtpAULDinayanMitaPasu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleAUBKarainamLayout.createSequentialGroup()
                            .addComponent(btnDeleteAULDMitaPasu)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnAddAULDMitaPasu))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(titleAUBKarainamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(titleAUBKarainamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(dtpAULDinayanMitaPera, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleAUBKarainamLayout.createSequentialGroup()
                            .addComponent(btnDeleteAULDMitaPera)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnAddAULDMitaPera))))
                .addGap(8, 8, 8)))
        .addContainerGap())
    );
    titleAUBKarainamLayout.setVerticalGroup(
        titleAUBKarainamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(titleAUBKarainamLayout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cmbAbadithaUpakarana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(titleAUBKarainamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtWenathAbadithaUpakarana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel6))
            .addGap(13, 13, 13)
            .addGroup(titleAUBKarainamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnAddAU)
                .addComponent(btnDeleteAU))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(titleAUBKarainamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(titleAUBKarainamLayout.createSequentialGroup()
                    .addComponent(dtpAULDinayanMitaPasu, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(13, 13, 13)
                    .addGroup(titleAUBKarainamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDeleteAULDMitaPasu)
                        .addComponent(btnAddAULDMitaPasu))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(titleAUBKarainamLayout.createSequentialGroup()
                    .addComponent(dtpAULDinayanMitaPera, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(13, 13, 13)
                    .addGroup(titleAUBKarainamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDeleteAULDMitaPera)
                        .addComponent(btnAddAULDMitaPera))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(56, 56, 56))
    );

    btnUpdateAU.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnUpdateAU.setText("දත්ත යාවත්කාලීන කරන්න ");
    btnUpdateAU.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnUpdateAUActionPerformed(evt);
        }
    });

    btnRefrashAU.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnRefrashAU.setText("දත්ත නැවත පුරවන්න ");
    btnRefrashAU.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnRefrashAUActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout abadithaUpakaranaLayout = new javax.swing.GroupLayout(abadithaUpakarana);
    abadithaUpakarana.setLayout(abadithaUpakaranaLayout);
    abadithaUpakaranaLayout.setHorizontalGroup(
        abadithaUpakaranaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(abadithaUpakaranaLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(abadithaUpakaranaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(abadithaUpakaranaLayout.createSequentialGroup()
                    .addComponent(btnRefrashAU)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnUpdateAU))
                .addGroup(abadithaUpakaranaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxAbadithaUBKarai)
                    .addComponent(titleAUBKarainam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(27, Short.MAX_VALUE))
    );
    abadithaUpakaranaLayout.setVerticalGroup(
        abadithaUpakaranaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(abadithaUpakaranaLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(cbxAbadithaUBKarai)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(titleAUBKarainam, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(abadithaUpakaranaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnUpdateAU)
                .addComponent(btnRefrashAU))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jTabbedPane1.addTab("wdndê; WmlrK ", abadithaUpakarana);

    jLabel63.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel63.setText("ìßof.a$iajdñmqreIhdf.a ku ");

    txtSahakarugeNama.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, cbxWiwahakada, org.jdesktop.beansbinding.ELProperty.create("${selected}"), txtSahakarugeNama, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
    bindingGroup.addBinding(binding);

    jLabel64.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel64.setText("ìßof.a$iajdñmqreIhdf.a rdcldÍ ,smskh ");

    jLabel66.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel66.setText("ìßof.a$iajdñmqreIhdf.a /lshdj ");

    txtSahakarugeRakiyawa.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, cbxWiwahakada, org.jdesktop.beansbinding.ELProperty.create("${selected}"), txtSahakarugeRakiyawa, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
    bindingGroup.addBinding(binding);

    txtSahakarugeRLipinaya.setColumns(20);
    txtSahakarugeRLipinaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    txtSahakarugeRLipinaya.setRows(5);

    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, cbxWiwahakada, org.jdesktop.beansbinding.ELProperty.create("${selected}"), txtSahakarugeRLipinaya, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
    bindingGroup.addBinding(binding);

    jScrollPane6.setViewportView(txtSahakarugeRLipinaya);

    cmbDikkasadada.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cmbDikkasadada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tõ", "ke;" }));

    jLabel65.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel65.setText("oslalido jQfhao hk nj ");

    cmbNawathaWiwahaUweda.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cmbNawathaWiwahaUweda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tõ", "ke;" }));

    jLabel67.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel67.setText("kej; újdy jQfhao hk nj");

    jLabel68.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel68.setText("újdyh wid¾:l ùug wdndê; ;;ajh n,mEfõo ");

    cmbWiwahayaAsarthakada.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cmbWiwahayaAsarthakada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tõ", "ke;" }));

    txtSahakarugeDKA.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, cbxWiwahakada, org.jdesktop.beansbinding.ELProperty.create("${selected}"), txtSahakarugeDKA, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
    bindingGroup.addBinding(binding);

    jLabel71.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel71.setText("ìßof.a$iajdñmqreIhdf.a ÿrl:k wxlh ");

    cbxWiwahakada.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cbxWiwahakada.setText("újdylhs ");

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
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                            .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(cmbNawathaWiwahaUweda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtSahakarugeDKA)
                        .addComponent(jScrollPane6)
                        .addComponent(txtSahakarugeRakiyawa)
                        .addComponent(txtSahakarugeNama)))
                .addGroup(titleWiwahakanamLayout.createSequentialGroup()
                    .addComponent(jLabel68)
                    .addGap(36, 36, 36)
                    .addComponent(cmbWiwahayaAsarthakada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(cbxWiwahakada))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    titleWiwahakanamLayout.setVerticalGroup(
        titleWiwahakanamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(titleWiwahakanamLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(cbxWiwahakada)
            .addGap(11, 11, 11)
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
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    btnUpdateWiwahaya.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnUpdateWiwahaya.setText("දත්ත යාවත්කාලීන කරන්න ");
    btnUpdateWiwahaya.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnUpdateWiwahayaActionPerformed(evt);
        }
    });

    btnRefrashWiwahaya.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnRefrashWiwahaya.setText("දත්ත නැවත පුරවන්න ");
    btnRefrashWiwahaya.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnRefrashWiwahayaActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout wiwahayaLayout = new javax.swing.GroupLayout(wiwahaya);
    wiwahaya.setLayout(wiwahayaLayout);
    wiwahayaLayout.setHorizontalGroup(
        wiwahayaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(wiwahayaLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(wiwahayaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(wiwahayaLayout.createSequentialGroup()
                    .addComponent(titleWiwahakanam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 21, Short.MAX_VALUE))
                .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, wiwahayaLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnRefrashWiwahaya)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnUpdateWiwahaya)))
            .addContainerGap())
    );
    wiwahayaLayout.setVerticalGroup(
        wiwahayaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(wiwahayaLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(titleWiwahakanam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(wiwahayaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnUpdateWiwahaya)
                .addComponent(btnRefrashWiwahaya))
            .addContainerGap(196, Short.MAX_VALUE))
    );

    jTabbedPane1.addTab("újdyh ", wiwahaya);

    jLabel9.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel9.setText("fmd,sishg nefokúg ;k;=r ");

    cmbPBadenawitaThanathura.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cmbPBadenawitaThanathura.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "fmd,sia fldia;dm,a ", "Wm fmd,sia mÍlaIl ", "iyldr fmd,sia wêldÍ" }));

    cmbWishramaYWThanathura.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cmbWishramaYWThanathura.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "fmd,sia fldia;dm,a ", "fmd,sia ierhka ", "Wm fmd,sia mÍlaIl", "fmd,sia mÍlaIl", "m%Odk fmd,sia mÍlaIl  ", "iyldr fmd,sia wêldÍ ", "fcHIaG fmd,sia wêldÍ", "ksfhdacH fmd,sia m;s" }));

    jLabel10.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel10.setText("úY%du hkúg ;k;=r ");

    jLabel11.setFont(new java.awt.Font("FMBindumathi", 0, 14)); // NOI18N
    jLabel11.setText("ks;H fiajd wxlh ");

    txtNilaAnkaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    jLabel12.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel12.setText("Wm fiajd wxlh ");

    txtUpasewaAnkaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    jLabel14.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel14.setText("rKúre'ye wxlh ");

    txtRanawiruHadunumAnkaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

    cmbWEAnuyuththada.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cmbWEAnuyuththada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tõ", "ke;" }));
    cmbWEAnuyuththada.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cmbWEAnuyuththadaActionPerformed(evt);
        }
    });

    jLabel15.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel15.setText("úfYaI tallhlg wkqhq;a;j isáfhao ");

    titleWEAnuyuththanam.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Iskoola Pota", 0, 14))); // NOI18N

    jLabel16.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel16.setText("úfYaI tallh ");

    cmbWisheshaEkakaya.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    cmbWisheshaEkakaya.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "úfYaI ld¾h n,ldh ", "m%N+ wdrlaIl wxYh ", "fmd,sia wkq lxv ", "wmrdO mÍlaIK fomd¾;fïka;=j ", ";%ia;jdoS úu¾Yk tallh" }));

    jLabel17.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
    jLabel17.setText("neÿk oskh ");

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
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbWisheshaEkakaya, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(titleWEAnuyuththanamLayout.createSequentialGroup()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAnuKanda)))
        .addGap(33, 33, 33)
        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(dtpWEBadunaDinaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(21, Short.MAX_VALUE))
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

    btnUpdateDaruwan1.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnUpdateDaruwan1.setText("දත්ත යාවත්කාලීන කරන්න ");
    btnUpdateDaruwan1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnUpdateDaruwan1ActionPerformed(evt);
        }
    });

    btnRefrashDaruwan1.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
    btnRefrashDaruwan1.setText("දත්ත නැවත පුරවන්න ");
    btnRefrashDaruwan1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnRefrashDaruwan1ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout thanathuraLayout = new javax.swing.GroupLayout(thanathura);
    thanathura.setLayout(thanathuraLayout);
    thanathuraLayout.setHorizontalGroup(
        thanathuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(thanathuraLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(thanathuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(thanathuraLayout.createSequentialGroup()
                    .addComponent(btnRefrashDaruwan1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnUpdateDaruwan1))
                .addGroup(thanathuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(thanathuraLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbPBadenawitaThanathura, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(thanathuraLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbWishramaYWThanathura, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(thanathuraLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNilaAnkaya, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(thanathuraLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUpasewaAnkaya, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(thanathuraLayout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRanawiruHadunumAnkaya, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(thanathuraLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmbWEAnuyuththada, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(titleWEAnuyuththanam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator4)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    thanathuraLayout.setVerticalGroup(
        thanathuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(thanathuraLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(thanathuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel9)
                .addComponent(cmbPBadenawitaThanathura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(thanathuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel10)
                .addComponent(cmbWishramaYWThanathura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(thanathuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel11)
                .addComponent(txtNilaAnkaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(thanathuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel12)
                .addComponent(txtUpasewaAnkaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(thanathuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel14)
                .addComponent(txtRanawiruHadunumAnkaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(thanathuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(cmbWEAnuyuththada)
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(titleWEAnuyuththanam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(1, 1, 1)
            .addGroup(thanathuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnUpdateDaruwan1)
                .addComponent(btnRefrashDaruwan1))
            .addContainerGap(218, Short.MAX_VALUE))
    );

    jTabbedPane1.addTab(";k;=r ", thanathura);

    jPanel8.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 19, 700, 600));

    jTabbedPane2.addTab("hdj;ald,sk lrkak ", jPanel8);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jTabbedPane2)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
    );

    bindingGroup.bind();

    pack();
    setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmbSeachByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSeachByActionPerformed

        changeTableColomnNames();
        seachAll();
        changeSeachTabelCWidth();
        changeColumnFont();

    }//GEN-LAST:event_cmbSeachByActionPerformed

    private void changeColumnFont() {
        int s_index = cmbSeachBy.getSelectedIndex();
        if (s_index == 1 || s_index == 3) {//JHA OR EMAIL
            setSTableColumnFont(FONT_ISKOLAPOTHA, 0);
        } else {
            setSTableColumnFont(FONT_MALITHI, 0);
        }
    }

    private void setSTableColumnFont(String font_name, int... indexes) {
        for (int index : indexes) {
            tblSeachedData.getColumnModel().getColumn(index).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(
                        JTable table,
                        Object value,
                        boolean isSelected,
                        boolean hasFocus,
                        int row,
                        int column) {
                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    setFont(new java.awt.Font(font_name, 0, 14));
                    return this;
                }
            });
        }
    }

    private void setSeachTableCWidht(int... w) {
        TableColumnModel cm = tblSeachedData.getColumnModel();
        cm.getColumn(0).setPreferredWidth(w[0]);
        cm.getColumn(1).setPreferredWidth(w[1]);
        cm.getColumn(2).setPreferredWidth(w[2]);

    }

    private void changeSeachTabelCWidth() {

        switch (cmbSeachBy.getSelectedIndex()) {
            case 0://සමාජික අංකය
                setSeachTableCWidht(93, 103, 390);
                break;
            case 1://ජාතික හැදුනුම්පත් අංකය
                setSeachTableCWidht(148, 101, 337);
                break;
            case 2://සම්පුර්ණ නම
                setSeachTableCWidht(457, 114, 15);
                break;
            case 3://විද්‍යුත් ලිපිනය
                setSeachTableCWidht(159, 99, 328);
                break;
            case 4://ජංගම දුරකථන අංකය 
                setSeachTableCWidht(135, 112, 339);
                break;
            case 5://ස්ථාවර දුරකථන අංකය
                setSeachTableCWidht(142, 97, 347);
                break;
            case 6://නිල අංකය
                setSeachTableCWidht(77, 105, 404);
                break;
            case 7://උපසේවා අංකය
                setSeachTableCWidht(110, 106, 370);
                break;
            case 8://රණවිරු හැදුනුම්පත් අංකය 
                setSeachTableCWidht(156, 95, 335);
                break;
        }
    }

    private void txtSeachFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSeachFieldKeyReleased

        seachAll();
    

    }//GEN-LAST:event_txtSeachFieldKeyReleased

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown

        txtSeachField.setText("");
        seachAll();
        jTabbedPane2.setSelectedIndex(0);

    }//GEN-LAST:event_formComponentShown

    private void txtAwasanWarataSKSthanayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAwasanWarataSKSthanayaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAwasanWarataSKSthanayaActionPerformed

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
            txtWenathHani.setText("");
        }

    }//GEN-LAST:event_cmbSharirikaHaniActionPerformed

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
            JOptionPane.showMessageDialog(this, "කරුණාකර නිවැරදි දත්ත පමණක් ඇතුළු කරන්න..!", "දත්ත නිවැරදි නොවේ", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btnAddAthuruAbadaActionPerformed

    private void btnDeleteAthuruAbadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAthuruAbadaActionPerformed

        deleteTableRow(tblAthuruAbada);

    }//GEN-LAST:event_btnDeleteAthuruAbadaActionPerformed

    private void btnDAllAthuruAbadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDAllAthuruAbadaActionPerformed

        deleteAllTableRow(tblAthuruAbada);

    }//GEN-LAST:event_btnDAllAthuruAbadaActionPerformed

    private void cmbAthuruAAdda1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAthuruAAdda1ActionPerformed

        if (cmbAthuruAAdda1.getSelectedIndex() == 0) {
            setEnableAA(true);
        } else {
            setEnableAA(false);
        }

    }//GEN-LAST:event_cmbAthuruAAdda1ActionPerformed

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

    private void txtRefrashSamajikayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRefrashSamajikayaActionPerformed

        if (tblSeachedData.getSelectedRow() != -1) {
            showAllSamajikayaData();
        } else {
            JOptionPane.showMessageDialog(this, "කරුණාකර විස්තර පෙන්විය යුතු සාමාජිකයා තෝරන්න", "තෝරන්න", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_txtRefrashSamajikayaActionPerformed

    private void btnUpdateSamajikayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateSamajikayaActionPerformed

        if (isSamajikaDataRedyToUpdate()) {
            int userAnswer = JOptionPane.showConfirmDialog(this, "දත්ත යාවත්කාලීන කිරීමට අවශ්‍යද ?", "අවශ්‍යද ?", JOptionPane.YES_NO_OPTION);
            if (userAnswer == 0) {
                updateSamajikaya();
            }
        } else {
            JOptionPane.showMessageDialog(this, "කරුණාකර නිවැරදිව ඉල්ලුම් පත්‍රය පුරවන්න", "නිවැරදි නොවේ", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btnUpdateSamajikayaActionPerformed

    private void btnDeleteAllDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAllDataActionPerformed

        detedeMember();


    }//GEN-LAST:event_btnDeleteAllDataActionPerformed

    private void detedeMember() {
        int user_input = JOptionPane.showConfirmDialog(this, "මෙම සාමාජිකයා ඉවත් කල යුතුද ", "ඉවත් කරන්න", JOptionPane.YES_NO_OPTION);
        if (user_input == 0) {
            try {
                if (tblSeachedData.getSelectedRow() != -1) {

                    String SA = tblSeachedData.getValueAt(tblSeachedData.getSelectedRow(), 1).toString();

                    //updte deleted_members table
                    String sql1 = "SELECT SN,JHA FROM SMJK WHERE  SA='" + SA + "'";
                    ResultSet rs1 = SQLConnection.SqlConnection.getData(sql1);
                    if (rs1.first()) {
                        String NAME = rs1.getString("SN");
                        String NIC = rs1.getString("JHA");
                        String RESON = txtIwathKirimataHetuwa.getText();
                        String sql2 = "INSERT INTO DELETED_MEMBERS(SID,NIC,NAME,RESON) VALUES("
                                + "'" + SA + "',"
                                + "'" + NIC + "',"
                                + "'" + NAME + "',"
                                + "'" + RESON + "'"
                                + ")";
                        SQLConnection.SqlConnection.updateDB(sql2);
                    }
                    //update sucsusfull

                    //delete member
                    String sqls = "";
                    sqls += "DELETE FROM AASS WHERE SA='" + SA + "';";
                    //sqls += "DELETE FROM DWWW WHERE SA='" + SA + "';";
                    sqls += "DELETE FROM DWW WHERE SA='" + SA + "';";
                    sqls += "DELETE FROM SDWWNH WHERE SA='" + SA + "';";
                    sqls += "DELETE FROM DWWNS WHERE SA='" + SA + "';";
                    //sqls += "DELETE FROM MWW WHERE SA='" + SA + "';";
                    sqls += "DELETE FROM MWLS WHERE SA='" + SA + "';";
                    //sqls += "DELETE FROM WWW WHERE SA='" + SA + "';";
                    sqls += "DELETE FROM SWWNH WHERE SA='" + SA + "';";
                    sqls += "DELETE FROM WWNS WHERE SA='" + SA + "';";
                    sqls += "DELETE FROM WW WHERE SA='" + SA + "';";
                    sqls += "DELETE FROM SD WHERE SA='" + SA + "';";
                    sqls += "DELETE FROM SLS WHERE SA='" + SA + "';";
                    sqls += "DELETE FROM SJWP WHERE SA='" + SA + "';";
                    sqls += "DELETE FROM SL WHERE SA='" + SA + "';";
                    sqls += "DELETE FROM SS WHERE SA='" + SA + "';";
                    sqls += "DELETE FROM SSWE WHERE SA='" + SA + "';";
                    sqls += "DELETE FROM ST WHERE SA='" + SA + "';";
                    ResultSet r = SQLConnection.SqlConnection.getData("SELECT UA FROM SAU WHERE SA='" + SA + "'");
                    while (r.next()) {
                        String UA = r.getString("UA");
                        sqls += "DELETE FROM AULDMITAPERA WHERE UA='" + UA + "';";
                        sqls += "DELETE FROM AULDMITAPASU WHERE UA='" + UA + "';";
                    }
                    sqls += "DELETE FROM SAU WHERE SA='" + SA + "';";
                    sqls += "DELETE FROM TRPWLS WHERE SA='" + SA + "';";
                    sqls += "DELETE FROM SMJK WHERE SA='" + SA + "';";

                    String[] qs = sqls.split(";");
                    for (String q : qs) {
                        SQLConnection.SqlConnection.updateDB(q);
                    }
                    //selete sucsusfull
                } else {
                    JOptionPane.showMessageDialog(this, "කරුණාකර ඉවත් කිරීමට අවශ්‍ය සාමාජිකයා තෝරන්න", "තෝරන්න", JOptionPane.INFORMATION_MESSAGE);

                }
            } catch (Exception e) {
               new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
            }
        }
        seachAll();
    }

    private void btnRefrashTrasthaPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrashTrasthaPActionPerformed

        if (tblSeachedData.getSelectedRow() != -1) {
            showAllTrasthaData();
        } else {
            JOptionPane.showMessageDialog(this, "කරුණාකර විස්තර පෙන්විය යුතු සාමාජිකයා තෝරන්න", "තෝරන්න", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btnRefrashTrasthaPActionPerformed

    private void btnUpdateTrasthaPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateTrasthaPActionPerformed

        if (isThrasthaDataIsRedyToUpdate()) {
            int userAnswer = JOptionPane.showConfirmDialog(this, "දත්ත යාවත්කාලීන කිරීමට අවශ්‍යද ?", "අවශ්‍යද ?", JOptionPane.YES_NO_OPTION);
            if (userAnswer == 0) {
                updateThrasthaPraharaya();
            }
        } else {
            JOptionPane.showMessageDialog(this, "කරුණාකර නිවැරදිව ඉල්ලුම් පත්‍රය පුරවන්න", "නිවැරදි නොවේ", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btnUpdateTrasthaPActionPerformed

    private void btnRefrashDaruwanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrashDaruwanActionPerformed

        showAllDaruwanData();
        TitledBorder tb = (TitledBorder) titleDaruwanSitinam.getBorder();
        titleDaruwanSitinam.setBorder(BorderFactory.createTitledBorder(null, tb.getTitle(),
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Iskoola Pota", 0, 14)));


    }//GEN-LAST:event_btnRefrashDaruwanActionPerformed

    private void btnUpdateDaruwanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateDaruwanActionPerformed

        updateDaruwan();


    }//GEN-LAST:event_btnUpdateDaruwanActionPerformed

    private void btnUpdateTblDaruwanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateTblDaruwanActionPerformed

        if (tblDaruwan.getSelectedRow() != -1) {
            String NAME = txtDaruwageNamaEdit.getText();
            Date UD = dtpDaruwageDOBEdit.getSelectedDate().getTime();
            String DT = cmbDaruwageDanataThathwayaEdit.getSelectedItem().toString();
            String ABTD = cmbDaruwaAbadithadaEdit.getSelectedItem().toString();

            int sr = tblDaruwan.getSelectedRow();
            tblDaruwan.setValueAt(NAME, sr, 0);
            tblDaruwan.setValueAt(dateToString(UD), sr, 1);
            tblDaruwan.setValueAt(DT, sr, 2);
            tblDaruwan.setValueAt(ABTD, sr, 3);
        }

    }//GEN-LAST:event_btnUpdateTblDaruwanActionPerformed

    private void btnDAllDaruwaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDAllDaruwaActionPerformed

        deleteAllTableRow(tblDaruwan);

    }//GEN-LAST:event_btnDAllDaruwaActionPerformed

    private void btnDeleteDaruwaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteDaruwaActionPerformed

        deleteTableRow(tblDaruwan);

    }//GEN-LAST:event_btnDeleteDaruwaActionPerformed

    private void btnAddDaruwaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDaruwaActionPerformed

        if (!txtDaruwageNama.getText().isEmpty()) {
            DefaultTableModel tmAA = (DefaultTableModel) tblDaruwan.getModel();
            Vector rowData = new Vector();
            rowData.add(txtDaruwageNama.getText());
            rowData.add(dateToString(dtpDaruwageDOB.getSelectedDate().getTime()));
            rowData.add(cmbDaruwageDanataThathwaya.getSelectedItem().toString());
            rowData.add(cmbDaruwaAbadithada.getSelectedItem().toString());
            tmAA.addRow(rowData);
            txtDaruwageNama.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "කරුණාකර දරුවාගේ නම සදහන් කරන්න", "සදහන් කර නොමැත ", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnAddDaruwaActionPerformed

    private void tblDaruwanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDaruwanMouseReleased

        if (tblDaruwan.getSelectedRow() != -1) {
            String NAME = tblDaruwan.getValueAt(tblDaruwan.getSelectedRow(), 0).toString();
            LocalDate UD = LocalDate.parse(tblDaruwan.getValueAt(tblDaruwan.getSelectedRow(), 1).toString());
            String DT = tblDaruwan.getValueAt(tblDaruwan.getSelectedRow(), 2).toString();
            String ABTD = tblDaruwan.getValueAt(tblDaruwan.getSelectedRow(), 3).toString();

            txtDaruwageNamaEdit.setText(NAME);
            dtpDaruwageDOBEdit.setSelectedDate(new GregorianCalendar(UD.getYear(),
                    UD.getMonth().getValue() - 1,
                    UD.getDayOfMonth()));
            setComboBoxData(cmbDaruwageDanataThathwayaEdit, DT);
            setComboBoxData(cmbDaruwaAbadithadaEdit, ABTD);
        }

    }//GEN-LAST:event_tblDaruwanMouseReleased

    private void cbxDaruwanSitiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDaruwanSitiActionPerformed

        if (cbxDaruwanSiti.isSelected()) {
            setEnableDaruwan(true);
        } else {
            setEnableDaruwan(false);
            DefaultTableModel model = (DefaultTableModel) tblDaruwan.getModel();
            model.setRowCount(0);
        }

    }//GEN-LAST:event_cbxDaruwanSitiActionPerformed

    private void btnUpdateWiwahayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateWiwahayaActionPerformed

        int userAnswer = JOptionPane.showConfirmDialog(this, "දත්ත යාවත්කාලීන කිරීමට අවශ්‍යද ?", "අවශ්‍යද ?", JOptionPane.YES_NO_OPTION);
        if (userAnswer == 0) {
            updateWiwahaya();
        }


    }//GEN-LAST:event_btnUpdateWiwahayaActionPerformed

    private void btnRefrashWiwahayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrashWiwahayaActionPerformed

        showAllWiwahayaData();

    }//GEN-LAST:event_btnRefrashWiwahayaActionPerformed

    private void cmbWEAnuyuththadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbWEAnuyuththadaActionPerformed

        if (cmbWEAnuyuththada.getSelectedIndex() == 0) {
            setEnableWE(true);
        } else {
            setEnableWE(false);
        }

    }//GEN-LAST:event_cmbWEAnuyuththadaActionPerformed

    private void btnUpdateDaruwan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateDaruwan1ActionPerformed

        int userAnswer = JOptionPane.showConfirmDialog(this, "දත්ත යාවත්කාලීන කිරීමට අවශ්‍යද ?", "අවශ්‍යද ?", JOptionPane.YES_NO_OPTION);
        if (userAnswer == 0) {
            updateThanathura();
        }

    }//GEN-LAST:event_btnUpdateDaruwan1ActionPerformed

    private void btnRefrashDaruwan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrashDaruwan1ActionPerformed

        showAllThanathuraData();

    }//GEN-LAST:event_btnRefrashDaruwan1ActionPerformed

    private void cbxAbadithaUBKaraiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAbadithaUBKaraiActionPerformed

        setEnableAbadithUpakarana(cbxAbadithaUBKarai.isSelected());

    }//GEN-LAST:event_cbxAbadithaUBKaraiActionPerformed

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

    private void cmbAbadithaUpakaranaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAbadithaUpakaranaActionPerformed

        if ("fjk;a".equals(cmbAbadithaUpakarana.getSelectedItem().toString())) {
            txtWenathAbadithaUpakarana.setEnabled(true);
        } else {
            txtWenathAbadithaUpakarana.setEnabled(false);
        }
    }//GEN-LAST:event_cmbAbadithaUpakaranaActionPerformed

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
            if ("වෙනත්".equals(cmbAbadithaUpakarana.getSelectedItem().toString())) {
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

    //---------------------------------------------------------------------------------------------------------//---------------------------------------------------------------------------------------------------------
    /**/    private final ArrayList<AULDinayan> auldMETAPERA = new ArrayList<>();//---------
    /**/    private final ArrayList<AULDinayan> auldMETAPASU = new ArrayList<>();//---------
    //---------------------------------------------------------------------------------------------------------

    private void btnDeleteAUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAUActionPerformed

        if (tblAU.getSelectedRow() != -1) {
            ArrayList<AULDinayan> indexes_for_r = new ArrayList<>();
            auldMETAPERA.stream().filter((auld1) -> (auld1.getIndex() == tblAU.getSelectedRow())).forEach((auld1) -> indexes_for_r.add(auld1));
            indexes_for_r.stream().forEach((indexes_for_r1) -> auldMETAPERA.remove(indexes_for_r1));
            auldMETAPERA.stream().filter((auld1) -> (auld1.getIndex() > tblAU.getSelectedRow())).forEach((auld1) -> auld1.setIndex(auld1.getIndex() - 1));
        }
        deleteTableRow(tblAU);
        DefaultTableModel tm = (DefaultTableModel) tblAULDinayanMitaPasu.getModel();
        tm.setRowCount(0);

    }//GEN-LAST:event_btnDeleteAUActionPerformed

    private void btnAddAULDMitaPasuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAULDMitaPasuActionPerformed

        ArrayList<String> dinayn = new ArrayList<>();
        if (tblAU.getSelectedRow() != -1) {
            DefaultTableModel tbl2 = (DefaultTableModel) tblAULDinayanMitaPasu.getModel();
            tbl2.setRowCount(0);
            int showIndex = tblAU.getSelectedRow();
            if (!Funtional.getBooleanFun(() -> {
                boolean isIn = false;
                for (AULDinayan auld1 : auldMETAPASU) {
                    if (auld1.getIndex() == showIndex) {
                        if (auld1.getDinaya().equals(DateToString(dtpAULDinayanMitaPasu.getSelectedDate().getTime()))) {
                            isIn = true;
                        }
                    }
                }
                return isIn;
            })) {
                auldMETAPASU.add(new AULDinayan(DateToString(dtpAULDinayanMitaPasu.getSelectedDate().getTime()), showIndex));
                auldMETAPASU.stream().filter((auld1) -> (auld1.getIndex() == showIndex)).forEach((auld1) -> {
                    dinayn.add(auld1.getDinaya());
                });
                dinayn.forEach(d -> {
                    DefaultTableModel tbl = (DefaultTableModel) tblAULDinayanMitaPasu.getModel();
                    Vector rowData = new Vector();
                    rowData.add(d);
                    tbl.addRow(rowData);
                });
            } else {
                auldMETAPASU.stream().filter((auld1) -> (auld1.getIndex() == showIndex)).forEach((auld1) -> {
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

    private void btnDeleteAULDMitaPasuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAULDMitaPasuActionPerformed
        ArrayList<String> dinayn = new ArrayList<>();

        if (tblAULDinayanMitaPasu.getSelectedRow() != -1) {
            for (int i = 0; i < auldMETAPASU.size(); i++) {
                if (auldMETAPASU.get(i).getIndex() == tblAU.getSelectedRow()) {
                    if (auldMETAPASU.get(i).getDinaya().equals(tblAULDinayanMitaPasu.getValueAt(tblAULDinayanMitaPasu.getSelectedRow(), 0))) {
                        auldMETAPASU.remove(auldMETAPASU.get(i));
                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "කරුණාකර ඉවත් කිරීමට අවශ්‍ය දිනය තෝරන්න", "තෝරා නොමැත", JOptionPane.INFORMATION_MESSAGE);
        }
        DefaultTableModel tbl = (DefaultTableModel) tblAULDinayanMitaPasu.getModel();
        tbl.setRowCount(0);
        auldMETAPASU.stream().filter((auld1) -> (auld1.getIndex() == tblAU.getSelectedRow())).forEach((auld1) -> {
            dinayn.add(auld1.getDinaya());
        });
        dinayn.forEach(d -> {
            Vector rowData = new Vector();
            rowData.add(d);
            tbl.addRow(rowData);
        });

    }//GEN-LAST:event_btnDeleteAULDMitaPasuActionPerformed

    private void btnUpdateAUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateAUActionPerformed

        int userAnswer = JOptionPane.showConfirmDialog(this, "දත්ත යාවත්කාලීන කිරීමට අවශ්‍යද ?", "අවශ්‍යද ?", JOptionPane.YES_NO_OPTION);
        if (userAnswer == 0) {
            updateAbadithaUpakarana();
        }

    }//GEN-LAST:event_btnUpdateAUActionPerformed

    private void btnRefrashAUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrashAUActionPerformed

        showAllAbadithaUpakaranaData();

    }//GEN-LAST:event_btnRefrashAUActionPerformed

    private void btnAddSahanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSahanaActionPerformed

        if (!txtSahanaya.getText().isEmpty()) {
            if (!txtSahanaLabanaSthanaya.getText().isEmpty()) {
                DefaultTableModel tmAA = (DefaultTableModel) tblSahana.getModel();
                Vector rowData = new Vector();
                rowData.add(txtSahanaya.getText());
                rowData.add(txtSahanaLabanaSthanaya.getText());
                rowData.add(dateToString(dtpSahanaAWLD.getSelectedDate().getTime()));
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

    private void cbxSahanaLabiAthaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSahanaLabiAthaActionPerformed

        setEnableSahana(cbxSahanaLabiAtha.isSelected());

    }//GEN-LAST:event_cbxSahanaLabiAthaActionPerformed

    private void btnRefrashSahanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrashSahanaActionPerformed

        showAllSahanaData();

    }//GEN-LAST:event_btnRefrashSahanaActionPerformed

    private void btnUpdateSahanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateSahanaActionPerformed

        int userAnswer = JOptionPane.showConfirmDialog(this, "දත්ත යාවත්කාලීන කිරීමට අවශ්‍යද ?", "අවශ්‍යද ?", JOptionPane.YES_NO_OPTION);
        if (userAnswer == 0) {
            updateAllSahana();
        }

    }//GEN-LAST:event_btnUpdateSahanaActionPerformed

    private void cmbMasukaWLabannedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMasukaWLabannedaActionPerformed

        setEnableMasikaWatup(getBooleanValue(cmbMasukaWLabanneda.getSelectedItem().toString()));

    }//GEN-LAST:event_cmbMasukaWLabannedaActionPerformed

    private void cmbWisramaWLabannedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbWisramaWLabannedaActionPerformed

        boolean x = getBooleanValue(cmbWisramaWLabanneda.getSelectedItem().toString());
        boolean y = getBooleanValue(cmbDWWLabanneda.getSelectedItem().toString());
        if (x) {
            DefaultTableModel ww = (DefaultTableModel) tblWWNHethu.getModel();
            ww.setRowCount(0);
        }

        setEnableWisramaWatup(x, y);


    }//GEN-LAST:event_cmbWisramaWLabannedaActionPerformed

    private void setEnableWisramaWatup(boolean x, boolean y) {
        DefaultTableModel ww = (DefaultTableModel) tblWWNHethu.getModel();
        DefaultTableModel dww = (DefaultTableModel) tblDWWNHethu.getModel();
        ww.setRowCount(0);
        dww.setRowCount(0);
        setA(x || y);
        setB(x);
        setC(y);
        setD(!x);
        setE(!y);
    }

    private void cmbDWWLabannedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDWWLabannedaActionPerformed

        boolean x = getBooleanValue(cmbWisramaWLabanneda.getSelectedItem().toString());
        boolean y = getBooleanValue(cmbDWWLabanneda.getSelectedItem().toString());
        if (y) {
            DefaultTableModel dww = (DefaultTableModel) tblDWWNHethu.getModel();
            dww.setRowCount(0);
        }
        setA(x || y);
        setB(x);
        setC(y);
        setD(!x);
        setE(!y);

    }//GEN-LAST:event_cmbDWWLabannedaActionPerformed

    private void setA(boolean b) {
        txtWWAnkaya.setEnabled(b);
        txtWWLPLKottashaya.setEnabled(b);
        if (!b) {
            txtWWAnkaya.setText("");
            txtWWLPLKottashaya.setText("");
            txtWWAnkaya.setBackground(Color.WHITE);
            txtWWLPLKottashaya.setBackground(Color.WHITE);

        }
    }

    private void setB(boolean b) {
        txtWWWatinakama.setEnabled(b);
        if (!b) {
            txtWWWatinakama.setText("");
            txtWWWatinakama.setBackground(Color.WHITE);
        }
    }

    private void setC(boolean b) {
        txtDWWWatinakama.setEnabled(b);
        if (!b) {
            txtDWWWatinakama.setText("");
            txtDWWWatinakama.setBackground(Color.WHITE);
        }
    }

    private void setD(boolean b) {
        titleWWNolabenam.setEnabled(b);

        for (int i = 0; i < titleWWNolabenam.getComponentCount(); i++) {
            titleWWNolabenam.getComponent(i).setEnabled(b);
            if (!b) {
                try {
                    JTextField f = (JTextField) titleWWNolabenam.getComponent(i);
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


    private void btnDAllWWNHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDAllWWNHActionPerformed

        deleteAllTableRow(tblWWNHethu);
    }//GEN-LAST:event_btnDAllWWNHActionPerformed

    private void btnDeleteWWNHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteWWNHActionPerformed

        deleteTableRow(tblWWNHethu);

    }//GEN-LAST:event_btnDeleteWWNHActionPerformed

    private void btnAddWWNHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddWWNHActionPerformed

        setTableDateSingleValue(tblWWNHethu, cmbWWNHethuwa);

    }//GEN-LAST:event_btnAddWWNHActionPerformed

    private void btnDAllDWWNHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDAllDWWNHActionPerformed

        deleteAllTableRow(tblDWWNHethu);

    }//GEN-LAST:event_btnDAllDWWNHActionPerformed

    private void btnDeleteDWWNHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteDWWNHActionPerformed

        deleteTableRow(tblDWWNHethu);
    }//GEN-LAST:event_btnDeleteDWWNHActionPerformed

    private void btnAddDWWNHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDWWNHActionPerformed

        setTableDateSingleValue(tblDWWNHethu, cmbDWWNHethuwa);

    }//GEN-LAST:event_btnAddDWWNHActionPerformed

    private void btnUpdateMasikaWatupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateMasikaWatupActionPerformed

        int userAnswer = JOptionPane.showConfirmDialog(this, "දත්ත යාවත්කාලීන කිරීමට අවශ්‍යද ?", "අවශ්‍යද ?", JOptionPane.YES_NO_OPTION);
        if (userAnswer == 0) {
            if (isMasikaWatupDataReady()) {
                updateMasikaWatupData();
                showAllMasikaWatupData();
            }

        }


    }//GEN-LAST:event_btnUpdateMasikaWatupActionPerformed

    private void btnRefrashMasikaWatupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrashMasikaWatupActionPerformed

        showAllMasikaWatupData();

    }//GEN-LAST:event_btnRefrashMasikaWatupActionPerformed

    private void btnDeleteAULDMitaPeraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAULDMitaPeraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteAULDMitaPeraActionPerformed

    private void btnAddAULDMitaPeraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAULDMitaPeraActionPerformed

        ArrayList<String> dinayn = new ArrayList<>();
        if (tblAU.getSelectedRow() != -1) {
            DefaultTableModel tbl2 = (DefaultTableModel) tblAULDinayanMitaPera.getModel();
            tbl2.setRowCount(0);
            int showIndex = tblAU.getSelectedRow();
            if (!Funtional.getBooleanFun(() -> {
                boolean isIn = false;
                for (AULDinayan auld1 : auldMETAPERA) {
                    if (auld1.getIndex() == showIndex) {
                        if (auld1.getDinaya().equals(DateToString(dtpAULDinayanMitaPera.getSelectedDate().getTime()))) {
                            isIn = true;
                        }
                    }
                }
                return isIn;
            })) {
                auldMETAPERA.add(new AULDinayan(DateToString(dtpAULDinayanMitaPera.getSelectedDate().getTime()), showIndex));
                auldMETAPERA.stream().filter((auld1) -> (auld1.getIndex() == showIndex)).forEach((auld1) -> {
                    dinayn.add(auld1.getDinaya());
                });
                dinayn.forEach(d -> {
                    DefaultTableModel tbl = (DefaultTableModel) tblAULDinayanMitaPera.getModel();
                    Vector rowData = new Vector();
                    rowData.add(d);
                    tbl.addRow(rowData);
                });
            } else {
                auldMETAPERA.stream().filter((auld1) -> (auld1.getIndex() == showIndex)).forEach((auld1) -> {
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

    private void btnRefrashWisramaWatupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrashWisramaWatupActionPerformed

        showAllWisramaWatup();

    }//GEN-LAST:event_btnRefrashWisramaWatupActionPerformed

    private void btnUpdateWisramaWatupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateWisramaWatupActionPerformed
        int userAnswer = JOptionPane.showConfirmDialog(this, "දත්ත යාවත්කාලීන කිරීමට අවශ්‍යද ?", "අවශ්‍යද ?", JOptionPane.YES_NO_OPTION);

        if (userAnswer == 0) {
            updateWishramaWatup();
        }

    }//GEN-LAST:event_btnUpdateWisramaWatupActionPerformed

    private void txtSeachFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSeachFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSeachFieldActionPerformed

    private String DateToString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

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

    private void setEnableDaruwan(boolean b) {
        titleDaruwanSitinam.setEnabled(b);
        for (int i = 0; i < titleDaruwanSitinam.getComponentCount(); i++) {
            titleDaruwanSitinam.getComponent(i).setEnabled(b);
        }
        tblDaruwan.setEnabled(b);
        if (!b) {
            TitledBorder tb = (TitledBorder) titleDaruwanSitinam.getBorder();
            titleDaruwanSitinam.setBorder(BorderFactory.createTitledBorder(null, tb.getTitle(),
                    TitledBorder.DEFAULT_JUSTIFICATION,
                    TitledBorder.DEFAULT_POSITION,
                    new java.awt.Font("FMMalithi", 0, 14)));
        }
    }

    private void updateDaruwan() {
        if (tblSeachedData.getSelectedRow() != -1) {

            String SA = lblSA.getText();
            if (isDaruwanDataReady()) {
                int userAnswer = JOptionPane.showConfirmDialog(this, "දත්ත යාවත්කාලීන කිරීමට අවශ්‍යද ?", "අවශ්‍යද ?", JOptionPane.YES_NO_OPTION);
                if (userAnswer == 0) {
                    if (cbxDaruwanSiti.isSelected()) {
                        try {
                            String sql1 = "DELETE FROM SD WHERE SA='" + SA + "'";
                            SQLConnection.SqlConnection.updateDB(sql1);

                            for (int i = 0; i < tblDaruwan.getRowCount(); i++) {
                                String NAME = tblDaruwan.getValueAt(i, 0).toString();
                                String UD = tblDaruwan.getValueAt(i, 1).toString();
                                String DT = tblDaruwan.getValueAt(i, 2).toString();
                                String ABTD = tblDaruwan.getValueAt(i, 3).toString();
                                new SaveSDaruwan(SA, UD, NAME, DT, ABTD).save();
                            }

                        } catch (Exception e) {
                        }
                    } else {
                        try {
                            String sql1 = "DELETE FROM SD WHERE SA='" + SA + "'";
                            SQLConnection.SqlConnection.updateDB(sql1);

                        } catch (Exception e) {
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "කරුණාකර නිවැරදි දත්ත පමණක් ඇතුළු කරන්න..!", "දත්ත නිවැරදි නොවේ", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {

        }
    }

    private boolean isDaruwanDataReady() {
        return UserInputChecker.check(titleDaruwanSitinam, tblDaruwan);
    }

    private void deleteTableRow(JTable tbl) {
        if (tbl.getSelectedRow() != -1) {
            DefaultTableModel tmAA = (DefaultTableModel) tbl.getModel();
            tmAA.removeRow(tbl.getSelectedRow());
        } else {
            JOptionPane.showMessageDialog(this, "කරුණාකර ඉවත්කිරීමට අවශ්‍ය row එක තෝරන්න ", "තේරීම නිවැරදි නොවේ", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void deleteAllTableRow(JTable tbl) {
        if (0 == JOptionPane.showConfirmDialog(this, "වගුවෙහි සියලු දත්ත ඉවත් කල යුතුද ?", "සියල්ල ඉවත් කරන්න", JOptionPane.YES_NO_OPTION)) {
            DefaultTableModel tmAA = (DefaultTableModel) tbl.getModel();
            tmAA.setRowCount(0);
        }
    }

    //Get boolean value from ChoiceBoxes--->if-ඔව්(->Tõ/Malithi)-true- or නැත-false 
    private boolean getBooleanValue(String value) {
        return value.equals("Tõ");
    }

    private void updateThrasthaPraharaya() {
        if (tblSeachedData.getSelectedRow() != -1) {
            String SA = lblSA.getText();
            try {

                String ISHP = Integer.toString(Integer.parseInt(cmbIpaimaSHPrathishathaya.getSelectedItem().toString().replace('%', '0')) / 10);
                String ASB = cmbSharirikaHani.getSelectedItem().toString().equals("fjk;a")
                        ? txtWenathHani.getText()
                        : cmbSharirikaHani.getSelectedItem().toString();

                String sql1 = "UPDATE TRPWLS SET "
                        + "ISHP='" + ISHP + "',"
                        + "OWAD='" + cmbOthpalada.getSelectedItem().toString() + "',"
                        + "LUD='" + dateToString(dtpPraharayaSiduwuDinaya.getSelectedDate().getTime()) + "',"
                        + "LUK='" + txtPraharayaSiduwuKottasaya.getText() + "',"
                        + "ASB='" + ASB + "',"
                        + "LUS='" + txtPraharayaSiduwuSthanaya.getText() + "',"
                        + "TRK='" + (cmbThrasthaKandayama.getSelectedIndex() + 1) + "'"
                        + "WHERE SA='" + SA + "'";

                SQLConnection.SqlConnection.updateDB(sql1);
                if (getBooleanValue(cmbAthuruAAdda1.getSelectedItem().toString())) {
                    String sql2 = "DELETE FROM AASS WHERE SA='" + SA + "'";
                    SQLConnection.SqlConnection.updateDB(sql2);

                    for (int i = 0; i < tblAthuruAbada.getRowCount(); i++) {
                        String ABD = tblAthuruAbada.getValueAt(i, 0).toString();
                        new SaveAthuruAbada(SA, ABD).save();
                    }
                } else {
                    String sql2 = "DELETE FROM AASS WHERE SA='" + SA + "'";
                    SQLConnection.SqlConnection.updateDB(sql2);
                }

                String sql3 = "UPDATE SJWP SET "
                        + "SKS='" + txtWidyaPPawathwuSthanaya.getText() + "',"
                        + "SKK='" + txtWidyaPPawathwuKo.getText() + "',"
                        + "SKD='" + dateToString(dtpWidyaPPawathwuDinaya.getSelectedDate().getTime()) + "'"
                        + "WHERE SA='" + SA + "'";

                SQLConnection.SqlConnection.updateDB(sql3);

            } catch (Exception e) {
               new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
            }
        }
    }

    private void updateSamajikaya() {

        if (tblSeachedData.getSelectedRow() != -1) {
            String SA = lblSA.getText();
            String sql1 = "UPDATE SMJK SET "
                    + "JHA ='" + txtJathikaHadunumAnkaya.getText() + "',"
                    + "SN ='" + txtSampurnaNama.getText() + "',"
                    + "PBD ='" + dateToString(dtpPolisiyataBadunaDinaya.getSelectedDate().getTime()) + "',"
                    + "UD ='" + dateToString(dtpDOB.getSelectedDate().getTime()) + "',"
                    + "WL ='" + txtEmail.getText() + "',"
                    + "WLD ='" + dateToString(dtpWishramaLabuDinaya.getSelectedDate().getTime()) + "',"
                    + "JDA ='" + txtJangamaDurakathanaya.getText() + "',"
                    + "SDA ='" + txtSthawaraDurakathanaya.getText() + "',"
                    + "NSUS ='" + cmbPolisiyataBahune.getSelectedItem().toString() + "',"
                    + "SLD ='" + dateToString(dtpSamajikathwayaLDinaya.getSelectedDate().getTime()) + "',"
                    + "AWSKS ='" + txtAwasanWarataSKSthanaya.getText() + "',"
                    + "AWSKK ='" + txtAwasanWarataSKKottasaya.getText() + "'"
                    + "WHERE SA='" + SA + "'";
            String sql2 = "UPDATE SL SET "
                    + "SL ='" + txtSthiraLipinaya.getText() + "',"
                    + "GNW ='" + txtGramaNWasama.getText() + "',"
                    + "PW ='" + txtPolisWasamaSL.getText() + "',"
                    + "PLK ='" + txtPradeshiyaLekamKottasaya.getText() + "',"
                    + "DTRK ='" + txtDistrikkaya.getText() + "'"
                    + "WHERE SA='" + SA + "'";
            try {
                SQLConnection.SqlConnection.updateDB(sql1);
                SQLConnection.SqlConnection.updateDB(sql2);
            } catch (Exception e) {
               new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "කරුණාකර යාවත්කාලීන කල යුතු සමාජිකය තෝරන්න.", "සමාජිකය තෝරන්න.", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private boolean isSamajikaDataRedyToUpdate() {

        //CHACK IS EMTY
        boolean C01 = UserInputChecker.check(txtSampurnaNama);
        boolean C02 = UserInputChecker.check(txtSthiraLipinaya);
        boolean C03 = UserInputChecker.check(txtJathikaHadunumAnkaya);
        boolean C04 = UserInputChecker.check(txtAwasanWarataSKSthanaya);
        boolean C05 = UserInputChecker.check(txtAwasanWarataSKKottasaya);
        boolean C06 = UserInputChecker.check(txtPolisWasamaSL);
        boolean C07 = UserInputChecker.check(txtPradeshiyaLekamKottasaya);
        boolean C08 = UserInputChecker.check(txtGramaNWasama);
        boolean C09 = UserInputChecker.check(txtDistrikkaya);
        boolean C10 = UserInputChecker.check(txtSthawaraDurakathanaya);
        boolean C11 = UserInputChecker.check(txtJangamaDurakathanaya);
        boolean C12 = UserInputChecker.check(txtEmail);

        return C01 && C02 && C03 && C04 && C05 && C06
                && C07 && C08 && C09 && C10 && C11 && C12;

    }

    private boolean isThrasthaDataIsRedyToUpdate() {

        boolean C01 = UserInputChecker.check(txtPraharayaSiduwuKottasaya);
        boolean C02 = UserInputChecker.check(txtPraharayaSiduwuSthanaya);
        boolean C03 = UserInputChecker.check(txtWidyaPPawathwuKo);
        boolean C04 = UserInputChecker.check(txtWidyaPPawathwuSthanaya);

        boolean C05 = UserInputChecker.check(titleAAbada, tblAthuruAbada);

        System.out.println(C01 + ", " + C02 + ", " + C03 + ", " + C04 + ", " + C05);
        return C01 && C02 && C03 && C04 && C05;
    }

    private String dateToString(Date d) {
        return new SimpleDateFormat("yyyy-MM-dd").format(d);
    }

    private void seachAll() {
        switch (cmbSeachBy.getSelectedItem().toString()) {
            case "idudðl wxlh":
                set_stype_font(FONT_MALITHI);
                System.out.println("01");
                seachDateBySA();
                break;
            case "cd;sl yeÿkqïm;a wxlh"://
                set_stype_font(FONT_ISKOLAPOTHA);
                seachDateByJHA();
                break;
            case "iïmQ¾K ku":
                set_stype_font(FONT_MALITHI);
                seachDateBySN();
                break;
            case "úoahq;a ,smskh"://
                set_stype_font(FONT_ISKOLAPOTHA);
                seachDateByWL();
                break;
            case "cx.u ÿrl:k wxlh":
                set_stype_font(FONT_MALITHI);
                seachDateByJDA();
                break;
            case "ia:djr ÿrl:k wxlh":
                set_stype_font(FONT_MALITHI);
                seachDateBySDA();
                break;
            case "ks, wxlh":
                set_stype_font(FONT_MALITHI);
                seachDateByNA();
                break;
            case "Wmfijd wxlh":
                set_stype_font(FONT_MALITHI);
                seachDateByUSA();
                break;
            case "rKúre yeÿkqïm;a wxlh":
                set_stype_font(FONT_MALITHI);
                seachDateByRHA();
                break;
        }
    }

    private void set_stype_font(String font) {
        txtSeachField.setFont(new java.awt.Font(font, 0, 14));
    }

    private void changeTableColomnNames() {
        DefaultTableModel dModel = (DefaultTableModel) tblSeachedData.getModel();
        Vector v = new Vector();
        int s = cmbSeachBy.getSelectedIndex();
        v.add(s == 0 ? "සමාජික අංකය"
                : s == 1 ? "ජාතික හැදුනුම්පත් අංකය  "
                        : s == 2 ? "සම්පුර්ණ නම"
                                : s == 3 ? "විද්‍යුත් ලිපිනය  "
                                        : s == 4 ? "ජංගම දුරකථන අංකය "
                                                : s == 5 ? "ස්ථාවර දුරකථන අංකය "
                                                        : s == 6 ? "නිල අංකය "
                                                                : s == 7 ? "උපසේවා අංකය "
                                                                        : "රණවිරු හැදුනුම්පත් අංකය");

        v.add("සාමාජික අංකය");
        v.add("නම");

        dModel.setColumnIdentifiers(v);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel abadithaUpakarana;
    private javax.swing.JButton btnAddAU;
    private javax.swing.JButton btnAddAULDMitaPasu;
    private javax.swing.JButton btnAddAULDMitaPera;
    private javax.swing.JButton btnAddAthuruAbada;
    private javax.swing.JButton btnAddDWWNH;
    private javax.swing.JButton btnAddDaruwa;
    private javax.swing.JButton btnAddSahana;
    private javax.swing.JButton btnAddWWNH;
    private javax.swing.JButton btnDAllAthuruAbada;
    private javax.swing.JButton btnDAllDWWNH;
    private javax.swing.JButton btnDAllDaruwa;
    private javax.swing.JButton btnDAllSahana;
    private javax.swing.JButton btnDAllWWNH;
    private javax.swing.JButton btnDeleteAU;
    private javax.swing.JButton btnDeleteAULDMitaPasu;
    private javax.swing.JButton btnDeleteAULDMitaPera;
    private javax.swing.JButton btnDeleteAllData;
    private javax.swing.JButton btnDeleteAthuruAbada;
    private javax.swing.JButton btnDeleteDWWNH;
    private javax.swing.JButton btnDeleteDaruwa;
    private javax.swing.JButton btnDeleteSahana;
    private javax.swing.JButton btnDeleteWWNH;
    private javax.swing.JButton btnRefrashAU;
    private javax.swing.JButton btnRefrashDaruwan;
    private javax.swing.JButton btnRefrashDaruwan1;
    private javax.swing.JButton btnRefrashMasikaWatup;
    private javax.swing.JButton btnRefrashSahana;
    private javax.swing.JButton btnRefrashTrasthaP;
    private javax.swing.JButton btnRefrashWisramaWatup;
    private javax.swing.JButton btnRefrashWiwahaya;
    private javax.swing.JButton btnUpdateAU;
    private javax.swing.JButton btnUpdateDaruwan;
    private javax.swing.JButton btnUpdateDaruwan1;
    private javax.swing.JButton btnUpdateMasikaWatup;
    private javax.swing.JButton btnUpdateSahana;
    private javax.swing.JButton btnUpdateSamajikaya;
    private javax.swing.JButton btnUpdateTblDaruwan;
    private javax.swing.JButton btnUpdateTrasthaP;
    private javax.swing.JButton btnUpdateWisramaWatup;
    private javax.swing.JButton btnUpdateWiwahaya;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cbxAbadithaUBKarai;
    private javax.swing.JCheckBox cbxDaruwanSiti;
    private javax.swing.JCheckBox cbxSahanaLabiAtha;
    private javax.swing.JCheckBox cbxWiwahakada;
    private javax.swing.JComboBox cmbAbadithaUpakarana;
    private javax.swing.JComboBox cmbAthuruAAdda1;
    private javax.swing.JComboBox cmbDWWLabanneda;
    private javax.swing.JComboBox cmbDWWNHethuwa;
    private javax.swing.JComboBox cmbDaruwaAbadithada;
    private javax.swing.JComboBox cmbDaruwaAbadithadaEdit;
    private javax.swing.JComboBox cmbDaruwageDanataThathwaya;
    private javax.swing.JComboBox cmbDaruwageDanataThathwayaEdit;
    private javax.swing.JComboBox cmbDikkasadada;
    private javax.swing.JComboBox cmbIpaimaSHPrathishathaya;
    private javax.swing.JComboBox cmbMasukaWLabanneda;
    private javax.swing.JComboBox cmbNawathaWiwahaUweda;
    private javax.swing.JComboBox cmbOthpalada;
    private javax.swing.JComboBox cmbPBadenawitaThanathura;
    private javax.swing.JComboBox cmbPolisiyataBahune;
    private javax.swing.JComboBox cmbSeachBy;
    private javax.swing.JComboBox cmbSharirikaHani;
    private javax.swing.JComboBox cmbThrasthaKandayama;
    private javax.swing.JComboBox cmbWEAnuyuththada;
    private javax.swing.JComboBox cmbWWNHethuwa;
    private javax.swing.JComboBox cmbWisheshaEkakaya;
    private javax.swing.JComboBox cmbWishramaYWThanathura;
    private javax.swing.JComboBox cmbWisramaWLabanneda;
    private javax.swing.JComboBox cmbWiwahayaAsarthakada;
    private javax.swing.JPanel daruwan;
    private datechooser.beans.DateChooserCombo dtpAULDinayanMitaPasu;
    private datechooser.beans.DateChooserCombo dtpAULDinayanMitaPera;
    private datechooser.beans.DateChooserCombo dtpDOB;
    private datechooser.beans.DateChooserCombo dtpDaruwageDOB;
    private datechooser.beans.DateChooserCombo dtpDaruwageDOBEdit;
    private datechooser.beans.DateChooserCombo dtpPolisiyataBadunaDinaya;
    private datechooser.beans.DateChooserCombo dtpPraharayaSiduwuDinaya;
    private datechooser.beans.DateChooserCombo dtpSahanaAWLD;
    private datechooser.beans.DateChooserCombo dtpSamajikathwayaLDinaya;
    private datechooser.beans.DateChooserCombo dtpWEBadunaDinaya;
    private datechooser.beans.DateChooserCombo dtpWidyaPPawathwuDinaya;
    private datechooser.beans.DateChooserCombo dtpWishramaLabuDinaya;
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
    private javax.swing.JLabel jLabel74;
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
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblDWWWorn;
    private javax.swing.JLabel lblDina;
    private javax.swing.JLabel lblDinaW;
    private javax.swing.JLabel lblMWWInfor;
    private javax.swing.JLabel lblMasa;
    private javax.swing.JLabel lblMasaW;
    private javax.swing.JLabel lblMuluWatupeWatinakama;
    private javax.swing.JLabel lblSA;
    private javax.swing.JLabel lblWWWorn;
    private javax.swing.JLabel lblWasara;
    private javax.swing.JLabel lblWasaraW;
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
    private javax.swing.JPanel titleWWNolabenam;
    private javax.swing.JPanel titleWishramaWLanannenam;
    private javax.swing.JPanel titleWiwahakanam;
    private javax.swing.JPanel trasthaPraharaya;
    private javax.swing.JTextField txtAnuKanda;
    private javax.swing.JTextField txtAthuruAbadaya;
    private javax.swing.JTextField txtAwasanWarataSKKottasaya;
    private javax.swing.JTextField txtAwasanWarataSKSthanaya;
    private javax.swing.JTextField txtDWWWatinakama;
    private javax.swing.JTextField txtDaruwageNama;
    private javax.swing.JTextField txtDaruwageNamaEdit;
    private javax.swing.JTextField txtDimanaWalaWatinakama;
    private javax.swing.JTextField txtDistrikkaya;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGramaNWasama;
    private javax.swing.JTextArea txtIwathKirimataHetuwa;
    private javax.swing.JTextField txtJangamaDurakathanaya;
    private javax.swing.JTextField txtJathikaHadunumAnkaya;
    private javax.swing.JTextField txtMulikaWatupeWatinakama;
    private javax.swing.JTextField txtNilaAnkaya;
    private javax.swing.JTextField txtPolisWasamaSL;
    private javax.swing.JTextField txtPradeshiyaLekamKottasaya;
    private javax.swing.JTextField txtPraharayaSiduwuKottasaya;
    private javax.swing.JTextField txtPraharayaSiduwuSthanaya;
    private javax.swing.JTextField txtRanawiruHadunumAnkaya;
    private javax.swing.JButton txtRefrashSamajikaya;
    private javax.swing.JTextField txtSahakarugeDKA;
    private javax.swing.JTextField txtSahakarugeNama;
    private javax.swing.JTextArea txtSahakarugeRLipinaya;
    private javax.swing.JTextField txtSahakarugeRakiyawa;
    private javax.swing.JTextField txtSahanaLabanaSthanaya;
    private javax.swing.JTextField txtSahanaya;
    private javax.swing.JTextField txtSampurnaNama;
    private javax.swing.JTextField txtSeachField;
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
    private javax.swing.JPanel wisramaWatup;
    private javax.swing.JPanel wiwahaya;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}
