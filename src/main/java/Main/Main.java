package Main;
import dao.ExpenseDAO;
import dao.IncomeDAO;
import dto.Expense;
import dto.Income;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExpenseDAO expenseDAO = new ExpenseDAO();
        IncomeDAO incomeDAO = new IncomeDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. List all expenses");
            System.out.println("2. Add new expense");
            System.out.println("3. Delete and expense by ID");
            System.out.println("4. List all incomes");
            System.out.println("5. Add new income");
            System.out.println("6. Delete expense by ID");
            System.out.println("7. List income and expenses for a particular month");
            System.out.println("0. Exit");
            System.out.println("Enter your choice");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    List<Expense> expenses = expenseDAO.getAllExpenses();
                    System.out.println("Expense List: ");
                    for (Expense expense : expenses) {
                        System.out.println(expense);
                    }
                    break;
                case 2:
                    System.out.println("Enter expense description: ");
                    scanner.nextLine();
                    String expDesc = scanner.nextLine();
                    System.out.println("Enter expense category: ");
                    String expCategory = scanner.nextLine();
                    System.out.println("Enter expense amount: ");
                    double expAmount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Enter expense date: ");
                    String expDateStr = scanner.nextLine();
                    Date expDate = Date.valueOf(expDateStr);

                    expenseDAO.addExpense(expDesc, expCategory, expAmount, expDate);
                    System.out.println("Expense added successfully!");
                    break;
                case 3:
                    System.out.println("Enter expense ID to delete: ");
                    int expIDToDelete = scanner.nextInt();
                    if(expenseDAO.deleteExpense(expIDToDelete)){
                        System.out.println("Expense deleted successfully!");
                    }else {
                        System.out.println("Expense with that ID not found!");
                    }
                    break;
                case 4:
                    List<Income> incomes = incomeDAO.getAllIncomes();
                    System.out.println("Income List: ");
                    for (Income income : incomes) {
                        System.out.println(income);
                    }
                    break;
                case 5:
                    System.out.println("Enter income source: ");
                    scanner.nextLine();
                    String incSource = scanner.nextLine();
                    System.out.println("Enter income amount: ");
                    double incAmount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Enter income date(YYYY-MM-DD): ");
                    String incDateStr = scanner.nextLine();
                    Date incDate = Date.valueOf(incDateStr);

                    incomeDAO.addIncome(incSource, incAmount, incDate);
                    System.out.println("Income added successfully!");
                    break;
                case 6:
                    System.out.println("Enter income ID to delete: ");
                    int incomeIDToDelete = scanner.nextInt();
                    if(incomeDAO.deleteIncome(incomeIDToDelete)){
                        System.out.println("Income deleted successfully!");
                    }else {
                        System.out.println("Income with that ID not found!");
                    }
                    break;
                case 7:
                    scanner.nextLine();
                    System.out.println("Enter month (YYYY-MM) to list income and expenses: ");
                    String month = scanner.nextLine();
                    System.out.println("Listing income and expenses for month:  " + month);

                    double totalIncome = 0;
                    double totalExpenses = 0;

                    List<Income> monthlyIncomes = incomeDAO.getMonthlyIncomes(month);
                    for(Income income: monthlyIncomes){
                        System.out.println(income);
                        totalIncome += income.getAmount();
                    }

                    List<Expense> monthlyExpenses = expenseDAO.getMonthlyExpenses(month);
                    for (Expense expense: monthlyExpenses){
                        System.out.println(expense);
                        totalExpenses += expense.getAmount();
                    }

                    System.out.println("Total income for the month: "+totalIncome);
                    System.out.println("Total expenses for the month: "+totalExpenses);
                    System.out.println("Balance for the month: "+(totalIncome-totalExpenses));
                    break;
                case 0:
                    System.out.println("Exiting system!");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}