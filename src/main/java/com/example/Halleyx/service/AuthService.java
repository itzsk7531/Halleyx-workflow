package com.example.Halleyx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Halleyx.repository.UserRepository;
import com.example.Halleyx.model.User;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    // LOGIN
    public User login(String empId, String password){

        User user = repo.findById(empId).orElse(null);

        if(user == null){
            return null;
        }

        if(user.getPassword().equals(password)){
            return user;
        }

        return null;
    }

    // GET USERS BY ROLE
    public List<User> getUsersByRole(String role){
        return repo.findByRole(role);
    }

    // GET ALL USERS (ADMIN EMPLOYEE PAGE)
    public List<User> getAllUsers(){
        return repo.findAll();
    }

    // SAVE USER
    public void saveUser(User user){
        repo.save(user);
    }

    // DELETE USER
    public void deleteUser(String empId){
        repo.deleteById(empId);
    }

    // COUNT USERS BY ROLE (ADMIN DASHBOARD)
    public long countByRole(String role){
        return repo.countByRole(role);
    }

    // EMPLOYEE COUNT (Manager/CEO Dashboard)
    public long getEmployeeCount(){
        return repo.countByRole("EMPLOYEE");
    }

    // GET EMPLOYEES ONLY (Manager/CEO View Employees Page)
    public List<User> getEmployees(){
        return repo.findByRole("EMPLOYEE");
    }

}