/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import db.Conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.User;

/**
 *
 * @author borges
 */
public class UserRepository implements CRUD<User> {

    public boolean loginVerify(String email, String password) throws SQLException {
//        throw new UnsupportedOperationException("Implemente esse método");
        Connection connection = null;
        String sql = "SELECT * FROM user WHERE email = ? and password = ?";

        try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

            return false;
        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }
    @Override
    public void insert(User object) throws SQLException {
        Connection connection = null;
        String sql = "INSERT INTO user(first_name, last_name, document, password, email) VALUES (?,?,?,?,?)";

         try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, object.getFirstName());
            ps.setString(2, object.getLastName());
            ps.setString(3, object.getDocument());
            ps.setString(4, object.getPassword());
            ps.setString(5, object.getEmail());

            ps.executeUpdate();
           
        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    public void update(int pk, User object) throws SQLException {
//        Connection connection = null;null
//        String sql = "UPDATE contas SET titulo = ?, saldo_inicial = ? WHERE id = ?";
//        
//         try {
//            connection = Conn.getConnection();
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, objeto.getTitulo());
//            ps.setDouble(2, objeto.getSaldoInicial());
//            ps.setInt(3, objeto.getId());
//
//            ps.executeUpdate();
//           
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            connection.close();
//        }
    }

    @Override
    public void delete(int pk) throws SQLException {
        Connection connection = null;
        String sql = "DELETE FROM user WHERE id = ?";
        
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
    public User find(int pk) throws SQLException {
        
        User user = new User();
        Connection connection = null;
        String sql = "SELECT * FROM user WHERE id = ?";
        
         try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pk);

            ResultSet result = ps.executeQuery();
            if (result.next()) {
               user.setId(result.getInt("id"));
               user.setFirstName(result.getString("first_name"));
               user.setLastName(result.getString("last_name"));
               user.setEmail(result.getString("email"));
               user.setDocument(result.getString("document"));
               user.setAvatar(result.getString("avatar"));
               user.setCreatedAt(result.getDate("created_at"));
               user.setUpdatedAt(result.getDate("updated_at"));
            }
            return user;
           
        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    public List<User> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User findUserByEmail(String email) throws SQLException {
        
        User user = new User();
        Connection connection = null;
        String sql = "SELECT * FROM user WHERE email = ?";
        
         try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet result = ps.executeQuery();
            if (result.next()) {
               user.setId(result.getInt("id"));
               user.setFirstName(result.getString("first_name"));
               user.setLastName(result.getString("last_name"));
               user.setEmail(result.getString("email"));
               user.setDocument(result.getString("document"));
               user.setAvatar(result.getString("avatar"));
               user.setCreatedAt(result.getDate("created_at"));
               user.setUpdatedAt(result.getDate("updated_at"));
            }
            return user;
           
        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }
}
