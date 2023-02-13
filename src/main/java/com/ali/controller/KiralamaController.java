package com.ali.controller;

import com.ali.repository.entity.Arac;
import com.ali.repository.entity.Kiralama;
import com.ali.repository.entity.Kisi;
import com.ali.service.AracService;
import com.ali.service.KiralamaService;
import com.ali.service.KisiService;

import java.util.Scanner;

public class KiralamaController {
    private Scanner scanner;
    private KiralamaService kiralamaService;
    KisiService kisiService;
    AracService aracService;

    public KiralamaController() {
        this.kiralamaService = new KiralamaService();
        this.kisiService = new KisiService();
        this.aracService = new AracService();
    }

    public void kiralamaPage(){
        System.out.println("****************************");
        System.out.println("****  KIRALAMA SAYFASI  ****");
        System.out.println("****************************");
        int secim;
        do{
            System.out.println("");
            System.out.println("1- Kiralama Yap");
            System.out.println("2- Kiralamayi Bitir");
            System.out.println("3- Tum Kiralamalari Goster");
            System.out.println("0- <<< Geri Dön");
            System.out.println("Seçiniz......: ");
            secim = Integer.parseInt(ifade());
            switch (secim){
                case 1: kiralamaYap(); break;
                case 2: kiralamayiBitir(); break;
                case 3: listKiralama(); break;
            }
        }while(secim!=0);
    }

    private void kiralamayiBitir() {
        boolean control = true;
        do{
            kiralamaService.findAll().forEach(k->{
                if(k.isKiralamaAktifmi()){
                    System.out.println("Kiralama id.......: " + k.getId());
                    System.out.println("Kiralayan kisi....: Id -> " + k.getKisi().getId() + " Ad -> " + k.getKisi().getAd() + " Soyad -> " + k.getKisi().getSoyad());
                    System.out.println("Kiralanan arac....: Id -> " + k.getArac().getId() + " Marka -> " + k.getArac().getMarka() + " Model -> " +  k.getArac().getModel());
                    System.out.println("------------------------------------");
                }
            });
            System.out.println("Bitirilecek kiralama id'sini giriniz.");
            Long kiralamaId = Long.parseLong(ifade());
            if(kiralamaService.findById(kiralamaId).get().isKiralamaAktifmi()){
                kiralamaService.findById(kiralamaId).get().setKiralamaAktifmi(false);
                control = false;
            }else {
                System.out.println("Lutfen sonlanmamis kiralama id'si seciniz.");
            }
        }while (control);
    }

    private void listKiralama() {
        kiralamaService.findAll().forEach(k->{
            System.out.println("Kiralama id.......: " + k.getId());
            System.out.println("Kiralayan kisi....: Id -> " + k.getKisi().getId() + " Ad -> " + k.getKisi().getAd() + " Soyad -> " + k.getKisi().getSoyad());
            System.out.println("Kiralanan arac....: Id -> " + k.getArac().getId() + " Marka -> " + k.getArac().getMarka() + " Model -> " +  k.getArac().getModel());
            System.out.println("------------------------------------");
        });
    }

    private void kiralamaYap() {
        System.out.println("**********************************");
        System.out.println("********   Kiralama Yap   ********");
        System.out.println("**********************************");
        kisiService.findAll().forEach(k->{
            System.out.println("Kisi id.......: " + k.getId());
            System.out.println("Kisi adi......: " + k.getAd());
            System.out.println("Kisi soyadi...: " + k.getSoyad());
            System.out.println("------------------------------------");
        });
        System.out.println("Kişi id'sini giriniz.");
        Kisi kisi = kisiService.findById(Long.parseLong(ifade())).get();
        boolean control = true;
        do{
            aracService.findAll().forEach(a->{
                if(a.isMusaitmi()) {
                    System.out.println("Arac id.......: " + a.getId());
                    System.out.println("Arac marka....: " + a.getMarka());
                    System.out.println("Arac model....: " + a.getModel());
                    System.out.println("Arac müsait mi: " + a.isMusaitmi());
                    System.out.println("------------------------------------");
                }
            });
            System.out.println("Arac id'sini giriniz.");
            Long aracid = Long.parseLong(ifade());
            if (aracService.findById(aracid).get().isMusaitmi()) {
                Arac arac = aracService.findById(aracid).get();
                Kiralama kiralama = Kiralama.builder()
                        .arac(arac)
                        .kisi(kisi)
                        .build();
                kiralamaService.save(kiralama);
                arac.setMusaitmi(false);
                control = false;
            }else {
                System.out.println("Lutfen musait olan arac id'si seciniz.");
            }
        }while(control);
    }

    private String ifade(){
        this.scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
