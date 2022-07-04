package by.academy.it.service.implementation;

import by.academy.it.repository.dao.interfaces.BuyerDao;
import by.academy.it.repository.dao.interfaces.DaoFactory;
import by.academy.it.repository.dao.interfaces.LotDao;
import by.academy.it.repository.entity.Buyer;
import by.academy.it.repository.entity.Lot;
import by.academy.it.service.interfaces.BasketService;

import java.util.List;

public class BasketServiceImpl implements BasketService {

    @Override
    public void addLotToBasket(Integer buyerId, Integer lotId) {
        BuyerDao buyerDao = DaoFactory.getInstance().getBuyerDao();
        LotDao lotDao = DaoFactory.getInstance().getLotDao();

        Buyer buyer = buyerDao.findById(buyerId);
        Lot lot = lotDao.findById(lotId);

        buyer.getLots().add(lot);
        buyerDao.update(buyer);

        buyerDao.closeDao();
        lotDao.closeDao();
    }

    @Override
    public void deleteLotFromBasket(Integer buyerId, Integer lotId) {
        BuyerDao buyerDao = DaoFactory.getInstance().getBuyerDao();
        LotDao lotDao = DaoFactory.getInstance().getLotDao();

        Buyer buyer = buyerDao.findById(buyerId);
        Lot lot = lotDao.findById(lotId);

        List<Lot> lots = buyer.getLots();
        lots.remove(lot);
        buyerDao.update(buyer);

        buyerDao.closeDao();
        lotDao.closeDao();
    }
}
