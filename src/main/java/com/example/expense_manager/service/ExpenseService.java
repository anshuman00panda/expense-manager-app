package com.example.expense_manager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.expense_manager.model.Expense;
import com.example.expense_manager.repository.ExpenseRepository;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }

    public Expense saveExpense(Expense expense){
        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long id){
        expenseRepository.deleteById(id);
    }
}
