/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.veritabaniproje;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.postgresql.ds.PGSimpleDataSource;

/**
 *
 * @author erkan
 */
public class DBConnection {
    static String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
    static Connection connection = null;
        static String username = "postgres";
    static String password = "1234";
    
    
    static void connect(){
        try{
            connection = DriverManager.getConnection(jdbcUrl,username,password);
            System.out.println("Connected to DB Server");
            
        }catch(SQLException e){
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    
    static ResultSet list(String query){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,null,e);
            return null;
        }
    }
    
    static boolean checkUserExists(String TcNo,String Sifre){
        String sqlQuery = "select * from musteri where TcNo='"+TcNo+"'and Sifre='"+Sifre+"'"; 
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,null,e);
            return  false;
            
        }
        return false;
        
    }
    
    static void insertUser (String TcNo,String Ad,String Soyad,String Sifre,String Telefon){
        String sqlQuery = "insert into musteri(tcno,ad,soyad,sifre,telefon) values (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, TcNo);
            preparedStatement.setString(2, Ad);
            preparedStatement.setString(3, Soyad);
            preparedStatement.setString(4, Sifre);
            preparedStatement.setString(5, Telefon);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
             Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,null,e);
           
        }
    }
    
        
}
        
    

