package MainGUIClases;

import OtherClases.Importer;
import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ImporterWindow extends javax.swing.JDialog {

    public ImporterWindow(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSTableColumnFont(tblXmlList, "Iskoola Pota", 1);
        setSTableColumnFont(tblXmlAvailableList, "Iskoola Pota", 1);
        setSTableColumnFont(tblChoosedList, "Iskoola Pota", 1);

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

        btnOpenExportedFile = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblXmlList = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChoosedList = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblXmlAvailableList = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        lblFileName = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnAddToDatabase = new javax.swing.JButton();
        btnRemoveAll = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        btnOpenExportedFile.setFont(new java.awt.Font("FMMalithi", 0, 18)); // NOI18N
        btnOpenExportedFile.setText("msg;g f.k.sh f.dkqj fidhdfokak ");
        btnOpenExportedFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenExportedFileActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("FMMalithi", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 204));
        jLabel1.setText("f.dkqfjys kduh ");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblXmlList.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        tblXmlList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "සාමාජික අංකය ", "ජා හැ අංකය ", "නම "
            }
        ));
        jScrollPane1.setViewportView(tblXmlList);

        jLabel2.setFont(new java.awt.Font("FMMalithi", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 0, 0));
        jLabel2.setText("f.dkqfjys wka;¾.; iuðlhkaf.a ,ehqia;=j ");

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblChoosedList.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        tblChoosedList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "සාමාජික අංකය ", "ජා හැ අංකය ", "නම "
            }
        ));
        jScrollPane2.setViewportView(tblChoosedList);

        jLabel3.setFont(new java.awt.Font("FMMalithi", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 51, 0));
        jLabel3.setText("moaO;shg we;=,;a l,hq;= iuðlhkaf.a ,ehqia;=j ");

        jScrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblXmlAvailableList.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        tblXmlAvailableList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "සාමාජික අංකය ", "ජා හැ අංකය ", "නම "
            }
        ));
        jScrollPane3.setViewportView(tblXmlAvailableList);

        jLabel4.setFont(new java.awt.Font("FMMalithi", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 0, 0));
        jLabel4.setText("f.dkqfjys wka;¾.; kuq;a fufu moaO;sh ;=, oekgu;a we;s iduðlhskaf.a ,ehqia;=j  ");

        lblFileName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblFileName.setForeground(new java.awt.Color(0, 102, 204));
        lblFileName.setText("file name");

        btnAddToDatabase.setBackground(new java.awt.Color(204, 204, 204));
        btnAddToDatabase.setFont(new java.awt.Font("FMMalithi", 0, 18)); // NOI18N
        btnAddToDatabase.setText("we;=¨ lrkak ");
        btnAddToDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToDatabaseActionPerformed(evt);
            }
        });

        btnRemoveAll.setBackground(new java.awt.Color(204, 204, 204));
        btnRemoveAll.setFont(new java.awt.Font("FMMalithi", 0, 18)); // NOI18N
        btnRemoveAll.setText("ish,a, bj;a lrkak ");
        btnRemoveAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveAllActionPerformed(evt);
            }
        });

        btnRemove.setBackground(new java.awt.Color(204, 204, 204));
        btnRemove.setFont(new java.awt.Font("FMMalithi", 0, 18)); // NOI18N
        btnRemove.setText("bj;a lrkak ");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("FMMalithi", 0, 18)); // NOI18N
        btnAdd.setText("tl;= lrkak ");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFileName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparator1)
                        .addComponent(btnOpenExportedFile)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane3)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnAdd)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnRemove)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnRemoveAll)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnAddToDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel3))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnOpenExportedFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblFileName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddToDatabase)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnRemoveAll, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRemove, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.LEADING)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed

        if (tblChoosedList.getSelectedRow() != -1) {
            DefaultTableModel model = (DefaultTableModel) tblChoosedList.getModel();
            model.removeRow(tblChoosedList.getSelectedRow());
        }

    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnRemoveAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveAllActionPerformed

        DefaultTableModel model = (DefaultTableModel) tblChoosedList.getModel();
        model.setRowCount(0);

    }//GEN-LAST:event_btnRemoveAllActionPerformed

    private void btnAddToDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToDatabaseActionPerformed
        if (tblChoosedList.getRowCount() != 0) {

            if (JOptionPane.showConfirmDialog(this, "මෙම සාමාජිකයන් පද්ධතියට ඇතුලත් කිරීමට අවශ්‍යද ?", "ඇතුලත් කරන්න", JOptionPane.YES_NO_OPTION) == 0) {

                if (!(importer == null || !importer.validateXML())) {

                    try {
                        importer.saveMember(getMemberList());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    DefaultTableModel m_xml = (DefaultTableModel) tblXmlList.getModel();
                    DefaultTableModel m_xmlAv = (DefaultTableModel) tblXmlAvailableList.getModel();
                    DefaultTableModel model = (DefaultTableModel) tblChoosedList.getModel();

                    model.setRowCount(0);
                    m_xml.setRowCount(0);
                    m_xmlAv.setRowCount(0);

                    ArrayList<Importer.Details> md = importer.getMembersDetails();
                    md.stream().map((md1) -> {
                        Vector row_data = new Vector();
                        row_data.add(md1.getSa());
                        row_data.add(md1.getJha());
                        row_data.add(md1.getSn());
                        return row_data;
                    }).forEach((row_data) -> {
                        try {
                            if (SQLConnection.SqlConnection.getData("SELECT SA FROM SMJK WHERE SA='" + row_data.get(0) + "'").first()) {
                                m_xmlAv.addRow(row_data);
                            } else {
                                m_xml.addRow(row_data);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    });

                }
            }
        } else {

        }

    }//GEN-LAST:event_btnAddToDatabaseActionPerformed

    private String[] getMemberList() {
        String s[] = new String[tblChoosedList.getRowCount()];
        for (int i = 0; i < tblChoosedList.getRowCount(); i++) {
            s[i] = tblChoosedList.getValueAt(i, 0).toString();
        }
        return s;
    }

    Importer importer;

    private void btnOpenExportedFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenExportedFileActionPerformed

        JFileChooser Open_file = new JFileChooser();

        Open_file.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(File f) {
                String name = f.getName();
                boolean c1 = name.contains(".xml");
                boolean c2 = !name.contains(".");
                return c1 || c2;
            }

            @Override
            public String getDescription() {
                return "Open only xml file";
            }
        });

        Open_file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        Open_file.setDialogTitle("Open Exported File");
        Open_file.showOpenDialog(this);
        File sf = Open_file.getSelectedFile();
        if (sf != null && !sf.isDirectory()) {
            lblFileName.setText(sf.getName());
            try {
                DefaultTableModel m_xml = (DefaultTableModel) tblXmlList.getModel();
                DefaultTableModel m_xmlAv = (DefaultTableModel) tblXmlAvailableList.getModel();
                m_xml.setRowCount(0);
                m_xmlAv.setRowCount(0);
                importer = new OtherClases.Importer(sf);
                if (importer.validateXML()) {
                    ArrayList<Importer.Details> md = importer.getMembersDetails();
                    md.stream().map((md1) -> {
                        Vector row_data = new Vector();
                        row_data.add(md1.getSa());
                        row_data.add(md1.getJha());
                        row_data.add(md1.getSn());
                        return row_data;
                    }).forEach((row_data) -> {
                        try {
                            if (SQLConnection.SqlConnection.getData("SELECT SA FROM SMJK WHERE SA='" + row_data.get(0) + "'").first()) {
                                m_xmlAv.addRow(row_data);
                            } else {
                                m_xml.addRow(row_data);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    });
                } else {
                    lblFileName.setText("Not supported this file");
                }
            } catch (Exception e) {
                lblFileName.setText("Not supported this file");
            }
        } else {
            lblFileName.setText("Not supported this file");
        }


    }//GEN-LAST:event_btnOpenExportedFileActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        if (tblXmlList.getSelectedRow() != -1) {
            DefaultTableModel model = (DefaultTableModel) tblChoosedList.getModel();
            Vector row_date = new Vector();
            for (int i = 0; i < tblXmlList.getColumnCount(); i++) {
                row_date.add(tblXmlList.getValueAt(tblXmlList.getSelectedRow(), i).toString());
            }
            model.addRow(row_date);
        }

    }//GEN-LAST:event_btnAddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddToDatabase;
    private javax.swing.JButton btnOpenExportedFile;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnRemoveAll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblFileName;
    private javax.swing.JTable tblChoosedList;
    private javax.swing.JTable tblXmlAvailableList;
    private javax.swing.JTable tblXmlList;
    // End of variables declaration//GEN-END:variables
}
