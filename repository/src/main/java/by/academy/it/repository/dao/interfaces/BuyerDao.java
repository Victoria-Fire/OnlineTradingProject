package by.academy.it.repository.dao.interfaces;

import by.academy.it.repository.entity.Buyer;

import java.util.List;

public interface BuyerDao extends EntityDao<Buyer> {

    List<Buyer> getBuyerOfLotDao(Integer lotId);
}
