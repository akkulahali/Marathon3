package com.ali.service;

import com.ali.Utility.MyFactoryService;
import com.ali.repository.AracRepository;
import com.ali.repository.entity.Arac;
import com.ali.repository.entity.Kiralama;

import java.util.List;

public class AracService extends MyFactoryService<AracRepository, Arac,Long> {
    public AracService() {
        super(new AracRepository());
    }

    public List<Arac> musterininKiraladigiAraclar(List<Kiralama> musterininKiraladigiAraclarIdleri) {
        return getRepository().musterininKiraladigiAraclar(musterininKiraladigiAraclarIdleri);
    }
}
