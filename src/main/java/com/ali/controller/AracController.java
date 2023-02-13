package com.ali.controller;

import com.ali.repository.entity.Arac;
import com.ali.service.AracService;

import java.util.Scanner;

public class AracController {
    private Scanner scanner;
    private AracService aracService;

    public AracController() {
        this.aracService = new AracService();
    }

    public void aracPage(){
        System.out.println("****************************");
        System.out.println("*****   ARAC SAYFASI   *****");
        System.out.println("****************************");
        int secim;
        do{
            System.out.println("");
            System.out.println("1- Arac Kaydet");
            System.out.println("2- Tum Araclari Goster");
            System.out.println("0- <<< Geri Dön");
            System.out.println("Seçiniz......: ");
            secim = Integer.parseInt(ifade());
            switch (secim){
                case 1: createArac(); break;
                case 2: listArac(); break;
            }
        }while(secim!=0);
    }

    private String ifade(){
        this.scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void createArac() {
        System.out.println("**********************************");
        System.out.println("********   Arac Olustur   ********");
        System.out.println("**********************************");
        System.out.println("Arac markasini giriniz:");
        String marka = ifade();
        System.out.println("Arac modelini giriniz:");
        String model = ifade();
        Arac arac = Arac.builder()
                .model(model)
                .marka(marka)
                .build();
        aracService.save(arac);
    }

    private void listArac() {
        aracService.findAll().forEach(a->{
            System.out.println("Arac id.......: " + a.getId());
            System.out.println("Arac marka....: " + a.getMarka());
            System.out.println("Arac model....: " + a.getModel());
            System.out.println("Arac müsait mi: " + a.isMusaitmi());
            System.out.println("------------------------------------");
        });
    }
}