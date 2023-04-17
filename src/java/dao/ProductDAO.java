/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thanh
 */
public class ProductDAO {
    Connection conn = null;

    public ProductDAO() {
        conn = DbConnection.getConnection();
    }
    
    public List<Product> getAllProduct(){
        List<Product> list = new ArrayList<>();
        
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from tblProduct");
            while (rs.next()) {
                Product p = new Product(rs.getInt("ProductId"), rs.getString("ProductName"), rs.getInt("Id_Category"), rs.getFloat("Price"), rs.getString("ProductImage"));
                list.add(p);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public List<Product> getByName(String name) {
        List<Product> lstCate = new ArrayList<>();

        try {
            PreparedStatement pst = conn.prepareStatement("select * from tblProduct where productName like ?");
            pst.setString(1, "%" + name + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt("ProductId"), rs.getString("ProductName"), rs.getInt("Id_Category"), rs.getFloat("Price"),rs.getString("ProductImage"));
                lstCate.add(p);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstCate;
    }
    
    public List<Product> getByCategory(Integer id_Category) {
        List<Product> list = new ArrayList<>();

        try {
            PreparedStatement pst = conn.prepareStatement("select * from tblProduct where id_Category = ?");
            pst.setInt(1, id_Category);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt("ProductId"), rs.getString("ProductName"), rs.getInt("Id_Category"), rs.getFloat("Price"),rs.getString("ProductImage"));
                list.add(p);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Product getById(Integer id) {
        Product p = null;

        try {

            PreparedStatement pst = conn.prepareStatement("select * from tblProduct where productId=?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                p = new Product(rs.getInt("ProductId"), rs.getString("ProductName"), rs.getInt("Id_Category"), rs.getFloat("Price"),rs.getString("ProductImage"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public Integer insert(Product p) {
        try {
            PreparedStatement pst = conn.prepareStatement("insert into tblCategory values(?,?,?,?)");
            pst.setString(1, p.getProductName());
            pst.setInt(2, p.getId_Category());
            pst.setFloat(3, p.getPrice());
            pst.setString(4, p.getProductImage());
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Integer update(Product p) {
        try {
            PreparedStatement pst = conn.prepareStatement("update tblProduct set productName=?, id_Category=?, price=?, productImage=? where productId=?");
            pst.setString(1, p.getProductName());
            pst.setInt(2, p.getId_Category());
            pst.setFloat(3, p.getPrice());
            pst.setString(4, p.getProductImage());
            pst.setInt(5, p.getProductId());
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Integer delete(Integer id) {
        Product p = null;

        try {

            PreparedStatement pst = conn.prepareStatement("delete from tblProduct where productId=?");
            pst.setInt(1, id);
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    
}
