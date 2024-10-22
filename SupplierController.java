/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Suppliers;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOSupplier;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SupplierController", urlPatterns = {"/SupplierURL"})
public class SupplierController extends HttpServlet {

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
        DAOSupplier dao = new DAOSupplier();
        String sql = "Select * from Suppliers";
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service.equals("insertSupplier")) {
                String CompanyName = request.getParameter("CompanyName"),
                        ContactName = request.getParameter("ContactName"),
                        ContactTitle = request.getParameter("ContactTitle"),
                        Address = request.getParameter("Address"),
                        City = request.getParameter("City"),
                        Region = request.getParameter("Region"),
                        PostalCode = request.getParameter("PostalCode"),
                        Country = request.getParameter("Country"),
                        Phone = request.getParameter("Phone"),
                        Fax = request.getParameter("Fax");
                String HomePage = request.getParameter("HomePage");
                
                int n = dao.addSupplier(new Suppliers(CompanyName,
                        ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax, HomePage));
                response.sendRedirect("SupplierURL?service=listAllSupplier");
            }
            if (service.equals("listAllSupplier")) {
                
                String submit = request.getParameter("submit");
                if (submit != null) {
                    String slname = request.getParameter("slname");
                    sql = "Select * from Suppliers where CompanyName like '%"+slname+"%' ";
                } else {
                    sql = "Select * from Suppliers";
                }
                Vector<Suppliers> vector = dao.getSuppliers(sql);
               
                RequestDispatcher dispath = request.getRequestDispatcher("/jsp/displaySupplier.jsp");
                request.setAttribute("data", vector);
                dispath.forward(request, response);
                
               
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
