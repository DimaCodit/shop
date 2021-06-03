package com.example.shop.repository;

import com.example.shop.model.Product;
import com.example.shop.util.HibernateUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final SessionFactory sessionFactory;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public List<Product> findAll(){

        List<Product> productsList = (List<Product>) HibernateUtil.doInTransactionWithResult(sessionFactory, s -> s.createQuery("from Product").getResultList());

        if (productsList == null) {
            return new ArrayList<Product>();
        }
        return productsList;
    }

    public Product getById(Long id) {
        return (Product)HibernateUtil.doInTransactionWithResult(sessionFactory, s -> s.get(Product.class, id));
    }

    public void deleteById(Long id) {
        Product product = (Product)HibernateUtil.doInTransactionWithResult(sessionFactory, s -> s.get(Product.class, id));
        HibernateUtil.doInTransaction(sessionFactory, s -> s.delete(product));
    }

    public void saveOrUpdate(Product product) {
        HibernateUtil.doInTransaction(sessionFactory, s -> s.saveOrUpdate(product));
    }
}
