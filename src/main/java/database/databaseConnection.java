package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database connection class handles my database connection setup.
 */
public class databaseConnection {
    //Database URL, Username, Password
    private static final String URL = "jdbc:mysql://localhost:3306/income_expense_tracker";
    private static final String USER = "root";
    private static final String PASSWORD = "";
/**
 * Establishes and returns a connection to the database.
 * @throws SQLException if teh connection fails
 */
    public static Connection getConnection()throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
