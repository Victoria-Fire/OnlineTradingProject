package by.academy.it.repository.dao.implementation;

import by.academy.it.repository.dao.interfaces.BuyerDao;
import by.academy.it.repository.entity.Buyer;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class BuyerDaoImpl extends EntityDaoImpl<Buyer> implements BuyerDao {

    public BuyerDaoImpl(EntityManager em) {
        super(em, Buyer.class);
    }

    @Override
    public List<Buyer> getBuyerOfLotDao(Integer lotId) {
        Query query = entityManager.createQuery("select b from Buyer as b join b.lots as l where l.id = :lotId");
        query.setParameter("lotId", lotId);
        return query.getResultList();
    }
}
