package com.example.expense_manager.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.persistence.Table;

/**
 * Represents an expense entity with details such as title, amount, category,
 * and date.
 * This entity is mapped to the "expenses" table in the database.
 * 
 * Fields:
 * - id: The unique identifier for the expense. It is auto-generated.
 * - title: The title of the expense. It is mandatory and cannot be blank.
 * - amount: The amount of the expense. It is required and must be a positive
 * value.
 * - category: The category of the expense. It is mandatory and cannot be blank.
 * - date: The date of the expense. It is required.
 * 
 * Annotations:
 * - @Entity: Specifies that the class is an entity and is mapped to a database
 * table.
 * - @Table: Specifies the name of the database table to be used for mapping.
 * - @Id: Specifies the primary key of the entity.
 * - @GeneratedValue: Provides the specification of generation strategies for
 * the primary keys.
 * - @NotBlank: Ensures that the annotated field is not null and the trimmed
 * length is greater than zero.
 * - @NotNull: Ensures that the annotated field is not null.
 * - @Positive: Ensures that the annotated field is a positive number.
 * 
 * Getters and Setters:
 * - getId(): Returns the unique identifier of the expense.
 * - setId(Long id): Sets the unique identifier of the expense.
 * - getTitle(): Returns the title of the expense.
 * - setTitle(String title): Sets the title of the expense.
 * - getAmount(): Returns the amount of the expense.
 * - setAmount(Double amount): Sets the amount of the expense.
 * - getCategory(): Returns the category of the expense.
 * - setCategory(String category): Sets the category of the expense.
 * - getDate(): Returns the date of the expense.
 * - setDate(LocalDate date): Sets the date of the expense.
 */
@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is mandatory.")
    private String title;

    @NotNull(message = "Amount is required.")
    @Positive(message = "Amount should be positive.")
    private Double amount;

    @NotBlank(message = "Category is required.")
    private String category;

    @NotNull(message = "Date is required.")
    private LocalDate date;

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
