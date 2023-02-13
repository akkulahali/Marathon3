package com.ali.repository;

import com.ali.Utility.MyFactoryRepository;
import com.ali.repository.entity.Arac;
import com.ali.repository.entity.Kiralama;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AracRepository extends MyFactoryRepository<Arac,Long> {
    public AracRepository() {
        super(new Arac());
    }

    public List<Arac> musterininKiraladigiAraclar(List<Kiralama> musterininKiraladigiAraclarIdleri) {
        CriteriaQuery<Arac> criteria = getCriteriaBuilder().createQuery(Arac.class);
        Root<Arac> root = criteria.from(Arac.class);
        List<Long> aracIdList = new ArrayList<>();
        musterininKiraladigiAraclarIdleri.forEach(x->{
            aracIdList.add(x.getAracId());
        });
        criteria.select(root).where(getCriteriaBuilder().in(root.get("id")).value(aracIdList));
        return getEntityManager().createQuery(criteria).getResultList();
    }
}
