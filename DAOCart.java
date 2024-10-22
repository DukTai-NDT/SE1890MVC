/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Cart;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Admin
 */
public class DAOCart extends DBConnection{
    public Cart getCart(int pid){
        Cart cart = null;
        String sql = " select p.ProductID,p.ProductName,p.UnitPrice\n" +
"  from Products p where p.ProductID = "+pid;
        
        try {
            Statement state = conn.createStatement();
        ResultSet rs = state.executeQuery(sql);
        if(rs.next()){
        cart = new Cart(rs.getInt(1), rs.getString(2), rs.getDouble(3),
                0,0);
        }} catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cart;
    }
}
