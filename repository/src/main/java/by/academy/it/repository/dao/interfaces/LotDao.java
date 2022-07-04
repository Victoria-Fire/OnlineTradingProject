package by.academy.it.repository.dao.interfaces;

import by.academy.it.repository.entity.Lot;

import java.util.List;

public interface LotDao extends EntityDao<Lot> {

    List<Lot> getLotOfBuyerDao(Integer buyerId);
    List<Lot> getLotOfBuyerStatusTrueForPurchaseDao(Integer buyerId);
    List<Lot> getLotOfBuyerStatusFalseForPurchaseDao(Integer buyerId);
}
