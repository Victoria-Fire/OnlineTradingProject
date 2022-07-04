package by.academy.it.service.interfaces;

import by.academy.it.service.dto.LotDto;
import org.hibernate.LazyInitializationException;

import java.time.LocalDate;
import java.util.List;

public interface OrderHistoryService {
    void buyLots(Integer buyerId);
    void buyOneLot(Integer buyerId, Integer lotId, LocalDate currentDate) throws LazyInitializationException;
    List<LotDto> findAllLotDtoInOrderHistory();
}
