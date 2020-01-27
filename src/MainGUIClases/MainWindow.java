/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainGUIClases;

import OtherClases.MyFrame;
import ReportsGUI.MemberDetails;
import ReportsGUI.Print_Addreses;
import ReportsGUI.Report03_Thanathura;
import ReportsGUI.Report04_ThrasthaPraharaya;
import ReportsGUI.Report05_SharirikaHani;
import ReportsGUI.Report06_IpaimeShakthiya;
import ReportsGUI.Report07_SevaKalaya;
import ReportsGUI.Report08_Watup;
import ReportsGUI.Report09_padinchiya;
import ReportsGUI.Report10_Agg55;
import ReportsGUI.Report11_Wiwahaya;
import ReportsGUI.Report12_Daruwan;
import ReportsGUI.Report13_AbadhithaUpakarana;
import ReportsGUI.Report14_Sahana;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;

public class MainWindow extends javax.swing.JFrame {

    private AddNewMemberWindow newMember;
    private UpdateMember deleteMember;
    private ExporterWindow export_window;
    private ImporterWindow import_window;
    //private MyFrame myFrame;

    private MemberDetails r_member_details;
    private Report03_Thanathura r_thnathura;
    private Report04_ThrasthaPraharaya r_trastha_prahara;
    private Report05_SharirikaHani r_sharirika_hani;
    private Report06_IpaimeShakthiya r_Ipaime_shakthiya;
    private Report07_SevaKalaya r_seva_kalaya;
    private Report08_Watup r_watup;
    private Report09_padinchiya r_padinchiya;
    private Report10_Agg55 r_upanDinaya;
    private Report11_Wiwahaya r_wiwahaya;
    private Report12_Daruwan r_daruwan;
    private Report13_AbadhithaUpakarana r_AU;
    private Report14_Sahana r_Sahana;
    private Print_Addreses p_Addreses;

    // private ReportsGUI.Report01_SA ReportSA;
    private Settings settings;

    public MainWindow() {
        this.setResizable(false);
        initComponents();
        init();
        lblTotalMrmbers.setText(Integer.toString(getTotalMembers()));
    }

    private void showDateTime() {
        new Timer(1000, e -> {
            Date today = new Date();
            lblDate.setText(new SimpleDateFormat("yyyy-MMM-dd").format(today));
            lblTime.setText(new SimpleDateFormat("hh:mm:ss a").format(today));
        }).start();
    }

    private int getTotalMembers() {
        int t = 0;
        try {
            ResultSet rs = SQLConnection.SqlConnection.getData("SELECT COUNT(SA) FROM SMJK");
            rs.first();
            t = rs.getInt(1);
        } catch (Exception e) {
            new OtherClases.MyExceptionDialog(this, true, e).setVisible(true);
        }
        return t;
    }

    private void init() {
        newMember = new AddNewMemberWindow();
        deleteMember = new UpdateMember();
        export_window = new ExporterWindow(this, true);
        import_window = new ImporterWindow(this, true);
        //myFrame = new MyFrame();

        r_member_details = new MemberDetails();
        r_thnathura = new Report03_Thanathura();
        r_trastha_prahara = new Report04_ThrasthaPraharaya();
        r_sharirika_hani = new Report05_SharirikaHani();
        r_Ipaime_shakthiya = new Report06_IpaimeShakthiya();
        r_seva_kalaya = new Report07_SevaKalaya();
        r_watup = new Report08_Watup();
        r_padinchiya = new Report09_padinchiya();
        r_upanDinaya = new Report10_Agg55();
        r_wiwahaya = new Report11_Wiwahaya();
        r_daruwan = new Report12_Daruwan();
        r_AU = new Report13_AbadhithaUpakarana();
        r_Sahana = new Report14_Sahana();
        p_Addreses = new Print_Addreses();

        settings = new Settings(this, true);
        showDateTime();
        setCustomTitle();
        settings.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                setCustomTitle();
            }
        });

        WindowAdapter forUpdateMemberCount = new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e) {
                lblTotalMrmbers.setText(Integer.toString(getTotalMembers()));
            }

        };
        newMember.addWindowListener(forUpdateMemberCount);
        import_window.addWindowListener(forUpdateMemberCount);
    }

    private void setCustomTitle() {
        this.setTitle("ශ්‍රී ලංකා පොලිස් යුධ අබාධිත රණවිරු සංගමය  " + " User Name: " + getUserName());
    }

    private String getUserName() {
        String user_name = "";
        try {
            ResultSet rs = SQLConnection.SqlConnection.getData("SELECT * FROM SYSTEM_USER");
            rs.first();
            user_name = rs.getString("user_name");
        } catch (Exception e) {

        }
        return user_name;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblTime525 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblTotalMrmbers = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lblTime = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnAddNewMember = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnDeleteMember = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblAni = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        btnRSamajikaAnjayaNuwa = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        btnShowMyFrame = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        btnSetting = new javax.swing.JLabel();
        btnImport = new javax.swing.JLabel();
        btnExport = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ශ්‍රී ලංකා පොලිස් යුධ අබාධිත රණවිරු සංගමය");
        setBackground(new java.awt.Color(255, 153, 0));

        jPanel5.setBackground(new java.awt.Color(153, 51, 255));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 51, 255), 2, true));
        jPanel5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel5MouseMoved(evt);
            }
        });
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Iskoola Pota", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("දිනය    -");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 13, 70, -1));

        lblDate.setFont(new java.awt.Font("Iskoola Pota", 1, 18)); // NOI18N
        lblDate.setForeground(new java.awt.Color(255, 255, 255));
        lblDate.setText("1994/Feb/19");
        jPanel5.add(lblDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 13, 130, -1));

        lblTime525.setFont(new java.awt.Font("FMMalithi", 0, 14)); // NOI18N
        lblTime525.setForeground(new java.awt.Color(255, 255, 255));
        lblTime525.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTime525.setText("uq¨ idudðlhska ixLHdj ");
        jPanel5.add(lblTime525, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 190, -1));

        jLabel9.setFont(new java.awt.Font("Iskoola Pota", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("වේලාව -");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        lblTotalMrmbers.setFont(new java.awt.Font("Iskoola Pota", 1, 18)); // NOI18N
        lblTotalMrmbers.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalMrmbers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalMrmbers.setText("00");
        jPanel5.add(lblTotalMrmbers, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 190, -1));

        jSeparator2.setForeground(new java.awt.Color(51, 153, 255));
        jPanel5.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, 190, 10));

        lblTime.setFont(new java.awt.Font("Iskoola Pota", 1, 18)); // NOI18N
        lblTime.setForeground(new java.awt.Color(255, 255, 255));
        lblTime.setText("වේලාව");
        jPanel5.add(lblTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 130, -1));

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 255), 2, true));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAddNewMember.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        btnAddNewMember.setForeground(new java.awt.Color(0, 0, 204));
        btnAddNewMember.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add_user.png"))); // NOI18N
        btnAddNewMember.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 0), 2, true));
        btnAddNewMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewMemberActionPerformed(evt);
            }
        });
        jPanel1.add(btnAddNewMember, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 160, 127));

        jLabel1.setFont(new java.awt.Font("Iskoola Pota", 0, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 51));
        jLabel1.setText("සාමාජිකයෙකු බදවගන්න ");
        jLabel1.setToolTipText("");
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 150, -1, -1));

        btnDeleteMember.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        btnDeleteMember.setForeground(new java.awt.Color(0, 0, 204));
        btnDeleteMember.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/edit_user.png"))); // NOI18N
        btnDeleteMember.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 51), 2, true));
        btnDeleteMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteMemberActionPerformed(evt);
            }
        });
        jPanel1.add(btnDeleteMember, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 160, 127));

        jLabel6.setFont(new java.awt.Font("Iskoola Pota", 0, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 102, 0));
        jLabel6.setText("තොරතුරු යාවත්කාලීන කරන්න ");
        jLabel6.setToolTipText("");
        jLabel6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 150, -1, -1));

        jPanel7.setBackground(new java.awt.Color(0, 153, 255));
        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 255), 2, true));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 18, 174));

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 204), 2, true));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Report.png"))); // NOI18N

        jPanel8.setBackground(new java.awt.Color(204, 0, 204));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAni.setFont(new java.awt.Font("FMBindumathi", 0, 14)); // NOI18N
        lblAni.setForeground(new java.awt.Color(255, 255, 255));
        lblAni.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAni.setText("jd¾;d ,nd.kak");
        jPanel8.add(lblAni, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 4, 650, -1));

        jScrollPane1.setBackground(new java.awt.Color(153, 153, 153));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtDescription.setEditable(false);
        txtDescription.setBackground(new java.awt.Color(240, 240, 240));
        txtDescription.setColumns(20);
        txtDescription.setFont(new java.awt.Font("FMMalithi", 0, 24)); // NOI18N
        txtDescription.setForeground(new java.awt.Color(204, 0, 204));
        txtDescription.setLineWrap(true);
        txtDescription.setRows(5);
        txtDescription.setWrapStyleWord(true);
        txtDescription.setBorder(null);
        jScrollPane1.setViewportView(txtDescription);

        jPanel3.setLayout(new java.awt.GridLayout(7, 2, 5, 5));

        btnRSamajikaAnjayaNuwa.setFont(new java.awt.Font("FMSamantha", 0, 18)); // NOI18N
        btnRSamajikaAnjayaNuwa.setForeground(new java.awt.Color(51, 51, 51));
        btnRSamajikaAnjayaNuwa.setText("idudðl wxlh wkqj ");
        btnRSamajikaAnjayaNuwa.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnRSamajikaAnjayaNuwaMouseMoved(evt);
            }
        });
        btnRSamajikaAnjayaNuwa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRSamajikaAnjayaNuwaActionPerformed(evt);
            }
        });
        jPanel3.add(btnRSamajikaAnjayaNuwa);

        jButton33.setFont(new java.awt.Font("FMSamantha", 0, 18)); // NOI18N
        jButton33.setForeground(new java.awt.Color(51, 51, 51));
        jButton33.setText("jegqma yd oSukd ");
        jButton33.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton33MouseMoved(evt);
            }
        });
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton33);

        jButton15.setFont(new java.awt.Font("FMSamantha", 0, 18)); // NOI18N
        jButton15.setForeground(new java.awt.Color(51, 51, 51));
        jButton15.setText(";k;=r ");
        jButton15.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton15MouseMoved(evt);
            }
        });
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton15);

        jButton37.setFont(new java.awt.Font("FMSamantha", 0, 18)); // NOI18N
        jButton37.setForeground(new java.awt.Color(51, 51, 51));
        jButton37.setText("iyk ");
        jButton37.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton37MouseMoved(evt);
            }
        });
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton37);

        jButton30.setFont(new java.awt.Font("FMSamantha", 0, 18)); // NOI18N
        jButton30.setForeground(new java.awt.Color(51, 51, 51));
        jButton30.setText("úY%du hkúg ");
        jButton30.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton30MouseMoved(evt);
            }
        });
        jButton30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton30MouseReleased(evt);
            }
        });
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton30);

        jButton20.setFont(new java.awt.Font("FMSamantha", 0, 18)); // NOI18N
        jButton20.setForeground(new java.awt.Color(51, 51, 51));
        jButton20.setText("orejka ");
        jButton20.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton20MouseMoved(evt);
            }
        });
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton20);

        jButton10.setFont(new java.awt.Font("FMSamantha", 0, 18)); // NOI18N
        jButton10.setForeground(new java.awt.Color(51, 51, 51));
        jButton10.setText("Wmka oskh ");
        jButton10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton10MouseMoved(evt);
            }
        });
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton10);

        jButton6.setFont(new java.awt.Font("FMSamantha", 0, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(51, 51, 51));
        jButton6.setText("mosxÑh ");
        jButton6.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton6MouseMoved(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton6);

        jButton18.setFont(new java.awt.Font("FMSamantha", 0, 18)); // NOI18N
        jButton18.setForeground(new java.awt.Color(51, 51, 51));
        jButton18.setText("YdÍßl ydks ");
        jButton18.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton18MouseMoved(evt);
            }
        });
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton18);

        jButton26.setFont(new java.awt.Font("FMSamantha", 0, 18)); // NOI18N
        jButton26.setForeground(new java.awt.Color(51, 51, 51));
        jButton26.setText(";%ia; m%ydrh ");
        jButton26.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton26MouseMoved(evt);
            }
        });
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton26);

        jButton23.setFont(new java.awt.Font("FMSamantha", 0, 18)); // NOI18N
        jButton23.setForeground(new java.awt.Color(51, 51, 51));
        jButton23.setText("újdyl$wújdyl ");
        jButton23.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton23MouseMoved(evt);
            }
        });
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton23);

        jButton31.setFont(new java.awt.Font("FMSamantha", 0, 18)); // NOI18N
        jButton31.setForeground(new java.awt.Color(51, 51, 51));
        jButton31.setText("bmhsfï  Yla;sh ");
        jButton31.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton31MouseMoved(evt);
            }
        });
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton31);

        jButton16.setFont(new java.awt.Font("FMSamantha", 0, 18)); // NOI18N
        jButton16.setForeground(new java.awt.Color(51, 51, 51));
        jButton16.setText("wdndê; WmlrK ");
        jButton16.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton16MouseMoved(evt);
            }
        });
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton16);

        jButton38.setFont(new java.awt.Font("FMSamantha", 0, 18)); // NOI18N
        jButton38.setForeground(new java.awt.Color(51, 51, 51));
        jButton38.setText(",smsK ");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton38);

        btnShowMyFrame.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        btnShowMyFrame.setForeground(new java.awt.Color(153, 0, 153));
        btnShowMyFrame.setText("E");
        btnShowMyFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowMyFrameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnShowMyFrame))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnShowMyFrame)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 255)));
        jPanel11.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel11MouseMoved(evt);
            }
        });
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSetting.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSetting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/settings01.png"))); // NOI18N
        btnSetting.setToolTipText("Settings");
        btnSetting.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnSettingMouseMoved(evt);
            }
        });
        btnSetting.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSettingMouseClicked(evt);
            }
        });
        jPanel11.add(btnSetting, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 60, 40));

        btnImport.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnImport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ri01.png"))); // NOI18N
        btnImport.setToolTipText("Import Data");
        btnImport.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnImportMouseMoved(evt);
            }
        });
        btnImport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnImportMouseClicked(evt);
            }
        });
        jPanel11.add(btnImport, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, 40));

        btnExport.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/re01.png"))); // NOI18N
        btnExport.setToolTipText("Export Data");
        btnExport.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnExportMouseMoved(evt);
            }
        });
        btnExport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExportMouseClicked(evt);
            }
        });
        jPanel11.add(btnExport, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 60, 40));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 9, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteMemberActionPerformed

        deleteMember.setVisible(true);

    }//GEN-LAST:event_btnDeleteMemberActionPerformed

    private void btnAddNewMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewMemberActionPerformed

        newMember.setVisible(true);

    }//GEN-LAST:event_btnAddNewMemberActionPerformed


    private void btnRSamajikaAnjayaNuwaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRSamajikaAnjayaNuwaMouseMoved

        txtDescription.setText("idudðl wxlh\" idudðlhdf.a ku\" ÿrl:k wxlh jeks o;a; Ndú;d lr jd¾:d ,nd.kak ");
        //"සාමාජික අංකය , සාමාජිකයාගේ නම , දුරකථන අංකය වැනි දත්ත බාවිත කර වාර්තා ලබාගන්න."


    }//GEN-LAST:event_btnRSamajikaAnjayaNuwaMouseMoved


    private void btnRSamajikaAnjayaNuwaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRSamajikaAnjayaNuwaActionPerformed

        r_member_details.setVisible(true);

    }//GEN-LAST:event_btnRSamajikaAnjayaNuwaActionPerformed

    private void btnSettingMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSettingMouseMoved

        btnSetting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/setting2.png")));


    }//GEN-LAST:event_btnSettingMouseMoved

    private void jPanel11MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseMoved

        btnSetting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/settings01.png"))); // NOI18N
        btnImport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ri01.png")));
        btnExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/re01.png")));


    }//GEN-LAST:event_jPanel11MouseMoved

    private void jPanel5MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseMoved

        btnSetting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/settings01.png"))); // NOI18N
        btnImport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ri01.png")));
        btnExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/re01.png")));


    }//GEN-LAST:event_jPanel5MouseMoved

    private void btnSettingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSettingMouseClicked

        settings.setVisible(true);

    }//GEN-LAST:event_btnSettingMouseClicked

    private void btnImportMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImportMouseMoved

        btnImport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ri02.png")));


    }//GEN-LAST:event_btnImportMouseMoved

    private void btnImportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImportMouseClicked

        import_window.setVisible(true);

    }//GEN-LAST:event_btnImportMouseClicked

    private void btnExportMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportMouseMoved

        btnExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/re02.png")));


    }//GEN-LAST:event_btnExportMouseMoved


    private void btnExportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportMouseClicked

        export_window.setVisible(true);

    }//GEN-LAST:event_btnExportMouseClicked


    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed

        r_thnathura.setVisible(true);

    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed

        r_trastha_prahara.setVisible(true);

    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed

        r_sharirika_hani.setVisible(true);

    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed

        r_Ipaime_shakthiya.setVisible(true);

    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed

        r_seva_kalaya.setVisible(true);

    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed

        r_watup.setVisible(true);

    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        r_padinchiya.setVisible(true);

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

        r_upanDinaya.setVisible(true);

    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed

        r_wiwahaya.setVisible(true);

    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed

        r_daruwan.setVisible(true);

    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed

        r_AU.setVisible(true);

    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton15MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MouseMoved

        txtDescription.setText("idudðlhdf.a ;k;=r\" úfYaI tallh\" Wm fiajd$ks;H fiajd hk tajd Ndú;d lr jd¾:d ,nd.kak ");
        //"සාමාජිකයාගේ තනතුර,විශේෂ එක්කය,උප සෙවා/නිත්‍ය සේවා යන ඒවා බාවිත කර වාර්තා ලබාගන්න."


    }//GEN-LAST:event_jButton15MouseMoved

    private void jButton26MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton26MouseMoved

        txtDescription.setText("idudðlhd ,lajQ ;%ia; m%ydrh ms<sno jd¾:d ,nd.kak");
        //"සාමාජිකයා ලක්වූ ත්‍රස්ත ප්‍රහාරය පිළිබද වාර්තා ලබාගන්න."


    }//GEN-LAST:event_jButton26MouseMoved

    private void jButton18MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton18MouseMoved

        txtDescription.setText("idudðlhd ,lajQ ;%ia; m%ydrh fya;=fjka isÿjQ ydks ms<sno jd¾:d ,nd.kak");
        //"සාමාජිකයා ලක්වු ත්‍රස්ත ප්‍රහාරය හේතුවෙන් සිදුවූ හානි පිළිබද වාර්තා ලබාගන්න."


    }//GEN-LAST:event_jButton18MouseMoved

    private void jButton31MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton31MouseMoved

        txtDescription.setText("bmhSfï Yla;sh ySkjQ m%;sY;h Ndú;d lr jd¾:d ,nd.kak");
        //"ඉපයීමේ ශක්තිය හීනවූ ප්‍රතිශතය බාවිතා කර වාර්තා ලබාගන්න."


    }//GEN-LAST:event_jButton31MouseMoved

    private void jButton30MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton30MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton30MouseReleased

    private void jButton30MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton30MouseMoved

        txtDescription.setText("úY%du hkúg fiajd ld,h Ndú;d lr jd¾:d ,nd.kak");
        //"විශ්‍රාම යනවිට සෙවා කාලය බාවිතා කර වාර්තා ලබාගන්න."


    }//GEN-LAST:event_jButton30MouseMoved

    private void jButton33MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton33MouseMoved

        txtDescription.setText("idudðlhka yg ,efnk udisl jegqma\" úY%du jegqma\" ÿn,;d úY%du jegqma ms<sno jd¾:d ,nd.kak");
        //"සාමාජිකයන් හට ලැබෙන මසික වැටුප්,විශ්‍රාම වැටුප්,දුබලතා විශ්‍රාම වැටුප් බාවිතා කර වාර්තා ලබාගන්න"


    }//GEN-LAST:event_jButton33MouseMoved

    private void jButton6MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseMoved

        txtDescription.setText("idudðlhd moskaÑ m<d;\" fmd,sia fldÜGdYh\" fmd,sia jiu hk tajd Ndú;d lr jd¾:d ,nd.kak");
        //"සාමාජිකයා පදින්චි පළාත,දිස්ත්‍රික්කය,පොලිස් කොට්ථාසය,පොලිස් වසම යන එවා බාවිතා කර වාර්තා ලබාගන්න."


    }//GEN-LAST:event_jButton6MouseMoved

    private void jButton10MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseMoved

        txtDescription.setText("idudðlhdf.a Wmkaoskh Ndú;d lr jd¾:d ,nd.kak");
        //"සාමාජිකයාගේ උපන් දිනය බාවිතා කර වාර්තා ලබාගන්න."


    }//GEN-LAST:event_jButton10MouseMoved

    private void jButton23MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton23MouseMoved

        txtDescription.setText("idudðlhdf.a újdyh ms<sno f;dr;=re Ndú;d lr jd¾:d ,nd.kak");
        //"සාමාජිකයාගේ විවාහය පිළිබද තොරතුරු බාවිතාකර වාර්තා ලබාගන්න."


    }//GEN-LAST:event_jButton23MouseMoved

    private void jButton20MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton20MouseMoved

        txtDescription.setText("idudðlhkaf.a orejka ms<sno úia;r Ndú;d lr jd¾:d ,nd.kak");
        //"සාමාජිකයාගේ දරුවන් පිළිපද විස්තර බාවිතා කර වාර්තා ලබාගන්න."


    }//GEN-LAST:event_jButton20MouseMoved

    private void jButton16MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton16MouseMoved

        txtDescription.setText("idudðlhd Ndú;d lrk wndê; WmlrK ms<sno úia;r Ndú;dlr jd¾:d ,nd.kak");
        //"සාමාජිකයා බාවිතා කරන ආබාදිත උපකරන පිළිබද විස්තර බාවිත කර වාර්තා ලබාගන්න."


    }//GEN-LAST:event_jButton16MouseMoved

    private void jButton37MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton37MouseMoved

        txtDescription.setText("idudðlhd yg ,eî we;s iyk ms<sno úia;r Ndú;d lr jd¾:d ,nd.kak");
        //"සාමාජිකයා හට ලැබී ඇති සහන පිළිබද විස්තර බාවිතා කර වාර්තා ලබාගන්න."


    }//GEN-LAST:event_jButton37MouseMoved

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed

        r_Sahana.setVisible(true);

    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed

        p_Addreses.setVisible(true);

    }//GEN-LAST:event_jButton38ActionPerformed

    private void btnShowMyFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowMyFrameActionPerformed

        //myFrame.setVisible(true);

    }//GEN-LAST:event_btnShowMyFrameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNewMember;
    private javax.swing.JButton btnDeleteMember;
    private javax.swing.JLabel btnExport;
    private javax.swing.JLabel btnImport;
    private javax.swing.JButton btnRSamajikaAnjayaNuwa;
    private javax.swing.JLabel btnSetting;
    private javax.swing.JButton btnShowMyFrame;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblAni;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTime525;
    private javax.swing.JLabel lblTotalMrmbers;
    private javax.swing.JTextArea txtDescription;
    // End of variables declaration//GEN-END:variables
}
