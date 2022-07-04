package by.academy.it.controller.servlets;

import by.academy.it.service.dto.BuyerDto;
import by.academy.it.service.implementation.BuyerServiceImpl;
import by.academy.it.service.interfaces.BuyerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BuyerServlet", value = "/buyer")
public class BuyerServlet extends HttpServlet {
    BuyerService buyerService = new BuyerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BuyerDto> buyerDtoList = buyerService.findAllBuyerDto();
        req.setAttribute("buyerList", buyerDtoList);
        req.getRequestDispatcher("/buyer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "addBuyer":
                String nameBuyerAdd = req.getParameter("nameBuyer");
                String surnameBuyerAdd = req.getParameter("surnameBuyer");
                buyerService.createBuyer(nameBuyerAdd, surnameBuyerAdd);
                resp.sendRedirect("buyer");
                break;
            case "updateBuyer":
                Integer idUpdate = Integer.valueOf(req.getParameter("id"));
                String nameBuyer = req.getParameter("nameBuyer");
                String surnameBuyer = req.getParameter("surnameBuyer");
                buyerService.updateBuyer(idUpdate, nameBuyer, surnameBuyer);
                resp.sendRedirect("buyer");
                break;
            case "deleteBuyer":
                Integer id = Integer.valueOf(req.getParameter("id"));
                buyerService.deleteBuyer(id);
                resp.sendRedirect("buyer");
                break;
            default:
                resp.sendRedirect("buyer");
        }
    }
}