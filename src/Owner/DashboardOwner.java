/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Owner;

import Frame.*;
import Karyawan.TransaksiBeli;
import Karyawan.TransaksiJual;
import Main.*;
import PopUp.*;
import  Main.BarChartPendapatan;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.UIManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Tria
 */
public class DashboardOwner extends javax.swing.JFrame {

    /**
     * Creates new form DashboardOwner
     */
    public DashboardOwner() {
        initComponents();
        this.lblNama.setText(Login.getNama());
        showData(Login.namaUser);
        PendapatanHarian();
        PendapatanBulanan();
        ProdukTerjual();
        Pembeli();
        BarChart();
//        new Main.ChartManager().showBarChart(panel_chart, "PENDAPATAN PERBULAN", 5, 2023);
    }

    public void BarChart() {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        System.out.println(date);
        String tgl = date.toString();
        try {
//            String sql = "SELECT MONTH(tanggal) AS bulan, YEAR(tanggal) AS tahun FROM transaksi_jual "
//                    + "WHERE DATE(tanggal) = '" + tgl + "'";
            String sql = "SELECT DATE_FORMAT(NOW(), '%c') AS Bulan, DATE_FORMAT(NOW(), '%Y') AS Tahun;";
            Connection conn = (Connection) Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
//                new Main.BarChartPendapatan().showBarChart(panel_chart, "PENDAPATAN BULAN INI", 
//                        rs.getInt("bulan"), rs.getInt("tahun"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "SALAH " + e);
        }
    }

    public void PendapatanHarian() {
        long millis = System.currentTimeMillis();
        Date date = new java.sql.Date(millis);
        String tgl = date.toString();
        try {
            String sql = "SELECT SUM(bayar) AS total FROM transaksi_jual WHERE DATE(tanggal) = '" + tgl + "'";
            Connection conn = (Connection) Koneksi.configDB();
                        System.out.println("SQl Pendapatan harian : "+sql);

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

    public void PendapatanBulanan() {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        String tgl = date.toString();

        try {
            String sql = "SELECT SUM(bayar) AS total FROM transaksi_jual WHERE month(tanggal) = "
                    + "'" + tgl.substring(5, 7) + "' ";
                        System.out.println("SQl Pendapatan Bulanan: "+sql);

            Connection conn = (Connection) Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            if (res.next()) {
                if (res.getString("total") == null) {
                    this.lbl_bulan.setText(Rupiah.convertToRupiah(0));
                } else {
                    this.lbl_bulan.setText(Rupiah.convertToRupiah(Integer.parseInt(res.getString("total"))));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "SALAH " + e);
        }
    }

    public void ProdukTerjual() {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        String tgl = date.toString();
        try {
            String sql = "SELECT SUM(total_item) AS total FROM transaksi_jual WHERE DATE(tanggal) = '" + tgl + "'";
            System.out.println("SQl Produk terjual : "+sql);
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
        String tgl = date.toString();
        try {
            String sql = "SELECT COUNT(*) AS jumlah_baris FROM transaksi_jual WHERE DATE(tanggal) = '" + tgl + "'";
            System.out.println("SQl Pembeli : "+sql);

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
                this.lblNamaO.setText(r.getString("nama_karyawan"));
                lbl_username.setText(Login.namaUser);
                this.lbl_kodeK.setText(r.getString("kode_karyawan"));
                this.lbl_noTelp.setText(r.getString("no_telp"));
                this.lbl_alamat.setText(r.getString("alamat"));
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

        lblNama = new javax.swing.JLabel();
        lbl_pembeli = new javax.swing.JLabel();
        lbl_produk = new javax.swing.JLabel();
        lbl_bulan = new javax.swing.JLabel();
        lbl_hari = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        btnProduk = new javax.swing.JButton();
        btnTransaksi = new javax.swing.JButton();
        btnSupplier = new javax.swing.JButton();
        menuowner = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        lbl_kodeK = new javax.swing.JLabel();
        lbl_username = new javax.swing.JLabel();
        lbl_noTelp = new javax.swing.JLabel();
        lbl_alamat = new javax.swing.JLabel();
        lblNamaO = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNama.setBackground(new java.awt.Color(255, 255, 255));
        lblNama.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNama.setOpaque(true);
        getContentPane().add(lblNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 220, 120, 40));

        lbl_pembeli.setBackground(new java.awt.Color(205, 31, 31));
        lbl_pembeli.setFont(new java.awt.Font("Tahoma", 1, 60)); // NOI18N
        lbl_pembeli.setForeground(new java.awt.Color(255, 255, 255));
        lbl_pembeli.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lbl_pembeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 530, 300, 40));

        lbl_produk.setBackground(new java.awt.Color(205, 31, 31));
        lbl_produk.setFont(new java.awt.Font("Tahoma", 1, 60)); // NOI18N
        lbl_produk.setForeground(new java.awt.Color(255, 255, 255));
        lbl_produk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lbl_produk, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 430, 300, 50));

        lbl_bulan.setBackground(new java.awt.Color(255, 255, 255));
        lbl_bulan.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        getContentPane().add(lbl_bulan, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 610, 320, 50));

        lbl_hari.setBackground(new java.awt.Color(255, 255, 255));
        lbl_hari.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        getContentPane().add(lbl_hari, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 340, 300, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/new_dashb.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 2, 1350, 690));

        btnEdit.setOpaque(false);
        btnEdit.setContentAreaFilled(false);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        getContentPane().add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 610, 120, 40));

        btnProduk.setOpaque(false);
        btnProduk.setContentAreaFilled(false);
        btnProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdukActionPerformed(evt);
            }
        });
        getContentPane().add(btnProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 200, 50));

        btnTransaksi.setOpaque(false);
        btnTransaksi.setContentAreaFilled(false);
        btnTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransaksiActionPerformed(evt);
            }
        });
        getContentPane().add(btnTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 200, 50));

        btnSupplier.setOpaque(false);
        btnSupplier.setContentAreaFilled(false);
        btnSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierActionPerformed(evt);
            }
        });
        getContentPane().add(btnSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 200, 60));

        menuowner.setText("menu owner");
        menuowner.setOpaque(false);
        menuowner.setContentAreaFilled(false);
        menuowner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuownerActionPerformed(evt);
            }
        });
        getContentPane().add(menuowner, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 200, 50));

        btnKeluar.setOpaque(false);
        btnKeluar.setContentAreaFilled(false);
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });
        getContentPane().add(btnKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 30, 120, 40));

        lbl_kodeK.setBackground(new java.awt.Color(255, 255, 255));
        lbl_kodeK.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_kodeK.setOpaque(true);
        getContentPane().add(lbl_kodeK, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 430, 180, 30));

        lbl_username.setBackground(new java.awt.Color(255, 255, 255));
        lbl_username.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_username.setOpaque(true);
        getContentPane().add(lbl_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 480, 180, 30));

        lbl_noTelp.setBackground(new java.awt.Color(255, 255, 255));
        lbl_noTelp.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_noTelp.setOpaque(true);
        getContentPane().add(lbl_noTelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 460, 180, 30));

        lbl_alamat.setBackground(new java.awt.Color(255, 255, 255));
        lbl_alamat.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_alamat.setOpaque(true);
        getContentPane().add(lbl_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 500, 180, 30));

        lblNamaO.setBackground(new java.awt.Color(255, 255, 255));
        lblNamaO.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblNamaO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamaO.setOpaque(true);
        getContentPane().add(lblNamaO, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 560, 250, 40));

        jLabel2.setText("jLabel2");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, 200, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuownerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuownerActionPerformed
        this.dispose();
        new DataKaryawan().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_menuownerActionPerformed

    private void btnProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdukActionPerformed
        new Owner.StokProduk().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnProdukActionPerformed

    private void btnSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierActionPerformed
        new Owner.MenghubungiSupplier1().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSupplierActionPerformed

    private void btnTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransaksiActionPerformed

        new Owner.MenuTransaksi().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTransaksiActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed

        this.dispose();
        new Login().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        new PopUp.Profile(this, rootPaneCheckingEnabled).setVisible(true);

    }//GEN-LAST:event_btnEditActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        new PopUp.Profile(null, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

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
            java.util.logging.Logger.getLogger(DashboardOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardOwner().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnProduk;
    private javax.swing.JButton btnSupplier;
    private javax.swing.JButton btnTransaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblNamaO;
    private javax.swing.JLabel lbl_alamat;
    private javax.swing.JLabel lbl_bulan;
    private javax.swing.JLabel lbl_hari;
    private javax.swing.JLabel lbl_kodeK;
    private javax.swing.JLabel lbl_noTelp;
    private javax.swing.JLabel lbl_pembeli;
    private javax.swing.JLabel lbl_produk;
    private javax.swing.JLabel lbl_username;
    private javax.swing.JButton menuowner;
    // End of variables declaration//GEN-END:variables
}
