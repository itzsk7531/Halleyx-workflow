package com.example.Halleyx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Halleyx.model.Expense;
import com.example.Halleyx.repository.ExpenseRepository;
import com.example.Halleyx.service.AuthService;

@Controller
public class CeoController {

    @Autowired
    private ExpenseRepository repo;

    @Autowired
    private AuthService service;

    // CEO DASHBOARD
    @GetMapping("/ceo/dashboard")
    public String ceoDashboard(Model model){

        model.addAttribute("employeeCount", service.getEmployeeCount());

        int pending = repo.findByStatus("PENDING_CEO").size();
        int approved = repo.findByStatus("APPROVED").size();

        model.addAttribute("pendingApproval", pending);
        model.addAttribute("approvedCount", approved);

        return "ceo-dashboard";
    }
    @GetMapping("/ceo/employees")
    public String viewEmployees(Model model){

        model.addAttribute("employees", service.getEmployees());

        return "view-employees";
    }
    // CEO APPROVAL PAGE
    @GetMapping("/ceo/approvals")
    public String ceoApprovals(Model model){

        List<Expense> expenses = repo.findByStatus("PENDING_CEO");

        model.addAttribute("expenses", expenses);

        return "ceo-approvals";
    }

    // CEO APPROVE
    @PostMapping("/ceo/approve/{id}")
    public String approve(@PathVariable Long id){

        Expense exp = repo.findById(id).orElse(null);

        if(exp != null){
            exp.setStatus("APPROVED");
            repo.save(exp);
        }

        return "redirect:/ceo/approvals";
    }

    // CEO REJECT
    @PostMapping("/ceo/reject/{id}")
    public String reject(@PathVariable Long id){

        Expense exp = repo.findById(id).orElse(null);

        if(exp != null){
            exp.setStatus("REJECTED");
            repo.save(exp);
        }

        return "redirect:/ceo/approvals";
    }
}