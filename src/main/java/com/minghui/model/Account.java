package com.minghui.model;

/**
 * 账户领域对象
 *
 * @author minghui.y BG358486
 * @create 2019-05-06 16:40
 **/
public class Account {
    private int id;

    private String name;

    private int account;

    public Account(int id, String name, int account) {
        this.id = id;
        this.name = name;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", account=" + account +
                '}';
    }
}
