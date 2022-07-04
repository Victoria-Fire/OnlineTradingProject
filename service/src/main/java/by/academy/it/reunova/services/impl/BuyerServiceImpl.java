package by.academy.it.reunova.services.impl;

import by.academy.it.reunova.dao.unterf.DaoFactory;
import by.academy.it.reunova.dao.unterf.EntityDao;
import by.academy.it.reunova.entity.Buyer;
import by.academy.it.reunova.entity.Lot;
import by.academy.it.reunova.entity.OrderHistory;
import by.academy.it.reunova.services.interf.BuyerService;
import org.hibernate.LazyInitializationException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuyerServiceImpl implements BuyerService {
    private static BigDecimal currentPrice = null;

    @Override
    public void buyLot(Integer buyerId, Integer lotId, LocalDate currentDate) throws LazyInitializationException {
        EntityDao<Buyer> buyerDao = DaoFactory.getInstance().getBuyerDao();
        EntityDao<Lot> lotDao = DaoFactory.getInstance().getLotDao();
        EntityDao<OrderHistory> orderHistoryDao = DaoFactory.getInstance().getOrderHistoryDao();

        Buyer buyer = buyerDao.findById(buyerId);
        Lot lot = lotDao.findById(lotId);

        if (buyer == null) {
            return;
        }

        if (lot == null) {
            return;
        }

        if (!((!currentDate.isBefore(lot.getStartDate())) && (currentDate.isBefore(lot.getEndDate().plusDays(1))))) {
            return;
        }

        List<OrderHistory> orderHistoryCheck = orderHistoryDao.findAll();
        for (int i = 0; i < orderHistoryCheck.size(); i++) {
            if (orderHistoryCheck.get(i).getId().equals(lotId)) {
                return;
            }
        }

        OrderHistory orderHistory = OrderHistory.builder()
                .buyer(buyer)
                .currentPrice(BuyerServiceImpl.getCurrentPrice(lot.getId(), currentDate))
                .dateOfPurchase(currentDate)
                .build();

        orderHistory.setLotInOrderHistory(lot);
        lot.setOrderHistory(orderHistory);
        orderHistoryDao.save(orderHistory);

        buyerDao.closeDao();
        lotDao.closeDao();
        orderHistoryDao.closeDao();
    }

    private static Integer countingDiffOfDays(Integer lotId) {
        EntityDao<Lot> lotDao = DaoFactory.getInstance().getLotDao();

        Lot lot = lotDao.findById(lotId);

        Integer diffDays = (lot.getEndDate().getDayOfYear() - lot.getStartDate().getDayOfYear());

        lotDao.closeDao();
        return diffDays;
    }

    private static BigDecimal countingDiffOfPrice(Integer lotId) {
        EntityDao<Lot> lotDao = DaoFactory.getInstance().getLotDao();

        Lot lot = lotDao.findById(lotId);
        BigDecimal diffPrice = lot.getStartPrice().subtract(lot.getEndPrice());

        lotDao.closeDao();
        return diffPrice;
    }

    private static BigDecimal getCurrentPrice(Integer lotId, LocalDate currentDate) {
        BigDecimal priceDifference = BuyerServiceImpl.countingDiffOfPrice(lotId);
        BigDecimal differenceOfDays = BigDecimal.valueOf(BuyerServiceImpl.countingDiffOfDays(lotId));

        EntityDao<Lot> lotDao = DaoFactory.getInstance().getLotDao();

        Lot lot = lotDao.findById(lotId);

        Map<LocalDate, BigDecimal> map = new HashMap<>();

        BigDecimal currentPriceValue = lot.getStartPrice();
        for (int i = 0; i <= BuyerServiceImpl.countingDiffOfDays(lotId); i++) {
            map.put(lot.getStartDate().plusDays(i), currentPriceValue);
            currentPriceValue = (currentPriceValue.subtract(priceDifference.divide(differenceOfDays, 3, RoundingMode.HALF_UP)));
        }

        for (Map.Entry<LocalDate, BigDecimal> pair : map.entrySet()) {
            if (pair.getKey().equals(currentDate)) {
                currentPrice = pair.getValue();
            }
        }

        lotDao.closeDao();
        return currentPrice;
    }
}