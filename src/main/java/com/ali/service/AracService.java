package com.ali.service;

import com.ali.Utility.MyFactoryService;
import com.ali.repository.AracRepository;
import com.ali.repository.entity.Arac;

public class AracService extends MyFactoryService<AracRepository, Arac,Long> {
    public AracService() {
        super(new AracRepository());
    }
}
