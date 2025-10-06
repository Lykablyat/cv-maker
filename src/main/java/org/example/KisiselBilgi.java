package org.example;

public class KisiselBilgi {
    private String adSoyad;
    private String eposta;
    private String telefon;
    private String adres;
    private String profilOzeti;

    public KisiselBilgi(String adSoyad, String eposta, String telefon, String adres, String profilOzeti) {
        this.adSoyad = adSoyad;
        this.eposta = eposta;
        this.telefon = telefon;
        this.adres = adres;
        this.profilOzeti = profilOzeti;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public String getEposta() {
        return eposta;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getAdres() {
        return adres;
    }

    public String getProfilOzeti() {
        return profilOzeti;
    }
}