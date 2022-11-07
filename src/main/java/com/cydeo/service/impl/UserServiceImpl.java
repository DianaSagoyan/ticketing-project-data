package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Task;
import com.cydeo.entity.User;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProjectService projectService;
    private final TaskService taskService;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, @Lazy ProjectService projectService, @Lazy TaskService taskService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @Override
    public List<UserDTO> listAllUsers() {
        List<User> userList = userRepository.findAllByIsDeletedOrderByFirstNameDesc(false);

        return userList.stream().map(userMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String userName) {
        return userMapper.convertToDto(userRepository.findByUserNameAndIsDeleted(userName, false));
    }

    @Override
    public void save(UserDTO user) {
        userRepository.save(userMapper.convertToEntity(user));
    }

    @Override
    public void deleteByUserName(String userName) {
        userRepository.deleteByUserName(userName);
    }

    @Override
    public UserDTO update(UserDTO user) {
       //Find current user
       User user1 = userRepository.findByUserNameAndIsDeleted(user.getUserName(), false);
       //dto to entity
       User convertedUser = userMapper.convertToEntity(user);
       //set id to converted obj
       convertedUser.setId(user1.getId());
       //save
       userRepository.save(convertedUser);

        return findByUserName(user.getUserName());
    }

    @Override
    public void delete(String userName) {
        User user = userRepository.findByUserNameAndIsDeleted(userName, false);

        if(checkIfUserCanBeDeleted(user)){
            user.setIsDeleted(true);
            user.setUserName(user.getUserName() + "-" + user.getId());
            userRepository.save(user);
        }
    }

    @Override
    public List<UserDTO> listAllByRole(String role) {

        List<User> users = userRepository.findByRoleDescriptionIgnoreCaseAndIsDeleted("manager", false);

        return users.stream().map(userMapper::convertToDto).collect(Collectors.toList());
    }


    private boolean checkIfUserCanBeDeleted(User user){
            switch (user.getRole().getDescription()){

                case "Manager":
                    List<ProjectDTO> projectDtoList = projectService.listAllNonCompletedByAssignedManager(userMapper.convertToDto(user));
                    return projectDtoList.size() == 0;
                case "Employee":
                    List<TaskDTO> taskDtoList = taskService.listAllNonCompletedByAssignedEmployee(userMapper.convertToDto(user));
                    return taskDtoList.size() == 0;
                default:
                    return true;

            }
    }
}
