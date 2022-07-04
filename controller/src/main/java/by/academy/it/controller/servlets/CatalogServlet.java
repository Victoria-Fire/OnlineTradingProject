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

@WebServlet(name = "CatalogServlet", value = "/catalog")
public class CatalogServlet extends HttpServlet {
    LotService lotService = new LotServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<LotDto> lotList = lotService.findLotDtoForSale(LocalDate.now());
        req.setAttribute("lots", lotList);
        req.getRequestDispatcher("/catalog.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}

