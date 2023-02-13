package com.ali.repository;

import com.ali.Utility.MyFactoryRepository;
import com.ali.repository.entity.Arac;

public class AracRepository extends MyFactoryRepository<Arac,Long> {
    public AracRepository() {
        super(new Arac());
    }
}
