package com.example.expense_manager.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<Expense> getExpensesByCategory(String category){
        return expenseRepository.findByCategory(category);
    }

    public Expense updateExpense(Long id, Expense updatedExpense){
        Expense existingExpense = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found"));
        existingExpense.setTitle(updatedExpense.getTitle());
        existingExpense.setAmount(updatedExpense.getAmount());
        existingExpense.setCategory(updatedExpense.getCategory());
        existingExpense.setDate(updatedExpense.getDate());
        return expenseRepository.save(existingExpense);
    }

    public List<Expense> getExpensesByDateRange(String start, String end){
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return expenseRepository.findAllByDateBetween(startDate, endDate);
    }

    public Map<String , Double> getExpenseSummary(){
        List<Expense> expenses = expenseRepository.findAll();
        return expenses.stream().collect(Collectors.groupingBy(Expense::getCategory, Collectors.summingDouble(Expense::getAmount)));
    }

    public Expense getExpenseById(Long id){
        return expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found with id: " +id));
    }

    public List<Expense> searchExpensesByTitle(String title){
        return expenseRepository.findByTitleContainingIgnoreCase(title);
    }

}
