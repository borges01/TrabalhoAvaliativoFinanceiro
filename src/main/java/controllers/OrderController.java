/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import models.Order;
import models.Account;
import models.OrderCategory;
import models.OrderCategory;
import repository.CRUD;
import repository.OrderRepository;
import repository.OrderCategoryRepository;
import repository.AccountRepository;

/**
 *
 * @author borges
 */
@ManagedBean(name = "order")
@SessionScoped
public class OrderController implements Serializable {

    private Order order;
    private List<Order> orders;
    private List<Account> accounts;
    private List<OrderCategory> orderCategories;
    private int loggedUserid;
    private final OrderRepository repository;
    private final OrderCategoryRepository orderCategoriesRepository;
    private final AccountRepository accountsRepository;


    public OrderController() {
        order = new Order();
        repository = new OrderRepository();
        orderCategoriesRepository = new OrderCategoryRepository();
        accountsRepository = new AccountRepository();
        try {
HttpSession session = (HttpSession) FacesContext.
                    getCurrentInstance().getExternalContext().getSession(false);
            loggedUserid = (int) session.getAttribute("user_id");

            orders = repository.myOrders(loggedUserid);
            orderCategories = orderCategoriesRepository.findAll();
            accounts = accountsRepository.myAccounts(loggedUserid);

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERRO", "Oops! " + e.getMessage()));
        }
    }

    public String save() {
        try {
            HttpSession session = (HttpSession) FacesContext.
                    getCurrentInstance().getExternalContext().getSession(false);
            int loggedUserId = (int) session.getAttribute("user_id");

            if (order.getId() == null) {
                order.setUserId(loggedUserId);
                repository.insert(order);
            } else {
                repository.update(order.getId(), order);
            }
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO", "Conta salva com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            orders = repository.myOrders(loggedUserid);
            orderCategories = orderCategoriesRepository.findAll();
            accounts = accountsRepository.myAccounts(loggedUserid);

            order = new Order();
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
        return "show.xhtml";
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<OrderCategory> getOrderCategories() {
        return orderCategories;
    }

    public void setOrderCategories(List<OrderCategory> orderCategories) {
        this.orderCategories = orderCategories;
    }

}
