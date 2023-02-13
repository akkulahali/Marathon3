package com.ali.Utility;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MyFactoryRepository<T, ID> implements ICrud<T, ID> {
    private Session ss;
    private Transaction tt;
    private T t;
    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;

    public MyFactoryRepository(T t){
        entityManager = HibernateUtility.getSessionFactory().createEntityManager();
        criteriaBuilder = entityManager.getCriteriaBuilder();
        this.t = t;
    }
    public void openSession(){
        ss = HibernateUtility.getSessionFactory().openSession();
        tt = ss.beginTransaction();
    }
    public void closeSession(){
        tt.commit();
        ss.close();
    }
    @Override
    public <S extends T> S save(S entity) {
        try {
            openSession();
            ss.save(entity);
            closeSession();
            return entity;
        }catch (Exception e){
            tt.rollback();
            throw e;
        }
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
        try {
            openSession();
            entities.forEach(entity -> {
                ss.save(entity);
            });
            closeSession();
            return entities;
        }catch (Exception e){
            tt.rollback();
            throw e;
        }
    }

    @Override
    public void delete(T entity) {
        try {
            openSession();
            ss.delete(entity);
            closeSession();
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public void deleteById(ID id) {
        try{
            CriteriaQuery<T> criteria = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
            Root<T> root = (Root<T>) criteria.from(t.getClass());
            criteria.select(root);
            criteria.where(criteriaBuilder.equal(root.get("id"),id));
            T deleteEntity = entityManager.createQuery(criteria).getSingleResult();
            openSession();
            ss.delete(deleteEntity);
            closeSession();
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Optional<T> findById(ID id) {
        CriteriaQuery<T> criteria = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
        Root<T> root = (Root<T>) criteria.from(t.getClass());
        criteria.select(root);
        criteria.where(criteriaBuilder.equal(root.get("id"),id));
        List<T> result = entityManager.createQuery(criteria).getResultList();
        if(result.isEmpty())
            return Optional.empty();
        return Optional.of(result.get(0));
    }

    @Override
    public boolean existsById(ID id) {
        try{
            CriteriaQuery<T> criteria = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
            Root<T> root = (Root<T>) criteria.from(t.getClass());
            criteria.select(root);
            criteria.where(criteriaBuilder.equal(root.get("id"),id));
            List<T> result = entityManager.createQuery(criteria).getResultList();
            return !result.isEmpty();
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<T> findAll() {
        CriteriaQuery<T> criteria = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
        Root<T> root = (Root<T>) criteria.from(t.getClass());
        criteria.select(root);
        List<T> result = entityManager.createQuery(criteria).getResultList();
        return result;
    }

    @Override
    public List<T> findAllByColumnNameAndValue(String columnName, String columnValue) {
        CriteriaQuery<T> criteria = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
        Root<T> root = (Root<T>) criteria.from(t.getClass());
        criteria.select(root);
        criteria.where(criteriaBuilder.equal(root.get(columnName),columnValue));
        List<T> result = entityManager.createQuery(criteria).getResultList();
        return result;
    }

    @Override
    public List<T> findByEntity(T entity) {
        List<T> result = null;
        Class cl = entity.getClass();
        Field[] fl = cl.getDeclaredFields();
        try{
            CriteriaQuery<T> criteria = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
            Root<T> root = (Root<T>) criteria.from(t.getClass());
            criteria.select(root);
            List<Predicate> list = new ArrayList<>();
            for(int i = 0; i<fl.length;i++){
                fl[i].setAccessible(true);
                if(fl[i].get(entity)!=null && !fl[i].getName().equals("id")){
                    if(fl[i].getType().isAssignableFrom(String.class))
                        list.add(criteriaBuilder.like(root.get(fl[i].getName()),"%"+fl[i].get(entity)+"%"));
                    else if (fl[i].getType().isAssignableFrom(Long.class) && !fl[i].get(entity).equals(0))
                        list.add(criteriaBuilder.equal(root.get(fl[i].getName()),fl[i].get(entity)));
                    else
                        list.add(criteriaBuilder.equal(root.get(fl[i].getName()),fl[i].get(entity)));
                }
            }
            criteria.where(list.toArray(new Predicate[]{}));
            result = entityManager.createQuery(criteria).getResultList();
        }catch (Exception e){
            System.out.println("Beklenmeyen hata!!! -->" + e.toString());
        }
        return result;
    }
}
