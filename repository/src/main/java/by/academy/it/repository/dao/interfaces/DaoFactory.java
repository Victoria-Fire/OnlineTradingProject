package by.academy.it.repository.dao.interfaces;

import by.academy.it.repository.dao.implementation.BuyerDaoImpl;
import by.academy.it.repository.dao.implementation.LotDaoImpl;
import by.academy.it.repository.dao.implementation.OrderHistoryDaoImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoFactory {
    private final EntityManagerFactory factory;

    private static class SingletonHolder {
        private final static DaoFactory INSTANCE =
                new DaoFactory();
    }

    private DaoFactory() {
        factory = Persistence
                .createEntityManagerFactory("trading");
    }

    public static DaoFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public BuyerDao getBuyerDao() {
        return new BuyerDaoImpl(factory.createEntityManager());
    }

    public LotDao getLotDao() {
        return new LotDaoImpl(factory.createEntityManager());
    }

    public OrderHistoryDao getOrderHistoryDao() {
        return new OrderHistoryDaoImpl(factory.createEntityManager());
    }
}
