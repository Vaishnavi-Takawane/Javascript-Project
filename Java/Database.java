import java.sql.*;

public class JDBCBasicExample {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase";
    static final String USER = "DB_testUname";
    static final String PASS = "DB_testPass";
    static final String EMAIL = "DB_email@gmail.com";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            // Create a statement
            statement = connection.createStatement();

            // Insert operation
            String insertQuery = "INSERT INTO users (username, password) VALUES ('john_doe', 'password123')";
            statement.executeUpdate(insertQuery);
            System.out.println("User inserted successfully!");

            String selectRecord = "SELECT * FROM users where username= "+"john";

            // Display operation
            String displayQuery = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(displayQuery);

            // Display the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                System.out.println("ID: " + id + ", Username: " + username + ", Password: " + password);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
