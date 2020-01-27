package ReportsGUI;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;

public class Print_Addreses extends JFrame {

    public Print_Addreses() {
        super();
        initComponents();
     
    }
    
    private void fillAdress(){
           try {
            String sql1 = "SELECT ADDRESS FROM S_ADDRESS";
            ResultSet rs1 = SQLConnection.SqlConnection.getData(sql1);
            if (rs1.first()) {
                txt_s_address.setText(rs1.getString("ADDRESS"));
            }
        } catch (Exception e) {
           new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txt_s_address = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        btnPrint = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        txt_s_address.setColumns(20);
        txt_s_address.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        txt_s_address.setRows(5);
        jScrollPane1.setViewportView(txt_s_address);

        jLabel1.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        jLabel1.setText(",smsh hjkakdf.a ,smsKh ");

        btnPrint.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        btnPrint.setText("uqøKh lrkak ");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 309, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPrint)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPrint)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed

        try {
            if (!txt_s_address.getText().isEmpty()) {
                // Save address 
                String sql1 = "SELECT ID,ADDRESS FROM S_ADDRESS";
                ResultSet rs1 = SQLConnection.SqlConnection.getData(sql1);
                if (!rs1.first()) {
                    String sql2 = "INSERT INTO S_ADDRESS(ID,ADDRESS) VALUES('1'," + "'" + txt_s_address.getText() + "') ";
                    SQLConnection.SqlConnection.updateDB(sql2);
                } else {
                    String sql3 = "UPDATE S_ADDRESS SET ADDRESS='" + txt_s_address.getText() + "'";
                    SQLConnection.SqlConnection.updateDB(sql3);
                }
            } else {
                //
            }

            //view report
            Map<String, Object> p_list = new HashMap<>();
            p_list.put("ADDRESS", txt_s_address.getText());
            ReportViewer.view_Report("Address", "Addreses", SQLConnection.SqlConnection.getSqlConnection(), p_list);
            this.setVisible(false);
        } catch (Exception e) {
           new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
        }


    }//GEN-LAST:event_btnPrintActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
       
        fillAdress();
        
    }//GEN-LAST:event_formComponentShown


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txt_s_address;
    // End of variables declaration//GEN-END:variables
}
