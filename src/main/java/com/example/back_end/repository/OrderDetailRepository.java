package com.example.back_end.repository;


import com.example.back_end.entity.Order;
import com.example.back_end.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {

    List<OrderDetail> findByOrderId(Integer orderId);

}
