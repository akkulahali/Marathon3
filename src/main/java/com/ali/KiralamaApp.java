package com.ali;

import com.ali.repository.entity.Arac;
import com.ali.service.AracService;

public class KiralamaApp {
    public static void main(String[] args) {
        Arac arac = Arac.builder()
                .marka("BMW")
                .model("520d")
                .musaitmi(true)
                .build();
        new AracService().save(arac);
    }
}