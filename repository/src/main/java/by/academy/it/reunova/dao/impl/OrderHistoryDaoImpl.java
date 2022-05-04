package by.academy.it.reunova.dao.impl;

import by.academy.it.reunova.dao.unterf.OrderHistoryDao;
import by.academy.it.reunova.entity.OrderHistory;

import javax.persistence.EntityManager;

public class OrderHistoryDaoImpl extends EntityDaoImpl<OrderHistory> implements OrderHistoryDao {

    public OrderHistoryDaoImpl(EntityManager em) {
        super(em, OrderHistory.class);
    }
}
