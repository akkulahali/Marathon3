package com.ali.service;

import com.ali.Utility.MyFactoryService;
import com.ali.repository.KiralamaRepository;
import com.ali.repository.entity.Kiralama;

import java.util.List;

public class KiralamaService extends MyFactoryService<KiralamaRepository, Kiralama, Long> {
    public KiralamaService() {
        super(new KiralamaRepository());
    }

    public List<Kiralama> musterininKiraladigiAraclar(Long id){
        return getRepository().musterininKiraladigiAraclar(id);
    }
}
