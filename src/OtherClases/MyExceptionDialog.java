package OtherClases;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class MyExceptionDialog extends javax.swing.JDialog {

    private final String printing;

    private final JFrame f;

    public MyExceptionDialog(JFrame parent, boolean modal, Throwable e) {
        super(parent, modal);
        this.setResizable(false);
        initComponents();
        lblTitle.setText(e.getClass().toString());
        String error = "";
        error += "------------------------------Infor---------------------------------------------\n";
        error += "Message  :  " + e.getMessage() + "\n";
        error += "Exception class name  :  " + e.getClass().getName() + "\n";
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (StackTraceElement s : stackTrace) {
            if (parent.getClass().getName().equals(s.getClassName())) {
                error += "Class File name  :  " + s.getFileName() + "\n";
                error += "Class name  :  " + s.getClassName() + "\n";
                error += "Method name  :  " + s.getMethodName() + "\n";
                error += "Line number  :  " + s.getLineNumber() + "\n";
                break;
            }
        }
        error += "\n";
        error += "\n";
        error += "-------------------------------------------------------------------------------\n";
        error += "-----------------------------StackTrace----------------------------------------\n";
        for (StackTraceElement s : stackTrace) {
            error += s.toString() + "\n";
        }

        txtErrorMessage.setText(error);
        panelMore.setVisible(false);
        this.setSize(this.getWidth(), 358);
        printing = error;
        f = null;
    }

    public MyExceptionDialog(Class parent, Throwable e) {
        super(new JFrame(), true);
        f = (JFrame) this.getParent();
        f.setVisible(false);
        initComponents();
        lblTitle.setText(e.getClass().toString());
        String error = "";
        error += "------------------------------Infor--------------------------------------------\n";
        error += "Message  :  " + e.getMessage() + "\n";
        error += "Exception class name  :  " + e.getClass().getName() + "\n";
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (StackTraceElement s : stackTrace) {
            if (parent.getName().equals(s.getClassName())) {
                error += "Class File name  :  " + s.getFileName() + "\n";
                error += "Class name  :  " + s.getClassName() + "\n";
                error += "Method name  :  " + s.getMethodName() + "\n";
                error += "Line number  :  " + s.getLineNumber() + "\n";
                break;
            }
        }
        error += "\n";
        error += "\n";
        error += "-------------------------------------------------------------------------------\n";
        error += "-----------------------------StackTrace----------------------------------------\n";
        for (StackTraceElement s : stackTrace) {
            error += s.toString() + "\n";
        }

        txtErrorMessage.setText(error);
        panelMore.setVisible(false);
        this.setSize(this.getWidth(), 298);
        printing = error;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        panelMore = new javax.swing.JScrollPane();
        txtErrorMessage = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        btnSaveAsText = new javax.swing.JButton();
        btn_moreInfor = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lblTitle = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Unexpeted Exception(Error)");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0), 3));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ExceptionIcon.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("Please contact your software developer");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 58)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("Sorry....!");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(204, 0, 0));
        jButton1.setText("ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        panelMore.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));

        txtErrorMessage.setEditable(false);
        txtErrorMessage.setColumns(20);
        txtErrorMessage.setFont(new java.awt.Font("Monospaced", 2, 14)); // NOI18N
        txtErrorMessage.setForeground(new java.awt.Color(204, 0, 0));
        txtErrorMessage.setRows(5);
        txtErrorMessage.setText("Message");
        txtErrorMessage.setWrapStyleWord(true);
        txtErrorMessage.setBorder(null);
        panelMore.setViewportView(txtErrorMessage);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 0, 0));
        jLabel5.setText("Unexpeted Exception(Error)");

        btnSaveAsText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSaveAsText.setForeground(new java.awt.Color(204, 0, 0));
        btnSaveAsText.setText("Save error as text file");
        btnSaveAsText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAsTextActionPerformed(evt);
            }
        });

        btn_moreInfor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_moreInfor.setForeground(new java.awt.Color(204, 0, 0));
        btn_moreInfor.setText("view details");
        btn_moreInfor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_moreInforActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        lblTitle.setEditable(false);
        lblTitle.setBackground(new java.awt.Color(240, 240, 240));
        lblTitle.setColumns(20);
        lblTitle.setFont(new java.awt.Font("Consolas", 3, 14)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(204, 0, 0));
        lblTitle.setLineWrap(true);
        lblTitle.setRows(5);
        lblTitle.setText("Exception Message");
        lblTitle.setWrapStyleWord(true);
        jScrollPane1.setViewportView(lblTitle);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSaveAsText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_moreInfor, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelMore)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 59, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnSaveAsText)
                    .addComponent(btn_moreInfor))
                .addGap(9, 9, 9)
                .addComponent(panelMore, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        this.dispose();
        if (f != null) {
            f.dispose();
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_moreInforActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_moreInforActionPerformed

        if (panelMore.isVisible()) {
            panelMore.setVisible(false);
            btn_moreInfor.setText("view details");
            this.setSize(this.getWidth(), 358);
        } else {
            panelMore.setVisible(true);
            btn_moreInfor.setText("hide details");
            this.setSize(this.getWidth(), 609);
        }

    }//GEN-LAST:event_btn_moreInforActionPerformed

    private void btnSaveAsTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAsTextActionPerformed

        JFileChooser fc = new JFileChooser();
        fc.setDialogType(JFileChooser.OPEN_DIALOG);
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.showDialog(this, "Export");
        File folder = fc.getSelectedFile();
        if (folder != null) {
            try {
                String filepath = folder.getPath() + "\\Error Report_" + new SimpleDateFormat("yyyy_MMM_dd_hh_mm_ss_a").format(new Date()) + ".rtf";
                File file = new File(filepath);
                file.createNewFile();
                try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                    fileOutputStream.write(printing.getBytes());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_btnSaveAsTextActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
      
        System.out.println("w: "+this.getSize().width+"\n h:"+this.getSize().height);
        
    }//GEN-LAST:event_formComponentResized


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSaveAsText;
    private javax.swing.JButton btn_moreInfor;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea lblTitle;
    private javax.swing.JScrollPane panelMore;
    private javax.swing.JTextArea txtErrorMessage;
    // End of variables declaration//GEN-END:variables
}
