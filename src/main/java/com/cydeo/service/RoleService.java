package com.cydeo.service;

import com.cydeo.dto.RoleDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleService {
    List<RoleDTO> listAllRoles();

    RoleDTO findById(Long id);
}
