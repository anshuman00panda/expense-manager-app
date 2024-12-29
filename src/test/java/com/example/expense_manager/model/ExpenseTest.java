package com.example.expense_manager.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;




public class ExpenseTest {

    @Test
    public void testGettersAndSetters() {
        Expense expense = new Expense();
        Long id = 1L;
        String title = "Lunch";
        Double amount = 15.50;
        String category = "Food";
        LocalDate date = LocalDate.now();

        expense.setId(id);
        expense.setTitle(title);
        expense.setAmount(amount);
        expense.setCategory(category);
        expense.setDate(date);

        assertEquals(id, expense.getId());
        assertEquals(title, expense.getTitle());
        assertEquals(amount, expense.getAmount());
        assertEquals(category, expense.getCategory());
        assertEquals(date, expense.getDate());
    }

    @Test
    public void testTitleNotBlank() {
        Expense expense = new Expense();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            expense.setTitle("");
        });
        assertTrue(exception.getMessage().contains("Title is mandatory."));
    }

    @Test
    public void testAmountNotNullAndPositive() {
        Expense expense = new Expense();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            expense.setAmount(null);
        });
        assertTrue(exception.getMessage().contains("Amount is required."));

        exception = assertThrows(IllegalArgumentException.class, () -> {
            expense.setAmount(-10.0);
        });
        assertTrue(exception.getMessage().contains("Amount should be positive."));
    }

    @Test
    public void testCategoryNotBlank() {
        Expense expense = new Expense();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            expense.setCategory("");
        });
        assertTrue(exception.getMessage().contains("Category is required."));
    }

    @Test
    public void testDateNotNull() {
        Expense expense = new Expense();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            expense.setDate(null);
        });
        assertTrue(exception.getMessage().contains("Date is required."));
    }
}