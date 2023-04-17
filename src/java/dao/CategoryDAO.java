/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Category;
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
public class CategoryDAO {

    Connection conn = null;

    public CategoryDAO() {
        conn = DbConnection.getConnection();
    }

    public List<Category> getAll() {
        List<Category> lstCate = new ArrayList<>();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from tblCategory");
            while (rs.next()) {
                Category c = new Category(rs.getInt("CategoryId"), rs.getString("CategoryName"));
                lstCate.add(c);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstCate;
    }

    public List<Category> getByName(String name) {
        List<Category> lstCate = new ArrayList<>();

        try {
            PreparedStatement pst = conn.prepareStatement("select * from tblCategory where categoryName like ?");
            pst.setString(1, "%" + name + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Category c = new Category(rs.getInt("CategoryId"), rs.getString("CategoryName"));
                lstCate.add(c);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstCate;
    }

    public Category getById(Integer id) {
        Category c = null;

        try {

            PreparedStatement pst = conn.prepareStatement("select * from tblCategory where categoryId=?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                c = new Category(rs.getInt("CategoryId"), rs.getString("CategoryName"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public Integer insert(Category c) {
        try {
            PreparedStatement pst = conn.prepareStatement("insert into tblCategory values(?)");
            pst.setString(1, c.getCategoryName());
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Integer update(Category c) {
        try {
            PreparedStatement pst = conn.prepareStatement("update tblCategory set categoryName=? where categoryId=?");
            pst.setString(1, c.getCategoryName());
            pst.setInt(2, c.getCategoryId());
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Integer delete(Integer id) {
        Category c = null;

        try {

            PreparedStatement pst = conn.prepareStatement("delete from tblCategory where categoryId=?");
            pst.setInt(1, id);
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
