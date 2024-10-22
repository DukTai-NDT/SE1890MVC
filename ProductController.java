package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import entity.Product;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOProduct;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
@WebServlet(urlPatterns = {"/ProductURL"})
public class ProductController extends HttpServlet {

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
        //Display all product
        DAOProduct dao = new DAOProduct();

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if (service == null) {
                service = "listAllProducts";
            }
            if (service.equals("updateProduct")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int pid = Integer.parseInt(request.getParameter("pid"));
                    Vector<Product> vector = dao.getProducts("Select * from Products where ProductID=" + pid);
                    request.setAttribute("vector", vector);
                    //Show form
                    ResultSet rsSup = dao.getData("SELECT   [SupplierID] ,[CompanyName] FROM [SE1890].[dbo].[Suppliers]");
                    ResultSet rsCate = dao.getData("SELECT  [CategoryID]  ,[CategoryName] FROM [SE1890].[dbo].[Categories]");
                    request.setAttribute("rsSup", rsSup);
                    request.setAttribute("rsCate", rsCate);
                    request.getRequestDispatcher("/jsp/updateProduct.jsp").forward(request, response);
                } else {
                    //getdata
                    int ProductID = Integer.parseInt(request.getParameter("ProductID"));
                    String ProductName = request.getParameter("ProductName");
                    String SupplierID = request.getParameter("SupplierID");
                    String CategoryID = request.getParameter("CategoryID");
                    String QuantityPerUnit = request.getParameter("QuantityPerUnit");
                    String UnitPrice = request.getParameter("UnitPrice");
                    String UnitsInStock = request.getParameter("UnitsInStock");
                    String UnitsOnOrder = request.getParameter("UnitsOnOrder");
                    String ReorderLevel = request.getParameter("ReorderLevel");
                    String Discontinued = request.getParameter("Discontinued");
                    //check data
                    if (ProductName.equals("")) {
                        out.print("Product name is not empty");
                    }
                    //convert

                    int SupplierId = Integer.parseInt(SupplierID),
                            CategoryId = Integer.parseInt(CategoryID);
                    double UnitPricE = Double.parseDouble(UnitPrice);
                    int UnitsInStocK = Integer.parseInt(UnitsInStock),
                            UnitsOnOrdeR = Integer.parseInt(UnitsOnOrder),
                            ReorderLeveL = Integer.parseInt(ReorderLevel);
                    boolean DiscontinueD = (Integer.parseInt(Discontinued) == 1 ? true : false);
                    Product pro = new Product(ProductID, ProductName, SupplierId, CategoryId, QuantityPerUnit, UnitPricE, UnitsInStocK, UnitsOnOrdeR, ReorderLeveL, DiscontinueD);
                    int n = dao.updateProduct(pro);
                    response.sendRedirect("ProductURL?service=listAllProducts");
                }

            }
            if (service.equals("deleteProduct")) {
                dao.deleteProduct(Integer.parseInt(request.getParameter("pid")));
                response.sendRedirect("ProductURL?service=listAllProducts");

            }
            if (service.equals("insertProduct")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    //Show form
                    ResultSet rsSup = dao.getData("SELECT   [SupplierID] ,[CompanyName] FROM [SE1890].[dbo].[Suppliers]");
                    ResultSet rsCate = dao.getData("SELECT  [CategoryID]  ,[CategoryName] FROM [SE1890].[dbo].[Categories]");
                    request.setAttribute("rsSup", rsSup);
                    request.setAttribute("rsCate", rsCate);
                    request.getRequestDispatcher("/jsp/insertProduct.jsp").forward(request, response);
                } else {
                    //getdata
                    String ProductName = request.getParameter("ProductName");
                    String SupplierID = request.getParameter("SupplierID");
                    String CategoryID = request.getParameter("CategoryID");
                    String QuantityPerUnit = request.getParameter("QuantityPerUnit");
                    String UnitPrice = request.getParameter("UnitPrice");
                    String UnitsInStock = request.getParameter("UnitsInStock");
                    String UnitsOnOrder = request.getParameter("UnitsOnOrder");
                    String ReorderLevel = request.getParameter("ReorderLevel");
                    String Discontinued = request.getParameter("Discontinued");
                    //check data
                    if (ProductName.equals("")) {
                        out.print("Product name is not empty");
                    }
                    //convert

                    int SupplierId = Integer.parseInt(SupplierID),
                            CategoryId = Integer.parseInt(CategoryID);
                    double UnitPricE = Double.parseDouble(UnitPrice);
                    int UnitsInStocK = Integer.parseInt(UnitsInStock),
                            UnitsOnOrdeR = Integer.parseInt(UnitsOnOrder),
                            ReorderLeveL = Integer.parseInt(ReorderLevel);
                    boolean DiscontinueD = (Integer.parseInt(Discontinued) == 1 ? true : false);
                    Product pro = new Product(ProductName, SupplierId, CategoryId, QuantityPerUnit, UnitPricE, UnitsInStocK, UnitsOnOrdeR, ReorderLeveL, DiscontinueD);
                    int n = dao.addProduct(pro);
                    response.sendRedirect("ProductURL?service=listAllProducts");
                }

            }
            if(service.equals("listAllProductsWithCateID")){
                int cateID =Integer.parseInt(request.getParameter("id")) ;
                String sql = "  Select * from Products where CategoryID = "+cateID;
                Vector<Product> vector = dao.getProducts(sql);
                //select view
                RequestDispatcher dispath = request.getRequestDispatcher("/jsp/displayProduct.jsp");
                //set data
                request.setAttribute("data", vector);
                request.setAttribute("title", "Product manager");
                // Run - view
                dispath.forward(request, response);
               
            }
            if(service.equals("listAllProductsWithSuppID")){
                int supID =Integer.parseInt(request.getParameter("id")) ;
                String sql = "  Select * from Products where [SupplierID] = "+supID;
                Vector<Product> vector = dao.getProducts(sql);
                //select view
                RequestDispatcher dispath = request.getRequestDispatcher("/jsp/displayProduct.jsp");
                //set data
                request.setAttribute("data", vector);
                request.setAttribute("title", "Product manager");
                // Run - view
                dispath.forward(request, response);
               
            }
            
            if (service.equals("listAllProducts")) {
                String choose = request.getParameter("choose");
                
                

                String sql = "select * from Products";
                String submit = request.getParameter("submit");
                if (submit != null) {

                    String pname = request.getParameter("pname");
                    sql = "select *\n"
                            + "from Products\n"
                            + "where ProductName like '%" + pname + "%'";
                }
                Vector<Product> vector = dao.getProducts(sql);
                //select view
                RequestDispatcher dispath = request.getRequestDispatcher("/jsp/displayProduct.jsp");
                //set data
                request.setAttribute("data", vector);
                request.setAttribute("title", "Product manager");
                // Run - view
                dispath.forward(request, response);
//                String submit = request.getParameter("submit");
//                if (submit == null) {
//                    sql = "select * from Products";
//                } else {
//                    String pname = request.getParameter("pname");
//                    sql = "select *\n"
//                            + "from Products\n"
//                            + "where ProductName like '%" + pname + "%'";
//                }
            
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
