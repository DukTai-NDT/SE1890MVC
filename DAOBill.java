/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Bill;
import entity.Orders;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.Vector;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class DAOBill extends DBConnection {

    

    public Vector<Bill> getBill(String sql) {
        
        Vector<Bill> vector = new Vector<>();
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {

                int OrderID = rs.getInt("OrderID");
                String OrderDate = rs.getString("OrderDate"),
                        RequiredDate = rs.getString("RequiredDate");
                String ContactName = rs.getString("ContactName")
                        ,FirstName = rs.getString("FirstName");
                int ProductID = rs.getInt("ProductID");
                        String ProductName = rs.getString("ProductName");
                
                double UnitPrice = rs.getDouble("UnitPrice");
                int Quantity = rs.getInt("Quantity");
                double Discount = rs.getDouble("Discount");
                Bill newBill = new Bill(OrderID, OrderDate, RequiredDate, ContactName, FirstName, ProductID, ProductName, UnitPrice, Quantity, Discount);
                vector.add(newBill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public static void main(String[] args) {
        DAOBill dod = new DAOBill();
        String sql = "Select odetail.OrderID,o.OrderDate,o.RequiredDate,cus.ContactName,emp.FirstName,pduct.ProductID,pduct.ProductName,odetail.UnitPrice,odetail.Quantity,odetail.Discount\n" +
"  from [Order Details] odetail join Orders o on odetail.OrderID = o.OrderID join Products pduct on odetail.ProductID = pduct.ProductID\n" +
"  join Customers cus on o.CustomerID = cus.CustomerID join Employees emp on o.EmployeeID=emp.EmployeeID\n" +
"  where o.OrderID = 10643	";
        Vector<Bill> vector= dod.getBill(sql);
        for (Bill bill : vector) {
            System.out.println(bill);
        }
//      Vector<Bill> vector = dod.getBill(sql);
//        
//       Bill bill = vector.get(vector.size()-1);
//        System.out.println(bill.getOrderID());
        
    }
}
