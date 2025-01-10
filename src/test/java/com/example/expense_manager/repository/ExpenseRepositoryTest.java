package com.example.expense_manager.repository;


import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.util.List;
import com.example.expense_manager.model.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;






@DataJpaTest
public class ExpenseRepositoryTest {

    @Autowired
    private ExpenseRepository expenseRepository;

    private Expense expense1;
    private Expense expense2;
    private Expense expense3;

    @BeforeEach
    public void setUp() {
        expense1 = new Expense();
        expense1.setCategory("Food");
        expense1.setDate(LocalDate.of(2023, 1, 1));
        expense1.setTitle("Grocery Shopping");

        expense2 = new Expense();
        expense2.setCategory("Transport");
        expense2.setDate(LocalDate.of(2023, 1, 2));
        expense2.setTitle("Bus Ticket");

        expense3 = new Expense();
        expense3.setCategory("Food");
        expense3.setDate(LocalDate.of(2023, 1, 3));
        expense3.setTitle("Restaurant Dinner");

        expenseRepository.save(expense1);
        expenseRepository.save(expense2);
        expenseRepository.save(expense3);
    }

    @Test
    public void testFindByCategory() {
        List<Expense> foodExpenses = expenseRepository.findByCategory("Food");
        assertThat(foodExpenses).hasSize(2).contains(expense1, expense3);
    }

    @Test
    public void testFindAllByDateBetween() {
        List<Expense> januaryExpenses = expenseRepository.findAllByDateBetween(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 31));
        assertThat(januaryExpenses).hasSize(3).contains(expense1, expense2, expense3);
    }

    @Test
    public void testFindByTitleContainingIgnoreCase() {
        List<Expense> groceryExpenses = expenseRepository.findByTitleContainingIgnoreCase("grocery");
        assertThat(groceryExpenses).hasSize(1).contains(expense1);
    }
}