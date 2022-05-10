package com.treehouseapp.service.serviceImpl;

import com.treehouseapp.dto.UserDetailsDTO;
import com.treehouseapp.repository.ShippingAddressRepository;
import com.treehouseapp.repository.UserRepository;
import com.treehouseapp.service.serviceInterfaces.OrderService;
import com.treehouseapp.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {

    private final WalletServiceImpl walletService;

    private final OrderService orderService;

    private final FavoriteMenuServiceImpl favoriteMenuService;

    private final UserServiceImpl userServiceImpl;

    private final UserRepository userRepository;

    private final ShippingAddressRepository shippingAddressRepository;


    public UserDetailsService(WalletServiceImpl walletService, OrderService orderService, FavoriteMenuServiceImpl favoriteMenuService, UserServiceImpl userServiceImpl, UserRepository userRepository, ShippingAddressRepository shippingAddressRepository) {
        this.walletService = walletService;
        this.orderService = orderService;
        this.favoriteMenuService = favoriteMenuService;
        this.userServiceImpl = userServiceImpl;
        this.userRepository = userRepository;
        this.shippingAddressRepository = shippingAddressRepository;
    }

    public UserDetailsDTO getUserDetails(Long userId){

        var userExists = userRepository.findById(userId);
        System.out.println("into get userdetails");
        System.out.println(userExists);
        if(!userExists.isPresent()){
            throw new UserNotFoundException("user with the" + userId + "does not exist");
        }

        var walletBal = walletService.getWalletBalance(userId);
        var favorite = favoriteMenuService.getAllFavoriteFlowersByAUser(userId);
        var orders = orderService.getAllOrdersByUserId(userId);
        var shippingAddress = shippingAddressRepository.findByUser_Id(userId);


        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setWalletBalance(walletBal);
        userDetailsDTO.setFavoriteDto(favorite);
        userDetailsDTO.setViewUserOrdersDTOS(orders);
        userDetailsDTO.setEmail(userExists.get().getEmail());
        userDetailsDTO.setName(userExists.get().getFirstName() + " " + userExists.get().getLastName());
        if (shippingAddress.getDefaultAddress() == true){
            userDetailsDTO.setShippingAddressDTO(shippingAddress);
        }



        return userDetailsDTO;


    }

}
