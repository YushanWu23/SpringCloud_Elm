package com.elm.dao;

import com.elm.pojo.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdersDao extends JpaRepository<Orders,Integer> {
    Orders findOrdersByOrderId(int orderId);
    List<Orders> findOrdersByUserUserId(String userId);

}
