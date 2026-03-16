package com.example.Halleyx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Halleyx.model.Expense;
import com.example.Halleyx.repository.ExpenseRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository repo;

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
}