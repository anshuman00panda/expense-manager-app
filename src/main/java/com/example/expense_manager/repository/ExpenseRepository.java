package com.example.expense_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.expense_manager.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
