/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import db.Conn;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.OrderCategory;

/**
 *
 * @author borges
 */
public class OrderCategoryRepository implements CRUD<OrderCategory> {

    @Override
    public void insert(OrderCategory object) throws SQLException {
        Connection connection = null;
        String sql = "INSERT INTO ifc.order_category(name, description) VALUES (?,?)";

        try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, object.getName());
            ps.setString(2, object.getDescription());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    public void update(int pk, OrderCategory object) throws SQLException {
        Connection connection = null;
        String sql = "UPDATE order_category SET name = ?, description = ? WHERE id = ?";

        try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, object.getName());
            ps.setString(2, object.getDescription());
            ps.setInt(3, pk);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    public void delete(int pk) throws SQLException {
        Connection connection = null;
        String sql = "DELETE FROM order_category WHERE id = ?";

        try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pk);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    public OrderCategory find(int pk) throws SQLException {

        OrderCategory orderCategory = new OrderCategory();
        Connection connection = null;
        String sql = "SELECT * FROM ifc.order_category WHERE id = ?";

        try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pk);

            ResultSet result = ps.executeQuery();
            if (result.next()) {
                orderCategory.setId(result.getInt("id"));
                orderCategory.setName(result.getString("name"));
                orderCategory.setDescription(result.getString("description"));
                orderCategory.setCreatedAt(result.getDate("created_at"));
                orderCategory.setUpdatedAt(result.getDate("updated_at"));
            }
            return orderCategory;

        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    public List<OrderCategory> findAll() throws SQLException {
        Connection connection = null;
        List<OrderCategory> orderCategories = new ArrayList<>();
        String sql = "SELECT * FROM ifc.order_category";

        try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                orderCategories.add(
                        new OrderCategory(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getDate("created_at"),
                                rs.getDate("updated_at")
                        ));
            }
            return orderCategories;
        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }
}
