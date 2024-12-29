package com.example.expense_manager.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.expense_manager.model.Expense;

/**
 * Repository interface for managing {@link Expense} entities.
 * Extends {@link JpaRepository} to provide CRUD operations and additional query methods.
 * 
 * Methods:
 * - {@link #findByCategory(String)}: Retrieves a list of expenses by category.
 * - {@link #findAllByDateBetween(LocalDate, LocalDate)}: Retrieves a list of expenses within a date range.
 * - {@link #findByTitleContainingIgnoreCase(String)}: Retrieves a list of expenses with titles containing the specified string, case insensitive.
 */
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByCategory(String category);

    List<Expense> findAllByDateBetween(LocalDate starDate, LocalDate endDate);

    List<Expense> findByTitleContainingIgnoreCase(String title);

}
