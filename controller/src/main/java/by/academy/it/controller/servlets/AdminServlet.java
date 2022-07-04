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
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "AdminServlet", value = "/admin")
public class AdminServlet extends HttpServlet {
    LotService lotService = new LotServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<LotDto> lotDtoList = lotService.findAllLotDtoWithoutOrderHistory(LocalDate.now());
        req.setAttribute("lotList", lotDtoList);

        String action = req.getParameter("action");
        if ("changeLot".equals(action)) {
            req.getRequestDispatcher("/adminLot.jsp").forward(req, resp);
        }
    }
}
