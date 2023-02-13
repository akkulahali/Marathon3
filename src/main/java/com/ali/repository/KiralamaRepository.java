package com.ali.repository;

import com.ali.Utility.MyFactoryRepository;
import com.ali.repository.entity.Kiralama;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class KiralamaRepository extends MyFactoryRepository<Kiralama,Long> {
    public KiralamaRepository() {
        super(new Kiralama());
    }

    public List<Kiralama> musterininKiraladigiAraclar(Long id){
        CriteriaQuery<Kiralama> criteria = getCriteriaBuilder().createQuery(Kiralama.class);
        Root<Kiralama> root = criteria.from(Kiralama.class);
        criteria.select(root).where(getCriteriaBuilder().equal(root.get("kisiId"),id));
        return getEntityManager().createQuery(criteria).getResultList();
    }
}
