package by.academy.it.controller.servlets;

import by.academy.it.service.dto.LotDto;
import by.academy.it.service.implementation.BasketServiceImpl;
import by.academy.it.service.implementation.LotServiceImpl;
import by.academy.it.service.interfaces.BasketService;
import by.academy.it.service.interfaces.LotService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "BasketServlet", value = "/basket")
public class BasketServlet extends HttpServlet {
    BasketService basketService = new BasketServiceImpl();
    LotService lotService = new LotServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer idBuyer = Integer.valueOf(req.getParameter("idBuyer"));
        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "returnCatalogFromOrder":
                    lotService.getLotOfBuyerWithoutSoldLot(idBuyer, LocalDate.now());
                    break;
                case "returnCatalogFromPurchase":
                    lotService.returnStatusTrueFromPurchase(idBuyer);
                    break;
                default:
                    resp.sendRedirect("basket");
            }
        }

        List<LotDto> lotDtoList = lotService.getLotOfBuyer(idBuyer, LocalDate.now());
        req.setAttribute("lotOfBuyer", lotDtoList);
        req.getRequestDispatcher("/basket.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Integer lotId = Integer.valueOf(req.getParameter("id"));
        Integer buyerId = Integer.valueOf(req.getParameter("buyerId"));

        switch (action) {
            case "addToBasketCatalog":
                basketService.addLotToBasket(buyerId, lotId);
                resp.sendRedirect("catalogBuyer?idBuyer=" + buyerId + "");
                break;
            case "deleteFromBasketCatalog":
                basketService.deleteLotFromBasket(buyerId, lotId);
                resp.sendRedirect("catalogBuyer?idBuyer=" + buyerId + "");
                break;
            case "deleteFromBasketBuyer":
                basketService.deleteLotFromBasket(buyerId, lotId);
                resp.sendRedirect("basket?idBuyer=" + buyerId + "");
                break;
            default:
                resp.sendRedirect("catalogBuyer?idBuyer=" + buyerId + "");
        }
    }
}
