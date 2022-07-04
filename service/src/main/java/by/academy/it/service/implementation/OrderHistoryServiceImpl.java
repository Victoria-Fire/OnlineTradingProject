package by.academy.it.service.implementation;

import by.academy.it.repository.dao.interfaces.*;
import by.academy.it.repository.entity.Buyer;
import by.academy.it.repository.entity.Lot;
import by.academy.it.repository.entity.OrderHistory;
import by.academy.it.service.dto.LotDto;
import by.academy.it.service.interfaces.BasketService;
import by.academy.it.service.interfaces.LotService;
import by.academy.it.service.interfaces.OrderHistoryService;
import org.hibernate.LazyInitializationException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderHistoryServiceImpl implements OrderHistoryService {

    @Override
    public void buyLots(Integer buyerId) {
        LotService lotService = new LotServiceImpl();
        OrderHistoryService orderHistoryService = new OrderHistoryServiceImpl();
        BasketService basketService = new BasketServiceImpl();

        List<Integer> listBuyerLots = lotService.getLotListIdOfBuyer(buyerId);
        for (Integer lotIdBuy : listBuyerLots) {
            orderHistoryService.buyOneLot(buyerId, lotIdBuy, LocalDate.now());
        }
        for (Integer lotIdDeleteFromBasket : listBuyerLots) {
            basketService.deleteLotFromBasket(buyerId, lotIdDeleteFromBasket);
        }

    }

    @Override
    public void buyOneLot(Integer buyerId, Integer lotId, LocalDate currentDate) throws LazyInitializationException {
        LotService lotService = new LotServiceImpl();

        BuyerDao buyerDao = DaoFactory.getInstance().getBuyerDao();
        LotDao lotDao = DaoFactory.getInstance().getLotDao();
        OrderHistoryDao orderHistoryDao = DaoFactory.getInstance().getOrderHistoryDao();

        Buyer buyer = buyerDao.findById(buyerId);
        Lot lot = lotDao.findById(lotId);

        OrderHistory orderHistory = OrderHistory.builder()
                .buyer(buyer)
                .currentPrice(lotService.getCurrentPrice(lot.getId(), currentDate))
                .dateOfPurchase(currentDate)
                .build();

        orderHistory.setLotInOrderHistory(lot);
        lot.setOrderHistory(orderHistory);
        orderHistoryDao.save(orderHistory);

        buyerDao.closeDao();
        lotDao.closeDao();
        orderHistoryDao.closeDao();
    }

    @Override
    public List<LotDto> findAllLotDtoInOrderHistory() {
        LotDao lotDao = DaoFactory.getInstance().getLotDao();
        List<Lot> lotList = lotDao.findAll();
        OrderHistoryDao historyDao = DaoFactory.getInstance().getOrderHistoryDao();
        List<OrderHistory> orderHistory = historyDao.findAll();
        List<Lot> checkLotList = new ArrayList<>();
        for (Lot lot : lotList) {
            for (OrderHistory history : orderHistory) {
                if (lot.getId().equals(history.getId())) {
                    checkLotList.add(lot);
                }
            }
        }

        return checkLotList.stream()
                .map(Lot -> LotDto.builder()
                        .id(Lot.getId())
                        .nameLot(Lot.getNameLot())
                        .description(Lot.getDescription())
                        .build())
                .collect(Collectors.toList());
    }
}
