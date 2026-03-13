package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import models.Product
import models.Employee

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val products = getProducts()

        // Исходный список
        val originalText = products.joinToString("\n") {
            "${it.name} – ${it.price} руб. (${if (it.inStock) "в наличии" else "нет"})"
        }
        findViewById<TextView>(R.id.textOriginal).text = originalText

        // Только в наличии
        val inStockProducts = products.filter { it.inStock }
        val inStockText = inStockProducts.joinToString("\n") { "${it.name} – ${it.price} руб." }
        findViewById<TextView>(R.id.textInStock).text = inStockText

        // Электроника в наличии, отсортированная по цене
        val electronicsSorted = products
            .filter { it.category == "Электроника" && it.inStock }
            .sortedBy { it.price }
            .map { "${it.name} – ${it.price} руб." }
        findViewById<TextView>(R.id.textSorted).text = electronicsSorted.joinToString("\n")


        // Список сотрудников -----------
        val employees = getEmployees()

        // Все сотрудники
        val originalEmployeesText = employees.joinToString("\n") { employee ->
            "${employee.name}, ${employee.department}, ${employee.salary} руб., стаж: ${employee.experience} лет"
        }
        findViewById<TextView>(R.id.textOriginalEmployees).text = originalEmployeesText

        // Сотрудники с зарплатой > 100000
        val highSalaryEmployees = employees.filter { it.salary > 100000.0 }

        val highSalaryText = if (highSalaryEmployees.isEmpty()) {"Нет сотрудников с зарплатой > 100 000 руб."}
        else {
            highSalaryEmployees.joinToString("\n") { emp -> "${emp.name} — ${emp.salary} руб."}
        }
        findViewById<TextView>(R.id.textHighSalary).text = highSalaryText

        // Сотрудники по стажу
        val sortedByExperience = employees
            .sortedByDescending { it.experience }
            .map { "${it.name} — стаж: ${it.experience} лет" }

        findViewById<TextView>(R.id.textSortedEmployees).text = sortedByExperience.joinToString("\n")
    }
    private fun getProducts(): List<Product> {
        return listOf(
            Product("Ноутбук", "Электроника", 75000.0, true),
            Product("Мышь", "Электроника", 1500.0, true),
            Product("Книга 'Котлин'", "Книги", 1200.0, false),
            Product("Флешка 64GB", "Электроника", 2000.0, true),
            Product("Блокнот", "Канцелярия", 300.0, true),
            Product("Ручка", "Канцелярия", 50.0, false),
            Product("Монитор", "Электроника", 25000.0, true)
        )
    }

    private fun getEmployees(): List<Employee> {
        return listOf(
            Employee("Иванов Иван Иванович", "Отдел разработки", 150000.0, 7),
            Employee("Петров Пётр Петрович", "Отдел маркетинга", 95000.0, 6),
            Employee("Антонов Антон Антонович", "Отдел разработки", 180000.0, 10),
            Employee("Павлов Павел Павлович", "Бухгалтерия", 110000.0, 12),
        )
    }
}