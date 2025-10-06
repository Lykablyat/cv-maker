package org.example;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Image;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.borders.Border;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String PDF_ADI = "ozgecmis.pdf";

        KisiselBilgi kisi = new KisiselBilgi(
                "Emre Düzcan",
                "duzcanemre@gmail.com",
                "+90 534 019 89 08",
                "Kırklareli / Türkiye",
                "Ne yapsam hata çıkıyor. Hatayı düzeltiyorum daha fazla hata çıkıyor. Hata çıkmayana kadar düzeltmeye devam. Hatalar elbet bir gün çıkmaktan yorulacak ama ben düzeltmekten yorulmayacağım."
        );

        List<EgitimBilgisi> egitimListesi = new ArrayList<>();
        egitimListesi.add(new EgitimBilgisi(
                "Kim Bilir Üniversitesi", "Yalan Mühendisliği", "Lisans",
                "Eylül 2015", "Haziran 2019", "3.45 / 4.00"
        ));
        egitimListesi.add(new EgitimBilgisi(
                "ChatGPT Üniversitesi", "Yapay Zekaya Kod Yazdırma Mühendisliği", "Arka Lisans",
                "Eylül 2020", "Haziran 2022", "3.80 / 4.00"
        ));

        List<String> beceriler = Arrays.asList(
                "LoL oynama",
                "Github'dan KubeJS kodu kopyalama",
                "İngilizce videoları 2x hızda izlerken anlama",
                "Whatsapptan arkadaşlarına ödevin ayrıntılarını sorma",
                "Umarım bu dönemin sonuna kadar Nesneye Yönelik Programlamayı çözme"
        );

        Ozgecmis ozgecmis = new Ozgecmis(kisi, egitimListesi, beceriler);

        ozgecmis.deneyimEkle(new IsDeneyimi(
                "Üst Düzey Boş Yapıcı", "Biz Gerçek Bir Şirket Değiliz A.Ş.",
                "Haziran 2003", "Halen",
                "Ben bu dünyaya her fırsatta aklıma geleni söyleyerek geldim her fırsatta aklıma geleni söyleyerek gideceğim."
        ));
        ozgecmis.deneyimEkle(new IsDeneyimi(
                "Baş Teknisyenin Arkadaşı", "Netron Bilişim",
                "Şubat 2024", "Şubat 2024",
                "Bilgisayar bakımı, temizliği, hata tespiti ve giderimiyle uğraştım. Mikroskopla anakart falan inceleyip bozuk bir monitörü parçalarına ayırıp katmanlarını gördüm. Keyifliydi."
        ));
        ozgecmis.deneyimEkle(new IsDeneyimi(
                "Çay Taşıyıcı", "Ruh Sömüren Dev Şirket LLC",
                "Temmuz 2019", "Eylül 2019",
                "İki oda arasında aynı çay bardağını ileri ve geri taşıdım."
        ));


        try {
            PdfWriter writer = new PdfWriter(PDF_ADI);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            PdfFont turkceFont = null;
            String fontPath = "src/main/resources/arial.ttf";

            try {
                PdfFontFactory.register(fontPath, "arial-turkce");
                turkceFont = PdfFontFactory.createRegisteredFont("arial-turkce", PdfEncodings.IDENTITY_H);
            } catch (IOException e) {
                System.err.println("Uyarı: Arial fontu yüklenemedi. Standart Helvetica kullanılıyor.");
                turkceFont = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            }

            if (turkceFont != null) {
                document.setFont(turkceFont);
            }

            Image profilResmi = new Image(ImageDataFactory.create("src/main/resources/ozgecmis_foto.png"));
            profilResmi.setWidth(80f);
            profilResmi.setHeight(80f);

            Table baslikTablosu = new Table(new float[]{80f, 400f});
            KisiselBilgi k = ozgecmis.getKisiselBilgi();

            com.itextpdf.layout.element.Cell resimHucresi = new com.itextpdf.layout.element.Cell().add(profilResmi);
            resimHucresi.setBorder(Border.NO_BORDER);
            resimHucresi.setPadding(0);
            baslikTablosu.addCell(resimHucresi);

            com.itextpdf.layout.element.Cell yaziHucresi = new com.itextpdf.layout.element.Cell();
            yaziHucresi.setBorder(Border.NO_BORDER);
            yaziHucresi.setPadding(0);

            yaziHucresi.add(new Paragraph(k.getAdSoyad())
                    .setFontSize(24).setBold()
                    .setMarginTop(0).setMarginBottom(2)
            );

            String iletisim = k.getEposta() + " | " + k.getTelefon() + " | " + k.getAdres();
            yaziHucresi.add(new Paragraph(iletisim)
                    .setFontSize(10).setMarginTop(0).setMarginBottom(0)
            );

            baslikTablosu.addCell(yaziHucresi);
            baslikTablosu.setBorder(Border.NO_BORDER);

            document.add(baslikTablosu.setMarginBottom(30));

            document.add(new Paragraph("PROFİL ÖZETİ")
                    .setFontSize(14).setBold().setUnderline());
            document.add(new Paragraph(k.getProfilOzeti())
                    .setFontSize(12).setMarginBottom(30));

            document.add(new Paragraph("İŞ DENEYİMİ")
                    .setFontSize(14).setBold().setUnderline().setMarginBottom(10));

            for (IsDeneyimi deneyim : ozgecmis.getIsDeneyimleri()) {
                document.add(new Paragraph(deneyim.getPozisyon() + " @ " + deneyim.getSirketAdi())
                        .setFontSize(12).setBold().setMarginBottom(0));
                document.add(new Paragraph(deneyim.getBaslangicTarihi() + " - " + deneyim.getBitisTarihi())
                        .setFontSize(10).setItalic().setMarginTop(0).setMarginBottom(5));

                com.itextpdf.layout.element.List gorevListesi = new com.itextpdf.layout.element.List();
                String[] gorevler = deneyim.getGorevTanimi().split("\\. ");
                for (String gorev : gorevler) {
                    gorevListesi.add(new com.itextpdf.layout.element.ListItem(gorev.trim() + "."));
                }
                document.add(gorevListesi.setFontSize(11).setMarginBottom(15));
            }

            document.add(new Paragraph("EĞİTİM BİLGİSİ")
                    .setFontSize(14).setBold().setUnderline().setMarginBottom(10));

            for (EgitimBilgisi egitim : ozgecmis.getEgitimBilgileri()) {
                document.add(new Paragraph(egitim.getDerece() + " - " + egitim.getBolum())
                        .setFontSize(12).setBold().setMarginBottom(0));
                document.add(new Paragraph(egitim.getOkulAdi())
                        .setFontSize(11).setMarginTop(0).setMarginBottom(0));
                document.add(new Paragraph(egitim.getBaslangicTarihi() + " - " + egitim.getBitisTarihi() + " | Ort: " + egitim.getNotOrtalamasi())
                        .setFontSize(10).setItalic().setMarginTop(0).setMarginBottom(10));
            }

            document.add(new Paragraph("BECERİLER")
                    .setFontSize(14).setBold().setUnderline().setMarginBottom(10));

            com.itextpdf.layout.element.List beceriListesi = new com.itextpdf.layout.element.List();
            for (String beceri : ozgecmis.getBeceriler()) {
                beceriListesi.add(new com.itextpdf.layout.element.ListItem(beceri));
            }
            document.add(beceriListesi.setFontSize(11).setMarginBottom(10));

            document.close();

            System.out.println("PDF basariyla olusturuldu: " + PDF_ADI);

        } catch (FileNotFoundException e) {
            System.err.println("Hata: Fotoğraf dosyası bulunamadi. 'src/main/resources/ozgecmis_foto.png' dosya yolu ve ismini kontrol edin.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Hata: IO sorunu veya font yüklenirken bir sorun oluştu. Detay: " + e.getMessage());
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}