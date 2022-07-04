package by.academy.it.controller.servlets;

import by.academy.it.service.dto.LotDto;
import by.academy.it.service.implementation.LotServiceImpl;
import by.academy.it.service.implementation.OrderHistoryServiceImpl;
import by.academy.it.service.interfaces.LotService;
import by.academy.it.service.interfaces.OrderHistoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "AdminLotServlet", value = "/adminLot")
public class AdminLotServlet extends HttpServlet {
    LotService lotService = new LotServiceImpl();
    OrderHistoryService orderHistoryService = new OrderHistoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<LotDto> lotDtoList = lotService.findAllLotDtoWithoutOrderHistory(LocalDate.now());
        req.setAttribute("lotList", lotDtoList);
        req.getRequestDispatcher("/adminLot.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        switch (action) {
            case "addLot":
                String nameLotAdd = req.getParameter("nameLot");
                String descriptionAdd = req.getParameter("description");
                String startDateAdd = req.getParameter("startDate");
                String endDateAdd = req.getParameter("endDate");
                Double startPriceAdd = Double.valueOf(req.getParameter("startPrice"));
                Double endPriceAdd = Double.valueOf(req.getParameter("endPrice"));
                Boolean statusAdd = Boolean.valueOf(req.getParameter("status"));
                lotService.createLot(nameLotAdd, descriptionAdd, startDateAdd, endDateAdd, startPriceAdd, endPriceAdd, statusAdd);
                resp.sendRedirect("adminLot");
                break;
            case "updateLot":
                Integer idUpdate = Integer.valueOf(req.getParameter("id"));
                String nameLot = req.getParameter("nameLot");
                String description = req.getParameter("description");
                String startDate = req.getParameter("startDate");
                String endDate = req.getParameter("endDate");
                Double startPrice = Double.valueOf(req.getParameter("startPrice"));
                Double endPrice = Double.valueOf(req.getParameter("endPrice"));
                Boolean status = Boolean.valueOf(req.getParameter("status"));
                lotService.updateLot(idUpdate, nameLot, description, startDate, endDate, startPrice, endPrice, status);
                resp.sendRedirect("adminLot");
                break;
            case "deleteLot":
                try {
                    Integer id = Integer.valueOf(req.getParameter("id"));
                    lotService.deleteLot(id);
                    resp.sendRedirect("adminLot");
                    break;
                } catch (Exception e) {
                    Integer id = Integer.valueOf(req.getParameter("id"));
                    resp.sendRedirect(req.getContextPath() + "/adminDeleteLotAnyway.jsp?id=" + id);
                    break;
                }
            case "deleteLotAnyway":
                Integer id = Integer.valueOf(req.getParameter("id"));
                lotService.deleteLotAnyway(id);
                resp.sendRedirect("adminLot");
                break;
            case "lotsInOrderHistory":
                List<LotDto> lotDtoListInOrderHistory = orderHistoryService.findAllLotDtoInOrderHistory();
                req.setAttribute("lotListInOrderHistory", lotDtoListInOrderHistory);
                req.getRequestDispatcher("/adminLotInOrderHistory.jsp").forward(req, resp);
                break;
            case "lotsForPurchase":
                resp.sendRedirect("adminLot");
                break;
            default:
                resp.sendRedirect("adminLot");
        }
    }
}
