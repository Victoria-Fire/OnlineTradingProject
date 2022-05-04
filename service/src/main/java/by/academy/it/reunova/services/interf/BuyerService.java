package by.academy.it.reunova.services.interf;

import java.time.LocalDate;

public interface BuyerService {

    void buyLot(Integer buyerId, Integer lotId, LocalDate currentDate);
}
