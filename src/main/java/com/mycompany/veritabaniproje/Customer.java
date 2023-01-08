/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.veritabaniproje;

/**
 *
 * @author erkan
 */
public class Customer { 
       
    private String ad;
    private String soyad;
    private String sifre;
    private String tcno;
    private String telefon;
    
    public Customer(String ad,String soyad,String sifre, String tcno,String telefon){
        this.ad=ad;
        this.soyad=soyad;
        this.sifre= sifre;
        this.tcno=tcno;
        this.telefon=telefon;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getTcno() {
        return tcno;
    }

    public void setTcno(String tcno) {
        this.tcno = tcno;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    
    
}
