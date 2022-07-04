package by.academy.it.controller.servlets;

import by.academy.it.service.implementation.OrderHistoryServiceImpl;
import by.academy.it.service.interfaces.OrderHistoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PurchaseServlet", value = "/purchase")
public class PurchaseServlet extends HttpServlet {
    OrderHistoryService orderHistoryService = new OrderHistoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer buyerId = Integer.valueOf(req.getParameter("idBuyer"));
        orderHistoryService.buyLots(buyerId);
        req.getRequestDispatcher("orderIsPaid.jsp").forward(req, resp);
    }
}


