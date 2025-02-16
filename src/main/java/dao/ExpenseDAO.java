package dao;
import database.databaseConnection;
import dto.Expense;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {


    public List<Expense> getAllExpenses() {
        List<Expense> expenses = new ArrayList<>();
        String query = "SELECT * FROM expenses";

        try(Connection connection = databaseConnection.getConnection();
            PreparedStatement prepState = connection.prepareStatement(query);
            ResultSet resSet = prepState.executeQuery()){

            while(resSet.next()){
                expenses.add(new Expense(
                        resSet.getInt("expenseID"),
                        resSet.getString("title"),
                        resSet.getString("category"),
                        resSet.getDouble("amount"),
                        resSet.getDate("dateIncurred")
                ));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return expenses;
    }
    public void addExpense(String title, String category, double amount, Date dateIncurred){
        String query = "INSERT INTO expenses (title, category, amount, dateIncurred) VALUES (?, ?, ?, ?)";

        try(Connection connection = databaseConnection.getConnection();
        PreparedStatement prepState = connection.prepareStatement(query)){
            prepState.setString(1, title);
            prepState.setString(2, category);
            prepState.setDouble(3, amount);
            prepState.setDate(4, dateIncurred);
            prepState.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public boolean deleteExpense(int expenseID){
        String query = "DELETE FROM expenses WHERE expenseID = ?";

        try(Connection connection = databaseConnection.getConnection();
        PreparedStatement prepState = connection.prepareStatement(query)){


            prepState.setInt(1, expenseID);
            int rowsAffected = prepState.executeUpdate();
            return rowsAffected > 0;
    }catch (SQLException e){
        e.printStackTrace();}
        return false;
    }

    public List<Expense> getMonthlyExpenses(String month){
        List<Expense> expenses = new ArrayList<>();
        String query = "SELECT * FROM expenses WHERE DATE_FORMAT(dateIncurred, '%Y-%m') = ?";

        try(Connection connection = databaseConnection.getConnection();
            PreparedStatement prepState = connection.prepareStatement(query)){
            prepState.setString(1, month);
            ResultSet resSet = prepState.executeQuery();
            while (resSet.next()){
                int id = resSet.getInt("expenseID");
                String description = resSet.getString("title");
                String category = resSet.getString("category");
                double amount = resSet.getDouble("amount");
                Date date = resSet.getDate("dateIncurred");
                expenses.add(new Expense(id, description, category, amount, date));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return expenses;
    }
}
