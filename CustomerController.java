/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOCustomers;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CustomerController", urlPatterns = {"/CustomerURL"})
public class CustomerController extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        DAOCustomers dao = new DAOCustomers();
        String sql = "Select * from Customers";
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            
            if(service == null){
                service = "listAllCustomer";
            }
            if(service.equals("loginWithContactName")){
                Vector<Customer> vector = dao.getLogin();
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            int cnt = 0;
            for (Customer customer : vector) {
                if(userName.equals(customer.getPhone()) && password.equals(customer.getFax())){
                    cnt ++;
                    
                }
            }
            if(cnt !=0){
               response.sendRedirect("CustomerURL?service=listAllCustomer");
            }
                else{
               out.print("Dang Nhap That Bai!!!");
            }
            }
            if(service.equals("deleteCustomer")){
                dao.deleteCustomer(request.getParameter("customerID"));
                response.sendRedirect("CustomerURL?service=listAllCustomer");
            }
            if(service.equals("insertCustomer")){
                String submit = request.getParameter("submit");
                if(submit == null){
                    request.getRequestDispatcher("/jsp/insertCustomer.jsp").forward(request, response);
                }
                String CustomerID = request.getParameter("CustomerID");
                String CompanyName = request.getParameter("CompanyName");
                String ContactName = request.getParameter("ContactName");
                String ContactTitle = request.getParameter("ContactTitle");
                String Address = request.getParameter("Address");
                String City = request.getParameter("City");
                String Region = request.getParameter("Region");
                String PostalCode = request.getParameter("PostalCode");
                String Country = request.getParameter("Country");
                String Phone = request.getParameter("Phone");
                String Fax = request.getParameter("Fax");
                Customer cus = new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax);
                int n = dao.addCustomer(cus);
                response.sendRedirect("CustomerURL?service=listAllCustomer");
            }
             
             if(service.equals("listAllCustomer")){
            sql = "Select * from Customers";
            
            String submit = request.getParameter("submit");
            if (submit != null) {
                String cusname = request.getParameter("cusname");
                sql = "SELECT * FROM Customers where CustomerID like '%" + cusname + "%' ";
            }
            Vector<Customer> vector = dao.getCustomer(sql);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/displayCustomer.jsp");
            
            request.setAttribute("data", vector);
            request.setAttribute("title", "CustomerManager");
            dispatcher.forward(request, response);
            
            
            
            }
             if(service.equals("loginCustomer")){
                 
                 String submit = request.getParameter("submit");
                 if(submit == null){
                 request.getRequestDispatcher("/jsp/loginCustomer.jsp").forward(request, response);
             }else{
                     Customer cus = dao.Login(request.getParameter("userName"), request.getParameter("password"));
                     if(cus == null){
                         //login fail
                         request.setAttribute("message", "Login fail");
                         request.getRequestDispatcher("/jsp/loginCustomer.jsp").forward(request, response);

                     }else{
                         //login success --> insert to the session
                         session.setAttribute("customer", cus);
                         
                         response.sendRedirect("CustomerURL?service=listAllCustomer");
                     }
                     
                 }
             }
             if(service.equals("logoutCustomer")){
                 session.invalidate();
                 response.sendRedirect("CustomerURL");
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
