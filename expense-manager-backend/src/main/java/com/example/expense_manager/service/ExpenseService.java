package com.example.expense_manager.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.expense_manager.model.Expense;
import com.example.expense_manager.repository.ExpenseRepository;

/**
 * Service class for managing expenses.
 */
@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    /**
     * Constructor for ExpenseService.
     *
     * @param expenseRepository the repository for managing expense data
     */
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    /**
     * Retrieves all expenses.
     *
     * @return a list of all expenses
     * @throws RuntimeException if there is an issue retrieving expenses from the repository
     */
    public List<Expense> getAllExpenses() {
        try {
            return expenseRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve expenses", e);
        }
    }

    /**
     * Saves a new expense.
     *
     * @param expense the expense to be saved
     * @return the saved expense
     */
    public Expense saveExpense(Expense expense) {
        if (expense.getTitle() == null || expense.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Expense title is required");
        }
        if (expense.getAmount() == null || expense.getAmount() <= 0) {
            throw new IllegalArgumentException("Expense amount must be greater than zero");
        }
        if (expense.getCategory() == null || expense.getCategory().isEmpty()) {
            throw new IllegalArgumentException("Expense category is required");
        }
        return expenseRepository.save(expense);
    }

    /**
     * Deletes an expense by its ID.
     *
     * @param id the ID of the expense to be deleted
     */
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    /**
     * Retrieves expenses by category.
     *
     * @param category the category of the expenses to be retrieved
     * @return a list of expenses in the specified category
     */
    public List<Expense> getExpensesByCategory(String category) {
        return expenseRepository.findByCategory(category);
    }

    /**
    /**
     * Updates an existing expense.
     *
     * @param id             the ID of the expense to be updated
     * @param updatedExpense the updated expense data
     * @return the updated expense
     */
    public Expense updateExpense(Long id, Expense updatedExpense) {
        if (!expenseRepository.existsById(id)) {
            throw new RuntimeException("Expense not found with id: " + id);
        }
        updatedExpense.setId(id);
        return expenseRepository.save(updatedExpense);
    }

    /**
     * Retrieves expenses within a specified date range.
     *
     * @param start the start date of the range
     * @param end   the end date of the range
     * @return a list of expenses within the specified date range
     */
    public List<Expense> getExpensesByDateRange(String start, String end) {
        try {
            LocalDate startDate = LocalDate.parse(start);
            LocalDate endDate = LocalDate.parse(end);
            return expenseRepository.findAllByDateBetween(startDate, endDate);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use the format 'yyyy-MM-dd'.", e);
        }
    }

    /**
     * Provides a summary of expenses grouped by category.
     *
     * @return a map where the keys are categories and the values are the total
     *         amounts for each category
    }
    public Map<String, Double> getExpenseSummary() {
        List<Map<String, Object>> summaryList = expenseRepository.getExpenseSummary();
        return summaryList.stream()
                .collect(Collectors.toMap(
                        entry -> (String) entry.get("category"),
                        entry -> (Double) entry.get("total")
                ));
    }
     * Retrieves an expense by its ID.
     *
     * @param id the ID of the expense to be retrieved
     * @return the expense with the specified ID
     */
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).orElseThrow(() -> new RuntimeException(
                "Expense not found with id: " + id + ". Please check the ID and try again."));
    }

    /**
     * @param page the page number to retrieve
     * @param size the size of the page to retrieve
     * @return a page of expenses with titles containing the specified string
     */
    public Page<Expense> searchExpensesByTitle(String title, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return expenseRepository.findByTitleContainingIgnoreCase(title, pageable);
    }
    public List<Expense> searchExpensesByTitle(String title) {
        return expenseRepository.findByTitleContainingIgnoreCase(title);
    }

}
