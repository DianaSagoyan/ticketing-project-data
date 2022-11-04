package com.cydeo.repository;

import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    @Transactional
    void deleteByUserName(String userName);

    List<User> findByRoleDescriptionIgnoreCase(String description);
}
