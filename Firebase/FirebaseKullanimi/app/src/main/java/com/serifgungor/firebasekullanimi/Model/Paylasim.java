package com.serifgungor.firebasekullanimi.Model;

public class Paylasim {
    private String icerik;
    private String uid;
    private String tarih;

    public Paylasim() {
    }

    public Paylasim(String icerik, String uid, String tarih) {
        this.icerik = icerik;
        this.uid = uid;
        this.tarih = tarih;
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
