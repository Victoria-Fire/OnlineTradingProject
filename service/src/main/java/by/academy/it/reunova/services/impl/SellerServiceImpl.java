package by.academy.it.reunova.services.impl;

import by.academy.it.reunova.dao.unterf.DaoFactory;
import by.academy.it.reunova.dao.unterf.EntityDao;
import by.academy.it.reunova.entity.Lot;
import by.academy.it.reunova.entity.Seller;
import by.academy.it.reunova.services.interf.SellerService;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SellerServiceImpl implements SellerService {

    @Override
    public void createLot(String nameLot, String description, String startDate, String endDate, Double startPrice, Double endPrice, Integer sellerId) {
        EntityDao<Lot> lotDao = DaoFactory.getInstance().getLotDao();
        EntityDao<Seller> sellerDao = DaoFactory.getInstance().getSellerDao();

        Seller seller = sellerDao.findById(sellerId);

        if (seller == null) {
            return;
        }

        Lot lot = Lot.builder()
                .nameLot(nameLot)
                .description(description)
                .startDate(LocalDate.parse(startDate))
                .endDate(LocalDate.parse(endDate))
                .startPrice(BigDecimal.valueOf(startPrice))
                .endPrice(BigDecimal.valueOf(endPrice))
                .seller(seller)
                .build();

        lotDao.save(lot);

        lotDao.closeDao();
        sellerDao.closeDao();
    }

    @Override
    public void deleteLot(Integer lotId) {
        EntityDao<Lot> lotDao = DaoFactory.getInstance().getLotDao();

        Lot lot = lotDao.findById(lotId);

        if (lot == null) {
            return;
        }

        lotDao.delete(lot.getId());

        lotDao.closeDao();
    }
}
