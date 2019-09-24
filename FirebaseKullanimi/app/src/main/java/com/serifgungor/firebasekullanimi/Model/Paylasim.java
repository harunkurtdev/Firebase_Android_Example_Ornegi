package com.serifgungor.firebasekullanimi.Model;

public class Paylasim {
    private String icerik;
    private String uid;
    private String tarih;
    private String paylasimId;

    public Paylasim() {
    }

    public Paylasim(String icerik, String uid, String tarih, String paylasimId) {
        this.icerik = icerik;
        this.uid = uid;
        this.tarih = tarih;
        this.paylasimId = paylasimId;
    }

    public String getPaylasimId() {
        return paylasimId;
    }

    public void setPaylasimId(String paylasimId) {
        this.paylasimId = paylasimId;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }
}
