/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Employee;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.DAOEmployee;

/**
 *
 * @author Admin
 */
@WebServlet(name = "EmployeeController", urlPatterns = {"/EmployeeURL"})
public class EmployeeController extends HttpServlet {

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
        DAOEmployee dao = new DAOEmployee();
        String sql;
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service.equals("deleteEmployee")) {
                dao.deleteEmployee(Integer.parseInt(request.getParameter("EmployeeID")));
                response.sendRedirect("EmployeeURL?service=listAllEmployee");
            }
            if (service.equals("insertEmployee")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    request.getRequestDispatcher("/jsp/insertEmployee.jsp").forward(request, response);
                } else {
                    String LastName = request.getParameter("LastName"),
                            FirstName = request.getParameter("FirstName"),
                            Title = request.getParameter("Title"),
                            TitleOfCourtesy = request.getParameter("TitleOfCourtesy");
                    String BirthDate = request.getParameter("BirthDate"),
                            HireDate = request.getParameter("HireDate");
                    String Address = request.getParameter("Address"),
                            City = request.getParameter("City"),
                            Region = request.getParameter("Region"),
                            PostalCode = request.getParameter("PostalCode"),
                            Country = request.getParameter("Country"),
                            HomePhone = request.getParameter("HomePhone"),
                            Extension = request.getParameter("Extension");
                    String Notes = request.getParameter("Notes");
                    String ReportsTo = request.getParameter("ReportsTo");
                    String PhotoPath = request.getParameter("PhotoPath");

                    int ReportsTO = Integer.parseInt(ReportsTo);
                    Employee emp = new Employee(LastName, FirstName, Title, TitleOfCourtesy, BirthDate, HireDate, Address, City, Region, PostalCode, Country, HomePhone, Extension, Notes, ReportsTO, PhotoPath);
                    int n = dao.addEmployee(emp);
                    response.sendRedirect("EmployeeURL?service=listAllEmployee");
                }
            }
            if (service.equals("listAllEmployee")) {

                String submit = request.getParameter("submit");
                if (submit == null) {
                    sql = "SELECT *  FROM  Employees  ";
                } else {
                    String ename = request.getParameter("ename");
                    sql = "SELECT *  FROM  Employees where EmployeeID like '%" + ename + "%'";
                }
                Vector<Employee> vector = dao.getEmployee(sql);
                RequestDispatcher dispath = request.getRequestDispatcher("/jsp/displayEmployee.jsp");
                request.setAttribute("data", vector);
                request.setAttribute("title", "Employee Manager");
                dispath.forward(request, response);
            }
            
            
            
            if (service.equals("loginEmployee")) {
                String userName = request.getParameter("userName");
                String passWord = request.getParameter("passWord");
                String submit = request.getParameter("submit");
                 if(submit == null){
                 request.getRequestDispatcher("/jsp/loginEmployee.jsp").forward(request, response);
             }
                // Check if username and password are provided
//                if (userName == null || passWord == null || userName.isEmpty() || passWord.isEmpty()) {
//                    request.setAttribute("messageNull", "Please enter username and password");
//                    request.getRequestDispatcher("/jsp/loginEmployee.jsp").forward(request, response);
//                    return;
//                }

                // Check if username is numeric
                if (!userName.matches("\\d+")) {
                    request.setAttribute("messageFail", "UserName must be numeric");
                    request.getRequestDispatcher("/jsp/loginEmployee.jsp").forward(request, response);
                   
                }

                // Proceed with login
                Employee emp = dao.getLogin(Integer.parseInt(userName), passWord);
               

                if ( emp == null) {
                    request.setAttribute("messageNull", "UserName or PassWord incorrect");
                    request.getRequestDispatcher("/jsp/loginEmployee.jsp").forward(request, response);
                } else {
                    // Success, set employee session and redirect
                    session.setAttribute("employee", emp);
                    response.sendRedirect("EmployeeURL?service=listAllEmployee");
                }
            }
            if(service.equals("logoutEmployee")){
                session.invalidate();
                response.sendRedirect("EmployeeURL?service=loginEmployee");
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
