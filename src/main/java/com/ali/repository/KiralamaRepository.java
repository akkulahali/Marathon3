package com.ali.repository;

import com.ali.Utility.MyFactoryRepository;
import com.ali.repository.entity.Kiralama;

public class KiralamaRepository extends MyFactoryRepository<Kiralama,Long> {
    public KiralamaRepository() {
        super(new Kiralama());
    }
}
