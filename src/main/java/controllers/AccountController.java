/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import models.Account;
import models.Order;
import repository.CRUD;
import repository.AccountRepository;
import repository.OrderRepository;

/**
 *
 * @author borges
 */
@ManagedBean(name = "account")
@SessionScoped
public class AccountController implements Serializable {

    private Account account;
    private List<Account> accounts;
    private List<Order> orders;
    private int loggedUserid;
    private final AccountRepository repository;
    private final OrderRepository orderRepository;

    public AccountController() {
        account = new Account();
        repository = new AccountRepository();
        orderRepository = new OrderRepository();
        try {
            HttpSession session = (HttpSession) FacesContext.
                    getCurrentInstance().getExternalContext().getSession(false);
            loggedUserid = (int) session.getAttribute("user_id");

            accounts = repository.myAccounts(loggedUserid);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERRO", "Oops! " + e.getMessage()));
        }
    }

    public String save() {
        try {

            if (account.getId() == null) {
                account.setUserId(loggedUserid);

                repository.insert(account);
            } else {
                repository.update(account.getId(), account);
            }
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO", "Conta salva com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            accounts = repository.myAccounts(loggedUserid);
            account = new Account();
            return "index.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Oops! " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }

    public String create() {
        return "create.xhtml";
    }

    public String update() {
        return "create.xhtml";
    }

    public String show() {
        try {
            orders = orderRepository.findLastOrders(account.getId(), loggedUserid);
            return "show.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Oops! " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }

    }

    public String delete() {
        try {

            repository.delete(account.getId());

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO", "Lançamento deletado com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            accounts = repository.myAccounts(loggedUserid);
            account = new Account();

            return "index.xhtml";
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Oops! " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
