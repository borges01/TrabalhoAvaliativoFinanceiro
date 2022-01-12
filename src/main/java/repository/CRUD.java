/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author borges
 */
public interface CRUD<T> {

    public void insert(T object) throws SQLException;

    public void update(int pk, T object) throws SQLException;

    public void delete(int pk) throws SQLException;

    public List<T> findAll() throws SQLException;

    public T find(int pk) throws SQLException;
}
