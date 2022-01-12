/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import models.User;
import repository.UserRepository;

/**
 *
 * @author borges
 */
@ManagedBean(name = "login")
@RequestScoped
public class LoginController implements Serializable {

    private String email;
    private String password;
    private User user;

    private final UserRepository repository;

    public LoginController() {
        repository = new UserRepository();
    }

    public String login() {
        try {

            boolean loginVerified = repository.loginVerify(email, password);
            if (loginVerified) {
                HttpSession session = (HttpSession) FacesContext.
                        getCurrentInstance().getExternalContext().getSession(false);
                user = repository.findUserByEmail(email);

                session.setAttribute("user", user);
                session.setAttribute("user_email", user.getEmail());
                session.setAttribute("user_id", user.getId());

                session.setMaxInactiveInterval(60 * 2);

                return "restricted/dashboard.xhtml";
            }
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Verifique seu login ou senha!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
//            return "returnestrito/
        } catch (Exception ex) {
            ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERRO", "Erro ao efetuar login!"));
            return null;
        }

    }

    public String logout() {
        try {
            HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            sessao.invalidate();
           
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERRO", "Erro ao efetuar login!"));

        }
         return "/";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
