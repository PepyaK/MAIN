package com.example.demo2;

import java.sql.*;

public class Authentication {

    public static boolean authenticateUser(String login, String password) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/huntlyschemas", "root", "pepka")) {
            String query = "SELECT * FROM huntlyschemas.accounts WHERE login = ? AND password = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, login);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return true; // Пользователь найден, вход успешен
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Если не найдено совпадений
    }
}
