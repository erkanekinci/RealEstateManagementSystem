/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.veritabaniproje;

import static com.mycompany.veritabaniproje.DBConnection.connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author erkan
 */
public class VeriTabaniProje {
    
    static Customer customer;
    public VeriTabaniProje(){
        DBConnection.connect();
    }
    
    
    public static void sequence(){
        String query = "create sequence if not exists idseq as int start with 1 increment by 1;";
        try {
            DBConnection.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException e) {
             Logger.getLogger(VeriTabaniProje.class.getName()).log(Level.SEVERE,null,e);
            
        }
    }

    public static void main(String[] args) {
        sequence();
        LoginPage loginPage = new LoginPage();
        loginPage.pack();
        loginPage.setLocationRelativeTo(null);
        loginPage.setVisible(true);
    }
}
