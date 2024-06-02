/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package osepcafe.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import osepcafe.koneksi;

/**
 *
 * @author sepae
 */
public class FrameTransaksi extends javax.swing.JFrame {
    private DefaultTableModel model = null;
    private PreparedStatement stat;
    private ResultSet rs;
    koneksi k = new koneksi();

    /**
     * Creates new form FrameTransaksi
     */
    public FrameTransaksi() {
        initComponents();
        k.connect();
        refreshTable();
        refreshComboMakanan();
        refreshComboMinuman();
        refreshComboKasir();
        refreshComboMeja();
    }

    class Transaksi extends FrameTransaksi{
        
        int id_transaksi, id_makanan, id_minuman, harga_makanan, harga_minuman, jumlah_makanan, jumlah_minuman, total_bayar, id_user, id_meja;
        String nama_pelanggan, tanggal, nama_makanan, nama_minuman, username, lokasi;

        public Transaksi() {
            this.nama_pelanggan = txtNamaPelanggan.getText();

            String combo = comboMakanan.getSelectedItem().toString();
            String[] arr = combo.split(":");
            this.id_makanan = Integer.parseInt(arr[0]);

            String combo1 = comboMinuman.getSelectedItem().toString();
            String[] arr1 = combo1.split(":");
            this.id_minuman = Integer.parseInt(arr1[0]);

            String combo2 = comboKasir.getSelectedItem().toString();
            String[] arr2 = combo2.split(":");
            this.id_user = Integer.parseInt(arr2[0]);

            String combo3 = comboMeja.getSelectedItem().toString();
            String[] arr3 = combo3.split(":");
            this.id_meja = Integer.parseInt(arr3[0]);
            this.lokasi = arr3[1];

            try {
                Date date = txtTanggal.getDate();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                this.tanggal = dateFormat.format(date);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

            this.nama_makanan = arr[1];
            this.nama_minuman = arr1[1];
            this.harga_makanan = Integer.parseInt(arr[2]);
            this.harga_minuman = Integer.parseInt(arr1[2]);
            this.username = arr2[1];

            this.jumlah_makanan = Integer.parseInt(txtJmlMakanan.getText());
            this.jumlah_minuman = Integer.parseInt(txtJmlMinuman.getText());

            this.total_bayar = (this.harga_makanan * this.jumlah_makanan) + (this.harga_minuman * this.jumlah_minuman);
        }
    }
    
    public void refreshTable() {
        model = (DefaultTableModel) tblTransaksi.getModel();
        model.setRowCount(0);

        model.setColumnIdentifiers(new Object[]{
            "ID TRX", "Kasir", "Nama Pelanggan", "Tanggal", "Nama Makanan",
            "Harga Makanan", "Jumlah Makanan", "Nama Minuman", "Harga Minuman",
            "Jumlah Minuman", "Total Bayar", "ID Meja", "Lokasi"
        });

        try {
            this.stat = k.getCon().prepareStatement("SELECT * FROM transaksi");
            this.rs = this.stat.executeQuery();
            while (rs.next()) {
                Object[] data = {
                    rs.getInt("id_transaksi"),
                    rs.getString("username"),
                    rs.getString("nama_pelanggan"),
                    rs.getString("date"),
                    rs.getString("nama_makanan"),
                    rs.getInt("harga_makanan"),
                    rs.getInt("jumlah_makanan"),
                    rs.getString("nama_minuman"),
                    rs.getInt("harga_minuman"),
                    rs.getInt("jumlah_minuman"),
                    rs.getInt("total_bayar"),
                    rs.getInt("id_meja"),
                    rs.getString("lokasi")
                };
                model.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        txtIdTransaksi.setText("");
        txtNamaPelanggan.setText("");
        txtJmlMakanan.setText("");
        txtJmlMinuman.setText("");
        txtTotalBayar.setText("");
    }


    
   public void refreshComboMakanan() {
       try {
          this.stat = k.getCon().prepareCall("select * from makanan "
                  + "where status='Tersedia'");
          this.rs = this.stat.executeQuery();
          while (rs.next()){
              comboMakanan.addItem(rs.getString("id_makanan")+":"+
              rs.getString("nama_makanan")+":"+
              rs.getString("harga"));
          }
       } catch (Exception e) {
       JOptionPane.showMessageDialog(null, e.getMessage());
       }
   }
   
      public void refreshComboMinuman() {
       try {
          this.stat = k.getCon().prepareCall("select * from minuman "
                  + "where status='Tersedia'");
          this.rs = this.stat.executeQuery();
          while (rs.next()){
              comboMinuman.addItem(rs.getString("id_minuman")+":"+
              rs.getString("nama_minuman")+":"+
              rs.getString("harga"));
          }
       } catch (Exception e) {
       JOptionPane.showMessageDialog(null, e.getMessage());
       }
   }

      public void refreshComboKasir() {
    try {
        this.stat = k.getCon().prepareCall("select * from user where id_level='2'");
        this.rs = this.stat.executeQuery();
        while (rs.next()) {
            comboKasir.addItem(rs.getString("id_user") + ":" + rs.getString("username"));
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
   }
   
   public void refreshComboMeja() {
        try {
            this.stat = k.getCon().prepareCall("select * from tempat where status='Tersedia'");
            this.rs = this.stat.executeQuery();
            while (rs.next()) {
                comboMeja.addItem(rs.getString("id_meja") + ":" +
                        rs.getString("lokasi") + ":" +
                        rs.getString("jumlah_kursi"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
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

        btnBack = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();
        txtTotalBayar = new javax.swing.JTextField();
        comboMinuman = new javax.swing.JComboBox<>();
        comboMakanan = new javax.swing.JComboBox<>();
        txtNamaPelanggan = new javax.swing.JTextField();
        txtTanggal = new com.toedter.calendar.JDateChooser();
        txtJmlMakanan = new javax.swing.JTextField();
        txtJmlMinuman = new javax.swing.JTextField();
        comboKasir = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtIdTransaksi = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        comboMeja = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBack.setBackground(new java.awt.Color(210, 224, 224));
        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBack.setText("Kembali");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        getContentPane().add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 90, 30));

        btnSave.setBackground(new java.awt.Color(101, 183, 65));
        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSave.setText("Simpan");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 340, -1, 30));

        btnUpdate.setBackground(new java.awt.Color(64, 162, 227));
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdate.setText("Edit");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 340, 80, 30));

        btnDelete.setBackground(new java.awt.Color(255, 0, 0));
        btnDelete.setText("Hapus");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 340, -1, 30));

        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTransaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTransaksi);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 900, 130));

        txtTotalBayar.setBackground(new java.awt.Color(64, 162, 227));
        txtTotalBayar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtTotalBayar.setEnabled(false);
        txtTotalBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalBayarActionPerformed(evt);
            }
        });
        getContentPane().add(txtTotalBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 290, 280, 30));

        comboMinuman.setBackground(new java.awt.Color(64, 162, 227));
        comboMinuman.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(comboMinuman, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 280, 30));

        comboMakanan.setBackground(new java.awt.Color(64, 162, 227));
        comboMakanan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(comboMakanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 280, 30));

        txtNamaPelanggan.setBackground(new java.awt.Color(64, 162, 227));
        txtNamaPelanggan.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtNamaPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaPelangganActionPerformed(evt);
            }
        });
        getContentPane().add(txtNamaPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, 280, 30));

        txtTanggal.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtTanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 280, 30));

        txtJmlMakanan.setBackground(new java.awt.Color(64, 162, 227));
        txtJmlMakanan.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtJmlMakanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 170, 280, 30));

        txtJmlMinuman.setBackground(new java.awt.Color(64, 162, 227));
        txtJmlMinuman.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtJmlMinuman, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, 280, 30));

        comboKasir.setBackground(new java.awt.Color(64, 162, 227));
        comboKasir.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(comboKasir, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 280, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("ID");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, -1, -1));

        txtIdTransaksi.setEnabled(false);
        getContentPane().add(txtIdTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 30, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Meja");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, -1, -1));

        comboMeja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMejaActionPerformed(evt);
            }
        });
        getContentPane().add(comboMeja, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 342, 120, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/TransaksiFrame.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTotalBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalBayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalBayarActionPerformed

    private void txtNamaPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaPelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaPelangganActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
    osepcafe.manager.ManagerDashboard dashboardFrame = new osepcafe.manager.ManagerDashboard();
    dashboardFrame.setVisible(true);
    this.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            Transaksi transaksi = new Transaksi();
            txtTotalBayar.setText("" + transaksi.total_bayar);
            this.stat = k.getCon().prepareStatement("INSERT INTO transaksi (nama_pelanggan, date, "
                    + "id_makanan, nama_makanan, harga_makanan, jumlah_makanan, id_minuman, nama_minuman, "
                    + "harga_minuman, jumlah_minuman, total_bayar, id_user, username, id_meja, lokasi) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            this.stat.setString(1, transaksi.nama_pelanggan);
            this.stat.setString(2, transaksi.tanggal);
            this.stat.setInt(3, transaksi.id_makanan);
            this.stat.setString(4, transaksi.nama_makanan);
            this.stat.setInt(5, transaksi.harga_makanan);
            this.stat.setInt(6, transaksi.jumlah_makanan);
            this.stat.setInt(7, transaksi.id_minuman);
            this.stat.setString(8, transaksi.nama_minuman);
            this.stat.setInt(9, transaksi.harga_minuman);
            this.stat.setInt(10, transaksi.jumlah_minuman);
            this.stat.setInt(11, transaksi.total_bayar);
            this.stat.setInt(12, transaksi.id_user);
            this.stat.setString(13, transaksi.username);
            this.stat.setInt(14, transaksi.id_meja);
            this.stat.setString(15, transaksi.lokasi);

            int pilihan = JOptionPane.showConfirmDialog(null,
                    "Tanggal: " + transaksi.tanggal +
                            "\nNama Kasir: " + transaksi.username +
                            "\nNama Pelanggan: " + transaksi.nama_pelanggan +
                            "\nMakanan: " + transaksi.jumlah_makanan + " " + transaksi.nama_makanan +
                            "\nMinuman: " + transaksi.jumlah_minuman + " " + transaksi.nama_minuman +
                            "\nMeja: " + transaksi.lokasi +
                            "\nTotal Bayar: " + transaksi.total_bayar + "\n",
                    "Tambahkan Transaksi ?",
                    JOptionPane.YES_NO_OPTION);
            if (pilihan == JOptionPane.YES_OPTION) {
                this.stat.executeUpdate();
                refreshTable();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
    try {
            Transaksi transaksi = new Transaksi();
            transaksi.id_transaksi = Integer.parseInt(txtIdTransaksi.getText());

            this.stat = k.getCon().prepareStatement("UPDATE transaksi SET nama_pelanggan = ?, date = ?, "
                    + "id_makanan = ?, nama_makanan = ?, harga_makanan = ?, jumlah_makanan = ?, "
                    + "id_minuman = ?, nama_minuman = ?, harga_minuman = ?, jumlah_minuman = ?, "
                    + "total_bayar = ?, id_user = ?, username = ?, id_meja = ?, lokasi = ? "
                    + "WHERE id_transaksi = ?");

            this.stat.setString(1, transaksi.nama_pelanggan);
            this.stat.setString(2, transaksi.tanggal);
            this.stat.setInt(3, transaksi.id_makanan);
            this.stat.setString(4, transaksi.nama_makanan);
            this.stat.setInt(5, transaksi.harga_makanan);
            this.stat.setInt(6, transaksi.jumlah_makanan);
            this.stat.setInt(7, transaksi.id_minuman);
            this.stat.setString(8, transaksi.nama_minuman);
            this.stat.setInt(9, transaksi.harga_minuman);
            this.stat.setInt(10, transaksi.jumlah_minuman);
            this.stat.setInt(11, transaksi.total_bayar);
            this.stat.setInt(12, transaksi.id_user);
            this.stat.setString(13, transaksi.username);
            this.stat.setInt(14, transaksi.id_meja);
            this.stat.setString(15, transaksi.lokasi);
            this.stat.setInt(16, transaksi.id_transaksi);

            this.stat.executeUpdate();
            refreshTable();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tblTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTransaksiMouseClicked
    int selectedRow = tblTransaksi.getSelectedRow();
    txtIdTransaksi.setText(model.getValueAt(selectedRow, 0).toString());
    txtNamaPelanggan.setText(model.getValueAt(selectedRow, 2).toString());
    txtJmlMakanan.setText(model.getValueAt(selectedRow, 6).toString());
    txtJmlMinuman.setText(model.getValueAt(selectedRow, 9).toString());
    txtTotalBayar.setText(model.getValueAt(selectedRow, 10).toString());
    }//GEN-LAST:event_tblTransaksiMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try {
         Transaksi transaksi = new Transaksi();
        int id_transaksi = Integer.parseInt(txtIdTransaksi.getText());

        this.stat = k.getCon().prepareStatement("DELETE FROM transaksi WHERE id_transaksi = ?");
        this.stat.setInt(1, id_transaksi);

        this.stat.executeUpdate();
        refreshTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void comboMejaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMejaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboMejaActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> comboKasir;
    private javax.swing.JComboBox<String> comboMakanan;
    private javax.swing.JComboBox<String> comboMeja;
    private javax.swing.JComboBox<String> comboMinuman;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTransaksi;
    public javax.swing.JTextField txtIdTransaksi;
    private javax.swing.JTextField txtJmlMakanan;
    private javax.swing.JTextField txtJmlMinuman;
    private javax.swing.JTextField txtNamaPelanggan;
    private com.toedter.calendar.JDateChooser txtTanggal;
    private javax.swing.JTextField txtTotalBayar;
    // End of variables declaration//GEN-END:variables
}
