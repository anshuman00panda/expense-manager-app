package com.example.expense_manager.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
     */
    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }

    /**
     * Saves a new expense.
     *
     * @param expense the expense to be saved
     * @return the saved expense
     */
    public Expense saveExpense(Expense expense){
        return expenseRepository.save(expense);
    }

    /**
     * Deletes an expense by its ID.
     *
     * @param id the ID of the expense to be deleted
     */
    public void deleteExpense(Long id){
        expenseRepository.deleteById(id);
    }

    /**
     * Retrieves expenses by category.
     *
     * @param category the category of the expenses to be retrieved
     * @return a list of expenses in the specified category
     */
    public List<Expense> getExpensesByCategory(String category){
        return expenseRepository.findByCategory(category);
    }

    /**
     * Updates an existing expense.
     *
     * @param id the ID of the expense to be updated
     * @param updatedExpense the updated expense data
     * @return the updated expense
     */
    public Expense updateExpense(Long id, Expense updatedExpense){
        Expense existingExpense = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found"));
        existingExpense.setTitle(updatedExpense.getTitle());
        existingExpense.setAmount(updatedExpense.getAmount());
        existingExpense.setCategory(updatedExpense.getCategory());
        existingExpense.setDate(updatedExpense.getDate());
        return expenseRepository.save(existingExpense);
    }

    /**
     * Retrieves expenses within a specified date range.
     *
     * @param start the start date of the range
     * @param end the end date of the range
     * @return a list of expenses within the specified date range
     */
    public List<Expense> getExpensesByDateRange(String start, String end){
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return expenseRepository.findAllByDateBetween(startDate, endDate);
    }

    /**
     * Provides a summary of expenses grouped by category.
     *
     * @return a map where the keys are categories and the values are the total amounts for each category
     */
    public Map<String , Double> getExpenseSummary(){
        List<Expense> expenses = expenseRepository.findAll();
        return expenses.stream().collect(Collectors.groupingBy(Expense::getCategory, Collectors.summingDouble(Expense::getAmount)));
    }

    /**
     * Retrieves an expense by its ID.
     *
     * @param id the ID of the expense to be retrieved
     * @return the expense with the specified ID
     */
    public Expense getExpenseById(Long id){
        return expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found with id: " +id));
    }

    /**
     * Searches for expenses by title.
     *
     * @param title the title to search for
     * @return a list of expenses with titles containing the specified string
     */
    public List<Expense> searchExpensesByTitle(String title){
        return expenseRepository.findByTitleContainingIgnoreCase(title);
    }

}
