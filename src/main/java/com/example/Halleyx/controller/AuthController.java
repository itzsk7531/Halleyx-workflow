package com.example.Halleyx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Halleyx.model.User;
import com.example.Halleyx.service.AuthService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private AuthService service;

    // LOGIN PAGE
    @GetMapping("/")
    public String loginPage(){
        return "login";
    }

    // LOGIN
    @PostMapping("/login")
    public String login(@RequestParam String empId,
                        @RequestParam String password,
                        HttpSession session){

        User user = service.login(empId,password);

        if(user == null){
            return "login";
        }

        session.setAttribute("loggedUser", user);

        if(user.getRole().equals("EMPLOYEE")){
            return "redirect:/employee/employee-dashboard";
        }

        if(user.getRole().equals("MANAGER")){
            return "redirect:/manager/dashboard";
        }

        if(user.getRole().equals("CEO")){
            return "redirect:/ceo/dashboard";
        }

        if(user.getRole().equals("ADMIN")){
            return "redirect:/admin/dashboard";
        }

        return "login";
    }

    // ADMIN DASHBOARD
    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model){

        model.addAttribute("ceoCount", service.countByRole("CEO"));
        model.addAttribute("managerCount", service.countByRole("MANAGER"));
        model.addAttribute("employeeCount", service.countByRole("EMPLOYEE"));
        model.addAttribute("adminCount", service.countByRole("ADMIN"));

        return "admin-dashboard";
    }

    // ADMIN EMPLOYEES PAGE (shows all users)
    @GetMapping("/admin/employees")
    public String employees(Model model){

        List<User> users = service.getAllUsers();

        model.addAttribute("users", users);

        return "admin-employees";
    }
    @GetMapping("/admin/add-employee")
    public String addEmployeePage(){

        return "admin-add-employee";
    }
    @PostMapping("/admin/save-employee")
    public String saveEmployee(User user){

        service.saveUser(user);

        return "redirect:/admin/employees";
    }

    // DELETE USER
    @GetMapping("/admin/delete/{empId}")
    public String deleteUser(@PathVariable String empId){

        service.deleteUser(empId);

        return "redirect:/admin/employees";
    }

    // LOGOUT
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}