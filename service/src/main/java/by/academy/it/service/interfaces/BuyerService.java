package by.academy.it.service.interfaces;

import by.academy.it.service.dto.BuyerDto;

import java.util.List;

public interface BuyerService {

    void createBuyer(String nameBuyer, String surnameBuyer);
    void updateBuyer(Integer buyerId, String newNameBuyer, String newSurnameBuyer);
    void deleteBuyer(Integer buyerId);
    List<BuyerDto> findAllBuyerDto();
    List<BuyerDto> getBuyerOfLot(Integer lotId);
}
