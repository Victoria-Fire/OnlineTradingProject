package by.academy.it.service.interfaces;

import by.academy.it.service.dto.LotDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface LotService {
    void createLot(String nameLot, String description, String startDate, String endDate, Double startPrice, Double endPrice, Boolean status);
    void updateLot(Integer lotId, String newNameLot, String newDescription, String newStartDate, String newEndDate, Double newStartPrice, Double newEndPrice, Boolean status);
    void deleteLot(Integer lotId);
    void deleteLotAnyway(Integer lotId);
    BigDecimal getCurrentPrice(Integer lotId, LocalDate currentDate);
    List<LotDto> findAllLotDto(LocalDate currentDate);
    List<LotDto> findAllLotDtoWithoutOrderHistory(LocalDate currentDate);
    List<LotDto> findLotDtoForSale(LocalDate currentDate);
    List<LotDto> getLotOfBuyer(Integer buyerId, LocalDate currentDate);
    List<Integer> getLotListIdOfBuyer(Integer buyerId);
    List<LotDto> getLotOfBuyerStatusTrue(Integer buyerId, LocalDate currentDate);
    List<LotDto> getLotOfBuyerStatusFalse(Integer buyerId, LocalDate currentDate);
    void getLotOfBuyerWithoutSoldLot(Integer buyerId, LocalDate currentDate);
    BigDecimal summationOfBuyerLotPricesInOrderConfirmation(Integer buyerId, LocalDate currentDate);
    BigDecimal summationOfBuyerLotPricesInOrder(Integer buyerId, LocalDate currentDate);
    List<LotDto> getLotOfBuyerForPurchase(Integer buyerId, LocalDate currentDate);
    void returnStatusTrueFromPurchase(Integer buyerId);
}
