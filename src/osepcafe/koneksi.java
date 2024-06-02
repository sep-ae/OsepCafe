package osepcafe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class koneksi {
 private String url="jdbc:mysql://localhost/db_cafe";
 private String username_xampp="root";
 private String password_xampp="";
 private Connection con;
 
 public void connect() {
     try {
         con = DriverManager.getConnection(url, username_xampp, password_xampp);
     } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
     }
 }
 public Connection getCon(){
     return con;
 }
}