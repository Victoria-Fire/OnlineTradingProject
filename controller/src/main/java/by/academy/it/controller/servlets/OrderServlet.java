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

@WebServlet(name = "OrderServlet", value = "/order")
public class OrderServlet extends HttpServlet {
    LotService lotService = new LotServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer buyerId = Integer.valueOf(req.getParameter("idBuyer"));
        List<LotDto> listLotForPurchase = lotService.getLotOfBuyerForPurchase(buyerId, LocalDate.now());
        req.setAttribute("listLotForPurchase", listLotForPurchase);
        BigDecimal sumPrice = lotService.summationOfBuyerLotPricesInOrder(buyerId, LocalDate.now());
        req.setAttribute("sumPrice", sumPrice);
        req.getRequestDispatcher("/order.jsp").forward(req, resp);
    }
}
