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
import org.springframework.web.bind.annotation.PutMapping;

import com.example.expense_manager.model.Expense;
import com.example.expense_manager.service.ExpenseService;

import jakarta.validation.Valid;

/**
 * ExpenseController is a REST controller that handles HTTP requests for
 * managing expenses.
 * It provides endpoints to perform CRUD operations and other expense-related
 * queries.
 * 
 * Endpoints:
 * 
 * - GET /api/expenses/all: Retrieve all expenses.
 * - POST /api/expenses/save: Save a new expense.
 * - DELETE /api/expenses/delete/{id}: Delete an expense by its ID.
 * - GET /api/expenses/category/{category}: Retrieve expenses by category.
 * - PUT /api/expenses/update/{id}: Update an existing expense by its ID.
 * - GET /api/expenses/date-range: Retrieve expenses within a specified date
 * range.
 * - GET /api/expenses/summary: Get a summary of expenses.
 * - GET /api/expenses/id/{id}: Retrieve an expense by its ID.
 * - GET /api/expenses/search: Search expenses by title.
 * 
 * This controller uses ExpenseService to perform the actual operations.
 * 
 * @author
 */
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/all")
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @PostMapping("/save")
    public Expense saveExpense(@Valid @RequestBody Expense expense) {
        return expenseService.saveExpense(expense);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }

    @GetMapping("/category/{category}")
    public List<Expense> getExpensesByCategory(@PathVariable String category) {
        return expenseService.getExpensesByCategory(category);
    }

    @PutMapping("/update/{id}")
    public Expense updateExpense(@PathVariable Long id, @Valid @RequestBody Expense updatedExpense) {
        return expenseService.updateExpense(id, updatedExpense);
    }

    @GetMapping("/date-range")
    public List<Expense> getExpensesByDateRange(@RequestParam String start, @RequestParam String end) {
        return expenseService.getExpensesByDateRange(start, end);
    }

    @GetMapping("/summary")
    public Map<String, Double> getExpenseSummary() {
        return expenseService.getExpenseSummary();
    }

    @GetMapping("/id/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }

    @GetMapping("/search")
    public List<Expense> searchExpensesByTitle(@RequestParam String title) {
        return expenseService.searchExpensesByTitle(title);
    }
}
