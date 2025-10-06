package org.example;

import java.util.List;
import java.util.ArrayList;

public class Ozgecmis {
    private KisiselBilgi kisiselBilgi;
    private List<IsDeneyimi> isDeneyimleri;
    private List<EgitimBilgisi> egitimBilgileri;
    private List<String> beceriler;

    public Ozgecmis(KisiselBilgi kisiselBilgi, List<EgitimBilgisi> egitimBilgileri, List<String> beceriler) {
        this.kisiselBilgi = kisiselBilgi;
        this.egitimBilgileri = egitimBilgileri;
        this.beceriler = beceriler;
        this.isDeneyimleri = new ArrayList<>();
    }

    public void deneyimEkle(IsDeneyimi deneyim) {
        this.isDeneyimleri.add(deneyim);
    }

    public KisiselBilgi getKisiselBilgi() {
        return kisiselBilgi;
    }

    public List<IsDeneyimi> getIsDeneyimleri() {
        return isDeneyimleri;
    }

    public List<EgitimBilgisi> getEgitimBilgileri() {
        return egitimBilgileri;
    }

    public List<String> getBeceriler() {
        return beceriler;
    }
}