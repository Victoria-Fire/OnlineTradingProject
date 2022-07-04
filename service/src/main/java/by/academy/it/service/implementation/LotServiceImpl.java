package by.academy.it.service.implementation;

import by.academy.it.repository.dao.interfaces.*;
import by.academy.it.repository.entity.Buyer;
import by.academy.it.repository.entity.Lot;
import by.academy.it.repository.entity.OrderHistory;
import by.academy.it.service.dto.LotDto;
import by.academy.it.service.interfaces.BasketService;
import by.academy.it.service.interfaces.LotService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LotServiceImpl implements LotService {

    private static BigDecimal currentPrice = null;

    @Override
    public void createLot(String nameLot, String description, String startDate, String endDate, Double startPrice, Double endPrice, Boolean status) {
        LotDao lotDao = DaoFactory.getInstance().getLotDao();

        Lot lot = Lot.builder()
                .nameLot(nameLot)
                .description(description)
                .startDate(LocalDate.parse(startDate))
                .endDate(LocalDate.parse(endDate))
                .startPrice(BigDecimal.valueOf(startPrice))
                .endPrice(BigDecimal.valueOf(endPrice))
                .status(status)
                .build();

        lotDao.save(lot);
        lotDao.closeDao();
    }

    @Override
    public void updateLot(Integer lotId, String newNameLot, String newDescription, String newStartDate, String newEndDate, Double newStartPrice, Double newEndPrice, Boolean status) {

        LotDao lotDao = DaoFactory.getInstance().getLotDao();
        Lot lot = lotDao.findById(lotId);
        LocalDate currentDate = LocalDate.now();

        lot.setNameLot(newNameLot);
        lot.setDescription(newDescription);
        lot.setStartDate(LocalDate.parse(newStartDate));
        lot.setEndDate(LocalDate.parse(newEndDate));
        lot.setStartPrice(BigDecimal.valueOf(newStartPrice));
        lot.setEndPrice(BigDecimal.valueOf(newEndPrice));
        lot.setStatus(status);

        if (!((!currentDate.isBefore(lot.getStartDate())) && (currentDate.isBefore(lot.getEndDate().plusDays(1))))) {
            lot.setStatus(false);
        }

        lotDao.update(lot);
        lotDao.closeDao();
    }

    @Override
    public void deleteLot(Integer lotId) {
        LotDao lotDao = DaoFactory.getInstance().getLotDao();

        Lot lot = lotDao.findById(lotId);
        lotDao.delete(lot.getId());

        lotDao.closeDao();
    }

    @Override
    public void deleteLotAnyway(Integer lotId) {
        LotService lotService = new LotServiceImpl();
        BasketService basketService = new BasketServiceImpl();

        BuyerDao buyerDao = DaoFactory.getInstance().getBuyerDao();
        List<Buyer> listBuyer = buyerDao.getBuyerOfLotDao(lotId);

        for (Buyer buyer : listBuyer) {
            basketService.deleteLotFromBasket(buyer.getId(), lotId);
        }

        lotService.deleteLot(lotId);
    }

    @Override
    public BigDecimal getCurrentPrice(Integer lotId, LocalDate currentDate) {
        BigDecimal priceDifference = countingDiffOfPrice(lotId);
        BigDecimal differenceOfDays = BigDecimal.valueOf(countingDiffOfDays(lotId));

        EntityDao<Lot> lotDao = DaoFactory.getInstance().getLotDao();
        Lot lot = lotDao.findById(lotId);

        if (!((!currentDate.isBefore(lot.getStartDate())) && (currentDate.isBefore(lot.getEndDate().plusDays(1))))) {
            lot.setStatus(false);
            lotDao.update(lot);
            return BigDecimal.valueOf(00.00);
        }

        Map<LocalDate, BigDecimal> map = new HashMap<>();

        BigDecimal currentPriceValue = lot.getStartPrice();
        for (int i = 0; i <= countingDiffOfDays(lotId); i++) {
            map.put(lot.getStartDate().plusDays(i), currentPriceValue);
            currentPriceValue = (currentPriceValue.subtract(priceDifference.divide(differenceOfDays, 2, RoundingMode.HALF_UP)));
        }

        for (Map.Entry<LocalDate, BigDecimal> pair : map.entrySet()) {
            if (pair.getKey().equals(currentDate)) {
                currentPrice = pair.getValue();
            }
        }

        lotDao.closeDao();
        return currentPrice;
    }

    @Override
    public List<LotDto> findAllLotDto(LocalDate currentDate) {
        LotService lotService = new LotServiceImpl();
        LotDao lotDao = DaoFactory.getInstance().getLotDao();
        List<Lot> lotList = lotDao.findAll();

        return lotList.stream()
                .map(Lot -> LotDto.builder()
                        .id(Lot.getId())
                        .nameLot(Lot.getNameLot())
                        .description(Lot.getDescription())
                        .startDate(Lot.getStartDate())
                        .endDate(Lot.getEndDate())
                        .startPrice(Lot.getStartPrice())
                        .endPrice(Lot.getEndPrice())
                        .status(Lot.getStatus())
                        .price(lotService.getCurrentPrice(Lot.getId(), currentDate))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<LotDto> findAllLotDtoWithoutOrderHistory(LocalDate currentDate) {
        LotService lotService = new LotServiceImpl();
        LotDao lotDao = DaoFactory.getInstance().getLotDao();
        List<Lot> lotList = lotDao.findAll();
        OrderHistoryDao historyDao = DaoFactory.getInstance().getOrderHistoryDao();
        List<OrderHistory> orderHistory = historyDao.findAll();

        for (int i = 0; i < lotList.size(); i++) {
            for (int j = 0; j < orderHistory.size(); j++) {
                if (lotList.get(i).getId().equals(orderHistory.get(j).getId())) {
                    lotList.remove(lotList.get(i));
                }
            }
        }

        return lotList.stream()
                .map(Lot -> LotDto.builder()
                        .id(Lot.getId())
                        .nameLot(Lot.getNameLot())
                        .description(Lot.getDescription())
                        .startDate(Lot.getStartDate())
                        .endDate(Lot.getEndDate())
                        .startPrice(Lot.getStartPrice())
                        .endPrice(Lot.getEndPrice())
                        .status(Lot.getStatus())
                        .price(lotService.getCurrentPrice(Lot.getId(), currentDate))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<LotDto> findLotDtoForSale(LocalDate currentDate) {
        LotService lotService = new LotServiceImpl();
        LotDao lotDao = DaoFactory.getInstance().getLotDao();
        List<Lot> lotList = lotDao.findAll();

        return lotList.stream()
                .filter(lot -> ((!currentDate.isBefore(lot.getStartDate())) && (currentDate.isBefore(lot.getEndDate().plusDays(1)))))
                .filter(lot -> lot.getStatus().equals(true))
                .map(Lot -> LotDto.builder()
                        .id(Lot.getId())
                        .nameLot(Lot.getNameLot())
                        .description(Lot.getDescription())
                        .startDate(Lot.getStartDate())
                        .endDate(Lot.getEndDate())
                        .startPrice(Lot.getStartPrice())
                        .endPrice(Lot.getEndPrice())
                        .price(lotService.getCurrentPrice(Lot.getId(), currentDate))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<LotDto> getLotOfBuyer(Integer buyerId, LocalDate currentDate) {
        LotService lotService = new LotServiceImpl();
        LotDao lotDao = DaoFactory.getInstance().getLotDao();
        List<Lot> lots = lotDao.getLotOfBuyerDao(buyerId);

        return lots.stream()
                .map(Lot -> LotDto.builder()
                        .id(Lot.getId())
                        .nameLot(Lot.getNameLot())
                        .description(Lot.getDescription())
                        .startDate(Lot.getStartDate())
                        .endDate(Lot.getEndDate())
                        .startPrice(Lot.getStartPrice())
                        .endPrice(Lot.getEndPrice())
                        .price(lotService.getCurrentPrice(Lot.getId(), currentDate))
                        .status(Lot.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> getLotListIdOfBuyer(Integer buyerId) {
        LotDao lotDao = DaoFactory.getInstance().getLotDao();
        List<Lot> lots = lotDao.getLotOfBuyerDao(buyerId);
        List<Integer> listId = lots.stream()
                .map(Lot -> Lot.getId())
                .collect(Collectors.toList());
        return listId;
    }

    @Override
    public List<LotDto> getLotOfBuyerStatusTrue(Integer buyerId, LocalDate currentDate) {
        LotService lotService = new LotServiceImpl();
        return lotService.getLotOfBuyer(buyerId, currentDate).stream()
                .filter(lot -> lot.getStatus().equals(true))
                .collect(Collectors.toList());
    }

    @Override
    public List<LotDto> getLotOfBuyerStatusFalse(Integer buyerId, LocalDate currentDate) {
        LotService lotService = new LotServiceImpl();
        return lotService.getLotOfBuyer(buyerId, currentDate).stream()
                .filter(lot -> lot.getStatus().equals(false))
                .collect(Collectors.toList());
    }

    @Override
    public void getLotOfBuyerWithoutSoldLot(Integer buyerId, LocalDate currentDate) {
        BasketService basketService = new BasketServiceImpl();
        LotDao lotDao = DaoFactory.getInstance().getLotDao();
        List<Lot> lots = lotDao.getLotOfBuyerDao(buyerId);
        for (Lot listLots : lots) {
            if (listLots.getStatus().equals(false)) {
                basketService.deleteLotFromBasket(buyerId, listLots.getId());
            }
        }
    }

    @Override
    public BigDecimal summationOfBuyerLotPricesInOrderConfirmation(Integer buyerId, LocalDate currentDate) {
        LotService lotService = new LotServiceImpl();
        List<LotDto> listLot = lotService.getLotOfBuyerStatusTrue(buyerId, currentDate);
        BigDecimal sumPrice = BigDecimal.valueOf(0);
        for (LotDto lot : listLot) {
            sumPrice = sumPrice.add(lot.getPrice());
        }
        return sumPrice;
    }

    @Override
    public BigDecimal summationOfBuyerLotPricesInOrder(Integer buyerId, LocalDate currentDate) {
        LotService lotService = new LotServiceImpl();
        List<LotDto> listLot = lotService.getLotOfBuyerStatusFalse(buyerId, currentDate);
        BigDecimal sumPrice = BigDecimal.valueOf(0);
        for (LotDto lot : listLot) {
            sumPrice = sumPrice.add(lot.getPrice());
        }
        return sumPrice;
    }

    @Override
    public List<LotDto> getLotOfBuyerForPurchase(Integer buyerId, LocalDate currentDate) {
        LotService lotService = new LotServiceImpl();
        lotService.getLotOfBuyerWithoutSoldLot(buyerId, currentDate);
        LotDao lotDao = DaoFactory.getInstance().getLotDao();
        List<Lot> listLot = lotDao.getLotOfBuyerStatusTrueForPurchaseDao(buyerId);
        for (Lot lot : listLot) {
            lot.setStatus(false);
            lotDao.update(lot);
        }
        lotDao.closeDao();

        return listLot.stream()
                .map(Lot -> LotDto.builder()
                        .id(Lot.getId())
                        .nameLot(Lot.getNameLot())
                        .description(Lot.getDescription())
                        .startDate(Lot.getStartDate())
                        .endDate(Lot.getEndDate())
                        .startPrice(Lot.getStartPrice())
                        .endPrice(Lot.getEndPrice())
                        .price(lotService.getCurrentPrice(Lot.getId(), currentDate))
                        .status(Lot.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void returnStatusTrueFromPurchase(Integer buyerId) {
        LotDao lotDao = DaoFactory.getInstance().getLotDao();
        List<Lot> listLot = lotDao.getLotOfBuyerStatusFalseForPurchaseDao(buyerId);
        for (Lot lot : listLot) {
            lot.setStatus(true);
            lotDao.update(lot);
        }
        lotDao.closeDao();
    }

    private Integer countingDiffOfDays(Integer lotId) {
        LotDao lotDao = DaoFactory.getInstance().getLotDao();

        Lot lot = lotDao.findById(lotId);

        Integer diffDays = (lot.getEndDate().getDayOfYear() - lot.getStartDate().getDayOfYear());

        lotDao.closeDao();
        return diffDays;
    }

    private BigDecimal countingDiffOfPrice(Integer lotId) {
        LotDao lotDao = DaoFactory.getInstance().getLotDao();

        Lot lot = lotDao.findById(lotId);
        BigDecimal diffPrice = lot.getStartPrice().subtract(lot.getEndPrice());

        lotDao.closeDao();
        return diffPrice;
    }
}
