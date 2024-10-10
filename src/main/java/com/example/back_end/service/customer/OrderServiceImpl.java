package com.example.back_end.service.customer;


import com.example.back_end.dto.customer.request.OrderDetailRequest;
import com.example.back_end.dto.customer.request.OrderRequest;
import com.example.back_end.dto.customer.request.PaymentRequest;
import com.example.back_end.dto.customer.request.ShippingRequest;
import com.example.back_end.dto.customer.response.OrderDetailResponse;
import com.example.back_end.dto.customer.response.OrderResponse;
import com.example.back_end.dto.customer.response.PaymentResponse;
import com.example.back_end.dto.customer.response.ShippingResponse;
import com.example.back_end.entity.*;
import com.example.back_end.exception.AppException;
import com.example.back_end.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ShippingRepository shippingRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ShippingMethodRepository shippingMethodRepository;

    @Transactional
    public OrderResponse placeOrder(Integer userId, OrderRequest request) {
        // Áp dụng mã khuyến mãi
        double discount = applyPromotion(request.getPromotionCode());

        // Lấy thông tin user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException("User not found"));

        // Tạo đơn hàng
        Order order = new Order();
        order.setUser(user);
        order.setShippingAddress(request.getShippingAddress());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setStatus(Order.OrderStatus.OPEN);
        order.setTotalAmount(0.0);
        orderRepository.save(order);

        double totalAmount = 0.0;

        // Thêm chi tiết đơn hàng
        for (OrderDetailRequest detailRequest : request.getOrderDetails()) {
            Product product = productRepository.findById(detailRequest.getProductId())
                    .orElseThrow(() -> new AppException("Product not found"));

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(product);
            orderDetail.setQuantity(detailRequest.getQuantity());
            orderDetail.setPrice(product.getPrice());
            orderDetail.setTotal(product.getPrice() * detailRequest.getQuantity());

            orderDetailRepository.save(orderDetail);

            totalAmount += orderDetail.getTotal();

            // Cập nhật số lượng hàng tồn kho
            product.setStock(product.getStock() - detailRequest.getQuantity());
            productRepository.save(product);

            // Xóa sản phẩm khỏi giỏ hàng
            cartRepository.deleteByUserIdAndProductId(userId, detailRequest.getProductId());
        }

        // Áp dụng giảm giá và cập nhật tổng tiền
        totalAmount -= discount;
        order.setTotalAmount(totalAmount);
        orderRepository.save(order);

        // Lưu thông tin Shipping
        for (ShippingRequest shippingRequest : request.getShippingRequests()) {
            Shipping shipping = new Shipping();
            shipping.setCarrier(shippingRequest.getCarrier());
            shipping.setShippingDate(shippingRequest.getShippingDate());
            shipping.setStatus(Shipping.ShippingStatus.valueOf(shippingRequest.getStatus()));
            shipping.setTrackingNumber(shippingRequest.getTrackingNumber());

            ShippingMethod shippingMethod = shippingMethodRepository.findById(shippingRequest.getShippingMethodId())
                    .orElseThrow(() -> new AppException("Shipping method not found"));
            shipping.setShippingMethod(shippingMethod);
            shipping.setOrder(order);

            shippingRepository.save(shipping);
        }

        // Lưu thông tin Transaction
        for (Integer transactionId : request.getTransactionIds()) {
            Transaction transaction = transactionRepository.findById(transactionId)
                    .orElseThrow(() -> new AppException("Transaction not found"));
            transaction.setOrder(order);
            transactionRepository.save(transaction);
        }

        // Xử lý thanh toán
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setOrderId(order.getId());
        paymentRequest.setAmount(totalAmount);
        paymentRequest.setPaymentMethod(request.getPaymentMethod());

        PaymentResponse paymentResponse = paymentService.processPayment(paymentRequest);

        // Cập nhật trạng thái đơn hàng nếu thanh toán thành công
        if ("SUCCESS".equals(paymentResponse.getStatus())) {
            order.setStatus(Order.OrderStatus.IN_PROGRESS);
            orderRepository.save(order);
        }

        // Trả về phản hồi đơn hàng
        return convertToOrderResponse(order);
    }

    private double applyPromotion(String promotionCode) {
        if (promotionCode == null || promotionCode.isEmpty()) {
            return 0.0;
        }

        Promotion promotion = promotionRepository.findByCode(promotionCode)
                .orElseThrow(() -> new AppException("Promotion not found"));

        return promotion.getDiscountPercentage();
    }

    private OrderResponse convertToOrderResponse(Order order) {
        List<OrderDetailResponse> orderDetails = new ArrayList<>();

        List<OrderDetail> details = orderDetailRepository.findByOrderId(order.getId());
        for (OrderDetail detail : details) {
            Product product = productRepository.findById(detail.getProduct().getId())
                    .orElseThrow(() -> new AppException("Product not found"));

            OrderDetailResponse detailResponse = new OrderDetailResponse();
            detailResponse.setId(detail.getId());
            detailResponse.setProductId(detail.getProduct().getId());
            detailResponse.setProductName(product.getName());
            detailResponse.setQuantity(detail.getQuantity());
            detailResponse.setPrice(detail.getPrice());
            detailResponse.setTotal(detail.getTotal());

            orderDetails.add(detailResponse);
        }

        List<ShippingResponse> shippingResponses = new ArrayList<>();
        List<Shipping> shippings = shippingRepository.findByOrderId(order.getId());
        for (Shipping shipping : shippings) {
            ShippingResponse shippingResponse = new ShippingResponse();
            shippingResponse.setCarrier(shipping.getCarrier());
            //shippingResponse.setShippingDate(shipping.getShippingDate());
            shippingResponse.setStatus(String.valueOf(shipping.getStatus()));
            shippingResponse.setTrackingNumber(shipping.getTrackingNumber());

            shippingResponses.add(shippingResponse);
        }

        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setUserId(order.getUser().getId());
        response.setOrderDate(order.getOrderDate().toString());
        response.setStatus(String.valueOf(order.getStatus()));
        response.setTotalAmount(order.getTotalAmount());
        response.setShippingAddress(order.getShippingAddress());
        response.setPaymentMethod(order.getPaymentMethod());
        response.setOrderDetails(orderDetails);
        response.setShippingResponses(shippingResponses);

        return response;
    }


//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Autowired
//    private OrderDetailRepository orderDetailRepository;
//
//    @Autowired
//    private CartRepository cartRepository;
//
//    @Autowired
//    private PromotionRepository promotionRepository;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PaymentService paymentService;
//
//    @Transactional
//    public OrderResponse placeOrder(Integer userId, OrderRequest request) {
//        // Áp dụng mã khuyến mãi
//        double discount = applyPromotion(request.getPromotionCode());
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new AppException("User not found"));
//
//        // Tạo đơn hàng
//        Order order = new Order();
//        order.setUser(user);
//        order.setShippingAddress(request.getShippingAddress());
//        order.setPaymentMethod(request.getPaymentMethod());
//        order.setStatus(Order.OrderStatus.valueOf("OPEN"));
//        order.setTotalAmount(0.0);
//        orderRepository.save(order);
//
//        double totalAmount = 0.0;
//
//        // Thêm chi tiết đơn hàng từ giỏ hàng
//        for (OrderDetailRequest detailRequest : request.getOrderDetails()) {
//            Product product = productRepository.findById(detailRequest.getProductId())
//                    .orElseThrow(() -> new AppException("Product not found"));
//            OrderDetail orderDetail = new OrderDetail();
//            orderDetail.setOrder(order);
//            orderDetail.setProduct(product);
//            orderDetail.setQuantity(detailRequest.getQuantity());
//            orderDetail.setPrice(product.getPrice());
//            orderDetail.setTotal(product.getPrice() * detailRequest.getQuantity());
//
//            orderDetailRepository.save(orderDetail);
//
//            totalAmount += orderDetail.getTotal();
//
//            // Cập nhật số lượng hàng tồn kho
//            product.setStock(product.getStock() - detailRequest.getQuantity());
//            productRepository.save(product);
//
//            // Xóa sản phẩm khỏi giỏ hàng
//           cartRepository.deleteByUserIdAndProductId(userId, detailRequest.getProductId());
//        }
//
//
//
//        // Áp dụng giảm giá và cập nhật tổng tiền
//        totalAmount -= discount;
//        order.setTotalAmount(totalAmount);
//        orderRepository.save(order);
//
//        // Tạo yêu cầu thanh toán và xử lý
//        PaymentRequest paymentRequest = new PaymentRequest();
//        paymentRequest.setOrderId(order.getId());
//        paymentRequest.setAmount(totalAmount);
//        paymentRequest.setPaymentMethod(request.getPaymentMethod());
//
//        PaymentResponse paymentResponse = paymentService.processPayment(paymentRequest);
//
//        // Nếu thanh toán thành công, cập nhật trạng thái đơn hàng
//        if ("SUCCESS".equals(paymentResponse.getStatus())) {
//            order.setStatus(Order.OrderStatus.IN_PROGRESS);
//            orderRepository.save(order);
//        }
//
//
//        return convertToOrderResponse(order);
//    }
//
//    private double applyPromotion(String promotionCode) {
//        if (promotionCode == null || promotionCode.isEmpty()) {
//            return 0.0;
//        }
//
//        Promotion promotion = promotionRepository.findByCode(promotionCode)
//                .orElseThrow(() -> new AppException("Promotion not found"));
//
//
//        return promotion.getDiscountPercentage();
//    }
//
//    private OrderResponse convertToOrderResponse(Order order) {
//        List<OrderDetailResponse> orderDetails = new ArrayList<>();
//
//        List<OrderDetail> details = orderDetailRepository.findByOrderId(order.getId());
//        for (OrderDetail detail : details) {
//            Product product = productRepository.findById(detail.getProduct().getId())
//                    .orElseThrow(() -> new AppException("Product not found"));
//
//            OrderDetailResponse detailResponse = new OrderDetailResponse();
//            detailResponse.setId(detail.getId());
//            detailResponse.setProductId(detail.getProduct().getId());
//            detailResponse.setProductName(product.getName());
//            detailResponse.setQuantity(detail.getQuantity());
//            detailResponse.setPrice(detail.getPrice());
//            detailResponse.setTotal(detail.getTotal());
//
//            orderDetails.add(detailResponse);
//        }
//
//        OrderResponse response = new OrderResponse();
//        response.setId(order.getId());
//        response.setUserId(order.getUser().getId());
//        response.setOrderDate(order.getOrderDate().toString());
//        response.setStatus(String.valueOf(order.getStatus()));
//        response.setTotalAmount(order.getTotalAmount());
//        response.setShippingAddress(order.getShippingAddress());
//        response.setPaymentMethod(order.getPaymentMethod());
//        response.setOrderDetails(orderDetails);
//
//        return response;
//    }


}
