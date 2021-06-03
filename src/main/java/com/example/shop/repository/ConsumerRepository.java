package com.example.shop.repository;

import com.example.shop.model.Consumer;
import com.example.shop.util.HibernateUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//@Repository
@RequiredArgsConstructor
public class ConsumerRepository {

    private final SessionFactory sessionFactory;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public List<Consumer> findAll(){

        List<Consumer> productsList = (List<Consumer>) HibernateUtil.doInTransactionWithResult(sessionFactory, s -> s.createQuery("from Consumer").getResultList());

        if (productsList == null) {
            return new ArrayList<Consumer>();
        }
        return productsList;
    }

    public Consumer getById(Long id) {
        return (Consumer)HibernateUtil.doInTransactionWithResult(sessionFactory, s -> s.get(Consumer.class, id));
    }

    public void deleteById(Long id) {
        Consumer consumer = (Consumer)HibernateUtil.doInTransactionWithResult(sessionFactory, s -> s.get(Consumer.class, id));
        HibernateUtil.doInTransaction(sessionFactory, s -> s.delete(consumer));
    }

    public void saveOrUpdate(Consumer consumer) {
        HibernateUtil.doInTransaction(sessionFactory, s -> s.saveOrUpdate(consumer));
    }
}
