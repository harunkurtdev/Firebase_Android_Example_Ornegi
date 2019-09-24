package com.serifgungor.firebasedatabaseornegi;

public class User {
    private String email;
    private String sifre;
    private String ad;
    private String soyad;

    public User(){}
    public User(String email, String sifre, String ad, String soyad) {
        this.email = email;
        this.sifre = sifre;
        this.ad = ad;
        this.soyad = soyad;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSifre() {
        return sifre;
    }
    public void setSifre(String sifre) {
        this.sifre = sifre;
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
}