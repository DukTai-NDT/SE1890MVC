/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Enumeration;
import model.DAOCart;
import java.util.Vector;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CartController", urlPatterns = {"/CartURL"})
public class CartController extends HttpServlet {

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
        DAOCart dao = new DAOCart();
        HttpSession session = request.getSession(true);

        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if(service == null){
                service = "showCart";
            }
            if (service.equals("add2Cart")) {
                int pid = Integer.parseInt(request.getParameter("pid"));
                Cart newCart = dao.getCart(pid);
                //kiem tra pid da ton tai chua
                if (session.getAttribute(pid + "") == null) {//first time
                    newCart.setQuantity(1);
                    session.setAttribute(pid + "", newCart);
                } else {
                    Cart oldCart = (Cart) session.getAttribute(pid + "");
                    oldCart.setQuantity(oldCart.getQuantity() + 1);
                    session.setAttribute(pid + "", oldCart);
                }
                response.sendRedirect("ProductURL");
            }

            if (service.equals("showCart")) {
                Vector<Cart> vector = new Vector<>();
                Enumeration<String> enu = session.getAttributeNames();//lay lai cot key
                while (enu.hasMoreElements()) {
                    String key = (String) enu.nextElement();
                    Object obj = session.getAttribute(key);

                    // Kiểm tra xem obj có phải là kiểu Cart không
                    if (obj instanceof Cart) {
                        Cart cart = (Cart) obj;
                        vector.add(cart);
                    }
                }
                request.setAttribute("vectorCart", vector);
                request.getRequestDispatcher("/jsp/showCart.jsp").forward(request, response);
            }
            if (service.equals("removeCart")) {
                int pid = Integer.parseInt(request.getParameter("pid"));
                Cart oldCart = (Cart)session.getAttribute(pid+"");
                if(oldCart.getQuantity() > 1){
                    oldCart.setQuantity(oldCart.getQuantity() - 1);
                    session.setAttribute(pid+"",oldCart);
                }else{
                session.removeAttribute(pid+"");
                
                }
                response.sendRedirect("CartURL");
            }
            if(service.equals("removeAllCart")){
                session.invalidate();
                response.sendRedirect("ProductURL");
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
