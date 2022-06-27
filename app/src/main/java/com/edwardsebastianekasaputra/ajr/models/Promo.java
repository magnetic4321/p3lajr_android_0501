package com.edwardsebastianekasaputra.ajr.models;

public class Promo {

    Long id;
    String kode;
    String keterangan;
    int diskon;

    public Promo(Long id, String kode, String keterangan, int diskon) {
        this.id = id;
        this.kode = kode;
        this.keterangan = keterangan;
        this.diskon = diskon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public int getDiskon() {
        return diskon;
    }

    public void setDiskon(int diskon) {
        this.diskon = diskon;
    }
}
