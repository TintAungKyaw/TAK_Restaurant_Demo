package com.tak.restaurant.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PreOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int tid, fid, f_price, amount, paid_status;
    private String f_name, type, cmt;

    public PreOrder() {
    }

    public PreOrder(int tid, int fid, int f_price, int amount, int paid_status, String f_name, String type, String cmt) {
        this.tid = tid;
        this.fid = fid;
        this.f_price = f_price;
        this.amount = amount;
        this.paid_status = paid_status;
        this.f_name = f_name;
        this.type = type;
        this.cmt = cmt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getF_price() {
        return f_price;
    }

    public void setF_price(int f_price) {
        this.f_price = f_price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPaid_status() {
        return paid_status;
    }

    public void setPaid_status(int paid_status) {
        this.paid_status = paid_status;
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
}
