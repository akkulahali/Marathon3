package com.ali;

import com.ali.controller.AracController;
import com.ali.controller.KiralamaController;
import com.ali.controller.KisiController;
import com.ali.repository.entity.Arac;
import com.ali.service.AracService;

import java.util.Scanner;

public class KiralamaApp {
    public static void main(String[] args) {
        int secim;
        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println("******************************************");
            System.out.println("*******  ARAC KIRALAMA UYGULAMASI  *******");
            System.out.println("******************************************");
            System.out.println();
            System.out.println("1- Arac Menusu");
            System.out.println("2- Musteri Menusu");
            System.out.println("3- Kiralama Menusu");
            System.out.println("4- Raporlar");
            System.out.println("0- CIKIS YAP");
            System.out.print("Seciniz....: ");
            secim = scanner.nextInt();
            switch (secim){
                case 1: new AracController().aracPage(); break;
                case 2: new KisiController().kisiPage(); break;
                case 3: new KiralamaController().kiralamaPage(); break;
                case 4: new KiralamaController().raporMenu(); break;
            }
        }while (secim!=0);
        System.out.println("TEKRAR GÖRÜSMEK ÜZERE");
    }
}