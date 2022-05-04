package by.academy.it.reunova.dao.impl;

import by.academy.it.reunova.dao.unterf.LotDao;
import by.academy.it.reunova.entity.Lot;

import javax.persistence.EntityManager;

public class LotDaoImpl extends EntityDaoImpl<Lot> implements LotDao {

    public LotDaoImpl(EntityManager em) {
        super(em, Lot.class);
    }
}
