package com.example.Halleyx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Halleyx.model.Expense;
import com.example.Halleyx.repository.ExpenseRepository;
import com.example.Halleyx.service.AuthService;
import com.example.Halleyx.service.MailService; // 🔥 ADD THIS

@Controller
public class ManagerController {

    @Autowired
    private AuthService service;

    @Autowired
    private ExpenseRepository expenseRepo;

    // 🔥 ADD THIS
    @Autowired
    private MailService mailService;

    // MANAGER DASHBOARD
    @GetMapping("/manager/dashboard")
    public String managerDashboard(Model model){

        model.addAttribute("employeeCount", service.getEmployeeCount());

        int pending = expenseRepo.findByStatus("PENDING_MANAGER").size();
        int approved = expenseRepo.findByStatus("APPROVED").size();
        model.addAttribute("pendingApproval", pending);
        model.addAttribute("approvedCount", approved);

        return "manager-dashboard";
    }

    // VIEW EMPLOYEES
    @GetMapping("/manager/employees")
    public String viewEmployees(Model model){

        model.addAttribute("employees", service.getEmployees());

        return "view-employees";
    }

    // MANAGER APPROVAL TABLE
    @GetMapping("/manager/approvals")
    public String managerApprovals(Model model){

        List<Expense> expenses = expenseRepo.findByStatus("PENDING_MANAGER");

        model.addAttribute("expenses", expenses);

        return "manager-approvals";
    }

    // APPROVE EXPENSE
    @PostMapping("/manager/approve/{id}")
    public String approveExpense(@PathVariable Long id){

        Expense expense = expenseRepo.findById(id).orElse(null);

        if(expense != null){

            if(expense.getAmount() <= 5000){

                // ✅ FINAL APPROVAL
                expense.setStatus("APPROVED");
                expenseRepo.save(expense);

                // 🔥 SEND MAIL HERE
                mailService.sendApprovalMail(
                        expense.getEmpId(),
                        expense.getEmail(),
                        String.valueOf(expense.getAmount()),
                        expense.getTitle()
                );

            }else{

                // ⏳ SEND TO CEO
                expense.setStatus("PENDING_CEO");
                expenseRepo.save(expense);
            }
        }

        return "redirect:/manager/approvals";
    }

    // REJECT EXPENSE
    @PostMapping("/manager/reject/{id}")
    public String rejectExpense(@PathVariable Long id){

        Expense expense = expenseRepo.findById(id).orElse(null);

        if(expense != null){
            expense.setStatus("REJECTED");
            expenseRepo.save(expense);
        }

        return "redirect:/manager/approvals";
    }
}