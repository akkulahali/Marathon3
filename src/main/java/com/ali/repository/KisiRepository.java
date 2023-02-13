package com.ali.repository;

import com.ali.Utility.MyFactoryRepository;
import com.ali.repository.entity.Kisi;

public class KisiRepository extends MyFactoryRepository<Kisi,Long> {
    public KisiRepository() {
        super(new Kisi());
    }
}
