package com.example.back_end.service.authention;

import com.example.back_end.dto.request.UserRequest;
import com.example.back_end.dto.response.UserResponse;
import com.example.back_end.entity.User;
import com.example.back_end.exception.AppException;
import com.example.back_end.repository.UserRepository;
import com.example.back_end.service.authention.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IUserService implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getAccountByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if(user == null){
            throw  new UsernameNotFoundException(username);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRole().toString())
        );


    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> userList = userRepository.findAll();
        if(userList.isEmpty()){
            throw new AppException("chưa có ai trong danh sách");
        }
        return  userList.stream().filter(user -> !user.isDeleted())
                .map(user -> modelMapper.map(user,UserResponse.class)).collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(Integer userId) {
       Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw  new AppException("chưa có dối nào");
        }

        return user.map(user1 -> modelMapper.map(user1,UserResponse.class)).orElse(null);
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
         User entity = modelMapper.map(userRequest,User.class);
         User user =   userRepository.save(entity);
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse updateUser(Integer userId, UserRequest userRequestDTO) {
        if(!userRepository.existsById(userId)){
            throw  new AppException("chưa thấy đối tượng này");
        }
        User user = modelMapper.map(userRequestDTO,User.class);
        user.setId(userId);
        userRepository.save(user);
        return modelMapper.map(user, UserResponse.class);

    }

    @Override
    public void softDeleteUser(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw  new AppException("k có trong danh sách");
        }
        user.get().setDeleted(true);
        userRepository.save(user.get());

    }


}
