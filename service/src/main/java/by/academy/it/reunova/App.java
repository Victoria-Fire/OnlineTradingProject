package by.academy.it.reunova;

import by.academy.it.reunova.services.impl.BuyerServiceImpl;
import by.academy.it.reunova.services.impl.SellerServiceImpl;
import by.academy.it.reunova.services.interf.BuyerService;
import by.academy.it.reunova.services.interf.SellerService;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        BuyerService buyerService = new BuyerServiceImpl();
        SellerService sellerService = new SellerServiceImpl();

        buyerService.buyLot(3, 3, LocalDate.of(2022, 04, 8));
        sellerService.createLot("Пылесос", "Описание пылесоса", "2022-03-15", "2022-05-20", 284.0, 77.0, 3);
        sellerService.deleteLot(3);
    }
}
