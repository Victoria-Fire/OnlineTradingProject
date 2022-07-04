package by.academy.it.controller.servlets;

import by.academy.it.service.dto.LotDto;
import by.academy.it.service.implementation.LotServiceImpl;
import by.academy.it.service.interfaces.LotService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "OrderConfirmationServlet", value = "/orderConfirmation")
public class OrderConfirmationServlet extends HttpServlet {
    LotService lotService = new LotServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer buyerId = Integer.valueOf(req.getParameter("idBuyer"));
        BigDecimal sumPrice = lotService.summationOfBuyerLotPricesInOrderConfirmation(buyerId, LocalDate.now());
        req.setAttribute("sumPrice", sumPrice);
        List<LotDto> listLotStatusTrue = lotService.getLotOfBuyerStatusTrue(buyerId, LocalDate.now());
        req.setAttribute("listLotStatusTrue", listLotStatusTrue);
        List<LotDto> listLotStatusFalse = lotService.getLotOfBuyerStatusFalse(buyerId, LocalDate.now());
        req.setAttribute("listLotStatusFalse", listLotStatusFalse);
        req.getRequestDispatcher("/orderConfirmation.jsp").forward(req, resp);
    }
}
