/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Karyawan;

import Frame.*;
import Main.Koneksi;
import Main.Rupiah;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DashboardKaryawan extends javax.swing.JFrame {

    /**
     * Creates new form DashboardKaryawan
     */
    public DashboardKaryawan() {
        initComponents();
        showData(Login.namaUser);
        PendapatanHarian();
        ProdukTerjual();
        Pembeli();
        BarChart();
    }
    
    public void BarChart() {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        System.out.println(date);
        String tgl = date.toString();
        try {
            String sql = "SELECT DATE_FORMAT(NOW(), '%c') AS Bulan, DATE_FORMAT(NOW(), '%Y') AS Tahun;";
            Connection conn = (Connection) Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                new Main.BarChartProduk().showBarChart(panel_chart, "PRODUK TERJUAL BULAN INI", 
                        rs.getInt("bulan"), rs.getInt("tahun"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "SALAH " + e);
        }
    }

    public void PendapatanHarian() {
        long millis = System.currentTimeMillis();
        Date date = new java.sql.Date(millis);
        System.out.println(date);
        String tgl = date.toString();
        try {
            String sql = "SELECT SUM(bayar) AS total FROM transaksi_jual WHERE DATE(tanggal) = '" + tgl + "'";
            Connection conn = (Connection) Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            if (res.next()) {
                if (res.getString("total") == null) {
                    this.lbl_hari.setText(Rupiah.convertToRupiah(0));
                } else {
                    this.lbl_hari.setText(Rupiah.convertToRupiah(Integer.parseInt(res.getString("total"))));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "SALAH " + e);
        }
    }

    public void ProdukTerjual() {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        System.out.println(date);
        String tgl = date.toString();
        try {
            String sql = "SELECT SUM(total_item) AS total FROM transaksi_jual WHERE DATE(tanggal) = '" + tgl + "'";
            Connection conn = (Connection) Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            if (res.next()) {
                if (res.getString("total") == null) {
                    this.lbl_produk.setText("0");
                } else {
                    this.lbl_produk.setText(String.format("%,d", res.getInt("total")).replaceAll(",", "."));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "SALAH " + e);
        }
    }

    public void Pembeli() {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        System.out.println(date);
        String tgl = date.toString();
        try {
            String sql = "SELECT COUNT(*) AS jumlah_baris FROM transaksi_jual WHERE DATE(tanggal) = '" + tgl + "'";
            Connection conn = (Connection) Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            if (res.next()) {
                if (res.getString("jumlah_baris") == null) {
                    this.lbl_pembeli.setText("0");
                } else {
                    this.lbl_pembeli.setText(String.format("%,d", res.getInt("jumlah_baris")).replaceAll(",", "."));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "SALAH " + e);
        }
    }

    private void showData(String username) {
        try {
            String query = "SELECT * FROM karyawan WHERE username = '" + username + "'";
            Connection conn = (Connection) Koneksi.configDB();
            Statement stat = conn.createStatement();
            ResultSet r = stat.executeQuery(query);

            if (r.next()) {
                this.lblNamak.setText(r.getString("nama_karyawan"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_hari = new javax.swing.JLabel();
        lbl_pembeli = new javax.swing.JLabel();
        lbl_produk = new javax.swing.JLabel();
        lblNamak = new javax.swing.JLabel();
        panel_chart = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        edit = new javax.swing.JButton();
        btnProduk = new javax.swing.JButton();
        btnSupplier = new javax.swing.JButton();
        btnTransaksi = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_hari.setBackground(new java.awt.Color(255, 255, 255));
        lbl_hari.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        getContentPane().add(lbl_hari, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 380, 330, 60));

        lbl_pembeli.setBackground(new java.awt.Color(205, 31, 31));
        lbl_pembeli.setFont(new java.awt.Font("Tahoma", 1, 50)); // NOI18N
        lbl_pembeli.setForeground(new java.awt.Color(255, 255, 255));
        lbl_pembeli.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lbl_pembeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 600, 310, 60));

        lbl_produk.setBackground(new java.awt.Color(205, 31, 31));
        lbl_produk.setFont(new java.awt.Font("Tahoma", 1, 50)); // NOI18N
        lbl_produk.setForeground(new java.awt.Color(255, 255, 255));
        lbl_produk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lbl_produk, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 490, 280, 60));

        lblNamak.setBackground(new java.awt.Color(255, 255, 255));
        lblNamak.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblNamak.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamak.setOpaque(true);
        getContentPane().add(lblNamak, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 250, 140, 30));

        panel_chart.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        panel_chart.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panel_chart, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 470, 510, 190));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/dash_kar.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 0, 1340, 690));

        edit.setOpaque(false);
        edit.setContentAreaFilled(false);
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        getContentPane().add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 280, 130, 40));

        btnProduk.setOpaque(false);
        btnProduk.setContentAreaFilled(false);
        btnProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdukActionPerformed(evt);
            }
        });
        getContentPane().add(btnProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 200, 50));

        btnSupplier.setOpaque(false);
        btnSupplier.setContentAreaFilled(false);
        btnSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierActionPerformed(evt);
            }
        });
        getContentPane().add(btnSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 200, 50));

        btnTransaksi.setOpaque(false);
        btnTransaksi.setContentAreaFilled(false);
        btnTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransaksiActionPerformed(evt);
            }
        });
        getContentPane().add(btnTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 200, 60));

        btnKeluar.setOpaque(false);
        btnKeluar.setContentAreaFilled(false);
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });
        getContentPane().add(btnKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 20, 130, 50));

        jLabel2.setText("jLabel2");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 590, 180, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdukActionPerformed

        new StokProduk().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnProdukActionPerformed

    private void btnSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierActionPerformed
        new MenghubungiSupplier().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSupplierActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        new PopUp.Profile(null, rootPaneCheckingEnabled).setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_editActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        new PopUp.Profile(null, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void btnTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransaksiActionPerformed
        new PopUp.Profile(null, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_btnTransaksiActionPerformed

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
            java.util.logging.Logger.getLogger(DashboardKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardKaryawan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnProduk;
    private javax.swing.JButton btnSupplier;
    private javax.swing.JButton btnTransaksi;
    private javax.swing.JButton edit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblNamak;
    private javax.swing.JLabel lbl_hari;
    private javax.swing.JLabel lbl_pembeli;
    private javax.swing.JLabel lbl_produk;
    private javax.swing.JPanel panel_chart;
    // End of variables declaration//GEN-END:variables
}
