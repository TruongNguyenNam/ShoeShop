package com.example.back_end.service.customer;

import com.example.back_end.dto.customer.request.WishlistRequest;
import com.example.back_end.dto.customer.response.WishlistResponse;
import com.example.back_end.entity.Product;
import com.example.back_end.entity.User;
import com.example.back_end.entity.Wishlist;
import com.example.back_end.exception.AppException;
import com.example.back_end.repository.ProductRepository;
import com.example.back_end.repository.UserRepository;
import com.example.back_end.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishListImpl implements WishListService{
    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public WishlistResponse addToWishlist(Integer userId, WishlistRequest request) {
      Wishlist wishlist = new Wishlist();
        User user = userRepository.findById(userId).orElseThrow(null);
        Product product = productRepository.findById(request.getProductId()).orElseThrow(null);
      wishlist.setUser(user);
      wishlist.setProduct(product);
      wishlistRepository.save(wishlist);
      return mapToResponse(wishlist);

    }

    @Override
    public List<WishlistResponse> getUserWishlist(Integer userId) {
        List<Wishlist> wishlists = wishlistRepository.findByUserId(userId);
        if(wishlists.isEmpty()){
            throw  new AppException("wishlists is not found");
        }
        return wishlists.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public void removeFromWishlist(Integer userId, Integer productId) {
            wishlistRepository.deleteByUserIdAndProductId(userId,productId);
    }

    private WishlistResponse mapToResponse(Wishlist wishlist){
        WishlistResponse response = new WishlistResponse();
        response.setId(wishlist.getId());
        response.setProductId(wishlist.getProduct().getId());
        response.setProductName(wishlist.getProduct().getName());
        return response;
    }

}
