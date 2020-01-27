package MainGUIClases;

import OtherClases.Exporter;
import java.awt.Component;
import java.io.File;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class ExporterWindow extends javax.swing.JDialog {

    private final String FONT_MALITHI = "FMMalithi";
    private final String FONT_ISKOLAPOTHA = "Iskoola Pota";

    public ExporterWindow(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        changeTableColomnNames();
        seachAll();
        changeSeachTabelCWidth();
        setSTableColumnFont(tblExportList, "Iskoola Pota", 1);
    }

    private void setSTableColumnFont(JTable table, String font_name, int... indexes) {
        for (int index : indexes) {
            table.getColumnModel().getColumn(index).setCellRenderer(new DefaultTableCellRenderer() {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSeachedData = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblExportList = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        btnRemoveAll = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtSeachField = new javax.swing.JTextField();
        cmbSeachBy = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("දත්ත පිටතට ගෙනයන්න ");
        setResizable(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 51), 2, true), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FMMalithi", 0, 16))); // NOI18N

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

        tblExportList.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        tblExportList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "සාමාජික අංකය ", "ජා හැ අංකය ", "නම "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblExportList.getTableHeader().setReorderingAllowed(false);
        tblExportList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblExportListMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblExportList);
        if (tblExportList.getColumnModel().getColumnCount() > 0) {
            tblExportList.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblExportList.getColumnModel().getColumn(1).setPreferredWidth(103);
            tblExportList.getColumnModel().getColumn(2).setPreferredWidth(383);
        }

        btnAdd.setBackground(new java.awt.Color(204, 204, 204));
        btnAdd.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        btnAdd.setText("tl;= lrkak ");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnExport.setBackground(new java.awt.Color(204, 204, 204));
        btnExport.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        btnExport.setText("msg;g f.khkak ");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        btnRemoveAll.setBackground(new java.awt.Color(204, 204, 204));
        btnRemoveAll.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        btnRemoveAll.setText("ish,a, bj;a lrkak ");
        btnRemoveAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveAllActionPerformed(evt);
            }
        });

        btnRemove.setBackground(new java.awt.Color(204, 204, 204));
        btnRemove.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        btnRemove.setText("bj;a lrkak ");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoveAll)
                        .addGap(22, 22, 22)
                        .addComponent(btnExport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdd)
                        .addComponent(btnRemove)
                        .addComponent(btnRemoveAll))
                    .addComponent(btnExport, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 51), 2, true), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FMMalithi", 0, 16))); // NOI18N

        txtSeachField.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        txtSeachField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSeachFieldKeyReleased(evt);
            }
        });

        cmbSeachBy.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        cmbSeachBy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "idudðl wxlh ", "cd;sl yeÿkqïm;a wxlh ", "iïmQ¾K ku ", "úoahq;a ,smskh ", "cx.u ÿrl:k wxlh ", "ia:djr ÿrl:k wxlh", "ks, wxlh ", "Wmfijd wxlh ", "rKúre yeÿkqïm;a wxlh" }));
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
                .addComponent(txtSeachField, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbSeachBy, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSeachField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSeachBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblSeachedDataMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSeachedDataMouseReleased


    }//GEN-LAST:event_tblSeachedDataMouseReleased

    private void txtSeachFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSeachFieldKeyReleased

        seachAll();

    }//GEN-LAST:event_txtSeachFieldKeyReleased

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


    private void tblExportListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblExportListMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblExportListMouseReleased

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        try {
            DefaultTableModel el = (DefaultTableModel) tblExportList.getModel();
            if (tblSeachedData.getSelectedRow() != -1) {
                String SA = tblSeachedData.getValueAt(tblSeachedData.getSelectedRow(), 1).toString();
                ResultSet rs = SQLConnection.SqlConnection.getData("SELECT SN,JHA FROM SMJK WHERE SA='" + SA + "'");
                if (rs.first()) {
                    String SN = rs.getString("SN");
                    String JHA = rs.getString("JHA");
                    Vector row_data = new Vector();
                    row_data.add(SA);
                    row_data.add(JHA);
                    row_data.add(SN);
                    if (!isAvailable(SA)) {
                        el.addRow(row_data);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed

        int[] srs = tblExportList.getSelectedRows();
        DefaultTableModel el = (DefaultTableModel) tblExportList.getModel();

        if (srs.length != 0) {
            for (int sr : srs) {
                el.removeRow(sr);
            }
        }

    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnRemoveAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveAllActionPerformed

        DefaultTableModel el = (DefaultTableModel) tblExportList.getModel();
        el.setRowCount(0);

    }//GEN-LAST:event_btnRemoveAllActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        if (tblExportList.getRowCount() != 0) {
            if (JOptionPane.showConfirmDialog(this, "මෙම සාමාජිකයන් පද්ධතියෙන් ඉවතට ගෙනයාමට  අවශ්‍යද ?", "තොරතුරු ඉවතට", JOptionPane.YES_NO_OPTION) == 0) {
                JFileChooser fc = new JFileChooser();
                fc.setDialogType(JFileChooser.OPEN_DIALOG);
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fc.showDialog(this, "Export");
                File folder = fc.getSelectedFile();
                if (folder != null) {
                    try {
                        String filepath = folder.getPath() + "\\ExportedData_" + new SimpleDateFormat("yyyy_MMM_dd_hh_mm_ss_a").format(new Date()) + ".xml";
                        System.out.println(filepath);
                        File xmlFile = new File(filepath);
                        new Exporter(getIDList()).Export(xmlFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                DefaultTableModel tm = (DefaultTableModel) tblExportList.getModel();
                tm.setRowCount(0);
            }

        }


    }//GEN-LAST:event_btnExportActionPerformed

    private ArrayList<String> getIDList() {
        ArrayList<String> id_l = new ArrayList<>();
        for (int i = 0; i < tblExportList.getRowCount(); i++) {
            id_l.add(tblExportList.getValueAt(i, 0).toString());
        }
        return id_l;
    }

    private boolean isAvailable(String SA) {
        boolean a = false;
        for (int i = 0; i < tblExportList.getRowCount(); i++) {
            if (tblExportList.getValueAt(i, 0).equals(SA)) {
                a = true;
                break;
            }
        }
        return a;
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

    private void setSeachTableCWidht(int... w) {
        TableColumnModel cm = tblSeachedData.getColumnModel();
        cm.getColumn(0).setPreferredWidth(w[0]);
        cm.getColumn(1).setPreferredWidth(w[1]);
        cm.getColumn(2).setPreferredWidth(w[2]);

    }

    private void set_stype_font(String font) {
        txtSeachField.setFont(new java.awt.Font(font, 0, 14));
    }

    private void seachAll() {
        switch (cmbSeachBy.getSelectedItem().toString()) {
            case "idudðl wxlh ":
                set_stype_font(FONT_MALITHI);
                seachDateBySA();
                break;
            case "cd;sl yeÿkqïm;a wxlh "://
                set_stype_font(FONT_ISKOLAPOTHA);
                seachDateByJHA();
                break;
            case "iïmQ¾K ku ":
                set_stype_font(FONT_MALITHI);
                seachDateBySN();
                break;
            case "úoahq;a ,smskh "://
                set_stype_font(FONT_ISKOLAPOTHA);
                seachDateByWL();
                break;
            case "cx.u ÿrl:k wxlh ":
                set_stype_font(FONT_MALITHI);
                seachDateByJDA();
                break;
            case "ia:djr ÿrl:k wxlh":
                set_stype_font(FONT_MALITHI);
                seachDateBySDA();
                break;
            case "ks, wxlh ":
                set_stype_font(FONT_MALITHI);
                seachDateByNA();
                break;
            case "Wmfijd wxlh ":
                set_stype_font(FONT_MALITHI);
                seachDateByUSA();
                break;
            case "rKúre yeÿkqïm;a wxlh":
                set_stype_font(FONT_MALITHI);
                seachDateByRHA();
                break;
        }
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
            System.out.println(e.getMessage());

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
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());

        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnRemoveAll;
    private javax.swing.JComboBox cmbSeachBy;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblExportList;
    private javax.swing.JTable tblSeachedData;
    private javax.swing.JTextField txtSeachField;
    // End of variables declaration//GEN-END:variables
}
