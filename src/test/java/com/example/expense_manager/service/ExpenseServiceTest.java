package com.example.expense_manager.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.expense_manager.model.Expense;
import com.example.expense_manager.repository.ExpenseRepository;





public class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private ExpenseService expenseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllExpenses() {
        List<Expense> expenses = Arrays.asList(new Expense(), new Expense());
        when(expenseRepository.findAll()).thenReturn(expenses);

        List<Expense> result = expenseService.getAllExpenses();

        assertEquals(2, result.size());
        verify(expenseRepository, times(1)).findAll();
    }

    @Test
    public void testSaveExpense() {
        Expense expense = new Expense();
        when(expenseRepository.save(expense)).thenReturn(expense);

        Expense result = expenseService.saveExpense(expense);

        assertNotNull(result);
        verify(expenseRepository, times(1)).save(expense);
    }

    @Test
    public void testDeleteExpense() {
        Long id = 1L;
        doNothing().when(expenseRepository).deleteById(id);

        expenseService.deleteExpense(id);

        verify(expenseRepository, times(1)).deleteById(id);
    }

    @Test
    public void testGetExpensesByCategory() {
        String category = "Food";
        List<Expense> expenses = Arrays.asList(new Expense(), new Expense());
        when(expenseRepository.findByCategory(category)).thenReturn(expenses);

        List<Expense> result = expenseService.getExpensesByCategory(category);

        assertEquals(2, result.size());
        verify(expenseRepository, times(1)).findByCategory(category);
    }

    @Test
    public void testUpdateExpense() {
        Long id = 1L;
        Expense existingExpense = new Expense();
        existingExpense.setId(id);
        Expense updatedExpense = new Expense();
        updatedExpense.setTitle("Updated Title");
        updatedExpense.setAmount(100.0);
        updatedExpense.setCategory("Updated Category");
        updatedExpense.setDate(LocalDate.now());

        when(expenseRepository.findById(id)).thenReturn(Optional.of(existingExpense));
        when(expenseRepository.save(existingExpense)).thenReturn(existingExpense);

        Expense result = expenseService.updateExpense(id, updatedExpense);

        assertEquals("Updated Title", result.getTitle());
        assertEquals(100.0, result.getAmount());
        assertEquals("Updated Category", result.getCategory());
        verify(expenseRepository, times(1)).findById(id);
        verify(expenseRepository, times(1)).save(existingExpense);
    }

    @Test
    public void testGetExpensesByDateRange() {
        String start = "2023-01-01";
        String end = "2023-12-31";
        List<Expense> expenses = Arrays.asList(new Expense(), new Expense());
        when(expenseRepository.findAllByDateBetween(LocalDate.parse(start), LocalDate.parse(end))).thenReturn(expenses);

        List<Expense> result = expenseService.getExpensesByDateRange(start, end);

        assertEquals(2, result.size());
        verify(expenseRepository, times(1)).findAllByDateBetween(LocalDate.parse(start), LocalDate.parse(end));
    }

    @Test
    public void testGetExpenseSummary() {
        Expense expense1 = new Expense();
        expense1.setCategory("Food");
        expense1.setAmount(50.0);
        Expense expense2 = new Expense();
        expense2.setCategory("Transport");
        expense2.setAmount(30.0);
        Expense expense3 = new Expense();
        expense3.setCategory("Food");
        expense3.setAmount(20.0);
        List<Expense> expenses = Arrays.asList(expense1, expense2, expense3);
        when(expenseRepository.findAll()).thenReturn(expenses);

        Map<String, Double> result = expenseService.getExpenseSummary();

        assertEquals(2, result.size());
        assertEquals(70.0, result.get("Food"));
        assertEquals(30.0, result.get("Transport"));
        verify(expenseRepository, times(1)).findAll();
    }

    @Test
    public void testGetExpenseById() {
        Long id = 1L;
        Expense expense = new Expense();
        expense.setId(id);
        when(expenseRepository.findById(id)).thenReturn(Optional.of(expense));

        Expense result = expenseService.getExpenseById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(expenseRepository, times(1)).findById(id);
    }

    @Test
    public void testSearchExpensesByTitle() {
        String title = "Lunch";
        List<Expense> expenses = Arrays.asList(new Expense(), new Expense());
        when(expenseRepository.findByTitleContainingIgnoreCase(title)).thenReturn(expenses);

        List<Expense> result = expenseService.searchExpensesByTitle(title);

        assertEquals(2, result.size());
        verify(expenseRepository, times(1)).findByTitleContainingIgnoreCase(title);
    }
}