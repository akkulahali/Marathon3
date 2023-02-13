package com.ali.controller;

import com.ali.repository.entity.Arac;
import com.ali.repository.entity.Kisi;
import com.ali.service.KisiService;

import java.util.Scanner;

public class KisiController {
    private Scanner scanner;
    private KisiService kisiService;

    public KisiController() {
        this.kisiService = new KisiService();
    }
    
    public void kisiPage(){
        System.out.println("****************************");
        System.out.println("*****   KISI SAYFASI   *****");
        System.out.println("****************************");
        int secim;
        do{
            System.out.println("");
            System.out.println("1- Kisi Kaydet");
            System.out.println("2- Tum Kisileri Goster");
            System.out.println("0- <<< Geri Dön");
            System.out.println("Seçiniz......: ");
            secim = Integer.parseInt(ifade());
            switch (secim){
                case 1: createKisi(); break;
                case 2: listKisi(); break;
            }
        }while(secim!=0);
    }

    private void listKisi() {
        kisiService.findAll().forEach(k->{
            System.out.println("Kisi id.......: " + k.getId());
            System.out.println("Kisi adi......: " + k.getAd());
            System.out.println("Kisi soyadi...: " + k.getSoyad());
            System.out.println("------------------------------------");
        });
    }

    private void createKisi() {
        System.out.println("**********************************");
        System.out.println("********   Kisi Olustur   ********");
        System.out.println("**********************************");
        System.out.println("Kisi adini giriniz:");
        String ad = ifade();
        System.out.println("Kisi soyadini giriniz:");
        String soyad = ifade();
        Kisi kisi = Kisi.builder()
                .ad(ad)
                .soyad(soyad)
                .build();
        kisiService.save(kisi);
    }

    private String ifade(){
        this.scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
