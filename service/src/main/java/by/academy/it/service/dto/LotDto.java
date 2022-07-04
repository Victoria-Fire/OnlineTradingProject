package by.academy.it.service.dto;

import by.academy.it.repository.entity.Buyer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LotDto {
    private Integer id;
    private String nameLot;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal startPrice;
    private BigDecimal endPrice;
    private Boolean status;
    private BigDecimal price;
    private List<Buyer> buyers;
}
