/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.veritabaniproje;

/**
 *
 * @author erkan
 */
public class VeriTabaniProje {
    
    static Customer customer;
    public VeriTabaniProje(){
        DBConnection.connect();
    }

    public static void main(String[] args) {
        
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true);
    }
}
