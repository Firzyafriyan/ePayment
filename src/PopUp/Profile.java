/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package PopUp;

import Frame.Login;
import static Frame.Login.namaUser;
import Karyawan.DashboardKaryawan;
import Main.Koneksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Tria
 */
public class Profile extends javax.swing.JDialog {

    /**
     * Creates new form Profile
     */
    public Profile(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        showData(Login.namaUser);
    }

    private void showData(String username) {
        try {
            String query = "SELECT * FROM karyawan WHERE username = '" + username + "'";
            Connection conn = (Connection) Koneksi.configDB();
            Statement stat = conn.createStatement();
            ResultSet r = stat.executeQuery(query);

            if (r.next()) {
                this.lbl_kodeK.setText(r.getString("kode_karyawan"));
                this.txt_nama.setText(r.getString("nama_karyawan"));
                lbl_username.setText(Login.namaUser);
                this.txt_noTelp.setText(r.getString("no_telp"));
                this.txt_alamat.setText(r.getString("alamat"));
                this.lbl_user.setText(r.getString("level_user"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }
    
    public static String username = "", kode_karyawan = "", nama = "";
    static boolean admin = false;
    public void pilihDashboard() {
        String Username = Login.getUsernamee();
        String Password = Login.getPassword();
        System.out.println("INI USERNAME ...." + Username);
        System.out.println(Password);
        try {
            String sql = "SELECT * FROM karyawan WHERE username=" + "'" + Username + "'" + " AND password='" + Password + "'";
            Connection conn = (Connection) Koneksi.configDB();
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(sql);
            System.out.println("Koneksi Berhasil!!");

            if (r.next()) {
                namaUser = r.getString("username");
                if (r.getString("level_user").equalsIgnoreCase("owner")) {
                    System.out.println("user admin");
                    new Owner.DashboardOwner().setVisible(true);
                    this.dispose();
                    admin = true;
                } else {
                    System.out.println("user karyawan");
                    new DashboardKaryawan().setVisible(true);
                    this.dispose();
                    admin = false;
                }

            } else {
                System.out.println("GAGAL KE DASHBOARD");
            }
        } catch (Exception e) {
            System.out.println("GAGAL KE DASHBOARD");

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_user = new javax.swing.JLabel();
        lbl_username = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        txt_alamat = new javax.swing.JTextField();
        txt_noTelp = new javax.swing.JTextField();
        lbl_kodeK = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JButton();
        kmbali = new javax.swing.JButton();
        btnGanti = new javax.swing.JButton();
        btnProduk = new javax.swing.JButton();
        btnSupplier = new javax.swing.JButton();
        btnTransaksi = new javax.swing.JButton();
        menuowner = new javax.swing.JButton();
        btnDashboard2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_user.setBackground(new java.awt.Color(255, 255, 255));
        lbl_user.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_user.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_user.setOpaque(true);
        getContentPane().add(lbl_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 380, 160, 28));

        lbl_username.setBackground(new java.awt.Color(255, 255, 255));
        lbl_username.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_username.setOpaque(true);
        getContentPane().add(lbl_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 350, 350, 20));

        txt_nama.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_nama.setBorder(null);
        txt_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaActionPerformed(evt);
            }
        });
        getContentPane().add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 255, 270, 30));

        txt_alamat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_alamat.setBorder(null);
        getContentPane().add(txt_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 520, 360, 30));

        txt_noTelp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_noTelp.setBorder(null);
        getContentPane().add(txt_noTelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 440, 350, 20));

        lbl_kodeK.setBackground(new java.awt.Color(255, 255, 255));
        lbl_kodeK.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_kodeK.setOpaque(true);
        getContentPane().add(lbl_kodeK, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 170, 270, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/prfl.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 680));

        btnSimpan.setOpaque(false);
        btnSimpan.setContentAreaFilled(false);
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        getContentPane().add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 640, 200, 40));

        kmbali.setOpaque(false);
        kmbali.setContentAreaFilled(false);
        kmbali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kmbaliActionPerformed(evt);
            }
        });
        getContentPane().add(kmbali, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 40, 40));

        btnGanti.setOpaque(false);
        btnGanti.setContentAreaFilled(false);
        btnGanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGantiActionPerformed(evt);
            }
        });
        getContentPane().add(btnGanti, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 640, 210, 40));

        btnProduk.setOpaque(false);
        btnProduk.setContentAreaFilled(false);
        btnProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdukActionPerformed(evt);
            }
        });
        getContentPane().add(btnProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 200, 50));

        btnSupplier.setOpaque(false);
        btnSupplier.setContentAreaFilled(false);
        btnSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierActionPerformed(evt);
            }
        });
        getContentPane().add(btnSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 200, 60));

        btnTransaksi.setOpaque(false);
        btnTransaksi.setContentAreaFilled(false);
        btnTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransaksiActionPerformed(evt);
            }
        });
        getContentPane().add(btnTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 200, 50));

        menuowner.setText("menu owner");
        menuowner.setOpaque(false);
        menuowner.setContentAreaFilled(false);
        menuowner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuownerActionPerformed(evt);
            }
        });
        getContentPane().add(menuowner, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 200, 50));

        btnDashboard2.setOpaque(false);
        btnDashboard2.setContentAreaFilled(false);
        btnDashboard2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboard2ActionPerformed(evt);
            }
        });
        getContentPane().add(btnDashboard2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 200, 60));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaActionPerformed

    private void kmbaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kmbaliActionPerformed
        this.dispose();
    }//GEN-LAST:event_kmbaliActionPerformed

    private void btnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGantiActionPerformed
    new PopUp.GantiPassword(null, rootPaneCheckingEnabled).setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_btnGantiActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        String kode = lbl_kodeK.getText();
        String nama = txt_nama.getText();
        String username = lbl_username.getText();
        String notelp = txt_noTelp.getText();
        String alamat = txt_alamat.getText();

        if (kode.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (nama.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (username.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (notelp.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (alamat.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else {

            try {
                Statement statement = Main.Koneksi.configDB().createStatement();
                statement.executeUpdate(String.format("UPDATE karyawan SET nama_karyawan = '%s', no_telp = '%s', alamat = '%s' "
                        + "WHERE kode_karyawan = '%s' ", nama, notelp, alamat, kode));
                statement.close();
                new Informasi.BerhasilEditData(null, rootPaneCheckingEnabled).setVisible(true);
                this.dispose();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                new Informasi.GagalEditData(null, rootPaneCheckingEnabled).setVisible(true);
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdukActionPerformed
        new Owner.StokProduk().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnProdukActionPerformed

    private void btnSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierActionPerformed
        new Owner.MenghubungiSupplier1().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSupplierActionPerformed

    private void btnTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransaksiActionPerformed
        this.dispose();
        new Owner.MenuTransaksi().setVisible(true);
        
    }//GEN-LAST:event_btnTransaksiActionPerformed

    private void menuownerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuownerActionPerformed
        new Owner.DataKaryawan().setVisible(true);
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_menuownerActionPerformed

    private void btnDashboard2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboard2ActionPerformed
        this.pilihDashboard();
    }//GEN-LAST:event_btnDashboard2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Profile dialog = new Profile(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDashboard2;
    private javax.swing.JButton btnGanti;
    private javax.swing.JButton btnProduk;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnSupplier;
    private javax.swing.JButton btnTransaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton kmbali;
    private javax.swing.JLabel lbl_kodeK;
    private javax.swing.JLabel lbl_user;
    private javax.swing.JLabel lbl_username;
    private javax.swing.JButton menuowner;
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_noTelp;
    // End of variables declaration//GEN-END:variables
}
