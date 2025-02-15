package Main;
import dao.ExpenseDAO;
import dao.IncomeDAO;
import dto.Expense;
import dto.Income;

import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ExpenseDAO expenseDAO = new ExpenseDAO();
        IncomeDAO incomeDAO = new IncomeDAO();

        expenseDAO.addExpense("Diesel","Car", 60.00, Date.valueOf("2025-02-16"));
        incomeDAO.addIncome("Freelancing", 200.00, Date.valueOf("2025-03-19"));

        List<Expense> expenses = expenseDAO.getAllExpenses();
        List<Income> incomes = incomeDAO.getAllIncomes();

        System.out.println("Expenses: " + expenses);
        System.out.println("Incomes: " + incomes);

    }
}