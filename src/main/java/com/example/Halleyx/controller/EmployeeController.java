package com.example.Halleyx.controller;

import com.example.Halleyx.model.Expense;
import com.example.Halleyx.repository.ExpenseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private ExpenseRepository repo;

    // Employee Dashboard
    @GetMapping("/employee-dashboard")
    public String dashboard(){
        return "employee-dashboard";
    }

    // Expense Form Page
    @GetMapping("/create-expense")
    public String createExpense(){
        return "create-expense";
    }

    // Save Expense Request
    @PostMapping("/save-expense")
    public String saveExpense(
            Expense expense,
            @RequestParam("file") MultipartFile file){

        // Save file name
        if(!file.isEmpty()){
            expense.setBillFile(file.getOriginalFilename());
        }

        // Approval Logic based on amount
        if(expense.getAmount() <= 5000){
            expense.setStatus("PENDING_MANAGER");
        }else{
            expense.setStatus("PENDING_CEO");
        }

        // Save expense
        repo.save(expense);

        return "redirect:/employee/status";
    }

    // Expense Status Page
    @GetMapping("/status")
    public String status(Model model){

        model.addAttribute("expenses", repo.findAll());

        return "employee-status";
    }

    // Expense History Page (NEW)
    @GetMapping("/history")
    public String history(Model model){

        model.addAttribute("expenses", repo.findAll());

        return "expense-history";
    }
}