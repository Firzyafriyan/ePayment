/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Owner;

import Main.Koneksi;
import PopUp.*;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author Tria
 */
public class DataSupplier extends javax.swing.JFrame {

    private String keyword = "";

    public DataSupplier() {
        initComponents();
        refresh();
        HeaderColumn();
        ShowTabel();

    }

    private void showData(String kode) {
        try {
            Connection conn = (Connection) Koneksi.configDB();
            Statement stat = conn.createStatement();
            ResultSet r = stat.executeQuery("SELECT * FROM supplier WHERE kode_supplier = '" + kode + "'");

            if (r.next()) {
                this.lbl_kode.setText(r.getString("kode_supplier"));
                this.lbl_nama.setText(r.getString("nama_supplier"));
                this.lbl_noTelp.setText(r.getString("no_telp"));
                this.lbl_alamat.setText(r.getString("alamat"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    public void ShowTabel() {
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("No");
        tbl.addColumn("Kode Supplier");
        tbl.addColumn("Nama");
        TabelSupplier.setModel(tbl);
        try {
            String sql = "SELECT * FROM supplier " + keyword;
            Connection conn = (Connection) Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            int no = 1;
            while (res.next()) {
                tbl.addRow(new Object[]{
                    no++,
                    res.getString("kode_supplier"),
                    res.getString("nama_supplier")
                });
                TabelSupplier.setModel(tbl);
                HeaderColumn();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "salah" + e);
        }
    }

    public void HeaderColumn() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TabelSupplier.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        ((DefaultTableCellRenderer) TabelSupplier.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        TabelSupplier.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        JTableHeader thead = TabelSupplier.getTableHeader();
        thead.setBackground(new Color(154,197,224));
        thead.setForeground(Color.BLACK);
        thead.setFont(new Font("Tahoma", Font.BOLD, 14));

        TableColumn column;
        TabelSupplier.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelSupplier.getColumnModel().getColumn(0);
        column.setPreferredWidth(70);
        TabelSupplier.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelSupplier.getColumnModel().getColumn(1);
        column.setPreferredWidth(400);
        TabelSupplier.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelSupplier.getColumnModel().getColumn(2);
        column.setPreferredWidth(607);
    }

    public void refresh() {
        txt_cari.setText(null);
        lbl_alamat.setText(null);
        lbl_kode.setText(null);
        lbl_nama.setText(null);
        lbl_noTelp.setText(null);
        txt_cari.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_kode = new javax.swing.JLabel();
        lbl_nama = new javax.swing.JLabel();
        lbl_noTelp = new javax.swing.JLabel();
        lbl_alamat = new javax.swing.JLabel();
        txt_cari = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelSupplier = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btn_hapus = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();
        btn_daftar = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_clean = new javax.swing.JButton();
        DataMember = new javax.swing.JButton();
        DataKaryawan = new javax.swing.JButton();
        DataProduk = new javax.swing.JButton();
        LaporanPengeluaran = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_kode.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(lbl_kode, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 370, 20));

        lbl_nama.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(lbl_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, 440, 30));

        lbl_noTelp.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(lbl_noTelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 380, 460, 20));

        lbl_alamat.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(lbl_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 470, 450, 20));

        txt_cari.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_cari.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_cari.setBorder(null);
        txt_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cariKeyReleased(evt);
            }
        });
        getContentPane().add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 30, 270, 30));

        TabelSupplier.setBackground(new java.awt.Color(255, 255, 250));
        TabelSupplier.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TabelSupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        TabelSupplier.setAutoscrolls(false);
        TabelSupplier.setGridColor(new java.awt.Color(255, 255, 255));
        TabelSupplier.setOpaque(false);
        TabelSupplier.setRowHeight(30);
        TabelSupplier.setSelectionBackground(new java.awt.Color(154, 197, 224));
        TabelSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelSupplierMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelSupplier);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 180, 690, 380));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/sup1.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 690));

        btn_hapus.setOpaque(false);
        btn_hapus.setContentAreaFilled(false);
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 520, 100, 40));

        btn_back.setOpaque(false);
        btn_back.setContentAreaFilled(false);
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 60, 50));

        btn_daftar.setOpaque(false);
        btn_daftar.setContentAreaFilled(false);
        btn_daftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_daftarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_daftar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 520, 120, 40));

        btn_edit.setOpaque(false);
        btn_edit.setContentAreaFilled(false);
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });
        getContentPane().add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 520, 100, 40));

        btn_clean.setOpaque(false);
        btn_clean.setContentAreaFilled(false);
        btn_clean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cleanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_clean, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 520, 110, 40));

        DataMember.setText("jButton1");
        DataMember.setOpaque(false);
        DataMember.setContentAreaFilled(false);
        DataMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DataMemberActionPerformed(evt);
            }
        });
        getContentPane().add(DataMember, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 640, 210, 40));

        DataKaryawan.setText("jButton1");
        DataKaryawan.setOpaque(false);
        DataKaryawan.setContentAreaFilled(false);
        DataKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DataKaryawanActionPerformed(evt);
            }
        });
        getContentPane().add(DataKaryawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 640, 180, 40));

        DataProduk.setText("jButton1");
        DataProduk.setOpaque(false);
        DataProduk.setContentAreaFilled(false);
        DataProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DataProdukActionPerformed(evt);
            }
        });
        getContentPane().add(DataProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 640, 190, 40));

        LaporanPengeluaran.setText("jButton1");
        LaporanPengeluaran.setOpaque(false);
        LaporanPengeluaran.setContentAreaFilled(false);
        LaporanPengeluaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaporanPengeluaranActionPerformed(evt);
            }
        });
        getContentPane().add(LaporanPengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 640, 190, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_daftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_daftarActionPerformed
        new PopUp.DaftarSupplier(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_btn_daftarActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        if (this.lbl_kode.getText().equals("")) {
            Informasi.PilihData p = new Informasi.PilihData(this, true);
            p.setVisible(true);
        } else {
            UpdateSupplier p = new UpdateSupplier(this, true, this.TabelSupplier.getValueAt(this.TabelSupplier.getSelectedRow(), 1).toString());
            System.out.println("contoh " + this.TabelSupplier.getValueAt(this.TabelSupplier.getSelectedRow(), 1).toString());
            p.setVisible(true);
            this.ShowTabel();
            refresh();
        }    // TODO add your handling code here:
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_cleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cleanActionPerformed

        ShowTabel();
        refresh();
    }//GEN-LAST:event_btn_cleanActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        if (this.lbl_kode.getText().equals("")) {
            Informasi.PilihData p = new Informasi.PilihData(this, true);
            p.setVisible(true);
        } else {
            Informasi.InginHapusData p = new Informasi.InginHapusData(this, true);
            p.setVisible(true);

            // jika button ya diclose
            if (p.isClose()) {
                String idS = this.lbl_kode.getText(),
                        sql = "DELETE FROM supplier WHERE kode_supplier = '" + idS + "'";
                try {
                    Connection conn = (Connection) Main.Koneksi.configDB();
                    Statement stat = conn.createStatement();
                    if (stat.executeUpdate(sql) > 0) {
                        new Informasi.BerhasilHapusData(this, rootPaneCheckingEnabled).setVisible(true);
                        refresh();
                        ShowTabel();
                        HeaderColumn();
                        this.ShowTabel();
                    } else {
                        Informasi.GagalHapusData q = new Informasi.GagalHapusData(this, true);
                        q.setVisible(true);
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        new Owner.DashboardOwner().setVisible(true);
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_btn_backActionPerformed

    private void TabelSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelSupplierMouseClicked
        this.showData(this.TabelSupplier.getValueAt(this.TabelSupplier.getSelectedRow(), 1).toString());
    }//GEN-LAST:event_TabelSupplierMouseClicked

    private void txt_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyReleased
        String key = this.txt_cari.getText();
        this.keyword = "WHERE kode_supplier LIKE '%" + key + "%' OR nama_supplier LIKE '%" + key + "%'";
        this.ShowTabel();
        HeaderColumn();
    }//GEN-LAST:event_txt_cariKeyReleased

    private void DataMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DataMemberActionPerformed
        new DataMember().setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_DataMemberActionPerformed

    private void DataKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DataKaryawanActionPerformed
        new DataKaryawan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_DataKaryawanActionPerformed

    private void DataProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DataProdukActionPerformed
        new DataProduk().setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_DataProdukActionPerformed

    private void LaporanPengeluaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LaporanPengeluaranActionPerformed
        new LaporanPengeluaran().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_LaporanPengeluaranActionPerformed

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
            java.util.logging.Logger.getLogger(DataSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataSupplier().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DataKaryawan;
    private javax.swing.JButton DataMember;
    private javax.swing.JButton DataProduk;
    private javax.swing.JButton LaporanPengeluaran;
    private javax.swing.JTable TabelSupplier;
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_clean;
    private javax.swing.JButton btn_daftar;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_alamat;
    private javax.swing.JLabel lbl_kode;
    private javax.swing.JLabel lbl_nama;
    private javax.swing.JLabel lbl_noTelp;
    private javax.swing.JTextField txt_cari;
    // End of variables declaration//GEN-END:variables
}
