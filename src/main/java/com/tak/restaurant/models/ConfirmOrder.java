package com.tak.restaurant.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ConfirmOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int qty, price, pid, c_status, paid_status, amount, tid;
    private String f_name, type, cmt, t_name;

    public ConfirmOrder() {
    }

    public ConfirmOrder(int qty, int price, int pid, int c_status, int paid_status, int amount, int tid, String f_name, String type, String cmt, String t_name) {
        this.qty = qty;
        this.price = price;
        this.pid = pid;
        this.c_status = c_status;
        this.paid_status = paid_status;
        this.amount = amount;
        this.tid = tid;
        this.f_name = f_name;
        this.type = type;
        this.cmt = cmt;
        this.t_name = t_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getC_status() {
        return c_status;
    }

    public void setC_status(int c_status) {
        this.c_status = c_status;
    }

    public int getPaid_status() {
        return paid_status;
    }

    public void setPaid_status(int paid_status) {
        this.paid_status = paid_status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }
}