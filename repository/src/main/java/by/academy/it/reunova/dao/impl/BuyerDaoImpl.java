package by.academy.it.reunova.dao.impl;

import by.academy.it.reunova.dao.unterf.BuyerDao;
import by.academy.it.reunova.entity.Buyer;

import javax.persistence.EntityManager;

public class BuyerDaoImpl extends EntityDaoImpl<Buyer> implements BuyerDao {

    public BuyerDaoImpl(EntityManager em) {
        super(em, Buyer.class);
    }
}
