package com.app.userservice.service;

import com.app.userservice.dto.AddressDTO;
import com.app.userservice.dto.UserRequest;
import com.app.userservice.dto.UserResponse;
import com.app.userservice.model.Address;
import com.app.userservice.model.User;
import com.app.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // to define the default constructor for userRepo interface
public class UserService {

    private final UserRepository userRepository;

    //list all users
    public List<UserResponse> fetchAllUser(){
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    //add user
    public void addUser(UserRequest userRequest){
        User user = new User();
        updateUserFromRequest(user, userRequest);
        userRepository.save(user);
    }

    //user request to update data
    private void updateUserFromRequest(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail((userRequest.getEmail()));
        user.setPhone(userRequest.getPhone());
        if(userRequest.getAddress() != null){
            Address address = new Address();
            address.setStreet(userRequest.getAddress().getStreet());
            address.setZipcode(userRequest.getAddress().getZipcode());
            address.setCity(userRequest.getAddress().getCity());
            address.setCountry(userRequest.getAddress().getCountry());
            address.setState(userRequest.getAddress().getState());
            user.setAddress(address);
        }
    }

    //fetch user using java stream, optional to avoid null exception
    public Optional<UserResponse> fetchUser(String id) {
            return userRepository.findById(String.valueOf(id))
                    .map(this::mapToUserResponse);
    }

    //update detail of user
    public boolean updateUser(String id, UserRequest updatedUserRequest) {
            return userRepository.findById(String.valueOf(id))
                    .map(existingUser -> {
                        updateUserFromRequest(existingUser, updatedUserRequest);
                        userRepository.save(existingUser);
                        return true;
                    }).orElse(false);
    }

    //mapping column values to user response type
    private UserResponse mapToUserResponse(User user){
        UserResponse response = new UserResponse();
        response.setId(String.valueOf(user.getId()));
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());

        if(user.getAddress() != null){
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setZipcode(user.getAddress().getZipcode());
            response.setAddress(addressDTO);
        }
        return response;
    }
}
