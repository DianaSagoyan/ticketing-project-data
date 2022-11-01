package com.cydeo.service;

import com.cydeo.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserService {
    List<UserDTO> listAllUsers();
    UserDTO findByUserName(String userName);
    void save(UserDTO user);
    void deleteByUserName(String userName);
}
