package com.example.task_manager.service;

import com.example.task_manager.entity.TaskList;
import com.example.task_manager.entity.User;
import com.example.task_manager.repository.TaskListRepository;
import com.example.task_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskListRepository taskListRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public Optional<User> findUser(String username){
        return userRepository.findByUsername(username);
    }

    public User  addNewUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        TaskList taskList = new TaskList(user.getUsername(), null, null);
        taskListRepository.save(taskList);
        return userRepository.save(user);
    }

}
