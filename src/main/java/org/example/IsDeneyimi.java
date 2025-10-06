package org.example;

public class IsDeneyimi {
    private String pozisyon;
    private String sirketAdi;
    private String baslangicTarihi;
    private String bitisTarihi;
    private String gorevTanimi;

    public IsDeneyimi(String pozisyon, String sirketAdi, String baslangicTarihi, String bitisTarihi, String gorevTanimi) {
        this.pozisyon = pozisyon;
        this.sirketAdi = sirketAdi;
        this.baslangicTarihi = baslangicTarihi;
        this.bitisTarihi = bitisTarihi;
        this.gorevTanimi = gorevTanimi;
    }

    public String getPozisyon() {
        return pozisyon;
    }

    public String getSirketAdi() {
        return sirketAdi;
    }

    public String getBaslangicTarihi() {
        return baslangicTarihi;
    }

    public String getBitisTarihi() {
        return bitisTarihi;
    }

    public String getGorevTanimi() {
        return gorevTanimi;
    }
}