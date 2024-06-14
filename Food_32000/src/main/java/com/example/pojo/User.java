package com.example.pojo;
import java.util.List;

public class User {
    private String userId;
    private String password;
    private String userName;
    private int userSex;
    private String userImg;
    private int delTag;
    private List<Cart> cart;
    private List<DeliveryAddress> deliveryAddresses;
    private List<Orders> orders;
    public void addCarts(Cart cart){
        this.cart.add(cart);
        cart.setUser(this);
    }

    public void addOrders(Orders orders){
        this.orders.add(orders);
        orders.setUser(this);
    }

    public void addDeliveryAddresses(DeliveryAddress deliveryAddress){
        this.deliveryAddresses.add(deliveryAddress);
        deliveryAddress.setUser(this);
    }
    public void removeDeliveryAddress(DeliveryAddress deliveryAddress) {
        if (deliveryAddress != null) {
            this.deliveryAddresses.remove(deliveryAddress);
            deliveryAddress.setUser(null);
        }
    }
    public void removeCarts(Cart cart){
        this.cart.remove(cart);
        cart.setUser(null);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public int getDelTag() {
        return delTag;
    }

    public void setDelTag(int delTag) {
        this.delTag = delTag;
    }

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }

    public List<DeliveryAddress> getDeliveryAddresses() {
        return deliveryAddresses;
    }

    public void setDeliveryAddresses(List<DeliveryAddress> deliveryAddresses) {
        this.deliveryAddresses = deliveryAddresses;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}
