/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import models.User;
import repository.CRUD;
import repository.UserRepository;

/**
 *
 * @author borges
 */
@ManagedBean(name = "register")
@RequestScoped
public class RegisterController implements Serializable {

    private CRUD repository;
    private User user;

    public RegisterController() {
        try {
            user = new User();
            repository = (CRUD) new UserRepository();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERRO", "Oops! "+e.getMessage()));
        }
    }


}
