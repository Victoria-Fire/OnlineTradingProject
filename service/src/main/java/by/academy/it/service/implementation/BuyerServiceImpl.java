package by.academy.it.service.implementation;

import by.academy.it.repository.dao.interfaces.BuyerDao;
import by.academy.it.repository.dao.interfaces.DaoFactory;
import by.academy.it.repository.entity.Buyer;
import by.academy.it.service.dto.BuyerDto;
import by.academy.it.service.interfaces.BuyerService;

import java.util.List;
import java.util.stream.Collectors;

public class BuyerServiceImpl implements BuyerService {

    @Override
    public void createBuyer(String nameBuyer, String surnameBuyer) {

        BuyerDao buyerDao = DaoFactory.getInstance().getBuyerDao();
        Buyer buyer = Buyer.builder()
                .nameBuyer(nameBuyer)
                .surnameBuyer(surnameBuyer)
                .build();

        buyerDao.save(buyer);
        buyerDao.closeDao();
    }

    @Override
    public void updateBuyer(Integer buyerId, String newNameBuyer, String newSurnameBuyer) {
        BuyerDao buyerDao = DaoFactory.getInstance().getBuyerDao();
        Buyer buyer = buyerDao.findById(buyerId);

        buyer.setNameBuyer(newNameBuyer);
        buyer.setSurnameBuyer(newSurnameBuyer);

        buyerDao.update(buyer);
        buyerDao.closeDao();
    }

    @Override
    public void deleteBuyer(Integer buyerId) {
        BuyerDao buyerDao = DaoFactory.getInstance().getBuyerDao();

        Buyer buyer = buyerDao.findById(buyerId);

        buyerDao.delete(buyer.getId());
        buyerDao.closeDao();
    }

    @Override
    public List<BuyerDto> findAllBuyerDto() {
        BuyerDao buyerDao = DaoFactory.getInstance().getBuyerDao();
        List<Buyer> buyerList = buyerDao.findAll();

        return buyerList.stream()
                .map(Buyer -> BuyerDto.builder()
                        .id(Buyer.getId())
                        .nameBuyer(Buyer.getNameBuyer())
                        .surnameBuyer(Buyer.getSurnameBuyer())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<BuyerDto> getBuyerOfLot(Integer lotId) {
        BuyerDao buyerDao = DaoFactory.getInstance().getBuyerDao();
        List<Buyer> buyers = buyerDao.getBuyerOfLotDao(lotId);
        return buyers.stream()
                .map(Buyer -> BuyerDto.builder()
                        .id(Buyer.getId())
                        .nameBuyer(Buyer.getNameBuyer())
                        .surnameBuyer(Buyer.getSurnameBuyer())
                        .build())
                .collect(Collectors.toList());
    }

}