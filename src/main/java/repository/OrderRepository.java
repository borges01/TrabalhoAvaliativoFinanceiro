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
import models.Order;

/**
 *
 * @author borges
 */
public class OrderRepository implements CRUD<Order> {

    public List<Order> findLastOrders(int accountId, int userId) throws SQLException {
        Connection connection = null;
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT \n"
                + "    o.id AS 'id',\n"
                + "    a.name AS 'account',\n"
                + "    a.id AS 'accountId',\n"
                + "    oc.description AS 'category',\n"
                + "    oc.id AS 'categoryId',\n"
                + "    o.description as 'description',\n"
                + "    o.amount as 'amount',\n"
                + "    o.created_at as 'createdAt',\n"
                + "    o.updated_at as 'updatedAt',\n"
                + "    u.first_name as 'user',\n"
                + "    u.id as 'userId'\n"
                + "FROM\n"
                + "    ifc.order o\n"
                + "        JOIN\n"
                + "    ifc.account a ON o.account_id = a.id\n"
                + "        JOIN\n"
                + "    ifc.order_category oc ON o.category_id = oc.id\n"
                + "        JOIN\n"
                + "    ifc.user u ON o.user_id = u.id WHERE o.account_id = ? AND o.user_id = ?";

        try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accountId);
            ps.setInt(2, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                orders.add(
                        new Order(
                                rs.getInt("id"),
                                rs.getString("account"),
                                rs.getInt("accountId"), rs.getString("category"),
                                rs.getInt("categoryId"),
                                rs.getString("description"),
                                rs.getString("user"),
                                rs.getInt("userId"),
                                rs.getDouble("amount"),
                                rs.getDate("createdAt"),
                                rs.getDate("updatedAt")
                        ));
            }
            return orders;
        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }

    public List<Order> myOrders(int userId) throws SQLException {
        Connection connection = null;
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT \n"
                + "    o.id AS 'id',\n"
                + "    a.name AS 'account',\n"
                + "    a.id AS 'accountId',\n"
                + "    oc.description AS 'category',\n"
                + "    oc.id AS 'categoryId',\n"
                + "    o.description as 'description',\n"
                + "    o.amount as 'amount',\n"
                + "    o.created_at as 'createdAt',\n"
                + "    o.updated_at as 'updatedAt',\n"
                + "    u.first_name as 'user',\n"
                + "    u.id as 'userId'\n"
                + "FROM\n"
                + "    ifc.order o\n"
                + "        JOIN\n"
                + "    ifc.account a ON o.account_id = a.id\n"
                + "        JOIN\n"
                + "    ifc.order_category oc ON o.category_id = oc.id\n"
                + "        JOIN\n"
                + "    ifc.user u ON o.user_id = u.id WHERE o.user_id = ?";

        try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                orders.add(
                        new Order(
                                rs.getInt("id"),
                                rs.getString("account"),
                                rs.getInt("accountId"), rs.getString("category"),
                                rs.getInt("categoryId"),
                                rs.getString("description"),
                                rs.getString("user"),
                                rs.getInt("userId"),
                                rs.getDouble("amount"),
                                rs.getDate("createdAt"),
                                rs.getDate("updatedAt")
                        ));
            }
            return orders;
        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    public void insert(Order object) throws SQLException {
        Connection connection = null;
        String sql = "INSERT INTO ifc.order(amount, description, account_id, category_id, user_id) VALUES (?,?,?,?,?)";

        try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, object.getAmount());
            ps.setString(2, object.getDescription());
            ps.setInt(3, object.getAccountId());
            ps.setInt(4, object.getCategoryId());
            ps.setInt(5, object.getUserId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    public void update(int pk, Order object) throws SQLException {
        Connection connection = null;
        String sql = "UPDATE ifc.order SET description = ?, account_id = ?, category_id = ?, amount= ? WHERE id = ?";

        try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, object.getDescription());
            ps.setInt(2, object.getAccountId());
            ps.setInt(3, object.getCategoryId());
            ps.setDouble(4, object.getAmount());
            ps.setInt(5, object.getId());

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
        String sql = "DELETE FROM ifc.order WHERE id = ?";

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
    public Order find(int pk) throws SQLException {

        Order order = new Order();
        Connection connection = null;
        String sql = "SELECT \n"
                + "    o.id AS 'id',\n"
                + "    a.name AS 'account',\n"
                + "    a.id AS 'accountId',\n"
                + "    oc.description AS 'category',\n"
                + "    oc.id AS 'categoryId',\n"
                + "    o.description as 'description',\n"
                + "    o.amount as 'amount',\n"
                + "    o.created_at as 'createdAt',\n"
                + "    o.updated_at as 'updatedAt',\n"
                + "    u.first_name as 'user',\n"
                + "    u.id as 'userId'\n"
                + "FROM\n"
                + "    ifc.order o\n"
                + "        JOIN\n"
                + "    ifc.account a ON o.account_id = a.id\n"
                + "        JOIN\n"
                + "    ifc.order_category oc ON o.category_id = oc.id\n"
                + "        JOIN\n"
                + "    ifc.user u ON o.user_id = u.id WHERE o.id = ?";

        try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pk);

            ResultSet result = ps.executeQuery();
            if (result.next()) {
                order.setId(result.getInt("id"));
                order.setAccountId(result.getInt("accountId"));
                order.setUserId(result.getInt("userId"));
                order.setCategoryId(result.getInt("categoryId"));
                order.setAccount(result.getString("account"));
                order.setCategory(result.getString("category"));
                order.setUser(result.getString("user"));
                order.setAmount(result.getDouble("amount"));
                order.setDescription(result.getString("description"));
                order.setCreatedAt(result.getDate("createdAt"));
                order.setUpdatedAt(result.getDate("updatedAt"));
            }
            return order;

        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    public List<Order> findAll() throws SQLException {
        Connection connection = null;
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT \n"
                + "    o.id AS 'id',\n"
                + "    a.name AS 'account',\n"
                + "    a.id AS 'accountId',\n"
                + "    oc.description AS 'category',\n"
                + "    oc.id AS 'categoryId',\n"
                + "    o.description as 'description',\n"
                + "    o.amount as 'amount',\n"
                + "    o.created_at as 'createdAt',\n"
                + "    o.updated_at as 'updatedAt',\n"
                + "    u.first_name as 'user',\n"
                + "    u.id as 'userId'\n"
                + "FROM\n"
                + "    ifc.order o\n"
                + "        JOIN\n"
                + "    ifc.account a ON o.account_id = a.id\n"
                + "        JOIN\n"
                + "    ifc.order_category oc ON o.category_id = oc.id\n"
                + "        JOIN\n"
                + "    ifc.user u ON o.user_id = u.id";

        try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                orders.add(
                        new Order(
                                rs.getInt("id"),
                                rs.getString("account"),
                                rs.getInt("accountId"), rs.getString("category"),
                                rs.getInt("categoryId"),
                                rs.getString("description"),
                                rs.getString("user"),
                                rs.getInt("userId"),
                                rs.getDouble("amount"),
                                rs.getDate("createdAt"),
                                rs.getDate("updatedAt")
                        ));
            }
            return orders;
        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }

}
