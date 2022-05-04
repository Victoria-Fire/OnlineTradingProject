package by.academy.it.reunova.services.interf;

public interface SellerService {

    void createLot(String nameLot, String description, String startDate, String endDate, Double startPrice, Double endPrice, Integer sellerId);

    void deleteLot(Integer lotId);
}
