package com.example.expense_manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.example.expense_manager.model.Expense;
import com.example.expense_manager.service.ExpenseService;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/all")
    public List<Expense> getAllExpenses(){
        return expenseService.getAllExpenses();
    }

    @PostMapping("/save")
    public Expense saveExpense(@RequestBody Expense expense){
        return expenseService.saveExpense(expense);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteExpense(@PathVariable Long id){
        expenseService.deleteExpense(id);
    }

    @GetMapping("/category/{category}")
    public List<Expense> getExpensesByCategory(@PathVariable String category){
        return expenseService.getExpensesByCategory(category);
    }

    @PutExchange("/update/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense updatedExpense){
        return expenseService.updateExpense(id, updatedExpense);
    }

    @GetMapping("/date-range")
    public List<Expense> getExpensesByDateRange(@RequestParam String start, @RequestParam String end){
        return expenseService.getExpensesByDateRange(start, end);
    }

    @GetMapping("/summary")
    public Map<String, Double> getExpenseSummary(){
        return expenseService.getExpenseSummary();
    }

    @GetMapping("/id/{id}")
    public Expense getExpenseById(@PathVariable Long id){
        return expenseService.getExpenseById(id);
    }

    @GetMapping("/search")
    public List<Expense> searchExpensesByTitle(@RequestParam String title){
        return expenseService.searchExpensesByTitle(title);
    }
}
