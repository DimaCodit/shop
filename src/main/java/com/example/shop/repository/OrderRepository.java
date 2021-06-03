package com.example.shop.repository;

import com.example.shop.model.Order;
import com.example.shop.util.HibernateUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final SessionFactory sessionFactory;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public List<Order> findAll(){

        List<Order> ordersList = (List<Order>) HibernateUtil.doInTransactionWithResult(sessionFactory, s -> s.createQuery("from Order").getResultList());

        if (ordersList == null) {
            return new ArrayList<>();
        }
        return ordersList;
    }

    public Order getById(Long id) {
        return (Order)HibernateUtil.doInTransactionWithResult(sessionFactory, s -> s.get(Order.class, id));
    }

    public List<Order> findAllByConsumerId(Long id) {

        List<Order> ordersList = (List<Order>) HibernateUtil.doInTransactionWithResult(
                sessionFactory, s -> s.createNamedQuery("Order.findAllByConsumerId", Order.class)
                        .setParameter("id", id).getResultList()
        );

        if (ordersList == null) {
            return new ArrayList<>();
        }
        return ordersList;
    }

    public List<Order> findAllByProductId(Long id) {

        List<Order> ordersList = (List<Order>) HibernateUtil.doInTransactionWithResult(
                sessionFactory, s -> s.createNamedQuery("Order.findAllByProductId", Order.class)
                        .setParameter("id", id).getResultList()
        );

        if (ordersList == null) {
            return new ArrayList<>();
        }
        return ordersList;
    }

    public void deleteById(Long id) {
        Order order = (Order)HibernateUtil.doInTransactionWithResult(sessionFactory, s -> s.get(Order.class, id));
        HibernateUtil.doInTransaction(sessionFactory, s -> s.delete(order));
    }

    public void saveOrUpdate(Order order) {
        HibernateUtil.doInTransaction(sessionFactory, s -> s.saveOrUpdate(order));
    }
}
