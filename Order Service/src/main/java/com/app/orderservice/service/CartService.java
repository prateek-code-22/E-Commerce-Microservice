package com.app.orderservice.service;

import com.app.orderservice.dto.CartItemRequest;
import com.app.orderservice.model.CartItem;
//import com.app.orderservice.model.Product;
//import com.app.orderservice.model.User;
import com.app.orderservice.repository.CartItemRepository;
//import com.app.orderservice.repository.ProductRepository;
//import com.app.orderservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CartService {
    private final CartItemRepository cartItemRepository;
    public boolean addToCart(String userId, CartItemRequest request) {

        CartItem existingCartItem = cartItemRepository.findByUserIdAndProductId(userId, request.getProductId());

        if(existingCartItem != null){
            //update the quantity
            existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
            existingCartItem.setPrice(BigDecimal.valueOf(1000.00));
            cartItemRepository.save(existingCartItem);
        }
        else{
            //create new cart item
            CartItem cartItem = new CartItem();
            cartItem.setUserId(userId);
            cartItem.setProductId(request.getProductId());
            cartItem.setQuantity(request.getQuantity());
//            cartItem.setPrice(request.getProductId().getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
            cartItem.setPrice(BigDecimal.valueOf(1000.00));
            cartItemRepository.save(cartItem);
        }

        return true;

    }

    //delete item from cart
    public boolean deleteItemFromCart(String userId, String productId) {
//        Optional<Product> productOpt = productRepository.findById(productId);
//        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));

        CartItem cartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);

        //check if user present or not
        if(cartItem != null){
            cartItemRepository.delete(cartItem);
            return true;
        }
        return false;
    }


    public List<CartItem> getCart(String userId) {
        return cartItemRepository.findByUserId(String.valueOf(userId));
    }

    public void clearCart(String userId) {
        cartItemRepository.deleteByUserId(userId);
    }
}
