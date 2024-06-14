package com.example.pojo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Orders {
    private Integer orderId;
    private User user;
    private Business business;
    private String orderDate;
    private Double orderTotal;
    private DeliveryAddress deliveryAddress;
    private int orderState;

    private List<OrderDetailet> orderDetailets = new ArrayList<>();
    public void addOrderDetail(OrderDetailet orderDetailet){
        this.orderDetailets.add(orderDetailet);
        orderDetailet.setOrders(this);
    }

    public Orders(){
        this.orderDate = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString();
        this.orderState = 0;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public List<OrderDetailet> getOrderDetailets() {
        return orderDetailets;
    }

    public void setOrderDetailets(List<OrderDetailet> orderDetailets) {
        this.orderDetailets = orderDetailets;
    }
}
