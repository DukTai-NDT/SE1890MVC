/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.OrderDetails;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOOrderDetails;

/**
 *
 * @author Admin
 */
@WebServlet(name = "OrderDetailController", urlPatterns = {"/OrderDetailURL"})
public class OrderDetailController extends HttpServlet {

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
        DAOOrderDetails dao = new DAOOrderDetails();
        String sql = "SELECT * FROM  [Order Details] ";
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service.equals("insertOrderDetail")) {
                String OrderID = request.getParameter("OrderID");
                String ProductID = request.getParameter("ProductID");
                String UnitPrice = request.getParameter("UnitPrice");
                String Quantity = request.getParameter("Quantity");
                String Discount = request.getParameter("Discount");

                int OrderId = Integer.parseInt(OrderID);
                int ProductId = Integer.parseInt(ProductID);
                double UnitPricE = Double.parseDouble(UnitPrice);
                int QuantitY = Integer.parseInt(Quantity);
                double DiscounT = Double.parseDouble(Discount);
                
                int n = dao.addOrderDetail(new OrderDetails(OrderId, ProductId, UnitPricE, QuantitY, DiscounT));
                response.sendRedirect("OrderDetailURL?service=listAllOrderDetail");
            }
            if (service.equals("listAllOrderDetail")) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet OrderDetailController</title>");
                out.println("</head>");
                out.println("<body>");
                out.print("<form action=\"OrderDetailURL\" method=\"get\">\n"
                        + "        <p>Search name : <input type=\"text\" name = \"odname\" id =\"\">\n"
                        + "        <input type=\"submit\" value=\"Search\" name=\"submit\">\n"
                        + "        <input type=\"reset\" value=\"reset\" name=\"clean\">\n"
                        + "        <input type=\"hidden\" value=\"listAllOrderDetail\" name=\"service\">\n"
                        + "        </p>\n"
                        + "    </form>");
                out.print("<p><a href=\"HTML/insertOrderDetail.html\">insert Order Detail</a></p>");
                String submit = request.getParameter("submit");
                if (submit == null) {
                    sql = "SELECT * FROM  [Order Details] ";
                } else {
                    String odname = request.getParameter("odname");
                    sql = "SELECT * FROM  [Order Details] where OrderID = " + odname;
                }
                Vector<OrderDetails> vector = dao.getOrderDetails(sql);

                out.print("<table>\n"
                        + "        <tr>\n"
                        + "            <th>OrderID</th>\n"
                        + "            <th>ProductID</th>\n"
                        + "            <th>UnitPrice</th>\n"
                        + "            <th>Quantity</th>\n"
                        + "            <th>Discount</th>\n"
                        + "            <th>update</th>\n"
                        + "            <th>delete</th>\n"
                        + "\n"
                        + "\n"
                        + "        </tr>");
                for (OrderDetails orderDetails : vector) {
                    out.print(" <tr>\n"
                            + "            <td>" + orderDetails.getOrderID() + "</td>\n"
                            + "            <td>" + orderDetails.getProductID() + "</td>\n"
                            + "            <td>" + orderDetails.getUnitPrice() + "</td>\n"
                            + "            <td>" + orderDetails.getQuantity() + "</td>\n"
                            + "            <td>" + orderDetails.getDiscount() + "</td>\n"
                            + "            <td></td>\n"
                            + "            <td></td>\n"
                            + "\n"
                            + "        </tr>     ");
                }
                out.print("\n</table>");
                out.println("<h1>Servlet OrderDetailController at " + request.getContextPath() + "</h1>");
                out.println("</body>");
                out.println("</html>");
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
