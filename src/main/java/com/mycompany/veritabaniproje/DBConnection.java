/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.veritabaniproje;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.swing.JOptionPane;
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
    
    static boolean checkUserWithTc (String TcNo){
        String sqlQuery = "select * from musteri where TcNo='"+TcNo+"'";
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlQuery);
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,null,e);
            return false;
        }
        return false;
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
             JOptionPane.showMessageDialog(null, e);
             Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,null,e);
           
        }
    }
    
    
    static void insertIslem( String tapuno,String alıcı,String satıcı,String fiyat,Integer ekstra,String toplam ){
        String query = "insert into islem(id,tapuno,alıcı,satıcı,fiyat,ekstra,toplam,tarih) values(?,?,?,?,?,?,?,?)";
        String sqlquery = "select nextval ('idseq'); ";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date(System.currentTimeMillis()); 
        long millis = date.getTime();
        Timestamp timestamp = new Timestamp(millis);
        System.out.println(date);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            ResultSet seq = preparedStatement.executeQuery();
            Integer id = -1 ;
            if(seq.next()){
                id = seq.getInt(1);
            }
            PreparedStatement preparedStatement2 = connection.prepareStatement(query);
            preparedStatement2.setInt(1, id);
            preparedStatement2.setString(2, tapuno);
            preparedStatement2.setString(3, alıcı);
            preparedStatement2.setString(4, satıcı);
            preparedStatement2.setString(5, fiyat);
            preparedStatement2.setInt(6, ekstra);
            preparedStatement2.setString(7, toplam);
            preparedStatement2.setTimestamp(8, timestamp);
            preparedStatement2.executeUpdate();

            
        } catch (SQLException e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,null,e);
            
        }
         
         
         
       
    }
    
    static void checkTc(){
        String sqlQuery = "create or replace function tc_trig() "
                + " returns trigger as$$"
                + "begin"
                + "if(length(new.tcno) = 11) then"
                + "return new;"
                + "else"
                + "raise exception 'tc kimlik numarasinin 11 haneli olmasi gerek';"
                + "return null;"
                + "end if;"
                + "end;;"
                + "$$ language 'plpgsql';"
                + "create trigger tcCheck"
                + "before insert"
                + "on musteri"
                + "for each row execute procedure tc_trig();";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        } catch (SQLException e) {
            
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,null,e);

        }
        
        
            
    }
    static void dropTriggersTC (){
        String sqlquery = "drop trigger tcCheck on musteri;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
        } catch (SQLException e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,null,e);
        }
        
        String query = "drop function tc_check();";
        try {
             PreparedStatement preparedStatement1 = connection.prepareStatement(query);
        } catch (SQLException e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,null,e);
        }
       
        
        
    }
        static void checkTel(){
        String sqlQuery = "create or replace function tel_trig() "
                + " returns trigger as ' "
                + "begin "
                + "if(length(new.telefon) = 10) then "
                + "return new; "
                + "else "
                + "raise exception ''telefon numarasinin 10 haneli olmasi gerek''; "
                + "return null; "
                + "end if; "
                + "end; "
                + "' language 'plpgsql'; "
                + "create trigger telCheck "
                + "before insert "
                + "on musteri "
                + "for each row execute procedure tel_trig();";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,null,e);

        }
        
        
            
    }
    
   
    
              
    }
    
        

        
    

