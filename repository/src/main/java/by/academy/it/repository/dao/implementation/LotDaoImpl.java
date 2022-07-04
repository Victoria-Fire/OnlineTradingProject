package by.academy.it.repository.dao.implementation;

import by.academy.it.repository.dao.interfaces.LotDao;
import by.academy.it.repository.entity.Lot;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class LotDaoImpl extends EntityDaoImpl<Lot> implements LotDao {

    public LotDaoImpl(EntityManager em) {
        super(em, Lot.class);
    }

    @Override
    public List<Lot> getLotOfBuyerDao(Integer buyerId) {
        Query query = entityManager.createQuery("select l from Lot as l join l.buyers as b where b.id = :buyerId order by l.id");
        query.setParameter("buyerId", buyerId);
        return query.getResultList();
    }

    @Override
    public List<Lot> getLotOfBuyerStatusTrueForPurchaseDao(Integer buyerId) {
        Query query = entityManager.createQuery("select l from Lot as l join l.buyers as b where b.id = :buyerId and l.status=true");
        query.setParameter("buyerId", buyerId);
        return query.getResultList();
    }

    @Override
    public List<Lot> getLotOfBuyerStatusFalseForPurchaseDao(Integer buyerId) {
        Query query = entityManager.createQuery("select l from Lot as l join l.buyers as b where b.id = :buyerId and l.status=false");
        query.setParameter("buyerId", buyerId);
        return query.getResultList();
    }
}
