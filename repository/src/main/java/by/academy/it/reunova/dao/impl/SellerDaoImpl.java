package by.academy.it.reunova.dao.impl;

import by.academy.it.reunova.dao.unterf.SellerDao;
import by.academy.it.reunova.entity.Seller;

import javax.persistence.EntityManager;

public class SellerDaoImpl extends EntityDaoImpl<Seller> implements SellerDao {

    public SellerDaoImpl(EntityManager em) {
        super(em, Seller.class);
    }
}
