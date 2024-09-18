package com.example.back_end.service.customer;

import com.example.back_end.dto.customer.request.ReturnDetailRequest;
import com.example.back_end.dto.customer.request.ReturnRequest;
import com.example.back_end.dto.customer.response.ReturnDetailResponse;
import com.example.back_end.dto.customer.response.ReturnResponse;
import com.example.back_end.entity.Order;
import com.example.back_end.entity.Product;
import com.example.back_end.entity.Return;
import com.example.back_end.entity.ReturnDetail;
import com.example.back_end.exception.AppException;
import com.example.back_end.repository.OrderRepository;
import com.example.back_end.repository.ProductRepository;
import com.example.back_end.repository.ReturnDetailRepository;
import com.example.back_end.repository.ReturnRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReturnImpl implements ReturnService{
    private final ReturnRepository returnRepository;
    private final ReturnDetailRepository returnDetailRepository;

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    @Autowired
    public ReturnImpl(ReturnRepository returnRepository, ReturnDetailRepository returnDetailRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.returnRepository = returnRepository;
        this.returnDetailRepository = returnDetailRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    @Override
    public ReturnResponse requestReturn(Integer userId, ReturnRequest request) {
        // Lấy thông tin đơn hàng từ orderId trong request
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new AppException("Order not found"));

        // Kiểm tra xem đơn hàng có thuộc về user không
        if (!order.getUser().getId().equals(userId)) {
            throw new AppException("Order does not belong to the user");
        }

        // Tạo yêu cầu trả hàng
        Return returnOrder = new Return();
        returnOrder.setOrder(order);
        returnOrder.setReason(request.getReason());
        returnOrder.setStatus(Return.ReturnStatus.PENDING);
        returnRepository.save(returnOrder);

        // Danh sách trả hàng
        List<ReturnDetail> returnDetails = new ArrayList<>();

        // Lặp qua các sản phẩm trong yêu cầu trả hàng
        for (ReturnDetailRequest detailRequest : request.getReturnDetails()) {
            // Lấy thông tin sản phẩm từ ID
            Product product = productRepository.findById(detailRequest.getProductId())
                    .orElseThrow(() -> new AppException("Product not found"));

            // Kiểm tra xem sản phẩm có thuộc đơn hàng hay không
            boolean productInOrder = order.getOrderDetails().stream()
                    .anyMatch(orderDetail -> orderDetail.getProduct().getId().equals(detailRequest.getProductId()));

            if (!productInOrder) {
                throw new AppException("Product not found in order");
            }

            // Tạo chi tiết trả hàng
            ReturnDetail returnDetail = new ReturnDetail();
            returnDetail.setProduct(product);
            returnDetail.setReturnOrder(returnOrder);
            returnDetail.setQuantity(detailRequest.getQuantity());

            // Tính toán tiền hoàn lại dựa trên giá của sản phẩm và số lượng trả lại
            double refundAmount = product.getPrice() * detailRequest.getQuantity();
            returnDetail.setRefundAmount(BigDecimal.valueOf(refundAmount));

            returnDetailRepository.save(returnDetail);
            returnDetails.add(returnDetail);
        }

        // Chuyển đổi kết quả thành DTO trả về
        return convertToDTO(returnOrder, returnDetails);
    }

    private ReturnResponse convertToDTO(Return returnOrder, List<ReturnDetail> returnDetails) {
        List<ReturnDetailResponse> detailResponses = returnDetails.stream()
                .map(this::convertToReturnDetailDTO)
                .collect(Collectors.toList());

        ReturnResponse response = new ReturnResponse();
        response.setId(returnOrder.getId());
        response.setOrderId(returnOrder.getOrder().getId());
        response.setReason(returnOrder.getReason());
        response.setStatus(returnOrder.getStatus().toString());
        response.setReturnDate(returnOrder.getReturnDate().toString());
        response.setReturnDetails(detailResponses);

        return response;
    }

    private ReturnDetailResponse convertToReturnDetailDTO(ReturnDetail returnDetail) {
        ReturnDetailResponse response = new ReturnDetailResponse();
        response.setProductId(returnDetail.getProduct().getId());
        response.setProductName(returnDetail.getProduct().getName());
        response.setQuantity(returnDetail.getQuantity());
        response.setRefundAmount(returnDetail.getRefundAmount());
        return response;
    }

}

