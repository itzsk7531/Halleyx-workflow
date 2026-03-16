package com.example.Halleyx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Halleyx.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByStatus(String status);

}