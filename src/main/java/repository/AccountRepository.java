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
import javax.servlet.http.HttpSession;
import models.Account;

/**
 *
 * @author borges
 */
public class AccountRepository implements CRUD<Account> {

    public List<Account> myAccounts(Integer userId) throws SQLException {
        Connection connection = null;
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT \n"
                + "    a.id AS 'id',\n"
                + "    a.number AS 'number',\n"
                + "    a.name AS 'name',\n"
                + "    u.first_name AS 'user',\n"
                + "    a.user_id AS 'userId',\n"
                + "    a.created_at AS 'createdAt',\n"
                + "    a.created_at AS 'updatedAt'\n"
                + "FROM\n"
                + "    ifc.account a\n"
                + "    JOIN ifc.user u ON a.user_id = u.id WHERE a.user_id = ?";

        try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                accounts.add(
                        new Account(
                                rs.getInt("id"),
                                rs.getString("number"),
                                rs.getString("name"),
                                rs.getString("user"),
                                rs.getInt("userId"),
                                rs.getDate("createdAt"),
                                rs.getDate("updatedAt")
                        ));
            }
            return accounts;
        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    public void insert(Account object) throws SQLException {
        Connection connection = null;
        String sql = "INSERT INTO ifc.account(number, name, user_id) VALUES (?,?,?)";

        try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, object.getNumber());
            ps.setString(2, object.getName());
            ps.setInt(3, object.getUserId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    public void update(int pk, Account object) throws SQLException {
        Connection connection = null;
        String sql = "UPDATE ifc.account SET number = ?, name= ? WHERE id = ? AND user_id = ?";

        try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, object.getNumber());
            ps.setString(2, object.getName());
            ps.setInt(3, object.getId());
            ps.setInt(4, object.getUserId());

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
        String sql = "DELETE FROM ifc.account WHERE id = ?";

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
    public Account find(int pk) throws SQLException {

        Account account = new Account();
        Connection connection = null;
        String sql = "SELECT \n"
                + "    a.id AS 'id',\n"
                + "    a.number AS 'number',\n"
                + "    a.name AS 'name',\n"
                + "    u.first_name AS 'user',\n"
                + "    a.user_id AS 'userId',\n"
                + "    a.created_at AS 'createdAt',\n"
                + "    a.created_at AS 'updatedAt'\n"
                + "FROM\n"
                + "    ifc.account a\n"
                + "    JOIN ifc.user u ON a.user_id = u.id WHERE a.id = ?";

        try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pk);

            ResultSet result = ps.executeQuery();
            if (result.next()) {
                account.setId(result.getInt("id"));
                account.setNumber(result.getString("number"));
                account.setName(result.getString("name"));
                account.setUser(result.getString("user"));
                account.setUserId(result.getInt("userId"));
                account.setCreatedAt(result.getDate("createdAt"));
                account.setUpdateAt(result.getDate("updatedAt"));
            }
            return account;

        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    public List<Account> findAll() throws SQLException {
        Connection connection = null;
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT \n"
                + "    a.id AS 'id',\n"
                + "    a.number AS 'number',\n"
                + "    a.name AS 'name',\n"
                + "    u.first_name AS 'user',\n"
                + "    a.user_id AS 'userId',\n"
                + "    a.created_at AS 'createdAt',\n"
                + "    a.created_at AS 'updatedAt'\n"
                + "FROM\n"
                + "    ifc.account a\n"
                + "    JOIN ifc.user u ON a.user_id = u.id";

        try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                accounts.add(
                        new Account(
                                rs.getInt("id"),
                                rs.getString("number"),
                                rs.getString("name"),
                                rs.getString("user"),
                                rs.getInt("userId"),
                                rs.getDate("createdAt"),
                                rs.getDate("updatedAt")
                        ));
            }
            return accounts;
        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }
}
