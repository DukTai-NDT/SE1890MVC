/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Bill;
import entity.Orders;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOOrders;
import jakarta.servlet.RequestDispatcher;
import model.DAOBill;
/**
 *
 * @author Admin
 */
@WebServlet(name = "OrderController", urlPatterns = {"/OrderURL"})
public class OrderController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOOrders dao = new DAOOrders();
        String sql = "Select * from [Orders]";

        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service.equals("insertOrder")) {

                String CustomerID = request.getParameter("CustomerID");
                String EmployeeID = request.getParameter("EmployeeID");
                String OrderDate = request.getParameter("OrderDate"),
                        RequiredDate = request.getParameter("RequiredDate"),
                        ShippedDate = request.getParameter("ShippedDate");
                String ShipVia = request.getParameter("ShipVia");
                String Freight = request.getParameter("Freight");
                String ShipName = request.getParameter("ShipName"),
                        ShipAddress = request.getParameter("ShipAddress"),
                        ShipCity = request.getParameter("ShipCity"),
                        ShipRegion = request.getParameter("ShipRegion"),
                        ShipPostalCode = request.getParameter("ShipPostalCode"),
                        ShipCountry = request.getParameter("ShipCountry");
                
                int EmployeeId = Integer.parseInt(EmployeeID);
                int ShipViA = Integer.parseInt(ShipVia);
                double FreighT = Double.parseDouble(Freight);
                
                int n = dao.addOrder(new Orders(CustomerID, EmployeeId, OrderDate, RequiredDate, ShippedDate, ShipViA, FreighT, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry));
                response.sendRedirect("OrderURL?service=listAllOrder");

            }
            if(service.equals("listAllOrderWithContactName")){
                String cid = request.getParameter("cid");
                  sql = "Select * from [Orders] where CustomerID like '%" + cid + "%'";
                 Vector<Orders> vector = dao.getOrder(sql);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/displayOrder.jsp");
                
                request.setAttribute("data", vector);
                
                dispatcher.forward(request, response);
            }
            if (service.equals("listAllOrder")) {
               
                String submit = request.getParameter("submit");
                if (submit == null) {
                    sql = "Select * from [Orders]";
                } else {
                    String oname = request.getParameter("oname");
                    sql = "Select * from [Orders] where CustomerID like '%" + oname + "%'";
                }
                Vector<Orders> vector = dao.getOrder(sql);
               
                RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/displayOrder.jsp");
                
                request.setAttribute("data", vector);
                
                dispatcher.forward(request, response);
                
                
            }
            if(service.equals("BillOrder")){
                DAOBill daobill = new DAOBill();
                int id = Integer.parseInt(request.getParameter("id"));
                sql = "Select odetail.OrderID,o.OrderDate,o.RequiredDate,cus.ContactName,emp.FirstName,pduct.ProductID,pduct.ProductName,odetail.UnitPrice,odetail.Quantity,odetail.Discount from [Order Details] odetail join Orders o on odetail.OrderID = o.OrderID join Products pduct on odetail.ProductID = pduct.ProductID "
                        + "join Customers cus on o.CustomerID = cus.CustomerID join Employees emp on o.EmployeeID=emp.EmployeeID where o.OrderID = "+id;
                Vector<Bill> vector = daobill.getBill(sql);
               
                RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/BillOrder.jsp");
                
                request.setAttribute("data", vector);
                
                dispatcher.forward(request, response);
               
                
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
