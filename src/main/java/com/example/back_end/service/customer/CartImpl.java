package com.example.back_end.service.customer;

import com.example.back_end.dto.customer.request.CartRequest;
import com.example.back_end.dto.customer.response.CartResponse;
import com.example.back_end.entity.Cart;
import com.example.back_end.entity.Product;
import com.example.back_end.entity.User;
import com.example.back_end.exception.AppException;
import com.example.back_end.repository.CartRepository;
import com.example.back_end.repository.ProductRepository;
import com.example.back_end.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartImpl implements CartService{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public CartResponse addToCart(CartRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException("Product not found with ID: " + request.getProductId()));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException("User not found with ID: " + request.getUserId()));

        Optional<Cart> existingCart = cartRepository.findByUserAndProduct(user, product);
        Cart cart = null;

        if (existingCart.isPresent()) {
            cart = existingCart.get();
            cart.setQuantity(cart.getQuantity() + request.getQuantity());
          //  product.setStock(product.getStock() - cart.getQuantity());
        } else {
            cart = new Cart();
            cart.setProduct(product);
            cart.setUser(user);
            cart.setQuantity(request.getQuantity());
        }

        Cart savedCart = cartRepository.save(cart);
        product.setStock(product.getStock() - request.getQuantity());
        productRepository.save(product);
        return mapToCartResponse(savedCart);

    }

    public List<CartResponse> viewCart(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException("User not found with ID: " + userId));

        List<Cart> cartList = cartRepository.findByUser(user);
        return cartList.stream().map(this::mapToCartResponse).collect(Collectors.toList());
    }

    public CartResponse updateCartQuantity(Integer cartId, Integer newQuantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new AppException("Cart not found with ID: " + cartId));

        // Kiểm tra số lượng sản phẩm có đủ không
        Product product = cart.getProduct();
        if (product.getStock() + cart.getQuantity() < newQuantity) {
            throw new IllegalArgumentException("Not enough product quantity available.");
        }

        // Cập nhật giỏ hàng và số lượng sản phẩm
        cart.setQuantity(newQuantity);
        Cart updatedCart = cartRepository.save(cart);
        product.setStock(product.getStock() + cart.getQuantity() - newQuantity);
        productRepository.save(product);

        return mapToCartResponse(updatedCart);
    }

    public void removeFromCart(Integer cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new AppException("Cart not found with ID: " + cartId));

        // Cập nhật số lượng sản phẩm khi xóa khỏi giỏ hàng
        Product product = cart.getProduct();
        product.setStock(product.getStock() + cart.getQuantity());
        productRepository.save(product);

        cartRepository.delete(cart);
    }

    private CartResponse mapToCartResponse(Cart cart) {
        CartResponse response = new CartResponse();
        response.setId(cart.getId());
        response.setUserId(cart.getUser().getId());
        response.setProductId(cart.getProduct().getId());
        response.setProductName(cart.getProduct().getName());
        response.setQuantity(cart.getQuantity());
        response.setProductPrice(cart.getProduct().getPrice());
        response.setTotalPrice(cart.getQuantity() * cart.getProduct().getPrice());
        return response;
    }

}
