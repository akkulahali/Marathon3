package com.ali.service;

import com.ali.Utility.MyFactoryService;
import com.ali.repository.KisiRepository;
import com.ali.repository.entity.Kisi;

public class KisiService extends MyFactoryService<KisiRepository, Kisi, Long> {
    public KisiService() {
        super(new KisiRepository());
    }
}
