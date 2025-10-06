package org.example;

public class EgitimBilgisi {
    private String okulAdi;
    private String bolum;
    private String derece;
    private String baslangicTarihi;
    private String bitisTarihi;
    private String notOrtalamasi;

    public EgitimBilgisi(String okulAdi, String bolum, String derece, String baslangicTarihi, String bitisTarihi, String notOrtalamasi) {
        this.okulAdi = okulAdi;
        this.bolum = bolum;
        this.derece = derece;
        this.baslangicTarihi = baslangicTarihi;
        this.bitisTarihi = bitisTarihi;
        this.notOrtalamasi = notOrtalamasi;
    }

    public String getOkulAdi() {
        return okulAdi;
    }

    public String getBolum() {
        return bolum;
    }

    public String getDerece() {
        return derece;
    }

    public String getBaslangicTarihi() {
        return baslangicTarihi;
    }

    public String getBitisTarihi() {
        return bitisTarihi;
    }

    public String getNotOrtalamasi() {
        return notOrtalamasi;
    }
}