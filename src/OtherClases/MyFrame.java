package OtherClases;

import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import SQLConnection.SqlConnection;

public class MyFrame extends javax.swing.JFrame {

    public MyFrame() {
        this.setResizable(false);
        initComponents();
        try {
            fillAll();
            cmbDProvince.setEditable(true);
            AutoCompleteDecorator.decorate(cmbDProvince);
        } catch (Exception e) {
            new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblResult = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtDivision = new javax.swing.JTextField();
        cmbSDivision = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        cmbDProvince = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtProvince = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnAddStation = new javax.swing.JButton();
        cmbSProvince = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        txtStation = new javax.swing.JTextField();
        btnAddProvince = new javax.swing.JButton();
        btnAddDivision = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProvince = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDivision = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblStation = new javax.swing.JTable();
        txtUpdateProvince = new javax.swing.JTextField();
        txtUpdateStation = new javax.swing.JTextField();
        txtUpdateDivision = new javax.swing.JTextField();
        btnUpdateProvince = new javax.swing.JButton();
        btnUpdateDivision = new javax.swing.JButton();
        btnUpdateStation = new javax.swing.JButton();
        btnRemoveProvince = new javax.swing.JButton();
        btnRemoveDivision = new javax.swing.JButton();
        btnRemoveStation = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("Station");

        txtDivision.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

        cmbSDivision.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        cmbSDivision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSDivisionActionPerformed(evt);
            }
        });

        jLabel5.setText("Division");

        cmbDProvince.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        cmbDProvince.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDProvinceActionPerformed(evt);
            }
        });

        jLabel3.setText("Province");

        jLabel1.setText("Province");

        txtProvince.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

        jLabel2.setText("Division");

        jLabel4.setText("Province");

        btnAddStation.setText("Add");
        btnAddStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStationActionPerformed(evt);
            }
        });

        cmbSProvince.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        cmbSProvince.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSProvinceActionPerformed(evt);
            }
        });

        txtStation.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

        btnAddProvince.setText("Add");
        btnAddProvince.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProvinceActionPerformed(evt);
            }
        });

        btnAddDivision.setText("Add");
        btnAddDivision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDivisionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparator1)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel1))
                            .addGap(46, 46, 46)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtProvince)
                                .addComponent(txtDivision)
                                .addComponent(cmbDProvince, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnAddDivision)
                                .addComponent(btnAddProvince))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtStation, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbSDivision, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbSProvince, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAddStation))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtProvince, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddProvince))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbDProvince, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDivision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddDivision))
                .addGap(11, 11, 11)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSProvince, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSDivision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtStation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(btnAddStation)))
                .addContainerGap())
        );

        tblProvince.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        tblProvince.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Provinces"
            }
        ));
        tblProvince.setColumnSelectionAllowed(true);
        tblProvince.getTableHeader().setReorderingAllowed(false);
        tblProvince.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblProvinceMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblProvinceMouseReleased(evt);
            }
        });
        tblProvince.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tblProvincePropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(tblProvince);
        tblProvince.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        tblDivision.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        tblDivision.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Divisions"
            }
        ));
        tblDivision.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblDivisionMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblDivision);

        tblStation.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        tblStation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stations"
            }
        ));
        tblStation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblStationMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tblStation);

        txtUpdateProvince.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

        txtUpdateStation.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

        txtUpdateDivision.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N

        btnUpdateProvince.setText("Update");
        btnUpdateProvince.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateProvinceActionPerformed(evt);
            }
        });

        btnUpdateDivision.setText("Update");
        btnUpdateDivision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateDivisionActionPerformed(evt);
            }
        });

        btnUpdateStation.setText("Update");
        btnUpdateStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateStationActionPerformed(evt);
            }
        });

        btnRemoveProvince.setText("Remove");
        btnRemoveProvince.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveProvinceActionPerformed(evt);
            }
        });

        btnRemoveDivision.setText("Remove");

        btnRemoveStation.setText("Remove");
        btnRemoveStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveStationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRemoveProvince, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoveDivision, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnUpdateProvince, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdateDivision, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtUpdateProvince, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUpdateDivision))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtUpdateStation)
                    .addComponent(btnUpdateStation, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                    .addComponent(btnRemoveStation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblResult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(lblResult, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUpdateProvince, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUpdateStation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUpdateDivision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateProvince)
                    .addComponent(btnUpdateDivision)
                    .addComponent(btnUpdateStation))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRemoveProvince)
                    .addComponent(btnRemoveDivision)
                    .addComponent(btnRemoveStation))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddProvinceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProvinceActionPerformed

        try {
            String province = txtProvince.getText();
            if (!SqlConnection.getData("SELECT PROVINCE FROM PROVINCE WHERE PROVINCE='" + province + "'").first()) {
                SqlConnection.updateDB("INSERT INTO PROVINCE(PROVINCE) VALUES('" + province + "')");
                fillAll();
                txtProvince.setText("");
            } else {
                //Allready in....
            }

        } catch (Exception e) {
            new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
        }


    }//GEN-LAST:event_btnAddProvinceActionPerformed

    private void btnAddDivisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDivisionActionPerformed

        try {
            String selectedDP = cmbDProvince.getSelectedItem().toString();
            String division = txtDivision.getText();
            if (!SqlConnection.getData("SELECT DIVID FROM DIVISIONS WHERE DIVISION='" + division + "'").first()) {
                SqlConnection.updateDB("INSERT INTO DIVISIONS(DIVISION,PRID) VALUES('" + division + "','" + getProvinceID(selectedDP) + "')");
                fillDivisionsCombo();
                txtDivision.setText("");
            } else {
                //Allready in....

            }
            fillDivisionsTable(cmbDProvince.getSelectedItem().toString());
        } catch (Exception e) {
            new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
        }

    }//GEN-LAST:event_btnAddDivisionActionPerformed

    private void btnAddStationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddStationActionPerformed

        try {
            String selectedSD = cmbSDivision.getSelectedItem().toString();
            String station = txtStation.getText();
            if (!SqlConnection.getData("SELECT STID FROM STATIONS WHERE STATION='" + station + "'").first()) {
                SqlConnection.updateDB("INSERT INTO STATIONS(STATION,DIVID) VALUES('" + station + "','" + getDivisionID(selectedSD) + "')");
                txtStation.setText("");
            } else {
                //Allready in....
            }
            fillStationsTable(cmbSDivision.getSelectedItem().toString());
        } catch (Exception e) {
            new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
        }

    }//GEN-LAST:event_btnAddStationActionPerformed

    private void cmbDProvinceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDProvinceActionPerformed

        try {
            cmbSProvince.setSelectedIndex(cmbDProvince.getSelectedIndex());
            fillDivisionsCombo();
            if (cmbDProvince.getSelectedIndex() != -1) {
                fillDivisionsTable(cmbDProvince.getSelectedItem().toString());
            }
        } catch (Exception ex) {

        }

    }//GEN-LAST:event_cmbDProvinceActionPerformed

    private void cmbSProvinceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSProvinceActionPerformed

        try {
            cmbDProvince.setSelectedIndex(cmbSProvince.getSelectedIndex());
            fillDivisionsCombo();

            if (cmbSProvince.getSelectedIndex() != -1) {
                fillDivisionsTable(cmbSProvince.getSelectedItem().toString());
            }
        } catch (Exception ex) {

        }


    }//GEN-LAST:event_cmbSProvinceActionPerformed

    private void cmbSDivisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSDivisionActionPerformed

        try {
            if (cmbSDivision.getSelectedIndex() != -1) {
                fillStationsTable(cmbSDivision.getSelectedItem().toString());
            } else {
                DefaultTableModel model = (DefaultTableModel) tblStation.getModel();
                model.setRowCount(0);
            }
        } catch (Exception ex) {

        }

    }//GEN-LAST:event_cmbSDivisionActionPerformed

    private void tblProvincePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tblProvincePropertyChange


    }//GEN-LAST:event_tblProvincePropertyChange

    private void tblProvinceMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProvinceMousePressed


    }//GEN-LAST:event_tblProvinceMousePressed

    private void tblProvinceMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProvinceMouseReleased

        if (tblProvince.getSelectedRow() != -1) {
            fillDivisionsTable(tblProvince.getValueAt(tblProvince.getSelectedRow(), 0).toString());
            DefaultTableModel model = (DefaultTableModel) tblStation.getModel();
            model.setRowCount(0);
            txtUpdateProvince.setText(tblProvince.getValueAt(tblProvince.getSelectedRow(), 0).toString());
        }


    }//GEN-LAST:event_tblProvinceMouseReleased

    private void tblDivisionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDivisionMouseReleased

        if (tblDivision.getSelectedRow() != -1) {
            fillStationsTable(tblDivision.getValueAt(tblDivision.getSelectedRow(), 0).toString());
            txtUpdateDivision.setText(tblDivision.getValueAt(tblDivision.getSelectedRow(), 0).toString());

        }

    }//GEN-LAST:event_tblDivisionMouseReleased

    private void btnRemoveProvinceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveProvinceActionPerformed

        tblProvince.getSelectionModel().setSelectionInterval(0, 0);

    }//GEN-LAST:event_btnRemoveProvinceActionPerformed

    private void btnUpdateProvinceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateProvinceActionPerformed

        try {
            int sr = tblProvince.getSelectedRow();
            if (sr != -1) {
                String sp = tblProvince.getValueAt(sr, 0).toString();
                String sql = "UPDATE PROVINCE SET PROVINCE='" + txtUpdateProvince.getText() + "' WHERE PROVINCE='" + sp + "'";
                SqlConnection.updateDB(sql);
                fillProvinceTable();
            }
        } catch (Exception e) {
            new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
        }

    }//GEN-LAST:event_btnUpdateProvinceActionPerformed

    private void btnRemoveStationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveStationActionPerformed

        int se = tblStation.getSelectedRow();
        if (se != -1) {
            try {
                if (JOptionPane.showConfirmDialog(this, "Do you want to remove selected station", "REMOVE", JOptionPane.YES_NO_OPTION) == 0) {
                    String selectedOne = tblStation.getValueAt(se, 0).toString();
                    String sql = "DELETE FROM STATIONS WHERE STATION='" + selectedOne + "'";
                    SqlConnection.updateDB(sql);
                    if (cmbSDivision.getSelectedIndex() != -1) {
                        fillStationsTable(cmbSDivision.getSelectedItem().toString());
                    }
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select one of station in station table to remove", "Not Selected", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btnRemoveStationActionPerformed

    private void tblStationMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStationMouseReleased

        int sr = tblStation.getSelectedRow();
        if (sr != -1) {
            txtUpdateStation.setText(tblStation.getValueAt(sr, 0).toString());
        }

    }//GEN-LAST:event_tblStationMouseReleased

    private void btnUpdateDivisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateDivisionActionPerformed

        try {
            int sr = tblDivision.getSelectedRow();
            int sr01 = tblProvince.getSelectedRow();
            if (sr != -1 && sr01 != -1) {
                String sp = tblDivision.getValueAt(sr, 0).toString();
                String sp01 = tblProvince.getValueAt(sr01, 0).toString();
                String sql = "UPDATE DIVISIONS SET DIVISION='" + txtUpdateDivision.getText() + "' WHERE DIVISION='" + sp + "'";
                SqlConnection.updateDB(sql);
                fillDivisionsTable(sp01);
            }
        } catch (Exception e) {
            new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
        }

    }//GEN-LAST:event_btnUpdateDivisionActionPerformed

    private void btnUpdateStationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateStationActionPerformed

        try {
            int sr = tblStation.getSelectedRow();
            int sr01 = tblDivision.getSelectedRow();
            if (sr != -1 && sr01 != -1) {
                String sp = tblStation.getValueAt(sr, 0).toString();
                String sp01 = tblDivision.getValueAt(sr01, 0).toString();
                String sql = "UPDATE STATIONS SET STATION='" + txtUpdateDivision.getText() + "' WHERE STATION='" + sp + "'";
                SqlConnection.updateDB(sql);
                fillStationsTable(sp01);
            }
        } catch (Exception e) {
            new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
        }

    }//GEN-LAST:event_btnUpdateStationActionPerformed

    private String getProvinceID(String province) throws Exception {
        String s = null;
        ResultSet data = SqlConnection.getData("SELECT PROID FROM PROVINCE WHERE PROVINCE='" + province + "'");
        if (data.first()) {
            s = data.getString(1);
        }
        return s;
    }

    private String getDivisionID(String division) throws Exception {
        String s = null;
        ResultSet data = SqlConnection.getData("SELECT DIVID FROM DIVISIONS WHERE DIVISION='" + division + "'");
        if (data.first()) {
            s = data.getString(1);
        }
        return s;
    }

    private void fillAll() throws Exception {
        fillProvinceTable();
        fillProvinceCombo();
        fillDivisionsCombo();

    }

    private void fillProvinceCombo() throws Exception {
        cmbDProvince.removeAllItems();
        cmbSProvince.removeAllItems();
        ResultSet rs = SqlConnection.getData("SELECT PROVINCE FROM PROVINCE");
        while (rs.next()) {
            cmbDProvince.addItem(rs.getString(1));
            cmbSProvince.addItem(rs.getString(1));
        }
    }

    private void fillDivisionsCombo() throws Exception {
        cmbSDivision.removeAllItems();
        String selectedP = cmbSProvince.getSelectedItem().toString();
        String sql = "SELECT DIVISION FROM DIVISIONS INNER JOIN PROVINCE "
                + "ON DIVISIONS.PRID=PROVINCE.PROID WHERE PROVINCE='" + selectedP + "'";
        ResultSet rs = SqlConnection.getData(sql);
        while (rs.next()) {
            cmbSDivision.addItem(rs.getString(1));
        }
    }

    private void fillProvinceTable() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblProvince.getModel();
        model.setRowCount(0);
        try {
            ResultSet rs = SqlConnection.getData("SELECT PROVINCE FROM PROVINCE");
            while (rs.next()) {
                Vector rd = new Vector();
                rd.add(rs.getString(1));
                model.addRow(rd);
            }
        } catch (Exception e) {
            new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
        }

    }

    private void fillDivisionsTable(String province) {
        DefaultTableModel model = (DefaultTableModel) tblDivision.getModel();
        model.setRowCount(0);

        try {
            ResultSet rs = SqlConnection.getData("SELECT DIVISION FROM DIVISIONS INNER JOIN "
                    + " PROVINCE ON DIVISIONS.PRID=PROVINCE.PROID WHERE PROVINCE='" + province + "'");
            while (rs.next()) {
                Vector rd = new Vector();
                rd.add(rs.getString(1));
                model.addRow(rd);
            }
        } catch (Exception e) {
            new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
        }
    }

    private void fillStationsTable(String division) {
        DefaultTableModel model = (DefaultTableModel) tblStation.getModel();
        model.setRowCount(0);
        try {
            ResultSet rs = SqlConnection.getData("SELECT STATION FROM STATIONS INNER JOIN "
                    + " DIVISIONS ON DIVISIONS.DIVID=STATIONS.DIVID WHERE DIVISION='" + division + "'");
            while (rs.next()) {
                Vector rd = new Vector();
                rd.add(rs.getString(1));
                model.addRow(rd);
            }
        } catch (Exception e) {
            new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddDivision;
    private javax.swing.JButton btnAddProvince;
    private javax.swing.JButton btnAddStation;
    private javax.swing.JButton btnRemoveDivision;
    private javax.swing.JButton btnRemoveProvince;
    private javax.swing.JButton btnRemoveStation;
    private javax.swing.JButton btnUpdateDivision;
    private javax.swing.JButton btnUpdateProvince;
    private javax.swing.JButton btnUpdateStation;
    private javax.swing.JComboBox cmbDProvince;
    private javax.swing.JComboBox cmbSDivision;
    private javax.swing.JComboBox cmbSProvince;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblResult;
    private javax.swing.JTable tblDivision;
    private javax.swing.JTable tblProvince;
    private javax.swing.JTable tblStation;
    private javax.swing.JTextField txtDivision;
    private javax.swing.JTextField txtProvince;
    private javax.swing.JTextField txtStation;
    private javax.swing.JTextField txtUpdateDivision;
    private javax.swing.JTextField txtUpdateProvince;
    private javax.swing.JTextField txtUpdateStation;
    // End of variables declaration//GEN-END:variables
}
