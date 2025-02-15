package dao;
import database.databaseConnection;
import dto.Income;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IncomeDAO {
    public List<Income> getAllIncomes(){
        List<Income> incomes = new ArrayList<>();
        String query = "SELECT * FROM income";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet resSet = ps.executeQuery()) {

            while(resSet.next()){
                incomes.add(new Income(
                        resSet.getInt("incomeID"),
                        resSet.getString("title"),
                        resSet.getDouble("amount"),
                        resSet.getDate("dateEarned")
                ));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return incomes;
    }

    public void addIncome(String title, double amount, Date dateEarned){
        String query = "INSERT INTO income (title, amount, dateEarned) VALUES (?,?,?)";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement prepState = connection.prepareStatement(query)) {

            prepState.setString(1, title);
            prepState.setDouble(2, amount);
            prepState.setDate(3, dateEarned);

            prepState.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteIncome(int incomeID){
        String query = "DELETE FROM income WHERE incomeID = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement prepState = connection.prepareStatement(query)) {

            prepState.setInt(1, incomeID);
            prepState.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}