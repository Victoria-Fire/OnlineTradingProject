package by.academy.it.repository.dao.implementation;

import by.academy.it.repository.dao.interfaces.OrderHistoryDao;
import by.academy.it.repository.entity.OrderHistory;

import javax.persistence.EntityManager;

public class OrderHistoryDaoImpl extends EntityDaoImpl<OrderHistory> implements OrderHistoryDao {

    public OrderHistoryDaoImpl(EntityManager em) {
        super(em, OrderHistory.class);
    }
}
