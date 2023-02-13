package com.ali.service;

import com.ali.Utility.MyFactoryService;
import com.ali.repository.KiralamaRepository;
import com.ali.repository.entity.Kiralama;

public class KiralamaService extends MyFactoryService<KiralamaRepository, Kiralama, Long> {
    public KiralamaService() {
        super(new KiralamaRepository());
    }
}
