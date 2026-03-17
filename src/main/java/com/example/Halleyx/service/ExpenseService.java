package com.example.Halleyx.service;

import java.util.List;
import com.example.Halleyx.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Halleyx.model.Expense;
import com.example.Halleyx.repository.ExpenseRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository repo;

    // 🔥 ADD THIS
    @Autowired
    private MailService mailService;

    public List<Expense> getManagerPending(){
        return repo.findByStatus("PENDING_MANAGER");
    }

    public List<Expense> getCeoPending(){
        return repo.findByStatus("PENDING_CEO");
    }

    public Expense getById(Long id){
        return repo.findById(id).orElse(null);
    }

    public void save(Expense e){
        repo.save(e);
    }

    // 🔥 ADD THIS METHOD (APPROVE + MAIL)
    public void approveExpense(Long id){

        Expense e = repo.findById(id).orElse(null);

        if(e != null){

            // ✅ update status
            e.setStatus("APPROVED");

            repo.save(e);

            // ✅ send mail
            mailService.sendApprovalMail(
                    e.getEmpId(),
                    e.getEmail(),
                    String.valueOf(e.getAmount()),
                    e.getTitle()
            );
        }
    }
}